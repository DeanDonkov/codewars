package equations.linear

import kotlin.properties.Delegates
import kotlin.random.Random
import kotlin.random.Random.Default.nextBoolean
import kotlin.random.nextInt


class LinearEquation{
    // Generates a generic Linear Equation with the formula below, where A, B and C are coefficients.
    // Ax - B = C
    // TODO: Make the result easier. Done.
    // TODO: Split the Classes A bit, too crammed I think ?


    private var firstCoefficient by Delegates.notNull<Int>()
    private var secondCoefficient by Delegates.notNull<Int>()
    private var thirdCoefficient by Delegates.notNull<Int>()

    private lateinit var firstCoeffPolarity: String
    private lateinit var secondCoeffPolarity: String
    private lateinit var thirdCoeffPolarity: String

    private lateinit var equation: String


    fun firstCoeffRange(lowBound: Int, highBound: Int): LinearEquation{
        firstCoefficient = (lowBound until highBound + 1).random()
        return this
    }

    fun secondCoeffRange(lowBound: Int, highBound: Int): LinearEquation{
        secondCoefficient = (lowBound until highBound + 1).random()
        return this
    }

    fun thirdCoeffRange(lowBound: Int, highBound: Int): LinearEquation{
        thirdCoefficient = (lowBound until highBound + 1).random()
        return this
    }

    fun firstCoeffSign(sign: SignENUM): LinearEquation {
            firstCoeffPolarity = sign.sign
        return this
        }
    fun secondCoeffSign(sign: SignENUM): LinearEquation {
        secondCoeffPolarity = sign.sign
        return this
    }

    fun thirdCoeffSign(sign: SignENUM): LinearEquation {
        thirdCoeffPolarity = sign.sign
        return this
    }

    fun build(): LinearEquation {
        val eq = buildString {
            append(firstCoeffPolarity)
            append(firstCoefficient)
            append("x")
            append("$secondCoeffPolarity")
            append(secondCoefficient)
            append("=")
            if(thirdCoeffPolarity == "+") {
                append(thirdCoefficient)
            } else {
                append(thirdCoeffPolarity)
                append(thirdCoefficient)
            }
        }
        this.equation = eq
        return this
    }



    fun generateLinearEquation(){
        val firstCoefficient = Random.nextInt(1..9)
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

    enum class SignENUM(val sign: String) {
        MINUS("-"), PLUS("+");
    }

    // TODO: Implement fractions in the pattern.
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

    fun solveEquation() {
        val n = equation.length
        var sign = 1
        var coeff = 0
        var total = 0
        var i = 0

        // Traverse the equation
        for (j in 0 until n) {
            if (equation[j] == '+' || equation[j] == '-') {
                if (j > i)
                    total += sign * Integer.parseInt(
                            equation.substring(i, j))
                i = j
            } else if (equation[j] == 'x') {
                if (i == j || equation[j - 1] == '+')
                    coeff += sign
                else if (equation[j - 1] == '-')
                    coeff -= sign
                else
                    coeff += sign * Integer.parseInt(
                            equation.substring(i, j))
                i = j + 1
            } else if (equation[j] == '=') {
                if (j > i)
                    total += sign * Integer.parseInt(
                            equation.substring(i, j))
                sign = -1
                i = j + 1
            }// Flip sign once
            // '=' is seen
            // For cases such
            // as: x, -x, +x
        }

        // There may be a
        // number left in the end
        if (i < n)
            total += sign * Integer.parseInt(
                    equation.substring(i))

        // For infinite
        // solutions
        if (coeff == 0 && total == 0)
            println("Infinite solutions.")

        // For no solution
        if (coeff == 0 && total != 0)
            println("No solutions.")

        // x = total sum / coeff
        // of x '-' sign indicates
        // moving numeric value to
        // right hand side
        val ans = -total / coeff
        print(ans.toString())
    }
}
