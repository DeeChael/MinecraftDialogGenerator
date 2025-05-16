package net.deechael.minecraftdialoggenerator.support

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import kotlinx.serialization.modules.SerializersModuleBuilder
import net.kyori.adventure.text.event.ClickEvent

internal object ClickEventSerializer : KSerializer<ClickEvent> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ClickEvent") {
        element("action", String.serializer().descriptor)
        element("url", String.serializer().descriptor, isOptional = true)
        element("path", String.serializer().descriptor, isOptional = true)
        element("command", String.serializer().descriptor, isOptional = true)
        element("page", Int.serializer().descriptor, isOptional = true)
        element("value", String.serializer().descriptor, isOptional = true)
    }

    override fun deserialize(decoder: Decoder): ClickEvent {
        return decoder.decodeStructure(descriptor) {
            lateinit var action: String
            lateinit var clickEvent: ClickEvent
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> action = decodeStringElement(descriptor, 0)
                    1 -> clickEvent = ClickEvent.openUrl(decodeStringElement(descriptor, 1))
                    2 -> clickEvent = ClickEvent.openFile(decodeStringElement(descriptor, 2))
                    3 -> clickEvent = if (action == "run_command") {
                        ClickEvent.runCommand(decodeStringElement(descriptor, 3))
                    } else {
                        ClickEvent.suggestCommand(decodeStringElement(descriptor, 3))
                    }

                    4 -> clickEvent = ClickEvent.changePage(decodeIntElement(descriptor, 4))
                    5 -> clickEvent = ClickEvent.copyToClipboard(decodeStringElement(descriptor, 5))
                    CompositeDecoder.DECODE_DONE -> break
                    else -> throw SerializationException("Unexpected index $index")
                }
            }
            clickEvent
        }
    }

    override fun serialize(
        encoder: Encoder,
        value: ClickEvent
    ) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.action().name)
            when (value.action()) {
                ClickEvent.Action.OPEN_URL -> encodeStringElement(descriptor, 1, value.value())
                ClickEvent.Action.OPEN_FILE -> encodeStringElement(descriptor, 2, value.value())
                ClickEvent.Action.RUN_COMMAND -> encodeStringElement(descriptor, 3, value.value())
                ClickEvent.Action.SUGGEST_COMMAND -> encodeStringElement(descriptor, 3, value.value())
                ClickEvent.Action.CHANGE_PAGE -> encodeIntElement(descriptor, 4, value.value().toInt())
                ClickEvent.Action.COPY_TO_CLIPBOARD -> encodeStringElement(descriptor, 5, value.value())
            }
        }
    }

}

fun SerializersModuleBuilder.clickEventSupport() {
    contextual(ClickEvent::class, ClickEventSerializer)
}