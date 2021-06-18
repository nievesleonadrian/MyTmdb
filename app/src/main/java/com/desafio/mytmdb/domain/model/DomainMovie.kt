package com.desafio.mytmdb.domain.model

data class DomainMovie (
    val id: Int,
    val overview: String,
    val poster_path: String,
    val title: String,
    val vote_average: Double
)