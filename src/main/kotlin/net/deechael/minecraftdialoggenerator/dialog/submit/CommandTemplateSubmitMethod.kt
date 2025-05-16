@file:OptIn(ExperimentalSerializationApi::class)

package net.deechael.minecraftdialoggenerator.dialog.submit

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import net.deechael.minecraftdialoggenerator.minecraftDialogGeneratorJson

@Serializable
data class CommandTemplateSubmitMethod(
    val template: String,
    val type: String = "minecraft:command_template"
) : SubmitMethod() {

    override fun buildJson(): String {
        return minecraftDialogGeneratorJson.encodeToString(this)
    }

}

class CommandTemplateSubmitMethodBuilder {

    var template: String? = null

    fun build(): CommandTemplateSubmitMethod {
        return CommandTemplateSubmitMethod(
            template!!
        )
    }

}

fun CommandTemplateSubmitMethodBuilder.template(value: String) {
    template = value
}

fun submitCommandTemplate(builder: CommandTemplateSubmitMethodBuilder.() -> Unit): SubmitMethod {
    return CommandTemplateSubmitMethodBuilder().apply(builder).build()
}