package josue.ambrosio.streamingtv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ImageAdapter(private val imageResIds: IntArray, private val courseNames: Array<String>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    var selectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false) // Usamos el layout original item_image
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        // Asignar la imagen correspondiente
        holder.imageView.setImageResource(imageResIds[position])

        // Asignar el texto del curso
        holder.textView.text = courseNames[position]

        // Cambiar el fondo según si está seleccionado
        if (position == selectedPosition) {
            holder.imageView.setBackgroundResource(R.drawable.selected_background) // Tu drawable de fondo seleccionado
            holder.imageView.alpha = 1.0f // Mantener la opacidad
            holder.imageView.scaleX = 1.1f // Aumentar un poco el tamaño
            holder.imageView.scaleY = 1.1f // Aumentar un poco el tamaño
        } else {
            holder.imageView.setBackgroundResource(0) // Sin fondo
            holder.imageView.alpha = 0.5f // Desvanecer un poco las imágenes no seleccionadas
            holder.imageView.scaleX = 1.0f // Tamaño normal
            holder.imageView.scaleY = 1.0f // Tamaño normal
        }

        // Manejar el clic para seleccionar
        holder.itemView.setOnClickListener {
            selectItem(position)
        }
    }

    override fun getItemCount(): Int {
        return imageResIds.size
    }

    fun selectItem(position: Int) {
        notifyItemChanged(selectedPosition) // Notificar el elemento previamente seleccionado
        selectedPosition = position
        notifyItemChanged(selectedPosition) // Notificar el nuevo elemento seleccionado
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.image_view)
        var textView: TextView = itemView.findViewById(R.id.image_text)  // Referencia al TextView
    }
}
