package br.com.asoncs.service.remote.node_mcu

import io.reactivex.Completable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface NodeMcuApi {

    @POST("light/toggle")
    fun lightToggle(): Completable

    @POST("tv/c/toggle")
    fun tvcToggle(): Completable

    @POST("tv/channel/down")
    fun tvChannelDown(): Completable

    @POST("tv/channel/up")
    fun tvChannelUp(): Completable

    @POST("tv/volume/down")
    fun tvVolumeDown(): Completable

    @POST("tv/volume/up")
    fun tvVolumeUp(): Completable

    @POST("tv/c/tools")
    fun tvcTools(): Completable

    @POST("tv/c/apps")
    fun tvcApps(): Completable

    @POST("tv/c/enter")
    fun tvcEnter(): Completable

    @POST("tv/c/menu")
    fun tvcMenu(): Completable

    @POST("tv/c/return")
    fun tvcReturn(): Completable

    @POST("tv/c/up")
    fun tvcUp(): Completable

    @POST("tv/c/left")
    fun tvcLeft(): Completable

    @POST("tv/c/right")
    fun tvcRight(): Completable

    @POST("tv/c/down")
    fun tvcDown(): Completable
    
    companion object {
        fun newInstance(endPoint: String) : NodeMcuApi {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .readTimeout(2, TimeUnit.SECONDS)
                .writeTimeout(2, TimeUnit.SECONDS)
                .build()
            val retrofit = Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(endPoint)
                .build()
            return retrofit.create(NodeMcuApi::class.java)
        }
    }
}