import java.io.File
import java.util.*
import kotlin.math.*

fun main() {
	val inputs = processInput("inputDay16")
	println(inputs)
	println(getCorrectOrder(inputs))
}

fun getCorrectOrder(inputs: Pair<MutableList<Pair<String, Set<Int>>>, MutableList<List<Int>>>): Int{
	val fields = inputs.first
	val tickets = inputs.second
	val cleanedTickets: List<List<Int>> = cleanInvalidTickets(fields, tickets)
	println(cleanedTickets)
	var scannedInputs: MutableList<MutableList<Int>> = mutableListOf()
	tickets[0].forEach{scannedInputs.add(mutableListOf())}
	for(ticket in cleanedTickets){
		for(i in 0..(ticket.size-1)){
			scannedInputs.get(i).add(ticket[i])
		}
	}
	println(scannedInputs)
	val potFields: MutableList<MutableList<Boolean>> = fields.map{field -> scannedInputs.map{it.all{field.second.contains(it)}}.toMutableList()}.toMutableList()
	println(potFields)
	var count = potFields.flatten().filter{it}.count()
	while(count > potFields.size){
		for(i in 0..(potFields.size-1)){
			if(potFields[i].filter{it}.count() == 1){
				val fieldTrue = potFields[i].indexOf(true)
				for(pos in 0..(potFields.size-1)){
					potFields[pos][fieldTrue] = false
				}
				potFields[i][fieldTrue] = true
			}
		}
		count = potFields.flatten().filter{it}.count()
		println(potFields)
	}
	for (field in potFields){
		println(field.indexOf(true))
	}
	return 5
}

fun cleanInvalidTickets(fields: MutableList<Pair<String, Set<Int>>>, tickets: MutableList<List<Int>>): List<List<Int>> {
	val possibleValues = fields.map{it.second}.flatten()
	return tickets.filter{it.all{possibleValues.contains(it)}}
}


fun getInvalidSum(inputs: Pair<MutableList<Pair<String, Set<Int>>>, MutableList<List<Int>>>): Int{
	val possibleValues = inputs.first.map{it.second}.flatten()
	val listValues = inputs.second.flatten()
	return listValues.filter{!possibleValues.contains(it)}.sum()
}

fun processInput(file: String): Pair<MutableList<Pair<String, Set<Int>>>, MutableList<List<Int>>> {
	val processedFile= File(file).readLines()
	var posOn = 0
	var line = processedFile.get(posOn)
	var ticketFields: MutableList<Pair<String, Set<Int>>> = mutableListOf()
	while(line != "") {
		val ticketFieldslist = line.split(":")
		val numberGroups = ticketFieldslist.get(1).split(" or ")
		val numberGroup1 = numberGroups[0].split("-").map{it.trim().toInt()}
		val numberGroup2 = numberGroups[1].split("-").map{it.trim().toInt()}
		val intRange1 = IntRange(numberGroup1[0], numberGroup1[1]).toSet()
		val intRange2 = IntRange(numberGroup2[0], numberGroup2[1]).toSet()
		ticketFields.add(Pair(ticketFieldslist[0], intRange1 + intRange2))
		posOn++
		line = processedFile.get(posOn)
	}

	var tickets: MutableList<List<Int>> = mutableListOf()
	posOn += 2
	line = processedFile.get(posOn)
	tickets.add(line.split(",").map{it.toInt()})
	posOn += 3
	for(pos in posOn..(processedFile.size-1)){
		line = processedFile.get(pos)
		tickets.add(line.split(",").map{it.toInt()})
	}
	return Pair(ticketFields, tickets)
}