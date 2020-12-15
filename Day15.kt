import java.io.File
import java.util.*
import kotlin.math.*

fun main() {
	val inputs = getNumbers("inputDay15")
	println(get2020Number(inputs))
}

fun getNumbers(file: String): List<String> {
	return File(file).readLines().get(0).split(",")
}

fun get2020Number(startList: List<String>): Long {
	var roundOn: Long = 0
	var prevMap: MutableMap<Long, Pair<Long, Long>> = mutableMapOf()
	var lastNum: Long = 0
	for(startValue in startList){
		roundOn++
		prevMap.put(startValue.toLong(), Pair(roundOn, -1))
		lastNum = startValue.toLong()
	}
	roundOn++
	for(i in roundOn..30000000){
		if(prevMap.contains(lastNum)){
			var prevMapValue: Pair<Long, Long> = prevMap?.get(lastNum) ?: Pair(i, -1)
			if(prevMapValue.second == -1.toLong()){
				lastNum = 0
			} else{
				lastNum = prevMapValue.first - prevMapValue.second
			}
			val prevLastNum: Pair<Long, Long> = prevMap?.get(lastNum) ?: Pair(-1, -1)
			prevMap.put(lastNum, Pair(i, prevLastNum.first))
		} else {
			print("something's wrong")
		}
	}
	return lastNum
}