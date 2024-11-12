package josue.ambrosio.streamingtv

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView

class HomeMainActivity : AppCompatActivity() {

    private lateinit var recyclerViewTop10: RecyclerView
    private lateinit var recyclerViewProgramacion: RecyclerView
    private lateinit var recyclerViewFramework: RecyclerView
    private lateinit var recyclerViewCursosAccesibles: RecyclerView
    private lateinit var recyclerViewCursosUltimamente: RecyclerView
    private lateinit var adapterTop10: CourseAdapter
    private lateinit var adapterTopProgramacion: CourseAdapter
    private lateinit var adapterTopFramework: CourseAdapter
    private lateinit var adapterCursosAccesibles: CourseAdapter
    private lateinit var adapterCursosUltimamente: CourseAdapter
    private var currentFocusSection: Int = SECTION_CURSOS_ACCESIBLES
    private var currentPosition: Int = 0 // Posición actual en el RecyclerView seleccionado

    // Secciones para el control del foco
    companion object {
        const val SECTION_CURSOS_ACCESIBLES = 1
        const val SECTION_CURSOS_ULTIMAMENTE = 2
        const val SECTION_TOP10 = 3
        const val SECTION_PROGRAMACION = 4
        const val SECTION_FRAMEWORK = 5
    }

    // Referencias a los botones (TextViews)
    private lateinit var btnVerDetalle: MaterialTextView
    private lateinit var btnPagar: MaterialTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_main)

        // Inicializar RecyclerViews y sus adaptadores
        initializeRecyclerViews()

        // Configurar botones y animaciones de enfoque
        initializeButtons()

        // Establecer el foco inicial en la primera sección
        setRecyclerViewFocus(SECTION_CURSOS_ACCESIBLES)

        btnVerDetalle.setOnClickListener{
            goVerDetalle()
        }

        btnPagar.setOnClickListener {
            goPagar()
        }
    }

    private fun initializeRecyclerViews() {

        // Configuración de RecyclerView para Cursos Más Accesibles (Penúltimos, 5 cursos)
        recyclerViewCursosAccesibles = findViewById(R.id.recyclerViewCursosAccesibles)
        recyclerViewCursosAccesibles.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterCursosAccesibles = CourseAdapter(
            arrayOf(R.drawable.mean, R.drawable.mean, R.drawable.mean, R.drawable.mean, R.drawable.mean), // Misma imagen
            arrayOf("Full Stack", "Selenium", "Django", "Ruby on Rails", "Android Development")
        ) { position ->
            val courseName = arrayOf("Full Stack", "Selenium", "Django", "Ruby on Rails", "Android Development")[position]
            Toast.makeText(this, "Selected Accessible Course: $courseName", Toast.LENGTH_SHORT).show()
        }
        recyclerViewCursosAccesibles.adapter = adapterCursosAccesibles

        // Configuración de RecyclerView para Cursos Agregados Últimamente (Últimos, 5 cursos)
        recyclerViewCursosUltimamente = findViewById(R.id.recyclerViewCursosUltimamente)
        recyclerViewCursosUltimamente.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterCursosUltimamente = CourseAdapter(
            arrayOf(R.drawable.mean, R.drawable.mean, R.drawable.mean, R.drawable.mean, R.drawable.mean), // Misma imagen
            arrayOf("Desarrollo Web", "Análisis Datos", "Java", "PHP", "C#")
        ) { position ->
            val courseName = arrayOf("Desarrollo Web", "Análisis Datos", "Java", "PHP", "C#")[position]
            Toast.makeText(this, "Selected Recently Added Course: $courseName", Toast.LENGTH_SHORT).show()
        }
        recyclerViewCursosUltimamente.adapter = adapterCursosUltimamente

        // Configuración de RecyclerView para Top 10 Cursos (5 cursos)
        recyclerViewTop10 = findViewById(R.id.recyclerViewTop10)
        recyclerViewTop10.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterTop10 = CourseAdapter(
            arrayOf(R.drawable.mean, R.drawable.mean, R.drawable.mean, R.drawable.mean, R.drawable.mean), // Misma imagen
            arrayOf("C++", "Python", "JavaScript", "Go", "Kotlin")
        ) { position ->
            val courseName = arrayOf("C++", "Python", "JavaScript", "Go", "Kotlin")[position]
            Toast.makeText(this, "Selected Top 10 Course: $courseName", Toast.LENGTH_SHORT).show()
        }
        recyclerViewTop10.adapter = adapterTop10

        // Configuración de RecyclerView para Programación (5 cursos)
        recyclerViewProgramacion = findViewById(R.id.recyclerViewProgramacion)
        recyclerViewProgramacion.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterTopProgramacion = CourseAdapter(
            arrayOf(R.drawable.mean, R.drawable.mean, R.drawable.mean, R.drawable.mean, R.drawable.mean), // Misma imagen
            arrayOf("Programación Básica", "Algoritmos", "Estructuras de Datos", "Bases de Datos", "Patrones de Diseño")
        ) { position ->
            val courseName = arrayOf("Programación Básica", "Algoritmos", "Estructuras de Datos", "Bases de Datos", "Patrones de Diseño")[position]
            Toast.makeText(this, "Selected Programming Course: $courseName", Toast.LENGTH_SHORT).show()
        }
        recyclerViewProgramacion.adapter = adapterTopProgramacion

        // Configuración de RecyclerView para Frameworks (5 cursos)
        recyclerViewFramework = findViewById(R.id.recyclerViewFramework)
        recyclerViewFramework.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterTopFramework = CourseAdapter(
            arrayOf(R.drawable.mean, R.drawable.mean, R.drawable.mean, R.drawable.mean, R.drawable.mean), // Misma imagen
            arrayOf("React", "Angular", "Vue.js", "Laravel", "Spring Boot")
        ) { position ->
            val courseName = arrayOf("React", "Angular", "Vue.js", "Laravel", "Spring Boot")[position]
            Toast.makeText(this, "Selected Framework Course: $courseName", Toast.LENGTH_SHORT).show()
        }
        recyclerViewFramework.adapter = adapterTopFramework
    }

    private fun initializeButtons() {
        btnVerDetalle = findViewById(R.id.btnVerDetalle)
        btnPagar = findViewById(R.id.btnPagar)

        btnVerDetalle.setOnFocusChangeListener { _, hasFocus ->
            handleButtonFocusChange(btnVerDetalle, hasFocus)
        }
        btnPagar.setOnFocusChangeListener { _, hasFocus ->
            handleButtonFocusChange(btnPagar, hasFocus)
        }

        btnVerDetalle.setOnClickListener { Toast.makeText(this, "Ver detalle", Toast.LENGTH_SHORT).show() }
        btnPagar.setOnClickListener { Toast.makeText(this, "Pagar", Toast.LENGTH_SHORT).show() }
    }

    private fun handleButtonFocusChange(button: MaterialTextView, hasFocus: Boolean) {
        if (hasFocus) {
            // Cuando el botón tiene foco, resaltar el botón sin cambiar el fondo
            button.animate().scaleX(1.1f).scaleY(1.1f).start()  // Aumentar el tamaño
            button.setBackgroundResource(R.drawable.button_gray)  // Mantener el fondo original
        } else {
            // Cuando el botón pierde el foco, devolver a su tamaño normal y fondo original
            button.animate().scaleX(1f).scaleY(1f).start()
            button.setBackgroundResource(R.drawable.button_gray)  // Mantener el fondo original
        }
    }

    private fun setRecyclerViewFocus(section: Int) {
        currentFocusSection = section
        currentPosition = 0
        when (section) {
            SECTION_CURSOS_ACCESIBLES -> recyclerViewCursosAccesibles.smoothScrollToPosition(currentPosition)
            SECTION_CURSOS_ULTIMAMENTE -> recyclerViewCursosUltimamente.smoothScrollToPosition(currentPosition)
            SECTION_TOP10 -> recyclerViewTop10.smoothScrollToPosition(currentPosition)
            SECTION_PROGRAMACION -> recyclerViewProgramacion.smoothScrollToPosition(currentPosition)
            SECTION_FRAMEWORK -> recyclerViewFramework.smoothScrollToPosition(currentPosition)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                when (currentFocusSection) {
                    SECTION_CURSOS_ACCESIBLES -> setRecyclerViewFocus(SECTION_CURSOS_ULTIMAMENTE)
                    SECTION_CURSOS_ULTIMAMENTE -> setRecyclerViewFocus(SECTION_TOP10)
                    SECTION_TOP10 -> setRecyclerViewFocus(SECTION_PROGRAMACION)
                    SECTION_PROGRAMACION -> setRecyclerViewFocus(SECTION_FRAMEWORK)
                }
            }
            KeyEvent.KEYCODE_DPAD_UP -> {
                when (currentFocusSection) {
                    SECTION_FRAMEWORK -> setRecyclerViewFocus(SECTION_PROGRAMACION)
                    SECTION_PROGRAMACION -> setRecyclerViewFocus(SECTION_TOP10)
                    SECTION_TOP10 -> setRecyclerViewFocus(SECTION_CURSOS_ULTIMAMENTE)
                    SECTION_CURSOS_ULTIMAMENTE -> setRecyclerViewFocus(SECTION_CURSOS_ACCESIBLES)
                }
            }
            KeyEvent.KEYCODE_DPAD_RIGHT -> moveFocusWithinRecyclerView(1)
            KeyEvent.KEYCODE_DPAD_LEFT -> moveFocusWithinRecyclerView(-1)
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun moveFocusWithinRecyclerView(direction: Int) {
        currentPosition += direction
        val adapter = when (currentFocusSection) {
            SECTION_CURSOS_ACCESIBLES -> adapterCursosAccesibles
            SECTION_CURSOS_ULTIMAMENTE -> adapterCursosUltimamente
            SECTION_TOP10 -> adapterTop10
            SECTION_PROGRAMACION -> adapterTopProgramacion
            SECTION_FRAMEWORK -> adapterTopFramework
            else -> return
        }

        // Aseguramos que currentPosition esté dentro de los límites
        if (currentPosition in 0 until adapter.itemCount) {
            when (currentFocusSection) {
                SECTION_CURSOS_ACCESIBLES -> recyclerViewCursosAccesibles.smoothScrollToPosition(currentPosition)
                SECTION_CURSOS_ULTIMAMENTE -> recyclerViewCursosUltimamente.smoothScrollToPosition(currentPosition)
                SECTION_TOP10 -> recyclerViewTop10.smoothScrollToPosition(currentPosition)
                SECTION_PROGRAMACION -> recyclerViewProgramacion.smoothScrollToPosition(currentPosition)
                SECTION_FRAMEWORK -> recyclerViewFramework.smoothScrollToPosition(currentPosition)
            }
        } else {
            currentPosition = if (direction > 0) adapter.itemCount - 1 else 0 // Límite de posición
        }
    }

    private fun goVerDetalle(){
        val intent = Intent(this, DetailCourseBlockMainActivity::class.java)
        startActivity(intent)
    }

    private fun goPagar(){
        val intent = Intent(this, QrMainActivity::class.java)
        startActivity(intent)
    }
}
