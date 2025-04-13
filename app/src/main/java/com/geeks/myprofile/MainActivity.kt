package com.geeks.myprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import com.geeks.myprofile.ui.ProfileScreen
import com.geeks.myprofile.ui.theme.MyProfileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyProfileTheme  {
                Scaffold { padding ->
                    ProfileScreen(padding)
                }
            }
        }
    }
}