package josue.ambrosio.streamingtv

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter(private val videos: List<Video>) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    var focusedPosition = -1 // Para rastrear el ítem enfocado

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videos[position]
        holder.bind(video)

        // Resaltar el ítem enfocado
        if (focusedPosition == position) {
            holder.itemView.setBackgroundColor(Color.YELLOW) // O cualquier color que te guste
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT)
        }

        // Manejo del foco
        holder.itemView.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                focusedPosition = position
                notifyDataSetChanged() // Notificar que el ítem seleccionado cambió
            }
        }
    }

    override fun getItemCount() = videos.size

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.videoTitle)

        fun bind(video: Video) {
            title.text = video.title
        }
    }
}
