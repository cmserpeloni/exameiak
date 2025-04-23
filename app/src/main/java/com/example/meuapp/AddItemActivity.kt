package com.example.meuapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.meuapp.databinding.ActivityAddItemBinding

class AddItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddItemBinding
    private lateinit var persistencia: Persistencia

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        persistencia = Persistencia(this)
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnSalvar.setOnClickListener {
            val novoItem = binding.etItem.text.toString().trim()
            if (novoItem.isNotEmpty()) {
                val listaAtual = persistencia.carregarLista().toMutableList()
                listaAtual.add(novoItem)
                persistencia.salvarLista(listaAtual)
                finish()
            }
        }
    }
}