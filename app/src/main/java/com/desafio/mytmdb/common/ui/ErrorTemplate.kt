package com.desafio.mytmdb.common.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.desafio.mytmdb.databinding.UiTemplateErrorBinding

data class AttrsFullPageErrorTemplate(
    val title: String = "",
    val description: CharSequence = "",
    val btnName: String = "",
    val icon: Drawable? = null,
    val clickBtnListener: () -> Unit = {}
)

class ErrorTemplate @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: UiTemplateErrorBinding? = null
    var attrsFullPageErrorTemplate: AttrsFullPageErrorTemplate? = null
        private set

    init {
        if (binding == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            binding = UiTemplateErrorBinding.inflate(inflater, this, true)
        }
    }

    fun setAttrs(attrsFullPageErrorTemplate: AttrsFullPageErrorTemplate?) {
        attrsFullPageErrorTemplate?.let {
            this.attrsFullPageErrorTemplate = it
            setTitle(it.title)
            setDescription(it.description)
            setBtnName(it.btnName)
            setIcon(it.icon)
            setBtnClickListener(it.clickBtnListener)
        }
    }

    fun updateAttrs(attrsFullPageErrorTemplate: AttrsFullPageErrorTemplate) {
        this.attrsFullPageErrorTemplate = attrsFullPageErrorTemplate
        updateTitle(attrsFullPageErrorTemplate.title)
        updateDescription(attrsFullPageErrorTemplate.description)
        updateBtnName(attrsFullPageErrorTemplate.btnName)
        updateIcon(attrsFullPageErrorTemplate.icon)
        updateBtnClickListener(attrsFullPageErrorTemplate.clickBtnListener)
    }

    private fun updateTitle(title: String) {
        binding?.apply {
            if (tvTitle.text != title) {
                setTitle(title)
            }
        }
    }

    private fun updateDescription(description: CharSequence) {
        binding?.apply {
            if (tvDescription.text != description) {
                setDescription(description)
            }
        }
    }

    private fun updateBtnName(btnName: String) {
        binding?.apply {
            if (btnAction.text != btnName) {
                setBtnName(btnName)
            }
        }
    }

    private fun updateIcon(icon: Drawable?) {
        icon?.let {
            setIcon(icon)
        }
    }

    private fun updateBtnClickListener(clickBtnListener: () -> Unit) {
        setBtnClickListener(clickBtnListener)
    }

    private fun setTitle(title: String) {
        binding?.tvTitle?.text = title
    }

    private fun setDescription(description: CharSequence) {
        binding?.tvDescription?.text = description
    }

    private fun setBtnName(btnName: String) {
        binding?.btnAction?.text = btnName
    }

    private fun setIcon(icon: Drawable?) {
        icon?.let { binding?.ivIcon?.setImageDrawable(it) }
    }

    private fun setBtnClickListener(clickListener: () -> Unit) {
        binding?.apply {
            btnAction.setOnClickListener {
                clickListener()
            }
        }
    }
}