package com.head.miso.feature.commons.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputLayout
import com.head.miso.feature.commons.R
import kotlinx.android.synthetic.main.full_screen_dialog.*

class FullScreenDialog(private var onStateAdapterClicked: ((Pair<String, String>?) -> Unit)?) :
    DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.fullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.full_screen_dialog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val toolbar = toolbarFullScreenDialog as? MaterialToolbar
        toolbar?.apply {
            this.setTitle(R.string.new_message)
            this.setNavigationIcon(R.drawable.ic_close_white_24dp)
            this.setNavigationOnClickListener {
                dismiss()
            }
            this.inflateMenu(R.menu.toolbar_menu)
            this.setOnMenuItemClickListener {
                if (validateField(titleMessage) && validateField(contentMessage)) {
                    onStateAdapterClicked?.invoke(
                        Pair(
                            titleMessage.editText?.text.toString(),
                            contentMessage.editText?.text.toString()
                        )
                    )
                    dismiss()
                    true
                } else {
                    false
                }
            }
        }

        titleMessage.editText?.setOnFocusChangeListener { _, hasFocus ->
            onFocusLost(hasFocus, titleMessage)
        }

        contentMessage.editText?.setOnFocusChangeListener { _, hasFocus ->
            onFocusLost(hasFocus, contentMessage)
        }
    }

    private fun onFocusLost(hasFocus: Boolean, textInputLayout: TextInputLayout) =
        takeIf { !hasFocus && validateField(textInputLayout) }

    private fun validateField(textInputLayout: TextInputLayout): Boolean =
        takeIf { textInputLayout.editText?.text.isNullOrEmpty() }?.let {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = context?.getString(R.string.mandatory_field)
            false
        } ?: let {
            textInputLayout.isErrorEnabled = false
            true
        }

    companion object {
        fun newInstance(onSaveMessageClicked: (Pair<String, String>?) -> Unit): FullScreenDialog {
            return FullScreenDialog(onSaveMessageClicked)
        }

        val TAG = FullScreenDialog::class.qualifiedName
    }
}
