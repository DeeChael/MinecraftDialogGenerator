package net.deechael.minecraftdialoggenerator.dialog.input

import kotlinx.serialization.Serializable

@Serializable
sealed class InputControl {

    abstract fun buildJson(): String

}

abstract class InputControlBuilder {

    internal var key: String? = null

    abstract fun build(): InputControl

}

fun InputControlBuilder.key(value: String) {
    this.key = key
}