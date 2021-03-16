import com.erbe.simplecalculator.UndefinedOperationException
import com.erbe.simplecalculator.operators.*

class CalculatorEngine {

    var result = "0"
    var operator: Char? = '+'
    var isInInvalidState = false

    fun calculate(value: String): String {
        val operation = when (operator) {
            '+' -> AddOperator
            '-' -> SubtractOperator
            '*' -> MultiplyOperator
            '/' -> DivideOperator
            '^' -> PowerOperator
            else -> throw UndefinedOperationException()
        }

        val rightHandSideValue = if (value.isEmpty()) {
            operation.defaultValue()
        } else {
            value.toBigDecimal()
        }

        result = try {
            operation.apply(result.toBigDecimal(), rightHandSideValue).toString()
        } catch (e: Exception) {
            isInInvalidState = true
            "Err"
        }
        return result
    }

    fun clear() {
        operator = '+'
        result = "0"
        isInInvalidState = false
    }
}