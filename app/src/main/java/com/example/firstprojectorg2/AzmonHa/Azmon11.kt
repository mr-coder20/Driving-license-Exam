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


class Azmon11 : AppCompatActivity() {
    private lateinit var binding: ActivityAzmonBinding
    private var currentQuestionIndex = 0
    private var questionCounter = 1 // شمارنده سوالات

    //19

    private val questions = listOf(

        "#1. ترمز دستی……و به ترمز پایی …….می گویند؟"
                to listOf(
            " 1- اولیه - سریع",
            " 2- ثانویه - قدرتی",
            " 3- ثانویه - اولیه",
            " 4- اولیه - ثانویه "
        ),

        "#2. کدام یک از تابلوهای بالا جریان همگرا می باشد؟"
                to listOf(
            " 1. 4",
            " 2. 3",
            " 3. 1",
            " 4. 2"
        ),

        "#3. دور زدن از چند متری پیچ ها ممنوع است؟"
                to listOf(
            " 1- 500 متری",
            " 2- 100 متری",
            " 3- 50 متری",
            " 4- 150 متری"
        ),

        "#4. کدامیک از موارد زیر از مزیت های ترمز ABS می باشد؟"
                to listOf(
            " 1- ترمز ناگهانی می باشد.",
            " 2- مسافت توقف خودرو را کاهش می دهد.",
            " 3- دیر ایستادن خودرو ",
            " 4- نگه داشتن ماشین در یک لحظه "
        ),

        "#5. علائم هشدار دهنده برای خطر به چه شکل ترسیم می شود؟"
                to listOf(
            " 1- لوزی",
            " 2- مربع",
            " 3- مثلث",
            " 4- دایره "
        ),

        "#6. مفهوم تابلو زیر چیست؟"
                to listOf(
            " 1- عبور وسائط نقلیه موتوری ممنوع",
            " 2- عبور سواری ممنوع",
            " 3- عبور کلیه وسائط نقلیه ممنوع",
            " 4- ورود سواری ممنوع "
        ),

        "#7. کدام مورد زیر از رفتارهای رانندگان پرخاشگر و پرخطر می باشد؟"
                to listOf(
            " 1- تغییر مسیر با زدن چراغ راهنما",
            " 2- استفاده از نور بالا در محل های مجاز",
            " 3- عبور از خط مقابل برای رهایی از ترافیک",
            " 4- رعایت سرعت مطمئنه و رانندگی بین خطوط "
        ),

        "#8. کدام علامت آگاه سازی برای سبقت در شب استفاده میشود؟"
                to listOf(
            " 1- استفاده از بوق کوتاه ",
            " 2- فقط تبدیل و تعویض نور چراغ بالا و پایین",
            " 3- تبدیل و تعویض نور چراغ بالا و پایین به همراه چراغ راهنما",
            " 4- استفاده از بوق بلند بطور متناوب"
        ),

        "#9. کدام مورد جزو آلاینده های هوا نمی باشد؟"
                to listOf(
            " 1- گاز کربن دی اکسید ",
            " 2- اکسیژن",
            " 3- ذرات معلق",
            " 4- گاز نیتروژن "
        ),

        "#10. در سه راه ها حق تقدم عبور با وسیله ای است که ……."
                to listOf(
            " 1- در طرف راست وسیله نقلیه دیگر قرار دارد. ",
            " 2- به سمت راست گردش می کند. ",
            " 3- به طور مستقیم و در مسیر مجاز حرکت می کند.",
            " 4- به سمت چپ در حال گردش است. "
        ),

        "#11. کدامیک از موارد زیر جزو سیستم خنک کاری موتور نمی باشد؟"
                to listOf(
            " 1- میل لنگ",
            " 2- رادیاتور",
            " 3- فن",
            " 4- ترموستات"
        ),

        "#12. انتقال نیرو در سیستم ترمز دستی توسط...انجام میشود ."
                to listOf(
            " 1- اهرم",
            " 2- سیم های فلزی",
            " 3- روغن ترمز",
            "  4- پدال"
        ),

        "#13. هنگام رانندگی و مشاهده چراغ چشمک زن قرمز چه اقدامی باید انجام داد؟"
                to listOf(
            " 1- عبور از تقاطع با سرعت کم و احتیاط لازم",
            "  2- عبور با احتیاط",
            " 3- عبور از تقاطع با همان سرعت و احتیاط لازم",
            "  4- ایست و عبور"
        ),

        "#14. وظیفه ………ایجاد تعادل میان سرعت و نیرو است ."
                to listOf(
            " 1- پدال گاز",
            " 2- گیربکس",
            " 3- کلاچ",
            " 4- دیفرانسیل"
        ),

        "#15. در صورت مشاهده تابلو بالا منتظر چه چیزی باید بود؟"
                to listOf(
            " 1- گردش به چپ ممنوع",
            " 2- پیچهای پی در پی ( اولین پیچ به چپ) ",
            " 3- پیچ به چپ",
            " 4- پیچ به راست"
        ),

        "#16. مفهوم تابلوی بالا چیست؟"
                to listOf(
            " 1. عبور حیوانات وحشی ",
            " 2. عبور حیوانات اهلی",
            " 3. منطقه حیات وحش ",
            " 4. محل عبور حیوانات "
        ),

        "#17. در ترمز ضد قفل برای ترمز گیری باید پا را به صورت …روی پدال ترمز فشرد ."
                to listOf(
            " 1- محکم",
            " 2- آهسته",
            " 3- منقطع ",
            " 4- ممتد "
        ),

        "#18. حق تقدم در تقاطع بالا را مشخص کنید؟"
                to listOf(
            " 1- سبز - قرمز - نارنجی",
            " 2- قرمز - سبز - نارنجی ",
            " 3- نارنجی - قرمز- سبز",
            " 4- قرمز - نارنجی - سبز"
        ),

        "#19. در آیین نامه راهنمایی و رانندگی منظور از سلب اعتبار قانونی گواهینامه رانندگی کدام است؟"
                to listOf(
            " 1- انقضاء گواهینامه",
            " 2- ابطال گواهینامه ",
            " 3- توقیف گواهینامه",
            " 4- استرداد گواهینامه "
        ),

        "#20. در ارزیابی مصدومین کدامیک از مصدومین را باید در اولیت انجام کمکهای اولیه قرار داد؟"
                to listOf(
            " 1- مصدومینی که آسیبهای آنها کمتر است و با انجام کمکهای اولیه سریعا بهبود می یابند.",
            " 2- مصدومینی که آسیبهای آنها بسیار شدید است نظیر سوختگی ها یا زخمهای وسیع",
            " 3- مصدومینی که از سن بالاتری نسبت به سایر مصدومین برخوردارند.",
            " 4- کودکان در اولویت انجام کمکهای اولیه هستند. "
        ),

        "#21. در تقاطع بالا حق تقدم را مشخص کنید."
                to listOf(
            " 1- آبی - قرمز - سبز ",
            " 2- قرمز - سبز - آبی ",
            " 3- سبز - آبی - قرمز ",
            " 4- قرمز - آبی - سبز "
        ),

        "#22. در تنظیم صندلی عدم تسلط کافی بر تجهیزات کنترلی باعث می شود تا …"
                to listOf(
            " 1- واکنش و عکس العمل راننده در مقابل خطرات مناسب نباشد.",
            "  2- واکنش و عکس العمل راننده با سرعت صورت می پذیرد.",
            " 3- واکنش و عکس العمل راننده با تاخیر زمانی بالا صورت می پذیرد.",
            "  4- گزینه های یک و سه"
        ),

        "#23. ایستادن در کدام مورد مجاز می باشد؟"
                to listOf(
            " 1- در 15 متری چراغ راهنمایی ",
            " 2- گذرگاه پیاده ",
            " 3- تابلو ایستادن برای مدت کوتاه",
            " 4- 15 متری تقاطع ها "
        ),

        "#24. حداقل سرعت در آزادراه درون شهری چند کیلومتر در ساعت است؟"
                to listOf(
            " 1- 50 کیلومتر در ساعت ",
            " 2- 70 کیلومتر در ساعت ",
            " 3- 30 کیلومتر در ساعت ",
            " 4- 60 کیلومتر در ساعت "
        ),

        "#25. رانندگان کلیه وسایل نقلیه موظفند از خط عبور یا مسیر عبوری سمت راست راه حرکت نمایند، مگر:"
                to listOf(
            " 1- هنگام سبقت گرفتن از وسیله نقلیه جلو ",
            " 2- هنگامی که سمت راست راه به هر علتی بسته شده و امکان عبور نباشد.",
            " 3- هنگام گردش به چپ ",
            " 4- همه موارد بالا "
        ),

        "#26. برای گردش به چپ اگر وسیله نقلیه موتوری فاقد چراغ راهنما باشد باید……"
                to listOf(
            " 1- دست چپ را به طور افقی نگهداشت. ",
            " 2- دست چپ را به طرف بالا نگهداشت. ",
            " 3- دست چپ را به طرف پایین نگهداشت. ",
            " 4- دست راست را به طرف بالا نگهداشت. "
        ),

        "#27. تابلو زیر بیانگر چیست؟"
                to listOf(
            "  1- عبور خودروی کشاورزی ممنوع",
            "  2- عبور گاری ممنوع",
            " 3- عبور چرخ دستی ممنوع",
            " 4- عبور با محموله خطرناک ممنوع"
        ),

        "#28. با کسانی که در صورت ضبط گواهی نامه مبادرت به رانندگی می نمایند چه بر خوردی می شود؟"
                to listOf(
            " 1- باید هر چه سریع تر خود را به مراجع قضایی معرفی نمایند.",
            " 2- تا پایان عمر به آنها گواهی نامه داده نمی شود.",
            " 3- در صورتی که مرتکب خلاف نشود مشمول مجازات نخواهد شد.",
            "  4- به مراجع قضایی معرفی و به مجازات مقرر برای رانندگان بدون گواهینامه محکوم میشوند."
        ),

        "#29. هنرجوی گرامی تابلوی بالا بیانگر چیست؟"
                to listOf(
            " 1. عبور موتور گازی ممنوع",
            " 2. سبقت موتور سیکلت ممنوع",
            " 3. عبور وسائط نقلیه موتوری ممنوع",
            " 4. عبور موتور سیکلت ممنوع "
        ),

        "#30. الکل و مواد مخدر بر کدامیک از موارد زیر تاثیر منفی می گذارد؟"
                to listOf(
            " 1- توانایی ذهنی، هوشیاری و دید",
            " 2- تشخیص موقعیت های خطرناک",
            " 3- تمرکز و کنترل سرعت",
            " 4- همه موارد"
        ),



        )
    private val questionImages = listOf(

        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/04/44678885.jpg",
        "https://th.bing.com/th/id/OIG4.e9wm3ehXXI_bwgaNFMes?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/03/457686598.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/63458788.jpg",
        "https://th.bing.com/th/id/OIG4.uTxyEVRaaA9ctrDbxJ_f?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.tIUZXhoxNcEwx0XOw6Qc?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/03/88843387578.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/03/43645378.jpg",
        "https://th.bing.com/th/id/OIG4.IQYEU2QsIUVBfrTmTd9U?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/03/88875547.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/35367675.jpg",
        "https://th.bing.com/th/id/OIG3.Q4WVkAnQ2GyJ2TTXhoYK?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/58588944.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/06/3457783.jpg",
        "https://th.bing.com/th/id/OIG1.YgoYgX3vs5eNHiBI7Znl?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG1.ArGzvBo.Sr9jzfhYtSTi?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.8EB5Sq8quG_EfbYDnKN8?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/03/547547886.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/377722271.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/04/346346778.jpg",
        "https://th.bing.com/th/id/OIG1.YxpeMpwxCckOwilFN6IZ?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
    )

    private val correctAnswers = listOf(

        " 3- ثانویه - اولیه",
        " 3. 1",
        " 4- 150 متری" ,
        " 2- مسافت توقف خودرو را کاهش می دهد.",
        " 3- مثلث",
        " 2- عبور سواری ممنوع",
        " 3- عبور از خط مقابل برای رهایی از ترافیک",
        " 3- تبدیل و تعویض نور چراغ بالا و پایین به همراه چراغ راهنما",
        " 2- اکسیژن",
        " 3- به طور مستقیم و در مسیر مجاز حرکت می کند.",
        " 1- میل لنگ",
        " 2- سیم های فلزی",
        "  4- ایست و عبور",
        " 2- گیربکس",
        " 3- پیچ به چپ",
        " 2. عبور حیوانات اهلی",
        " 4- ممتد " ,
        " 2- قرمز - سبز - نارنجی ",
        " 2- ابطال گواهینامه ",
        " 2- مصدومینی که آسیبهای آنها بسیار شدید است نظیر سوختگی ها یا زخمهای وسیع",
        " 4- قرمز - آبی - سبز ",
        "  4- گزینه های یک و سه",
        " 3- تابلو ایستادن برای مدت کوتاه",
        " 2- 70 کیلومتر در ساعت ",
        " 4- همه موارد بالا " ,
        " 1- دست چپ را به طور افقی نگهداشت. ",
        "  2- عبور گاری ممنوع",
        "  4- به مراجع قضایی معرفی و به مجازات مقرر برای رانندگان بدون گواهینامه محکوم میشوند." ,
        " 4. عبور موتور سیکلت ممنوع " ,
        " 4- همه موارد"

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
        intent.putExtra("examId", "Azmon11")
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


