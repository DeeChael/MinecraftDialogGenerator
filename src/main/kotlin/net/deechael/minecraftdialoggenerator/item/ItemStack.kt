package net.deechael.minecraftdialoggenerator.item

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import net.kyori.adventure.key.Key

@Serializable
data class ItemStack(
    @Contextual
    val id: Key,
    val count: Int = 1,
    // TODO: components
)

class ItemStackBuilder {

    var id: Key? = null
    var count: Int = 1

    fun build(): ItemStack {
        return ItemStack(
            this.id!!,
            this.count
        )
    }

}

fun ItemStackBuilder.id(id: Key) {
    this.id = id
}

fun ItemStackBuilder.count(count: Int) {
    this.count = count
}