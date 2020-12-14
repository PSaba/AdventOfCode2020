
import java.io.File
import java.util.*

fun main() {
	val map = getMap("inputDay3")
	println(getCountTreesPt1(map))
	println(getCountTrees(map))
}

fun getMap(file: String): List<String> {
	val lines: List<String> = File(file).readLines()
    return lines
}

fun getCountTrees(map: List<String>): Long {
	val oneOne: Long = getCountTreesSingleSlope(map, 1, 1).toLong()
	val oneThree: Long = getCountTreesSingleSlope(map, 1, 3).toLong()
	val oneFive: Long = getCountTreesSingleSlope(map, 1, 5).toLong()
	val oneSeven: Long = getCountTreesSingleSlope(map, 1, 7).toLong()
	val twoTwo: Long = getCountTreesSingleSlope(map, 2, 1).toLong()
	println(oneOne)
	println(oneThree)
	println(oneFive)
	println(oneSeven)
	println(twoTwo)
	return oneOne * oneThree * oneFive * oneSeven * twoTwo
}

fun getCountTreesSingleSlope(map: List<String>, vert: Int, hoz: Int): Int {
	var count = 0
	val size: Int = map.size - 1
	for (i in 0..size step vert){
		val xValue = i*hoz/vert % map[i].length
		println("Checking" + i + " " + xValue + " " + map[i][xValue])
		if (isTree(map[i][xValue])) count++
	} 
	return count
}

fun getCountTreesPt1(map: List<String>): Int {
	var count = 0
	val size: Int = map.size - 1
	for (i in 1..size){
		val xValue = i*3 % map[i].length
		println("Checking" + i + " " + map[i][xValue])
		if (isTree(map[i][xValue])) count++
	} 
	return count
}
fun isTree(spot: Char): Boolean {
	return spot == '#'
}
