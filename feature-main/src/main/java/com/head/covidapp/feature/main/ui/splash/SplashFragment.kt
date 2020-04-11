package com.head.covidapp.feature.main.ui.splash

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.head.covidapp.feature.main.ui.map.MapFragment.Companion.MESSAGES
import com.head.covidapp.feature.main.ui.model.MessageUiModel
import com.head.covidapp.main.R
import kotlinx.android.synthetic.main.splash_fragment.*
import kotlinx.coroutines.delay
import org.koin.android.ext.android.inject


class SplashFragment : Fragment(R.layout.splash_fragment), SplashContract.View {

    private val presenter: SplashContract.Presenter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
        presenter.getMessages()
    }

    override suspend fun startAnimation() {
        val logoAnimation: Animation = AnimationUtils.loadAnimation(context, R.anim.logo_anim)
        val textAnimation: Animation = AnimationUtils.loadAnimation(context, R.anim.text_app_anim)
        imageLogo.startAnimation(logoAnimation)
        textApp.startAnimation(textAnimation)
        delay(logoAnimation.duration)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

    override fun navigateToMapFragment(messageUiModel: MessageUiModel) {
        view?.let {
            val bundle = Bundle()
            bundle.putSerializable(MESSAGES, messageUiModel)
            Navigation.findNavController(it)
                .navigate(R.id.splashFragment_to_mapFragment, bundle)
        }
    }
}
