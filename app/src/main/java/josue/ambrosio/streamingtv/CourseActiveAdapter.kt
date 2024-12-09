package josue.ambrosio.streamingtv

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CourseActiveAdapter(
    private val videoList: List<Video>
) : RecyclerView.Adapter<CourseActiveAdapter.VideoViewHolder>() {

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val videoThumbnail: ImageView = itemView.findViewById(R.id.videoThumbnail)
        val videoTitle: TextView = itemView.findViewById(R.id.videoTitle)
        val videoDescription: TextView = itemView.findViewById(R.id.videoDescription)
        val videoDuration: TextView = itemView.findViewById(R.id.videoDuration)

        init {
            // Manejar el click en cada ítem del RecyclerView
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, VideoViewActivity::class.java)

                // Pasar datos del video seleccionado a VideoViewActivity
                val video = videoList[adapterPosition] // Obtener el video actual
                intent.putExtra("VIDEO_TITLE", video.title)
                intent.putExtra("VIDEO_DESCRIPTION", video.description)
                intent.putExtra("VIDEO_DURATION", video.duration)
                intent.putExtra("VIDEO_IMAGE", video.imageResId)

                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video_active, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videoList[position]
        holder.videoThumbnail.setImageResource(video.imageResId)
        holder.videoTitle.text = video.title
        holder.videoDescription.text = video.description
        holder.videoDuration.text = video.duration
    }

    override fun getItemCount() = videoList.size
}
