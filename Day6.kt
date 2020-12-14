import java.io.File
import java.util.*

fun main() {
	val inputs = getList("inputDay6")
	println(getQuestionsYesByGroup(inputs))
	println(getSumOfQuestions(getQuestionsYesByGroup(inputs)))
	println(getAllQuestionsYesByGroup(inputs))
	println(getSumOfQuestionsBetterTypes(getAllQuestionsYesByGroup(inputs)))
}

fun getList(file: String): List<String> {
	val lines: List<String> = File(file).readLines()
    return lines
}

fun getQuestionsYesByGroup(list: List<String>): MutableList<MutableSet<String>>{
	var groups: MutableList<MutableSet<String>> = mutableListOf(mutableSetOf())
	var curSet: MutableSet<String> = mutableSetOf()
	for(line in list){
		if(line.isEmpty()){
			groups.add(curSet)
			curSet = mutableSetOf()
		} else {
			//Note: could have done union as intersect is done below
			line.forEach{question -> curSet.add(question.toString())}
		}
	}
	groups.add(curSet)
	return groups
}

fun getAllQuestionsYesByGroup(list: List<String>): MutableList<Set<Char>>{
	var groups: MutableList<Set<Char>> = mutableListOf(setOf())
	var curSet: Set<Char> = CharRange('a', 'z').toSet()
	for(line in list){
		if(line.isEmpty()){
			groups.add(curSet)
			curSet = CharRange('a', 'z').toSet()
		} else {
			curSet = curSet.intersect(line.toSet())
		}
	}
	groups.add(curSet)
	return groups
}

fun getSumOfQuestions(questions: MutableList<MutableSet<String>>): Int{
	return questions.map{group -> group.count()}.sum()
}

fun getSumOfQuestionsBetterTypes(questions: MutableList<Set<Char>>): Int{
	return questions.map{group -> group.count()}.sum()
}
