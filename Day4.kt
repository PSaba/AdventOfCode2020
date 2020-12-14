
import java.io.File
import java.util.*

fun main() {
	val passports = getPassports("inputDay4")
	println(checkAllValues2(passports))
}

fun getPassports(file: String): MutableList<MutableMap<String, String>> {
	val lines: List<String> = File(file).readLines()
	val attributesSplit: List<List<String>> = lines.map { it -> it.split(" ") }
	var passports: MutableList<MutableMap<String, String>> = mutableListOf(mutableMapOf())
	var curMap: MutableMap<String, String> = mutableMapOf()
	for(line in attributesSplit){
		if(!line[0].contains(":")) {
			passports.add(curMap)
			curMap = mutableMapOf()
		} else {
			for(attribute in line){
				val splitAtt: List<String> = attribute.split(":")
				curMap.put(splitAtt[0], splitAtt[1])
			}
		}
	}
	passports.add(curMap)
    return passports
}

fun checkAllValues1(passports: MutableList<MutableMap<String, String>>): Int {
	val valuesRequired: Set<String> = setOf("ecl","pid", "eyr", "hcl", "byr", "iyr", "hgt")
	val finalList = passports.filter{it -> (it.keys.intersect(valuesRequired) == valuesRequired)}
	return finalList.size
}

fun checkAllValues2(passports: MutableList<MutableMap<String, String>>): Int {
	val validPassportsList = passports.filter{it -> checkPassport(it)}
	return validPassportsList.size
}

fun checkPassport(passport: MutableMap<String, String>): Boolean {
	return checkYearRange(passport["byr"], 1920, 2002) && checkYearRange(passport["iyr"], 2010, 2020) && checkYearRange(passport["eyr"], 2020, 2030) && checkHeight(passport["hgt"]) && checkHairColor(passport["hcl"]) && checkEyeColor(passport["ecl"]) && checkPassportId(passport["pid"])
	
}

fun checkPassportId(value: String?): Boolean {
	if(value == null || value.length != 9)
		return false
	return value.all{it -> it.isDigit() }
}

fun checkYearRange(value: String?, start: Int, end: Int): Boolean {
	if(value?.length != 4)
		return false
	val valueNumber: Int = value.toInt()
	return (valueNumber >= start) && (valueNumber <= end)
}

fun checkHeight(value: String?): Boolean {
	if(value == null || !(value.endsWith("in") || value.endsWith("cm")))
		return false
	val number: Int = value.substring(0, value.length - 2).toInt();
	if(value.endsWith("in"))
		return number >= 59 && number <= 76
	else
		return number >=150 && number <= 193
}

fun checkHairColor(value: String?): Boolean{
	if(value == null || value.length != 7 || value[0].toString() != "#")
		return false
	val possibleValues: List<Char> = "abcdefg0123456789".toList()
	return value.substring(1).all{it -> it in possibleValues}
}

fun checkEyeColor(value:String?): Boolean {
	if(value == null)
		return false
	val potEyeColor: Set<String> = setOf("amb","blu","brn","gry","grn","hzl","oth")
	return potEyeColor.contains(value)
}
