package net.deechael.minecraftdialoggenerator.dialog.submit

import kotlinx.serialization.Serializable

@Serializable
sealed class SubmitMethod {

    abstract fun buildJson(): String

}