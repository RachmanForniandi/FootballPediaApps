package com.rachmanforniandi.footballpediaapps.MVPCore.FootballMatch

import com.google.gson.Gson
import com.rachmanforniandi.footballpediaapps.models.LeagueFeedback
import com.rachmanforniandi.footballpediaapps.networkUnits.APIHandler
import com.rachmanforniandi.footballpediaapps.networkUnits.SourceInfoLeague
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class FootballMatchPresenter(val view: FootballMatchView) {

    val apiHandler = APIHandler()
    val gson = Gson()

    fun getAllArchiveLeagueInfo(){
        view.loadingView()

        doAsync {
            val leagueData = gson.fromJson(apiHandler
                    .doRequest(SourceInfoLeague.getAllArchiveLeagueInfo()),
                    LeagueFeedback::class.java)

            print(leagueData)

            uiThread {
                view.hideLoadingView()
                view.showLeagueList(leagueData)
            }
        }
    }
}