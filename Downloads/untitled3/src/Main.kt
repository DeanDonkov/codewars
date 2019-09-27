import equations.linear.LinearEquation

fun main(){
    LinearEquation().generateLinearEquation()
    val eq = LinearEquation()
            .firstCoeffRange(1, 10).firstCoeffSign(LinearEquation.SignENUM.MINUS)
            .secondCoeffRange(2, 5).secondCoeffSign(LinearEquation.SignENUM.PLUS)
            .thirdCoeffRange(10,20).thirdCoeffSign(LinearEquation.SignENUM.PLUS)
            .build().solveEquation()


}

