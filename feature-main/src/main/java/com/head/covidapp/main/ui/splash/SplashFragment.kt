package com.head.covidapp.main.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.head.covidapp.domain.models.message.MessageModel
import com.head.covidapp.main.R
import org.koin.android.ext.android.inject

class SplashFragment : Fragment(R.layout.splash_fragment), SplashContract.View {

    private val presenter: SplashContract.Presenter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
        presenter.getMessages()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

    override fun navigateToMapFragment(messageList: List<MessageModel>?) {
        view?.let {
            Navigation.findNavController(it).navigate(R.id.splashFragment_to_mapFragment)
        }
    }
}
