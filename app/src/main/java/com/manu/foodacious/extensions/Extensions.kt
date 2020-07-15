package com.manu.foodacious.extensions

import android.view.View

fun View.show(){
    visibility = View.VISIBLE
}

fun View.hide(){
    visibility = View.GONE
}

fun Int.convertCountToString(): String {
    return "$this Places"
}

fun String.changeString() : String {
    return "$this Places"
}

fun convertToCustomCostString(cost:Int) : String? {
    return "CA$${cost}(approx)"
}