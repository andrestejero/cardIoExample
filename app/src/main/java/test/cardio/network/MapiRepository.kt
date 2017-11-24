package test.cardio.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import test.cardio.models.Home


class MapiRepository {

    private val mService: MapiService = MapiServiceGenerator.createService(MapiService::class.java)

    private val mCallHome: Call<Home> = mService.getHome()

    fun getHome(callback: RepositoryCallback<Home>) {
        mCallHome.enqueue(object : Callback<Home> {
            override fun onResponse(call: Call<Home>?, response: Response<Home>?) {
                if (response != null && response.isSuccessful) {
                    callback.onSuccess(response.body())
                } else {
                    callback.onFailure()
                }
            }

            override fun onFailure(call: Call<Home>?, t: Throwable?) {
                callback.onFailure()
            }
        })
    }
}