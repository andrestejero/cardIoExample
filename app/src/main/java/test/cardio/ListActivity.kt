package test.cardio

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import test.cardio.models.Item
import java.util.*

private const val EXTRA_LIST = "EXTRA_LIST"

fun Context.LaunchListActivity(items: ArrayList<Item>) {
    val intent = Intent(this, ListActivity::class.java).apply {
        putParcelableArrayListExtra(EXTRA_LIST, items)
    }
    startActivity(intent)
}

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val items = intent.getParcelableArrayListExtra<Item>(EXTRA_LIST)

        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = ListAdapter(items)
    }
}