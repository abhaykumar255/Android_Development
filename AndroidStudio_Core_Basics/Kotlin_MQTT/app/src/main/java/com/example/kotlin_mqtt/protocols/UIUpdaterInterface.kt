package com.example.kotlin_mqtt.protocols

interface UIUpdaterInterface {
    fun resetUIWithConnection(status: Boolean)
    fun updateStatusViewWith(status: String)
    fun update(message: String)
}