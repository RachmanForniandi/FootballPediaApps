package com.rachmanforniandi.footballpediaapps

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.*

class DetailTeamActivity :AppCompatActivity(){

    companion object {
        const val TEAM_NAME = "name"
        const val TEAM_SYMBOL = "symbol"
        const val TEAM_DESCRIPTION = "description"
    }

    private var team_name:  String=""
    private var team_symbol:  Int = 0
    private var team_description:  String=""

    lateinit var teamName_tv : TextView
    lateinit var teamSymbol_img: ImageView
    lateinit var teamDescriptionDetail_tv :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            padding = dip(16)

            teamSymbol_img = imageView()
                    .lparams(width = dip(72),
                            height = wrapContent)
                    {
                        gravity = Gravity.CENTER
                    }
            teamName_tv = textView()
                    .lparams(width= wrapContent){
                        gravity = Gravity.CENTER
                        topMargin = dip(8)
                    }
            teamDescriptionDetail_tv = textView()
                    .lparams(width= matchParent, height = wrapContent){
                        margin = dip(16)
                    }
        }

        team_name= intent.getStringExtra(TEAM_NAME)
        team_symbol = intent.getIntExtra(TEAM_SYMBOL,0)
        team_description = intent.getStringExtra(TEAM_DESCRIPTION)

        teamName_tv.text = team_name
        Glide.with(teamSymbol_img).load(team_symbol).into(teamSymbol_img)
        teamDescriptionDetail_tv.text = team_description



    }
}