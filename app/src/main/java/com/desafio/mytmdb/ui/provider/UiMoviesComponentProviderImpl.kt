package com.desafio.mytmdb.ui.provider

import android.content.Context
import com.desafio.mytmdb.R
import com.desafio.mytmdb.common.ui.AttrsFullPageErrorTemplate
import javax.inject.Inject

class UiMoviesComponentProviderImpl @Inject constructor(val context: Context) :
    UiMoviesComponentProvider {
    override fun getErrorTemplateAttrs(action: () -> Unit): AttrsFullPageErrorTemplate =
        AttrsFullPageErrorTemplate(
            title = context.getString(R.string.an_error_has_occured),
            description = context.getString(R.string.check_your_internet_connection_and_retry),
            btnName = context.getString(R.string.retry),
            icon = context.getDrawable(R.drawable.ic_clouderror),
            clickBtnListener = action
        )

}