package com.example.firstprojectorg2.AzmonHa

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
import com.example.firstprojectorg2.R
import com.example.firstprojectorg2.databinding.ActivityAzmonBinding

class Azmon15 : AppCompatActivity() {
    private lateinit var binding: ActivityAzmonBinding
    private var currentQuestionIndex = 0
    private var questionCounter = 1

    private val questions = listOf(
        "#1. الکل و مواد مخدر بر کدام یک از موارد زیر تاثیر منفی می گذارد؟" to listOf(
            " الف. توانایی ذهنی، هوشیاری و دید ",
            " ب. تشخیص موقیعتهای خطرناک",
            " ج. تمرکز و کنترل سرعت",
            " د. تمام موارد بالا"
        ),
        "#2. تنها موقعی می توانید از تلفن همراه استفاده کنید که؟" to listOf(
            " الف. در یک محل و مکان امن توقف کرده باشید. ",
            " ب. وسیله نقلیه شما دارای دنده اتوماتیک باشید. ",
            " ج. نیاز به انجام یک تلفن اضطراری باشید. ",
            " د. فرمان رابایک دست بگیرید اتومبیل راهدایت کنید. "
        ),
        "#3. کدام مورد زیر هنگام رانندگی در شب احتمال خطر را افزایش می دهد؟" to listOf(
            " الف. محدودیت میدان دید راننده و خیره شدن به آن ",
            " ب. استفاده از چراغها ",
            " ج. استفاده از سایبان ",
            " د. استفاده از بوق "
        ),
    )

    private val questionImages = listOf(
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG4.lH0lqN_VPVqxAaAbjewb?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG1.lqf8aSGlwtu5JPv6flZK?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
    )

    private val correctAnswers = listOf(
        " د. تمام موارد بالا",
        " الف. در یک محل و مکان امن توقف کرده باشید. ",
        " الف. محدودیت میدان دید راننده و خیره شدن به آن ", " ج. 10 برابر ",
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
        binding = ActivityAzmonBinding.inflate(layoutInflater)
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
                // You might want to handle swipe right (previous question) if needed
                return true
            }
            return false
        }
    }

    private fun showResult() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("correctCount", correctCount)
        intent.putExtra("incorrectCount", incorrectCount)
        intent.putExtra("examId", "Azmon15")
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












//"#4. در شرایط یخبندان مسافت توقف ممکن است چند برابر افزایش یابد؟"
//to listOf(
//" الف. 5 برابر",
//" ب. 2 برابر ",
//" ج. 10 برابر ",
//" د. 3 برابر "
//),
//
//"#5. نام تابلوی تصویر زیر چیست؟"
//to listOf(
//" الف. تقاطع راه آهن",
//" ب. تقاطع راه آهن بدون راه بند",
//" ج. تقاطع مسیر قطار شهری",
//" د. تقاطع راه اهن با قطار شهری "
//),
//
//"#6. در تصویر زیر حق تقدم وسایل نقلیه به ترتیب از راست به چپ کدام است؟"
//to listOf(
//" الف. آبی - سبز - قرمز - زرد",
//" ب. زرد - قرمز - آبی - سبز ",
//" ج. سبز - زرد - قرمز - آبی",
//" د. زرد و قرمز همزمان - سبز - آبی "
//),
//
//"#7. برای کاستن سرعت هنگامی که وسیله نقلیه فاقد چراغ راهنما باشد باید؟"
//to listOf(
//" الف. دست راست را به طرف پایین نگهداشت.",
//" ب. دست چپ را به طرف بالا نگهداشت. ",
//" ج. دست چپ را به طور افقی نگهداشت. ",
//" د. دست چپ را به طور افقی از بالا به پایین حرکت داد. "
//),
//
//"#8. در شرایط جاده خیس و یخبندان در حال رانندگی هستید کدام مورد باید هنگام نزدیک شدن به پیچ تند رعایت شود؟\n"
//to listOf(
//" الف. هنگام رسیدن به پیچ با سرعت بیشتری برانید. ",
//" ب. قبل از رسیدن به پیچ از سرعت خود بکاهید و تا حد امکان با دنده سنگین حرکت کنید. ",
//" ج. به آرامی ترمز دستی را بکشید. ",
//" د. در تمامی مدت کلاج را پایین نگه دارید. "
//),
//
//"#9. ما بین شما و اتومبیل جلویی فاصله دو ثانیه ای مناسب خواهد بود چنانچه؟"
//to listOf(
//" الف. هوا مرطوب و جاده خیس باشد.",
//" ب. هوا بارانی باشد.",
//" ج. هوا مه آلود باشد ",
//" د. وضعیت راه خشک و هوا خوب باشد. "
//),
//
//"#10. با سرعت مجاز در رانندگی هستید اتومبیل پشت سر شما در حال سبقت است آیا می توانید مانع سبقت او شوید؟"
//to listOf(
//"  الف. نه مگر اینکه انجام این کار خطر باشد.",
//" ب. بله برای اینکه راننده مذکور کار خطرناکی انجام می دهد.",
//" ج. نه به هیچ وجه ",
//" د. بله برای اینکه راننده مذکور قانون شکنی می کند. "
//),
//
//"#11. آیا رانندگان وسایل نقلیه امدادی در هنگام ماموریت مجاز به عبور از محل ممنوع می باشند؟"
//to listOf(
//" الف. در صورتی که موجب تصادف نشوند. ",
//" ب. بستگی به شرایط دارد.",
//" ج. در هر شرایطی مجاز هستند. ",
//" د. به علت خطرات موجود نمی توانند عبور ممنوع بروند. "
//),
//
//"#12. شکل مستطیل قائم معمولا برای چه نوع تابلوهایی استفاده می شود؟"
//to listOf(
//" الف. شروع و پایان حق تقدم مسیر ",
//" ب. راهنما و هشدار",
//" ج. دستوری و خدمات",
//" د. دستوری یا بازدارنده"
//),
//
//"#13. معنی تابلوی شکل زیر چیست ؟"
//to listOf(
//" الف. توقف ممنوع",
//" ب. ایستادن ممنوع ",
//" ج. منطقه ایستادن ممنوع",
//" د. عبور ممنوع "
//),
//
//"#14. از مثلث متساوی الاضلاع (نشسته روی قاعده) معمولا برای چه نوع تابلوهایی استفاده می شود؟"
//to listOf(
//" الف. دستوری و خدمات",
//" ب. علائم راهنما ",
//" ج. علامت هشدار دهنده برای خطر ",
//" د. دستوری یا بازدارنده"
//),
//
//"#15. حق تقدم عبور در تقاطع بالا را مشخص کنید."
//to listOf(
//" الف. قرمز - آبی - سبز ",
//" ب. آبی - سبز - قرمز ",
//" ج. سبز - قرمز - آبی ",
//" د. آبی - قرمز- سبز "
//),
//
//"#16. حداکثر سرعت در جاده های اصلی(برون شهری)؟"
//to listOf(
//" الف. در روز 80 کیلو متر در شب 90 کیلومتر ",
//" ب. در روز 110 کیلومتر در شب 90 کیلومتر ",
//" ج. در روز 95 کیلومتر در شب 85 کیلومتر ",
//" د. در روز 90 کیلومتر در شب 85 کیلومتر "
//),
//
//"#17. نام تابلوی شکل بالا چیست؟"
//to listOf(
//" الف. شروع جدا کننده ",
//" ب. جریان همگرا ",
//" ج. پایان جدا کننده",
//" د. هدایت مسیر با جدا کننده "
//),
//
//"#18. شما قصد دارید رانندگی کنید ولی احساس بیماری می کنید چکار باید انجام دهید؟"
//to listOf(
//" الف. قبل از رانندگی داروی مناسبی بخورید. ",
//" ب. در صورت امکان طول سفر را کوتاه کنید.",
//" ج. شب زود بخوابید. ",
//" د. رانندگی نکنید."
//),
//
//"#19. در جاده ای در حال حرکت هستید احساس خستگی می کنید چکار می کنید؟"
//to listOf(
//" الف. به رانندگی ادامه می دهید ولی آهسته برانید.",
//" ب. سفر را هر چه سریعتر به انتها برسانید. ",
//" ج. در شانه خاکی جاده توقف نمایید.",
//" د. در اولین خروجی از جاده خارج شده استراحت کنید یا یک لیوان چایی بنوشید. "
//),
//
//"#20. مفهوم شکل بالا چیست؟"
//to listOf(
//" الف. پشت خط توقف بایستید ",
//" ب. فقط به راست گردش کنید ",
//" ج. فقط به چپ گردش کنید ",
//" د. مقابل پلیس بایستید"
//),
//
//"#21. کدام تابلو بالا بیانگر کاهش خطوط عبور می باشد؟"
//to listOf(
//" الف. 2 ",
//" ب. 4 ",
//" ج. 3 ",
//" د. 1 "
//),
//
//"#22. کدام مورد است که به هنگام سبقت گرفتن از آنها باید فضای بیشتری در نظر گرفت؟"
//to listOf(
//" الف. وانت و تراکتور",
//" ب. موتورسیکلت و دوچرخه ",
//" ج. ماشین های مجاور و وسایل نقلیه سنگین ",
//" د. ماشین های حامل مسافر و ماشین های حامل بار"
//),
//
//"#23. نام تابلو شکل زیر چیست؟"
//to listOf(
//" الف. خیابان اصلی ",
//" ب. راه اصلی ",
//" ج. آزاد راه ",
//" د. جاده اصلی"
//),
//
//"#24. کدامیک به معنی توقف وسیله نقلیه ممنوع جز برای سوار و پیاده کردن مشروط به استقرار راننده در پشت فرمان است؟"
//to listOf(
//" الف. توقف",
//" ب. ایستادن ممنوع ",
//" ج. توقف مطلقا ممنوع",
//" د. توقف ممنوع "
//),
//
//"#25. عمر مفید کنیستر چقدر است؟"
//to listOf(
//" الف. حدود 200 هزار کیلومتر",
//" ب. حدود 300 هزار کیلومتر",
//" ج. حدود 2 سال",
//" د. حدود 10 سال "
//),
//
//"#26. نام تابلوی شکل زیر چیست؟"
//to listOf(
//" الف. فقط عبور دوچرخه ",
//" ب. عبور دوچرخه سوار ",
//" ج. عبور دوچرخه ممنوع",
//" د. محل عبور دوچرخه "
//),
//
//"#27. تبعیت از این تابلوها الزامی و در صورت عدم توجه، رانندگان مرتکب خلاف و نقض قانون می گردند:"
//to listOf(
//" الف. تابلوهای هشدار دهنده ",
//" ب. تابلوهای اخطاری ",
//" ج. تابلوهای آگاهی دهنده غیر دستوری ",
//" د. تابلوهای آگاهی دهنده دستوری "
//),
//
//"#28. مهمترین مزیت ترمز ضد قفل چیست؟"
//to listOf(
//" الف. افزایش قدرت و سرعت خودرو",
//" ب. حفظ هدایت پذیری خودرو و جلوگیری از لغزش ",
//" ج. کاهش مصرف سوخت ",
//" د. استهلاک کمتر تایرها"
//),
//
//"#29. کثیف بودن شیشه چراغ ها منجر به چه می شود؟"
//to listOf(
//" الف. سوختن لامپ ها ",
//" ب. کاهش شدت روشنایی ",
//" ج. عدم کار کردن نور بالا ",
//" د. از کار افتادن چراغ های راهنما "
//),
//
//"#30. نام تابلوی شکل بالا چیست؟"
//to listOf(
//"  1. کارگران مشغول کارند",
//" 2. عبور عابر ممنوع ",
//" 3. محل عبور کارگران",
//"  4. جاده در دست تعمیر"
//),


//"https://th.bing.com/th/id/OIG4.m45uzPe1exeyzFejctnb?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
//"https://test-drive.ir/wp-content/uploads/2022/03/7774334.jpg",
//"https://test-drive.ir/wp-content/uploads/2022/05/4435435.jpg",
//"https://test-drive.ir/wp-content/uploads/2022/03/547547886.jpg",
//"https://th.bing.com/th/id/OIG4.DBzQFI4uFISddU.P2LOD?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
//"https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
//"https://th.bing.com/th/id/OIG1.kO5g5K3bZ1iRWFbSEDZi?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
//"https://test-drive.ir/wp-content/uploads/2022/03/63782788.jpg",
//"https://test-drive.ir/wp-content/uploads/2022/03/66585353.jpg",
//"https://test-drive.ir/wp-content/uploads/2022/03/888774789.jpg",
//"https://test-drive.ir/wp-content/uploads/2022/03/4536458.jpg",
//"https://test-drive.ir/wp-content/uploads/2022/05/464889894.jpg",
//"https://th.bing.com/th/id/OIG4.K_LZAlVSQ_NIecau5Umd?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
//"https://test-drive.ir/wp-content/uploads/2022/03/88893447589.jpg",
//"https://th.bing.com/th/id/OIG2.eWbOUs4Y83PzuDksWWM4?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
//"https://th.bing.com/th/id/OIG3.0lDdsAebIoFC9A__WoJ_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
//"https://test-drive.ir/wp-content/uploads/2022/03/78787475784.jpg",
//"https://test-drive.ir/wp-content/uploads/2022/03/6547689545.jpg",
//"https://th.bing.com/th/id/OIG4.hpM93XAuf05pHX0RBNOQ?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
//"https://test-drive.ir/wp-content/uploads/2022/03/999086743.jpg",
//"https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
//"https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
//"https://test-drive.ir/wp-content/uploads/2022/03/6688378483.jpg",
//"https://test-drive.ir/wp-content/uploads/2022/03/457686598.jpg",
//"https://th.bing.com/th/id/OIG1.VlxZtuTGmYeGIaT3eLvD?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
//"https://th.bing.com/th/id/OIG1.qcSs47k9HUfZFH7P98Sq?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
//"https://test-drive.ir/wp-content/uploads/2022/04/343356878766.jpg"
//



//" ج. تقاطع مسیر قطار شهری",
//" د. زرد و قرمز همزمان - سبز - آبی ",
//" د. دست چپ را به طور افقی از بالا به پایین حرکت داد. ",
//" ب. قبل از رسیدن به پیچ از سرعت خود بکاهید و تا حد امکان با دنده سنگین حرکت کنید. ",
//" د. وضعیت راه خشک و هوا خوب باشد. ",
//" ج. نه به هیچ وجه ",
//" د. به علت خطرات موجود نمی توانند عبور ممنوع بروند. ",
//" ج. دستوری و خدمات",
//" ب. ایستادن ممنوع ",
//" ج. علامت هشدار دهنده برای خطر ",
//" د. آبی - قرمز- سبز ",
//" ج. در روز 95 کیلومتر در شب 85 کیلومتر ",
//" د. هدایت مسیر با جدا کننده ",
//" د. رانندگی نکنید.",
//" د. در اولین خروجی از جاده خارج شده استراحت کنید یا یک لیوان چایی بنوشید. ",
//" الف. پشت خط توقف بایستید ",
//" د. 1 ",
//" ب. موتورسیکلت و دوچرخه ",
//" ج. آزاد راه ",
//" د. توقف ممنوع ",
//" ج. حدود 2 سال",
//" ب. عبور دوچرخه سوار ",
//" د. تابلوهای آگاهی دهنده دستوری ",
//" ب. حفظ هدایت پذیری خودرو و جلوگیری از لغزش ",
//" ب. کاهش شدت روشنایی ",
//"  1. کارگران مشغول کارند",

