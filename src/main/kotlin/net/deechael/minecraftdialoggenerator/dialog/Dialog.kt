package net.deechael.minecraftdialoggenerator.dialog

import ink.pmc.advkt.component.RootComponentKt
import kotlinx.serialization.Serializable
import net.deechael.minecraftdialoggenerator.dialog.body.DialogBody
import net.deechael.minecraftdialoggenerator.dialog.body.ItemDialogBodyBuilder
import net.deechael.minecraftdialoggenerator.dialog.body.PlainMessageDialogBodyBuilder
import net.kyori.adventure.text.Component

@Serializable
sealed class Dialog {

    abstract fun buildJson(): String

}

abstract class DialogBuilder {

    internal var title: Component? = null
    internal var externalTitle: Component? = null
    internal var body: List<DialogBody>? = null
    internal var canCloseWithEscape: Boolean = true

    abstract fun build(): Dialog

}

class DialogBodyBuilder {

    internal val bodies = mutableListOf<DialogBody>()

    fun build(): List<DialogBody> {
        return bodies.toList()
    }

}

fun DialogBuilder.title(content: RootComponentKt.() -> Unit) {
    this.title = RootComponentKt().apply(content).build()
}

fun DialogBuilder.externalTitle(content: RootComponentKt.() -> Unit) {
    this.externalTitle = RootComponentKt().apply(content).build()
}

fun DialogBuilder.body(content: DialogBodyBuilder.() -> Unit) {
    this.body = DialogBodyBuilder().apply(content).build()
}

fun DialogBuilder.canCloseWithEscape(value: Boolean) {
    this.canCloseWithEscape = value
}

fun DialogBuilder.dontCloseWithEscape() {
    this.canCloseWithEscape = false
}

fun DialogBuilder.closeWithEscape() {
    this.canCloseWithEscape = true
}

fun DialogBodyBuilder.item(builder: ItemDialogBodyBuilder.() -> Unit) {
    this.bodies.add(ItemDialogBodyBuilder().apply(builder).build())
}

fun DialogBodyBuilder.plainMessage(builder: PlainMessageDialogBodyBuilder.() -> Unit) {
    this.bodies.add(PlainMessageDialogBodyBuilder().apply(builder).buildDialogBody())
}