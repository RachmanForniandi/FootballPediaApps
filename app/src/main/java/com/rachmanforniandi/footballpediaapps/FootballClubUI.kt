package com.rachmanforniandi.footballpediaapps

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*

class FootballClubUI  : AnkoComponent<ViewGroup>{

    companion object {
        val fc_symbol = 1
        val fc_name = 2
    }

    override fun createView(ui: AnkoContext<ViewGroup>)= with(ui){
        verticalLayout {
            orientation = LinearLayout.HORIZONTAL
            padding = dip(16)

            imageView {
                id = fc_symbol
            }.lparams(width = 60.dp, height = 60.dp)

            textView {
                id = fc_name
            }.lparams(wrapContent, wrapContent){
                gravity = Gravity.CENTER_VERTICAL
                margin = dip(16)
            }
        }
    }
}