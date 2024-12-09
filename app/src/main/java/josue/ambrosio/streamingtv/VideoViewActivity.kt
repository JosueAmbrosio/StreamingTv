package josue.ambrosio.streamingtv

import android.os.Bundle
import android.view.KeyEvent
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import android.widget.VideoView
import android.widget.SeekBar
import android.widget.TextView
import android.os.Handler
import android.net.Uri
import android.view.View

class VideoViewActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var playPauseButton: MaterialButton
    private lateinit var rewindButton: MaterialButton
    private lateinit var forwardButton: MaterialButton
    private lateinit var controlBar: View
    private lateinit var videoProgressBar: SeekBar
    private lateinit var exitButton: MaterialButton
    private lateinit var timeTextView: TextView
    private lateinit var personSpeakingButton: ImageButton
    private lateinit var infoPopup: LinearLayout
    private lateinit var closeButton: MaterialButton

    private var isPlaying = false
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_view)

        // Inicializa las vistas
        videoView = findViewById(R.id.videoView)
        playPauseButton = findViewById(R.id.playPauseButton)
        rewindButton = findViewById(R.id.rewindButton)
        forwardButton = findViewById(R.id.forwardButton)
        controlBar = findViewById(R.id.controlBar)
        videoProgressBar = findViewById(R.id.videoProgressBar)
        exitButton = findViewById(R.id.exitButton)
        timeTextView = findViewById(R.id.timeTextView)
        personSpeakingButton = findViewById(R.id.personSpeakingButton)
        infoPopup = findViewById(R.id.infoPopup)
        closeButton = findViewById(R.id.closeButton)

        // Establece el video desde un archivo local
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.nodejs)
        videoView.setVideoURI(videoUri)

        // Barra de progreso
        videoProgressBar.max = videoView.duration

        // Cuando el video está listo
        videoView.setOnPreparedListener {
            videoProgressBar.progress = 0
            videoProgressBar.max = videoView.duration
            videoView.start()
            isPlaying = true
            playPauseButton.icon = getDrawable(R.drawable.ic_pause)
            controlBar.visibility = View.VISIBLE
            updateProgressBar()
        }

        // Cuando el video finaliza
        videoView.setOnCompletionListener {
            isPlaying = false
            playPauseButton.icon = getDrawable(R.drawable.ic_play)
            controlBar.visibility = View.GONE
        }

        // Botones
        playPauseButton.setOnClickListener { togglePlayPause() }
        rewindButton.setOnClickListener { rewindVideo() }
        forwardButton.setOnClickListener { forwardVideo() }
        exitButton.setOnClickListener { finish() }

        // Funcionalidad para el botón de persona hablando
        personSpeakingButton.setOnClickListener {
            infoPopup.visibility = View.VISIBLE
        }

        // Funcionalidad para cerrar la ventana emergente
        closeButton.setOnClickListener {
            infoPopup.visibility = View.GONE
        }

        // Barra de progreso interactiva
        videoProgressBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) videoView.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    // Función para alternar entre reproducción y pausa
    private fun togglePlayPause() {
        if (isPlaying) {
            videoView.pause()
            isPlaying = false
            playPauseButton.icon = getDrawable(R.drawable.ic_play)
            handler.removeCallbacksAndMessages(null)
        } else {
            videoView.start()
            isPlaying = true
            playPauseButton.icon = getDrawable(R.drawable.ic_pause)
            updateProgressBar()
        }
    }

    // Función para retroceder 10 segundos
    private fun rewindVideo() {
        val currentPosition = videoView.currentPosition
        val rewindPosition = (currentPosition - 10000).coerceAtLeast(0)
        videoView.seekTo(rewindPosition)
    }

    // Función para adelantar 10 segundos
    private fun forwardVideo() {
        val currentPosition = videoView.currentPosition
        val forwardPosition = (currentPosition + 10000).coerceAtMost(videoView.duration)
        videoView.seekTo(forwardPosition)
    }

    // Actualiza la barra de progreso
    private fun updateProgressBar() {
        if (isPlaying) {
            videoProgressBar.progress = videoView.currentPosition
            val currentTime = videoView.currentPosition / 1000
            val totalTime = videoView.duration / 1000
            val currentTimeFormatted = String.format("%02d:%02d", currentTime / 60, currentTime % 60)
            val totalTimeFormatted = String.format("%02d:%02d", totalTime / 60, totalTime % 60)
            timeTextView.text = "$currentTimeFormatted / $totalTimeFormatted"
            handler.postDelayed({ updateProgressBar() }, 1000)
        }
    }

    // Manejar teclas del control remoto
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_ENTER -> { // Play/Pausa
                togglePlayPause()
                return true
            }
            KeyEvent.KEYCODE_DPAD_LEFT -> { // Retroceder
                rewindVideo()
                return true
            }
            KeyEvent.KEYCODE_DPAD_RIGHT -> { // Adelantar
                forwardVideo()
                return true
            }
            KeyEvent.KEYCODE_BACK -> { // Salir
                finish()
                return true
            }
            KeyEvent.KEYCODE_DPAD_UP -> {
                // Mover el foco al botón de salir si está en el control de reproducción
                if (currentFocus == playPauseButton) {
                    exitButton.requestFocus()
                    return true
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}
