package com.desafio.mytmdb.factory

import java.util.*
import java.util.concurrent.ThreadLocalRandom

object RandomFactory {

//    fun String.randomString(): String = UUID.randomUUID().toString()

    fun generateString(): String = UUID.randomUUID().toString()
    fun generateDouble(): Double = Math.random()
    fun generateInt(): Int = ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    fun generateBoolean(): Boolean = Math.random() < 0.5
    fun generateInt(min: Int = 0, max: Int): Int = ThreadLocalRandom.current().nextInt(min, max)
    fun generateLong(): Long = Random().nextLong()
    fun generateLong(min: Long = 0, max: Long = 1): Long = ThreadLocalRandom.current().nextLong(min, max)

}