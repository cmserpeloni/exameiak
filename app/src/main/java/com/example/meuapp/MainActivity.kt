package com.example.meuapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meuapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var persistencia: Persistencia

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        persistencia = Persistencia(this)
        setupRecyclerView()
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        (binding.rvLista.adapter as? ListaAdapter)?.let {
            it.items = persistencia.carregarLista()
            it.notifyDataSetChanged()
        }
    }

    private fun setupRecyclerView() {
        binding.rvLista.layoutManager = LinearLayoutManager(this)
        binding.rvLista.adapter = ListaAdapter(persistencia.carregarLista())
    }

    private fun setupListeners() {
        binding.btnAddItem.setOnClickListener {
            startActivity(Intent(this, AddItemActivity::class.java))
        }
    }
}