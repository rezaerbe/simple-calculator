package com.erbe.simplecalculator.operators

import java.math.BigDecimal
import kotlin.math.pow

object PowerOperator : Calculate {
    override fun apply(leftOperand: BigDecimal, rightOperand: BigDecimal): BigDecimal {
        return leftOperand.toDouble().pow(rightOperand.toDouble())
            .toBigDecimal()
            .stripTrailingZeros()
    }

    override fun defaultValue() = 1.toBigDecimal()
}