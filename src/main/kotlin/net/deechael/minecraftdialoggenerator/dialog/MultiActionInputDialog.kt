@file:OptIn(ExperimentalSerializationApi::class)

package net.deechael.minecraftdialoggenerator.dialog

import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import net.deechael.minecraftdialoggenerator.dialog.body.DialogBody
import net.deechael.minecraftdialoggenerator.dialog.input.InputControl
import net.deechael.minecraftdialoggenerator.dialog.submit.CommandTemplateSubmitMethodBuilder
import net.deechael.minecraftdialoggenerator.dialog.submit.CustomFormSubmitMethodBuilder
import net.deechael.minecraftdialoggenerator.dialog.submit.CustomTemplateSubmitMethodBuilder
import net.deechael.minecraftdialoggenerator.dialog.submit.SubmitMethod
import net.deechael.minecraftdialoggenerator.minecraftDialogGeneratorJson
import net.kyori.adventure.text.Component

@Serializable
data class MultiActionInputDialog(
    @Contextual
    val title: Component,
    val inputs: List<InputControl>, // not empty
    val actions: List<SubmitMethod>, // not empty
    @SerialName("external_title")
    @Contextual
    val externalTitle: Component? = null,
    val body: List<DialogBody>? = null,
    @SerialName("can_close_with_escape")
    val canCloseWithEscape: Boolean = true,
    val type: String = "minecraft:multi_action_input_form"
) : Dialog() {

    override fun buildJson(): String {
        return minecraftDialogGeneratorJson.encodeToString(this)
    }

}

class SubmitMethodsBuilder {

    internal val actions = mutableListOf<SubmitMethod>()

    fun build(): List<SubmitMethod> {
        return actions.toList()
    }

}

fun multiActionInputDialog(builder: MultiActionInputDialogBuilder.() -> Unit): Dialog {
    return MultiActionInputDialogBuilder().apply(builder).build()
}

class MultiActionInputDialogBuilder : DialogBuilder() {

    var inputs: List<InputControl> = emptyList()
    var actions: List<SubmitMethod> = emptyList()

    override fun build(): Dialog {
        return MultiActionInputDialog(
            this.title!!,
            this.inputs,
            this.actions,
            this.externalTitle,
            this.body,
            this.canCloseWithEscape,
        )
    }

}

fun MultiActionInputDialogBuilder.inputs(builder: InputsBuilder.() -> Unit) {
    this.inputs = InputsBuilder().apply(builder).build()
}

fun MultiActionInputDialogBuilder.actions(builder: SubmitMethodsBuilder.() -> Unit) {
    this.actions = SubmitMethodsBuilder().apply(builder).build()
}

fun SubmitMethodsBuilder.commandTemplate(builder: CommandTemplateSubmitMethodBuilder.() -> Unit) {
    this.actions.add(CommandTemplateSubmitMethodBuilder().apply(builder).build())
}

fun SubmitMethodsBuilder.customTemplate(builder: CustomTemplateSubmitMethodBuilder.() -> Unit) {
    this.actions.add(CustomTemplateSubmitMethodBuilder().apply(builder).build())
}

fun SubmitMethodsBuilder.customForm(builder: CustomFormSubmitMethodBuilder.() -> Unit) {
    this.actions.add(CustomFormSubmitMethodBuilder().apply(builder).build())
}