package com.shakiv.husain.meditationapp.utils

import com.shakiv.husain.meditationapp.R
import com.shakiv.husain.meditationapp.data.model.BottomMenuContent
import com.shakiv.husain.meditationapp.data.model.Feature
import com.shakiv.husain.meditationapp.ui.theme.Beige1
import com.shakiv.husain.meditationapp.ui.theme.Beige2
import com.shakiv.husain.meditationapp.ui.theme.Beige3
import com.shakiv.husain.meditationapp.ui.theme.BlueViolet1
import com.shakiv.husain.meditationapp.ui.theme.BlueViolet2
import com.shakiv.husain.meditationapp.ui.theme.BlueViolet3
import com.shakiv.husain.meditationapp.ui.theme.LightGreen1
import com.shakiv.husain.meditationapp.ui.theme.LightGreen2
import com.shakiv.husain.meditationapp.ui.theme.LightGreen3
import com.shakiv.husain.meditationapp.ui.theme.OrangeYellow1
import com.shakiv.husain.meditationapp.ui.theme.OrangeYellow2
import com.shakiv.husain.meditationapp.ui.theme.OrangeYellow3

object Data {

    fun getChipList(): List<String> {
        return listOf<String>("Sweet Sleep", "Insomnia", "Depression")
    }

    fun getFeaturedList(): List<Feature> {
        return listOf(
            Feature(
                title = "Sleep meditation",
                iconId = R.drawable.ic_headphone,
                lightColor = BlueViolet1,
                mediumColor = BlueViolet2,
                darkColor = BlueViolet3
            ),
            Feature(
                title = "Tips for sleeping",
                R.drawable.ic_videocam,
                LightGreen1,
                LightGreen2,
                LightGreen3
            ),
            Feature(
                title = "Night island",
                R.drawable.ic_headphone,
                OrangeYellow1,
                OrangeYellow2,
                OrangeYellow3
            ),
            Feature(
                title = "Calming sounds",
                R.drawable.ic_headphone,
                Beige1,
                Beige2,
                Beige3
            )
        )
    }

    fun getBottomMenuList(): List<BottomMenuContent> {

        return listOf(
            BottomMenuContent(title = "Home", R.drawable.ic_home),
            BottomMenuContent(title = "Meditate", R.drawable.ic_bubble),
            BottomMenuContent(title = "Sleep", R.drawable.ic_moon),
            BottomMenuContent(title = "Music", R.drawable.ic_music),
            BottomMenuContent(title = "Profile", R.drawable.ic_profile)
        )
    }
}