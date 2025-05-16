@file:OptIn(ExperimentalSerializationApi::class)

package net.deechael.minecraftdialoggenerator.dialog

import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import net.deechael.minecraftdialoggenerator.dialog.body.DialogBody
import net.deechael.minecraftdialoggenerator.dialog.input.BooleanInputControlBuilder
import net.deechael.minecraftdialoggenerator.dialog.input.InputControl
import net.deechael.minecraftdialoggenerator.dialog.input.NumberRangeInputControlBuilder
import net.deechael.minecraftdialoggenerator.dialog.input.SingleOptionInputControlBuilder
import net.deechael.minecraftdialoggenerator.dialog.input.TextInputControlBuilder
import net.deechael.minecraftdialoggenerator.dialog.submit.SubmitMethod
import net.deechael.minecraftdialoggenerator.minecraftDialogGeneratorJson
import net.kyori.adventure.text.Component

@Serializable
data class SimpleInputFormDialog(
    @Contextual
    val title: Component,
    val inputs: List<InputControl>, // not empty
    val action: SubmitMethod,
    @SerialName("external_title")
    @Contextual
    val externalTitle: Component? = null,
    val body: List<DialogBody>? = null,
    @SerialName("can_close_with_escape")
    val canCloseWithEscape: Boolean = true,
    val type: String = "minecraft:simple_input_form"
) : Dialog() {

    override fun buildJson(): String {
        return minecraftDialogGeneratorJson.encodeToString(this)
    }

}

class InputsBuilder {

    internal val inputs = mutableListOf<InputControl>()

    fun build(): List<InputControl> {
        return inputs.toList()
    }

}

fun simpleInputFormDialog(builder: SimpleInputFormDialogBuilder.() -> Unit): Dialog {
    return SimpleInputFormDialogBuilder().apply(builder).build()
}

class SimpleInputFormDialogBuilder : DialogBuilder() {

    var inputs: List<InputControl> = emptyList()
    var action: SubmitMethod? = null

    override fun build(): Dialog {
        return SimpleInputFormDialog(
            this.title!!,
            this.inputs,
            this.action!!,
            this.externalTitle,
            this.body,
            this.canCloseWithEscape,
        )
    }

}

fun SimpleInputFormDialogBuilder.inputs(builder: InputsBuilder.() -> Unit) {
    this.inputs = InputsBuilder().apply(builder).build()
}

fun SimpleInputFormDialogBuilder.action(builder: () -> SubmitMethod) {
    this.action = builder()
}

fun InputsBuilder.text(builder: TextInputControlBuilder.() -> Unit) {
    this.inputs.add(TextInputControlBuilder().apply(builder).build())
}

fun InputsBuilder.boolean(builder: BooleanInputControlBuilder.() -> Unit) {
    this.inputs.add(BooleanInputControlBuilder().apply(builder).build())
}

fun InputsBuilder.numberRange(builder: NumberRangeInputControlBuilder.() -> Unit) {
    this.inputs.add(NumberRangeInputControlBuilder().apply(builder).build())
}

fun InputsBuilder.singleOption(builder: SingleOptionInputControlBuilder.() -> Unit) {
    this.inputs.add(SingleOptionInputControlBuilder().apply(builder).build())
}