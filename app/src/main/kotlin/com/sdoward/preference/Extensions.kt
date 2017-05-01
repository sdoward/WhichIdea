package com.sdoward.preference

import com.google.firebase.database.DatabaseReference

fun requireBetween(value: Int, lowerValue: Int, higherValue: Int, lazyMessage: () -> Any) {
    require(lowerValue < higherValue) {"lower value is higher or the same as higher value"}
    require(value in lowerValue..higherValue, lazyMessage)
}

fun DatabaseReference.getUsersReference(): DatabaseReference {
    return child("users")
}

fun DatabaseReference.getSessionsReference(): DatabaseReference {
    return child("sessions")
}