package josue.ambrosio.streamingtv

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QrMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_qr_main)

        // Configurar el OnClickListener para imgQR
        val imgQR = findViewById<ImageView>(R.id.imgQR)
        imgQR.setOnClickListener {
            // Crear la intención para abrir la actividad QrMainActivity
            val intent = Intent(this, CourseActiveActivity::class.java)
            startActivity(intent)
        }
    }
}