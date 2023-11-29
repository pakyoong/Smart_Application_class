package kr.ac.kumoh.ce.s20190531.s23w04carddealer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class CardDealerViewModel : ViewModel() {
    // 카드 정보를 저장하는 LiveData
    private var _cards = MutableLiveData<IntArray>(IntArray(5) { -1 })
    val cards: LiveData<IntArray>
        get() = _cards

    // 핸드 랭킹 정보를 저장하는 LiveData
    private var _HandRankings = MutableLiveData<String>()
    val HandRankings: LiveData<String>
        get() = _HandRankings

    // 시뮬레이션 결과를 저장하는 LiveData
    private var _simulationResults = MutableLiveData<Map<String, Double>>()
    val simulationResults: LiveData<Map<String, Double>>
        get() = _simulationResults

    // 주어진 횟수만큼 시뮬레이션을 실행하는 함수
    fun runSimulation(times: Int) {
        val statistics = mutableMapOf<String, Int>()

        repeat(times) {
            val cards = IntArray(5) { -1 }
            for (i in cards.indices) {
                var num: Int
                do {
                    num = Random.nextInt(52)
                } while (num in cards)
                cards[i] = num
            }
            cards.sort()

            val handRanking = simpleClassification_Hand(cards)
            statistics[handRanking] = statistics.getOrDefault(handRanking, 0) + 1
        }

        val probabilities = mutableMapOf<String, Double>()
        for ((key, value) in statistics) {
            val probability = (value.toDouble() / times) * 100
            probabilities[key] = probability
        }

        _simulationResults.value = probabilities
    }

    // 카드를 셔플하는 함수
    fun shuffle() {
        var num = 0
        val newCards = IntArray(5) { -1 }

        for (i in newCards.indices) {
            // 중복 검사
            do {
                num = Random.nextInt(52)
            } while (num in newCards) // newCards.contains(num)<- 가능
            newCards[i] = num
        }

        // 카드 정렬
        newCards.sort()

        _cards.value = newCards

        _HandRankings.value = classification_Hand(newCards)
    }

    // 카드 핸드의 타입을 결정하는 함수
    private fun determineHandType(cards: IntArray): String {
        val numbers = cards.map { getCardNumber(it) }.sorted()
        val suits = cards.map { getCardSuit(it) }

        if (suits.distinct().size == 1) {
            if (isConsecutive(numbers)) {
                if (numbers == listOf(10, 11, 12, 13, 14)) return "Royal Straight Flush"
                return "Straight Flush"
            }
            return "Flush"
        }

        val groupedNumbers = numbers.groupBy { it }.map { it.value.size }.sortedDescending()
        when {
            groupedNumbers[0] == 4 -> return "Four Card"
            groupedNumbers == listOf(3, 2) -> return "Full House"
            groupedNumbers[0] == 3 -> return "Triple"
            groupedNumbers.take(2) == listOf(2, 2) -> return "Two Pair"
            groupedNumbers[0] == 2 -> return "One Pair"
            isConsecutive(numbers) -> return "Straight"
            else -> return "Top"
        }
    }

    // 카드 핸드의 자세한 정보를 반환하는 함수(셔플 버튼)
    private fun classification_Hand(cards: IntArray): String {
        val handType = determineHandType(cards)
        val numbers = cards.map { getCardNumber(it) }.sorted()
        val suits = cards.map { getCardSuit(it) }

        return when (handType) {
            "Royal Straight Flush" -> "Royal Straight Flush of ${suits[0]}"
            "Straight Flush" -> "Straight Flush of ${suits[0]}"
            "Flush" -> "Flush of ${suits[0]}"
            "Four Card" -> "Four Card of ${
                cardNumberToName(numbers.groupBy { it }.filter { it.value.size == 4 }.keys.first())
            }"

            "Full House" -> {
                val three = cardNumberToName(numbers.groupBy { it }
                    .filter { it.value.size == 3 }.keys.first())
                val two = cardNumberToName(numbers.groupBy { it }
                    .filter { it.value.size == 2 }.keys.first())
                "Full House with Three of $three and Two of $two"
            }

            "Triple" -> "Triple of ${
                cardNumberToName(numbers.groupBy { it }.filter { it.value.size == 3 }.keys.first())
            }"

            "Two Pair" -> {
                val pairs = numbers.groupBy { it }
                    .filter { it.value.size == 2 }.keys.map { cardNumberToName(it) }
                    .joinToString(" and ")
                "Two Pair of $pairs"
            }

            "One Pair" -> "One Pair of ${
                cardNumberToName(numbers.groupBy { it }.filter { it.value.size == 2 }.keys.first())
            }"

            "Straight" -> "Straight from ${cardNumberToName(numbers.first())} to ${
                cardNumberToName(
                    numbers.last()
                )
            }"

            "Top" -> "Top card is ${cardNumberToName(numbers.last())}"
            else -> handType
        }
    }

    // 간략한 카드 핸드 정보를 반환하는 함수(시뮬레이션 버튼)
    private fun simpleClassification_Hand(cards: IntArray): String {
        return determineHandType(cards)
    }

    private fun isConsecutive(numbers: List<Int>): Boolean {
        return numbers.zipWithNext().all { (a, b) -> b - a == 1 }
    }

    private fun getCardNumber(card: Int): Int {
        return card % 13 + 1
    }

    private fun getCardSuit(card: Int): String {
        return when (card / 13) {
            0 -> "Spades"
            1 -> "Diamonds"
            2 -> "Hearts"
            3 -> "Clubs"
            else -> "Unknown"
        }
    }

    private fun cardNumberToName(number: Int): String {
        return when (number) {
            1 -> "Ace"
            11 -> "Jack"
            12 -> "Queen"
            13 -> "King"
            else -> number.toString()
        }
    }
}