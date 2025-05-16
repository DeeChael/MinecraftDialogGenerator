@file:OptIn(ExperimentalSerializationApi::class)

package net.deechael.minecraftdialoggenerator.dialog

import ink.pmc.advkt.component.ClickEventKt
import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import net.deechael.minecraftdialoggenerator.dialog.action.ClickAction
import net.deechael.minecraftdialoggenerator.dialog.action.ClickActionBuilder
import net.deechael.minecraftdialoggenerator.dialog.body.DialogBody
import net.deechael.minecraftdialoggenerator.minecraftDialogGeneratorJson
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.Style

@Serializable
data class MultiActionDialog(
    @Contextual
    val title: Component,
    val actions: List<ClickAction>, // not empty
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
    val type: String = "minecraft:multi_action"
) : Dialog() {

    override fun buildJson(): String {
        return minecraftDialogGeneratorJson.encodeToString(this)
    }

}

class ActionsBuilder {

    internal val actions = mutableListOf<ClickAction>()

    fun build(): List<ClickAction> {
        return actions.toList()
    }

}

fun multiActionDialog(builder: MultiActionDialogBuilder.() -> Unit): Dialog {
    return MultiActionDialogBuilder().apply(builder).build()
}

class MultiActionDialogBuilder : DialogBuilder() {

    var actions: List<ClickAction> = emptyList()
    var onCancel: ClickEvent? = null
    var columns: Int = 2

    override fun build(): Dialog {
        return MultiActionDialog(
            this.title!!,
            this.actions,
            this.externalTitle,
            this.body,
            this.canCloseWithEscape,
            this.onCancel,
            this.columns
        )
    }

}

fun MultiActionDialogBuilder.actions(builder: ActionsBuilder.() -> Unit) {
    this.actions = ActionsBuilder().apply(builder).build()
}

fun MultiActionDialogBuilder.onCancel(event: ClickEvent) {
    this.onCancel = event
}

fun MultiActionDialogBuilder.onCancel(event: ClickEventKt) {
    this.onCancel = event.with(Style.style().build()).clickEvent()
}

fun MultiActionDialogBuilder.columns(value: Int) {
    this.columns = value
}

fun ActionsBuilder.action(action: ClickActionBuilder.() -> Unit) {
    this.actions.add(ClickActionBuilder().apply(action).build())
}