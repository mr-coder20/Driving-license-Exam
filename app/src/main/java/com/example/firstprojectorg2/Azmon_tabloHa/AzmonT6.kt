package com.example.firstprojectorg2.Azmon_tabloHa

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.firstprojectorg2.AzmonHa.ResultActivity
import com.example.firstprojectorg2.R
import com.example.firstprojectorg2.databinding.ActivityAzmonBinding
import com.example.firstprojectorg2.databinding.AzmonTabloHaBinding

class AzmonT6: AppCompatActivity() {
    private lateinit var binding: AzmonTabloHaBinding
    private var currentQuestionIndex = 0
    private var questionCounter = 1

    private val questions = listOf(
        "#1. مفهوم تابلوی زیر چیست؟" to
                listOf(
                    " الف. فرعی به اصلی ",
                    " ب. تقاطع فرعی به اصلی ",
                    " ج. تقاطع",
                    " د.ورودی از چپ و راست"
                ),
        " #2.مفهوم تابلوی زیر چیست؟ "
                to listOf(
            " الف. سبقت ممنوع ",
            " ب.  عبور ممنوع ",
            " ج. عبور کلیه وسایل نقلیه ممنوع ",
            " د.عبور دوچرخه، سواری و گاری ممنوع"
        ),
        "#3.مفهوم مستطیل پرچی در علائم عبور و مرور کدام است؟"
                to listOf(
            " الف.  دستوری و خدمات ",
            " ب. رعایت حق تقدم ",
            " دستوری و بازدارنده ",
            " د. علائم راهنما"
        ),

        "#4.مفهوم تابلو شکل زیر چیست؟  "
                to listOf(
            " الف.  حداقل سرعت در خط های عبور ",
            " ب. حداکثر سرعت در خط های عبور",
            " ج. حداقل سرعت توصیه شده",
            "د. سرعت بیش از 50 کیلومتر در ساعت ممنوع"
        ),

        "#5.مفهوم شکل لوزی در علائم عبور و مرور چیست؟"
                to listOf(
            " الف.علامت دستوری، بازدارنده",
            " ب. صرفا برای رعایت حق تقدم",
            " ج.معمولا برای علائم راهنما",
            " د.شروع و پایان حق تقدم مسیر"
        ),

        "#6.مفهوم شکل هشت گوشه بالا در علائم عبور و مرور چیست؟"
                to listOf(
            " الف.شروع و پایان حق تقدم مسیر",
            " ب. صرفا برای رعایت حق تقدم",
            " ج.تنها برای تابلوهای ایست (توقف)",
            " د.علامت دستوری - بازدارنده"
        ),

        "#7.کدام یک از تابلوهای بالا راهنمای مسیر می باشد؟"
                to listOf(
            " الف. 2",
            " ب.3 ",
            " ج.1",
            " د.4"
        ),

        "#8.مفهوم تابلوی زیر چیست؟ "
                to listOf(
            " الف.بن بست",
            " ب.راه بسته است",
            " ج.تقاطع",
            " د.راه خطرناک است"
        ),

        "#9.تابلو شکل زیر بیانگر چیست؟ "
                to listOf(
            " الف. گذرگاه پیاده",
            " ب. زیرگذر یا روگذر",
            " ج.شروع محل پیاده روی",
            " د. شیب سربالایی ده درصد"
        ),

        "#10.کدام یک از تابلوهای بالا درباره توقف نمی باشد؟"
                to listOf(
            " الف. 4",
            " ب. 3",
            " ج. 2",
            " د. 1"
        ),


        )
    private val questionImages = listOf(
        "https://test-drive.ir/wp-content/uploads/2022/04/5437378996.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/77883635.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/6367475357.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/457598996.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/344367788.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/6645745886.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/4364378.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/5347678785.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/643647889.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/446543687.jpg",
    )

    private val correctAnswers = listOf(

        " ج. تقاطع",
        " ج. عبور کلیه وسایل نقلیه ممنوع ",
        " د. علائم راهنما" ,
        "د. سرعت بیش از 50 کیلومتر در ساعت ممنوع" ,
        " د.شروع و پایان حق تقدم مسیر" ,
        " ج.تنها برای تابلوهای ایست (توقف)",
        " د.4" ,
        " الف.بن بست",
        " ب. زیرگذر یا روگذر",
        " د. 1"

    )
    private var correctCount = 0
    private var incorrectCount = 0
    private val answeredQuestions = BooleanArray(questions.size) { false }

    private lateinit var gestureDetector: GestureDetector
    private lateinit var countDownTimer: CountDownTimer

    private var selectedAnswer: String? = null
    private var showCorrectAnswers = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AzmonTabloHaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.answerLayout1.setOnClickListener {
            onAnswerLayoutClicked(binding.answerLayout1, binding.option1Text.text.toString())
        }
        binding.answerLayout2.setOnClickListener {
            onAnswerLayoutClicked(binding.answerLayout2, binding.option2Text.text.toString())
        }
        binding.answerLayout3.setOnClickListener {
            onAnswerLayoutClicked(binding.answerLayout3, binding.option3Text.text.toString())
        }
        binding.answerLayout4.setOnClickListener {
            onAnswerLayoutClicked(binding.answerLayout4, binding.option4Text.text.toString())
        }

        binding.submitButton.setOnClickListener {
            checkAnswer()
        }

        binding.endButton.setOnClickListener {
            endQuiz()
        }

        showCorrectAnswers = intent.getBooleanExtra("showCorrectAnswers", false)

        initializeQuiz()

        gestureDetector = GestureDetector(this, MyGestureListener())

        if (!showCorrectAnswers) {
            startTimer(1200000)
        }

        binding.textView31.text = questionCounter.toString()
    }

    private fun startTimer(millisInFuture: Long) {
        countDownTimer = object : CountDownTimer(millisInFuture, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                binding.timerTextView.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                showResult()
            }
        }.start()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let { gestureDetector.onTouchEvent(it) }
        return super.onTouchEvent(event)
    }

    private fun initializeQuiz() {
        updateQuestion()
        updateQuestionImage()
        updateOptions()
        resetAnswerLayouts()
        selectedAnswer = null
        if (showCorrectAnswers) {
            highlightCorrectAnswers()
        }

        preloadNextQuestionImage() // پیش‌بارگذاری تصویر سوال بعدی
    }

    private fun preloadNextQuestionImage() {
        if (currentQuestionIndex < questionImages.size - 1) {
            Glide.with(this)
                .load(questionImages[currentQuestionIndex + 1])
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .preload()
        }
    }

    private fun updateQuestionImage() {
        Glide.with(this)
            .load(questionImages[currentQuestionIndex])
            .thumbnail(
                Glide.with(this).load(questionImages[currentQuestionIndex])
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            )
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imageView6)
    }

    private fun updateQuestion() {
        binding.questionTextView.text = questions[currentQuestionIndex].first
    }

    private fun updateOptions() {
        val options = questions[currentQuestionIndex].second
        binding.option1Text.text = options[0]
        binding.option2Text.text = options[1]
        binding.option3Text.text = options[2]
        binding.option4Text.text = options[3]
    }

    private fun checkAnswer() {
        if (selectedAnswer != null) {
            if (!answeredQuestions[currentQuestionIndex]) {
                val correctAnswer = correctAnswers[currentQuestionIndex]
                if (selectedAnswer == correctAnswer) {
                    correctCount++
                } else {
                    incorrectCount++
                }
                answeredQuestions[currentQuestionIndex] = true
            } else {
                showErrorMessage()
                return
            }
        } else {
            Toast.makeText(this, "لطفاً یک پاسخ را انتخاب کنید!", Toast.LENGTH_SHORT).show()
            return
        }
        moveToNextQuestion()
    }

    private fun showErrorMessage() {
        AlertDialog.Builder(this)
            .setTitle("خطا")
            .setMessage("شما قبلاً به این سوال پاسخ داده‌اید!")
            .setPositiveButton("باشه", null)
            .show()
    }

    inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {
        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val diffX = e1?.x?.let { e2.x - it } ?: 0f
            if (diffX < -SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (currentQuestionIndex < questions.size - 1) {
                    moveToNextQuestion()
                }
                return true
            } else if (diffX > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                return true
            }
            return false
        }
    }

    private fun showResult() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("correctCount", correctCount)
        intent.putExtra("incorrectCount", incorrectCount)
        intent.putExtra("examId", "AzmonT6")
        startActivity(intent)
        finish()
    }

    private fun moveToNextQuestion() {
        if (currentQuestionIndex < questions.size - 1) {
            val outAnim = AnimationUtils.loadAnimation(this, R.anim.slide_out_left)
            val inAnim = AnimationUtils.loadAnimation(this, R.anim.slide_in_right)

            binding.questionTextView.startAnimation(outAnim)
            binding.option1Text.startAnimation(outAnim)
            binding.option2Text.startAnimation(outAnim)
            binding.option3Text.startAnimation(outAnim)
            binding.option4Text.startAnimation(outAnim)
            binding.imageView6.startAnimation(outAnim)

            outAnim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}

                override fun onAnimationRepeat(animation: Animation) {}

                override fun onAnimationEnd(animation: Animation) {
                    currentQuestionIndex++
                    questionCounter++
                    binding.textView31.text = questionCounter.toString()
                    initializeQuiz()

                    binding.questionTextView.startAnimation(inAnim)
                    binding.option1Text.startAnimation(inAnim)
                    binding.option2Text.startAnimation(inAnim)
                    binding.option3Text.startAnimation(inAnim)
                    binding.option4Text.startAnimation(inAnim)
                    binding.imageView6.startAnimation(inAnim)
                }
            })
        } else {
            countDownTimer.cancel()
            showResult()
        }
    }


    private fun onAnswerLayoutClicked(layout: ConstraintLayout, answer: String) {
        resetAnswerLayouts()
        layout.setBackgroundColor(Color.parseColor("#ADD8E6")) // رنگ آبی کم‌رنگ
        selectedAnswer = answer
    }

    private fun resetAnswerLayouts() {
        val defaultColor = Color.parseColor("#E6D6D1")
        binding.answerLayout1.setBackgroundColor(defaultColor)
        binding.answerLayout2.setBackgroundColor(defaultColor)
        binding.answerLayout3.setBackgroundColor(defaultColor)
        binding.answerLayout4.setBackgroundColor(defaultColor)
    }

    private fun endQuiz() {
        countDownTimer.cancel()
        showResult()
    }

    private fun highlightCorrectAnswers() {
        val correctAnswer = correctAnswers[currentQuestionIndex]
        if (binding.option1Text.text == correctAnswer) {
            binding.answerLayout1.setBackgroundColor(Color.GREEN)
        }
        if (binding.option2Text.text == correctAnswer) {
            binding.answerLayout2.setBackgroundColor(Color.GREEN)
        }
        if (binding.option3Text.text == correctAnswer) {
            binding.answerLayout3.setBackgroundColor(Color.GREEN)
        }
        if (binding.option4Text.text == correctAnswer) {
            binding.answerLayout4.setBackgroundColor(Color.GREEN)
        }
    }
}
