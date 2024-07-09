package com.example.draggerdependency

import dagger.Component

@Component
interface UserRegistrationComponent {
    fun getUserRegistrationService() : UserRegistrationService

    fun getEmailService() : EmailService
}