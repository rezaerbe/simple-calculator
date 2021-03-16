package com.erbe.simplecalculator

import CalculatorEngine
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class CalculatorEngineTest {

    private val engine = CalculatorEngine()

    @Before
    fun setup() {
        engine.clear()
    }

    @Test
    fun testForAddition() {
        engine.operator = '+'
        assertThat(engine.result, equalTo(0.toString()))

        // 0 + 10
        engine.calculate(10.toString())
        assertThat(engine.result, equalTo(10.toString()))

        // 10 + 10
        engine.calculate(10.toString())
        assertThat(engine.result, equalTo(20.toString()))

        // 20 + 10
        engine.calculate(10.toString())
        assertThat(engine.result, equalTo(30.toString()))
    }

    @Test
    fun testForSubtraction() {
        engine.operator = '+'
        // Setup initial value
        assertThat(engine.result, equalTo(0.toString()))
        engine.calculate(100.toString())
        assertThat(engine.result, equalTo(100.toString()))

        engine.operator = '-'

        // 100 - 10
        engine.calculate(10.toString())
        assertThat(engine.result, equalTo(90.toString()))

        // 90 - 50
        engine.calculate(50.toString())
        assertThat(engine.result, equalTo(40.toString()))

        // 40 - 50
        engine.calculate(50.toString())
        assertThat(engine.result, equalTo("-10"))
    }

    @Test
    fun testForMultiplication() {
        engine.operator = '*'
        // set initial value
        engine.result = "1"
        // 1 * 10
        engine.calculate(10.toString())
        assertThat(engine.result, equalTo(10.toString()))

        // 10 * 20
        engine.calculate(20.toString())
        assertThat(engine.result, equalTo(200.toString()))

        // 200 * 5
        engine.calculate(5.toString())
        assertThat(engine.result, equalTo(1000.toString()))
    }

    @Test
    fun testForDivision() {
        engine.operator = '/'
        // set initial value
        engine.result = "1000"

        // 1000 / 10
        engine.calculate(10.toString())
        assertThat(engine.result, equalTo(100.toString()))

        // 100 / 5
        engine.calculate(5.toString())
        assertThat(engine.result, equalTo(20.toString()))

        // 20 / 2
        engine.calculate(2.toString())
        assertThat(engine.result, equalTo(10.toString()))

        // 10 / 3
        engine.calculate(4.toString())
        assertThat(engine.result, equalTo("2.5"))

        // 2.5 / 2
        engine.calculate(2.toString())
        assertThat(engine.result, equalTo("1.25"))
    }

    @Test
    fun testForPower() {
        engine.operator = '^'
        // set initial value
        engine.result = "5"
        // 5 ^ 2
        engine.calculate(2.toString())
        assertThat(engine.result, equalTo(25.toString()))

        // 25 ^ 0.5
        engine.calculate(0.5.toString())
        assertThat(engine.result, equalTo(5.toString()))
    }
}