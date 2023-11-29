package kr.ac.kumoh.ce.s20910531.s23w1101layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kr.ac.kumoh.ce.s20910531.s23w1101layout.ui.theme.S23W1101LayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    S23W1101LayoutTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//            Greeting("에이치 아이~")
            MyLinearLayout()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun MyLinearLayout() {
    Column {
        Row{
            Text(
                text = "Hello",
                modifier = Modifier
                    .background(Color.Yellow)
                    .padding(8.dp)
            )
            Text(
                text = "나는 컴공",
                modifier = Modifier
                    .background(Color.Cyan)
                    .padding(8.dp)
            )
        }
        Text(
            text = "어디서 해본 것 같아~",
            modifier = Modifier
                .background(Color.Magenta)
                .padding(8.dp)
        )
        Text(
            text = "월요일은 힘들어",
            modifier = Modifier
                .background(Color.Black)
                .padding(8.dp),
            color = Color.White
        )
    }
}