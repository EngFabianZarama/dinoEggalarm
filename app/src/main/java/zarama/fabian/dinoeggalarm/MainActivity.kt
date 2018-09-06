package zarama.fabian.dinoeggalarm

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Time

class MainActivity : AppCompatActivity() {

    private lateinit var timeBar: SeekBar
    private lateinit var minutes: TextView
    private lateinit var seconds: TextView
    private lateinit var buttonStart: Button
    private lateinit var mp: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeBar = findViewById(R.id.seekBarTime)
        minutes = findViewById(R.id.textViewMinutes)
        seconds = findViewById(R.id.textViewSeconds)
        buttonStart = findViewById(R.id.buttonStart)
        mp = MediaPlayer.create(this,R.raw.tyrannosaurusrex)




        // Maximum location number
        timeBar.max = 600 // 600 seconds = 10 min

        // Start location number
        timeBar.progress = 0



        timeBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

                minutes.setText((progress/60).toString())
                seconds.setText((progress%60).toString())

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {


            }
        })



        buttonStart.setOnClickListener {


            var timeInSeconds =   ((((minutes.text).toString().toInt())*60)+(seconds.text.toString().toInt()))*1000

             var cdTimer = object :CountDownTimer(timeInSeconds.toLong()+100,1000 ){

                    override fun onTick(timeUntilDoneInMilliseconds: Long) {
                        minutes.setText((timeUntilDoneInMilliseconds/60000).toString())
                        seconds.setText(((timeUntilDoneInMilliseconds/1000)%60).toString())
                    }

                    override fun onFinish() {
                        mp.start()
                    }

               }.start()

            seekBarTime.isEnabled = false
            buttonStop.visibility = View.VISIBLE

            buttonStop.setOnClickListener {
                //reset counter
                cdTimer.cancel()
                seekBarTime.isEnabled = true
                buttonStop.visibility = View.INVISIBLE

            }

        }




    }
}
