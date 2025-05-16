import ink.pmc.advkt.component.aqua
import ink.pmc.advkt.component.blue
import ink.pmc.advkt.component.gold
import ink.pmc.advkt.component.green
import ink.pmc.advkt.component.newline
import ink.pmc.advkt.component.red
import ink.pmc.advkt.component.runCommand
import ink.pmc.advkt.component.text
import net.deechael.minecraftdialoggenerator.dialog.Dialog
import net.deechael.minecraftdialoggenerator.dialog.action
import net.deechael.minecraftdialoggenerator.dialog.action.label
import net.deechael.minecraftdialoggenerator.dialog.action.onClick
import net.deechael.minecraftdialoggenerator.dialog.actions
import net.deechael.minecraftdialoggenerator.dialog.body
import net.deechael.minecraftdialoggenerator.dialog.body.contents
import net.deechael.minecraftdialoggenerator.dialog.body.itemStack
import net.deechael.minecraftdialoggenerator.dialog.columns
import net.deechael.minecraftdialoggenerator.dialog.confirmationDialog
import net.deechael.minecraftdialoggenerator.dialog.dialog
import net.deechael.minecraftdialoggenerator.dialog.dialogListDialog
import net.deechael.minecraftdialoggenerator.dialog.dialogs
import net.deechael.minecraftdialoggenerator.dialog.externalTitle
import net.deechael.minecraftdialoggenerator.dialog.item
import net.deechael.minecraftdialoggenerator.dialog.multiActionDialog
import net.deechael.minecraftdialoggenerator.dialog.no
import net.deechael.minecraftdialoggenerator.dialog.plainMessage
import net.deechael.minecraftdialoggenerator.dialog.title
import net.deechael.minecraftdialoggenerator.dialog.yes
import net.deechael.minecraftdialoggenerator.generateDialog
import net.deechael.minecraftdialoggenerator.item.count
import net.deechael.minecraftdialoggenerator.item.id
import net.kyori.adventure.key.Key

fun main() {
    val generatedConfirmationDialog = generateConfirmationDialog()
    val generatedMultiActionDialog = generateMultiActionDialog()
    val generatedDialogListDialog = generateDialogListDialog(listOf(
        generatedConfirmationDialog,
        generatedMultiActionDialog
    ))
    println("/dialog @p ${generateDialog { generatedConfirmationDialog }}")
    println("/dialog @p ${generateDialog { generatedMultiActionDialog }}")
    println("/dialog @p ${generateDialog { generatedDialogListDialog }}")
}

fun generateDialogListDialog(dialogs: List<Dialog>): Dialog {
    return dialogListDialog {
        title {
            text("测试一下 DialogList") with gold()
        }
        body {
            plainMessage {
                contents {
                    text("我不知道能塞什么，那就塞个物品看看吧")
                }
            }
            item {
                itemStack {
                    id(Key.key("minecraft", "diamond"))
                    count(23)
                }
            }
        }
        dialogs {
            for (dialog in dialogs) {
                dialog {
                    dialog
                }
            }
        }
        columns(3)
    }
}

fun generateConfirmationDialog(): Dialog {
    return confirmationDialog {
        title {
            text("测试一下 Confirmation") with gold()
        }
        externalTitle {
            text("ConfirmationDialog 测试") with blue()
        }
        body {
            plainMessage {
                contents {
                    text("第一行内容")
                    newline()
                    text("第二行内容")
                }
            }
        }
        yes {
            label {
                text("点了就会说 yes 哦") with green()
            }
            onClick(runCommand("/say 我点击了 yes"))
        }
        no {
            label {
                text("点了就会说 no 哦") with red()
            }
            onClick(runCommand("/say 我点击了 no"))
        }
    }
}

fun generateMultiActionDialog(): Dialog {
    return multiActionDialog {
        title {
            text("测试一下 MultiAction") with gold()
        }
        externalTitle {
            text("MultiAction 测试") with aqua()
        }
        body {
            plainMessage {
                contents {
                    text("第一行内容")
                    newline()
                    text("第二行内容")
                }
            }
        }
        actions {
            action {
                label {
                    text("选项1")
                }
                onClick(runCommand("/say 我选择了 1"))
            }
            action {
                label {
                    text("选项2")
                }
                onClick(runCommand("/say 我选择了 2"))
            }
            action {
                label {
                    text("选项3")
                }
                onClick(runCommand("/say 我选择了 3"))
            }
            action {
                label {
                    text("选项4")
                }
                onClick(runCommand("/say 我选择了 3"))
            }
        }
    }
}