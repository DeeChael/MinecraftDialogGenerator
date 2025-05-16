@file:OptIn(ExperimentalSerializationApi::class)

package net.deechael.minecraftdialoggenerator.dialog.input

import ink.pmc.advkt.component.RootComponentKt
import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import net.deechael.minecraftdialoggenerator.minecraftDialogGeneratorJson
import net.kyori.adventure.text.Component

@Serializable
data class BooleanInputControl(
    val key: String,
    @Contextual
    val label: Component,
    val initial: Boolean = false,
    @SerialName("on_true")
    val onTrue: String = "true",
    @SerialName("on_false")
    val onFalse: String = "false",
    val type: String = "minecraft:boolean"
) : InputControl() {

    override fun buildJson(): String {
        return minecraftDialogGeneratorJson.encodeToString(this)
    }

}

class BooleanInputControlBuilder : InputControlBuilder() {

    var label: Component? = null
    var initial: Boolean = false
    var onTrue: String = "true"
    var onFalse: String = "false"

    override fun build(): InputControl {
        return BooleanInputControl(
            this.key!!,
            this.label!!,
            this.initial,
            this.onTrue,
            this.onFalse
        )
    }

}

fun BooleanInputControlBuilder.label(content: RootComponentKt.() -> Unit) {
    this.label = RootComponentKt().apply(content).build()
}

fun BooleanInputControlBuilder.initial(value: Boolean) {
    initial = value
}

fun BooleanInputControlBuilder.onTrue(value: String) {
    onTrue = value
}

fun BooleanInputControlBuilder.onFalse(value: String) {
    onFalse = value
}