package kr.ac.kumoh.ce.s20190531.s23w03lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.kumoh.ce.s20190531.s23w03lotto.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var main: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)

        main.btnGenerate.setOnClickListener {
            val uniqueNumbers = mutableSetOf<Int>()

            // Keep generating numbers until we have 6 unique numbers
            while (uniqueNumbers.size < 6) {
                uniqueNumbers.add(Random.nextInt(1, 46))
            }

            // Convert the set to a list and set the text fields
            val numberList = uniqueNumbers.toList()
            main.num1.text = numberList[0].toString()
            main.num2.text = numberList[1].toString()
            main.num3.text = numberList[2].toString()
            main.num4.text = numberList[3].toString()
            main.num5.text = numberList[4].toString()
            main.num6.text = numberList[5].toString()
        }
    }
}
