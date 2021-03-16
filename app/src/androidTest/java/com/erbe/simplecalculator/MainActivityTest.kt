package com.erbe.simplecalculator

import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class MainActivityTest : TestCase() {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun testMainActivity() {
        before {
            activityTestRule.launchActivity(null)
            testLogger.i("Starting test")
        }.after {
        }.run {
            step("Test setup") {
                MainScreen {
                    button0.hasTag("0")
                    button1.hasTag("1")
                    button2.hasTag("2")
                    button3.hasTag("3")
                    button4.hasTag("4")
                    button5.hasTag("5")
                    button6.hasTag("6")
                    button7.hasTag("7")
                    button8.hasTag("8")
                    button9.hasTag("9")
                    buttonDecimal.hasTag(".")
                    buttonPlus.hasTag("+")
                    buttonMinus.hasTag("-")
                    buttonDivide.hasTag("/")
                    buttonMultiply.hasTag("*")
                    buttonPower.hasTag("^")
                    buttonDelete.hasTag("del")
                    buttonClear.hasTag("C")
                }
            }
            step("Test for input") {
                MainScreen {
                    button0.click()
                    button1.click()
                    button2.click()
                    editTextInput.hasText("012")

                    buttonPlus.click()
                    textOperator.hasText("+")
                    textResult.hasText("12")
                    editTextInput.hasText("")
                }
            }
            step("When clicked clear") {
                MainScreen {
                    buttonClear.click()
                    editTextInput.hasText("")
                    textOperator.hasText("+")
                    textResult.hasText("0")
                }
            }
            step("When clicked delete") {
                MainScreen {
                    buttonClear.click()
                    button1.click()
                    button2.click()
                    button3.click()
                    button4.click()
                    editTextInput.hasText("1234")
                    buttonDelete.click()
                    editTextInput.hasText("123")
                    buttonDelete.click()
                    editTextInput.hasText("12")
                }
            }
            step("Handle decimal") {
                MainScreen {
                    buttonClear.click()
                    button1.click()
                    button2.click()
                    button3.click()
                    button4.click()
                    editTextInput.hasText("1234")
                    buttonDecimal.click()
                    editTextInput.hasText("1234.")
                    button4.click()
                    editTextInput.hasText("1234.4")
                    buttonDecimal.click()
                    editTextInput.hasText("1234.4")
                    buttonDecimal.click()
                    buttonDecimal.click()
                    buttonDecimal.click()
                    editTextInput.hasText("1234.4")
                }
            }
            step("Handle error") {
                MainScreen {
                    buttonClear.click()
                    button1.click()
                    buttonDivide.click()
                    button0.click()
                    buttonEquals.click()
                    textResult.hasText("Err")
                }
            }
        }
    }
}