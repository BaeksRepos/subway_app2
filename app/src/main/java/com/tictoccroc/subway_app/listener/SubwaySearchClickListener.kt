package com.tictoccroc.subway_app.listener

import android.view.View
import androidx.navigation.NavController
import com.tictoccroc.subway_app.R

class SubwaySearchClickListener(private val navSubwayFrag: NavController) : View.OnClickListener{
    override fun onClick(p0: View?) {
        navSubwayFrag.navigate(R.id.action_mainViewFragment_to_searchViewFragment)
    }
}