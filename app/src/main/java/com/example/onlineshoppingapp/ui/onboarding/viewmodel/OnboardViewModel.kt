package com.example.onlineshoppingapp.ui.onboarding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlineshoppingapp.R
import com.example.onlineshoppingapp.data.model.OnBoardModel

class OnboardViewModel : ViewModel() {

    val boardingLiveData = MutableLiveData<List<OnBoardModel>>()

    init {
        getOnboardData()
    }

    private fun getOnboardData(){

        boardingLiveData.postValue(

            listOf(
                OnBoardModel(
                    R.drawable.ic_onboarding_1,
                    R.string.onboarding_1_title,
                    R.string.onboarding_1_desc
                ),
                OnBoardModel(
                    R.drawable.ic_onboarding_2,
                    R.string.onboarding_2_title,
                    R.string.onboarding_2_desc
                ),
                OnBoardModel(
                    R.drawable.ic_onboarding_3,
                    R.string.onboarding_3_title,
                    R.string.onboarding_3_desc
                ),
                OnBoardModel(
                    R.drawable.ic_onboarding_4,
                    R.string.onboarding_4_title,
                    R.string.onboarding_4_desc
                )
            )

        )

    }

}