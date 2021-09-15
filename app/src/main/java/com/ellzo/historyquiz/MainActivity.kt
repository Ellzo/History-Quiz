package com.ellzo.historyquiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ellzo.historyquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnWw1.setOnClickListener {
            val intent = Intent(this@MainActivity, QuizActivity::class.java)
            intent.putExtra(QuizActivity.WAR_KEY, QuizActivity.WW1)
            startActivity(intent)
        }

        binding.btnWw2.setOnClickListener {
            val intent = Intent(this@MainActivity, QuizActivity::class.java)
            intent.putExtra(QuizActivity.WAR_KEY, QuizActivity.WW2)
            startActivity(intent)
        }

    }
}