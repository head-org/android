package com.head.covidapp.feature.commons.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.appbar.MaterialToolbar
import com.head.covidapp.feature.commons.R
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
            this.setNavigationIcon(R.drawable.ic_close_black_24dp)
            this.setNavigationOnClickListener {
                dismiss()
            }
            this.inflateMenu(R.menu.toolbar_menu)
            this.setOnMenuItemClickListener {
                onStateAdapterClicked?.invoke(
                    Pair(
                        titleMessage.editText?.text.toString(),
                        contentMessage.editText?.text.toString()
                    )
                )
                dismiss()
                true
            }
        }
    }

    companion object {
        fun newInstance(onSaveMessageClicked: (Pair<String, String>?) -> Unit): FullScreenDialog {
            return FullScreenDialog(onSaveMessageClicked)
        }

        val TAG = FullScreenDialog::class.qualifiedName
    }
}
