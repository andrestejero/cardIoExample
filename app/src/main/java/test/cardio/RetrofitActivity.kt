package test.cardio

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import test.cardio.models.Home
import test.cardio.network.MapiRepository
import test.cardio.network.RepositoryCallback


fun Context.LaunchRetrofit() {
    startActivity(Intent(this, RetrofitActivity::class.java))
}

class RetrofitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        MapiRepository().getHome(object : RepositoryCallback<Home> {
            override fun onSuccess(value: Home?) {
                if (value != null) {
                    val home: Home = value
                    for (banner in home.banners) {
                        Log.d("RetrofitActivity", "banner: ${banner.url}")
                    }
                }
            }

            override fun onFailure() {
                Log.d("RetrofitActivity", "getHome onFailure")
            }
        })
    }
}