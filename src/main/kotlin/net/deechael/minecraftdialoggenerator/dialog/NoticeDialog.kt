@file:OptIn(ExperimentalSerializationApi::class)

package net.deechael.minecraftdialoggenerator.dialog

import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import net.deechael.minecraftdialoggenerator.dialog.action.ClickAction
import net.deechael.minecraftdialoggenerator.dialog.action.ClickActionBuilder
import net.deechael.minecraftdialoggenerator.dialog.body.DialogBody
import net.deechael.minecraftdialoggenerator.minecraftDialogGeneratorJson
import net.kyori.adventure.text.Component

@Serializable
data class NoticeDialog(
    @Contextual
    val title: Component,
    @SerialName("external_title")
    @Contextual
    val externalTitle: Component? = null,
    val body: List<DialogBody>? = null,
    @SerialName("can_close_with_escape")
    val canCloseWithEscape: Boolean = true,
    val action: ClickAction = ClickAction(
        label = Component.translatable("gui.ok")
    ),
    val type: String = "minecraft:notice"
) : Dialog() {

    override fun buildJson(): String {
        return minecraftDialogGeneratorJson.encodeToString(this)
    }

}

fun noticeDialog(builder: NoticeDialogBuilder.() -> Unit): Dialog {
    return NoticeDialogBuilder().apply(builder).build()
}

class NoticeDialogBuilder : DialogBuilder() {

    var action: ClickAction? = null

    override fun build(): Dialog {
        return NoticeDialog(
            this.title!!,
            this.externalTitle,
            this.body,
            this.canCloseWithEscape,
            this.action!!,
        )
    }

}

fun NoticeDialogBuilder.action(action: ClickActionBuilder.() -> Unit) {
    this.action = ClickActionBuilder().apply(action).build()
}