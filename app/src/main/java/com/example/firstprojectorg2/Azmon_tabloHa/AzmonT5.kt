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

class AzmonT5 : AppCompatActivity() {
    private lateinit var binding: AzmonTabloHaBinding
    private var currentQuestionIndex = 0
    private var questionCounter = 1

    private val questions = listOf(
        "#1. تابلو زیر بیانگر چیست؟" to
                listOf(
                    " الف.شیب سرازیری 10 درصد ",
                    " ب.شیب سرازیری 20 درصد ",
                    " ج.سربالایی خطرناک ",
                    " د.سرازیری خطرناک "
                ),
        " #2. مفهوم رنگ سبز در علائم عبور و مرور چیست؟ "
                to listOf(
            " الف.حرکات مجاز، راهنمایی مسیر در بزرگراه ها و امکان مذهبی ",
            " ب.منع کننده یا ایست  ",
            " ج. راهنما برای مناطق اداری و آموزشی و خدماتی ",
            " د.راهنما برای مناطق تفریحی و فرهنگی و گردشگری"
        ),
        "#3. این تابلو بیانگر چیست؟   "
                to listOf(
            " الف.طول محدوده خطر  ",
            " ب.طول ممنوعیت با محدودیت پارکینگ",
            "رعایت فاصله کمتر از 10 متر ممنوع ",
            " د.راهنمای مسیر "
        ),

        "#4.این تابلو بیانگر چیست؟"
                to listOf(
            " الف.پایان حداقل سرعت 30 کیلومتر",
            " ب.سرعت بیش از 30 کیلومتر بر ساعت ممنوع ",
            " ج.حداقل سرعت 30 کیلومتر  ",
            "د.پایان محدودیت سرعت ( 30 کیلومتر)"
        ),

        "#5.مفهوم تابلوی زیر چیست؟ "
                to listOf(
            " الف. تقاطع راه آهن با راه بند",
            " ب.جهت وزش باد شدید از راست",
            " ج.تقاطع راه آهن بدون راه بند",
            " د.جهت وزش باد شدید از چپ"
        ),

        "#6.تابلو بالا بیانگر چیست؟   "
                to listOf(
            " الف.تقاطع",
            " ب.فقط گردش به چپ مجاز",
            " ج. میدان ",
            " د.جهت عبور در میدان"
        ),

        "#7.  مفهوم این تابلو چیست؟ "
                to listOf(
            " الف. تقاطع راه آهن با راه بند",
            " ب. تقاطع جاده و راه آهن با راه بند، نصب در محل تقاطع",
            " ج.تقاطع جاده با راه بند",
            " د.چمنزار"
        ),

        "#8.مفهوم تابلوی زیر چیست؟"
                to listOf(
            " الف.جاده گل آلود است ",
            " ب.جاده لغزنده است. ",
            " ج. منطقه پرتاب سنگ ",
            " د. پرتاب سنگ"
        ),

        "#9.هنرجوی گرامی مفهوم تابلو زیر چیست؟ "
                to listOf(
            " الف.تقاطع",
            " ب.سه راه",
            " ج.فرعی به اصلی",
            " د.خروجی از چپ"
        ),

        "#10.تابلو شکل زیر بیانگر چیست؟ "
                to listOf(
            " الف.تقاطع جاده و راه آهن در 100 متری",
            " ب. خروجی از آزادراه 100 متر ",
            " ج. تقاطع جاده و راه آهن در 200 متری",
            " د. خروجی از آزادراه 200 متر"
        ),


        )
    private val questionImages = listOf(
        "https://test-drive.ir/wp-content/uploads/2022/04/4889338.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/457686598.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/4457889904.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/6435878443.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/77784478.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/6678747.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/43677877.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/46457883.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/436777885.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/346788899.jpg",
    )

    private val correctAnswers = listOf(

        " ج.سربالایی خطرناک ",
        " الف.حرکات مجاز، راهنمایی مسیر در بزرگراه ها و امکان مذهبی ",
        " ب.طول ممنوعیت با محدودیت پارکینگ",
        " ج.حداقل سرعت 30 کیلومتر  ",
        " ب.جهت وزش باد شدید از راست",
        " د.جهت عبور در میدان" ,
        " الف. تقاطع راه آهن با راه بند",
        " د. پرتاب سنگ" ,
        " الف.تقاطع",
        " د. خروجی از آزادراه 200 متر"

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
        intent.putExtra("examId", "AzmonT5")
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
