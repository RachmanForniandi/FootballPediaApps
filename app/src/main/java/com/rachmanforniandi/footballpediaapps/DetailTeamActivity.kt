package com.rachmanforniandi.footballpediaapps

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.rachmanforniandi.recyclerviewkotlin.TeamPerItem
import kotlinx.android.synthetic.main.notification_template_lines_media.view.*
import org.jetbrains.anko.*

class DetailTeamActivity :AppCompatActivity(){

    /*companion object {
        const val TEAM_NAME = "name"
        const val TEAM_SYMBOL = "symbol"
        const val TEAM_DESCRIPTION = "description"
    }

    private var team_name:  String=""
    private var team_symbol:  Int = 0
    private var team_description:  String=""

    lateinit var teamName_tv : TextView
    lateinit var teamSymbol_img: ImageView
    lateinit var teamDescriptionDetail_tv :TextView*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val item = intent.getParcelableExtra<TeamPerItem>(MainActivity.PARCELABLE_EACH_ITEM_DATA)
        UIDetailActivityUI(item).setContentView(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home){
            finish()
            true
        }else{
        super.onOptionsItemSelected(item)
    }
}

    inner class UIDetailActivityUI(val item: TeamPerItem):AnkoComponent<DetailTeamActivity> {
        val id_view_team = 1
        val id_symbol_team = 2
        val id_name_team =3

        override fun createView(ui: AnkoContext<DetailTeamActivity>)= with(ui) {
            scrollView {
                relativeLayout {
                    lparams(matchParent, wrapContent)

                    view {
                        id = id_view_team
                        setBackgroundColor(Color.rgb(126, 203, 238))
                    }.lparams(matchParent,dip(100))

                    imageView {
                        id = id_symbol_team
                        Glide.with(this)
                                .load(item.logoClub)
                                .into(this)
                    }.lparams(dip(120),dip(120)){
                        centerHorizontally()
                        topMargin = dip(120)
                    }

                    textView {
                        id = id_name_team
                        text = item.name
                        textSize = 20f
                        setTypeface(null, Typeface.BOLD)
                    }.lparams {
                        below(id_symbol_team)
                        centerHorizontally()
                    }

                    textView {
                        padding = dip(16)
                        text = item.descriptionDetail
                    }.lparams {
                        below(id_name_team)
                    }
                }
            }

        }
    }

}
