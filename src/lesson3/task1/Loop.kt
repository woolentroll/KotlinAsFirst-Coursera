@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

import lesson6.task1.firstDuplicateIndex
import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
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
    for (m in 2..n/2) {
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
fun digitNumber(n: Int): Int =
        when {
            n == 0 -> 1
            abs(n) < 10 -> 1
            else -> 1 + digitNumber(n / 10)
        }
//fun digitNumber(n: Int): Int {
//    // loop
//    var cnt = 0
//    var tmp = n;
//    do {
//        cnt += 1
//        tmp /= 10
//    } while (tmp > 0);
//    return cnt;
//
//
//    // recursion
//    if (n == 0) return 1
//    if (abs(n) < 10) return 1
//    return 1 + digitNumber(n / 10)
//}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    // loop
    if (n < 1) return 0
    if (n < 3) return 1

    var n1 = 1
    var n2 = 1
    var tmp = 0
    for (i in 3 until n) {
        tmp = n1
        n1 = n2
        n2 += tmp
    }
    return n1 + n2

//    // recursion
//    if (n < 1) return 0
//    if (n < 3) return 1
//    return fib(n - 2) + fib(n - 1)
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
//fun main(args: Array<String>) {
////    println(lcm(12, 16))
////    println(minDivisor(75 / 3))
////    println(maxDivisor(24))
//}


fun lcm(m: Int, n: Int): Int {
    // base
    if (m == 0 || n == 0) return 0
    if (m == 1 || n == 1) return max(m, n)
    if (m == n) return m

    for (i in max(m, n)..(m * n) step max(m, n)) {
        if (i % m == 0 && i % n == 0) return i
    }
    return m * n

//    // recursion NOT WORK
//    val max_v = max(m, n)
//    val min_v = min(m, n)
//    if (max_v == 0 || min_v == 0) return 0
//    var tmp = max_v % min_v
//    if (tmp == 0) {
//        tmp = 0
//    } else {
//        tmp = min_v / tmp
//    }
//    return lcm(tmp, max_v) + max_v
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    // loop
    if (n < 2) return -1
    if (n == 2) return 2
    if (n % 2 == 0) return 2
    for (i in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % i == 0) return i
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    // loop
    if (n <= 1) return -1
    if (n % 2 == 0) return n / 2
    for (i in (n / 2) - 1 downTo 1) {
        if (n % i == 0) return i
    }
    return 1
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {

    val min_val = min(m, n)
    if (min_val == 1) return true
    val max_val = max(m, n)
    val min_div = minDivisor(min_val)
    for (i in min_div..min_val) {
        if (min_val % i == 0 && max_val % i == 0) return false
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val minv = min(m, n)
    val maxv = max(m, n)
    for (i in sqrt(minv.toDouble()).toInt()..sqrt(maxv.toDouble()).toInt()) {
        println(i)
        if (i * i in minv..maxv) return true
    }
    return false
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
    var tmp = x
    var cnt = 0
    while (tmp != 1) {
        cnt += 1
        if (tmp % 2 == 0) {
            tmp /= 2
        } else {
            tmp = 3 * tmp + 1
        }
    }
    return cnt
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int = TODO()

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = TODO()

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean = TODO()

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
    // loop
    var seq_len = 0
    var int_v = 0
    while (n > seq_len) {
        int_v += 1
        seq_len += digitNumber(int_v * int_v)
    }
    val tmp = (10.toDouble().pow(seq_len - n).toInt())
    return int_v * int_v % (tmp * 10) / tmp
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
fun fibSequenceDigit(n: Int): Int = TODO()
