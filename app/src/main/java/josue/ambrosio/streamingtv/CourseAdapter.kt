package josue.ambrosio.streamingtv

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CourseAdapter(
    private val courseImages: Array<Int>,
    private val courseNames: Array<String>,
    private val onCourseClick: (position: Int) -> Unit
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    var selectedPosition = RecyclerView.NO_POSITION  // Para almacenar el elemento seleccionado

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(courseImages[position], courseNames[position])

        // Verificar si la posición actual es la seleccionada para aplicar efectos de enfoque
        holder.itemView.isSelected = selectedPosition == position
        // Cuando el item está seleccionado, no cambia el fondo ni se aplica ningún borde
        if (selectedPosition == position) {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT)  // Sin fondo ni borde
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT)  // Sin fondo ni borde cuando no está seleccionado
        }
    }

    override fun getItemCount(): Int = courseNames.size

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val courseImageView: ImageView = itemView.findViewById(R.id.image_view)
        private val courseNameTextView: TextView = itemView.findViewById(R.id.course_name)

        init {
            itemView.setOnClickListener {
                // Al hacer clic, seleccionamos el item
                onCourseClick(adapterPosition)
                notifyItemChanged(selectedPosition)  // Desenfocar el elemento anterior
                selectedPosition = adapterPosition
                notifyItemChanged(selectedPosition)  // Enfocar el nuevo elemento
            }

            // Establecer el efecto de enfoque para el control remoto
            itemView.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    // Aumentar tamaño del item cuando tiene foco
                    itemView.scaleX = 1.1f
                    itemView.scaleY = 1.1f
                } else {
                    // Devolver tamaño original cuando se pierde el foco
                    itemView.scaleX = 1f
                    itemView.scaleY = 1f
                }
            }
        }

        fun bind(imageResId: Int, courseName: String) {
            courseImageView.setImageResource(imageResId)
            courseNameTextView.text = courseName
        }
    }
}
