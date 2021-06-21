package com.desafio.mytmdb.ui.provider

import com.desafio.mytmdb.common.ui.AttrsFullPageErrorTemplate

interface UiMoviesComponentProvider {
    fun getErrorTemplateAttrs(action: () -> Unit): AttrsFullPageErrorTemplate
}