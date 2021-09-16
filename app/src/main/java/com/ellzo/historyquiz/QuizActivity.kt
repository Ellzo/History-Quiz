package com.ellzo.historyquiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.ellzo.historyquiz.databinding.ActivityQuizBinding
import java.util.*
import kotlin.properties.Delegates

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private var ww by Delegates.notNull<Int>()
    private val c = Calendar.getInstance()
    private var current = 0
    private var correctCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ww = intent.getIntExtra(WAR_KEY, -1)
        when (ww) {
            WW1 -> {
                binding.tvQuestion.text = resources.getStringArray(R.array.ww1_questions)[current]
            }

            WW2 -> {
                binding.tvQuestion.text = resources.getStringArray(R.array.ww2_questions)[current]
            }
        }

        setTvDate()

        binding.tvDate.setOnClickListener {
            val dateString = binding.tvDate.text.toString()
            DatePickerFragment(
                dateString.subSequence(3, 5).toString().toInt(),
                dateString.subSequence(0, 2).toString().toInt() - 1,
                dateString.subSequence(6, dateString.length).toString().toInt()
            ).show(supportFragmentManager, "Date Picker")
        }

        binding.btnDone.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val isCorrect =
            when (ww) {
                WW1 -> binding.tvDate.text.toString() == resources.getStringArray(R.array.ww1_answers)[current]
                WW2 -> binding.tvDate.text.toString() == resources.getStringArray(R.array.ww2_answers)[current]
                else -> false
            }

        val result =
            if (isCorrect)
                resources.getString(R.string.correct)
            else getString(R.string.wrong)

        if (isCorrect)
            correctCount++

        AlertDialog.Builder(this@QuizActivity).apply {
            setTitle(result)
            setPositiveButton("Next Question") { _, _ ->
            }
            setOnDismissListener {
                nextQuestion(isCorrect)
            }
            show()
        }
    }

    fun setTvDate(
        day: Int = c[Calendar.DAY_OF_MONTH],
        month: Int = c[Calendar.MONTH] + 1,
        year: Int = c[Calendar.YEAR] - 90
    ) {
        binding.tvDate.text =
            if (month < 10 && day < 10) "0$month/0$day/$year"
            else if (month < 10) "0$month/$day/$year"
            else if (day < 10) "$month/0$day/$year"
            else "$month/$day/$year"

    }

    private fun nextQuestion(isCorrect: Boolean) {
        if (current == 4) {
            val intent = Intent(this@QuizActivity, ResultActivity::class.java)
            intent.putExtra(WAR_KEY, ww)
            intent.putExtra(RES_KEY, correctCount)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        } else {
            when (current) {
                0 -> {
                    binding.tvQ1.setBackgroundColor(
                        if (isCorrect) ResourcesCompat.getColor(resources, R.color.green, null)
                        else ResourcesCompat.getColor(resources, R.color.red, null)
                    )
                    binding.tvQ1.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.secondaryColor,
                            null
                        )
                    )
                    binding.tvQ2.background = null
                    binding.tvQ2.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.accentColor,
                            null
                        )
                    )
                }
                1 -> {
                    binding.tvQ2.setBackgroundColor(
                        if (isCorrect) ResourcesCompat.getColor(resources, R.color.green, null)
                        else ResourcesCompat.getColor(resources, R.color.red, null)
                    )
                    binding.tvQ2.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.secondaryColor,
                            null
                        )
                    )
                    binding.tvQ3.background = null
                    binding.tvQ3.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.accentColor,
                            null
                        )
                    )
                }
                2 -> {
                    binding.tvQ3.setBackgroundColor(
                        if (isCorrect) ResourcesCompat.getColor(resources, R.color.green, null)
                        else ResourcesCompat.getColor(resources, R.color.red, null)
                    )
                    binding.tvQ3.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.secondaryColor,
                            null
                        )
                    )
                    binding.tvQ4.background = null
                    binding.tvQ4.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.accentColor,
                            null
                        )
                    )
                }
                3 -> {
                    binding.tvQ4.setBackgroundColor(
                        if (isCorrect) ResourcesCompat.getColor(resources, R.color.green, null)
                        else ResourcesCompat.getColor(resources, R.color.red, null)
                    )
                    binding.tvQ4.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.secondaryColor,
                            null
                        )
                    )
                    binding.tvQ5.background = null
                    binding.tvQ5.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.accentColor,
                            null
                        )
                    )
                }
            }

            when (ww) {
                WW1 -> {
                    binding.tvQuestion.text =
                        resources.getStringArray(R.array.ww1_questions)[++current]
                }

                WW2 -> {
                    binding.tvQuestion.text =
                        resources.getStringArray(R.array.ww2_questions)[++current]
                }
            }

            setTvDate()
        }

    }


    companion object {
        const val WAR_KEY = "com.ellzo.historyquiz.whichwarforquiz"
        const val RES_KEY = "com.ellzo.historyquiz.howmuchdidget"
        const val WW1 = 47
        const val WW2 = 89
    }
}