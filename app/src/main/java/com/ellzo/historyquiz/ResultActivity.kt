package com.ellzo.historyquiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import com.ellzo.historyquiz.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val score = intent.getIntExtra(QuizActivity.RES_KEY, 0) * 20

        binding.tvScore.text = "$score%"

        binding.btnHome.setOnClickListener {
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        binding.btnAgain.setOnClickListener {
            val newIntent = Intent(this@ResultActivity, QuizActivity::class.java)
            newIntent.putExtra(QuizActivity.WAR_KEY, intent.getIntExtra(QuizActivity.WAR_KEY, -1))
            newIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(newIntent)
        }

        binding.btnShare.setOnClickListener {
            val quizType = when (intent.getIntExtra(QuizActivity.WAR_KEY, -1)) {
                QuizActivity.WW1 -> "WWI"
                QuizActivity.WW2 -> "WWII"
                else -> "History"
            }
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder(this)
                .setType(mimeType)
                .setChooserTitle(getString(R.string.share_score))
                .setText("I got $score% in $quizType quiz!")
                .startChooser()
        }
    }
}