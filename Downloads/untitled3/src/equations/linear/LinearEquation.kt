data class EquationStack(private val coefficients: MutableList<Coefficient>, private val signs: MutableList<String>) {
    operator fun plus(coefficient: Coefficient) = apply {
        coefficients.add(coefficient)
        signs.add(" + ")
    }

    operator fun minus(coefficient: Coefficient) = apply {
        coefficients.add(coefficient)
        signs.add(" - ")
    }

    operator fun div(coefficient: Coefficient) = apply {
        coefficients.add(coefficient)
        signs.add(" / ")
    }

    operator fun times(coefficient: Coefficient) = apply {
        coefficients.add(coefficient)
        signs.add(" * ")
    }

    infix fun `=`(coefficient: Coefficient) =
            coefficients.zip(signs).joinToString("") { (coefficient, sign) ->
                coefficient.build() + sign
            } + coefficients.last().build() + " = " + coefficient.build()
}

interface Coefficient {
    fun build(): String

    operator fun plus(coefficient: Coefficient) =
            EquationStack(mutableListOf(this, coefficient), mutableListOf(" + "))

    operator fun minus(coefficient: Coefficient) =
            EquationStack(mutableListOf(this, coefficient), mutableListOf(" - "))
}

data class Fraction(private val numerator: Int, private val denominator: Int, private val variable: String = ""): Coefficient {
    override fun build() = "$numerator/$denominator"
}

data class WholeNumber(private val number: Int, private val variable: String = ""): Coefficient {
    override fun build() = number.toString() + variable
}