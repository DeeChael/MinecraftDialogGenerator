@file:OptIn(ExperimentalSerializationApi::class)

package net.deechael.minecraftdialoggenerator.dialog

import ink.pmc.advkt.component.ClickEventKt
import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import net.deechael.minecraftdialoggenerator.dialog.body.DialogBody
import net.deechael.minecraftdialoggenerator.minecraftDialogGeneratorJson
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.Style

@Serializable
data class DialogListDialog(
    @Contextual
    val title: Component,
    @SerialName("external_title")
    @Contextual
    val externalTitle: Component? = null,
    val body: List<DialogBody>? = null,
    @SerialName("can_close_with_escape")
    val canCloseWithEscape: Boolean = true,
    val dialogs: List<Dialog>,
    @Contextual
    @SerialName("on_cancel")
    val onCancel: ClickEvent? = null,
    val columns: Int = 2,
    @SerialName("button_width")
    val buttonWidth: Int = 150,
    val type: String = "minecraft:dialog_list"
) : Dialog() {

    override fun buildJson(): String {
        return minecraftDialogGeneratorJson.encodeToString(this)
    }

}

fun dialogListDialog(builder: DialogListDialogBuilder.() -> Unit): Dialog {
    return DialogListDialogBuilder().apply(builder).build()
}

class DialogListDialogBuilder : DialogBuilder() {

    var dialogs: List<Dialog> = emptyList()
    var onCancel: ClickEvent? = null
    var columns: Int = 2
    var buttonWidth: Int = 150

    override fun build(): Dialog {
        return DialogListDialog(
            this.title!!,
            this.externalTitle,
            this.body,
            this.canCloseWithEscape,
            this.dialogs,
            this.onCancel,
            this.buttonWidth
        )
    }

}

class DialogsBuilder {

    internal val dialogs = mutableListOf<Dialog>()

    fun build(): List<Dialog> {
        return dialogs.toList()
    }

}

fun DialogListDialogBuilder.dialogs(builder: DialogsBuilder.() -> Unit) {
    this.dialogs = DialogsBuilder().apply(builder).build()
}

fun DialogListDialogBuilder.onCancel(event: ClickEvent) {
    this.onCancel = event
}

fun DialogListDialogBuilder.onCancel(event: ClickEventKt) {
    this.onCancel = event.with(Style.style().build()).clickEvent()
}

fun DialogListDialogBuilder.columns(value: Int) {
    this.columns = value
}

fun DialogListDialogBuilder.buttonWidth(value: Int) {
    this.buttonWidth = value
}

fun DialogsBuilder.dialog(block: () -> Dialog) {
    dialogs.add(block())
}