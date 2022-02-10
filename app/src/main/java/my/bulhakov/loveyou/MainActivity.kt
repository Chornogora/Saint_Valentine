package my.bulhakov.loveyou

import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var drawView: DrawView

    var mMediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        drawView = findViewById(R.id.canvas)
        drawView.visibility = View.GONE

        button = findViewById(R.id.button)
        button.setOnClickListener { onButtonClicked() }
    }

    fun onButtonClicked() {
        button.visibility = View.GONE
        drawView.visibility = View.VISIBLE
        mMediaPlayer = MediaPlayer.create(this, R.raw.media)
        mMediaPlayer?.start()
    }
}