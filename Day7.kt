import java.io.File
import java.util.*

fun main() {
	val inputs = getBags("inputDay7")
	println(convertListToMap(inputs))
	//println(getAllCanContain("shiny gold", convertListToMapContained(inputs)).count())
	println(getAllMustContain("shiny gold", convertListToMap(inputs)))
}

data class Bag(val BagColor: String, val canContain: Set<Pair<Int, String>>)

fun getBags(file: String): List<Bag> {
	val lines: List<String> = File(file).readLines()
	val bags: List<Bag> = lines.map{line -> line.split(" contain ")}
										.map{line -> Bag(line[0].replace(" bags", ""), getCanContain(line[1]))}
    return bags
}

fun getCanContain(contain: String): Set<Pair<Int, String>> {
	if(contain.contains("no other bags"))
		return setOf<Pair<Int, String>>()
	val listBags: List<String> = contain.replace(".", "").split(", ")
	return listBags.map{color -> color.split(" ")}
					.map{color -> Pair(color[0].toInt(), color[1].plus(" ").plus(color[2])) }
					.toSet()
}

fun convertListToMap(bags: List<Bag>): Map<String, Set<Pair<Int, String>>>{
	return bags.map{bag -> bag.BagColor to bag.canContain}.toMap()
}

fun convertListToMapContained(bags: List<Bag>): Map<String, Set<String>>{
	val returningMap: MutableMap<String, MutableSet<String>> = mutableMapOf()
	for (bag in bags){
		for(containsBag in bag.canContain){
			if(containsBag.second in returningMap)
				returningMap[containsBag.second]!!.add(bag.BagColor)
			else
				returningMap.put(containsBag.second, mutableSetOf(bag.BagColor))
		}
	}
	return returningMap
}

fun getAllCanContain(bagColor: String, bags: Map<String, Set<String>>)
		:  Set<String>{ 
	if(!(bagColor in bags))
		return setOf()
	val futureBags = bags[bagColor]!!.map{bag -> getAllCanContain(bag, bags)}.flatten()
	val curBags = bags[bagColor]!!.map{bag -> bag}.toSet()
	return setOf(futureBags, curBags).flatten().toSet()
}

//Can improve by adding memoize
fun getAllMustContain(bagColor: String, bags: Map<String, Set<Pair<Int, String>>>): Int {
	if(!(bagColor in bags) || bags[bagColor]!!.isEmpty())
		return 1
	val futureBags = bags[bagColor]!!.map{bag -> getAllMustContain(bag.second, bags)*bag.first}
	println(bags[bagColor])
	println(futureBags)
	return futureBags.sum() + 1
}