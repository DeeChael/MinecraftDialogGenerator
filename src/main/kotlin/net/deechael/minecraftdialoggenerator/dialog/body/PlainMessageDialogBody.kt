package net.deechael.minecraftdialoggenerator.dialog.body

import ink.pmc.advkt.component.RootComponentKt
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import net.deechael.minecraftdialoggenerator.minecraftDialogGeneratorJson
import net.kyori.adventure.text.Component

@Serializable
data class PlainMessageDialogBody(
    @Contextual
    val contents: Component,
    val width: Int = 200,
    val type: String = "minecraft:item"
) : DialogBody() {

    override fun buildJson(): String {
        return minecraftDialogGeneratorJson.encodeToString(this)
    }

}

class PlainMessageDialogBodyBuilder {

    var contents: Component? = null
    var width: Int = 200

    fun buildDialogBody(): PlainMessageDialogBody {
        return PlainMessageDialogBody(
            contents!!,
            width,
        )
    }

    fun buildItemDescription(): ItemDescription {
        return ItemDescription(
            contents!!,
            width
        )
    }

}

fun PlainMessageDialogBodyBuilder.contents(content: RootComponentKt.() -> Unit) {
    this.contents = RootComponentKt().apply(content).build()
}

fun PlainMessageDialogBodyBuilder.width(width: Int) {
    this.width = width
}