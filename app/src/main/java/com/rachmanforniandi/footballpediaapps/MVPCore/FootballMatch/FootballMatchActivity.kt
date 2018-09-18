package com.rachmanforniandi.footballpediaapps.MVPCore.FootballMatch

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.rachmanforniandi.footballpediaapps.R
import com.rachmanforniandi.footballpediaapps.models.LeagueFeedback
import com.rachmanforniandi.footballpediaapps.models.LeaguesPerItem
import com.rachmanforniandi.footballpediaapps.utils.invisible
import com.rachmanforniandi.footballpediaapps.utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView

class FootballMatchActivity:AppCompatActivity(),FootballMatchView {

    lateinit var presenter: FootballMatchPresenter
    lateinit var spinner: Spinner
    lateinit var progressBar: ProgressBar

    var leagues:MutableList<LeaguesPerItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        buildLayout()
        buildScopeArea()
    }

    override fun loadingView() {
        progressBar.visible()
    }

    override fun hideLoadingView() {
        progressBar.invisible()
    }

    override fun showLeagueList(data: LeagueFeedback) {
        spinner.adapter = ArrayAdapter(ctx,android.R.layout.simple_spinner_dropdown_item, data.leagues)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item = spinner.selectedItem as LeaguesPerItem
                toast(item.idLeague.toString())
            }
        }
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
                lparams(matchParent, matchParent)

                progressBar = progressBar {
                }.lparams{
                    centerInParent()
                }

                bottomNavigationView {
                    backgroundColor = Color.GRAY

                    menu.apply {
                        add("Previous Match")
                                .setIcon(R.drawable.left_arrow)
                                .setOnMenuItemClickListener {
                                    toast("Previosly")
                                    false
                                }

                        add("Next Match")
                                .setIcon(R.drawable.right_arrow)
                                .setOnMenuItemClickListener {
                                    toast("Coming up next..")
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

        presenter.getAllArchiveLeagueInfo()
    }






}