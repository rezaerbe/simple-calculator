package com.erbe.simplecalculator

import com.agoda.kakao.text.KButton
import com.kaspersky.kaspresso.screens.KScreen

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int? = R.layout.activity_main
    override val viewClass: Class<*>? = MainActivity::class.java

    val button0 = KButton { withId(R.id.button0) }
    val button1 = KButton { withId(R.id.button1) }
    val button2 = KButton { withId(R.id.button2) }
    val button3 = KButton { withId(R.id.button3) }
    val button4 = KButton { withId(R.id.button4) }
    val button5 = KButton { withId(R.id.button5) }
    val button6 = KButton { withId(R.id.button6) }
    val button7 = KButton { withId(R.id.button7) }
    val button8 = KButton { withId(R.id.button8) }
    val button9 = KButton { withId(R.id.button9) }
    val buttonDecimal = KButton { withId(R.id.buttonDecimal) }
    val buttonPlus = KButton { withId(R.id.buttonAdd) }
    val buttonMinus = KButton { withId(R.id.buttonSubtract) }
    val buttonDivide = KButton { withId(R.id.buttonDivide) }
    val buttonMultiply = KButton { withId(R.id.buttonMultiply) }
    val buttonPower = KButton { withId(R.id.buttonExp) }
    val buttonDelete = KButton { withId(R.id.buttonDelete) }
    val buttonClear = KButton { withId(R.id.buttonClear) }
    val textResult = KButton { withId(R.id.textResult) }
    val textOperator = KButton { withId(R.id.textOperator) }
    val editTextInput = KButton { withId(R.id.editTextInput) }
    val buttonEquals = KButton { withId(R.id.buttonEqual) }
}