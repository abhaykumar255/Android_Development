package com.example.draggerdependency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.draggerdependency.ui.theme.DraggerDependencyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Now this will be automatically with the help of dragger
//        val userRepository = UserRepository()
//        val emailService = EmailService()
//
//        val userRepositoryService = UserRegistrationService(userRepository,emailService)

        val component = DaggerUserRegistrationComponent.builder().build()

        val userRepositoryService = component.getUserRegistrationService()
        val emailService = component.getEmailService()
        userRepositoryService.registerUser("rajput@gmai.com","Sample1")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMain(){
    MainActivity()
}