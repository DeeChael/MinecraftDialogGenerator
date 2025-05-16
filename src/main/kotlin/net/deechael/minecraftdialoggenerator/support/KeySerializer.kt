package net.deechael.minecraftdialoggenerator.support

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.SerializersModuleBuilder
import net.kyori.adventure.key.Key

object KeySerializer : KSerializer<Key> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Component") {
    }

    override fun deserialize(decoder: Decoder): Key {
        throw RuntimeException()
    }

    override fun serialize(
        encoder: Encoder,
        value: Key
    ) {
        encoder.encodeString(value.asString())
    }

}

fun SerializersModuleBuilder.keySupport() {
    contextual(Key::class, KeySerializer)
}