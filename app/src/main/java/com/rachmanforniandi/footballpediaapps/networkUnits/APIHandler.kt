package com.rachmanforniandi.footballpediaapps.networkUnits

import java.net.URL

class APIHandler{

    fun doRequest(url: String):String{
        return URL(url).readText()
    }
}