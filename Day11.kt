import java.io.File
import java.util.*

fun main() {
	val inputs = getGrid("inputDay11")
	println(getSteadyInt(inputs))
}


fun getGrid(file: String): List<String> {
	return File(file).readLines()
}

fun getSteadyInt(grid: List<String>): Int{
	var curCount = 0
	var prevCount = -1
	var curGrid = grid
	while(curCount != prevCount){
		prevCount = curCount
		curGrid = fillGrid(curGrid)
		curCount = curGrid.joinToString().filter{it == '#'}.count()
		println(curGrid.joinToString())
		println(curCount)
	}
	return curCount
}

fun fillGrid(grid: List<String>): List<String> {
	var gridToReturn: MutableList<String> = mutableListOf()
	for(line in 0..(grid.size-1)){
		var lineToReturn = ""
		for(spot in 0..(grid.get(0).length-1)){
			//print(getNumberAdjacent(line, spot, grid))
			if(grid.get(line).get(spot) == '.'){
				lineToReturn += '.'
			} else if(grid.get(line).get(spot) == '#'){
				if(getNumberAdjacentv2(line, spot, grid) >= 5)
					lineToReturn += 'L'
				else
					lineToReturn += '#'
			} else {
				if(getNumberAdjacentv2(line, spot, grid) == 0)
					lineToReturn += '#'
				else
					lineToReturn += 'L'
			}
		}
		gridToReturn.add(lineToReturn)
	}
	return gridToReturn
}

fun getNumberAdjacentv2(row: Int, col: Int, grid: List<String>): Int {
	var gridToCheck: MutableList<Pair<Int, Int>> = IntRange(-1,1).toList().flatMap { row -> IntRange(-1,1).toList().map { col -> row to col } }.toMutableList()
	gridToCheck.remove(Pair(0, 0))
	var numberAdjacent = 0
	for(pair in gridToCheck){
		var startRow = row + pair.first
		var startCol = col + pair.second
		while(true){
			if(startRow == -1 || startCol == -1 || startRow == grid.size || startCol == grid.get(0).length)
				break
			var chair = grid.get(startRow).get(startCol)
			if(chair == '#'){
				numberAdjacent++
				break
			} else if (chair == 'L'){
				break
			}
			startRow += pair.first
			startCol += pair.second
		}
	}
	return numberAdjacent
}

fun getNumberAdjacentv1(row: Int, col: Int, grid: List<String>): Int {
	val rowsToCheck = IntRange(maxOf(0, row - 1), minOf(grid.get(0).length - 1, row + 1))
	val columnsToCheck = IntRange(maxOf(0, col - 1), minOf(grid.size - 1, col + 1))
	var gridToCheck: MutableList<Pair<Int, Int>> = rowsToCheck.flatMap { row -> columnsToCheck.map { col -> row to col } }.toMutableList()
	gridToCheck.remove(Pair(row, col))
	//println(gridToCheck.map{grid.get(it.first).get(it.second)})
	return gridToCheck.filter{grid.get(it.first).get(it.second) == '#'}.count()
}