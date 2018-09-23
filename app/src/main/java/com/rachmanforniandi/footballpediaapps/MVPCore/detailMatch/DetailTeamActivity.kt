package com.rachmanforniandi.footballpediaapps.MVPCore.detailMatch

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.MenuItem
import android.widget.*
import com.rachmanforniandi.footballpediaapps.MVPCore.FootballMatch.FootballMatchView
import com.rachmanforniandi.footballpediaapps.R
import com.rachmanforniandi.footballpediaapps.adapter.FootBallMatchAdapter
import com.rachmanforniandi.footballpediaapps.models.EventsItem
import com.rachmanforniandi.footballpediaapps.models.TeamsPerItem
import com.rachmanforniandi.footballpediaapps.utils.FormatDateTime
import com.rachmanforniandi.footballpediaapps.utils.invisible
import com.rachmanforniandi.footballpediaapps.utils.visible
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

const val INTENT_TO_DETAIL = "INTENT_TO_DETAIL"

class DetailTeamActivity :AppCompatActivity() ,DetailView{

    lateinit var presenter: DetailTeamPresenter
    lateinit var progressBar: ProgressBar
    lateinit var dataView: ScrollView

    lateinit var imgHomeTeam: ImageView
    lateinit var imgAwayTeam: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val perItem =intent.getParcelableExtra<EventsItem>(INTENT_TO_DETAIL)

        buildLayout(perItem)
        buildEnv(perItem)

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

    override fun loadingView() {
        progressBar.visible()
        dataView.invisible()
    }

    override fun hideLoadingView() {
        progressBar.invisible()
        dataView.visible()
    }

    override fun showInfoTeamDetails(dataHomeTeam: List<TeamsPerItem>, dataAwayTeam: List<TeamsPerItem>){
        Picasso.get()
                .load(dataHomeTeam[0].strTeamBadge)
                .into(imgHomeTeam)
        Picasso.get()
                .load(dataAwayTeam[0].strTeamBadge)
                .into(imgAwayTeam)
    }

    private fun buildLayout(item: EventsItem) {
        relativeLayout {
                dataView = scrollView {
                    verticalLayout{
                        padding = dip(16)

                        // utk date
                        textView {
                            gravity = Gravity.CENTER
                            textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                            text = FormatDateTime.getLongDate(item.dateEvent!!)
                        }

                        // utk score team home & away
                        linearLayout {
                            padding = dip(16)
                            gravity = Gravity.CENTER

                            textView {
                                textSize = 48f
                                setTypeface(null, Typeface.BOLD)
                                text = item.intHomeScore
                            }

                            textView {
                                padding = dip(16)
                                textSize = 24f
                                text = "vs"
                            }

                            textView {
                                textSize = 48f
                                setTypeface(null, Typeface.BOLD)
                                text = item.intAwayScore
                            }
                        }

                        // utk team home & away
                        linearLayout {
                            verticalLayout {
                                imgHomeTeam = imageView () {
                                }.lparams{
                                    width= dip(100)
                                    height = dip(100)
                                    gravity = Gravity.CENTER
                                }
                                textView {
                                    gravity = Gravity.CENTER
                                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                                    textSize = 24f
                                    setTypeface(null, Typeface.BOLD)
                                    text = item.strHomeTeam
                                }

                                textView {
                                    gravity = Gravity.CENTER
                                    text = item.strHomeFormation
                                }
                            }.lparams(matchParent, wrapContent, 1f)
                            verticalLayout {
                                imgAwayTeam = imageView () {
                                }.lparams{
                                    width= dip(100)
                                    height = dip(100)
                                    gravity = Gravity.CENTER
                                }

                                textView {
                                    gravity = Gravity.CENTER
                                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                                    textSize = 24f
                                    setTypeface(null, Typeface.BOLD)
                                    text = item.strAwayTeam
                                }

                                textView {
                                    gravity = Gravity.CENTER
                                    text = item.strAwayFormation
                                }
                            }.lparams(matchParent, wrapContent, 1f)
                        }

                        view {
                            backgroundColor = Color.LTGRAY
                        }.lparams(matchParent, dip(1)) {
                            topMargin = dip(8)
                        }

                        // goal detail team home & away
                        linearLayout {
                            topPadding = dip(8)
                            textView {
                                text = item.strHomeGoalDetails
                            }.lparams(matchParent, wrapContent, 1f)
                            textView {
                                leftPadding = dip(8)
                                rightPadding = dip(8)
                                textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                                text = "Goals"
                            }
                            textView {
                                gravity = Gravity.RIGHT
                                text = item.strAwayGoalDetails
                            }.lparams(matchParent, wrapContent, 1f)
                        }

                        // jumlah shots team home & away
                        linearLayout {
                            topPadding = dip(16)

                            textView {
                                text = item.intHomeShots
                            }.lparams(matchParent, wrapContent, 1f)

                            textView {
                                leftPadding = dip(8)
                                rightPadding = dip(8)
                                textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                                text = "Shots"
                            }
                            textView {
                                gravity = Gravity.RIGHT
                                text = item.intAwayShots
                            }.lparams(matchParent, wrapContent, 1f)
                        }

                        view {
                            backgroundColor = Color.LTGRAY
                        }.lparams(matchParent, dip(1)) {
                            topMargin = dip(8)
                        }

                        // susunan squad
                        textView {
                            topPadding = dip(8)
                            gravity = Gravity.CENTER
                            textSize = 18f
                            setTypeface(null, Typeface.BOLD)
                            text = "Lineups"
                        }

                        //utk goal keeper team home & away
                        linearLayout {
                            topPadding = dip(16)
                            textView {
                                text = item.strHomeLineupGoalkeeper
                            }.lparams(matchParent, wrapContent, 1f)
                            textView {
                                leftPadding = dip(8)
                                rightPadding = dip(8)
                                textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                                text = "Goal Keeper"
                            }
                            textView {
                                gravity = Gravity.RIGHT
                                text = item.strAwayLineupGoalkeeper
                            }.lparams(matchParent, wrapContent, 1f)
                        }

                        // utk bek team home & away
                        linearLayout {
                            topPadding = dip(16)
                            textView {
                                text = item.strHomeLineupDefense
                            }.lparams(matchParent, wrapContent, 1f)
                            textView {
                                leftPadding = dip(8)
                                rightPadding = dip(8)
                                textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                                text = "Defense"
                            }
                            textView {
                                gravity = Gravity.RIGHT
                                text = item.strAwayLineupDefense
                            }.lparams(matchParent, wrapContent, 1f)
                        }

                        // utk gelandang team home &away
                        linearLayout {
                            topPadding = dip(16)
                            textView {
                                text = item.strHomeLineupMidfield
                            }.lparams(matchParent, wrapContent, 1f)
                            textView {
                                leftPadding = dip(8)
                                rightPadding = dip(8)
                                textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                                text = "Midfield"
                            }
                            textView {
                                gravity = Gravity.RIGHT
                                text = item.strAwayLineupMidfield
                            }.lparams(matchParent, wrapContent, 1f)
                        }

                        // utk penyerang team home & away
                        linearLayout {
                            topPadding = dip(16)

                            textView {
                                text = item.strHomeLineupForward
                            }.lparams(matchParent, wrapContent, 1f)

                            textView {
                                leftPadding = dip(8)
                                rightPadding = dip(8)
                                textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                                text = "Forward"
                            }
                            textView {
                                gravity = Gravity.RIGHT
                                text = item.strAwayLineupForward
                            }.lparams(matchParent, wrapContent, 1f)
                        }

                        // substitutes team home & away
                        linearLayout {
                            topPadding = dip(16)
                            textView {
                                text = item.strHomeLineupSubstitutes
                            }.lparams(matchParent, wrapContent, 1f)
                            textView {
                                leftPadding = dip(8)
                                rightPadding = dip(8)
                                textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                                text = "Substitutes"
                            }
                            textView {
                                gravity = Gravity.RIGHT
                                text = item.strAwayLineupSubstitutes
                            }.lparams(matchParent, wrapContent, 1f)
                        }
                    }
                }
            progressBar = progressBar {
                indeterminateDrawable.setColorFilter(ContextCompat.getColor(ctx,R.color.colorPrimary),
                        PorterDuff.Mode.SRC_IN)
            }.lparams{
                centerInParent()
            }
        }

    }

    fun buildEnv(item: EventsItem) {
        presenter = DetailTeamPresenter(this)
        presenter.getInfoTeamDetails(item.idHomeTeam!!, item.idAwayTeam!!)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Match Detail"
    }
}

