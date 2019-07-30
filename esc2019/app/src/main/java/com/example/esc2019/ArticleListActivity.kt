package com.example.esc2019

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem

class ArticleListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val articleAdapter = ArticleAdapter(this, Articles.list)
        val rcvArticles = findViewById<RecyclerView>(R.id.rcv_articles)
        val lm = LinearLayoutManager(this)


        with(Articles.list){
            add(Article(1, "제목입니다1", "내용입니다1", "이름1", "2019-07-29 14:37"))
            add(Article(2, "제목입니다2", "내용입니다2", "이름2", "2019-07-29 14:37"))
            add(Article(3, "제목입니다3", "내용입니다3", "이름3", "2019-07-29 14:37"))
        }


        rcvArticles.apply {
            adapter = articleAdapter
            layoutManager = lm
            setHasFixedSize(true)
        }

        setSupportActionBar(toolbar)
        supportActionBar!!.title = "커뮤니티"


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
}
