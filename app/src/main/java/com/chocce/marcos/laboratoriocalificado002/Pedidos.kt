package com.chocce.marcos.laboratoriocalificado002

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.chocce.marcos.laboratoriocalificado002.databinding.ActivityPedidosBinding

class Pedidos : AppCompatActivity() {

    // Definir las mismas constantes usadas en Ejercicio02
    private val NAME_KEY = "NAME_KEY"
    private val PHONE_KEY = "PHONE_KEY"
    private val PRODUCT_KEY = "PRODUCT_KEY"
    private val CITY_KEY = "CITY_KEY"
    private val ADDRESS_KEY = "ADDRESS_KEY"

    private lateinit var binding: ActivityPedidosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar el binding
        binding = ActivityPedidosBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Ajustar el padding para las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Mostrar la información del pedido
        showInformation(intent.extras)

        // Configurar los botones
        observeButtons(intent.extras)
    }

    private fun showInformation(bundle: Bundle?) {
        if (bundle != null) {
            val name = bundle.getString(NAME_KEY)
            val phone = bundle.getString(PHONE_KEY)
            val product = bundle.getString(PRODUCT_KEY)
            val city = bundle.getString(CITY_KEY)
            val address = bundle.getString(ADDRESS_KEY)

            // Mostrar los valores en los TextViews
            binding.tvName.text = "Nombre cliente: $name"
            binding.tvNumero.text = "Número Cliente: $phone"
            binding.tvProducto.text = "Producto: $product"
            binding.tvCiudad.text = city
            binding.tvDireccion.text = address
        }
    }

    private fun observeButtons(bundle: Bundle?) {
        // Botón de WhatsApp
        binding.imbWsp.setOnClickListener {
            goWsp(bundle)
        }

        // Botón de SMS
        binding.imbSms.setOnClickListener {
            goSms(bundle)
        }

        // Botón de Mapas
        binding.imbMap.setOnClickListener {
            goMap(bundle)
        }
    }

    private fun goWsp(bundle: Bundle?) {
        val phone = "+51${bundle?.getString(PHONE_KEY)}"
        val message = "Hola, su pedido de ${bundle?.getString(PRODUCT_KEY)} está listo para ser entregado."

        val intentWsp = Intent()
        intentWsp.action = Intent.ACTION_VIEW
        intentWsp.data = Uri.parse("https://wa.me/$phone?text=$message")

        startActivity(intentWsp)
    }

    private fun goSms(bundle: Bundle?) {
        val phone = bundle?.getString(PHONE_KEY)
        val message = "Hola, su pedido de ${bundle?.getString(PRODUCT_KEY)} está listo para ser entregado."

        val intentSms = Intent()
        intentSms.action = Intent.ACTION_SENDTO
        intentSms.data = Uri.parse("smsto:$phone")
        intentSms.putExtra("sms_body", message)

        startActivity(intentSms)
    }

    private fun goMap(bundle: Bundle?) {
        val city = bundle?.getString(CITY_KEY)
        val address = bundle?.getString(ADDRESS_KEY)
        val query = "$address, $city"

        val intentMap = Intent()
        intentMap.action = Intent.ACTION_VIEW
        intentMap.data = Uri.parse("geo:0,0?q=$query")

        startActivity(intentMap)
    }
}
