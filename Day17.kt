import java.io.File
import java.util.*
import kotlin.math.*

fun main() {
	val inputs = getMap("inputDay17")
	println(doCycles(inputs))
	val inputs2 = getMap2("inputDay17")
	println(doCycles2(inputs2))
}

data class Quadruple(val first: Int, val second: Int, val third: Int, val fourth: Int)

fun doCycles2(universe: MutableSet<Quadruple>): Int{
	var curUniverse = universe
	for(cycle in 1..6){
		var newUniverse: MutableSet<Quadruple> = mutableSetOf() 
		val coordinatesToCheck = curUniverse.map{gridSurrounding2(it)}.flatten().toSet()
		for(co in coordinatesToCheck){
			val neighbors = getNeighborCount2(co, curUniverse)
			if(neighbors == 3){
				newUniverse.add(co)
			} else if(curUniverse.contains(co) && neighbors == 2){
				newUniverse.add(co)
			}
		}
		curUniverse = newUniverse
	}
	return curUniverse.count()
}

fun getNeighborCount2(point: Quadruple, universe: MutableSet<Quadruple>): Int{
	var gridToCheck = gridSurrounding2(point)
	gridToCheck.remove(point)
	return gridToCheck.map{it in universe}.filter{it}.count()
}

fun gridSurrounding2(point: Quadruple): MutableList<Quadruple> {
	return IntRange(-1 + point.first,1 + point.first).toList().flatMap { x -> 
				IntRange(-1 + point.second ,1 + point.second).toList().flatMap { y -> 
					IntRange(-1 + point.third, 1 + point.third).toList().flatMap { z -> 
						IntRange(-1 + point.fourth, 1 + point.fourth).toList().map { a -> 
							Quadruple(x, y, z, a)  } } } }.toMutableList()
}

fun getMap2(file: String): MutableSet<Quadruple> {
	val lines = File(file).readLines()
	val activeValues: MutableSet<Quadruple> = mutableSetOf()
	for(x in 1..lines.size){
		for(y in 1..lines[x-1].length){
			if(lines[x-1][y-1]=='#') activeValues.add(Quadruple(x,y,0,0))
		}
	}
	return activeValues
}


fun doCycles(universe: MutableSet<Triple<Int, Int, Int>>): Int{
	var curUniverse = universe
	for(cycle in 1..6){
		var newUniverse: MutableSet<Triple<Int, Int, Int>> = mutableSetOf() 
		val coordinatesToCheck = curUniverse.map{gridSurrounding(it)}.flatten().toSet()
		for(co in coordinatesToCheck){
			val neighbors = getNeighborCount(co, curUniverse)
			if(neighbors == 3){
				newUniverse.add(co)
			} else if(curUniverse.contains(co) && neighbors == 2){
				newUniverse.add(co)
			}
		}
		curUniverse = newUniverse
	}
	return curUniverse.count()
}

fun getNeighborCount(point: Triple<Int, Int, Int>, universe: MutableSet<Triple<Int, Int, Int>>): Int{
	var gridToCheck = gridSurrounding(point)
	gridToCheck.remove(point)
	return gridToCheck.map{it in universe}.filter{it}.count()
}

fun gridSurrounding(point: Triple<Int, Int, Int>): MutableList<Triple<Int, Int, Int>> {
	return IntRange(-1 + point.first,1 + point.first).toList().flatMap { x -> 
				IntRange(-1 + point.second ,1 + point.second).toList().flatMap { y -> 
					IntRange(-1 + point.third, 1 + point.third).toList().map { z -> 
						Triple(x, y, z)  } } }.toMutableList()
}

fun getMap(file: String): MutableSet<Triple<Int, Int, Int>> {
	val lines = File(file).readLines()
	val activeValues: MutableSet<Triple<Int, Int, Int>> = mutableSetOf()
	for(x in 1..lines.size){
		for(y in 1..lines[x-1].length){
			if(lines[x-1][y-1]=='#') activeValues.add(Triple(x,y,0))
		}
	}
	return activeValues
}