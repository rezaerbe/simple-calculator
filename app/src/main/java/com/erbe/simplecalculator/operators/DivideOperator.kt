package com.erbe.simplecalculator.operators

import java.math.BigDecimal
import java.math.RoundingMode

object DivideOperator : Calculate {
    override fun apply(leftOperand: BigDecimal, rightOperand: BigDecimal): BigDecimal {
        return leftOperand.divide(rightOperand, 15, RoundingMode.HALF_UP)
            .stripTrailingZeros() // Remove trailing zeros from numbers
            .toPlainString() // To prevent integrals numbers ending with zeros from being converted into power of 10 form eg: 1000 to 1E+3
            .toBigDecimal()
    }

    override fun defaultValue() = 1.toBigDecimal()
}