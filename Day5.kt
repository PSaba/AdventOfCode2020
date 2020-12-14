import java.io.File
import java.util.*

fun main() {
	val inputs = getList("inputDay5")
	println(getMissing(getFullRows(inputs)))
}

fun getList(file: String): List<String> {
	val lines: List<String> = File(file).readLines()
    return lines
}

fun getMax(inputs: List<String>):Int?{
	return inputs.map{it -> getNumber(it)}.max()
}

fun getNumber(input: String?):Int{
	if(input == null) return 0
	return input.replace("B", "1").replace("F", "0").replace("R", "1").replace("L", "0").toInt(2)
}

fun getMissing(rowsFull: Set<Int>): List<Int>{
	val range = 20..100
	print(range.max())
	return listOf(0)
	// return 20..100.filter{it -> !(it in rowsFull)}.toList()
}

fun getFullRows(inputs: List<String>): Set<Int>{
	val freq: MutableMap<Int, Int> = HashMap()
    for (input in inputs) {
    	val row: Int = getNumber(input.substring(0,7))
        freq.putIfAbsent(row, 0)
        freq[row] = freq[row]!! + 1
        print(freq)
    }
    return freq.filter{it -> it.value == 8}.keys
}

//LRL
/RLR

//LLR
//RLL
//RRL
//LRR

//RRR
//LLL

//101

//3+8*82

//1010010
//BFBFFBF