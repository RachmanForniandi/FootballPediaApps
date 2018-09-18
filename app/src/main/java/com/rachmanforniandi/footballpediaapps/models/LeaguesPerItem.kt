package com.rachmanforniandi.footballpediaapps.models

import com.google.gson.annotations.SerializedName

import java.io.Serializable


class LeaguesPerItem {

    @SerializedName("strLeagueAlternate")
    var strLeagueAlternate: String? = null

    @SerializedName("strLeague")
    var strLeague: String? = null

    @SerializedName("strSport")
    var strSport: String? = null

    @SerializedName("idLeague")
    var idLeague: String? = null

    override fun toString(): String {
        return super.toString()
    }
}