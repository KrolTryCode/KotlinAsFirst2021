@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.*
import kotlin.math.sqrt

// Урок 2: ветвления (здесь), логический тип (см. 2.2).
// Максимальное количество баллов = 6
// Рекомендуемое количество баллов = 5
// Вместе с предыдущими уроками = 9/12

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая (2 балла)
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String =
    if (age in 10..20 || age in 110..120) "$age лет"
    else {
        when {
            age % 10 == 1 -> "$age год"
            age % 10 in 2..4 -> "$age года"
            age % 10 in 5..9 -> "$age лет"
            else -> "$age лет"
        }
    }


/**
 * Простая (2 балла)
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    if (((t1 * v1 + t2 * v2 + t3 * v3) / 2) < t1 * v1) {
        return ((t1 * v1 + t2 * v2 + t3 * v3) / 2) / v1
    }
    if ((((t1 * v1 + t2 * v2 + t3 * v3) / 2) > t1 * v1) && (((t1 * v1 + t2 * v2 + t3 * v3) / 2) < v1 * t1 + t2 * v2)) {
        return t1 + (((t1 * v1 + t2 * v2 + t3 * v3) / 2) - t1 * v1) / v2
    }
    if (((t1 * v1 + t2 * v2 + t3 * v3) / 2) > v1 * t1 + t2 * v2) {
        return t1 + t2 + (((t1 * v1 + t2 * v2 + t3 * v3) / 2) - v1 * t1 - v2 * t2) / v3
    }
    if (((t1 * v1 + t2 * v2 + t3 * v3) / 2) == v1 * t1 + v2 * t2) {
        return t1 + t2
    }
    if (((t1 * v1 + t2 * v2 + t3 * v3) / 2) == t1 * v1) {
        return t1
    }
    return t1 + t2 + t3
}

/**
 * Простая (2 балла)
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    if (kingX != rookX1 && kingX != rookX2 && kingY != rookY1 && kingY != rookY2) {
        return 0
    }
    if ((rookX1 == kingX || rookY1 == kingY) && rookX2 != kingX && rookY2 != kingY) {
        return 1
    }
    if ((rookX2 == kingX || rookY2 == kingY) && rookX1 != kingX && rookY1 != kingY) {
        return 2
    }
    return 3
}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    if (rookX != kingX && kingY != rookY && abs(kingX - bishopX) != abs(kingY - bishopY)) {
        return 0
    }
    if ((rookX == kingX || rookY == kingY) && abs(kingX - bishopX) != abs(kingY - bishopY)) {
        return 1
    }
    if ((rookX != kingX && rookY != kingY) && abs(kingX - bishopX) == abs(kingY - bishopY)) {
        return 2
    }
    return 3
}

/**
 * Простая (2 балла)
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    if ((maxOf(a, b, c)) > minOf(a, b, c) + (a + b + c - maxOf(a, b, c) - minOf(a, b, c))) {
        return -1
    }
    if (((maxOf(a, b, c)).pow(2)) == minOf(a, b, c).pow(2) + (a + b + c - maxOf(a, b, c) - minOf(a, b, c)).pow(2)) {
        return 1
    } else if (((maxOf(a, b, c)).pow(2)) < minOf(a, b, c).pow(2) + (a + b + c - maxOf(a, b, c) - minOf(
            a,
            b,
            c
        )).pow(2)
    ) {
        return 0
    } else if (((maxOf(a, b, c)).pow(2)) > minOf(a, b, c).pow(2) + (a + b + c - maxOf(a, b, c) - minOf(
            a,
            b,
            c
        )).pow(2)
    ) {
        return 2
    }
    return -1
}


/**
 * Средняя (3 балла)
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    if (a == b && b == c && c == d) {
        return b - c
    }
    if ((b < c) || (d < a)) {
        return -1
    }
    if ((b == c) && (a < d)) {
        return c - b
    }
    if ((d == a) && (c < b)) {
        return d - a
    }
    if ((d == c) && (a < b)) {
        return d - c
    }
    if ((b == a) && (c < d)) {
        return b - a
    }
    if ((a < c) && (d < b)) {
        return d - c
    }
    if ((c <= a) && (b <= d)) {
        return b - a
    }
    if ((a < c) && (b < d)) {
        return b - c
    }
    if ((c <= a) && (d <= b)) {
        return d - a
    }
    if ((a == c) && (b == d)) {
        return abs(c - d)
    }
    if (a == c && b <= d) {
        return b - c
    }
    if (a == c && b >= d) {
        return d - c
    }
    if (a != b && b == c && c == d) {
        return c - b
    }
    if (c !=d && a == b && b == c) {
        return c - b
    }
    return -1
}