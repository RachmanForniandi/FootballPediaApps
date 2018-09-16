package com.rachmanforniandi.recyclerviewkotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.rachmanforniandi.footballpediaapps.FootballClubUI
import com.rachmanforniandi.footballpediaapps.R
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.AnkoContext

class FootBallTeamAdapter(val items:List<TeamPerItem>, val listener: (TeamPerItem) -> Unit):
        RecyclerView.Adapter<FootBallTeamAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(FootballClubUI().createView(AnkoContext.Companion.create(parent.context,parent)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position],listener)
    }

    override fun getItemCount()= items.size


    inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView),
            LayoutContainer{

        val image = itemView.findViewById<ImageView>(FootballClubUI.fc_symbol)
        val name = itemView.findViewById<TextView>(FootballClubUI.fc_name)

        fun bindItem(teamPerItem: TeamPerItem, listener: (TeamPerItem) -> Unit) {

            Glide.with(itemView.context)
                    .load(teamPerItem.logoClub)
                    .into(image)
            name.text = teamPerItem.name;

            containerView.setOnClickListener {
                listener(teamPerItem)
            }
        }
    }

}

