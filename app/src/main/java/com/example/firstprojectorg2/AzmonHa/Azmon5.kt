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


class Azmon5 : AppCompatActivity() {
    private lateinit var binding: ActivityAzmonBinding
    private var currentQuestionIndex = 0
    private var questionCounter = 1 // شمارنده سوالات

    private val questions = listOf(

        "#1. رانندگی با وسیله نقلیه ای که برگ معاینه فنی معتبر نداشته باشد؟"
                to listOf(
            "  1- فقط خاج از شهر",
            " 2- ممنوع است.",
            "  3- فقط داخل شهر مجاز است.",
            "  4- بلامانع است."
        ),

        "#2. اگر هنگام ترمز کردن ماشین به یک طرف کشیده شود مشکل از کجاست؟"
                to listOf(
            " 1- ترمزها به خوبی تنظیم نشده است. ",
            " 2- فرمان ماشین تنظیم نیست.",
            " 3- لاستیک ساییده شده است.",
            " 4- روغن ترمز کم شده است. "
        ),

        "#3. شما در هنگام رانندگی در شهر مجاز هستید حداکثر با سرعتی معادل …"
                to listOf(
            " 1- 50 کیلومتر در خیابان های شریانی اصلی 50 کیلومتر در خیابان های شریانی فرعی و 30 کیلومتر در معابر محلی و میادین حرکت کنید. ",
            " 2- 60 کیلومتر در خیابان های شریانی اصلی 50 کیلومتر در خیابان های شریانی فرعی و 30 کیلومتر در معابر محلی و میادین حرکت کنید. ",
            "  3- 60 کیلومتر در خیابان های شریانی اصلی 40 کیلومتر در خیابان های شریانی فرعی و 30 کیلومتر در معابر محلی و میادین حرکت کنید.\n",
            "  4- 50 کیلومتر در خیابان های شریانی اصلی 50 کیلومتر در خیابان های شریانی فرعی و 20 کیلومتر در معابر محلی و میادین حرکت کنید."
        ),

        "#4. در آزاد راهی لاستیک شما پنچر میشود چه می کنید؟"
                to listOf(
            "  1- در خط توقف اضطراری توقف می کنید.",
            "  2- در خط عبور توقف کرده و شروع به تعویض لاستیک می کنید.",
            " 3- در همان جا توقف کرده و تابلوی هشدار نصب کنید. ",
            "  4- چراغ اضطرار را روشن کرده و همان جا شروع به تعویض لاستیک می کنید."
        ),

        "#5. مهم ترین وسیله هشدار دهنده در خودرو چیست؟"
                to listOf(
            " 1- نور بالا ",
            " 2- چراغ نور پایین ",
            " 3- بوق ",
            " 4- چراغ های راهنما "
        ),

        "#6. در چه مواردی باید توقف کنیم؟"
                to listOf(
            "  1- زمانی که پلیس مدرسه عبور ایمن دانش آموزان دستور میدهد.",
            " 2- زمانی که با چراغ قرمز روبرو می شوید.\n ",
            " 3- قبل از رسیدن به تقاطع که فاقد علائم و مامور راهنمایی و رانندگی است.",
            " 4- همه موارد"
        ),

        "#7. نور چراغ کوچک جلو و خطر عقب باید از چند متری دیده شود؟"
                to listOf(
            "  1- صد متری",
            "  2- دویست متری",
            "  3- سیصد متری",
            " 4- صد و پنجاه متری "
        ),

        "#8. نور بالا خودرو باید طوری تنظیم شود که اشیا و اشخاص از چند متری قابل تشخیص باشند؟"
                to listOf(
            " 1- صد متری",
            " 2- صد و پنجاه متری ",
            "  3- دویست متری",
            "  4- سیصد متری"
        ),

        "#9. مسافت توقف در چه جاده هایی افزایش خواهد یافت؟"
                to listOf(
            "  1- جاده آغشته به قیر",
            "  2- جاده ای که شن و ماسه در سطح آسفالت وجود داشته باشد",
            " 3- شرایط آب و هوایی بد",
            "  4- تمامی موارد"
        ),

        "#10. درصورتی که لاستیک اتومبیل صاف باشد باعث می شود که هنگام ترمز کردن…."
                to listOf(
            " 1- اتومبیل فورا متوقف شود.",
            " 2- اتومبیل به موقع توقف ننموده و باعث تصادف می گردد.",
            " 3- تاثیری در کیفیت توقف ندارد.",
            " 4- هیچکدام"
        ),

        "#11. حداقل فاصله ای که رانندگان وسیله نقلیه بایستی از طریق آیینه ها پشت سرشان را رویت کنند چقدر است؟"
                to listOf(" 1- یک متر", "  2- هفتاد متری", " 3- پنجاه متر", " 4- پنج متر "),

        "#12. به گردش در آوردن آب داخل پوسته موتور و اطراف سیلندر وظیفه .... می باشد؟"
                to listOf("  1- رادیاتور", " 2- واتر پمپ", "  3- پروانه", "  4- ترموستات"),

        "#13. کلیدی ترین ابزار در کنترل خودرو چیست؟"
                to listOf("  1-کلاچ", "  2- پدال ترمز", " 3- پدال گاز ", " 4- غربیلک فرمان "),

        "#14. معاینه و آزمایش فنی وسایل نقلیه شامل چیست؟"
                to listOf(
            " 1- تشخیص اصالت",
            "2- سلامت زیست محیطی و فنی",
            " 3- تجهزاتی و ایمنی وسیله نقلیه",
            " 4- همه موارد"
        ),

        "#15. هنرجوی گرامی مفهوم این تابلو چیست؟"
                to listOf(" 1- قهوه خانه", "  2- خوابگاه", " 3- هتل", "  4- غذاخوری (رستوران)"),

        "#16. کدام یک به معنای راننده معلول است؟"
                to listOf(
            " 1- تابلو شماره 1",
            "  2- تابلو شماره 2",
            "  3- تابلو شماره 3",
            " 4-تابلو شماره 4"
        ),

        "#17. حق تقدم عبور را در این تقاطع به ترتیب مشخص نمایید."
                to listOf(
            " 1- آبی - زرد - قرمز",
            " 2- زرد - آبی - قرمز",
            "  3- آبی - قرمز - زرد",
            "  4- قرمز - زرد - آبی"
        ),

        "#18. مجموع مسافتی که در زمان واکنش طی می شود به علاوه مسافت ترمزها، چه نام دارد؟"
                to listOf(
            "  1- مسافت ترمز",
            "  2- مسافت توقف",
            "  3- مسافت واکنش",
            " 4- مسافت اضطراری"
        ),

        "#19. میزان سرعت و محدودیت سرعت در جاده ها و خیابان ها به چه صورت مشخص می شود؟"
                to listOf(
            " 1- سرعت مجاز بسته به نوع خودرو و مدل آن قابل تغییر است. ",
            "  2- با توجه به مواردی که GPS خودرو نشان می دهد.",
            "  3- با توجه به تابلوهای نصب شده و آیین نامه راهنمایی و رانندگی",
            "  4- با توجه به دستور متصدی جاده ها مشخص می شود."
        ),

        "#20. منظور از وسیله نقلیه طویل چیست؟"
                to listOf(
            " 1- وسیله نقلیه ای است که طول آن کمتر از 22/5 متر باشد.",
            " 2- وسیله نقلیه ای است که طول آن بیش از 12/5 متر باشد.",
            "  3- وسیله نقلیه ای است که طول آن بیش از 18/5 متر باشد.",
            "  4- هر نوع خودروی باربری"
        ),

        "#21. هنرجوی گرامی حق تقدم عبور را در تقاطع زیر مشخص کنید."
                to listOf(" 1- 2 - 1 - 3", "  2- 3 - 1 - 2", " 3- 1 - 3 - 2", "  4- 3 - 2 - 1"),

        "#22. کدامیک از موارد زیر کنترل شما را بر روی وسیله نقلیه کاهش می دهد؟"
                to listOf(
            " 1- ترمز کردن ",
            " 2- استفاده بیش از حد از دنده ",
            "  3- استفاده از دنده های سبک",
            "  4- مدت زیادی پای خود را روی کلاچ قرار دادن"
        ),

        "#23. چرا باید در سطح جاده های خیس برفی لغزنده باید از دنده سنگین استفاده شود؟"
                to listOf(
            " 1- جهت ترمز سریع ",
            "  2- جهت حرکت صحیح",
            " 3- جهت ممانعت از لغزش چرخ ها",
            "  4- جهت ممانعت از چپ و راست رفتن خودرو"
        ),

        "#24. منظور از علامت R بر روی دسته دنده چیست؟"
                to listOf(
            " 1- آماده حرکت عقب ",
            " 2- خلاص بودن دنده ",
            " 3- حالت پارک ",
            " 4- هیچکدام "
        ),

        "#25. در تنظیم صندلی عدم تسلط کافی بر تجهیزات کنترلی باعث می شود تا….."
                to listOf(
            " 1- واکنش و عکس العمل راننده در مقابل خطرات مناسب نباشد. ",
            " 2- واکنش و عکس العمل راننده با تاخیر زمانی بالا صورت می پذیرد. ",
            " 3- واکنش و عکس العمل راننده با سرعت صورت می پذرید. ",
            "  4- موارد 1 و 2"
        ),

        "#26. کدام نوع از خطوط پارک در این شکل نشان داده شده است؟"
                to listOf("  1- مورب", "  2- افقی", "  3- عمودی", " 4- هیچکدام "),

        "#27. کاربرد چراغ جانبی جلو چیست؟"
                to listOf(
            " 1- حضور وسیله نقلیه جلو را نشان می دهد. ",
            "  2- حضور وسیله نقلیه و طول آن را نشان می دهد.",
            "  3- حضور وسیله نقلیه و عرض آن را از سمت جلو نشان می دهد.",
            " 4- حضور وسیله نقلیه و عرض آن را از سمت عقب نشان می دهد. "
        ),

        "#28. انتقال نیرو در سیستم ترمز دستی توسط ….. انجام می شود."
                to listOf(" 1- اهرم ", " 2- سیم های فلزی ", " 3- روغن ترمز ", "  4- پدال"),

        "#29. رانندگان وسایل نقلیه موظفند برای رویت کلیه اشیاء و اشخاص در جاده ها از چراغ ….. استفاده نمایند."
                to listOf(
            " 1- چراغهای جانبی عقب ",
            "  2- نور پایین",
            "  3- چراغهای جانبی جلو",
            " 4- نور بالا "
        ),

        "#30. حداکثر میزان سرعت در جاده های فرعی برای انواع وسایل نقلیه در روز … کیلومتر و در شب … کیلومتر در ساعت می‌باشد"
                to listOf(" 1- روز 75- شب 70 ", " 2- روز 95-شب 85 ", "  3- روز 85 شب 75", "  4- روز 65 شب 55"),


        )
    private val questionImages = listOf(

        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG3.f4aGTkW1i_tP308OCrIU?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG3.WSujAf3l8jHewkeEWGsz?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG4.E8e5AVazunLLqIrMGpk8?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG3.4hIxjM7pW7kiYp0rkHvi?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG4.jGmyxL5PHS.FE3EVuPbO?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.CXjUrX1ptnyaeBGNRmup?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG3.latocTsEWfThnwO8BwAS?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.ktOY6jnEAHRlZdzFKeuE?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/6664289.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/000976865.jpg ",
        "https://test-drive.ir/wp-content/uploads/2022/03/8865643.jpg",
        "https://test-drive.ir/wp-content/uploads/2023/10/35677766.jpg",
        "https://th.bing.com/th/id/OIG2.ZiQrrjM3A5PpYtQ8sM6S?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/05/354377748.jpg",
        "https://th.bing.com/th/id/OIG1.SirRCV4nE3n55DOechIb?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG1.Zc723EZjRJ1dIDZrfPUl?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/34638590676.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/886456.jpg",
        "https://th.bing.com/th/id/OIG2.P3LPYKTHfj6Z0HADt_Km?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/43645378.jpg",
        "https://th.bing.com/th/id/OIG2.w8BvKiRT64P.dD_90duC?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.wYZKuwijtAblrnCuUEvb?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
    )

    private val correctAnswers = listOf(

        " 2- ممنوع است.",
        " 1- ترمزها به خوبی تنظیم نشده است. ",
        " 2- 60 کیلومتر در خیابان های شریانی اصلی 50 کیلومتر در خیابان های شریانی فرعی و 30 کیلومتر در معابر محلی و میادین حرکت کنید. ",
        "  1- در خط توقف اضطراری توقف می کنید.",
        " 3- بوق ",
        " 4- همه موارد",
        " 4- صد و پنجاه متری ",
        " 2- صد و پنجاه متری ",
        "  4- تمامی موارد",
        " 2- اتومبیل به موقع توقف ننموده و باعث تصادف می گردد.",
        "  2- هفتاد متری",
        " 2- واتر پمپ",
        " 4- غربیلک فرمان ",
        " 4- همه موارد",
        " 3- هتل",
        " 1- تابلو شماره 1",
        "  4- قرمز - زرد - آبی",
        "  2- مسافت توقف",
        "  3- با توجه به تابلوهای نصب شده و آیین نامه راهنمایی و رانندگی",
        " 2- وسیله نقلیه ای است که طول آن بیش از 12/5 متر باشد.",
        "  2- 3 - 1 - 2",
        "  4- مدت زیادی پای خود را روی کلاچ قرار دادن",
        " 3- جهت ممانعت از لغزش چرخ ها",
        " 1- آماده حرکت عقب ",
        "  4- موارد 1 و 2",
        "  2- افقی",
        "  3- حضور وسیله نقلیه و عرض آن را از سمت جلو نشان می دهد.",
        " 2- سیم های فلزی ",
        " 4- نور بالا " ,
        "  3- روز 85 شب 75",

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
        intent.putExtra("examId", "Azmon5")
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


