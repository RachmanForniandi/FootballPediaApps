package com.rachmanforniandi.footballpediaapps

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.rachmanforniandi.footballpediaapps.R.array.club_description_detail
import com.rachmanforniandi.footballpediaapps.R.id.football_club_list
import com.rachmanforniandi.recyclerviewkotlin.FootBallTeamAdapter
import com.rachmanforniandi.recyclerviewkotlin.TeamPerItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private var teamPerItems:MutableList<TeamPerItem> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val list = findViewById<RecyclerView>(R.id.football_club_list)
        val progressDialog = indeterminateProgressDialog("Loading...")

        progressDialog.show()

        initData()

        progressDialog.dismiss()

        football_club_list.layoutManager = LinearLayoutManager(this)
        football_club_list.adapter = FootBallTeamAdapter(this, teamPerItems){
            /*val toast = Toast.makeText(applicationContext, it.name, Toast.LENGTH_SHORT)
            toast.show()*/
            itemClicked(it)
        }

    }

    private fun itemClicked(perItem: TeamPerItem) {
        startActivity<DetailTeamActivity>(DetailTeamActivity.TEAM_NAME to  perItem.name,
                DetailTeamActivity.TEAM_SYMBOL to perItem.logoClub,
                DetailTeamActivity.TEAM_DESCRIPTION to perItem.descriptionDetail)

    }

    private fun initData(){
        val nameOfTeam = resources.getStringArray(R.array.club_name)
        val imageTeam = resources.obtainTypedArray(R.array.club_image)
        val detailTeam = resources.getStringArray(club_description_detail)

        teamPerItems.clear()

        for (i in nameOfTeam.indices){
            teamPerItems.add(TeamPerItem(nameOfTeam[i],
                    imageTeam.getResourceId(i,0),detailTeam[i]))
        }

        imageTeam.recycle()
    }
}
