package com.rachmanforniandi.footballpediaapps.networkUnits

import com.rachmanforniandi.footballpediaapps.BuildConfig

object SourceInfoLeague {

    fun getAllArchiveLeagueInfo():String{
        return "${BuildConfig.BASE_URL}${BuildConfig.TSDB_API_KEY}" +
                "/all_leagues.php"
    }

    fun getPrevEventsLeague(id: String): String{
        return "${BuildConfig.BASE_URL}${BuildConfig.TSDB_API_KEY}" +
                "/eventspastleague.php?id=${id}"
    }

    fun getNextEventsLeague(id: String): String{
        return "${BuildConfig.BASE_URL}${BuildConfig.TSDB_API_KEY}" +
                "/eventsnextleague.php?id=${id}"
    }

    fun getTeamDetailsInfo(id: String): String{
        return "${BuildConfig.BASE_URL}${BuildConfig.TSDB_API_KEY}" +
                "/lookupteam.php?id=${id}"
    }
}