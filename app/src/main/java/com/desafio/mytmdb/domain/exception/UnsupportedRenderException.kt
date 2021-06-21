package com.desafio.mytmdb.domain.exception


class UnsupportedRenderException(render :String)
    : RuntimeException("Cannot support the render: $render ")