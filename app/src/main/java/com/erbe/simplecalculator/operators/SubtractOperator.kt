package com.erbe.simplecalculator.operators

import java.math.BigDecimal

object SubtractOperator : Calculate {
    override fun apply(leftOperand: BigDecimal, rightOperand: BigDecimal): BigDecimal {
        return leftOperand.subtract(rightOperand)
    }

    override fun defaultValue() = 0.toBigDecimal()
}