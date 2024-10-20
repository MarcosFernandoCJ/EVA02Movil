package com.chocce.marcos.laboratoriocalificado002

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.chocce.marcos.laboratoriocalificado002.databinding.ActivityEjercicio02Binding

class Ejercicio02 : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio02Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar el binding
        binding = ActivityEjercicio02Binding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        // Ajustar padding para las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar el botón Registrar
        binding.btnRegistrar.setOnClickListener {
            goToPedidoActivity()
        }
    }

    private fun goToPedidoActivity() {
        // Obtener los valores ingresados
        val nombreCliente = binding.edtName.text.toString()
        val numeroCliente = binding.edtNumero.text.toString()
        val producto = binding.edtProducto.text.toString()
        val ciudad = binding.edtCiudad.text.toString()
        val direccion = binding.edtDireccion.text.toString()

        // Validar que no haya campos vacíos
        if (nombreCliente.isEmpty() || numeroCliente.isEmpty() || producto.isEmpty() || ciudad.isEmpty() || direccion.isEmpty()) {
            // Mostrar un mensaje de error o notificación si algún campo está vacío
            binding.edtName.error = "Completar todos los campos"
            return
        }

        // Crear el intent para pasar los datos a PedidoActivity
        val intent = Intent(this, Pedidos::class.java)
        intent.putExtra("nombreCliente", nombreCliente)
        intent.putExtra("numeroCliente", numeroCliente)
        intent.putExtra("producto", producto)
        intent.putExtra("ciudad", ciudad)
        intent.putExtra("direccion", direccion)

        // Iniciar la actividad PedidoActivity
        startActivity(intent)
    }
}
