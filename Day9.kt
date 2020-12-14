import java.io.File
import java.util.*

fun main() {
	val inputs = getList("inputDay9")
	val oddOneOut = getOddOneOut(inputs)
	println(oddOneOut)
	println(getContiguousList(oddOneOut, inputs))
}

fun getList(file: String): List<Long> {
	return File(file).readLines().map{it.toLong()}
}

fun getOddOneOut(list: List<Long>): Long{
	var iPos: Int = 25
	while(iPos <= list.size){
		val sumCheck: Long = list.get(iPos)
		val setToCheck: SortedSet<Long> = list.subList((iPos - 25), iPos).toList().toSortedSet()
		if(!twoSum(sumCheck, setToCheck)) return sumCheck
		iPos++
	}
	return -1
}

fun twoSum(num: Long, set: SortedSet<Long>): Boolean {
	return set.any{set.contains(num - it)}
}

fun getContiguousList(input: Long, list: List<Long>): Long{
	var sum: Long = 0
	var firstPos: Int = 0
	var lastPos: Int = 0
	var listToSum: List<Long> = listOf()
	while(sum != input){
	    listToSum = list.subList(firstPos, lastPos)
		sum = listToSum.sum()
		if(sum < input) lastPos++
		if(sum > input) firstPos++
		println(list.subList(firstPos, lastPos))
	}
	val max: Long = listToSum.maxOrNull() ?: 0
	val min: Long = listToSum.minOrNull() ?: 0
	return max + min
}