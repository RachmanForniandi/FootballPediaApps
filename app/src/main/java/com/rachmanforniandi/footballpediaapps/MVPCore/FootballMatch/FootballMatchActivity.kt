package com.rachmanforniandi.footballpediaapps.MVPCore.FootballMatch

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.*
import com.rachmanforniandi.footballpediaapps.R
import com.rachmanforniandi.footballpediaapps.adapter.FootBallMatchAdapter
import com.rachmanforniandi.footballpediaapps.models.EventsItem
import com.rachmanforniandi.footballpediaapps.models.LeagueFeedback
import com.rachmanforniandi.footballpediaapps.models.LeaguesPerItem
import com.rachmanforniandi.footballpediaapps.utils.invisible
import com.rachmanforniandi.footballpediaapps.utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView
import org.jetbrains.anko.recyclerview.v7.recyclerView

class FootballMatchActivity:AppCompatActivity(),FootballMatchView {

    lateinit var presenter: FootballMatchPresenter
    lateinit var adapter: FootBallMatchAdapter

    lateinit var spinner: Spinner
    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView

    lateinit var emptyDataStage: LinearLayout
    lateinit var league: LeaguesPerItem

    var events:MutableList<EventsItem> = mutableListOf()

    companion object {
        val ID_BottomNav = 1
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        buildLayout()
        buildScopeArea()
        //loadFakeData()
    }



    override fun loadingView() {
        progressBar.visible()
        recyclerView.invisible()
        emptyDataStage.invisible()
    }

    override fun hideLoadingView() {
        progressBar.invisible()
        recyclerView.visible()
        emptyDataStage.invisible()
    }

    override fun showEmptyData(){
        progressBar.invisible()
        recyclerView.invisible()
        emptyDataStage.visible()
    }

    override fun showLeagueList(data: LeagueFeedback) {
        spinner.adapter = ArrayAdapter(ctx,android.R.layout.simple_spinner_dropdown_item, data.leagues)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                league = spinner.selectedItem as LeaguesPerItem
                //presenter.getPreviousEvents(league.idLeague!!)

                when(presenter.match){
                    1-> presenter.getPreviousEvents(league.idLeague!!)
                    2-> presenter.getNextEvents(league.idLeague!!)
                }
            }
        }
    }

    override fun showPrevListEvent(data: List<EventsItem>) {
        /*events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
        recyclerView.scrollToPosition(0)*/
        showEventListData(data)
    }

    override fun showNextListEvent(data: List<EventsItem>) {
        showEventListData(data)
    }

    fun itemClicked(item: EventsItem){
        toast("item: ${item.strEvent}")
    }

    private fun buildLayout() {
        linearLayout {
            orientation = LinearLayout.VERTICAL

            linearLayout {
                orientation = LinearLayout.VERTICAL
                backgroundColor = Color.CYAN

                spinner = spinner {
                    padding = dip(16)
                    minimumHeight = dip(64)
                }
            }
            relativeLayout {
                emptyDataStage= linearLayout {
                    orientation = LinearLayout.VERTICAL

                    imageView {
                        setImageResource(R.drawable.no_data)
                    }

                    textView {
                        padding= dip(8)
                        text = "No data founded"
                        gravity = Gravity.CENTER
                    }
                }.lparams{
                    centerInParent()
                }
                recyclerView = recyclerView {
                    layoutManager = LinearLayoutManager(ctx)
                }.lparams(matchParent, matchParent){
                    topOf(ID_BottomNav)
                }


                progressBar = progressBar {
                }.lparams{
                    centerInParent()
                }

                bottomNavigationView {
                    id = ID_BottomNav
                    backgroundColor = Color.GRAY

                    menu.apply {
                        add("Previous Match")
                                .setIcon(R.drawable.left_arrow)
                                .setOnMenuItemClickListener {
                                    presenter.getPreviousEvents(league.idLeague!!)
                                    false
                                }

                        add("Next Match")
                                .setIcon(R.drawable.right_arrow)
                                .setOnMenuItemClickListener {
                                    //toast("Coming up next..")
                                    presenter.getNextEvents(league.idLeague!!)
                                    false
                                }
                    }
                }.lparams(matchParent, wrapContent){
                    alignParentBottom()
                }
            }
        }
    }

    private fun buildScopeArea() {
        presenter = FootballMatchPresenter(this)
        adapter = FootBallMatchAdapter(events,{item:EventsItem ->itemClicked(item)})

        presenter.getAllArchiveLeagueInfo()
        recyclerView.adapter = adapter
    }

    fun showEventListData(data: List<EventsItem>){
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
        recyclerView.scrollToPosition(0)
    }

    /*fun loadFakeData() {
        for (i in 1..9) {
            val item = EventsItem()
            item.dateEvent = "2018-09-0${i}"
            item.strHomeTeam = "Tottenham Hotspur"
            item.strAwayTeam = "Liverpool"
            item.intHomeScore = "1"
            item.intAwayScore = "2"
            item.strEvent = "Tottenham Hotspur vs Liverpool"
            events.add(item)
        }
        adapter.notifyDataSetChanged()
    }*/
}