package josue.ambrosio.streamingtv

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DetailCourseBlockMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_course_block_main)

        // Configuración del RecyclerView para los episodios
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Lista de episodios (puedes sustituir esto por tus propios datos)
        val videos = listOf(
            Video("Introducción", "44 min", "Descripción del video 1", R.drawable.clase_introduccion_nodejs),
            Video("Manejo de Asincronía", "35 min", "Descripción del video 2", R.drawable.clase_instalacion_nodejs),
            Video("Desarrollo de APIs", "50 min", "Descripción del video 3", R.drawable.clase_primera_pagina_nodejs)
        )

        // Configura el adaptador
        val adapter = VideoAdapter(videos)
        recyclerView.adapter = adapter

        // Variable para verificar si el curso está comprado
        val isCoursePurchased = false // Esto debes sustituirlo con la lógica real

        // Deshabilitar el RecyclerView si el curso no ha sido comprado
        recyclerView.isFocusable = isCoursePurchased
        recyclerView.isFocusableInTouchMode = isCoursePurchased
        recyclerView.alpha = if (isCoursePurchased) 1.0f else 0.5f
        recyclerView.isEnabled = isCoursePurchased

        // Manejo del desplazamiento con el control remoto
        recyclerView.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                when (keyCode) {
                    KeyEvent.KEYCODE_DPAD_DOWN -> {
                        recyclerView.smoothScrollBy(0, 100)  // Desplaza el contenido hacia abajo
                        return@setOnKeyListener true
                    }
                    KeyEvent.KEYCODE_DPAD_UP -> {
                        recyclerView.smoothScrollBy(0, -100) // Desplaza el contenido hacia arriba
                        return@setOnKeyListener true
                    }
                }
            }
            false
        }

        // Configuración de los botones
        val btnReproducir = findViewById<Button>(R.id.btnReproducir)
        val btnPrecio = findViewById<Button>(R.id.btnPrecio)

        // Resaltar los botones cuando están en foco (con el control remoto)
        btnReproducir.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // Cambiar el fondo y hacer el botón más grande cuando tiene foco
                btnReproducir.background = resources.getDrawable(R.drawable.button_highlighted, null)
                btnReproducir.scaleX = 1.2f
                btnReproducir.scaleY = 1.2f
            } else {
                // Volver a su tamaño original cuando pierde el foco
                btnReproducir.background = resources.getDrawable(R.drawable.button_gray, null)
                btnReproducir.scaleX = 1f
                btnReproducir.scaleY = 1f
            }
        }

        btnPrecio.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // Cambiar el fondo y hacer el botón más grande cuando tiene foco
                btnPrecio.background = resources.getDrawable(R.drawable.button_highlighted, null)
                btnPrecio.scaleX = 1.2f
                btnPrecio.scaleY = 1.2f
            } else {
                // Volver a su tamaño original cuando pierde el foco
                btnPrecio.background = resources.getDrawable(R.drawable.button_gray, null)
                btnPrecio.scaleX = 1f
                btnPrecio.scaleY = 1f
            }
        }

        // Mostrar alerta si el curso no ha sido comprado al intentar reproducir
        btnReproducir.setOnClickListener {
            if (!isCoursePurchased) {
                val dialog = AlertDialog.Builder(this)
                    .setTitle("Curso no comprado")
                    .setMessage("Para acceder al contenido, debes comprar el curso.")
                    .setPositiveButton("Ok") { _, _ -> }
                    .show()
            } else {
                // Lógica para reproducir el contenido si el curso está comprado
            }
        }

        // Navegar a QrMainActivity cuando se presiona el botón de precio
        btnPrecio.setOnClickListener {
            val intent = Intent(this, QrMainActivity::class.java)
            startActivity(intent)
        }
    }
}
