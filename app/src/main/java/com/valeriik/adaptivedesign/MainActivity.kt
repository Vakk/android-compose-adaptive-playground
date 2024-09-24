package com.valeriik.adaptivedesign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.valeriik.adaptivedesign.ui.adaptive.AdaptiveExampleScreen
import com.valeriik.adaptivedesign.ui.responsive.ResponsiveExampleScreen
import com.valeriik.adaptivedesign.ui.theme.AdaptiveDesignTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            AdaptiveDesignTheme {
                NavHost(
                    modifier = Modifier.fillMaxWidth(),
                    navController = navController,
                    startDestination = "/"
                ) {
                    composable("/") {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate("/responsive")
                                }) {
                                Text(text = "Go responsive")
                            }
                            Button(onClick = {
                                navController.navigate("/adaptive")
                            }) {
                                Text(text = "Go adaptive")
                            }
                        }
                    }
                    composable("/responsive") {
                        ResponsiveExampleScreen()
                    }

                    composable("/adaptive") {
                        AdaptiveExampleScreen()
                    }
                }
            }
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AdaptiveDesignTheme {
        Greeting("Android")
    }
}