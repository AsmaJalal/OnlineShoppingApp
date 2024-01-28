package com.example.onlineshoppingapp.ui.splash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.example.onlineshoppingapp.R
import com.example.onlineshoppingapp.data.preference.UserPref
import com.example.onlineshoppingapp.databinding.FragmentOnboardingBinding
import com.example.onlineshoppingapp.databinding.FragmentSplashBinding
import com.example.onlineshoppingapp.ui.onboarding.adapter.OnboardAdapter
import com.example.onlineshoppingapp.ui.onboarding.viewmodel.OnboardViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private lateinit var bnd: FragmentSplashBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bnd = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        return bnd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){

        val userPref = UserPref(requireContext())
        CoroutineScope(Dispatchers.Main).launch {

            delay(5000)

            if(FirebaseAuth.getInstance().currentUser != null && userPref.getEmail().isNotEmpty()){

                findNavController().navigate(R.id.action_splashFragment_to_mainMenuFragment)

            }else{

                if(userPref.isFirstUsage()){
                    findNavController().navigate(R.id.action_splashFragment_to_onboardingFragment)
                }else{
                    findNavController().navigate(R.id.action_splashFragment_to_authFragment)
                }

            }

        }

    }

}