import java.io.File
import java.util.*

fun main() {
	val inputs = getList("inputDay8")
	println(changeNopOrJump(inputs))
}

data class Command(val commandType: CommandType, val num: Int)

enum class CommandType {
    nop, acc, jmp
}

fun getList(file: String): List<Command> {
	val lines: List<String> = File(file).readLines()
    return lines.map{it.split(" ")}.map{Command(CommandType.valueOf(it[0]), it[1].toInt())}
}

fun changeNopOrJump(commands: List<Command>): Int {
	var testingCommands = commands.toMutableList()
	var checkResponse: Int? = null
	var curPos = 0
	while(checkResponse == null){
		var testingCommands = commands.toMutableList()
		var isNopOrJmp: Boolean = (testingCommands.get(curPos).commandType == CommandType.nop) || (testingCommands.get(curPos).commandType == CommandType.jmp)
		while(!isNopOrJmp ){
			curPos++
			isNopOrJmp = (testingCommands.get(curPos).commandType == CommandType.nop) || (testingCommands.get(curPos).commandType == CommandType.jmp)
		}
		if(testingCommands.get(curPos).commandType == CommandType.nop)
			testingCommands.set(curPos, Command(CommandType.jmp, testingCommands.get(curPos).num))
		else
			testingCommands.set(curPos, Command(CommandType.nop, testingCommands.get(curPos).num))
		checkResponse = checkNoRepeatsModified(testingCommands)
		curPos++
		print(testingCommands)
		println(checkResponse)
	}
	return checkResponse
}

fun checkNoRepeatsModified(commands: List<Command>): Int? {
	var requests = BooleanArray(commands.size)
	var curAcc = 0
	var curPos = 0
	while(!requests[curPos]){
		requests[curPos] = true
		val response = doCommand(commands.get(curPos), curAcc, curPos)
		curAcc = response.first
		curPos = response.second
		if(curPos == commands.size)
			return curAcc
	}
	return null
}

fun checkNoRepeats(commands: List<Command>): Int {
	var requests = BooleanArray(commands.size)
	var curAcc = 0
	var curPos = 0
	while(!requests[curPos]){
		requests[curPos] = true
		val response = doCommand(commands.get(curPos), curAcc, curPos)
		curAcc = response.first
		curPos = response.second
		print(curAcc)
		println(curPos)
	}
	return curAcc
}

fun doCommand(command: Command, curAcc: Int, curPos: Int): Pair<Int, Int> {
	return when(command.commandType){
		CommandType.nop -> Pair(curAcc, curPos + 1)
		CommandType.acc -> Pair(curAcc + command.num, curPos + 1)
		CommandType.jmp -> Pair(curAcc, curPos + command.num)
	}
}
