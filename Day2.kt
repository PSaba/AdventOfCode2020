
import java.io.File
import java.util.*

fun main() {
	println(passwordsCorrect1(getList("inputDay2")))
	println(passwordsCorrect2(getList("inputDay2")))
}

fun getList(file: String): List<List<Any>> {
	val lines: List<List<String>> = File(file).readLines().map{it -> it.split(" ")}
	val numbersCleaned = mutableListOf<List<Any>>()
	for (line in lines) {
		val firstTwo = line[0].split("-")
		val letter = line[1].replace(":", "")
		numbersCleaned.add(listOf(firstTwo[0].toInt(), firstTwo[1].toInt(), letter, line[2]))

	}
    return numbersCleaned
}

fun passwordsCorrect1(lists: List<List<Any>>): Any {
	var passwordCount = 0
	for (list in lists) {
		val numberCharacters: Int = list[3].toString().toList().filter{it -> (it.toString() == list[2])}.size
		val min: Int = list[0].toString().toInt()
		val max: Int = list[1].toString().toInt()
		if ((numberCharacters >= min) && (numberCharacters <= max)) passwordCount+=1

	}
	return passwordCount
}

fun passwordsCorrect2(lists: List<List<Any>>): Any {
	var passwordCount = 0
	for (list in lists) {
		val characters: List<Char> = list[3].toString().toList()
		val char: String = list[2].toString()
		val min: Int = list[0].toString().toInt()
		val minValue: String = characters[min-1].toString()
		val minIsChar: Boolean = minValue == char
		val max: Int = list[1].toString().toInt()
		val maxValue: String = characters[max-1].toString()
		val maxIsChar: Boolean = maxValue == char
		
		if (minIsChar != maxIsChar) passwordCount+=1
	}
	return passwordCount
}