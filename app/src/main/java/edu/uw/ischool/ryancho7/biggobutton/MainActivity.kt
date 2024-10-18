package edu.uw.ischool.ryancho7.biggobutton

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.graphics.Color
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    // keep track of counts
    private var pushCount = 0
    private lateinit var pushButton: Button
    private var isAnimating = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        pushButton = findViewById(R.id.push_button)
        pushButton.animate().scaleX(1.3f)
        pushButton.animate().scaleY(1.3f)
        pushButton.setBackgroundColor(Color.GREEN)
        // allow the button to listen for events
        pushButton.setOnClickListener {
            // increment our count each time
            pushCount++
            // update our text using updated count value
            val updatedText = when (pushCount) {
                1 -> getString(R.string.pushed_1_time)
                else -> getString(
                    R.string.pushed_x_times,
                    pushCount
                ) // parameter replaces the placeholder
            }
            // set the text
            pushButton.text = updatedText

            // get random background color
            pushButton.setBackgroundColor(getRandomColor())
            pushButton.setTextColor(getRandomColor())

            // start or stop animation
            if(isAnimating) {
                stopButtonAnimation()
                isAnimating = false
            } else {
                startButtonAnimation()
                isAnimating = true
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // random color getter
    private fun getRandomColor() : Int {
        return Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
    }

    // button animation
    private fun startButtonAnimation() {
        pushButton.animate()
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(500)
            .start()
    }

    private fun stopButtonAnimation() {
        pushButton.animate()
            .scaleX(1.3f)
            .scaleY(1.3f)
            .setDuration(500)
            .start()
    }

}