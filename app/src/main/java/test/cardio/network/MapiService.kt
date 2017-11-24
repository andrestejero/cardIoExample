package test.cardio.network

import retrofit2.Call
import retrofit2.http.GET
import test.cardio.models.Home


interface MapiService {

    @GET("api/home")
    fun getHome(): Call<Home>
}