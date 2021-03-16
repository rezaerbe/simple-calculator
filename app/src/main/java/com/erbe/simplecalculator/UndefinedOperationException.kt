package com.erbe.simplecalculator

import java.lang.RuntimeException

class UndefinedOperationException : RuntimeException("Operation is undefined.")