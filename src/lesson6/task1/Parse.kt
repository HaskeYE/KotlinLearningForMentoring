@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth
import java.lang.IllegalArgumentException
import java.lang.StringBuilder

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val date = str.split(" ")
    var month = -1
    if (date.size > 3) return ""
    try {
        val year = date.last().toInt()
    } catch (e: NumberFormatException) {
        return ""
    }
    try {
        val day = date.first().toInt()
    } catch (e: NumberFormatException) {
        return ""
    }
    for (dates in date) when (dates) {
        "января" -> month = 1
        "февраля" -> month = 2
        "марта" -> month = 3
        "апреля" -> month = 4
        "мая" -> month = 5
        "июня" -> month = 6
        "июля" -> month = 7
        "августа" -> month = 8
        "сентября" -> month = 9
        "октября" -> month = 10
        "ноября" -> month = 11
        "декабря" -> month = 12
    }
    if (month == -1) return ""
    if (daysInMonth(month, date.last().toInt()) < date.first().toInt())
        return ""
    return String.format("%02d.%02d.%d", date.first().toInt(),
            month, date.last().toInt())
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val date = digital.split(".")
    var month = date[1]
    if (date.size > 3) return ""
    try {
        val year = date.last().toInt()
    } catch (e: NumberFormatException) {
        return ""
    }
    try {
        val day = date.first().toInt()
    } catch (e: NumberFormatException) {
        return ""
    }
    when (date[1].toInt()) {
        1 -> month = "января"
        2 -> month = "февраля"
        3 -> month = "марта"
        4 -> month = "апреля"
        5 -> month = "мая"
        6 -> month = "июня"
        7 -> month = "июля"
        8 -> month = "августа"
        9 -> month = "сентября"
        10 -> month = "октября"
        11 -> month = "ноября"
        12 -> month = "февраля"
    }
    if (month == date[1]) return ""
    if (daysInMonth(date[1].toInt(), date.last().toInt()) < date.first().toInt())
        return ""
    return String.format("%d %s %d", date.first().toInt(),
            month, date.last().toInt())
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String {
    val s = StringBuilder()
    var n = 0
    var b = true
    for (symbol in phone) {
        when (symbol) {
            '+' -> if (n == 0) {
                s.append("+")
                n++
            }
            '-' -> {
            }
            '(' -> {
            }
            ')' -> {
            }
            '1' -> s.append(1)
            '0' -> s.append(0)
            '2' -> s.append(2)
            '3' -> s.append(3)
            '4' -> s.append(4)
            '5' -> s.append(5)
            '6' -> s.append(6)
            '7' -> s.append(7)
            '8' -> s.append(8)
            '9' -> s.append(9)
            ' ' -> {

            }
            else -> b = false
        }
        if (b == false) break
    }
    return if (b) s.toString() else ""
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    val s = jumps.split(" ")
    var max = -1
    for (result in s) {
        if ((result != "-") && (result != "%")) {
            try {
                val res = result.toInt()
            } catch (e: NumberFormatException) {
                return -1
            }
            if (result.toInt() > max)
                max = result.toInt()
        }
    }
    return max
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    val s = jumps.split(" ")
    var max = -1
    for (i in 0 until s.size step 2) {
        try {
            val height = s[i].toInt()
        } catch (e: NumberFormatException) {
            return -1
        }
        if (s[i + 1].contains("+") ||
                s[i + 1].contains("%") ||
                s[i + 1].contains("-")) {
            if (s[i + 1].contains("+"))
                max = s[i].toInt()
        } else {
            max = -1
            break
        }
    }
    return max
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val s = expression.split(" ")
    var summ = 0
    var j: Int
    when {
        s[0] == "-" -> {
            try {
                val n = s[1].toInt()
            } catch (e: IllegalArgumentException) {
                throw e
            }
            summ -= s[1].toInt()
             j = 1
        }
        s[0] == "+" -> {
            try {
                val n = s[1].toInt()
            } catch (e: IllegalArgumentException) {
                throw e
            }
            summ += s[1].toInt()
            j = 1
        }
        else -> {
            if (s[0].toInt().toString() == s[0])
                try {
                    val n = s[0].toInt()
                } catch (e: IllegalArgumentException) {
                    throw e
                }
            else try {
                val n = "+".toInt()
            } catch (e: IllegalArgumentException) {
                throw e
            }
            summ += s[0].toInt()
             j = 0
        }
    }
    if (s.size >= 3)
        for (i in j + 1 until s.size step 2)
            when {
                s[i] == "-" -> {
                    if ((s[i + 1].toInt().toString() != s[i + 1]) ||
                            (s[i + 1].toInt() < 0))
                        try {
                            val n = "+".toInt()
                        } catch (e: IllegalArgumentException) {
                            throw e
                        }
                    summ -= s[i + 1].toInt()

                }
                s[i] == "+" -> {
                    if ((s[i + 1].toInt().toString() != s[i + 1]) ||
                            (s[i + 1].toInt() < 0))
                        try {
                            val n = "+".toInt()
                        } catch (e: IllegalArgumentException) {
                            throw e
                        }
                    summ += s[i + 1].toInt()
                }
                else -> try {
                    val n = "+".toInt()
                } catch (e: IllegalArgumentException) {
                    throw e
                }
            }
    return summ
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */

fun generalLeter(string: String): String {
    val s = StringBuilder()
    val first = string.first()
    val small = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
    val big = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
    if (small.contains(first))
        s.append(big[small.indexOf(first)])
    if (big.contains(first))
        s.append(small[big.indexOf(first)])
    if (string.length > 1)
    s.append(string, 1, string.length)
    return s.toString()
}

fun firstDuplicateIndex(str: String): Int {
    val s = str.split(" ")
    var number = 0
    for (i in 0 until (s.size-1)) {
        val word = generalLeter(s[i])
        if ((word == s[i + 1]) || (s[i] == s[i + 1]))
            return number else
            number += s[i].length + 1
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    var maxVal = 0.0
    var goodH = ""
    val s = description.split("; ")
    if (s[0]=="") return ""
    for (good in s) {
        val n = good.split(" ")
        val price = n[1].split(".")
        if (price.size != 2) try {
            val g = "+".toInt()
        } catch (e: NumberFormatException) {
            return ""
        }
        val priceN = String.format("%d.%d", price[0].toInt(), price[1].toInt())
        if ((n.size < 2) || (priceN != n[1]))
            try {
                val g = "+".toInt()
            } catch (e: NumberFormatException) {
                return ""
            }
        if ((price[0].toInt() + price[1].toDouble()/10) > maxVal) {
            goodH = n[0]
            maxVal = (price[0].toInt() + price[1].toDouble()/10)
        }
}
    return goodH
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()
