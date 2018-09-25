@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import lesson4.task1.abs
import kotlin.math.sqrt

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
        sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    val a = ((number % 10) + (number / 10 % 10))
    val b = (((number / 100) % 10) + (number / 1000))
    return (a == b)
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean =
         ((x1 == x2) or
            (y1 == y2) or
            ((kotlin.math.abs(x1 - x2)) == (kotlin.math.abs(y1 - y2))))


/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int =

     when {
        ((year % 4 == 0) and (year % 100 != 0) and (month == 2)) -> 29
        ((year % 400 == 0) and (month == 2)) -> 29
        month == (1 or 5 or 7 or 8 or 10 or 12) -> 31
        month == 4 or 6 or 9 or 11 -> 30
        else -> 28
    }


/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(x1: Double, y1: Double, r1: Double,
                 x2: Double, y2: Double, r2: Double): Boolean =
        (((sqrt(sqr(x1 - x2) + sqr(y1 - y2))) + r1) <= r2)


/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    val m: Int
    val m1: Int
    if (a <= b)
        if (a <= c) {
            m = a
            if (b <= c) m1 = b else m1 = c
        } else {
            m = c; m1 = a
        }
    else if (b <= c) {
        m = b
        if (a <= c) m1 = a else m1 = c
    } else {
        m = c; m1 = b
    }
    return (((m <= r) and (m1 <= s)) or ((m <= s) and (m1 <= r)))
}
