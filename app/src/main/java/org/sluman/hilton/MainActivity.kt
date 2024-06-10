package org.sluman.hilton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import org.sluman.hilton.presentation.ui.MainScreenRoot
import org.sluman.hilton.presentation.ui.theme.HiltonTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HiltonTheme {
                MainScreenRoot()
            }
        }
    }
}
