package com.rachmanforniandi.footballpediaapps.models

data class LeaguesPerItem(
        var idLeague: String?,
        var strLeague: String?,
        var strSport: String?,
        var strLeagueAlternate: String?) {


    override fun toString(): String{
        return strLeague.toString()
    }
}

