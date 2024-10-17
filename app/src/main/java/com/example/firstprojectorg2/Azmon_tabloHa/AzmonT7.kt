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

class AzmonT7 : AppCompatActivity() {
    private lateinit var binding: AzmonTabloHaBinding
    private var currentQuestionIndex = 0
    private var questionCounter = 1

    private val questions = listOf(
        "#1. کدام یک از تابلوهای بالا راننده را از انجام عملی باز می دارد؟" to
                listOf(
                    " الف.2",
                    " ب. 4",
                    " ج.1",
                    " د.3"
                ),
        " #2.هنرجوی گرامی تابلو بالا بیانگر چیست؟"
                to listOf(
            " الف.تقاطع",
            " ب.محدوده خطر",
            " ج.تقاطع با راه آهن",
            " د.خطوط پرواز هواپیما"
        ),
        "#3. تابلو شکل بالا بیانگر چیست؟"
                to listOf(
            " الف.سازمان امداد و نجات جاده ای",
            " ب.مرکز درمانی واقع در جاده",
            "پست امدادی ",
            " د. بیمارستان"
        ),

        "#4.از مثلث متساوی الاضلاع (نشسته روی قاعده) معمولا برای چه نوع تابلوهایی استفاده می شود؟"
                to listOf(
            " الف. دستوری و خدمات",
            " ب. علائم راهنما",
            " ج. علامت هشدار دهنده برای خطر",
            "د.دستوری یا بازدارنده"
        ),

        "#5.مفهوم تابلوی بالا چیست؟"
                to listOf(
            " الف. علامت ورود به شهر در جاده",
            " ب. ورود به شهر ",
            " ج.ابتدای ورود به شهر در جاده",
            " د. ابتدای تهران"
        ),

        "#6.مفهوم رنگ آبی در علائم راهنمایی و رانندگی چیست؟ "
                to listOf(
            " الف.علائم دستوری و راهنمای مسیر",
            " ب.راهنما برای مناطق اداری ",
            " ج.راهنمای خدمات و مسیر و علائم اخباری در آزادراه ها",
            " د.منع کننده و بازدارنده"
        ),

        "#7.عنوان تابلو زیر چیست؟"
                to listOf(
            " الف.راه لغزنده",
            " ب.دست انداز",
            " ج.چاله",
            " د.شانه خطرناک"
        ),

        "#8.معنی تابلوی تصویر بالا چیست؟"
                to listOf(
            " الف. پایان سبقت ممنوع برای کامیون",
            " ب.پایان سبقت ممنوع",
            " ج.سبقت برای کامیون ممنوع",
            " د.پایان تمام محدودیت ها"
        ),

        "#9. تبعیت از این تابلوها الزامی و در صورت عدم توجه، رانندگان مرتکب خلاف و نقض قانون می گردند: "
                to listOf(
            " الف.تابلوهای هشدار دهنده",
            " ب.تابلوهای اخطاری",
            " ج. تابلوهای آگاهی دهنده غیر دستوری",
            " د.تابلوهای آگاهی دهنده دستوری"
        ),

        "#10.معنی تابلوی شکل زیر چیست ؟"
                to listOf(
            " الف.جاده باریک می شود",
            " ب. حاشیه نمای دو طرفه",
            " ج.حاشیه نمای چب",
            " د.حاشیه نمای راست"
        ),


        )
    private val questionImages = listOf(
        "https://test-drive.ir/wp-content/uploads/2022/04/6457788.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/464537878.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/5346777.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/5346677832.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/883434377.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/997645534.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/474575868.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/886585.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/66585353.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/74568468.jpg",
    )

    private val correctAnswers = listOf(
        " د.3" ,
        " الف.تقاطع",
        " د. بیمارستان" ,
        " ج. علامت هشدار دهنده برای خطر",
        " ب. ورود به شهر ",
        " ج.راهنمای خدمات و مسیر و علائم اخباری در آزادراه ها",
        " ج.چاله",
        " الف. پایان سبقت ممنوع برای کامیون",
        " د.تابلوهای آگاهی دهنده دستوری" ,
        " ب. حاشیه نمای دو طرفه",

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
        intent.putExtra("examId", "AzmonT7")
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
