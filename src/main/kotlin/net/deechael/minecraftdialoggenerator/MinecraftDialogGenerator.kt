package net.deechael.minecraftdialoggenerator

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.ClassDiscriminatorMode
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import net.deechael.minecraftdialoggenerator.dialog.Dialog
import net.deechael.minecraftdialoggenerator.support.clickEventSupport
import net.deechael.minecraftdialoggenerator.support.componentSupport
import net.deechael.minecraftdialoggenerator.support.keySupport

@OptIn(ExperimentalSerializationApi::class)
val minecraftDialogGeneratorJson = Json {
    classDiscriminatorMode = ClassDiscriminatorMode.NONE
    serializersModule = SerializersModule {
        clickEventSupport()
        componentSupport()
        keySupport()
        /*
        polymorphicDefaultDeserializer(Dialog::class) { className ->
            when (className) {
                "ConfirmationDialog" -> ConfirmationDialog.serializer()
                "DialogListDialog" -> DialogListDialog.serializer()
                "MultiActionDialog" -> MultiActionDialog.serializer()
                "MultiActionInputDialog" -> MultiActionInputDialog.serializer()
                "NoticeDialog" -> NoticeDialog.serializer()
                "ServerLinksDialog" -> ServerLinksDialog.serializer()
                "SimpleInputFormDialog" -> SimpleInputFormDialog.serializer()
                else -> error("Unknown type $className")
            }
        }
        polymorphicDefaultDeserializer(DialogBody::class) { className ->
            when (className) {
                "PlainMessageDialogBody" -> PlainMessageDialogBody.serializer()
                "ItemDialogBody" -> ItemDialogBody.serializer()
                else -> error("Unknown type $className")
            }
        }
        polymorphicDefaultDeserializer(InputControl::class) { className ->
            when (className) {
                "BooleanInputControl" -> BooleanInputControl.serializer()
                "NumberRangeInputControl" -> NumberRangeInputControl.serializer()
                "SingleOptionInputControl" -> SingleOptionInputControl.serializer()
                "TextInputControl" -> TextInputControl.serializer()
                else -> error("Unknown type $className")
            }
        }
        polymorphicDefaultDeserializer(SubmitMethod::class) { className ->
            when (className) {
                "CommandTemplateSubmitMethod" -> CommandTemplateSubmitMethod.serializer()
                "CustomFormSubmitMethod" -> CustomFormSubmitMethod.serializer()
                "CustomTemplateSubmitMethod" -> CustomTemplateSubmitMethod.serializer()
                else -> error("Unknown type $className")
            }
        }

        contextual(PlainMessageDialogBody::class, PlainMessageDialogBody.serializer())
        contextual(ItemDialogBody::class, ItemDialogBody.serializer())
        contextual(BooleanInputControl::class, BooleanInputControl.serializer())
        contextual(NumberRangeInputControl::class, NumberRangeInputControl.serializer())
        contextual(SingleOptionInputControl::class, SingleOptionInputControl.serializer())
        contextual(TextInputControl::class, TextInputControl.serializer())
        contextual(CommandTemplateSubmitMethod::class, CommandTemplateSubmitMethod.serializer())
        contextual(CustomFormTemplateSubmitMethod::class, CustomFormTemplateSubmitMethod.serializer())
        contextual(CustomTemplateSubmitMethod::class, CustomTemplateSubmitMethod.serializer())
        contextual(ConfirmationDialog::class, ConfirmationDialog.serializer())
        contextual(DialogListDialog::class, DialogListDialog.serializer())
        contextual(MultiActionDialog::class, MultiActionDialog.serializer())
        contextual(MultiActionInputDialog::class, MultiActionInputDialog.serializer())
        contextual(NoticeDialog::class, NoticeDialog.serializer())
        contextual(ServerLinksDialog::class, ServerLinksDialog.serializer())
        contextual(SimpleInputFormDialog::class, SimpleInputFormDialog.serializer())*/
    }
}

fun generateDialog(generator: () -> Dialog): String {
    return generator().buildJson()
}
