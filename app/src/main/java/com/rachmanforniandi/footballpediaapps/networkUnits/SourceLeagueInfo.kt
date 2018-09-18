package com.rachmanforniandi.footballpediaapps.networkUnits

import com.rachmanforniandi.footballpediaapps.BuildConfig

object SourceInfoLeague {

    fun getAllArchiveLeagueInfo():String{
        return "${BuildConfig.BASE_URL}${BuildConfig.TSDB_API_KEY}" + "/all_leagues.php"
    }

    fun getPrevEventsLeague(id: String): String{
        return "${BuildConfig.BASE_URL}${BuildConfig.TSDB_API_KEY}" +
                "/eventspastleague.php?id=${id}"
    }
}