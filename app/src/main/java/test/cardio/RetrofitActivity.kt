package test.cardio

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


fun Context.LaunchRetrofit() {
    startActivity(Intent(this, RetrofitActivity::class.java))
}

class RetrofitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        RetrofitPresenter(this).getHome()
    }
}