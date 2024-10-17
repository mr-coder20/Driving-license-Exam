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

//26
class Azmon14 : AppCompatActivity() {

    private lateinit var binding: ActivityAzmonBinding
    private var currentQuestionIndex = 0
    private var questionCounter = 1

    private val questions = listOf(

        "#1. حق تقدم در برخورد چند وسیله در یک تقاطع هم عرض با کدام وسیله می باشد؟"
                to listOf(
            " الف. وسیله ای که به سمت چپ گردش می کند. ",
            " ب. وسیله ای که سمت راست وسیله ای دیگر قرار دارد. ",
            " ج. هم زمان می توانند وارد یک مسیر شوند.",
            " د. وسیله ای که بصورت مستقیم حرکت می کند. "
        ),

        "#2. هنگام رانندگی در شرایط یخبندان از انجام دادن کدام عمل باید اجتناب نمود؟"
                to listOf(
            " الف. استفاده مدام از ترمز دستی ",
            " ب. سریعا و پشت سر هم ترمز گرفتن",
            " ج. استفاده مدام از دنده یک",
            " د. از تمام موارد"
        ),

        "#3. حداکثر سرعت در خیابانهای شریانی اصلی …. و در خیابانهای شریانی فرعی …. کیلومتر بر ساعت است؟"
                to listOf(
            " الف. 100-80",
            " ب. 80-100",
            " ج. 50-60",
            " د. 60-50"
        ),

        "#4. تابلو تقدم عبور معمولا در چه جاهایی نصب می شود؟"
                to listOf(
            " الف. پلهای باریک ",
            " ب. محل انجام تعمیرات جاده",
            " ج. تقاطعها",
            " د. همه موارد"
        ),

        "#5. وظیفه اصلی ترمز دستی چیست؟"
                to listOf(
            " الف. نگه داشتن خودرو در حالت متوقف",
            " ب. نگه داشتن خودرو در هنگام پارک کردن",
            " ج. کمک کردن به توقف وسیله نقلیه در سرعت پایین",
            " د. همه موارد"
        ),

        "#6. راننده کامیونی به تقاطع رسیده و به سمت راست راهنما می زند اما ابتدا به سمت چپ متمایل می شود چه توجیحی برای اینکار وجود دارد؟\n"
                to listOf(
            " الف. راننده اشتباه راهنما زده است. ",
            " ب. برای اینکه چرخهای عقب به جدول سمت راست برخورد نکند. ",
            " ج. راننده کنترل خود را بر کامیون از دست داده است. ",
            " د. راننده از گردش به راست پشیمان شده است. "
        ),

        "#7. مفهوم این تابلو چیست؟"
                to listOf(
            " الف. شروع جدا کننده",
            " ب. جریان همگرا ",
            " ج. پایان جدا کننده",
            " د. هدایت مسیر با جدا کننده "
        ),

        "#8. کدام یک از گزینه های زیر صحیح می باشد؟"
                to listOf(
            " الف. رانندگی در مه مانند رانندگی با چشم بسته است.",
            " ب. رانندگی در مه مانند رانندگی با دید عادی است.",
            " ج. رانندگی در مه مانند رانندگی در شب است. ",
            " د. رانندگی درمه مانند رانندگی درهوای بارانی است."
        ),

        "#9. عبور از روی کدام خطوط زیر ممنوع نمی باشد؟"
                to listOf(
            " الف. خط زیگزاک",
            " ب. خط مقطع ",
            " ج. خط طولی ممتد",
            " د. الف و ج "
        ),

        "#10. روش یک طول اتومبیل در تعیین فاصله با خودرو جلویی در راههای خشک چگونه است؟"
                to listOf(
            " الف. یک طول خودرو به ازای هر 15 کیلومتر سرعت در ساعت. ",
            " ب. دو طول خودرو به ازای هر 15 کیلومتر سرعت در ساعت. ",
            " ج. یک طول خودرو به ازای هر 30 کیلومتر سرعت در ساعت. ",
            " د. دو طول خودرو به ازای هر 30 کیلومتر سرعت در ساعت. "
        ),

        "#11. اگر بر سرچهار راهی چراغ سبز باشد اما داخل تقاطع به دلیل ترافیک بسته باشد؟"
                to listOf(
            " الف. چون چراغ سبز است می توان عبور کرد.",
            " ب. باید صبر کرد تا راه باز شود و نباید تقاطع را مسدود کرد. ",
            " ج. میتوان با بوق زدن خودروهای جلویی را وادار به حرکت نمود. ",
            " د. میتوان با چراغ خودروهای جلویی را وادار به حرکت کرد."
        ),

        "#12. نام تابلوی که مشاهده می کنید چیست؟"
                to listOf(
            " الف. ورود به راه اصلی از چپ ",
            " ب. ورود به راه اصلی از راست ",
            " ج. تقاطع فرعی به اصلی ",
            " د. تقاطع"
        ),

        "#13. در تقاطع زیر حق تقدم عبور را مشخص کنید."
                to listOf(
            " الف. سبز - قرمز - آبی - زرد ",
            " ب. قرمز - سبز - آبی - زرد ",
            " ج. زرد - قرمز - آبی - سبز ",
            " د. آبی - زرد - سبز - قرمز "
        ),

        "#14. نام تابلوی تصویر بالا چیست؟"
                to listOf(
            " الف. پایان خیابان دو طرفه ",
            " ب. وسایل نقلیه مقابل حق تقدم دارند. ",
            " ج. مسیر ویژه اتوبوس شروع می شود.",
            " د. شما نسبت به وسیله نقلیه ای که به طرف شما می آید حق تقدم دارید. "
        ),

        "#15. در کدامیک از محلهای زیر توقف اتومبیل ایجاد خطر کرده و مانع عبور سایرین خواهد شد؟\n"
                to listOf(
            " الف. مقابل درب ورودی ساختمانها",
            " ب. حریم ایستگاه اتوبوس ",
            " ج. حریم تقاطعها و گذرگاه عابر پیاده",
            " د. تمام موارد "
        ),

        "#16. مفهوم چراغ راهنمایی سبز چیست؟"
                to listOf(
            " الف. ایست کامل و سپس عبور ",
            " ب. عبور یا گردش آزاد است مگر آنکه گردش نمودن توسط علائم دیکر ممنوع شده باشد. ",
            " ج. رعایت حق تقدم وسایلی که مستقیم حرکت می کنند و یا عابرین پیاده الزامی است. ",
            " د. موارد ب و ج صحیح می باشد."
        ),

        "#17. رانندگی در بخش وسط راههایی که به وسیله خط کشی یا علائم دیگر به سه بخش تقسیم شده ممنوع است مگر برای ….؟"
                to listOf(
            " الف. سبقت گرفتن یا گردش به چپ با رعایت علائم و مقررات ",
            " ب. استراحت کردن با رعایت احتیاط ",
            " ج. سرعتهای بالا",
            " د. سرعتهای پایین "
        ),

        "#18. مبدل کاتالیستی چه کاری انجام می دهد؟"
                to listOf(
            " الف. توان موتور را افزایش می دهد.",
            " ب. صدای نابهنجار را کاهش می دهد. ",
            " ج. گازهای مضر را به گاز بی ضرر تبدیل می کند. ",
            "  د. هیچ کدام"
        ),

        "#19. نام تابلوی شکل بالا چیست؟"
                to listOf(
            " الف. عبور وسایل نقلیه موتوری ممنوع",
            " ب. سبقت ممنوع ",
            " ج. راه آزاد است",
            " د. فقط موتور سیکلت و اتومبیل سواری می توانند وارد شود."
        ),

        "#20. در هنگام حرکت و لغزش همزمان عقب خودرو از کدام موارد باید اجتناب کرد؟"
                to listOf(
            " الف. گاز دادن ",
            " ب. استفاده از ترمز و کلاج ",
            " ج. موارد الف و ب ",
            " د. علامت دادن به سایرین "
        ),

        "#21. هنگام ورود به میادین حق تقدم با وسیله نقلیهای است که …"
                to listOf(
            " الف. در حال ورود به میدان است.",
            " ب. در سمت راست وسیله نقلیه قرار دارد.",
            " ج. درون میدان در حال حرکت است.",
            " د. در حال عبور مستقیم است."
        ),

        "#22. در راه های یک طرفه، حرکت بر خلاف مسیر تعیین شده…."
                to listOf(
            " الف. با دادن علامت و زدن چراغ راهنما مانعی ندارد. ",
            " ب. با رعایت حق تقدم آزاد است.",
            " ج. برای گردش به چپ آزاد است. ",
            " د. ممنوع است. "
        ),

        "#23. مفهوم تابلوی شکل بالا چیست؟"
                to listOf(
            " الف. حاشیه نمای راست 300 متر",
            " ب. حاشیه نمای چپ 300 متر ",
            " ج. خروجی از آزادراه 300 متر",
            " د. حاشیه و جهت نما(گروهی)"
        ),

        "#24. سبقت گرفتن در سر پیچهای تند و سربالاییها به چه علت ممنوع می باشد؟"
                to listOf(
            " الف. نامحدود بودن میدان دید راننده ",
            " ب. کم بودن میدان دید راننده ",
            " ج. اختلال در ترافیک ",
            " د. عدم کشش خودرو "
        ),

        "#25. تردد کدام نوع وسیله نقلیه در خط سوم آزاد راه ها ممنوع نمی باشد؟"
                to listOf(
            " الف. اتوبوس",
            " ب. وانت ها ",
            " ج. مینی بوس",
            " د. انواع بارکش ها با وزن بیش از 3.5 تن "
        ),

        "#26. نام تابلوی شکل بالا چیست؟"
                to listOf(
            " الف. پارکینگ همگانی ",
            " ب. عبور معلولین",
            " ج. پارکینگ مخصوص افراد معلول",
            " د. پارک برای افراد معلول ممنوع"
        ),

        "#27. علت سر خوردن خودرو در شرایط یخبندان کدام عامل می باشد؟ "
                to listOf(
            " الف. اصطحکاک زیاد تایر با سطح جاده ",
            " ب. دیر عمل نمودن ترمزها ",
            " ج. سفت شدن ترمزها",
            " د. اصطحکاک کم تایر با سطح جاده "
        ),

        "#28. هنرجوی گرامی حق تقدم عبور را در این تقاطع مشخص نمایید."
                to listOf(
            " الف. آبی . قرمز. زرد. سبز ",
            " ب. سبز. زرد. قرمز . آبی",
            " ج. آبی . زرد . سبز و قرمز همزمان ",
            " د. قرمز. آبی . زرد . سبز"
        ),

        "#29. این تابلو بیانگر چیست؟"
                to listOf(
            " الف. تلفن عمومی",
            " ب. جایگاه سوخت",
            " ج. تعمیرگاه",
            " د. استراحت گاه کاروان "
        ),

        "#30. هنرجوی گرامی حق تقدم عبور را در تقاطع بالا مشخص کنید."
                to listOf(
            " الف-کامیون - سواری سبز - سواری قرمز ",
            " ب- سواری سبز - سواری قرمز - کامیون ",
            " د- سواری قرمز - سواری سبز - کامیون ",
            " ج- سواری سبز - کامیون - سواری قرمز "
        ),


        )
    private val questionImages = listOf(

        "https://test-drive.ir/wp-content/uploads/2022/03/532466.jpg",
        "https://th.bing.com/th/id/OIG1.ps6W3HNReYhS2X32ZmPh?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG4.yUWr3S4VQGwD7VSGaM1f?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG4.I2oXpP0AfkdCLCjoAQ9o?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/8999454577.jpg",
        "https://th.bing.com/th/id/OIG1.DoWIJ0I4ODsnXo5yvXAs?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG1.djABk9DKApzcrAaHQaSg?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/77843434378.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/06/34366777.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/9990554447.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.A1SB6vG1t_zPtCuinD4Q?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG4.NHWR7kh8EWHFM_WzqvNe?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/7789045477.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/88855544.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG3.uX.kGdNvnh4.sqKW4nwR?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/889095445.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.oFa2sHLTrKXf1PfhSjWX?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/67678955578.jpg",
        "https://th.bing.com/th/id/OIG4.hITsA.XsRecawCvHJN.E?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/34636778.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/745588333.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/05/3653777.jpg",
    )

    private val correctAnswers = listOf(

        " ب. وسیله ای که سمت راست وسیله ای دیگر قرار دارد. ",
        " د. از تمام موارد",
        " د. 60-50",
        " د. همه موارد",
        " د. همه موارد",
        " ب. برای اینکه چرخهای عقب به جدول سمت راست برخورد نکند. ",
        " ج. پایان جدا کننده",
        " الف. رانندگی در مه مانند رانندگی با چشم بسته است.",
        " ب. خط مقطع ",
        " الف. یک طول خودرو به ازای هر 15 کیلومتر سرعت در ساعت. ",
        " ب. باید صبر کرد تا راه باز شود و نباید تقاطع را مسدود کرد. ",
        " ب. ورود به راه اصلی از راست ",
        " د. آبی - زرد - سبز - قرمز ",
        " د. شما نسبت به وسیله نقلیه ای که به طرف شما می آید حق تقدم دارید. ",
        " د. تمام موارد ",
        " د. موارد ب و ج صحیح می باشد.",
        " الف. سبقت گرفتن یا گردش به چپ با رعایت علائم و مقررات ",
        " ج. گازهای مضر را به گاز بی ضرر تبدیل می کند. ",
        " الف. عبور وسایل نقلیه موتوری ممنوع",
        " ج. موارد الف و ب ",
        " ج. درون میدان در حال حرکت است.",
        " د. ممنوع است. ",
        " ج. خروجی از آزادراه 300 متر",
        " ب. کم بودن میدان دید راننده ",
        " ب. وانت ها ",
        " ج. پارکینگ مخصوص افراد معلول",
        " د. اصطحکاک کم تایر با سطح جاده ",
        " ج. آبی . زرد . سبز و قرمز همزمان ",
        " ب. جایگاه سوخت",
        " ج- سواری سبز - کامیون - سواری قرمز "


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
            .thumbnail(Glide.with(this).load(questionImages[currentQuestionIndex]).diskCacheStrategy(DiskCacheStrategy.ALL))
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
        intent.putExtra("examId", "Azmon14")
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


