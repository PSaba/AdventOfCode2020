import java.io.File
import java.util.*

fun main() {
	val inputs = getList("inputDay10")
	println(inputs)
	println(getDifferences(inputs))
	println(getNumberArrangements(inputs))
}

fun getList(file: String): List<Int> {
	return File(file).readLines().map{it.toInt()}.sorted()
}

fun getDifferences(list: List<Int>): Int {
	val updatedList = listOf(0) + list
	val differences = updatedList.zipWithNext{ a, b -> b - a }
	val number1 = differences.filter{it == 1}.count()
	val number3 = differences.filter{it == 3}.count() + 1
	return number1 * number3
}

fun getNumberArrangements(list: List<Int>): Long {
	val updatedList = listOf(0) + list
	val differences = updatedList.zipWithNext{ a, b -> b - a }
	println(differences + listOf(3))
	//val listToTest = listOf(1, 3, 1, 3)
	return getNumberArrangmentsRecursive(differences + listOf(3))
}

var Memoize: HashMap<List<Int>, Long> = hashMapOf()
fun getNumberArrangmentsRecursive(list: List<Int>): Long {
	if(list.size<=1)
		return 1
	var curPos = 0
	var sumSoFar = list.get(curPos)
	var numberOfA: Long = 0
	while(sumSoFar <= 3) {
		if(list.subList(curPos + 1, list.size) in Memoize){
			numberOfA += Memoize.getOrDefault(list.subList(curPos + 1, list.size), 0)
 		} else {
 			numberOfA += getNumberArrangmentsRecursive(list.subList(curPos + 1, list.size))
 		}
		curPos++
		if(curPos==list.size) break
		sumSoFar += list.get(curPos)
	}
	println(curPos)
	println(numberOfA)
	Memoize.put(list, numberOfA)
	return numberOfA
}