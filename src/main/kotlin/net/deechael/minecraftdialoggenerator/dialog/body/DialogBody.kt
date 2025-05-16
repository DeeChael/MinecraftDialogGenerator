package net.deechael.minecraftdialoggenerator.dialog.body

import kotlinx.serialization.Serializable

@Serializable
sealed class DialogBody {

    abstract fun buildJson(): String

}