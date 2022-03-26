package com.example.myapplication

class Person2 : Person() {

    fun hello(): Int {
        var a = -1
        for (index in 0..10_000_000) {
            a = a xor index
        }
        return a
    }

    fun hi(): Int {
        var a = -1
        for (index in 0..1_000_000) {
            a = a xor index
        }
        return a
    }
}