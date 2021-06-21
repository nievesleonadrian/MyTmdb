package com.desafio.mytmdb.domain.exception


class UnsupportedNavigationException(link : String): RuntimeException("Cannot navigate with link: $link ")