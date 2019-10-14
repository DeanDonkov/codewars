
import kotlin.random.*;

fun main(){
    //LinearEquation().generateLinearEquation()
   val equation = WholeNumber(5, "x") +
           Fraction(5, 6) `=`
           WholeNumber((100..200).random())
    println(equation)

    // Generate 100 Quadratic equations
    for(x in 1 until 100){
        val equation = WholeNumber((1..10).random(), "x^2")+
            WholeNumber((1..20).random(), "x") +
            WholeNumber((1..30).random()) `=` Fraction((10..60).random(), (1..10).random())
        println(equation)
    }

}
