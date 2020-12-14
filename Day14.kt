import java.io.File
import java.util.*
import kotlin.math.*

fun main() {
	val inputs = processInput("inputDay14")
	println(inputs)
	println(computeMasks(inputs))
	println(computeMasksV2(processInputV2("inputDay14")))
}

fun processInputV2(file: String): List<Pair<String, String>> {
	val processedFile= File(file).readLines()
		.map{it.split(" = ")}.map{Pair(it[0], it[1])}
	var returningList= mutableListOf<Pair<String, String>>()
	for(line in processedFile){
		if(line.first == "mask"){
			returningList.add(line)
		} else {
			val firstNum = line.first.removePrefix("mem[").removeSuffix("]").toUInt().toString(radix = 2)
			returningList.add(Pair(firstNum, line.second))
		}
	}
	return returningList
}

fun computeMasksV2(list: List<Pair<String, String>>): Long {
	var memory = mutableMapOf<String, Long>()
	var curMask = "empty"
	for(line in list){
		if(line.first == "mask"){
			curMask = line.second
		} else {
			val maskedValue = getMaskedValueV2(curMask, line.first)
			println(maskedValue)
			var valuesList = getListFromMask(maskedValue)
			valuesList.forEach{memory.put(it, line.second.toLong())}
		}
	}
	println(memory)
	return memory.map{ it.value }.sum()
}

fun getMaskedValueV2(mask: String, value: String): String {
	val paddedValue = value.padStart(mask.length, '0')
	var toReturn = ""
	for(ch in 0..(mask.length - 1)){
		toReturn += when(mask.get(ch)){
			'0' -> paddedValue.get(ch)
			'1' -> "1"
			'X' -> "X"
			else -> ""
		}
	}
	return toReturn
}

fun getListFromMask(mask: String): List<String>{
	if(mask.length == 1){
		if((mask.get(0) == '1') || (mask.get(0) == '0')) 
			return listOf(mask.get(0).toString())
		else
			return listOf("1", "0")
	} 
	val subMasks = getListFromMask(mask.substring(1))
	if((mask.get(0) == '1') || (mask.get(0) == '0')) return subMasks.map{mask.get(0) + it}
	return listOf(subMasks.map{"0" + it}, subMasks.map{"1" + it}).flatten()
	var masks = mutableListOf<String>()
}

fun processInput(file: String): List<Pair<String, String>> {
	val processedFile= File(file).readLines()
		.map{it.split(" = ")}.map{Pair(it[0], it[1])}
	var returningList= mutableListOf<Pair<String, String>>()
	for(line in processedFile){
		if(line.first == "mask"){
			returningList.add(line)
		} else {
			val firstNum = line.first.removePrefix("mem[").removeSuffix("]")
			returningList.add(Pair(firstNum, line.second.toUInt().toString(radix = 2)))
		}
	}
	return returningList
}

fun computeMasks(list: List<Pair<String, String>>): Long {
	var memory = mutableMapOf<Int, Long>()
	var curMask = "empty"
	for(line in list){
		if(line.first == "mask"){
			curMask = line.second
		} else {
			memory.put(line.first.toInt(), getMaskedValue(curMask, line.second))
		}
	}
	println(memory)
	return memory.map{ it.value }.sum()
}

fun getMaskedValue(mask: String, value: String): Long {
	val paddedValue = value.padStart(mask.length, '0')
	var toReturn = ""
	for(ch in 0..(mask.length - 1)){
		toReturn += when(mask.get(ch)){
			'0' -> "0"
			'1' -> "1"
			'X' -> paddedValue.get(ch)
			else -> ""
		}
	}
	return toReturn.toLong(2)
}