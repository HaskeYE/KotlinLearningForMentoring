@file:Suppress("UNUSED_PARAMETER")

package lesson8.task1

import lesson1.task1.sqr
import java.lang.IllegalArgumentException
import java.lang.Math.ulp
import kotlin.math.*

/**
 * Точка на плоскости
 */
data class Point(val x: Double, val y: Double) {
    /**
     * Пример
     *
     * Рассчитать (по известной формуле) расстояние между двумя точками
     */
    fun distance(other: Point): Double = sqrt(sqr(x - other.x) + sqr(y - other.y))

    fun center(other: Point): Point = Point((x + other.x) / 2, (y + other.y) / 2)
}

/**
 * Треугольник, заданный тремя точками (a, b, c, см. constructor ниже).
 * Эти три точки хранятся в множестве points, их порядок не имеет значения.
 */
class Triangle private constructor(private val points: Set<Point>) {

    private val pointList = points.toList()

    val a: Point get() = pointList[0]

    val b: Point get() = pointList[1]

    val c: Point get() = pointList[2]

    constructor(a: Point, b: Point, c: Point) : this(linkedSetOf(a, b, c))

    /**
     * Пример: полупериметр
     */
    fun halfPerimeter() = (a.distance(b) + b.distance(c) + c.distance(a)) / 2.0

    /**
     * Пример: площадь
     */
    fun area(): Double {
        val p = halfPerimeter()
        return sqrt(p * (p - a.distance(b)) * (p - b.distance(c)) * (p - c.distance(a)))
    }

    /**
     * Пример: треугольник содержит точку
     */
    fun contains(p: Point): Boolean {
        val abp = Triangle(a, b, p)
        val bcp = Triangle(b, c, p)
        val cap = Triangle(c, a, p)
        return abp.area() + bcp.area() + cap.area() <= area()
    }

    override fun equals(other: Any?) = other is Triangle && points == other.points

    override fun hashCode() = points.hashCode()

    override fun toString() = "Triangle(a = $a, b = $b, c = $c)"
}

/**
 * Окружность с заданным центром и радиусом
 */
data class Circle(val center: Point, val radius: Double) {
    /**
     * Простая
     *
     * Рассчитать расстояние между двумя окружностями.
     * Расстояние между непересекающимися окружностями рассчитывается как
     * расстояние между их центрами минус сумма их радиусов.
     * Расстояние между пересекающимися окружностями считать равным 0.0.
     */
    fun distance(other: Circle): Double {
        val a = center.distance(other.center) - (radius + other.radius)
        return if (a > 0) a else 0.0
    }

    /**
     * Тривиальная
     *
     * Вернуть true, если и только если окружность содержит данную точку НА себе или ВНУТРИ себя
     */
    fun contains(p: Point): Boolean = center.distance(p) <= radius
}

/**
 * Отрезок между двумя точками
 */
data class Segment(val begin: Point, val end: Point) {
    override fun equals(other: Any?) =
            other is Segment && (begin == other.begin && end == other.end || end == other.begin && begin == other.end)

    override fun hashCode() =
            begin.hashCode() + end.hashCode()

    //Maybe will be useful...
    fun length() = begin.distance(end)
}

/**
 * Средняя
 *
 * Дано множество точек. Вернуть отрезок, соединяющий две наиболее удалённые из них.
 * Если в множестве менее двух точек, бросить IllegalArgumentException
 */
fun diameter(vararg points: Point): Segment {
    if (points.size < 2) throw IllegalArgumentException()
    var maxLength = 0.0
    var segment = Segment(points[0], points[1])
    for (i in 0 until points.size)
        for (j in i + 1 until points.size)
            if (points[i].distance(points[j]) > maxLength) {
                maxLength = points[i].distance(points[j])
                segment = Segment(points[i], points[j])
            }
    return segment
}

/**
 * Простая
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle =
        Circle((diameter.begin.center(diameter.end)),
                diameter.begin.distance(diameter.end) / 2)


/**
 * Прямая, заданная точкой point и углом наклона angle (в радианах) по отношению к оси X.
 * Уравнение прямой: (y - point.y) * cos(angle) = (x - point.x) * sin(angle)
 * или: y * cos(angle) = x * sin(angle) + b, где b = point.y * cos(angle) - point.x * sin(angle).
 * Угол наклона обязан находиться в диапазоне от 0 (включительно) до PI (исключительно).
 */
class Line(val b: Double, val angle: Double) {
    init {
        require(angle >= 0 && angle < PI) { "Incorrect line angle: $angle" }
    }

    constructor(point: Point, angle: Double) : this(point.y * cos(angle) - point.x * sin(angle), angle)

    /**
     * Средняя
     *
     * Найти точку пересечения с другой линией.
     * Для этого необходимо составить и решить систему из двух уравнений (каждое для своей прямой)
     */
    //Algorithm from the internet
    fun crossPoint(other: Line): Point {
        val cos1 = cos(angle)
        val cos2 = cos(other.angle)
        return if ((abs(cos1) > abs(cos2)))
            Point((cos2 * b - cos1 * other.b)
                    / sin(other.angle - angle),
                    ((cos2 * b - cos1 * other.b)
                            / sin(other.angle - angle) * sin(angle) + b) / cos1)
        else Point((cos2 * b - cos1 * other.b)
                / sin(other.angle - angle),
                ((cos2 * b - cos1 * other.b)
                        / sin(other.angle - angle) * sin(other.angle) + other.b) / cos2)
    }

    override fun equals(other: Any?) = other is Line && angle == other.angle && b == other.b

    override fun hashCode(): Int {
        var result = b.hashCode()
        result = 31 * result + angle.hashCode()
        return result
    }

    override fun toString() = "Line(${cos(angle)} * y = ${sin(angle)} * x + $b)"
}

/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line {
    val alpha = atan2(s.end.y - s.begin.y, s.end.x - s.begin.x)
    return Line(s.begin, correctAngle(alpha))
}

/**
 * Средняя
 *
 * Построить прямую по двум точкам
 */
fun lineByPoints(a: Point, b: Point): Line = lineBySegment(Segment(a, b))

/**
 * Сложная
 *
 * Построить серединный перпендикуляр по отрезку или по двум точкам
 */
fun bisectorByPoints(a: Point, b: Point): Line {
    val newAngle = correctAngle(lineByPoints(a, b).angle + (PI / 2))
    val g = a.center(b)
    return Line(g, newAngle)
}

fun correctAngle(angle: Double): Double {
    var newAngle: Double
    when {
        angle >= PI -> newAngle = angle % PI
        angle >= 0 -> newAngle = angle
        else -> {
            newAngle = angle
            while (newAngle < 0) newAngle += PI
        }
    }
    if ((PI - newAngle) <= PI.ulp)
        newAngle = 0.0
    return newAngle
}

/**
 * Средняя
 *
 * Задан список из n окружностей на плоскости. Найти пару наименее удалённых из них.
 * Если в списке менее двух окружностей, бросить IllegalArgumentException
 */
fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> {
    if (circles.size < 2) throw IllegalArgumentException()
    var minDist = Double.MAX_VALUE
    var min = Pair(circles[0], circles[1])
    if (circles.size == 2) return min
    for (i in 0 until circles.size)
        for (j in i + 1 until circles.size)
            if (circles[i].distance(circles[j]) < minDist) {
                minDist = circles[i].distance(circles[j])
                min = Pair(circles[i], circles[j])
            }
    return min
}

/**
 * Сложная
 *
 * Дано три различные точки. Построить окружность, проходящую через них
 * (все три точки должны лежать НА, а не ВНУТРИ, окружности).
 * Описание алгоритмов см. в Интернете
 * (построить окружность по трём точкам, или
 * построить окружность, описанную вокруг треугольника - эквивалентная задача).
 */
//Algorithm from the internet
fun circleByThreePoints(a: Point, b: Point, c: Point): Circle {
    val center = bisectorByPoints(a, b).crossPoint(bisectorByPoints(b, c))
    return Circle(center, center.distance(a))
}

/**
 * Очень сложная
 *
 * Дано множество точек на плоскости. Найти круг минимального радиуса,
 * содержащий все эти точки. Если множество пустое, бросить IllegalArgumentException.
 * Если множество содержит одну точку, вернуть круг нулевого радиуса с центром в данной точке.
 *
 * Примечание: в зависимости от ситуации, такая окружность может либо проходить через какие-либо
 * три точки данного множества, либо иметь своим диаметром отрезок,
 * соединяющий две самые удалённые точки в данном множестве.
 */

fun containsDots(circle: Circle, points: Array<out Point>): Boolean {
    var containsAllDots = true
    for (point in points)
        if (!circle.contains(point)) containsAllDots = false
    return containsAllDots
}

fun minContainingCircle(vararg points: Point): Circle {
    //simple conditions
    if (points.isEmpty()) throw IllegalArgumentException("no points found")
    if (points.size == 1) return Circle(points[0], 0.0)

    //for other conditions
    var minCircle = Circle(points[0], Double.MAX_VALUE)

    //condition with two or more points
    var maxLength = Double.MIN_VALUE
    var dots = Pair(points[0], points[1])
    for (i in 0 until points.size)
        for (j in i + 1 until points.size)
            if (points[i].distance(points[j]) > maxLength) {
                maxLength = points[i].distance(points[j])
                dots = Pair(points[i], points[j])
            }
    if (containsDots(circleByDiameter(Segment(dots.first, dots.second)), points))
        minCircle = circleByDiameter(Segment(dots.first, dots.second))

    //condition for three or more points
    if (points.size > 2) {
        for (i in 0 until points.size)
            for (j in i + 1 until points.size)
                for (k in j + 1 until points.size) {
                    val newCircle =
                            circleByThreePoints(points[i], points[j], points[k])
                    if (newCircle.radius < minCircle.radius
                            && containsDots(newCircle, points))
                        minCircle = newCircle
                }
    }
    return minCircle
}

