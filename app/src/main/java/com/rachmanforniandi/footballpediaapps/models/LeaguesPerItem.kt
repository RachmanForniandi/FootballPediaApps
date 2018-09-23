package com.rachmanforniandi.footballpediaapps.models

data class LeaguesPerItem(val idLeague: String?, val strLeague: String?) {

    override fun toString(): String{
        return strLeague.toString()
    }
}
