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
data class InputControlOption(
    val id: String,
    @Contextual
    val display: Component? = null,
    val initial: Boolean = false
)

@Serializable
data class SingleOptionInputControl(
    val key: String,
    @Contextual
    val label: Component,
    @SerialName("label_visible")
    val labelVisible: Boolean = true,
    val width: Int = 200,
    val options: List<InputControlOption> = emptyList(),
    val type: String = "minecraft:single_option"
) : InputControl() {

    override fun buildJson(): String {
        return minecraftDialogGeneratorJson.encodeToString(this)
    }

}

class SingleOptionInputControlBuilder : InputControlBuilder() {

    var label: Component? = null
    var labelVisible: Boolean = true
    var width: Int = 200
    var options: List<InputControlOption> = emptyList()

    internal var setInitial: Boolean = false

    override fun build(): InputControl {
        return SingleOptionInputControl(
            this.key!!,
            this.label!!,
            this.labelVisible,
            this.width,
            this.options
        )
    }

}

class OptionsBuilder {

    internal val options = mutableListOf<InputControlOption>()

    fun build(): List<InputControlOption> {
        return options.toList()
    }

}

class OptionBuilder {

    var id: String? = null
    var display: Component? = null
    var initial: Boolean = false

    fun build(): InputControlOption {
        return InputControlOption(
            this.id!!,
            this.display,
            this.initial
        )
    }

}

fun SingleOptionInputControlBuilder.label(content: RootComponentKt.() -> Unit) {
    this.label = RootComponentKt().apply(content).build()
}

fun SingleOptionInputControlBuilder.labelVisible(visibility: Boolean) {
    this.labelVisible = visibility
}

fun SingleOptionInputControlBuilder.width(width: Int) {
    this.width = width
}

fun SingleOptionInputControlBuilder.options(builder: OptionsBuilder.() -> Unit) {
    this.options = OptionsBuilder().apply(builder).build()
}

fun OptionsBuilder.option(builder: OptionBuilder.() -> Unit) {
    this.options.add(OptionBuilder().apply(builder).build())
}

fun OptionBuilder.id(id: String) {
    this.id = id
}

fun OptionBuilder.display(builder: RootComponentKt.() -> Unit) {
    this.display = RootComponentKt().apply(builder).build()
}

fun OptionBuilder.initial(builder: SingleOptionInputControlBuilder.() -> Unit) {
    this.initial = true
}