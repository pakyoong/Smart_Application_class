package kr.ac.kumoh.ce.s20190531.s23w03lotto

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlin.random.Random
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LottoViewModel : ViewModel( ) {
    private var _numbers = MutableLiveData<IntArray>(IntArray(6) { 0 })
    val numbers: LiveData<IntArray>
        get() = _numbers
    fun generate() {
        var num = 0
        val newNumbers = IntArray(6) { 0 }
        //newNumbers.forEach { Log.i("newNumbers!!!", "$it") }

        for (i in newNumbers.indices) {
            // 중복 검사
            do {
                num = Random.nextInt(1, 46)
            } while (newNumbers.contains(num))
            newNumbers[i] = num

        }

        // 정렬
        newNumbers.sort()
        _numbers.value = newNumbers
    }
}