package com.erbe.simplecalculator.operators

import java.math.BigDecimal

object MultiplyOperator : Calculate {
    override fun apply(leftOperand: BigDecimal, rightOperand: BigDecimal): BigDecimal {
        return leftOperand.multiply(rightOperand)
    }

    override fun defaultValue() = 1.toBigDecimal()
}