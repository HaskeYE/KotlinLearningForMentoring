@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import com.sun.org.apache.xalan.internal.lib.ExsltMath.power
import lesson1.task1.discriminant
import lesson3.task1.isPrime
import lesson3.task1.minDivisor
import java.lang.Math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt(v.map { it * it }.sum())

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.size != 0) return list.sum() / list.size
    else return 0.0
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> = (list.map { it - mean(list) }).toMutableList()

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var s = 0.0
    for (i in 0..a.size - 1)
        s += a[i] * b[i]
    return s
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    var px = 0.0
    var n = 0.0
    for (i in 0 until p.size) {
        px += p[i] * power(x, n)
        n++
    }
    return px
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    list.foldIndexed(0.0) { i, e, _ ->
        list[i] += e
        list[i]
    }
    return list
}


/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val s = mutableListOf<Int>()
    var e = n
    while (e > 1) {
        s.add(minDivisor(e))
        e /= minDivisor(e)
    }
    return s
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val s = mutableListOf<Int>()
    var a = n
    if (n == 0) {
        return emptyList()
    } else
        while (a > 0) {
            s.add(a % base)
            a /= base
        }
    return s.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    var s = ""
    var a = n
    if (n == 0) s += 0
    else
        while (a > 0) {
            if (a % base < 10) s += (a % base)
            else when {
                a % base == 10 -> s += "a"
                a % base == 11 -> s += "b"
                a % base == 12 -> s += "c"
                a % base == 13 -> s += "d"
                a % base == 14 -> s += "e"
                a % base == 15 -> s += "f"
                a % base == 16 -> s += "g"
                a % base == 17 -> s += "h"
                a % base == 18 -> s += "i"
                a % base == 19 -> s += "j"
                a % base == 20 -> s += "k"
                a % base == 21 -> s += "l"
                a % base == 22 -> s += "m"
                a % base == 23 -> s += "n"
                a % base == 24 -> s += "o"
                a % base == 25 -> s += "p"
                a % base == 26 -> s += "q"
                a % base == 27 -> s += "r"
                a % base == 28 -> s += "s"
                a % base == 29 -> s += "t"
                a % base == 30 -> s += "u"
                a % base == 31 -> s += "v"
                a % base == 32 -> s += "w"
                a % base == 33 -> s += "x"
                a % base == 34 -> s += "y"
                else -> s += "z"
            }
            a /= base
        }
    return s.reversed()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var s = 0
    for (i in 0 until digits.size)
        s += digits[i] * Math.pow(base.toDouble(), (digits.size - i - 1).toDouble()).toInt()
    return s
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    var s = 0
    for (i in 0 until str.length) {
        val e = Math.pow(base.toDouble(), (str.length - i - 1).toDouble()).toInt()
        when {
            str[i] == '0' -> s += 0 * e
            str[i] == '1' -> s += 1 * e
            str[i] == '2' -> s += 2 * e
            str[i] == '3' -> s += 3 * e
            str[i] == '4' -> s += 4 * e
            str[i] == '5' -> s += 5 * e
            str[i] == '6' -> s += 6 * e
            str[i] == '7' -> s += 7 * e
            str[i] == '8' -> s += 8 * e
            str[i] == '9' -> s += 9 * e
            str[i] == 'a' -> s += 10 * e
            str[i] == 'b' -> s += 11 * e
            str[i] == 'c' -> s += 12 * e
            str[i] == 'd' -> s += 13 * e
            str[i] == 'e' -> s += 14 * e
            str[i] == 'f' -> s += 15 * e
            str[i] == 'g' -> s += 16 * e
            str[i] == 'h' -> s += 17 * e
            str[i] == 'i' -> s += 18 * e
            str[i] == 'j' -> s += 19 * e
            str[i] == 'k' -> s += 20 * e
            str[i] == 'l' -> s += 21 * e
            str[i] == 'm' -> s += 22 * e
            str[i] == 'n' -> s += 23 * e
            str[i] == 'o' -> s += 24 * e
            str[i] == 'p' -> s += 25 * e
            str[i] == 'q' -> s += 26 * e
            str[i] == 'r' -> s += 27 * e
            str[i] == 's' -> s += 28 * e
            str[i] == 't' -> s += 29 * e
            str[i] == 'y' -> s += 30 * e
            str[i] == 'u' -> s += 31 * e
            str[i] == 'w' -> s += 32 * e
            str[i] == 'x' -> s += 33 * e
            str[i] == 'y' -> s += 34 * e
            str[i] == 'z' -> s += 35 * e
        }
    }
    return s
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var s = ""
    val letters = listOf("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M")
    val numbers = listOf(1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000)
    var g = n
    while (g / 1000 > 0) {
        s += letters.last()
        g -= numbers.last()
    }
    while (g > 0) {
        for (i in 0 until numbers.size) {
            if (numbers[i] > g) {
                g -= numbers[i - 1]
                s += letters[i - 1]
                break
            }
        }
    }
    return s
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russianNumber(n: Int): String = when {
    n == 0 -> ""
    n == 1 -> "один"
    n == 2 -> "два"
    n == 3 -> "три"
    n == 4 -> "четыре"
    n == 5 -> "пять"
    n == 6 -> "шесть"
    n == 7 -> "семь"
    n == 8 -> "восемь"
    n == 9 -> "девять"
    else -> "crash"
}


fun russianPart(n: Int): String {
    var s = ""
    when {n / 100 == 1 -> s += "сто "
        n / 100 == 2 -> s += "двести "
        n / 100 == 3 -> s += "триста "
        n / 100 == 4 -> s += "четыреста "
        n / 100 > 4 -> s += russianNumber(n / 100) + "сот "
    }
    when {
        n % 100 == 10 -> s += "десять "
        n % 100 == 11 -> s += "одинадцать "
        n % 100 == 12 -> s += "двенадцать "
        n % 100 == 13 -> s += "тринадцать "
        n % 100 == 14 -> s += "четырнадцать "
        n % 100 == 15 -> s += "пятнадцать "
        n % 100 == 16 -> s += "шестнадцать "
        n % 100 == 17 -> s += "семнадцать "
        n % 100 == 18 -> s += "восемнадцать "
        n % 100 == 19 -> s += "девятнадцать "
        (n % 100 / 10 == 2) || (n % 100 / 10 == 3) -> s += russianNumber(n % 100 / 10) + "дцать "
        n % 100 / 10 == 4 -> s += "сорок "
        ((n % 100 / 10 > 4) && (n % 100 / 10 < 9)) -> s += russianNumber(n % 100 / 10) + "десят "
        n % 100 / 10 == 9 -> s += "девяносто "
    }
    return s
}

fun russian(n: Int): String {
    var s = ""
    if (n / 1000 != 0) {
        s += russianPart(n / 1000)
        if ((n / 1000 % 100 > 0) && ((n / 1000 % 100 < 11) || (n / 1000 % 100) > 19)) when {
            n / 1000 % 10 == 1 -> s += "одна "
            n / 1000 % 10 == 2 -> s += "две "
            else -> s += russianNumber(n / 1000 % 10) + " "
        }
        if ((n / 1000 % 100 > 10) && (n / 1000 % 100 < 20)) s += "тысяч"
        else when {
            n / 1000 % 10 == 0 -> s += "тысяч"
            n / 1000 % 10 == 1 -> s += "тысяча"
            n / 1000 % 10 == 2 -> s += "тысячи"
            n / 1000 % 10 == 3 -> s += "тысячи"
            n / 1000 % 10 == 4 -> s += "тысячи"
            n / 1000 % 10 > 4 -> s += "тысяч"
        }
    }
    if ((n % 1000 != 0) && (n / 1000 != 0)) s += " " + russianPart(n % 1000) + russianNumber(n % 10)
    if ((n % 1000 != 0) && (n / 1000 == 0) && ((n % 100 > 9) && (n % 100) < 20)) s += when {
        n % 100 == 10 -> "десять"
        n % 100 == 11 -> "одинадцать"
        n % 100 == 12 -> "двенадцать"
        n % 100 == 13 -> "тринадцать"
        n % 100 == 14 -> "четырнадцать"
        n % 100 == 15 -> "пятнадцать"
        n % 100 == 16 -> "шестнадцать"
        n % 100 == 17 -> "семнадцать"
        n % 100 == 18 -> "восемнадцать"
        n % 100 == 19 -> "девятнадцать"
        else -> "crash"
    } else
        if ((n % 1000 != 0) && (n / 1000 == 0)) s += russianPart(n % 1000) + if ((n % 100 > 0) && ((n % 100 < 11) || (n % 100) > 19)) russianNumber(n % 10) else ""
    return s
}