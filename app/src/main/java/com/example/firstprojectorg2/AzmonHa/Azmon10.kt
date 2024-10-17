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


class Azmon10 : AppCompatActivity() {
    private lateinit var binding: ActivityAzmonBinding
    private var currentQuestionIndex = 0
    private var questionCounter = 1 // شمارنده سوالات

    //18

    private val questions = listOf(

        "#1. روغن موتور اتومبیل هر چند وقت یکبار باید تعویض گردد؟"
                to listOf(
            " 1- در حدود 2 تا 5 هزار کیلومتر کارکرد",
            " 2- هرسه ماه یکبار",
            "  3- هر ماه یکبار",
            " 4- در حدود 8 تا 10 هزار کیلومتر کارکرد"
        ),

        "#2. علت سر خوردن خودرو در شرایط یخبندان کدام عامل می باشد؟"
                to listOf(
            " 1- اصطحکاک زیاد تایر با سطح جاده",
            "  2- دیر عمل نمودن ترمزها",
            " 3- سفت شدن ترمزها ",
            " 4- اصطحکاک کم تایر با سطح جاده"
        ),

        "#3. در زمان دیدن گیج روغن در چه حالت باید روغن مورد بررسی قرار گیرد؟"
                to listOf(
            " 1- سطح روغن از علامت حک شده روی گیج بالاتر باشد.",
            " 2- سطح روغن از علامت حک شده روی گیج پایین تر باشد.",
            "  3- سطح روغن بین علامت حک شده روی گیج باشد.",
            " 4- مورد 1 و 2"
        ),

        "#4. مفهوم این تصویر چیست؟"
                to listOf(
            " 1- خط ایست - رانندگان باید سرعت خود را به آرامی کاهش داده و سپس توقف نمایند.",
            " 2- خط توقف منقطع - رانندگان باید از سرعت خود کاسته و با رعایت احتیاط و حق تقدم عبور وارد راه اصلی شوند.",
            " 3- خط توقف - رانندگان باید توقف کامل نمایند و سپس با رعایت احتیاط عبور کنند.",
            " 4- خط ممتد ایست - معمولا در تقاطع ها کشیده می شود و رانندگان باید توقف کامل نمایند."
        ),

        "#5. روغن گیربکس اتومبیل چه زمان نیاز به کنترل دارد؟"
                to listOf(
            " 1- هر بار تعویض روغن",
            " 2- هر 100 هزار کیلومتر",
            " 3- یک بار در هفته",
            " 4- یک بار در ماه"
        ),

        "#6. از چند متری تقاطع راه آهن سبقت گرفتن ممنوع است؟"
                to listOf(
            " 1- محدودیت ندارد.",
            " 2- 100 متری",
            " 3- 150 متری",
            " 4- 50 متری"
        ),

        "#7. آب رادیاتور هر چند وقت یکبار نیاز به کنترل دارد؟"
                to listOf(
            " 1- یکبار در ماه",
            " 2- هفته ای یکبار",
            " 3- سه روز یکبار",
            "  4- هر روز"
        ),

        "#8. ضد یخ چه عملی در رادیاتور انجام می دهد؟"
                to listOf(
            " 1- جلوگیری از یخ زدگی آب رادیاتور",
            "  2- به تاخیر اندازی نقطه جوش آب",
            " 3- جلوگیری از زنگ زدگی رادیاتور و قطعات",
            " 4- تمام موارد"
        ),

        "#9. در مبحث مفهوم رنگ ها و علائم عبور و مرور، مفهوم کدام رنگ به معنی حرکات مجاز می باشد؟"
                to listOf(
            "  1. 4",
            " 2. 3 ",
            " 3. 1",
            " 4. 2"
        ),

        "#10. از کدام علامت آگاه سازی برای سبقت در شب استفاده می شود؟"
                to listOf(
            "  1. استفاده از بوق کوتاه",
            " 2. فقط تبدیل و تعویض نور چراغ بالا و پایین",
            " 3. تبدیل و تعویض نور چراغ بالا و پایین به همراه چراغ راهنما",
            "  4. استفاده از بوق بلند بطور متناوب"
        ),

        "#11. عمر تسمه تایم بسته به نوع خودرو می تواند بین ……..باشد ."
                to listOf(
            " 1- 50 تا 100 هزار کیلومتر",
            " 2- 40 تا 70 هزار کیلومتر",
            " 3- 60 تا 90 هزار کیلومتر",
            " 4- هیچکدام"
        ),

        "#12. هنرجوی گرامی حق تقدم عبور را در این تقاطع مشخص نمایید."
                to listOf(
            " 1- قرمز - نارنجی - آبی ",
            " 2- آبی - قرمز - نارنجی ",
            " 3- نارنجی - آبی - قرمز ",
            " 4- آبی - نارنجی - قرمز "
        ),

        "#13. چه زمانی تایر نیاز به تعویض دارد؟"
                to listOf(
            "  1- ارتفاع آج به کمتر از 1/5 میلی متر برسد.",
            " 2- ارتفاع آج به کمتر از 2 میلی متر برسد. ",
            "  3- ارتفاع آج به کمتر از 1 میلی متر برسد.",
            " 4- هر سال یکبار"
        ),

        "#14. محل تایرها باید هر…….تعویض نمود تا تمام آنها به طور یکسان تحت سایش قرار گیرد ."
                to listOf(
            "  1- هر 10 هزار کیلومتر",
            "  2- هر 5 هزار کیلومتر",
            " 3- هر 20 هزار کیلومتر",
            " 4- هر سه ماه یکبار"
        ),

        "#15. جمله دیر رسیدن بهتر از هرگز نرسیدن است چه مفهومی دارد؟"
                to listOf(
            " 1- عدم توجه به قوانین و مقررات ",
            "  2- رعایت کردن مربوط به پارک",
            " 3- با سرعت بالا حرکت کردن ",
            " 4- پرهیز از عجله و شتاب در رانندگی "
        ),

        "#16. تردد کدام نوع وسیله نقلیه در خط سوم آزاد راه ها ممنوع نیست؟"
                to listOf(
            " 1. اتوبوس",
            " 2. وانت ها",
            " 3. مینی بوس ",
            "  4. انواع بارکش ها با وزن بیش از 3/5 تن"
        ),

        "#17. در کدامیک از شرایط زیر برای توقف مسافت بیشتری نیاز است؟"
                to listOf(
            "  1- وقتی خودرو سنگین تر است یا شرایط بارانی و برفی است.",
            " 2- در شرایط آفتابی و روز روشن",
            " 3- وقتی خودرو سبک تر است.",
            " 4- وقتی بدون بار و مسافر در حال حرکت هستیم. "
        ),

        "#18. چه زمان سیستم هدایت و فرمان پذیری معیوب است؟"
                to listOf(
            " 1- در صورتی که فرمان دارای خلاصی نباشد.",
            " 2- خلاصی آن بیش از حد مجاز باشد.",
            "  3- خلاصی آن حدود 2 یا 3 سانتی متر باشد.",
            "  4- مورد 1 و 2"
        ),

        "#19. در باتری های آبی میزان آب باتری چه زمان مورد بررسی و کنترل قرار می گیرد؟"
                to listOf(
            " 1- هر 5 هزار کیلومتر",
            " 2- هر 10 هزار کیلومتر ",
            " 3- هر 20 هزار کیلومتر ",
            " 4- هر 15 هزار کیلومتر "
        ),

        "#20. به همراه چراغ چشمک زن قرمز معمولا کدام تابلوی راهنمایی و رانندگی نصب می گردد؟"
                to listOf(
            " 1- فقط عبور مستقیم مجاز ",
            " 2- گردش به چپ ممنوع ",
            " 3- گردش به چپ ممنوع ",
            " 4- ایست و یا رعایت حق تقدم"
        ),

        "#21. هنرجوی گرامی حق تقدم عبور را در این تقاطع مشخص نمایید."
                to listOf(
            " 1- سبز - آبی - نارنجی - قرمز ",
            " 2- قرمز - نارنجی - آبی - سبز ",
            " 3- سبز - قرمز - آبی - نارنجی",
            " 4- نارنجی - سبز - قرمز - آبی "
        ),

        "#22. تابلوای که مشاهده می کنید بیانگر چیست؟"
                to listOf(
            " 1- عبور اطفال",
            " 2- عبور پیاده ممنوع ",
            " 3- عبور عابر پیاده ",
            " 4- فقط عبور پیاده "
        ),

        "#23. پدال های مقابل پای راننده به ترتیب از راست به چپ عبارتند از ….\n"
                to listOf(
            "  1- کلاچ - گاز - ترمز",
            "  2- ترمز - گاز - کلاچ",
            " 3- گاز - ترمز - کلاچ",
            " 4- ترمز - کلاچ - گاز"
        ),

        "#24. سبقت چیست؟"
                to listOf(
            " 1- در جلوی خودرو دیگر حرکت نمودن",
            "  2- حرکت در جهت مسیر خودروهای دیگر و جلوتر از همه آن ها",
            " 3- حرکت در لاین مخالف و جلو افتادن از خودروهای دیگر",
            "  4- جلو افتادن و یا پیشی گرفتن از خودرو دیگری که در همان مسیر حرکت می کند."
        ),

        "#25. کدام چراغ راهنمایی معمولا در محل برخورد راه اصلی با راه فرعی نصب می گردد؟"
                to listOf(
            " 1- چراغ چشمک زن قرمز برای عبور ",
            " 2- چراغ چشمک زن قرمز برای ایست ",
            " 3- چراغ چشمک زن زرد " ,
            " 4- چراغ چشمک زن قرمز"
        ),

        "#26. کدام عوامل بر عملکرد راننده در حین رانندگی تاثیر دارد؟"
                to listOf(
            " 1- میزان دید راننده در شرایط آب و هوایی مختلف ",
            " 2- شرایط فیزیکی جاده ",
            " 3- عملکرد سایر رانندگان",
            " 4- تمام موارد بالا "
        ),

        "#27. کدامیک از موارد زیر در خصوص استفاده از تلفن همراه در حین رانندگی صحیح تر است؟"
                to listOf(
            " 1- هرگونه استفاده از تلفن همراه شامل صحبت کردن، ارسال پیامک، دیدن تصاویر و ...ممنوع است.",
            " 2- صرفا صحبت کردن با تلفن همراه ممنوع است. ",
            " 3- صرفا ارسال پیامک با تلفن همراه در حین رانندگی ممنوع می باشد. ",
            " 4- فقط خواندن پیامک از تلفن همراه در حین رانندگی ممنوع است. "
        ),

        "#28. در شهرها و مناطق مسکونی، در خیابان های شریان اصلی حداکثر سرعت چند کیلومتر در ساعت است؟"
                to listOf(
            "  1- 100 کیلومتر در ساعت",
            "  2- 95 کیلومتر در ساعت",
            " 3- 70 کیلومتر در ساعت",
            " 4- 60 کیلومتر در ساعت"
        ),

        "#29. کدام یک از تابلوهای بالا جریان همگرا می باشد؟"
                to listOf(
            " 1. 4 ",
            " 2. 3",
            " 3. 1",
            " 4. 2 "
        ),

        "#30. در زمستان و در هنگام برف و کولاک چه موردی در خصوص تایر خودرو باید رعایت شود؟"
                to listOf(
            " 1- باید از تایر یخ شکن یا زنجیر چرخ استفاده کرد. ",
            " 2- باید باد لاستیک را 1.5 برابر بیشتر کنید.",
            " 3- باید باد لاستیک را 1.5 برابر کمتر کنید. ",
            " 4- باید از تایرهایی با عمق عاج کمتر از 1.5 میلی متر استفاده نمود."
        ),


        )
    private val questionImages = listOf(

        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG3.BVPh_gqNX6lbfqtPKYlH?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2023/10/89767868.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG3.HganuacD61lVLmXSwfuY?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.kMFJ9G75l6AfnkQ5tmeV?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/04/346788655.jpg",
        "https://th.bing.com/th/id/OIG4.e0pYyAuNmbAS9J1H86_m?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/994500054.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/06/643645888.jpg",
        "https://th.bing.com/th/id/OIG3.f6_T4YlfWu_0BfxYXeaf?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG4.rLDz.354tmKoH_6Vg6.E?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG3.1RtK83PtZGNSncP_EvCL?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/06/53477.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/74533573878.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG3.y3erA799ifNPZXo_nROA?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG3.z3VaynJfw28JvrTH6FDP?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/04/44678885.jpg",
        "https://th.bing.com/th/id/OIG2.k.Ts3lAZ8WBxzAlwrGFQ?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
    )

    private val correctAnswers = listOf(

        " 4- در حدود 8 تا 10 هزار کیلومتر کارکرد",
        " 4- اصطحکاک کم تایر با سطح جاده",
        " 4- مورد 1 و 2",
        " 4- خط ممتد ایست - معمولا در تقاطع ها کشیده می شود و رانندگان باید توقف کامل نمایند.",
        " 1- هر بار تعویض روغن",
        " 4- 50 متری",
        " 2- هفته ای یکبار",
        " 4- تمام موارد",
        " 4. 2",
        " 3. تبدیل و تعویض نور چراغ بالا و پایین به همراه چراغ راهنما",
        " 3- 60 تا 90 هزار کیلومتر",
        " 4- آبی - نارنجی - قرمز ",
        " 2- ارتفاع آج به کمتر از 2 میلی متر برسد. ",
        "  2- هر 5 هزار کیلومتر",
        " 4- پرهیز از عجله و شتاب در رانندگی ",
        " 2. وانت ها",
        "  1- وقتی خودرو سنگین تر است یا شرایط بارانی و برفی است.",
        "  4- مورد 1 و 2",
        " 2- هر 10 هزار کیلومتر ",
        " 4- ایست و یا رعایت حق تقدم",
        " 4- نارنجی - سبز - قرمز - آبی ",
        " 4- فقط عبور پیاده ",
        " 3- گاز - ترمز - کلاچ",
        "  4- جلو افتادن و یا پیشی گرفتن از خودرو دیگری که در همان مسیر حرکت می کند." ,
        " 3- چراغ چشمک زن زرد " ,
        " 4- تمام موارد بالا ",
        " 1- هرگونه استفاده از تلفن همراه شامل صحبت کردن، ارسال پیامک، دیدن تصاویر و ...ممنوع است.",
        " 4- 60 کیلومتر در ساعت" ,
        " 3. 1",
        " 1- باید از تایر یخ شکن یا زنجیر چرخ استفاده کرد. ",


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
        intent.putExtra("examId", "Azmon10")
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


