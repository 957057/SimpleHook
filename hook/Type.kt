package me.simpleHook.hook


import android.content.Context
import java.util.regex.Pattern.matches

object Type {
    private const val INT_PATTERN = """^-?[0-9]\d*$"""
    private const val LONG_PATTERN = """^-?[0-9]\d*[l|L]$"""
    private const val BOOLEAN_PATTERN = """(?i)true|false"""
    private const val STRING_PATTERN = """^-?0?[1-9]\d*[s|S]$"""
    private const val NULL_PATTERN = """(?i)null"""
    private const val STRING_EMPTY_PATTERN = """(?i)empty|空"""
    private const val BYTE_PATTERN = """^-?[0-9]\d*[b|B]$"""
    private const val SHORT_PATTERN = """^-?[0-9]\d*(?i)short$"""
    private const val CHAR_PATTERN = """^.*[c|C]$"""
    private const val INTEGER_PATTERN = """^-?[0-9]\d*(?i)integer$"""

    fun getDataTypeValue(value: String) = when{
        matches(BOOLEAN_PATTERN,value) -> value.toBoolean()
        matches(INT_PATTERN,value) -> value.toInt()
        matches(LONG_PATTERN,value) -> value.replace(Regex("""[l|L]"""),"").toLong()
        matches(STRING_PATTERN,value) -> value.replace(Regex("""[s|S]"""),"")
        matches(NULL_PATTERN,value) -> null
        matches(STRING_EMPTY_PATTERN,value) -> ""
        matches(BYTE_PATTERN,value) -> value.replace(Regex("""[b|B]"""),"").toByte()
        matches(SHORT_PATTERN,value) -> value.replace(Regex("""[(?i)short]"""),"").toShort()
        matches(CHAR_PATTERN, value) -> value.replace(Regex("""[b|B]"""), "")[0]
        matches(INTEGER_PATTERN, value) -> Integer.valueOf(
            value.replace(
                Regex("""[(?i)integer]"""),
                ""
            ).toInt()
        )
        else -> value
    }
    fun getClassType(className:String) = when(className){
        "byte","B","b" -> Byte::class.java
        "int", "I", "i" -> Int::class.java
        "short", "S", "s" -> Short::class.java
        "long", "J", "j" -> Long::class.java
        "float", "F", "f" -> Float::class.java
        "double", "D", "d" -> Double::class.java
        "boolean", "Z", "z" -> Boolean::class.java
        "char", "c", "C" -> Char::class.java
        "string" -> String::class.java
        "context" -> Context::class.java
        else -> null
    }
}