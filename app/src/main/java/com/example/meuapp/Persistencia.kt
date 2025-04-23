package com.example.meuapp

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Persistencia(context: Context) {
    private val prefs = context.getSharedPreferences("meu_app_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun salvarLista(items: List<String>) {
        val json = gson.toJson(items)
        prefs.edit {
            putString("lista_items", json)
        }
    }

    fun carregarLista(): List<String> {
        val json = prefs.getString("lista_items", null)
        return json?.let {
            val type = object : TypeToken<List<String>>() {}.type
            gson.fromJson(it, type) ?: emptyList()
        } ?: emptyList()
    }
}