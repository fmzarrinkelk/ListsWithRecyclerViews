package com.example.listswithrecyclerviews

import java.util.UUID

data class Person(
    val id: UUID = UUID.randomUUID()
)
{
    var name: String = ""
    var age: Int = 0
    var isSuperCool: Boolean = false
}

