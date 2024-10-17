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


//20

class Azmon12 : AppCompatActivity() {
    private lateinit var binding: ActivityAzmonBinding
    private var currentQuestionIndex = 0
    private var questionCounter = 1 // شمارنده سوالات

    private val questions = listOf(

        "#1. کدام گزینه در مورد گردش به چپ در راههای یک طرفه درست است؟"
                to listOf(
            " 1- رانندگان موظفند وسیله نقلیه را به منتهی الیه طرف راست خیابان هداست و سپس به چپ گردش کنند. ",
            " 2- گردش به چپ در خیابان یک طرفه ممنوع است.",
            " 3- رانندگان موظفند وسیله نقلیه را به منتهی الیه طرف چپ خیابان هدایت و سپس به چپ گردش کنند. ",
            " 4- باید منتظر بمانند تا خیابان خلوت شود."
        ),

        "#2. در علائم عبور و مرور راهنما برای مناطق تفریحی و فرهنگی و گردشگری به چه رنگی مشخص شده است؟"
                to listOf(
            " 1- سبز",
            " 2- قهوه ای",
            " 3- زرد",
            " 4- نارنجی "
        ),

        "#3. حداکثر میزان سرعت در خیابانهای شریانی اصلی چند کیلومتر بر ساعت است؟"
                to listOf(
            " 1- 50 کیلومتر در ساعت ",
            " 2- 60 کیلومتر در ساعت ",
            " 3- 30 کیلومتر در ساعت ",
            " 4- 70 کیلومتر در ساعت "
        ),

        "#4. مفهوم تابلوی شکل زیر چیست؟"
                to listOf(
            " 1- طول محدوده خطر ",
            " 2- افزایش خطوط عبور",
            " 3- کاهش خطوط عبور ",
            " 4- بسته بودن یکی از خطوط عبور "
        ),

        "#5. هر باری که برروی باربند سقف اتومبیل حمل می شود باید؟"
                to listOf(
            " 1- زمانیکه نیاز باشد حمل شود.",
            " 2- با یک پوشش پلاستیکی پوشیده شود. ",
            " 3- محکم بسته شود.",
            " 4- تا حد امکان سبک باشد. "
        ),

        "#6. هنرجوی گرامی مفهوم این تابلو چیست؟"
                to listOf(
            " 1- پایان منطقه محدودیت سرعت",
            " 2- منطقه محدودیت سرعت",
            " 3- حداقل سرعت 30 کیلومتر",
            " 4- حداکثر سرعت 30 کیلومتر"
        ),

        "#7. سطح روغن موتور را با چه وسیله ای می توان کنترل نمود؟"
                to listOf(
            " 1. فیلتر روغن",
            " 2. در مخزن روغن",
            " 3. سیم روغن (گیج روغن)",
            " 4. پمپ روغن "
        ),

        "#8. مفهوم تابلوی تصویر زیر چیست؟"
                to listOf(
            " 1- پایان شهر",
            " 2- پایان تمام محدودیت ها ",
            " 3- خیابان اصلی ",
            " 4- پایان خیابان اصلی"
        ),

        "#9. کدامیک از موارد زیر در خصوص نقاط کور در وسایل نقلیه صحیح نمی باشد؟"
                to listOf(
            " 1. موتور سیکلتها دارای نقاط کور نمی باشند.",
            " 2. با تنظیم دقیق آئینه ها می توان میزان نقاط کور را کاهش داد. ",
            " 3. حتی با تنظیم دقیق آئینه ها نمی توان نقاط کور را در خودرها حذف نمود. ",
            " 4. میزان وسعت نقاط کور در خودروهای سنگین بیشتر از خودروهای سبک است."
        ),

        "#10. رانندگان وسیله نقلیه با دیدن چراغ چشمک زدن زرد در تقاطع و گذر گاه پیاده چه عملی را باید انجام دهند؟"
                to listOf(
            " 1- باید توقف نموده وپس از اطمینان از نبودن خطر تصادف عبور نمایند. ",
            " 2- با احتیاط و سرعت کم عبور نمایند.",
            " 3- توقف کامل نمایند در صورت نبودن خط ایست در فاصله 5 متری چراغ راهنمایی و رانندگی توقف نمایند. ",
            " 4- گزینه یک و سه درست است. "
        ),

        "#11. تحت چه شرایطی می توان در تقاطع ها سبقت گرفت؟"
                to listOf(
            " 1. اگر تقاطع خلوت باشد. ",
            " 2. اگر چراغ تقاطع چشمک زن باشد. ",
            " 3. سبقت در تقاطع ها ممنوع است. ",
            " 4. اگر مسیر یک طرفه باشد."
        ),

        "#12. تابلو به معنای فقط گردش به راست مجاز می باشد به چه شکل نمایش داده می شود؟"
                to listOf(
            " 1- دایره شکل با زمینه سفید ",
            " 2- دایره شکل با زمینه آبی",
            " 3- مثلث شکل با زمینه قرمز ",
            " 4- دایره شکل با زمینه قرمز "
        ),

        "#13. درباره محل پارک خودرو کدام گزینه غلط است؟"
                to listOf(
            " 1- پارک کردن در خطوط شطرنجی داخل تقاطع ممنوع است.",
            " 2- پارک کردن مقابل درب ورودی ساختمان منازل مجاز است. ",
            " 3- در محلی باید پارک کرد که مانع سایر استفاده کنندگان راه نباشد. ",
            " 4- پارک کردن در حریم تقاطع ها و گذرگاه های عابر پیاده ممنوع است."
        ),

        "#14. مفهوم تابلو شکل زیر چیست؟"
                to listOf(
            " 1- خط عبور برای گریز",
            " 2- بسته بودن یکی از خطوط عبور ",
            " 3- افزایش خطوط عبور ",
            " 4- کاهش خطوط عبور "
        ),

        "#15. مهم ترین عامل تصادفات رانندگی چیست؟"
                to listOf(
            " 1- شرایط جاده ",
            " 2- شرایط آب و هوایی ",
            " 3- اشتباه رانندگان",
            " 4- مسائل مکانیکی"
        ),

        "#16. معنی تابلوی شکل زیر چیست؟"
                to listOf(
            " 1- رعایت حق تقدم ",
            " 2- به تابلو ایست نزدیک میشوید ",
            " 3- خطر",
            " 4- به تابلو رعایت حق تقدم نزدیک می شوید "
        ),

        "#17. قانون و مقررات اولیه در هنگام حرکت کردن از منتها الیه سمت …………..می باشد مگر برای ………گرفتن"
                to listOf(
            " 1- راست - علامت دادن به راننده پشت سر ",
            " 2- چپ - سرعت ",
            " 3- راست - سبقت",
            " 4- چپ - سبقت "
        ),

        "#18. در تقاطع زیر حق تقدم را به ترتیب مشخص کنید."
                to listOf(
            " 1- آبی - نارنجی - قرمز ",
            " 2- قرمز - آبی - نارنجی ",
            " 3- نارنجی - قرمز - آبی ",
            " 4- قرمز - نارنجی - آبی "
        ),

        "#19. مفهوم تابلوی شکل بالا چیست؟"
                to listOf(
            " 1- تقاطع راه آهن با راه بند ",
            " 2- تقاطع راه آهن بدون راه بند",
            " 3- تقاطع مسیر قطار ",
            " 4- تقاطع جاده و راه آهن "
        ),

        "#20. فاصله زمانی ۲ ثانیه بین شما و وسیله نقلیه جلویی چه وقتی مناسب است؟"
                to listOf(
            " 1- زمین لیز و لغزنده باشد",
            " 2- هوا آفتابی و راه خشک ",
            " 3- هوای برفی و در حال بارش",
            " 4- هوای ابری و باران در حال بارش"
        ),

        "#21. مفهوم تابلوی شکل زیر چیست؟"
                to listOf(
            " 1- تقاطع فرعی و اصلی ",
            " 2- ورود به راه اصلی از راست",
            " 3- ورود به راه اصلی از چپ",
            " 4- تقاطع"
        ),

        "#22. در ۳ راه تا چه فاصله ای توقف ممنوع می باشد؟"
                to listOf(
            " 1- بیست متری",
            " 2- ده متری ",
            " 3- پانزده متری",
            " 4- پنج متری"
        ),

        "#23. کدام یک از علائم زیر بر فرمان پلیس مقدم می باشد؟"
                to listOf(
            " 1- تابلو های باز دارنده ",
            " 2- چراغ راهنما ",
            " 3- خط کشی زوجی ",
            " 4- هیچکدام "
        ),

        "#24. کدامیک از موارد زیر در خصوص استفاده از کمربند ایمنی صحیح تر است؟"
                to listOf(
            " 1- بستن کمربند ایمنی برای شرنشینان زیر 12 سال در ردیف جلو الزامی است.",
            " 2- بستن کمربند ایمنی برای سرنشینان ردیف جلو الزامی است.",
            " 3- بستن کمربند ایمنی برای سرنشینان ردیف عقب الزامی است. ",
            " 4- بستن کمربند ایمنی برای سرنشینان ردیف جلو و عقب الزامی است."
        ),

        "#25. معنی تابلوی شکل زیر چیست؟"
                to listOf(
            " 1- سمت چپ بن بست",
            " 2- بن بست ",
            " 3- سمت راست بن بست",
            " 4- خیابان بن بست"
        ),

        "#26. هنرجوی گرامی تصویر زیر بیانگر چیست؟"
                to listOf(
            " 1. تا 200 متر پارک کردن مجاز است ",
            " 2. طول ممنوعیت با محدودیت پارکینگ",
            " 3. فاصله از محل نصب تابلو تا شروع قسمت خطر ",
            " 4. رعایت فاصله کمتر از 200 متر ممنوع "
        ),

        "#27. معنی تابلوی شکل زیر چیست؟"
                to listOf(
            " 1- عبور موتور گازی ممنوع ",
            " 2- عبور موتور سیکلت ممنوع ",
            " 3- عبور دوچرخه ممنوع",
            " 4- عبور وسایل نقلیه موتوری ممنوع "
        ),

        "#28. درتقاطع شکل بالا حق تقدم را به ترتیب مشخص کنید."
                to listOf(
            " 1- مشکی - قرمز - زرد - آبی ",
            " 2- قرمز - مشکی - آبی یا زرد با هم ",
            " 3- قرمز - آبی - زرد - مشکی ",
            " 4- آبی - زرد - قرمز - مشکی "
        ),

        "#29. در گذرگاه پیاده که چراغ راهنمایی و رانندگی وجود نداشته باشد حق تقدم عبور با ………"
                to listOf(
            " 1. وسیله نقلیه ای که در مسیر مجاز حرکت می نماید. ",
            " 2. وسیله نقلیه ای که از سرعت بالایی برخوردار است. ",
            " 3. وسیله نقلیه ای که به طور مستقیم حرکت می کند. ",
            " 4. پیادگان است. "
        ),

        "#30. هنرجوی گرامی در تقاطع زیر حق تقدم را مشخص کنید."
                to listOf(
            " 1. اتوبوس - کامیون - سواری - دوچرخه ",
            " 2. دوچرخه - اتوبوس - کامیون - سواری ",
            " 3. کامیون - سواری - اتوبوس - دوچرخه ",
            " 4. سواری - کامیون - اتوبوس - دوچرخه "
        ),



        )
    private val questionImages = listOf(

        "https://test-drive.ir/wp-content/uploads/2022/03/43622687.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/66585353.jpg",
        "https://th.bing.com/th/id/OIG2.MQvQoRn6P2g6Us04MF7Q?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/688659443.jpg",
        "https://th.bing.com/th/id/OIG4.dXDggjFIXUrY7nPogsa6?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/6663883722.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/03/787889474.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG3..dgtSzAMOMxTSB2FnW8u?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/04/45457878.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/457686598.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/03/63777278322.jpg",
        "https://th.bing.com/th/id/OIG2.i687kUcwvFRVpjNLs1pE?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/996084848.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/05/534673757575.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/78648943438.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/03/48788544458.jpg",
        "https://th.bing.com/th/id/OIG1.KxF_g4ydHPJKqY79oUdh?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG1.UedjAP963PSK7tXWlYxS?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.GMcKqKLSRxn2j2Nam8m0?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/77733443287.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/745853337.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/8844334.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/887457644.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/05/644878843.jpg"
    )
    private val correctAnswers = listOf(

        " 3- رانندگان موظفند وسیله نقلیه را به منتهی الیه طرف چپ خیابان هدایت و سپس به چپ گردش کنند. ",
        " 2- قهوه ای",
        " 2- 60 کیلومتر در ساعت ",
        " 2- افزایش خطوط عبور",
        " 3- محکم بسته شود.",
        " 1- پایان منطقه محدودیت سرعت",
        " 3. سیم روغن (گیج روغن)",
        " 3- خیابان اصلی ",
        " 1. موتور سیکلتها دارای نقاط کور نمی باشند.",
        " 2- با احتیاط و سرعت کم عبور نمایند.",
        " 3. سبقت در تقاطع ها ممنوع است. ",
        " 2- دایره شکل با زمینه آبی",
        " 2- پارک کردن مقابل درب ورودی ساختمان منازل مجاز است. ",
        " 4- کاهش خطوط عبور " ,
        " 3- اشتباه رانندگان",
        " 4- به تابلو رعایت حق تقدم نزدیک می شوید " ,
        " 3- راست - سبقت",
        " 4- قرمز - نارنجی - آبی " ,
        " 1- تقاطع راه آهن با راه بند ",
        " 2- هوا آفتابی و راه خشک ",
        " 1- تقاطع فرعی و اصلی ",
        " 3- پانزده متری",
        " 4- هیچکدام " ,
        " 4- بستن کمربند ایمنی برای سرنشینان ردیف جلو و عقب الزامی است." ,
        " 3- سمت راست بن بست",
        " 3. فاصله از محل نصب تابلو تا شروع قسمت خطر ",
        " 2- عبور موتور سیکلت ممنوع ",
        " 2- قرمز - مشکی - آبی یا زرد با هم ",
        " 4. پیادگان است. " ,
        " 3. کامیون - سواری - اتوبوس - دوچرخه ",














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
        intent.putExtra("examId", "Azmon12")
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


