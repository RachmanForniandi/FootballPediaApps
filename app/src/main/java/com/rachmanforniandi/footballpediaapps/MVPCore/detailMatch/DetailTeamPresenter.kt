package com.rachmanforniandi.footballpediaapps.MVPCore.detailMatch

import com.google.gson.Gson
import com.rachmanforniandi.footballpediaapps.models.LeagueFeedback
import com.rachmanforniandi.footballpediaapps.models.TeamDetailFeedback
import com.rachmanforniandi.footballpediaapps.networkUnits.APIHandler
import com.rachmanforniandi.footballpediaapps.networkUnits.SourceInfoLeague
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailTeamPresenter(val view: DetailView){

    val apiHandler = APIHandler()
    val gson = Gson()

    fun getInfoTeamDetails(idHomeTeam: String, idAwayTeam: String){
        view.loadingView()

        doAsync {
            val dataHomeTeam = gson.fromJson(apiHandler
                    .doRequest(SourceInfoLeague.getTeamDetailsInfo(idHomeTeam)),
                    TeamDetailFeedback::class.java

            )
            val dataAwayTeam = gson.fromJson(apiHandler
                    .doRequest(SourceInfoLeague.getTeamDetailsInfo(idAwayTeam)),
                    TeamDetailFeedback::class.java)

            uiThread {
                view.hideLoadingView()
                view.showInfoTeamDetails(dataHomeTeam.teams!!, dataAwayTeam.teams!!)
            }
        }
    }
}