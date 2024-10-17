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

class Azmon4 : AppCompatActivity() {
    private lateinit var binding: ActivityAzmonBinding
    private var currentQuestionIndex = 0
    private var questionCounter = 1 // شمارنده سوالات
    private val questions = listOf(

        "#1. کدام مورد هنگام رانندگی ممنوع می باشد؟"
                to listOf("  1- استعمال دخانیات", "  2- استفاده از تلفن همراه" , "  3- خوردن و آشامیدن", "  4- همه موارد"),

        "#2. در چه حالتی می توانیم از سمت راست سبقت بگیریم؟"
                to listOf(" 1- هنگام گردش به راست ", " 2- حرکت از سمت چپ ممنوع می باشد." , "  3- سمت راست باز باشد.", " 4- سبقت گرفتن از وسیله نقلیه ای که به سمت چپ گردش می کند. "),

        "#3. قبل از شروع حرکت باید کدامیک از چراغهای خودرو مورد بررسی قرار گیرد؟"
                to listOf("  1- چراغ جلو - چراغ خطر - چراغهای راهنما - چراغ دنده عقب", " 2- چراغ عقب - چراغ راهنما - چراغ abs " , "  3- چراغ عقب و چراغ راهنما در صورت کثیف بودن", "  4- هیچ کدام"),

        "#4. چه موقع میتوانید از چراغ هشدار دهنده خطر ( فلاشر یا چهار چراغ ) استفاده نمایید ؟"
                to listOf("  1- زمانیکه به صورت موقت و یا اضطراری توقف کرده و ترافیک را متوقف نموده اید.", "  2- زمانیکه به آرامی رانندگی کرده و به دنیال آدرس میگردید." , " 3- زمانیکه در تاریکی بدون چراغ بزرگ حرکت میکنید.", "  4- زمانیکه برای خرید بر روی خطوط زرد دوبل توقف کرده اید."),

        "#5. راننده ای که از وسیله خود برای تنبیه دیگران استفاده می کند جزو چه نوع از حالت های رانندگی می باشد؟"
                to listOf("  1- پرخاشگری وسیله ای", " 2- رفتار وسیله ای " , " 3- پرخاشگری خصمانه", "  4- رفتار خصمانه"),

        "#6. هرگاه وسیله نقلیه موتوری در راه ها از کار بیفتد و به دیل نور کافی از به چراغ نباشد راننده باید مثلث شبرنگ را در چه فاصله ای از طرف عقب و جلو وسیله نقلیه به نحوی که قابل دیدن باشد قرار دهد؟"
                to listOf("  1- 200 متری", "  2- 150 متری" , "  3- 100 متری", " 4- 70 متری"),

        "#7. چه چیزی موجب به هم ریختن تمرکز شما در رانندگی خواهد شد؟"
                to listOf(" 1- نگاه کردن به اطراف اتومبیل", " 2- نگاه کردن به آیینه اتومبیل " , " 3- استفاده از شیشه شور اتومبیل ", "  4- نگاه کردن به نقشه گوش کردن به موزیک با صدای بلند و استفاده از تلفن همراه"),

        "#8. حق تقدم عبور را در این تقاطع مشخص کنید (آمبولانس در حال ماموریت است)"
                to listOf(" 1- خودرو نارنجی - خودرو سبز - خودرو زرد - آمبولانس", " 2- آمبولانس - خودرو سبز - خودرو نارنجی - خودرو زرد" , "  3- خودرو سبز - خودرو نارنجی - آمبولانس - خودرو زرد", " 4- آمبولانس - خودرو نارنجی - خودرو سبز - خودرو زرد"),

        "#9. کدام یکی از موارد زیر جزو پرخاشگری اجتماعی و ترافیکی محسوب نمی شود؟"
                to listOf(" 1- حرکات مارپیچ", "  2- سبقت غیر مجاز" , " 3- عدم توجه به تابلوی ترافیکی ", "  4- بوق زدن بیهوده و مکرر"),

        "#10. فاصله مناسب از جدول هنگام پارک چقدر است؟"
                to listOf(" 1- 1 متر از جلو - نیم متر از عقب - 40 سانت از جدول", "  2- 1 متر از جلو - 1 متر از عقب - 45 سانت از جدول" , "  3- نیم متر از جلو - 1 متر از عقب - 40 سانت از جدول", " 4- 1 متر از جلو - نیم متر از عقب - 45 سانت از جدول "),

        "#11. اتومبیلی جلوی شما از یک فرعی بیرون می آید٬ چکار باید انجام دهید؟"
                to listOf("  1- از سرعت خود کاسته و آماده توقف شوید.", "  2- با سرعت از کنارش گذشته و بوق بزنید." , "  3- چراغ داده و پشت سر او قرار بگیرید.", " 4- با شتاب از کنار او عبور کنید. "),

        "#12. در تصادفات منجر به خسارت مالی باید …"
                to listOf("  1- باید تا رسیدن مامور در جای خود ایستاد", " 2- نباید توقف کرد." , "  3- به کنار راه منتقل تا موجب سد معبر نشود.", " 4- هیچ کدام "),

        "#13. آلودگی صوتی جزو کدام مفهوم رانندگی می باشد؟"
                to listOf("  1- شهر نشینی", "  2- زمان سفر" , "  3- نابسامانی ترافیکی", " 4- حقوق شهروندی"),

        "#14. بی توجهی به علائم و مقررات رانندگی نشانه:"
                to listOf("  1- عدم توانائی و صلاحیت رانندگی است.", " 2- عدم سواد کافی" , "  3- عدم آمادگی لازم", " 4- شتاب و عجله بیهوده است."),

        "#15. رانندگی در شب مشکل تر از روز می باشد زیرا…………….."
                to listOf(" 1- عدم تشخیص به موقع موانع و عابرین پیاده ", " 2- عدم استفاده از آینه های جانبی " , "  3- محدود شدن میدان دید طبیعی", " 4- تمام موارد "),

        "#16. نابسمانی ترافیکی کدام یک از موارد زیر است؟"
                to listOf("  1- عدم توجه به تابلوهای ترافیکی", " 2- حرکات مارپیچ و سبقت های غیر مجاز" , "  3- رانندگی پرخاشگرانه", " 4- رفتارهای نابهنجار ترافیکی همچون تخلفات، تصادفها، حرکات مارپیچ، سبقت های غیر مجاز، عدم توجه به تابلوهای ترافیکی"),

        "#17. باد لاستیک چرخها را در چه زمانی باید تنظیم کرد؟"
                to listOf("  1- در زمان سرد بودن هوا لاستیکها را نمی توان تنظیم باد نمود.", "  2- زمانیکه مسافت طولانی را طی کردیم." , "  3- در زمان سرد بودن لاستیک", " 4- در زمان گرم بودن لاستیک"),

        "#18. کدام یک از تابلوهای زیر مفهوم پیچ های پی در پی را می دهد؟"
                to listOf(" 1- تابلو شماره 4", "  2- تابلو شماره 2" , " 3- تابلو شماره 1 ", " 4- تابلو شماره 3"),

        "#19. به هنگام ترکیدن لاستیک کدام مورد را باید انجام دهید؟"
                to listOf(" 1- ترمز کرده و سریعا توقف کنید.", "  2- فرمان را به شدت و محکم بگیرید و اجازه دهید اتومبیل خودبخود توقف کند." , "  3- فرمان را شل بگیرید ولی محکم ترمز کنید.", " 4- دنده را تعویض کرده و سریعا ترمز کنید. "),

        "#20. مفهوم تابلوی شکل زیر چیست؟"
                to listOf("  1- انتهای بزرگراه", "  2- پایان آزاد راه" , "  3- آزاد راه", " 4- بن بست"),

        "#21. معاینه و آزمایش فنی وسایل نقلیه شامل چه مواردی می شود؟"
                to listOf(" 1- تشخیص اصالت ", "  2- سلامت زیست محیطی" , "  3- سلامت فنی ، تجهیزات و ایمنی", "  4- همه موارد"),

        "#22. مفهوم تابلوی شکل زیر چیست؟"
                to listOf("  1- ابتدای حداکثر سرعت در بزرگراه", " 2- حداکثر سرعت در خطهای عبور" , " 3- حداقل سرعت در خطهای عبور ", "  4- هیچ کدام"),

        "#23. مبدل کاتالیستی در کدام بخش خودرو قرار دارد؟"
                to listOf(" 1- موتور ", "  2- جعبه دنده" , "  3- گیربکس", "  4- اگزوز"),

        "#24. رانندگانی که قصد گردش یا توقف را دارند، باید از چه فاصله ای به وسیله چراغ راهنما علامت بدهند؟"
                to listOf("  1- بیش از 100 متر", "  2- از فاصله 100 متری" , "  3- حداکثر 500 متری", "  4- کمتر از 100 متری"),

        "#25. مهمترین عامل مشکلات زیست محیطی ……….و ناشی از …………می باشد ."
                to listOf("  1- آلودگی هوا _ ناشی از خودرو ها", "  2- آلودگی هوا _ ناشی کارخانه و دود بخاری های گرمایشی" , "  3- صدای ناهنجار _ ناشی از خودرو ها", "  4- گرمای هوا _ ناشی از کارخانه جات"),

        "#26. کاربرد خطوط شطرنجی به رنگ زرد و سیاه در تقاطع چیست؟"
                to listOf("  1- سرعت خود را کاهش داده و با احتیاط عبور نمایید.", " 2- قبل از خطوط شطرنجی توقف و مسیر حرکت خودروهای دیگر را باز نگه دارید. " , " 3- نوعی خطر را هشدار میدهد و باید سرعت را کاهش داد. ", " 4- توقف و عبور روی آنها ممنوع می باشد. "),

        "#27. در سه راه ها حق تقدم عبور با وسیله نقلیه ای است که …؟"
                to listOf(" 1- سریع تر از وسیله نقلیه دیگر حرکت می کند. ", "  2- به چپ گردش می کند." , " 3- به راست گردش می کند.", " 4- به طور مستقیم و در سمت و مسیر مجاز حرکت میکند. "),

        "#28. دور زدن تا چه فاصله ای از پیچ ها که میدان دید کافی ندارند ممنوع است؟"
                to listOf(" 1- 250 متری", " 2- 300 متری " , " 3- 400 متری ", " 4- 150 متری"),

        "#29. وظیفه سیستم ……….. ایجاد تعادل میان سرعت و نیرو است."
                to listOf("  1- دیفرانسیل", "  2- گیربکس" , "  3- کلاچ", "  4- محورها و پدال گاز"),

        "#30. مهم ترین عامل تصادف رانندگی چیست؟"
                to listOf("  1- ترافیک سنگین", " 2- اشتباهات رانندگی" , " 3- نقص فنی", "  4- سرعت غیرمجاز"),



        )
    private val questionImages = listOf(
        "https://th.bing.com/th/id/OIG2.nKKsBT_9VYE87SnMUz7G?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG4.lbV.FQnY_2F0USR_tSnK?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/4577856899.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG3.ICObLRTIKxz_k622G1Aa?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.J8SjZp0N_fgbdvBcGBP3?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/05/35477754.jpg",
        "https://th.bing.com/th/id/OIG3.2Rt_CcDYpiUIAzPZIFGY?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG3.myeLPBT0xo6RV896izfe?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG3.YwKcjynKraCulSZ3bZ9m?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG4.MuzfvGVNfSxfVHW9dFOB?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn&cb=1720136565477",
        "https://test-drive.ir/wp-content/uploads/2022/03/9090856.jpg",
        "https://th.bing.com/th/id/OIG3.shfRlH4bIQkxEFvmhvkL?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.5tTMTmD2.wHRaDKGByRU?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/04/5346666623.jpg",
        "https://th.bing.com/th/id/OIG2.Pm_m8f2Nb30jpILWG8AM?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/04/338997745.jpg",
        "https://th.bing.com/th/id/OIG2.Clpo8XuhH4XfjiBdb1cG?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn" ,
        "https://test-drive.ir/wp-content/uploads/2022/04/3534374889.jpg",
        "https://th.bing.com/th/id/OIG4.1isuTUAgh5M.4ZkIbV7q?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG1.0RmlZl9CSigyUzqCSsrR?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/43457548.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/88843387578.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.r8PcqKMm7h0UacLfZcB9?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",


        )
    private val correctAnswers = listOf(

        "  4- همه موارد",
        " 4- سبقت گرفتن از وسیله نقلیه ای که به سمت چپ گردش می کند. ",
        "  1- چراغ جلو - چراغ خطر - چراغهای راهنما - چراغ دنده عقب",
        "  1- زمانیکه به صورت موقت و یا اضطراری توقف کرده و ترافیک را متوقف نموده اید.",
        "  1- پرخاشگری وسیله ای",
        " 4- 70 متری" ,
        "  4- نگاه کردن به نقشه گوش کردن به موزیک با صدای بلند و استفاده از تلفن همراه",
        " 4- آمبولانس - خودرو نارنجی - خودرو سبز - خودرو زرد" ,
        " 3- عدم توجه به تابلوی ترافیکی ",
        "  2- 1 متر از جلو - 1 متر از عقب - 45 سانت از جدول" ,
        "  1- از سرعت خود کاسته و آماده توقف شوید.",
        "  3- به کنار راه منتقل تا موجب سد معبر نشود.",
        " 4- حقوق شهروندی",
        "  1- عدم توانائی و صلاحیت رانندگی است.",
        " 4- تمام موارد ",
        " 4- رفتارهای نابهنجار ترافیکی همچون تخلفات، تصادفها، حرکات مارپیچ، سبقت های غیر مجاز، عدم توجه به تابلوهای ترافیکی",
        "  3- در زمان سرد بودن لاستیک",
        " 3- تابلو شماره 1 ",
        "  2- فرمان را به شدت و محکم بگیرید و اجازه دهید اتومبیل خودبخود توقف کند." ,
        "  2- پایان آزاد راه" ,
        "  4- همه موارد" ,
        " 2- حداکثر سرعت در خطهای عبور" ,
        "  4- اگزوز" ,
        "  2- از فاصله 100 متری" ,
        "  1- آلودگی هوا _ ناشی از خودرو ها",
        " 2- قبل از خطوط شطرنجی توقف و مسیر حرکت خودروهای دیگر را باز نگه دارید. " ,
        " 4- به طور مستقیم و در سمت و مسیر مجاز حرکت میکند. ",
        " 4- 150 متری" ,
        "  2- گیربکس" ,
        " 2- اشتباهات رانندگی"
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
        intent.putExtra("examId", "Azmon4")
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


