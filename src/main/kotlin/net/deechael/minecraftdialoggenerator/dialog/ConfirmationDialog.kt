@file:OptIn(ExperimentalSerializationApi::class)

package net.deechael.minecraftdialoggenerator.dialog

import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.deechael.minecraftdialoggenerator.dialog.action.ClickAction
import net.deechael.minecraftdialoggenerator.dialog.action.ClickActionBuilder
import net.deechael.minecraftdialoggenerator.dialog.body.DialogBody
import net.deechael.minecraftdialoggenerator.minecraftDialogGeneratorJson
import net.kyori.adventure.text.Component

@Serializable
data class ConfirmationDialog(
    @Contextual
    val title: Component,
    @SerialName("external_title")
    @Contextual
    val externalTitle: Component? = null,
    val body: List<DialogBody>? = null,
    @SerialName("can_close_with_escape")
    val canCloseWithEscape: Boolean = true,
    val yes: ClickAction = ClickAction(
        label = Component.translatable("gui.ok")
    ),
    val no: ClickAction = ClickAction(
        label = Component.translatable("gui.no")
    ),
    val type: String = "minecraft:confirmation"
) : Dialog() {

    override fun buildJson(): String {
        return minecraftDialogGeneratorJson.encodeToString(this)
    }

}

fun confirmationDialog(builder: ConfirmationDialogBuilder.() -> Unit): Dialog {
    return ConfirmationDialogBuilder().apply(builder).build()
}

class ConfirmationDialogBuilder : DialogBuilder() {

    var yes: ClickAction = ClickAction(
        label = Component.translatable("gui.ok")
    )
    var no: ClickAction = ClickAction(
        label = Component.translatable("gui.no")
    )

    override fun build(): Dialog {
        return ConfirmationDialog(
            this.title!!,
            this.externalTitle,
            this.body,
            this.canCloseWithEscape,
            this.yes,
            this.no
        )
    }

}

fun ConfirmationDialogBuilder.yes(action: ClickActionBuilder.() -> Unit) {
    this.yes = ClickActionBuilder().apply(action).build()
}

fun ConfirmationDialogBuilder.no(action: ClickActionBuilder.() -> Unit) {
    this.no = ClickActionBuilder().apply(action).build()
}