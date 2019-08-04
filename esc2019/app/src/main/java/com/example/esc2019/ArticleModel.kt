package com.example.esc2019

import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject

object ArticleModel {
    /*
        안드로이드는 가상 라우터와 방화벽 뒤에서 네트워크가 동작하기 때문에
        locahost나 127.0.0.1이 에뮬레이터를 실행하는 컴퓨터가 아니라 에뮬레이터 자신이 되어버립니다.
        그래서 로컬 컴퓨터에 접근하기 위해 실제 로컬컴퓨터의 ip를 썼었는데요.
        이 ip는 바뀔수 있기 때문에 여간 불편한게 아니였습니다.
        그런데 접근할 수 있는 변하지 않는 주소가 있었네요
        ##10.0.2.2
        바로 이 주소입니다.
     */
    private const val serverAddress = "http://10.0.2.2"
    private const val serverPort = "8080"
    private const val defaultPath = "api/article"
    private const val defaultUrl = "$serverAddress:$serverPort/$defaultPath"
    private var requestBuilder = Request.Builder()
        .addHeader("Content-Type", "application/json")
        .addHeader("cache-control", "no-cache")
    private val mediaType = "application/json".toMediaTypeOrNull()


    fun getArticle(pk: Int): Article {
        val client = OkHttpClient()
        val request = requestBuilder.url("$serverAddress:$serverPort$defaultPath/$pk").get().build()
        val response = client.newCall(request).execute()

        val jsonObject = JSONObject(response.body.toString())

        return Article(
            jsonObject.getInt("pk"),
            jsonObject.getString("name"),
            jsonObject.getString("title"),
            jsonObject.getString("content"),
            jsonObject.getString("createdate"))
    }

    fun getArticles():ArrayList<Article> {
        val client = OkHttpClient()
        val articles = arrayListOf<Article>()
        val request = requestBuilder.url(defaultUrl).get().build()
        val response = client.newCall(request).execute()
        val jsonArray = JSONArray(response.body?.string())

        Log.d("jsonArray", jsonArray.getString(0))
        for(i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            articles.add(Article(
                jsonObject.getInt("pk"),
                jsonObject.getString("name"),
                jsonObject.getString("title"),
                jsonObject.getString("content"),
                jsonObject.getString("createdate")))
        }

        return articles
    }

    fun createArticle(article: Article): Boolean {
        val client = OkHttpClient()
        val body = articleToJSONObject(article).toString().toRequestBody(mediaType)
        val request = requestBuilder.url(defaultUrl).post(body).build()
        val response = client.newCall(request).execute()

        return response.body.toString().toBoolean()
    }

    fun updateArticle(article: Article): Boolean {
        val client = OkHttpClient()
        val body = articleToJSONObject(article).toString().toRequestBody(mediaType)
        val request = requestBuilder.url("$defaultUrl/${article.pk}").put(body).build()
        val response = client.newCall(request).execute()

        return response.body.toString().toBoolean()
    }

    fun deleteArticle(pk: Int): Boolean {
        val client = OkHttpClient()
        val request = requestBuilder.url("$defaultUrl/$pk").delete(null).build()
        val response = client.newCall(request).execute()

        return response.body.toString().toBoolean()
    }

    private fun articleToJSONObject(article: Article): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("pk", article.pk)
        jsonObject.put("name", article.name)
        jsonObject.put("title", article.title)
        jsonObject.put("content", article.content)
        jsonObject.put("createdate", article.createdate)

        return jsonObject
    }
}
