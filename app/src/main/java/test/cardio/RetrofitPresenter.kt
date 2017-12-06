package test.cardio

import test.cardio.models.Home
import java.lang.ref.WeakReference


class RetrofitPresenter() {

    val weakView: WeakReference<WeakView>? = null

    fun getHome() {

    }

    interface WeakView {
        fun showLoading()

        fun showHome(home: Home)
    }
}