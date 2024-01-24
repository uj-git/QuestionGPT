package com.umang.questiongpt

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ChatGptInterface {

    @GET("api/chatgpt/{question}")
    fun getResponse(@Path("question") question : String): Call<JsonObject>
}