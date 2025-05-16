@file:OptIn(ExperimentalSerializationApi::class)

package net.deechael.minecraftdialoggenerator.dialog.body

import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import net.deechael.minecraftdialoggenerator.item.ItemStack
import net.deechael.minecraftdialoggenerator.item.ItemStackBuilder
import net.deechael.minecraftdialoggenerator.minecraftDialogGeneratorJson
import net.kyori.adventure.text.Component

@Serializable
data class ItemDescription(
    @Contextual
    val contents: Component,
    val width: Int = 200
)

@Serializable
data class ItemDialogBody(
    val item: ItemStack,
    val description: ItemDescription? = null,
    @SerialName("show_decorations")
    val showDecorations: Boolean = true,
    @SerialName("show_tooltip")
    val showTooltip: Boolean = true,
    val width: Int = 16,
    val height: Int = 16,
    val type: String = "minecraft:item"

) : DialogBody() {

    override fun buildJson(): String {
        return minecraftDialogGeneratorJson.encodeToString(this)
    }

}

class ItemDialogBodyBuilder {

    var item: ItemStack? = null
    var description: ItemDescription? = null
    var showDecorations: Boolean = true
    var showTooltip: Boolean = true
    var width: Int = 16
    var height: Int = 16

    fun build(): ItemDialogBody {
        return ItemDialogBody(
            this.item!!,
            this.description,
            this.showDecorations,
            this.showTooltip,
            this.width,
            this.height
        )
    }

}

fun ItemDialogBodyBuilder.itemStack(builder: ItemStackBuilder.() -> Unit) {
    this.item = ItemStackBuilder().apply(builder).build()
}

fun ItemDialogBodyBuilder.description(builder: PlainMessageDialogBodyBuilder.() -> Unit) {
    this.description = PlainMessageDialogBodyBuilder().apply(builder).buildItemDescription()
}

fun ItemDialogBodyBuilder.showDecorations() {
    this.showDecorations = true
}

fun ItemDialogBodyBuilder.dontShowDecorations() {
    this.showDecorations = false
}

fun ItemDialogBodyBuilder.showTooltip() {
    this.showTooltip = true
}

fun ItemDialogBodyBuilder.dontShowTooltip() {
    this.showTooltip = false
}

fun ItemDialogBodyBuilder.width(value: Int) {
    this.width = value
}

fun ItemDialogBodyBuilder.height(value: Int) {
    this.height = value
}