@file:OptIn(ExperimentalSerializationApi::class)

package net.deechael.minecraftdialoggenerator.dialog.input

import ink.pmc.advkt.component.RootComponentKt
import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import net.deechael.minecraftdialoggenerator.minecraftDialogGeneratorJson
import net.kyori.adventure.text.Component

@Serializable
data class NumberRangeInputControl(
    val key: String,
    @Contextual
    val label: Component,
    val start: Float, // inclusive
    val end: Float, // inclusive
    val steps: Int, // 1 means only has start and end
    val labelFormat: String = "options.generic_value",
    val width: Int = 200,
    val initial: Float = start, // will be rounded down nearest step, must be within range
    val type: String = "minecraft:number_range"
) : InputControl() {

    override fun buildJson(): String {
        return minecraftDialogGeneratorJson.encodeToString(this)
    }

}

class NumberRangeInputControlBuilder : InputControlBuilder() {

    var label: Component? = null
    var start: Float? = null
    var end: Float? = null
    var steps: Int? = null
    var labelFormat: String = "options.generic_value"
    var width: Int = 200
    var initial: Float = 0.0F

    internal var setInitial: Boolean = false

    override fun build(): InputControl {
        return NumberRangeInputControl(
            this.key!!,
            this.label!!,
            this.start!!,
            this.end!!,
            this.steps!!,
            this.labelFormat,
            this.width,
            this.initial
        )
    }

}

fun NumberRangeInputControlBuilder.label(content: RootComponentKt.() -> Unit) {
    this.label = RootComponentKt().apply(content).build()
}

fun NumberRangeInputControlBuilder.start(value: Float) {
    this.start = value
    if (!this.setInitial) {
        this.initial = value
    }
}

fun NumberRangeInputControlBuilder.end(value: Float) {
    this.end = value
}

fun NumberRangeInputControlBuilder.steps(value: Int) {
    this.steps = value
}

fun NumberRangeInputControlBuilder.labelFormat(value: String) {
    this.labelFormat = value
}

fun NumberRangeInputControlBuilder.width(value: Int) {
    this.width = value
}

fun NumberRangeInputControlBuilder.initial(value: Float) {
    this.setInitial = true
    initial = value
}