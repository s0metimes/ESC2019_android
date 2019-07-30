package com.example.esc2019

object Articles {
    val list = arrayListOf<Article>()

    fun findByPk(pk: Int): Article? {
        for(article in list) {
            if(pk == article.pk)
                return article
        }

        return null
    }
}