package josue.ambrosio.streamingtv

import android.content.Intent
import android.os.Bundle
import android.app.Dialog
import android.os.Handler
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class QrMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_qr_main)

        val imgQR = findViewById<ImageView>(R.id.imgQR)
        imgQR.setOnClickListener {
            // Mostrar el dialog personalizado
            val processingDialog = Dialog(this)
            val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_processing, null)
            processingDialog.setContentView(dialogView)
            processingDialog.setCancelable(false) // Evita que se cierre al tocar fuera del dialog
            processingDialog.show()

            // Retardar el cambio de actividad
            Handler().postDelayed({
                processingDialog.dismiss() // Cierra el dialog después de 3 segundos
                val intent = Intent(this, CourseActiveActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000) // Retraso de 3 segundos
        }
    }
}
