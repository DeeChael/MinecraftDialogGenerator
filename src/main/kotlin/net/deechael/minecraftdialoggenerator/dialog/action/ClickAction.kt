@file:OptIn(ExperimentalSerializationApi::class)

package net.deechael.minecraftdialoggenerator.dialog.action

import ink.pmc.advkt.component.ClickEventKt
import ink.pmc.advkt.component.RootComponentKt
import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.Style

@Serializable
open class ClickAction(
    @Contextual
    val label: Component,
    @Contextual
    val tooltip: Component? = null,
    val width: Int = 150,
    @Contextual
    @SerialName("on_click")
    val onClick: ClickEvent? = null,
)

class ClickActionBuilder {

    var label: Component? = null
    var tooltip: Component? = null
    var width: Int = 150
    var onClick: ClickEvent? = null

    fun build(): ClickAction {
        return ClickAction(
            this.label!!,
            this.tooltip,
            this.width,
            this.onClick,
        )
    }

}

fun ClickActionBuilder.label(content: RootComponentKt.() -> Unit) {
    this.label = RootComponentKt().apply(content).build()
}

fun ClickActionBuilder.tooltip(content: RootComponentKt.() -> Unit) {
    this.tooltip = RootComponentKt().apply(content).build()
}

fun ClickActionBuilder.width(value: Int) {
    this.width = value
}

fun ClickActionBuilder.onClick(event: ClickEvent) {
    this.onClick = event
}

fun ClickActionBuilder.onClick(event: ClickEventKt) {
    this.onClick = event.with(Style.style().build()).clickEvent()
}