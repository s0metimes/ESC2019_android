package com.example.esc2019

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem

class ArticleListActivity : AppCompatActivity() {

    private val articleAdapter = ArticleAdapter(this, Articles.list)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val rcvArticles = findViewById<RecyclerView>(R.id.rcv_articles)
        val lm = LinearLayoutManager(this)


        rcvArticles.apply {
            adapter = articleAdapter
            layoutManager = lm
            setHasFixedSize(true)
        }

        setSupportActionBar(toolbar)
        supportActionBar!!.title = "커뮤니티"

        NetworkAsyncTask().execute()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        menu!!.findItem(R.id.menu_edit).isVisible = false
        menu!!.findItem(R.id.menu_delete).isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            R.id.menu_write -> {
                val intent = Intent(this, ArticleWriteActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class NetworkAsyncTask: AsyncTask<Void, Void, ArrayList<Article>>() {
        override fun doInBackground(vararg p0: Void?): ArrayList<Article> = ArticleModel.getArticles()

        override fun onPostExecute(result: ArrayList<Article>) {
            super.onPostExecute(result)

            articleAdapter.updateData(result)
        }
    }
}
