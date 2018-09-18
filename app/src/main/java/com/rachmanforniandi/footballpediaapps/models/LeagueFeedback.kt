package com.rachmanforniandi.footballpediaapps.models

import com.google.gson.annotations.SerializedName


class LeagueFeedback {

    @SerializedName("leagues")
    var leagues: List<LeaguesPerItem>? = null

}