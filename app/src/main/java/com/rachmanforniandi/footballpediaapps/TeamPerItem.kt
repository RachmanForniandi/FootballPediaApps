package com.rachmanforniandi.recyclerviewkotlin

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamPerItem(val name: String?, val logoClub:Int?, val descriptionDetail: String? ): Parcelable