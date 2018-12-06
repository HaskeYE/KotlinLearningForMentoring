@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1


import lesson1.task1.sqr
import java.lang.Math.pow
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n)
        result = result * i // Please do not fix in master
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var s = abs(n)
    var g = 0
    return if (abs(n) < 10) 1 else {
        while (s > 0) {
            s /= 10
            g += 1
        }
        g
    }
}


/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n <= 2) return 1
    else {
        var a = 1
        var b = 1
        for (i in 2..(n - 1)) {
            if (i % 2 == 0) a += b else b += a
        }
        return max(a, b)
    }
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val a = max(m, n)
    val b = min(m, n)
    var g = 1
    if (a % b == 0) return a else {
        while ((a * g) % b != 0) g++
        return a * g
    }
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var x = 2
    for (i in 2..(floor(sqrt(n.toDouble()))).toInt())
        while (n % x != 0) x++
    return if (n % x == 0) x else n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    /*  var c = true*/
   // Pretty better and faster one...
    if (min(m, n) == 1) return true
    var a = m
    var b = n
    while (max(a, b) % min(a, b) != 0) {
        if (a > b) a = max(a, b) % min(a, b)
        else b = max(a, b) % min(a, b)
    }
    return (min(a, b) == 1)
    /*if (m == n) c = false else
        for (i in 2..(min(min(m, n), (floor(max(m, n).toDouble() / 2)).toInt()))) {
            if ((n % i == 0) && (m % i == 0)) {
                c = false
                break
            }
        }
    return c*/
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val k = sqrt(n.toDouble())
    val g = floor(k)
    return (g >= sqrt(m.toDouble()))
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var n = x
    var g = 0
    while (n != 1) {
        if (n % 2 == 0) n /= 2 else n = 3 * n + 1
        g++
    }
    return g
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var s = abs(x % (2 * PI))
    var g = 3.0
    var n = 1
    val j = abs(x % (2 * PI))
    while ((pow(j, g)) / factorial(g.toInt()) >= eps) {
        if (n % 2 != 0)
            s -= ((pow(j, g)) / factorial(g.toInt())) else
            s += ((pow(j, g)) / factorial(g.toInt()))
        g += 2
        n++
    }
    return if (x < 0) -s else s

}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double = (sin(x + PI / 2, eps))

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var f: Int
    var digitN = 1
    var s = n
    var g: Int
    var t: Int
    for (i in 1..(floor(digitNumber(n).toDouble() / 2).toInt())) {
        f = pow(10.0, (digitNumber(n).toDouble() - digitN)).toInt()
        g = ((s / f) % 10)
        t = (s / pow(10.0, (digitN.toDouble() - 1)).toInt()) % 10
        s = s - g * f - (t * pow(10.0, (digitN.toDouble() - 1))).toInt() +
                +t * f + g * pow(10.0, (digitN.toDouble() - 1)).toInt()
        digitN++
    }
    return s
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = n == revert(n)

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val s = n % 10
    var g = n / 10
    while (g > 0) {
        if (g % 10 != s) return true
        g /= 10
    }
    return false
}


/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var g = 0
    var s = 1
    while (g < n) {
        g += digitNumber(sqr(s))
        s++
    }
    return (sqr(s - 1) / pow(10.0, (g - n).toDouble()).toInt()) % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var g = 2
    var s = 3
    if (n <= 2) return 1 else {
        while (g < n) {
            g += digitNumber(fib(s))
            s++
        }
        return (fib(s - 1) / pow(10.0, (g - n).toDouble()).toInt()) % 10
    }
}
