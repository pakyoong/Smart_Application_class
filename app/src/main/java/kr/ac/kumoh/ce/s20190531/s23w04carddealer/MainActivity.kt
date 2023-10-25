package kr.ac.kumoh.ce.s20190531.s23w04carddealer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.kumoh.ce.s20190531.s23w04carddealer.databinding.ActivityMainBinding

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var main: ActivityMainBinding
    private lateinit var model: CardDealerViewModel
    private var isProcessing = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)

        // ViewModel 초기화
        model = ViewModelProvider(this)[CardDealerViewModel::class.java]


        // 초기 카드 조커 이미지 설정
        model.cards.observe(this, Observer {
            val res = IntArray(5)
            for (i in res.indices) {
                if (it[i] == -1) {
                    res[i] = resources.getIdentifier("c_red_joker", "drawable", packageName)
                } else {
                    res[i] = resources.getIdentifier(
                        getCardName(it[i]),
                        "drawable",
                        packageName
                    )
                }
            }
            main.card1.setImageResource(res[0])
            main.card2.setImageResource(res[1])
            main.card3.setImageResource(res[2])
            main.card4.setImageResource(res[3])
            main.card5.setImageResource(res[4])
        })

        // 셔플 버튼 클릭 리스너 설정
        main.btnShuffle.setOnClickListener {
            if (!isProcessing) {
                isProcessing = true
                model.shuffle()

                main.tvHandRanking.visibility = View.VISIBLE

                main.tvHandRanking.postDelayed({
                    main.tvHandRanking.visibility = View.INVISIBLE
                    isProcessing = false
                }, 2000)
            }
        }

        // 시뮬레이션 버튼 클릭 리스너 설정
        main.btnSimulation.setOnClickListener {
            if (!isProcessing) {
                isProcessing = true
                model.runSimulation(10000) // 예를 들어 10,000번의 시뮬레이션을 실행
            }
        }

        // 핸드 랭킹 정보 변경 리스너 설정
        model.HandRankings.observe(this, Observer { handRanking ->
            main.tvHandRanking.text = handRanking
            main.tvHandRanking.visibility = View.VISIBLE

            main.tvHandRanking.postDelayed({
                main.tvHandRanking.visibility = View.INVISIBLE
            }, 2000)
        })

        // 시뮬레이션 결과 변경 리스너 설정
        model.simulationResults.observe(this, Observer { results ->
            val handOrder = listOf(
                "Royal Straight Flush",
                "Straight Flush",
                "Four Card",
                "Full House",
                "Flush",
                "Straight",
                "Triple",
                "Two Pair",
                "One Pair",
                "Top"
            )

            val output = StringBuilder()
            for (hand in handOrder) {
                val probability = results[hand] ?: continue
                output.append("$hand: ${"%.2f".format(probability)}%\n")
            }

            main.tvHandRanking.text = output.toString()
            main.tvHandRanking.visibility = View.VISIBLE

            main.tvHandRanking.postDelayed({
                main.tvHandRanking.visibility = View.INVISIBLE
                isProcessing = false
            }, 20000)
        })
    }

    // 카드 이름을 결정하는 함수
    private fun getCardName(c: Int): String {
        var shape = when (c / 13) {
            0 -> "spades"
            1 -> "diamonds"
            2 -> "hearts"
            3 -> "clubs"
            else -> "error"
        }

        val number = when (c % 13) {
            0 -> "ace"
            in 1..9 -> (c % 13 + 1).toString()
            10 -> {
                shape = shape.plus("2")
                "jack"
            }

            11 -> {
                shape = shape.plus("2")
                "queen"
            }

            12 -> {
                shape = shape.plus("2")
                "king"
            }

            else -> "error"
        }
        return "c_${number}_of_${shape}"
    }
}
