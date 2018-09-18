package com.rachmanforniandi.footballpediaapps.MVPCore.FootballMatch

import com.rachmanforniandi.footballpediaapps.models.EventsItem
import com.rachmanforniandi.footballpediaapps.models.LeagueFeedback

interface FootballMatchView {
    fun loadingView()
    fun hideLoadingView()
    fun showLeagueList(data: LeagueFeedback)
    fun showPrevListEvent(data: List<EventsItem>)
}