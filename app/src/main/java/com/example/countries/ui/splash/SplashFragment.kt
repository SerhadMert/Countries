package com.example.countries.ui.splash

import android.animation.Animator
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.countries.R
import com.example.countries.base.BaseFragment
import com.example.countries.databinding.FragmentSplashBinding


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lottieAnimationListener()
    }

    private fun lottieAnimationListener() {
        binding.lottieAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationRepeat(p0: Animator?) {
            }
        })
    }

    override fun isNavigationBarVisible() = false
}