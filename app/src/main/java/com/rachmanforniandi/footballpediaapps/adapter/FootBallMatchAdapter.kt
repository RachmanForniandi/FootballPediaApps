package com.rachmanforniandi.footballpediaapps.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rachmanforniandi.footballpediaapps.R
import com.rachmanforniandi.footballpediaapps.models.EventsItem
import com.rachmanforniandi.footballpediaapps.utils.FormatDateTime
import org.jetbrains.anko.*

class FootBallMatchAdapter(val items:List<EventsItem>, val clickListener: (EventsItem)->Unit):
        RecyclerView.Adapter<FootBallMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(FootballClubUI().createView(AnkoContext.create(parent.context,parent)))

    override fun getItemCount()= items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bindItem(items[position],clickListener)
    }

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        val matchDate: TextView = view.findViewById(ID_DATE)
        val nameHomeTeam: TextView = view.findViewById(ID_HOME_TEAM)
        val panelHomeScore: TextView = view.findViewById(ID_HOME_SCORE)
        val nameAwayTeam: TextView = view.findViewById(ID_AWAY_TEAM)
        val panelAwayScore: TextView = view.findViewById(ID_AWAY_SCORE)

        fun bindItem(item: EventsItem,clickListener: (EventsItem) -> Unit) {
            matchDate.text = FormatDateTime.getLongDate(item.dateEvent!!)
            nameHomeTeam.text = item.strHomeTeam
            panelHomeScore.text = item.intHomeScore
            nameAwayTeam.text = item.strAwayTeam
            panelAwayScore.text = item.intAwayScore

            itemView.setOnClickListener { clickListener(item) }
        }
    }

        companion object {
            val ID_DATE = 1
            val ID_HOME_TEAM = 2
            val ID_HOME_SCORE = 3
            val ID_AWAY_TEAM = 4
            val ID_AWAY_SCORE =5
        }


        inner class FootballClubUI  : AnkoComponent<ViewGroup> {
            override fun createView(ui: AnkoContext<ViewGroup>)= with(ui){
                verticalLayout {
                    lparams(matchParent, wrapContent)

                    verticalLayout {
                        backgroundColor = Color.WHITE
                        padding = dip(8)

                        textView {
                            id = ID_DATE
                            textColor = ContextCompat.getColor(ctx,R.color.colorPrimary)
                            gravity = Gravity.CENTER
                        }.lparams(matchParent, wrapContent)

                        linearLayout {
                            gravity = Gravity.CENTER_VERTICAL

                            textView {
                                id = ID_HOME_TEAM
                                gravity =Gravity.CENTER
                                textSize= 18f
                                text = "home"
                            }.lparams(matchParent, wrapContent,1f)

                            linearLayout {
                                gravity = Gravity.CENTER_VERTICAL

                                textView {
                                    id = ID_HOME_SCORE
                                    padding = dip(8)
                                    textSize = 22f
                                    setTypeface(null,Typeface.BOLD)
                                    text = "0"
                                }

                                textView{
                                    text = "vs"
                                }

                                textView {
                                    id = ID_AWAY_SCORE
                                    padding = dip(8)
                                    textSize = 22f
                                    setTypeface(null,Typeface.BOLD)
                                    text = "0"
                                }
                            }
                            textView {
                                id = ID_AWAY_TEAM
                                gravity = Gravity.CENTER
                                textSize = 18f
                                text = "away"
                            }.lparams(matchParent, wrapContent,1f)
                        }
                    }.lparams(matchParent, matchParent){
                        setMargins(dip(16), dip(8),dip(16),dip(8))
                    }
                }
            }
        }
    }


