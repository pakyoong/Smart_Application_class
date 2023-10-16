package kr.ac.kumoh.ce.s20190531.s23w06intent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import kr.ac.kumoh.ce.s20190531.s23w06intent.ui.theme.S23W06IntentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S23W06IntentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier
    ){ 
        Button(
            modifier = modifier
                .fillMaxWidth(),
            onClick = {
                val uri = Uri.parse("https://www.youtube.com/results?search_query=%EC%97%90%EC%8A%A4%ED%8C%8C+%EB%85%B8%EB%9E%98%EB%B0%A9")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(context, intent, null)
            }
        ) {
            Text("유튜브")
        }
        Button(
            modifier = modifier
                .fillMaxWidth(),
            onClick = {
                val uri =
                    Uri.parse("https://www.instagram.com/pa__kyoong/")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(context, intent, null)
            }
        ) {
            Text("인스타그램")
        }
        Button(
            modifier = modifier
                .fillMaxWidth(),
            onClick = {
                val uri =
                    Uri.parse("https://www.facebook.com/hyeonbin2/")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(context, intent, null)
            }
        ) {
            Text("페이스북")
        }
        Button(
            modifier = modifier
                .fillMaxWidth(),
            onClick = {
                val uri =
                    Uri.parse("geo:36.145014,128.393047?z=17")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(context, intent, null)
            }
        ) {
            Text("좌표")
        }
        Button(
            modifier = modifier
                .fillMaxWidth(),
            onClick = {
                val uri =
                    Uri.parse("geo:34.7398694,127.6811652")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(context, intent, null)
            }
        ) {
            Text("우리집")
        }
        Button(
            modifier = modifier
                .fillMaxWidth(),
            onClick = {
                val uri =
                    Uri.parse("tel:01089398400")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(context, intent, null)
            }
        ) {
            Text("전화")
        }
        Button(
            modifier = modifier
                .fillMaxWidth(),
            onClick = {
                val uri =
                    Uri.parse("geo:0,0?q=금오공과대학교")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(context, intent, null)
            }
        ) {
            Text("주소 또는 상호")
        }
        Button(
            modifier = modifier
                .fillMaxWidth(),
            onClick = {
                val uri =
                    Uri.parse("sms:01073167616")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.putExtra("sms_body", "류현식 하이여")
                startActivity(context, intent, null)
            }
        ) {
            Text("문자")
        }
        Button(
            modifier = modifier
                .fillMaxWidth(),
            onClick = {
                val intent = Intent(context, SecondActivity::class.java)
                startActivity(context, intent, null)
            }
        ) {
            Text("Activity")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    S23W06IntentTheme {
        Greeting("Android")
    }
}