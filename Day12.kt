import java.io.File
import java.util.*
import kotlin.math.*

fun main() {
	val inputs = getList("inputDay12")
	//println(inputs)
	//println(doActionsPt1(inputs))
	println(doActionsPt2(inputs))
}

fun getList(file: String): List<String> {
	return File(file).readLines()
}

fun doActionsPt2(list: List<String>): Int{
	// N E S W
	var NS = 0
	var EW = 0
	var wayPointE = 10
	var wayPointN = 1

	for(action in list){
		val distToGo = action.substring(1).toInt()
		val act = action.get(0)
		when(act){
			'N' -> wayPointN += distToGo
			'S' -> wayPointN -= distToGo
			'E' -> wayPointE += distToGo
			'W' -> wayPointE -= distToGo
			'R' -> for (i in 90..distToGo step 90) {
                        val tmp = -wayPointE
                        wayPointE = wayPointN
                        wayPointN = tmp
                    }
			'L' -> for (i in 90..distToGo step 90) {
                        val tmp = -wayPointN
                        wayPointN = wayPointE
                        wayPointE = tmp
                    }
		}
		if(act == 'F'){
			NS += wayPointN * distToGo
			EW += wayPointE * distToGo
		}
		println(action)
		println(NS)
		println(EW)
		println(wayPointE)
		println(wayPointN)
	}

	return NS.absoluteValue + EW.absoluteValue
}

fun doActionsPt1(list: List<String>): Int{
	// N E S W
	var dir = 1
	var NS = 0
	var EW = 0

	for(action in list){
		val distToGo = action.substring(1).toInt()
		val act = action.get(0)
		when(act){
			'N' -> NS += distToGo
			'S' -> NS -= distToGo
			'E' -> EW += distToGo
			'W' -> EW -= distToGo
			'L' -> dir -= distToGo/90
			'R' -> dir += distToGo/90
		}
		if(act == 'F'){
			val moveHowMuch = moveForward(NS, EW, dir, distToGo)
			NS = moveHowMuch.first
			EW = moveHowMuch.second
		}
		if (dir < 0) dir += 4
	}

	return NS.absoluteValue + EW.absoluteValue
}

fun moveForward(curNS: Int, curEW: Int, dir: Int, distance: Int): Pair<Int, Int> {
	return when(dir%4){
		0 -> Pair(curNS + distance, curEW)
		1 -> Pair(curNS, curEW + distance)
		2 -> Pair(curNS - distance, curEW)
		3 -> Pair(curNS, curEW - distance)
		else -> Pair(curNS, curEW)
	}
}