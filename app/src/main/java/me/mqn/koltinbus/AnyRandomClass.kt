package me.mqn.koltinbus

class AnyRandomClass {

    suspend fun triggerEvent() {
        KotlinBus.publish("Test data to publish")
    }
}