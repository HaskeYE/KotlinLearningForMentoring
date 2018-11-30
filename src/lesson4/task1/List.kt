@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import com.sun.org.apache.xalan.internal.lib.ExsltMath.power
import lesson1.task1.discriminant
import lesson3.task1.minDivisor
import java.lang.Math.pow
import java.lang.StringBuilder
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
fun mean(list: List<Double>): Double =
        if (list.isNotEmpty()) list.sum() / list.size
        else 0.0


/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val m = mean(list)
    for (i in 0 until list.size) list[i] -= m
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var s = 0.0
    for (i in 0 until a.size)
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
    for (i in 0 until p.size) {
        px += p[i] * power(x, i.toDouble())
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
    if (n < base) {
        return (s + a)
    } else
        while (a >= base) {
            s.add(a % base)
            a /= base
            if ((a < base) && (a != 0)) s.add(a)
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
    val s = StringBuilder()
    var a = n
    if (n == 0) when {
        n < 10 -> s.append(n)
        n >= 10 -> s.append((n - 10 + 'a'.toInt()).toChar())
    }
    else
        while (a > 0) {
            val number = a % base
            when {
                number < 10 -> s.append(number)
                number >= 10 -> s.append((number - 10 + 'a'.toInt()).toChar())
            }
            a /= base
        }
    return s.toString().reversed()
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
        s += digits[i] * pow(base.toDouble(), (digits.size - i - 1).toDouble()).toInt()
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
        val n = str[i].toInt() - '0'.toInt()
        val e = pow(base.toDouble(), (str.length - i - 1).toDouble()).toInt()
        if (n < 10)
            s += n * e
        else
            s += (n - '0'.toInt() + 9) * e
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
    val s = StringBuilder()
    val letters = mapOf(1 to "I", 4 to "IV", 5 to "V",
            9 to "IX", 10 to "X", 40 to "XL",
            50 to "L", 90 to "XC", 100 to "C",
            400 to "CD", 500 to "D", 900 to "CM", 1000 to "M")
    var g = n
    while (g / 1000 > 0) {
        s.append(letters[1000])
        g -= 1000
    }
    var k = 1
    while (g > 0) {
        for (key in letters.keys) if (g <= key) {
            if (g == key) {
                g -= key
                s.append(letters[key])
            } else {
                while (g >= k) {
                    g -= k
                    s.append(letters[k])
                }
            }
        } else k = key
    }
    return s.toString()
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russianNumber(n: Int): String {
    val s = mapOf(0 to "", 1 to "один", 2 to "два", 3 to "три",
            4 to "четыре", 5 to "пять", 6 to "шесть", 7 to "семь",
            8 to "восемь", 9 to "девять")
    return (s[n] ?: "error")
}


fun russianHundreds(n: Int): String {
    val highDigit = n / 100
    if (highDigit != 0)
        return when (highDigit) {
            1 -> "сто "
            2 -> "двести "
            3 -> "триста "
            4 -> "четыреста "
            else -> russianNumber(n / 100) + "сот "
        }
    else return ""
}

fun russianPart(n: Int): String {
    val s = StringBuilder()
    val highDigit = n / 100
    val lastDigits = n % 100
    val lastSymbol = n % 100 / 10
    when {
        highDigit == 1 -> s.append("сто ")
        highDigit == 2 -> s.append("двести ")
        highDigit == 3 -> s.append("триста ")
        highDigit == 4 -> s.append("четыреста ")
        highDigit > 4 -> s.append(russianNumber(n / 100) + "сот ")
    }
    when {
        lastDigits == 10 -> s.append("десять")
        lastDigits == 11 -> s.append("одиннадцать")
        lastDigits == 12 -> s.append("двенадцать")
        lastDigits == 13 -> s.append("тринадцать")
        lastDigits == 14 -> s.append("четырнадцать")
        lastDigits == 15 -> s.append("пятнадцать")
        lastDigits == 16 -> s.append("шестнадцать")
        lastDigits == 17 -> s.append("семнадцать")
        lastDigits == 18 -> s.append("восемнадцать")
        lastDigits == 19 -> s.append("девятнадцать")
        (lastSymbol == 2) || (lastSymbol == 3) -> s.append(russianNumber(n % 100 / 10) + "дцать ")
        lastSymbol == 4 -> s.append("сорок ")
        ((lastSymbol > 4) && (lastSymbol < 9)) -> s.append(russianNumber(n % 100 / 10) + "десят ")
        lastSymbol == 9 -> s.append("девяносто ")
    }
    return s.toString()
}

fun russian(n: Int): String {
    val s = StringBuilder()
    if (n / 1000 != 0) {
        s.append(russianPart(n / 1000))
        if ((n / 1000 % 100 > 0) && ((n / 1000 % 100 < 11) || (n / 1000 % 100) > 19)) when {
            n / 1000 % 10 == 1 -> s.append("одна ")
            n / 1000 % 10 == 2 -> s.append("две ")
            else -> s.append(russianNumber(n / 1000 % 10) +
                    if ((n / 1000 != 0) && (n / 1000 % 10 == 0) &&
                            (n / 1000 % 100 != 0)) "" else " ")
        }
        if ((n / 1000 % 100 > 10) && (n / 1000 % 100 < 20)) s.append(" тысяч")
        else when (n / 1000 % 10) {
            0 -> s.append("тысяч")
            1 -> s.append("тысяча")
            2, 3, 4 -> s.append("тысячи")
            else -> s.append("тысяч")
        }
    }
    if ((n % 1000 != 0) && (n / 1000 != 0)) {
        s.append(" ")
        if ((n % 100 > 9) && (n % 100) < 20)
            s.append(russianHundreds(n % 1000) + russianPart(n % 100))
        else
            s.append(russianPart(n % 1000) + russianNumber(n % 10))
    } else
        if ((n % 1000 != 0) && (n / 1000 == 0)) {
            s.append(russianPart(n % 1000))
            if ((n % 100 > 0) && ((n % 100 < 11) || (n % 100) > 19))
                s.append(russianNumber(n % 10))
        }
    if ((n % 1000 != 0) && (n % 10 == 0)) s.delete(s.length - 1, s.length)
    return s.toString()
}