package com.rachmanforniandi.footballpediaapps.MVPCore.detailMatch

import com.rachmanforniandi.footballpediaapps.models.TeamsPerItem

interface DetailView {

    fun loadingView()
    fun hideLoadingView()
    fun showInfoTeamDetails(dataHomeTeam: List<TeamsPerItem>, dataAwayTeam: List<TeamsPerItem>)

}