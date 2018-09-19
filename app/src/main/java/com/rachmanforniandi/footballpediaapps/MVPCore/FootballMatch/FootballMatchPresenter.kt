package com.rachmanforniandi.footballpediaapps.MVPCore.FootballMatch

import com.google.gson.Gson
import com.rachmanforniandi.footballpediaapps.models.EventsFeedback
import com.rachmanforniandi.footballpediaapps.models.LeagueFeedback
import com.rachmanforniandi.footballpediaapps.networkUnits.APIHandler
import com.rachmanforniandi.footballpediaapps.networkUnits.SourceInfoLeague
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class FootballMatchPresenter(val view: FootballMatchView) {

    val apiHandler = APIHandler()
    val gson = Gson()

    var match = 1

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

    fun getPreviousEvents(id: String){
        match =1
        view.loadingView()

        doAsync {
            val leagueData = gson.fromJson(apiHandler
                    .doRequest(SourceInfoLeague.getPrevEventsLeague(id)),
                    EventsFeedback::class.java)

            uiThread {
                view.hideLoadingView()
                //view.showPrevListEvent(leagueData.events!!)
                try {
                    view.showPrevListEvent(leagueData.events!!)
                }catch (e: NullPointerException){
                    view.showEmptyData()
                }
            }
        }
    }

    fun getNextEvents(id: String){
        match =2
        view.loadingView()

        doAsync {
            val leagueData = gson.fromJson(apiHandler
                    .doRequest(SourceInfoLeague.getNextEventsLeague(id)),
                    EventsFeedback::class.java)

            uiThread {
                view.hideLoadingView()
                //view.showPrevListEvent(leagueData.events!!)
                try {
                    view.showNextListEvent(leagueData.events!!)
                }catch (e: NullPointerException){
                    view.showEmptyData()
                }
            }
        }
    }
}