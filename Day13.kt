import java.io.File
import java.util.*

fun main() {
	val inputs1 = processInputPt1("inputDay13")
	println(getBestTime(inputs1))
	val inputs2 = processInputPt2("inputDay13")
	println(getCoordinatedTime(inputs2))
}

fun processInputPt2(file: String): List<String> {
	val processedFile = File(file).readLines()
	return processedFile[1].split(",")
}

fun getCoordinatedTime(list: List<String>): Long {
	val pairsNeeded = getPairsNeeded(list)
	println(pairsNeeded)
	var multiplier: Long = 1
	var remainder: Long = 0
	var checking: Long = 0
	for (pair in pairsNeeded){
		var num = 0
		println(pair)
		while((checking + pair.second) % pair.first != 0.toLong()){
			checking = remainder + multiplier*num
			println(checking)
			num++
		}
		remainder = checking % multiplier
		multiplier *= pair.first
		println(remainder)
		println(multiplier)
	}
	return remainder
}

//	var multiplier: Long = 0
//	val firstVal = pairsNeeded.get(0)
//	while(true){
//		val valOn: Long = firstVal.first * multiplier - firstVal.second
		//println(valOn)
//		val allTrue = pairsNeeded.all{((valOn+it.second) % it.first) == 0.toLong()}
//		if(allTrue) return valOn
//		multiplier++
//	}

fun getPairsNeeded(list:List<String>): List<Pair<Int, Int>>{
	var i = 0
	var pairsNeeded = mutableListOf<Pair<Int, Int>>()
	for(bus in list){
		i++
		if(bus == "x") continue
		pairsNeeded.add(Pair(bus.toInt(), i-1))
	}
	return pairsNeeded
}

fun processInputPt1(file: String): Pair<Long, List<Int>> {
	val processedFile = File(file).readLines()
	return Pair(processedFile[0].toLong(), processedFile[1].split(",").filter{it != "x"}.map{it.toInt()})
}

fun getBestTime(info: Pair<Long, List<Int>>) : Long {
	val bestTime = info.second.map{it to (it - info.first % it)}.minByOrNull{it.second}
	return bestTime!!.first * bestTime!!.second
}



/*
See in thread: https://www.reddit.com/r/adventofcode/comments/kc4njx/2020_day_13_solutions/
Haskell solution by u/mstksg
*/