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


class Azmon6 : AppCompatActivity() {
    private lateinit var binding: ActivityAzmonBinding
    private var currentQuestionIndex = 0
    private var questionCounter = 1 // شمارنده سوالات

    private val questions = listOf(

        " #1. در هنگام مشاهده چراغ زرد چشمک زن چه اقدامی باید انجام داد؟"
                to listOf(" 1- با مشاهده چنین چراغی ایست کامل می کنیم.", "  2- عبور می کنیم و احتیاط لازم نیست.", "  3- قبل از خط ایست ویا گذرگاه عابر پیاده توقف کامل می کنیم.", "  4- از سرعت خود کم کرده و با احتیاط عبور کرده ودر ضرورت توقف می کنیم."),

        "#2. هنرجوی گرامی در تقاطع شکل بالا حق تقدم عبور را مشخص کنید. ( آمبولانس در حال ماموریت است) "
                to listOf(" 1- آمبولانس - زرد - سبز - بنفش", "  2- آمبولانس - سبز - بنفش - زرد", " 3- آمبولانس - بنفش - زرد - سبز", "  4- آمبولانس - زرد و بنفش همزمان - سبز"),

        "#3. کدام یک از گزینه های زیر روش صحیح ترمز کردن است ؟"
                to listOf("  1- ابتدا به آرامی ترمز کرده سپس با ترمز دستی اتومبیل را نگه می داریم.", "  2- ابتدا به آرامی ترمز کرده سپس دنده را در حالت خلاص می گذاریم.", "  3- ابتدا به آرامی ترمز کرده سپس پدال ترمز را بیشتر فشار دهید.", "  4- ابتدا سریع ترمز کرده سپس پدال ترمز را بیشتر فشار دهید."),

        " #4. هنگام نزدیک شدن به پیچ تند در شرایط جاده خیس و یخبندان کدامیک از موارد زیر باید رعایت شود؟"
                to listOf(" 1- تا حد امکان با دنده سنگین حرکت کنید. ", "  2- از حرکت سریع فرمان به طرفین خودداری کنید.", " 3- قبل از رسیدن به پیچ از سرعت خود بکاهید. ", "  4- همه موارد بالا"),

        " #5. پایین بودن سطح کدامیک از موارد زیر می تواند منجر به حادثه شود ؟"
                to listOf(" 1- گرمای موتور", " 2- روغن ترمز ", "  3- ضد یخ", "  4- آب باتری"),

        " #6. رانندگان وسایل نقلیه امدادی در چه مواردی مجاز به استفاده از سوت خطر آژیر و زنگ مخصوص و چراغ گردان می باشند ؟"
                to listOf("  1- جهت انجام فعالیت های مهم و فوری", " 2- جهت اطلاع به راننده جلویی", "  3- در هنگام مواجه با خطر", "  4- هیچ کدام"),

        " #7. کدامیک از عوامل زیر باعث افزایش مصرف سوخت می شود؟"
                to listOf("  1- پایین بودن پنجره خودرو در سرعت های بالا", "  2- سرعت زیاد و ترمز های پی در پی", " 3- استفاده از کولر در مسیر های سربالایی ", "  4- همه موارد"),

        " #8. از زمانی که راننده تشخیص می دهد نیاز به توقف و ایستادن دارد تا لحظه بکارگیری ترمزها چه نامیده می شود؟"
                to listOf(" 1- زمان حیاتی ", " 2- زمان هوشیاری", " 3- زمان توقف ", " 4- زمان واکنش "),

        "#9. مطابق قانون کدامیک از گزینه های زیر در برابر دیگر سرنشینان مسئولیت دارد؟ "
                to listOf(" 1- شخصی که دارای گواهینامه رانندگی است ", "  2- با تجربه ترین شخص", " 3- مسافر روی صندلی جلو ", " 4- راننده"),

        " #10. رانندگی با سرعتهای کمتر از……کیلومتر بر ساعت در آزادراهها ممنوع است مگر آنکه شرایط محیطی و یا تراکم ترافیکی ایجاب نماید.\n"
                to listOf(" 1- 70 ", " 2- 75 ", "  3- 65", "  4- 80"),

        " #11. راهی که ارتباط بین خیابان های جمع و پخش کننده و بزرگراه ها را برقرار می کند چه نام دارد؟"
                to listOf("  1- خیابان شریانی اصلی", " 2- خیابان های شریانی فرعی", " 3- معابر محلی ", " 4- خیابان های شریانی فرعی درجه یک"),

        " #12. کدامیک از موارد زیر مربوط به استفاده از باند سمت چپ در آزاد راه می باشد؟"
                to listOf("  1- برای سبقت از سایر وسایل نقلیه", " 2- برای شتاب گرفتن وسیله نقلیه ", "  3- برای رانندگی درسرعت های بیش از 90 کیلومتر در ساعت", " 4- برای انجام کارهای تعمیراتی "),

        " #13. کم بودن باد لاستیک بر کدام مورد ذیل تاثیر می گذارد ؟"
                to listOf("  1- ترمز کردن", " 2- تعویض دنده ", "  3- دیر ساییده شدن لاستیک", "  4- طولانی شدن عمر لاستیک"),

        " #14. اگر چراغ های جلوی وسیله نقلیه معیوب باشد …"
                to listOf(" 1- در روز حرکت می کنید و در شب آن را متوقف می نمایید. ", "  2- رانندگی با چنین وسیله ای ایرادی ندارد.", "  3- نقص فنی محسوب می شود و اجازه حرکت ندارید.", "  4- نقص فنی محسوب نمی شود."),

        "#15. چرا کامیون های بزرگ در زمان گردش به راست به سمت چپ متمایل می شوند ؟"
                to listOf(" 1- چون کشنده و یدک از هم جدا هستند", "  2- برای ممانعت از نزدیک شدن چرخهای عقب به جدول و رفتن روی آن", "  3- برای اینکه وسیله نقلیه ای از سمت راست نتواند عبور کند.", "  4- هیچکدام"),

        " #16. فاصله قبل و بعد از تونل ها برای سبقت گرفتن چند متر است؟"
                to listOf(" 1- چهل متر ", "  2- صد متر", "  3- پنجاه متر", "  4- صد و پنجاه متر"),

        "#17. کدامیک از موارد زیر علت تصادف با اتومبیل جلویی است؟ "
                to listOf(" 1- استفاده از نور پایین ", "  2- عدم توجه کافی به راه و نزدیک شدن بیش از حد به اتومبیل جلویی", " 3- نور زرد چراغ های مه شکن ", "  4- چراغ های راهنما"),

        " #18. کدامیک از اشکالات زیر خطر تصادف پشت سر را افزایش می دهد؟"
                to listOf(" 1- نداشتن معاینه فنی ", " 2- خرابی چراغ خطر دنده عقب", "  3- خرابی چراغ ترمز", "  4- خراب بودن ترمز"),

        "#19. از روی خط مقطع وسط خیابان …….. "
                to listOf(" 1- برای دور زدن و سبقت گرفتن می توان استفاده کرد.", " 2- می توان دور زد ولی نمی توان سبقت گرفت. ", "  3- فقط می توان سبقت گرفت.", " 4- فقط می توان دور زد. "),

        "#20. به هنگام خروج از پارک چه اعمالی باید انجام داد؟ "
                to listOf("  1- از آیینه ها استفاده کرده و برای کنترل نهایی سر را به قسمت چپ برگردانده و عقب را نگاه کنید.", " 2- اگر اتومبیل دیگری در مقابل اتومبیل شما پارک نشده به عقب برنگردید.", "  3- چنانچه لازم است سایر رانندگان آهسته برانند به آنها علامت دهید.", " 4- با استفاده از دست می توان علامت داد. "),

        " #21. سبقت گرفتن در کدامیک از موارد زیر ممنوع است؟"
                to listOf(" 1- در سر پیچ های تند و سربالایی با میدان دید کم", "  2- سبقت از وسیله ای که خود در حال سبقت است و یا 50 متر مانده به پیچ ها", "  3- برای اتوبوس و کامیون در معابر شهری", "  4- همه موارد بالا"),

        "#22. در زمانیکه قصد گردش به چپ و یا راست را دارید از چه فاصله ای باید راهنما بزنید؟ "
                to listOf(" 1- از فاصله 100متری", "  2- از فاصله 25متری", " 3- از فاصله 75متری ", " 4- از فاصله 50 متری"),

        " #23. هنگام سبقت گرفتن اعم از اینکه روز باشد یا شب، برای جلب توجه وسایل نقلیه پشت سر، انجام کدام مورد زیر ضروری است؟\n"
                to listOf(" 1- بوق زدن", "  2- تبدیل نور چراغ خودرو", "  3- اشاره با دست", "  4- زدن چراغ راهنما"),

        "#24. از شکل دایره معمولا برای چه نوع علائم عبور و مرور و تابلوهایی استفاده می شود؟"
                to listOf("  1- دستوری و خدمات", "  2- علائم راهنما", "  3- علامت هشدار دهنده برای خطر", "  4- دستوری یا بازدارنده"),

        " #25. مفهوم تابلوی شکل بالا چیست؟"
                to listOf(" 1- انحراف جاده به سمت چپ", " 2- جاده دو بانده می شود", " 3- پیش انتخاب مسیر", "  4- خروجی اتوبان از سمت چپ"),

        " #26. در این تقاطع حق تقدم با کدام اتومبیل است؟"
                to listOf(" 1- قرمز ", " 2- سیاه ", "  3- سفید", " 4- همه باهم"),

        " #27. کدامیک از موارد زیر در خصوص استفاده از کمربند ایمنی صحیح تر است؟"
                to listOf(" 1- بستن کمربند ایمنی برای سرنشینان زیر 12 سال در ردیف جلو الزامی است ", " 2- بستن کمربند ایمنی برای سرنشینان ردیف جلو الزامی است.", "  3- بستن کمربند ایمنی برای سرنشینان ردیف عقب الزامی است.", " 4- بستن کمربند ایمنی برای شرنشینان ردیف جلو و عقب الزامی است. "),

        " #28. هنگام پارک اتومبیل در کنار خیابان حق تقدم با وسیله ای است که:"
                to listOf("  1- وسیله ای که بزرگتر است.", "  2- ضمن حرکت با دنده عقب مشغول پارک کردن است.", " 3- با حرکت به جلو مشغول پارک است.", "  4- زودتر رسیده باشد."),

        "#29. مفهوم تابلوی شکل بالا چیست ؟"
                to listOf("  1- توقف ممنوع", "  2- توقف در روزهای فرد ممنوع", "  3- توقف در روزهای زوج هفته ممنوع", "  4- ایستادن ممنوع"),

        "#2. در علائم عبور و مرور تابلو مثلث متساوی الا ضلاع ( نشسته روی قاعده ) به معنی؟ "
                to listOf(" 1. تابلو ایست و خدمات ", " 2. صرفا برای تابلو ایست ", " 3. علامت هشدار دهنده برای خطر ", "  4. علامت دستوری و بازدارنده"),


        )
    private val questionImages = listOf(

        " https://th.bing.com/th/id/OIG3.tar8QTYeYRgZQDFLU5My?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        " https://test-drive.ir/wp-content/uploads/2023/10/657889564.jpg",
        "https://th.bing.com/th/id/OIG3.Omnk4F.QmyV7l1urMaSu?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn&cb=1717359857132",
        " https://th.bing.com/th/id/OIG4.Q4ccyCopu3oFzmH75IPh?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG1.YBNlpt.fP6xAjkbCUOVg?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        " https://th.bing.com/th/id/OIG4.GyQTEMWbefdOY0MKz.q3?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        " https://th.bing.com/th/id/OIG1.Rq8Un9rTZdPKkrxae3mW?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        " https://test-drive.ir/wp-content/uploads/2022/03/436347.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        " https://th.bing.com/th/id/OIG1.7xLZZRY1wAIT8OEYqydh?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG1.jrDw4zMeuAYEb3Qv3iKQ?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        " https://th.bing.com/th/id/OIG4._VtqoADqxTZJZhjFvmN8?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        " https://test-drive.ir/wp-content/uploads/2022/03/3346737.jpg",
        "https://th.bing.com/th/id/OIG4..r0gwFPc8JwFawu0vo2j?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        " https://test-drive.ir/wp-content/uploads/2022/03/65765789.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        " https://th.bing.com/th/id/OIG1.qx7EhlzjM.tUwcvIo17g?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        " https://th.bing.com/th/id/OIG1.qx7EhlzjM.tUwcvIo17g?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/66585353.jpg ",
        " https://test-drive.ir/wp-content/uploads/2022/03/99609564.jpg",
        " https://test-drive.ir/wp-content/uploads/2023/10/4534664.jpg",
        "https://th.bing.com/th/id/OIG4.bYoQ9reYr12YJzTXYG2N?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/03/236688.jpg ",
        " https://test-drive.ir/wp-content/uploads/2022/03/90955.jpg",
        " https://test-drive.ir/wp-content/uploads/2022/04/54343436737.jpg",

        )
    private val correctAnswers = listOf(

        "  4- از سرعت خود کم کرده و با احتیاط عبور کرده ودر ضرورت توقف می کنیم." ,
        "  4- آمبولانس - زرد و بنفش همزمان - سبز" ,
        "  3- ابتدا به آرامی ترمز کرده سپس پدال ترمز را بیشتر فشار دهید.",
        "  4- همه موارد بالا" ,
        " 2- روغن ترمز ",
        "  1- جهت انجام فعالیت های مهم و فوری",
        "  4- همه موارد" ,
        " 4- زمان واکنش " ,
        " 4- راننده" ,
        " 1- 70 ",
        "  1- خیابان شریانی اصلی",
        "  1- برای سبقت از سایر وسایل نقلیه",
        "  1- ترمز کردن",
        "  3- نقص فنی محسوب می شود و اجازه حرکت ندارید.",
        "  2- برای ممانعت از نزدیک شدن چرخهای عقب به جدول و رفتن روی آن",
        "  2- صد متر",
        "  2- عدم توجه کافی به راه و نزدیک شدن بیش از حد به اتومبیل جلویی",
        "  3- خرابی چراغ ترمز",
        " 1- برای دور زدن و سبقت گرفتن می توان استفاده کرد.",
        "  1- از آیینه ها استفاده کرده و برای کنترل نهایی سر را به قسمت چپ برگردانده و عقب را نگاه کنید.",
        "  4- همه موارد بالا" ,
        " 1- از فاصله 100متری",
        "  4- زدن چراغ راهنما" ,
        "  4- دستوری یا بازدارنده" ,
        " 3- پیش انتخاب مسیر",
        " 1- قرمز ",
        " 4- بستن کمربند ایمنی برای شرنشینان ردیف جلو و عقب الزامی است. " ,
        "  2- ضمن حرکت با دنده عقب مشغول پارک کردن است.",
        "  3- توقف در روزهای زوج هفته ممنوع",
        " 3. علامت هشدار دهنده برای خطر ",
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
        intent.putExtra("examId", "Azmon6")
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


