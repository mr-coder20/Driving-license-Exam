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

class AzmonT2 : AppCompatActivity() {
    private lateinit var binding: AzmonTabloHaBinding
    private var currentQuestionIndex = 0
    private var questionCounter = 1

    private val questions = listOf(
        "#1.نام این تابلو چیست؟" to
                listOf(
                    " الف. ورود ممنوع",
                    " ب. راه مسدود است. ",
                    " ج. ورود از هر دو طرف ممنوع ",
                    " د. توقف ممنوع"
                ),
        " #2. نام این تابلو چیست ؟ "
                to listOf(
            " الف. ریزش سنگ ",
            " ب. سرازیری خطرناک ",
            " ج. شیب سرازیری 12 درصد ",
            " د. سربالایی خطرناک "
        ),
        "#3.این تابلو بیانگر چیست ؟   "
                to listOf(
            " الف. سبقت موتور سیکلت ممنوع ",
            " ب. عبور وسایل نقلیه موتوری ممنوع",
            " عبور موتور سیکلت ممنوع",
            " د. عبور موتور گازی ممنوع"
        ),

        "#4.مفهوم این تابلو چیست ؟  "
                to listOf(
            " الف. حداکثر سرعت در خط های عبور ",
            " ب. حداقل سرعت در خطهای عبور ",
            " ج. حداکثر سرعت در خط دوم 120 کیلومتر  ",
            "د. حداکثر سرعت در خط سوم 120 کیلومتر "
        ),

        "#5.معنی این تابلو چیست ؟ "
                to listOf(
            " الف. پایان سبقت ممنوع ",
            " ب. توقف ممنوع ",
            " ج. پایان تمام محدودیت ها ",
            " د. تقاطع با راه آهن  "
        ),

        "#6.تابلوی زیر یعنی....   "
                to listOf(
            " الف.  پایان حداقل سرعت 30 کیلومتر",
            " ب. حداقل سرعت 30 کیلومتر  ",
            " ج.  حداکثر سرعت 30 کیلومتر",
            " د.  پایان حداکثر سرعت 30 کیلومتر "
        ),

        "#7. مفهوم این تابلو چیست ؟  "
                to listOf(
            " الف. خطر در گردش به راست ",
            " ب. خطر در گردش به چپ ",
            " ج. پیچ های پی در پی اولین پیچ به چپ ",
            " د. پیچ های پی در پی اولین پیچ به راست  "
        ),

        "#8.این تابلو بیانگر چیست ؟    "
                to listOf(
            " الف. بوق زدن در پمپ آزاد ",
            " ب. منطقه بوق زدن آزاد ",
            " ج. بوق زدن ممنوع  ",
            " د. تعمیرگاه "
        ),

        "#9.مفهوم این تابلو چیست؟   "
                to listOf(
            " الف. پیچ به راست  ",
            " ب.  دور زدن ممنوع ",
            " ج. پیچ به چپ ",
            " د.  گردش به راست ممنوع "
        ),

        "#10.این تابلو بیانگر چیست؟    "
                to listOf(
            " الف. پایان شهر  ",
            " ب. ابتدای منطقه مسکونی  ",
            " ج. ورود به شهر  ",
            " د. ابتدای شهر  "
        ),


        )
    private val questionImages = listOf(
        "https://govahiyar.ir/wp-content/uploads/2021/06/vorood-mamnou.png",
        "https://govahiyar.ir/wp-content/uploads/2021/05/shib-saraziri-min.png",
        "https://govahiyar.ir/wp-content/uploads/2018/12/oboor-motor-cyclet-mamnou.png",
        "https://govahiyar.ir/wp-content/uploads/2021/04/hadeaksar-sorat-dar-khathaye-oboor.gif",
        "https://govahiyar.ir/wp-content/uploads/2022/05/payan-tamame-mahdoodyatha-min.png",
        "https://govahiyar.ir/wp-content/uploads/2023/06/hadeaghal-sorat-30kilometr.png",
        "https://govahiyar.ir/wp-content/uploads/2021/05/pichhaye-peydarpeynokhostin-pich-berast-min.png",
        "https://govahiyar.ir/wp-content/uploads/2023/08/boogh-zadan-mamnou.png",
        "https://govahiyar.ir/wp-content/uploads/2024/04/gardesh-be-chap.png",
        "https://govahiyar.ir/wp-content/uploads/2021/04/Capture-min.jpg",
    )

    private val correctAnswers = listOf(
        " الف. ورود ممنوع",
        " ج. شیب سرازیری 12 درصد ",
        " عبور موتور سیکلت ممنوع",
        " الف. حداکثر سرعت در خط های عبور ",
        " ج. پایان تمام محدودیت ها ",
        " ب. حداقل سرعت 30 کیلومتر  ",
        " د. پیچ های پی در پی اولین پیچ به راست  ",
        " ج. بوق زدن ممنوع  ",
        " د.  گردش به راست ممنوع ",
        " ب. ابتدای منطقه مسکونی  ",

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
        intent.putExtra("examId", "AzmonT2")
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
