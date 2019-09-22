package equations.linear

import kotlin.random.Random
import kotlin.random.Random.Default.nextBoolean
import kotlin.random.nextInt


class LinearEquation {
    // Generates a generic Linear Equation with the formula below, where A, B and C are coefficients.
    // Ax - B = C
    fun genericEquation(){
        val firstCoefficient = Random.nextInt(1..9)
        val secondCoefficient = Random.nextInt(1..100 )
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

    fun withFractionsEquation(){
        val firstCoefficient = Random.nextInt(1..9)
        val secondCoefficient = Random.nextInt(1..100)
        val thirdCoefficient = Random.nextInt(1..100)
        val find = (arrayOf("x", "y", "z", "t", "f")).random()

        val equation = buildString {
            val firstCoeffSign = arrayOf("+", "-").random()
            val midCoeff = arrayOf("+", "-").random();
            val thirdCoeffSign = arrayOf("+", "-").random()
            val fourthCoeffSign = arrayOf("+", "-").random()

            if(firstCoeffSign == "-") {
                append(firstCoeffSign)
            }

            append(firstCoefficient)
            append(find)
            if(nextBoolean()) {
                val firstCoeffDivisor = Random.nextInt(1..10)
                append("/").append(firstCoeffDivisor)
            }

            if(midCoeff == "+" && thirdCoeffSign == "-"){
                append(" - ")
            } else {
                append(" + ")
            }

            append(secondCoefficient)
            if(nextBoolean()) {
                val secondCoeffDivisor = Random.nextInt(1..10)
                append("/").append(secondCoeffDivisor)
            }
            if(nextBoolean()){
                append(find)
            }
            append(" = ")

            if(fourthCoeffSign == "+") {
                append(thirdCoefficient)
            } else {
                append("-").append(thirdCoefficient)
                if(nextBoolean()) {
                    val thirdCoeffDivisor = Random.nextInt(1..10)
                    append("/").append(thirdCoeffDivisor)
                }
                if(nextBoolean()){
                    append(find)
                }
            }
        }
        println(equation)

    }

    fun getRandomBoolean(): Boolean {
        return nextBoolean()
    }
}