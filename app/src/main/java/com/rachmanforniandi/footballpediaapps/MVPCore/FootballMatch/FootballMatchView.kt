package com.rachmanforniandi.footballpediaapps.MVPCore.FootballMatch

import com.rachmanforniandi.footballpediaapps.models.EventsItem
import com.rachmanforniandi.footballpediaapps.models.LeagueFeedback

interface FootballMatchView {
    fun loadingView()
    fun hideLoadingView()
    fun showEmptyData()
    fun showLeagueList(data: LeagueFeedback)
    fun showPrevListEvent(data: List<EventsItem>)
    fun showNextListEvent(data: List<EventsItem>)
}