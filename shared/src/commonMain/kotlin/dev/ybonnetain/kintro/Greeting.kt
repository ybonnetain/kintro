package dev.ybonnetain.kintro

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}