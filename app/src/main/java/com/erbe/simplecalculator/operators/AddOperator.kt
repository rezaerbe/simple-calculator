package com.erbe.simplecalculator.operators

import java.math.BigDecimal

object AddOperator : Calculate {
    override fun apply(leftOperand: BigDecimal, rightOperand: BigDecimal): BigDecimal {
        return leftOperand.add(rightOperand)
    }

    override fun defaultValue() = 0.toBigDecimal()
}