package com.example.simplehttpclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.simplehttpclient.ui.theme.SimpleHttpClientTheme
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the UI using Compose
        setContent {
            SimpleHttpClientTheme {
                HealthScreen()
            }
        }
    }
}

@Composable
fun HealthScreen() {

    // This variable controls what text is shown on screen
    var result by remember { mutableStateOf("Result will appear here") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text="Hi!! I made this android app and it uses my own C server."
        )

        Button(onClick = {

            // Update UI immediately
            result = "Checking..."

            // Run network call in background
            thread {
                try {
                    // Android emulator → Mac localhost
                    val url = URL("http://10.0.2.2:8080/health")
                    val conn = url.openConnection() as HttpURLConnection
                    conn.requestMethod = "GET"
                    conn.connectTimeout = 5000
                    conn.readTimeout = 5000

                    val response =
                        conn.inputStream.bufferedReader().readText()

                    // Update state → UI updates automatically
                    result = response

                } catch (e: Exception) {
                    result = "Error: ${e.message}"
                }
            }

        }) {
            Text("Check Server Health")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = result)
    }
}
