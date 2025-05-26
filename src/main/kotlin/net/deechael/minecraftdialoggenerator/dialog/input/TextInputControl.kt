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
data class Multiline(
    @SerialName("max_lines")
    val maxLines: Int? = null,
    val height: Int? = null
)

@Serializable
data class TextInputControl(
    val key: String,
    @Contextual
    val label: Component,
    val width: Int = 200,
    @SerialName("label_visible")
    val labelVisible: Boolean = true,
    val initial: String = "",
    @SerialName("max_length")
    val maxLength: Int = 32,
    val multiline: Multiline? = null,
    val type: String = "minecraft:text"
) : InputControl() {

    override fun buildJson(): String {
        return minecraftDialogGeneratorJson.encodeToString(this)
    }

}

class TextInputControlBuilder : InputControlBuilder() {

    var label: Component? = null
    var width: Int = 200
    var labelVisible: Boolean = true
    var initial: String = ""
    var maxLength: Int = 32
    var multiline: Multiline? = null

    override fun build(): InputControl {
        return TextInputControl(
            this.key!!,
            this.label!!,
            this.width,
            this.labelVisible,
            this.initial,
            this.maxLength,
            this.multiline,
        )
    }

}

fun TextInputControlBuilder.label(content: RootComponentKt.() -> Unit) {
    this.label = RootComponentKt().apply(content).build()
}

fun TextInputControlBuilder.width(value: Int) {
    this.width = value
}

fun TextInputControlBuilder.labelVisible(visibility: Boolean) {
    this.labelVisible = visibility
}

fun TextInputControlBuilder.initial(value: String) {
    initial = value
}

fun TextInputControlBuilder.maxLength(value: Int) {
    this.maxLength = value
}

fun TextInputControlBuilder.multiline(builder: MultilineBuilder.() -> Unit) {
    this.multiline = MultilineBuilder().apply(builder).build()
}

class MultilineBuilder {

    var maxLines: Int? = null
    var height: Int? = null

    fun build(): Multiline {
        return Multiline(maxLines, height)
    }

}

fun MultilineBuilder.maxLines(value: Int) {
    this.maxLines = value
}

fun MultilineBuilder.height(value: Int) {
    this.height = value
}