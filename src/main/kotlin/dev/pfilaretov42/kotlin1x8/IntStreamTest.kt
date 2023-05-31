package dev.pfilaretov42.kotlin1x8

fun main() {
    // generate list of decreasing numbers
    val start = 5
    val end = 0
    val sequence = generateSequence(start) { it - 1 }.take(start - end)
    println(sequence.joinToString(", "))

    val intProgression = start .. end
    println(intProgression)
}
