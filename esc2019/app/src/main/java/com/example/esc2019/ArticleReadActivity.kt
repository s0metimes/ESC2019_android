package com.example.esc2019

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

class ArticleReadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_read)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val tvTitle = findViewById<TextView>(R.id.tv_title)
        val tvName = findViewById<TextView>(R.id.tv_name)
        val tvContent = findViewById<TextView>(R.id.tv_content)
        val tvCreatedate = findViewById<TextView>(R.id.tv_createdate)
        val pk = intent.getIntExtra("pk", 0)
        val article = Articles.findByPk(pk)

        setSupportActionBar(toolbar)
        supportActionBar!!.title = "글읽기"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        tvTitle.text = article!!.title
        tvName.text = article!!.name
        tvContent.text = article!!.content
        tvCreatedate.text = article!!.createdate
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        menu!!.findItem(R.id.menu_write).isVisible = false
        // true 면 visible, false 면 invisible
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            R.id.menu_edit -> {
                val intent = Intent(this, ArticleEditActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_delete -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
