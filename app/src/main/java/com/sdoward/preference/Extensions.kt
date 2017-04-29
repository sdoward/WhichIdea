package com.sdoward.preference

fun requireBetween(value: Int, lowerValue: Int, higherValue: Int, lazyMessage: () -> Any) {
    require(lowerValue < higherValue) {"lower value is higher or the same as higher value"}
    require(value in lowerValue..higherValue, lazyMessage)
}