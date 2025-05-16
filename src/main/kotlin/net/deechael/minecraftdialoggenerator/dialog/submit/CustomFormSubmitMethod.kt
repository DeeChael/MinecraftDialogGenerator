@file:OptIn(ExperimentalSerializationApi::class)

package net.deechael.minecraftdialoggenerator.dialog.submit

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import net.deechael.minecraftdialoggenerator.minecraftDialogGeneratorJson
import net.kyori.adventure.key.Key

@Serializable
data class CustomFormSubmitMethod(
    val id: Key,
    val type: String = "minecraft:custom_form"
) : SubmitMethod() {

    override fun buildJson(): String {
        return minecraftDialogGeneratorJson.encodeToString(this)
    }

}

class CustomFormSubmitMethodBuilder {

    var id: Key? = null

    fun build(): CustomFormSubmitMethod {
        return CustomFormSubmitMethod(
            id!!
        )
    }

}

fun CustomFormSubmitMethodBuilder.id(value: Key) {
    id = value
}

fun submitCustomForm(builder: CustomFormSubmitMethodBuilder.() -> Unit): SubmitMethod {
    return CustomFormSubmitMethodBuilder().apply(builder).build()
}