package com.rachmanforniandi.footballpediaapps.MVPCore.detailMatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.rachmanforniandi.footballpediaapps.models.EventsItem
import org.jetbrains.anko.*

const val INTENT_TO_DETAIL = "INTENT_TO_DETAIL"

class DetailTeamActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        buildLayout(intent.getParcelableExtra<EventsItem>(INTENT_TO_DETAIL))
        buildEnv()

    }




    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home){
            finish()
            true
        }else{
        return super.onOptionsItemSelected(item)
    }
}
    fun buildLayout(item: EventsItem) {
        relativeLayout {
            textView{
                text = item.strEvent
            }.lparams{
                centerInParent()
            }
        }
    }
    private fun buildEnv() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Match Detail"
    }

}
