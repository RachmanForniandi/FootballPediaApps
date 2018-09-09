package com.rachmanforniandi.recyclerviewkotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.rachmanforniandi.footballpediaapps.R
import kotlinx.android.synthetic.main.item_list.view.*
import kotlinx.android.extensions.LayoutContainer

class FootBallTeamAdapter(private val context: Context, private val teamPerItems: List<TeamPerItem>,
                          private val listener:(TeamPerItem)->Unit):
        RecyclerView.Adapter<FootBallTeamAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent,
                    false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(teamPerItems[position], listener)
    }

    override fun getItemCount(): Int = teamPerItems.size


    class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView),
            LayoutContainer{

        fun bindItem(teamPerItem: TeamPerItem, listener: (TeamPerItem) -> Unit) {
            itemView.football_club_name.text = teamPerItem.name;
            Glide.with(containerView).load(teamPerItem.logoClub).into(itemView.img_football_club)

            containerView.setOnClickListener {
                listener(teamPerItem)
            }
        }
    }

}

