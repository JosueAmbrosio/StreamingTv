package josue.ambrosio.streamingtv

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

class HomeMainActivity : AppCompatActivity() {

    private lateinit var recyclerViewLenguajes: RecyclerView
    private lateinit var recyclerViewFrameworks: RecyclerView
    private lateinit var imageAdapterLenguajes: ImageAdapter
    private lateinit var imageAdapterFrameworks: ImageAdapter
    private var currentFocusSection: Int = SECTION_LENGUAJES

    companion object {
        const val SECTION_LENGUAJES = 1
        const val SECTION_FRAMEWORKS = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_main)

        // Inicializar RecyclerView para Lenguajes de Programación
        recyclerViewLenguajes = findViewById(R.id.recyclerViewLenguajes)
        recyclerViewLenguajes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val imagesLenguajes = intArrayOf(
            R.drawable.logo_js,
            R.drawable.logo_py,
            R.drawable.logo_java
        )

        val courseNamesLenguajes = arrayOf(
            "Node JS",
            "Python",
            "Java"
        )

        imageAdapterLenguajes = ImageAdapter(imagesLenguajes, courseNamesLenguajes)
        recyclerViewLenguajes.adapter = imageAdapterLenguajes

        val snapHelperLenguajes = LinearSnapHelper()
        snapHelperLenguajes.attachToRecyclerView(recyclerViewLenguajes)

        // Inicializar RecyclerView para Frameworks
        recyclerViewFrameworks = findViewById(R.id.recyclerViewFrameworks)
        recyclerViewFrameworks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val imagesFrameworks = intArrayOf(
            R.drawable.logo_spb,
            R.drawable.logo_angular,
            R.drawable.logo_vue
        )

        val courseNamesFrameworks = arrayOf(
            "Spring Boot",
            "Angular",
            "Vue.js"
        )

        imageAdapterFrameworks = ImageAdapter(imagesFrameworks, courseNamesFrameworks)
        recyclerViewFrameworks.adapter = imageAdapterFrameworks

        val snapHelperFrameworks = LinearSnapHelper()
        snapHelperFrameworks.attachToRecyclerView(recyclerViewFrameworks)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            // Movemos a la izquierda
            KeyEvent.KEYCODE_DPAD_LEFT -> {
                if (recyclerViewLenguajes.hasFocus()) {
                    val newPosition = (imageAdapterLenguajes.selectedPosition - 1).coerceAtLeast(0)
                    imageAdapterLenguajes.selectItem(newPosition)
                    recyclerViewLenguajes.scrollToPosition(newPosition)
                    return true
                } else if (recyclerViewFrameworks.hasFocus()) {
                    val newPosition = (imageAdapterFrameworks.selectedPosition - 1).coerceAtLeast(0)
                    imageAdapterFrameworks.selectItem(newPosition)
                    recyclerViewFrameworks.scrollToPosition(newPosition)
                    return true
                }
            }

            // Movemos a la derecha
            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                if (recyclerViewLenguajes.hasFocus()) {
                    val newPosition = (imageAdapterLenguajes.selectedPosition + 1).coerceAtMost(imageAdapterLenguajes.itemCount - 1)
                    imageAdapterLenguajes.selectItem(newPosition)
                    recyclerViewLenguajes.scrollToPosition(newPosition)
                    return true
                } else if (recyclerViewFrameworks.hasFocus()) {
                    val newPosition = (imageAdapterFrameworks.selectedPosition + 1).coerceAtMost(imageAdapterFrameworks.itemCount - 1)
                    imageAdapterFrameworks.selectItem(newPosition)
                    recyclerViewFrameworks.scrollToPosition(newPosition)
                    return true
                }
            }

            // Movemos hacia abajo (cambiamos a la segunda sección)
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                if (currentFocusSection == SECTION_LENGUAJES) {
                    recyclerViewFrameworks.requestFocus() // Mover el foco a la segunda sección
                    currentFocusSection = SECTION_FRAMEWORKS
                    highlightSection()
                    // Seleccionar el primer item en la nueva sección de Frameworks
                    imageAdapterFrameworks.selectItem(0)
                    recyclerViewFrameworks.scrollToPosition(0) // Hacer scroll al primer elemento
                    return true
                }
            }

            // Movemos hacia arriba (cambiamos a la primera sección)
            KeyEvent.KEYCODE_DPAD_UP -> {
                if (currentFocusSection == SECTION_FRAMEWORKS) {
                    recyclerViewLenguajes.requestFocus() // Mover el foco a la primera sección
                    currentFocusSection = SECTION_LENGUAJES
                    highlightSection()
                    // Seleccionar el primer item en la nueva sección de Lenguajes
                    imageAdapterLenguajes.selectItem(0)
                    recyclerViewLenguajes.scrollToPosition(0) // Hacer scroll al primer elemento
                    return true
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun highlightSection() {
        // Resaltar la sección actual en función de si el foco está en Lenguajes o Frameworks
        if (currentFocusSection == SECTION_LENGUAJES) {
            recyclerViewLenguajes.alpha = 1.0f // Sección de Lenguajes más clara
            recyclerViewFrameworks.alpha = 0.5f // Sección de Frameworks menos clara
        } else if (currentFocusSection == SECTION_FRAMEWORKS) {
            recyclerViewLenguajes.alpha = 0.5f // Sección de Lenguajes menos clara
            recyclerViewFrameworks.alpha = 1.0f // Sección de Frameworks más clara
        }
    }
}
