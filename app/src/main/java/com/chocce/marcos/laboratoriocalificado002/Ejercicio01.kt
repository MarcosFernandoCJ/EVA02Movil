package com.chocce.marcos.laboratoriocalificado002

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Ejercicio01 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ejercicio01)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraintLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Referencias a los botones y la vista verde
        val greenView = findViewById<View>(R.id.greenView) // La vista verde
        val buttonMostrar = findViewById<Button>(R.id.buttonMostrar) // Botón Mostrar
        val buttonOcultar = findViewById<Button>(R.id.buttonOcultar) // Botón Ocultar

        // Lógica para mostrar la vista verde
        buttonMostrar.setOnClickListener {
            greenView.visibility = View.VISIBLE
        }

        // Lógica para ocultar la vista verde
        buttonOcultar.setOnClickListener {
            greenView.visibility = View.GONE
        }
    }
}