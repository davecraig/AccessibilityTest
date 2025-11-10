package com.example.accessibilitytest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.example.accessibilitytest.ui.theme.AccessibilityTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AccessibilityTestTheme {
                AccessibilityTestApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@PreviewScreenSizes
@Composable
fun AccessibilityTestApp() {
    var currentDestination by rememberSaveable { mutableStateOf("Home") }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.semantics
                {
                    liveRegion = LiveRegionMode.Polite
                    contentDescription = currentDestination
                },
                title = {
                    Text(
                        text = currentDestination,
                        modifier = Modifier.semantics { invisibleToUser() }
                    )
                },
                navigationIcon = {
                    Button(
                        content = { Text("Back") },
                        onClick = {
                            when(currentDestination) {
                                "Home" ->  currentDestination = "Profile"
                                "Profile" ->  currentDestination = "Favorites"
                                "Favorites" ->  currentDestination = "Home"
                            }
                        },
                    )
                }
            )
        },
        content = { innerPadding ->
            Button(
                content = { Text("Press me") },
                onClick = {
                    when(currentDestination) {
                        "Home" ->  currentDestination = "Favorites"
                        "Favorites" ->  currentDestination = "Profile"
                        "Profile" ->  currentDestination = "Home"
                    }
                },
                modifier = Modifier.padding(innerPadding)
            )
        }
    )
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
    AccessibilityTestTheme {
        Greeting("Android")
    }
}