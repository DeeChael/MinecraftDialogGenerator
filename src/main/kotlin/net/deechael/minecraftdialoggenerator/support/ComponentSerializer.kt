package net.deechael.minecraftdialoggenerator.support

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.modules.SerializersModuleBuilder
import net.deechael.minecraftdialoggenerator.minecraftDialogGeneratorJson
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer

object ComponentSerializer : KSerializer<Component> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Component") {
    }

    override fun deserialize(decoder: Decoder): Component {
        throw RuntimeException()
    }

    override fun serialize(
        encoder: Encoder,
        value: Component
    ) {
        encoder.encodeSerializableValue(
            JsonElement.serializer(),
            minecraftDialogGeneratorJson.decodeFromString<JsonElement>(GsonComponentSerializer.gson().serialize(value))
        )
    }

}

fun SerializersModuleBuilder.componentSupport() {
    contextual(Component::class, ComponentSerializer)
}