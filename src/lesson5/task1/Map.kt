@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

import java.lang.Double.MAX_VALUE

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
        shoppingList: List<String>,
        costs: Map<String, Double>): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
        phoneBook: MutableMap<String, String>,
        countryCode: String) {
    val namesToRemove = mutableListOf<String>()
    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
        text: List<String>,
        vararg fillerWords: String): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {

    val a = mapA.toMutableMap()
    for ((key, value) in mapB)
        if ((a.containsKey(key)) && (a[key] != value))
            a[key] = a[key] + ", $value"
        else a[key] = value
    return a
}

/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */

fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    val mapS = mutableMapOf<Int, List<String>>()
    for (i in 5 downTo 2) {
        if (grades.containsValue(i)) {
            val listS = mutableListOf<String>()
            for ((name, grade) in grades) {
                if (grade == i) listS.add(name)
            }
            listS.sortDescending()
            mapS[i] = listS
        }
    }
    return mapS.toMap()
}

/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean = a.all { (key, value) -> b[key] == value }

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
/*Not done*/
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val a = mutableMapOf<String, Double>()
    val repeats = mutableMapOf<String, Int>()
    for ((name, price) in stockPrices) {
        if (a[name] != null) {
            a[name] = a[name]!! + price
            repeats[name] = repeats[name]!! + 1
        } else {
            a[name] = price
            repeats[name] = 1
        }
    }
    a.forEach { (name, price) -> a[name] = price / (repeats[name] ?: 1) }
    return a
}

/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? = stuff
        .filter { (_, product) -> product.first == kind }
        .minBy { (_, product) -> product.second }
        ?.component1()

/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */
fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    val a = mutableMapOf<String, MutableSet<String>>()
    for ((name, people) in friends) {
        if (people != emptySet<String>())
            a[name] = people.toMutableSet() else
            a[name] = emptySet<String>().toMutableSet()
    }
    for ((name, friend) in a)
        for ((friendName, newFriends) in a)
            if ((friendName != name) && (friend.contains(friendName)))
                friend += newFriends

    return a
}

/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>) = a.keys.removeIf { b[it] == a[it] }

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> = a.filter { name -> b.contains(name) }

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean = chars.containsAll(word.toSet())


/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> {
    val a = mutableMapOf<String, Int>()
    list.forEach { letter -> if (a[letter] == null) a[letter] = 1 else a[letter] = (a[letter] ?: 0) + 1 }
    return a.filter { (_, number) -> number > 1 }
}

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
    var s = 0
    for (word in words) if (words.contains(word.reversed())) {
        s++
        break
    }
    return s > 0
}

/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    var a = Pair(-1, -1)
    for (i in 0..(list.size - 1))
        for (j in i + 1 until list.size)
            if (list[i] + list[j] == number) {
                a = Pair(i, j)
                break
            }
    return a
}

/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */


fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String>  {
    val useFactor = mutableListOf<String>()
    var capLeft = capacity.toDouble()
    var j = ""
    var g = 0.0
    //Building list of treasures
    //by descending of utility ratio
    for ((_, _) in treasures) {
        for ((treasure, char) in treasures)
            if ((char.second / char.first).toDouble() > g)
                if (useFactor.contains(treasure)) {
                } else {
                    g = (char.second / char.first).toDouble()
                    j = treasure
                }
        useFactor.add(j)
        g = 0.0
    }
    //variable for saving first and last picked treasure names
    var firsT = ""
    var lasT = ""

    //variables for saving next step values and const
    val stableTr = mutableSetOf<String>()
    var oldTr = mutableSetOf<String>()
    var oldPrice = 0.0

    //taking the most valuable first and then
    //trying to make more useful combination

    for (treasure in useFactor) {
        var price = 0.0
        val newTr = mutableSetOf<String>()
        var capNewLeft = capLeft
        //Finding the first taken treasure
        for (name in useFactor)
            if ((treasures[name]?.first ?: 0) < capNewLeft) {
                firsT = name
                break
            }
        //making new treasure list
        for (name in useFactor)
            if ((treasures[name]?.first ?: 0) < capNewLeft) {
                newTr.add(name)
                capNewLeft -= treasures[name]?.first ?: 0
                price += treasures[name]?.second ?: 0
            }
        //choosing next step
        if (price >= oldPrice) {
            oldTr = newTr
            oldPrice = price
        } else if (stableTr.contains(lasT)) {
        } else {
            stableTr.add(lasT)
            capLeft -= treasures[treasure]?.first ?: 0
            oldPrice -= treasures[treasure]?.second ?: 0
        }
        if (treasure == firsT) lasT = treasure
        useFactor.remove(treasure)
    }
    return (stableTr + oldTr)
}
