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


class Azmon8 : AppCompatActivity() {
    private lateinit var binding: ActivityAzmonBinding
    private var currentQuestionIndex = 0
    private var questionCounter = 1 // شمارنده سوالات

    private val questions = listOf(

        "#1. بوستر ترمز نیروی پدال ترمز را ...... داده و زمان واکنش سیستم ترمز را ...... میدهد."
                to listOf(
            " 1- کاهش - افزایش",
            " 2- انتقال - کاهش",
            " 3- افزایش - کاهش",
            " 4- افزایش - انتقال"
        ),

        "#2. صدای بوق اتومبیل باید از چه فاصله ای شنیده شود؟"
                to listOf(
            "  1- هفتاد متری",
            " 2- صد و ده متری",
            "  3- پنجاه متری",
            " 4- نود متری"
        ),

        "#3. حرکت با دنده خلاص در سر پایینی چه خطراتی را در پی خواهد داشت؟"
                to listOf(
            " 1- موجب سرعت گرفتن خودرو می شود و دنده سخت جا می رود.",
            " 2- ترمز گرفتن و هدایت خودرو سخت می شود.",
            "  3- سیستم کلاج آسیب می بیند.",
            " 4- موارد 1 و 2 "
        ),

        "#4. حداقل فاصله ای که رانندگان وسایل نقلیه بایستی بتوانند اشیا و اشخاص را به وضوح ببینند چقدر است؟"
                to listOf(
            "  1- دویست و پنجاه متری",
            " 2- صد متری ",
            "  3- دویست متری",
            " 4- صد و پنجاه متری "
        ),

        "#5. در خیابان شریان فرعی در شهرها، حداکثر سرعت چند کیلومتر در ساعت است؟"
                to listOf(
            " 1- 60 کیلومتر در ساعت",
            "  2- 50 کیلومتر در ساعت",
            "  3- 70 کیلومتر در ساعت",
            "  4- 30 کیلومتر در ساعت"
        ),

        "#6. چنانچه راه خشک و هوا خوب باشد فاصله بین دو خودرو چقدر باید باشد؟"
                to listOf(
            " 1- یک متر",
            "  2- شمارش از یک تا دو",
            " 3- فاصله دو ثانیه",
            " 4- با استفاده از ساعت اندازه می گیریم "
        ),

        "#7. منظور از چرخ های محرک چیست؟"
                to listOf(
            " 1- چرخ هایی هستند که بر اثر حرکت خودرو دوران می کنند. ",
            " 2- چرخ های محرک همان چرخ های هرزگرد می باشند.",
            " 3- چرخ هایی که قدرت و نیرویی پشت آن ها وجود ندارند.",
            "  4-چرخ هایی هستند که خودرو را به حرکت در می آورند."
        ),

        "#8. در جاده خشک با سرعت ۶۰ کیلومتر در حال رانندگی هستید فاصله شما با اتومبیل جلویی چقدر باید باشد؟"
                to listOf(
            "  1- دو طول اتومبیل",
            " 2- سه طول اتومبیل",
            " 3- چهار طول اتومبیل",
            " 4- پنج طول اتومبیل "
        ),

        "#9. مفهوم این تابلو چیست؟"
                to listOf(
            "  1- پیچ به راست",
            " 2- دور زدن به راست ",
            " 3- دور زدن ممنوع",
            " 4- گردش به چپ ممنوع "
        ),

        "#10. هنرجوی گرامی مفهوم تابلو بالا چیست؟"
                to listOf(
            " 1- با رعایت احتیاط می توان از تقاطع عبور نمود.",
            "  2- درباره خراب بودن چراغ راهنمایی هشدار می دهد.",
            "  3- درباره وجود چراغ راهنمایی هشدار می دهد.",
            " 4- عبور از چراغ قرمز ممنوع است. "
        ),

        "#11. در کدامیک از موارد زیر بایستی از رانندگی خودداری نمود؟"
                to listOf(
            " 1- نداشتن جعبه کمک های اولیه در داخل خودور ",
            " 2- هنگام بارندگی شدید",
            " 3- سوختن چراغ داخل خودرو ",
            "  4- عصبانیت شدید در شرایط جسمی نامناسب"
        ),

        "#12. محدودیتهای حرکت و سرعت در جاده بوسیله ….. تعیین می گردد."
                to listOf(
            " 1- رانندگان",
            " 2- علائم و تابلوها",
            " 3- سازمان راهداری",
            " 4- افراد صلاحیت دار"
        ),

        "#13. در این تصویر حق تقدم وسایل نقلیه به ترتیب از راست به چپ کدام است؟"
                to listOf(
            " 1- کامیون - سواری قرمز - سواری سفید ",
            " 2- سواری قرمز - کامیون - سواری سفید ",
            " 3- سواری سفید - سواری قرمز - کامیون ",
            " 4- سواری قرمز - سواری سفید - کامیون "
        ),

        "#14. یکی از مهم ترین مشکلات زیست محیطی کلان شهرهای کشور کدام است؟\n"
                to listOf(
            "  1- آلودگی هوا ناشی از مصارف خانگی",
            "  2- آلودگی هوا ناشی از صنایع",
            " 3- آلودگی هوا ناشی از تردد روزانه خودرو",
            " 4- سیستم حمل و نقل پایدار شهری"
        ),

        "#15. مفهوم این تابلو چیست؟"
                to listOf(
            "  1- پارکینگ",
            " 2- پارک سوار ",
            " 3- محدوده پارک آزاد",
            " 4- محدوده توقف ممنوع در طول شبانه روز"
        ),

        "#16. خودرو دیفرانسیل جلو است یعنی چه ؟"
                to listOf(
            " 1- چرخ های عقب محرک هستند. ",
            "  2- چرخ های جلو محرک هستند.",
            " 3- ترمزد چرخهای جلو قوی تر است ترمز چرخهای عقب قوی تر است. ",
            " 4- هیچ کدام"
        ),

        "#17. فشردن پدال کلاچ باعث کدام یک از موارد زیر می شود ؟"
                to listOf(
            " 1- انتقال قدرت میان موتور و گیربکس قطع می شود.",
            " 2- انتقال قدرت میان جعبه دنده و موتور می شود. ",
            " 3- انتقال قدرت میان پدال گاز و موتور قطع می شود.",
            " 4- انتقال قدرت میان دیفرانسیل و گیربکس قطع می شود."
        ),

        "#18. تسمه دینام قدرت کدام قسمت را به دینام منتقل می کند ؟"
                to listOf(
            " 1- میل لنگ ",
            " 2- پیستون ",
            "  3- دینام",
            " 4- دیفرانسیل"
        ),

        "#19. سیستم اگزوز قادر به کاهش کدام آلودگی ها است؟"
                to listOf(
            " 1- آلودگی هوا",
            " 2- آلودگی صوتی",
            " 3- هیچ کدام",
            "  4- مورد یک و دو"
        ),

        "#20. معنی تابلوی تصویر بالا چیست؟"
                to listOf(
            " 1- پایان سبقت ممنوع برای کامیون",
            " 2- پایان سبقت ممنوع",
            "  3- سبقت برای کامیون ممنوع",
            "  4- پایان تمام محدودیت ها"
        ),

        "#21. عنوان تابلو بالا چیست؟"
                to listOf(
            "  1- تقاطع راه آهن با راهبند",
            " 2- خطر سقوط در آب",
            "  3- راه لغزنده",
            "  4- پل متحرک"
        ),

        "#22. طریقه صحیح گرفتن فرمان چیست؟"
                to listOf(
            "  1- ساعت ۲ و ۵",
            "  2- ساعت ۱۲ و ۳",
            "  3- ساعت ۱ و ۵",
            "  4- ساعت ۳ و ۹"
        ),

        "#23. استفاده از ترمز دستی در سرعتهای با چه خطری را به وجود می آورد ؟"
                to listOf(
            "  1- از دست رفتن پایداری خودرو و فراهم شدن شرایط واژگونی",
            " 2- خودرو به شدت سرعتش کاسته می شود.",
            "  3- مسافت توقف کاهش می یابد.",
            " 4- در موارد ضروری نیاز است."
        ),

        "#24. در راه های بیرون شهر، حداکثر سرعت مجاز در آزادراه ها برای کامیونت چند کیلومتر در ساعت است؟"
                to listOf(
            " 1- 100 کیلومتر در ساعت ",
            " 2- 90 کیلومتر در ساعت",
            "  3- 120 کیلومتر در ساعت",
            " 4- 110 کیلومتر در ساعت"
        ),

        "#25. عنوان این تابلو چیست؟"
                to listOf(
            "  1- جاده باریک می شود.",
            " 2- راه باریک",
            " 3- ورودی خیابان",
            "  4- راه از چپ باریک می شود."
        ),

        "#26. کدام مورد زیر از عوامل افزایش ایمنی در شبکه معابر و راه های کشور می باشد؟"
                to listOf(
            " 1- تغییر ناگهانی مسیر حرکت به چپ در راه های فاقد خط کشی",
            " 2- رعایت حق تقدم عبور هنگام رانندگی",
            " 3- تغییر ناگهانی مسیر حرکت به راست در راه های فاقد خط کشی ",
            "  4- حرکت با سرعتی بیش از سرعت تعیین شده در شبکه راه ها"
        ),

        "#27. عنوان تابلو بالا چیست؟"
                to listOf(
            " 1- تقاطع با راه آهن",
            " 2- تقاطع فرعی و اصلی",
            " 3- ورود به راه اصلی از راست ",
            " 4- تقاطع "
        ),

        "#28. در تقاطع بالا حق تقدم عبور را مشخص کنید."
                to listOf(
            " 1- آبی - قرمز - زرد ",
            " 2- زرد - قرمز - آبی ",
            " 3- قرمز - زرد - آبی ",
            "  4- زرد - آبی - قرمز"
        ),

        "#29. کدامیک جز وظایف سیستم تعلیق نمی باشد؟"
                to listOf(
            " 1- حفظ تعادل و پایداری خودرو هنگام عبور از پستی و بلندی ",
            "  2- ممانعت از واژگونی خودرو در هنگام عبور از پیچ ها",
            "  3- کنترل قدرت و شتاب خودرو",
            "  4- تامین راحتی راننده"
        ),

        "#30. ایستادن یا توقف وسایل نقلیه در کدام یک از محلهای زیر ممنوع است؟"
                to listOf(
            " 1- در فاصله 20 متری میدان یا تقاطع یا سه راهها یا تقاطع راه آهن",
            " 2- پیاده رو و گذرگاه پیاده ",
            " 3- از 50 متری ورودی مراکز آتش نشانی",
            " 4- از 50 متری ورودی مراکز فوریت های پزشکی"
        ),



        )
    private val questionImages = listOf(

        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.fKbD1I2fjZZmbiUQINrW?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG4.W4rcU9w93HGBAm6r5eV8?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG4.7kfrKJZyXMWFSNwi5Tt8?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG4.R.yky91Byu182yRKhHLN?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/658433223.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/884332252.jpg",
        "https://th.bing.com/th/id/OIG1.u0O3pa88VUZFlQOTmXF9?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/05/5346878.jpg",
        "https://th.bing.com/th/id/OIG3.JMBoiaunTERXxo2dh84C?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/0009768.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/03/886585.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/8889484686.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/434353.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/43645378.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/03/685599644334.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/03/6856856995.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/05/4334435747.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG3.1sURotXoi8eOVMKuf__U?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
    )

    private val correctAnswers = listOf(

        " 3- افزایش - کاهش",
        "  1- هفتاد متری",
        " 4- موارد 1 و 2 " ,
        " 4- صد و پنجاه متری " ,
        "  2- 50 کیلومتر در ساعت",
        " 3- فاصله دو ثانیه",
        "  4-چرخ هایی هستند که خودرو را به حرکت در می آورند." ,
        " 3- چهار طول اتومبیل",
        " 4- گردش به چپ ممنوع ",
        "  3- درباره وجود چراغ راهنمایی هشدار می دهد.",
        "  4- عصبانیت شدید در شرایط جسمی نامناسب",
        " 2- علائم و تابلوها",
        " 4- سواری قرمز - سواری سفید - کامیون ",
        " 3- آلودگی هوا ناشی از تردد روزانه خودرو",
        " 3- محدوده پارک آزاد",
        "  2- چرخ های جلو محرک هستند.",
        " 1- انتقال قدرت میان موتور و گیربکس قطع می شود.",
        " 1- میل لنگ ",
        "  4- مورد یک و دو",
        " 1- پایان سبقت ممنوع برای کامیون",
        "  4- پل متحرک",
        "  4- ساعت ۳ و ۹",
        "  1- از دست رفتن پایداری خودرو و فراهم شدن شرایط واژگونی",
        " 4- 110 کیلومتر در ساعت" ,
        "  4- راه از چپ باریک می شود." ,
        " 2- رعایت حق تقدم عبور هنگام رانندگی",
        " 4- تقاطع ",
        "  4- زرد - آبی - قرمز",
        "  3- کنترل قدرت و شتاب خودرو",
        " 2- پیاده رو و گذرگاه پیاده ",


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
            .thumbnail(Glide.with(this).load(questionImages[currentQuestionIndex]).diskCacheStrategy(
                DiskCacheStrategy.ALL))
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
        intent.putExtra("examId", "Azmon8")
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


