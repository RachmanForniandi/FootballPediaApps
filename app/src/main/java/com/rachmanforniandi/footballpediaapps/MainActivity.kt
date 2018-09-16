package com.rachmanforniandi.footballpediaapps

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.rachmanforniandi.recyclerviewkotlin.FootBallTeamAdapter
import com.rachmanforniandi.recyclerviewkotlin.TeamPerItem
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    companion object {
        const val PARCELABLE_EACH_ITEM_DATA = "TeamPerItem"
    }

    var teamPerItems:MutableList<TeamPerItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val progressDialog = indeterminateProgressDialog("Loading...")

        progressDialog.show()

        initData()

        progressDialog.dismiss()
        UIMainActivity(teamPerItems).setContentView(this)
    }

    inner class UIMainActivity(val items: List<TeamPerItem>) :AnkoComponent<MainActivity>{
        override fun createView(ui: AnkoContext<MainActivity>)= with(ui){
            verticalLayout {
                lparams(matchParent,wrapContent)

                recyclerView {
                    layoutManager = LinearLayoutManager(context)
                    addItemDecoration(DividerItemDecoration(context,1))
                    adapter = FootBallTeamAdapter(items){
                        startActivity<DetailTeamActivity>(PARCELABLE_EACH_ITEM_DATA to it)
                    }
                }

            }
        }

    }


    private fun initData(){
        val nameOfTeam = resources.getStringArray(R.array.club_name)
        val imageTeam = resources.obtainTypedArray(R.array.club_image)
        val detailTeam = resources.getStringArray(R.array.club_description_detail)

        teamPerItems.clear()

        for (i in nameOfTeam.indices){
            teamPerItems.add(TeamPerItem(nameOfTeam[i],
                    imageTeam.getResourceId(i,0),detailTeam[i]))
        }

        imageTeam.recycle()
    }
}


