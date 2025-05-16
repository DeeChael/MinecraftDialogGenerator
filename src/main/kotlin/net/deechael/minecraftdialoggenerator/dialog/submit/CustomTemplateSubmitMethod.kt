@file:OptIn(ExperimentalSerializationApi::class)

package net.deechael.minecraftdialoggenerator.dialog.submit

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import net.deechael.minecraftdialoggenerator.minecraftDialogGeneratorJson
import net.kyori.adventure.key.Key

@Serializable
data class CustomTemplateSubmitMethod(
    val id: Key,
    val template: String,
    val type: String = "minecraft:custom_template"
) : SubmitMethod() {

    override fun buildJson(): String {
        return minecraftDialogGeneratorJson.encodeToString(this)
    }

}

class CustomTemplateSubmitMethodBuilder {

    var id: Key? = null
    var template: String? = null

    fun build(): CustomTemplateSubmitMethod {
        return CustomTemplateSubmitMethod(
            id!!,
            template!!
        )
    }

}

fun CustomTemplateSubmitMethodBuilder.id(value: Key) {
    id = value
}

fun CustomTemplateSubmitMethodBuilder.template(value: String) {
    template = value
}

fun submitCustomTemplate(builder: CustomTemplateSubmitMethodBuilder.() -> Unit): SubmitMethod {
    return CustomTemplateSubmitMethodBuilder().apply(builder).build()
}
