package josue.ambrosio.streamingtv
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CourseActiveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_active)

        val videoList = listOf(
            Video("Introducción a Node.js", "44 min", "Conoce los fundamentos de Node.js", R.drawable.clase_instalacion_nodejs),
            Video("API REST con Express", "30 min", "Construye una API REST con Express", R.drawable.clase_introduccion_nodejs),
            Video("Conexión a Base de Datos", "36 min", "Aprende a conectar Node.js con MongoDB", R.drawable.clase_primera_pagina_nodejs)
        )

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CourseActiveAdapter(videoList)

        val btnReproducir = findViewById<TextView>(R.id.btnReproducir)
        btnReproducir.setOnClickListener {
            // Abrir VideoViewActivity con un video predeterminado
            val intent = Intent(this, VideoViewActivity::class.java)
            intent.putExtra("VIDEO_URL", "https://www.example.com/video1.mp4") // Cambia a la URL de tu video
            startActivity(intent)
        }
    }
}
