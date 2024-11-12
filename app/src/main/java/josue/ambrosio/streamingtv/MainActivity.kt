package josue.ambrosio.streamingtv

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var btnLogin: TextView

    private val correctEmail = "grupo.asistente@gmail.com"
    private val correctPassword = "Pollito"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        btnLogin = findViewById(R.id.btnLogin)

        // Listener para el TextView del botón
        btnLogin.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                btnLogin.setTextColor(Color.YELLOW) // Cambia el color cuando tiene foco
            } else {
                btnLogin.setTextColor(Color.WHITE) // Vuelve al color original cuando pierde foco
            }
        }

        // Al hacer clic en el TextView, intentamos hacer login
        btnLogin.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email == correctEmail && password == correctPassword) {
                // Login exitoso
                Toast.makeText(this, "Bienvenido!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeMainActivity::class.java)
                startActivity(intent)
            } else {
                // Credenciales incorrectas
                Toast.makeText(this, "Credenciales incorrectas. Intenta de nuevo.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            emailEditText.clearFocus()
        }
        return super.onKeyDown(keyCode, event)
    }
}
