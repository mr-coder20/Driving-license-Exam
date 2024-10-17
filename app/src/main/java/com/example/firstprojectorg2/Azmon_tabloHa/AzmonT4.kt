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

class AzmonT4 : AppCompatActivity() {
    private lateinit var binding: AzmonTabloHaBinding
    private var currentQuestionIndex = 0
    private var questionCounter = 1

    private val questions = listOf(
        "#1.از شکل دایره معمولاً برای چه نوع علائم عبور و مرور و تابلوهایی استفاده می شود؟" to
                listOf(
                    " الف.دستوری و خدمات ",
                    " ب.علائم راهنما",
                    " ج.علائم هشداردهنده برای خطر",
                    " د.دستوری یا بازدارنده"
                ),
        " #2.این تابلو بیانگر چیست؟"
                to listOf(
            " الف.تقاطع جاده و راهآهن در 100متری ",
            " ب.  خروجی از آزادراه 100 متری ",
            " ج.تقاطع جاده و راهآهن در 100 متری ",
            " د. خروجی از آزادراه 200 متری "
        ),
        "#3.این تابلو بیانگر چیست؟"
                to listOf(
            " الف.سرعت بیش از ۷۰ کیلومتر بر ساعت ممنوع",
            " ب. رعایت فاصله بیشتر از ۷۰ متر ممنوع",
            "رعایت فاصله کمتر از ۷۰ متر ممنوع ",
            " د. سرعت کمتر از ۷۰ کیلومتر بر ساعت ممنوع "
        ),

        "#4.در مبحث مفهوم رنگ ها و علائم عبور و مرور، مفهوم کدام رنگ به معنی حرکات مجاز می باشد؟"
                to listOf(
            " الف.تابلوی شماره 1",
            " ب. تابلوی شماره 2 ",
            " ج. تابلوی شماره 3",
            "د. تابلوی شماره 4"
        ),

        "#5.کدامیک از تابلوهای زیر به معنای خطر می باشد؟"
                to listOf(
            " الف.تابلوی شماره 1 ",
            " ب.تابلوی شماره 2 ",
            " ج. تابلوی شماره 3 ",
            " د. تابلوی شماره 4 "
        ),

        "#6.کدامیک از تابلوهای زیر راننده را از انجام عمل بازمی دارد؟"
                to listOf(
            " الف. تابلوی شماره 1",
            " ب. تابلوی شماره 2",
            " ج. تابلوی شماره3",
            " د. تابلوی شماره 4"
        ),

        "#7.این تابلو بیانگر چیست؟"
                to listOf(
            " الف. منطقه محدودیت سرعت ۳۰ کیلومتر",
            " ب.حداقل سرعت ۳۰ کیلومتر ",
            " ج. سرعت بیش از 30 کیلومتر بر ساعت ممنوع ",
            " د.پایان حداقل سرعت ۳۰ کیلومتر"
        ),

        "#8.این تابلو بیانگر چیست؟"
                to listOf(
            " الف. عبور حیوانات وحشی ",
            " ب.  عبور حیوانات اهلی",
            " ج. فقط عبور گوزن",
            " د.شروع منطقه حیات وحش"
        ),

        "#9.کدام یک جزو تابلوهای حاشیه نما نمی باشد؟"
                to listOf(
            " الف.تابلوی شماره 1",
            " ب. تابلوی شماره 2",
            " ج. تابلوی شماره 3",
            " د. تابلوی شماره 4"
        ),

        "#10.این تابلو بیانگر چیست؟ "
                to listOf(
            " الف. فاصله از نصب تابلو",
            " ب.حداقل سرعت در خط عبور ",
            " ج.حداقل سرعت",
            " د.سرعت توصیه شده"
        ),


        )
    private val questionImages = listOf(
        "https://www.testdriver.ir/wp-content/uploads/2021/02/2021-01-26_005635.jpg",
        "https://www.testdriver.ir/wp-content/uploads/2021/02/6-1.jpg",
        "https://www.testdriver.ir/wp-content/uploads/2021/02/12.png",
        "https://www.testdriver.ir/wp-content/uploads/2021/02/Master-PDF-Editor-5.Uh3536-copy.png",
        "https://www.testdriver.ir/wp-content/uploads/2021/02/2021-01-30_025830.jpg",
        "https://www.testdriver.ir/wp-content/uploads/2021/02/2021-01-30_025731.jpg",
        "https://www.testdriver.ir/wp-content/uploads/2021/02/2021-01-26_000117.jpg",
        "https://www.testdriver.ir/wp-content/uploads/2021/02/2021-01-24_234603.jpg",
        "https://www.testdriver.ir/wp-content/uploads/2021/02/200.jpg",
        "https://www.testdriver.ir/wp-content/uploads/2021/02/2021-01-29_164615.jpg",
    )

    private val correctAnswers = listOf(
        " د.دستوری یا بازدارنده" ,
        " د. خروجی از آزادراه 200 متری " ,
        "رعایت فاصله کمتر از ۷۰ متر ممنوع ",
        " الف.تابلوی شماره 1",
        " د. تابلوی شماره 4 " ,
        " ب. تابلوی شماره 2",
        " د.پایان حداقل سرعت ۳۰ کیلومتر" ,
        " الف. عبور حیوانات وحشی ",
        " ب. تابلوی شماره 2",
        " د.سرعت توصیه شده"



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
        intent.putExtra("examId", "AzmonT4")
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
