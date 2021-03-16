package com.erbe.simplecalculator.operators

import java.math.BigDecimal

interface Calculate {
    /**
     * @return the result of calculation
     * @throws [RuntimeException] like when divided by zero
     */
    @Throws(RuntimeException::class)
    fun apply(leftOperand: BigDecimal, rightOperand: BigDecimal): BigDecimal

    /**
     * The value to be used if the right hand side of the operator is invalid
     * @return the value that does not change the result of operation
     * for eg: 0 in case of addition, 1 in case of multiplication
     */
    fun defaultValue(): BigDecimal
}