
import java.io.File
import java.util.*

fun main() {
	println(multiplyTwo2020(getList("inputDay1")))
	println(multiplyThree2020(getList("inputDay1")))
}

fun getList(file: String): List<Int> {
    return File(file).readLines().map { it -> it.toInt() }
}

fun multiplyTwo2020(list: List<Int>): Int {
    val set: SortedSet<Int> = list.toSortedSet()
    val filteredList = list.filter { it -> set.contains(2020-it) }
    return filteredList[0] * (2020 - filteredList[0])
}

fun multiplyThree2020(list: List<Int>): Int {
    val filteredList = list.filter { it -> (getThird(list, it) > 0)}
    return filteredList[0] * filteredList[1] * filteredList[2]
}

fun getThird(list: List<Int>, start: Int): Int {
    val set: SortedSet<Int> = list.toSortedSet()
	val filteredList = list.filter { it -> set.contains(2020 - it - start) }
	return if (filteredList.size > 0) filteredList[0] else -1 
}

