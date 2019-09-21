package equations.linear

import kotlin.random.Random
import kotlin.random.nextInt


class LinearEquation {
    // Generates a generic Linear Equation with the formula below, where A, B and C are coefficients.
    // Ax - B = C
    fun genericEquation(){
        val firstCoefficient = Random.nextInt(1..10)
        val secondCoefficient = Random.nextInt(1..100)
        val thirdCoefficient = Random.nextInt(1..100)

        val equation = buildString {
            val firstCoeffSign = arrayOf("+", "-").random()
            val midCoeff = arrayOf("+", "-").random();
            val thirdCoeffSign = arrayOf("+", "-").random()
            val fourthCoeffSign = arrayOf("+", "-").random()

            if(firstCoeffSign == "-") {
                append(firstCoeffSign)
            }

            append(firstCoefficient)
            append((arrayOf("x", "y", "z", "t", "f")).random())

            if(midCoeff == "+" && thirdCoeffSign == "-"){
                append(" - ")
            } else {
                append(" + ")
            }

            append(secondCoefficient)
            append(" = ")

            if(fourthCoeffSign == "+") {
                append(thirdCoefficient)
            } else {
                append("-").append(thirdCoefficient)
            }
        }
        println(equation)
    }
}