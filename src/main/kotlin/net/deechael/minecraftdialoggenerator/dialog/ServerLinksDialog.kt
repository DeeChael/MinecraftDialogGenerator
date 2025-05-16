@file:OptIn(ExperimentalSerializationApi::class)

package net.deechael.minecraftdialoggenerator.dialog

import ink.pmc.advkt.component.ClickEventKt
import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.deechael.minecraftdialoggenerator.dialog.body.DialogBody
import net.deechael.minecraftdialoggenerator.minecraftDialogGeneratorJson
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.Style

@Serializable
data class ServerLinksDialog(
    @Contextual
    val title: Component,
    @SerialName("external_title")
    @Contextual
    val externalTitle: Component? = null,
    val body: List<DialogBody>? = null,
    @SerialName("can_close_with_escape")
    val canCloseWithEscape: Boolean = true,
    @Contextual
    @SerialName("on_cancel")
    val onCancel: ClickEvent? = null,
    val columns: Int = 2,
    @SerialName("button_width")
    val buttonWidth: Int = 150,
    val type: String = "minecraft:server_links"
) : Dialog() {

    override fun buildJson(): String {
        return minecraftDialogGeneratorJson.encodeToString(this)
    }

}

fun serverLinksDialog(builder: ServerLinksDialogBuilder.() -> Unit): Dialog {
    return ServerLinksDialogBuilder().apply(builder).build()
}

class ServerLinksDialogBuilder : DialogBuilder() {

    var onCancel: ClickEvent? = null
    var columns: Int = 2
    var buttonWidth: Int = 150

    override fun build(): Dialog {
        return ServerLinksDialog(
            this.title!!,
            this.externalTitle,
            this.body,
            this.canCloseWithEscape,
            this.onCancel,
            this.columns,
            this.buttonWidth
        )
    }

}

fun ServerLinksDialogBuilder.onCancel(event: ClickEvent) {
    this.onCancel = event
}

fun ServerLinksDialogBuilder.onCancel(event: ClickEventKt) {
    this.onCancel = event.with(Style.style().build()).clickEvent()
}

fun ServerLinksDialogBuilder.columns(value: Int) {
    this.columns = value
}

fun ServerLinksDialogBuilder.buttonWidth(value: Int) {
    this.buttonWidth = value
}