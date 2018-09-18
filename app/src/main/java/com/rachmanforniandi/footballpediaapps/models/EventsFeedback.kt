package com.rachmanforniandi.footballpediaapps.models


import com.google.gson.annotations.SerializedName


class EventsFeedback{

    @SerializedName("events")
    var events: List<EventsItem>? = null
}