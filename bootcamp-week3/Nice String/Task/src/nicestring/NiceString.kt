package nicestring

fun String.isNice(): Boolean {
    var count = 0
    if (checkOne(this)) count++
    if (checkTwo(this)) count++
    if (checkThree(this)) count++
    return count >= 2
}

fun checkOne(str: String): Boolean {
    return !str.contains("bu") &&
           !str.contains("ba") &&
           !str.contains("be")
}

fun checkTwo(str: String): Boolean {
    var count = 0
    val vowels = listOf('a', 'e', 'i', 'o', 'u')
    val chars = str.toCharArray()
    for (char in chars) {
        if (vowels.contains(char)) count++
    }
    return count >= 3
}

fun checkThree(str: String): Boolean {
    val chars = str.toCharArray()
    var res3 = false
    for (i in 0..chars.size - 1) {
        if (i < chars.size - 1) {
            if (chars[i] == chars[i + 1]) res3 = true
        }
    }
    return res3
}