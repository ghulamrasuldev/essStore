package com.example.essstore.common

import com.example.essstore.data.product

object Common {
    val paths = arrayOf("item 1", "item 2", "item 3")
    val DISPLAY_TIME_LONG: Long= 100
    val DISPLAY_TIME_SHORT: Long = 1000
    val DISPLAY_TIME_TOO_LONG: Long = 3000
    var list: ArrayList<product> = arrayListOf(product(
        1,
        "house",
        "11,11,11",
        10,
        10,
        "hello",
        "sdsds",
        "Gold",
        10,
        20
    ))

    fun addData(){
        list.add(
            product(
            1,
            "house",
            "11,11,11",
            10,
            10,
            "hello",
            "sdsds",
            "Gold",
            10,
            20
        )
        )
    }
}