package com.head.covidapp.feature.main.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.head.covidapp.feature.main.R


class SplashFragment : Fragment(R.layout.splash_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({
            Navigation.findNavController(view).navigate(R.id.splashFragment_to_mapFragment)
        }, 3000)
    }
}
