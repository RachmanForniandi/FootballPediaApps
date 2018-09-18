package com.rachmanforniandi.footballpediaapps.MVPCore.FootballMatch

import com.rachmanforniandi.footballpediaapps.models.LeagueFeedback

interface FootballMatchView {
    fun loadingView()
    fun hideLoadingView()
    fun showLeagueList(data: LeagueFeedback)
}