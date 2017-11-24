package test.cardio.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapiServiceGenerator {

    companion object {

        val builder = Retrofit.Builder()
                .baseUrl("http://carxon.com.ar/")
                .addConverterFactory(GsonConverterFactory.create())

        var retrofit = builder.build()

        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()

        fun <S> createService(serviceClass: Class<S>): S {
            if (!httpClient.interceptors().contains(logging)) {
                httpClient.addInterceptor(logging)
                builder.client(httpClient.build())
                retrofit = builder.build()
            }
            return retrofit.create(serviceClass)
        }
    }
}