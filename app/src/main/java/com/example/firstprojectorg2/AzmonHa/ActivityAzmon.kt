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

class ActivityAzmon : AppCompatActivity() {

    private lateinit var binding: ActivityAzmonBinding
    private var currentQuestionIndex = 0
    private var questionCounter = 1

    private val questions = listOf(

        "1. حداکثر سرعت مجازبرای سواری و وانت بارها دربزرگراهها برون شهری چقدر است؟ "
                to listOf("1- 110 کیلومتر بر ساعت", "2- 120 کیلومتر بر ساعت", " 3- 95 کیلومتر بر ساعت", " 4- 100 کیلومتر بر ساعت"),

        "2. چراغ های جلو وسایل نقلیه ای که در شانه راه ها ( به استثنای آزاد راه ها و بزرگراه ها) توقف می نمایند بادیدن حرکت وسایل نقلیه ای که از مقابل در حرکتند باید خاموش شود و برای تشخیص وجود و ابعاد وسیله نقلیه، چراغ های ……… و ……… را روشن نمایند. ?"
                to listOf("1- کوچک - راهنما", " 2- بزرگ - جانبی", " 3- کوچک - جانبی", " 4- کوچک - بزرگ"),

        "#3. در خیابانها و جادههایی که نور کافی وجود دارد، …………… ?"
                to listOf(" 1- باید تنها از چراغ های جانبی جلو و عقب استفاده نمود.", " 2- باید از نوربالا استفاده نمود.", " 3- تمام چراغ ها را باید خاموش نمود.", " 4- باید تنها از نور پایین استفاده نمود."),

        "#4. حق تقدم عبور را در تقاطع زیر مشخص کنید. ( براساس رنگ خودرو) ?"
                to listOf(" 1- نارنجی وسبز همزمان – قرمز ", "  2- سبز- قرمز – نارنجی", "  3- نارنجی – سبز – قرمز", "  4- قرمز- سبز - نارنجی"),

        "#5. در راههای مسطح که دو طرف راه منتهی به کوه و پرتگاه می باشد حق تقدم عبور با کدامیک است؟ ?"
                to listOf(" 1- وسیله ای که سمت کوه قرار دارد. ", "  2- وسیله ای که سمت پرتگاه قراردارد.", "  3- هر دو وسیله حق تقدم عبور دارند.", "  4- هیچکدام"),

        "#6. در محل توقف(پارک) کنار خیابان حق تقدم با کیست؟ ?"
                to listOf(" 1- وسیله نقلیه ای که بصورت مستقیم وارد پارک می شود. ", "  2- وسیله ای که ضمن حرکت با دنده عقب مشغول پارک کردن است.", "  3- هر دو حق تقدم دارند.", " هیچکدام"),

        "#7. حداقل فاصله طولی ایمنی در جاده برفی با سرعت۴۵ کیلومتر در ساعت چند طول اتومبیل است؟ ?"
                to listOf("   3", "   6", " 9", " 2 "),

        "#8. دور زدن در چند متری پیچ ها و تقاطع ها و تونل ها ممنوع است؟ ?"
                to listOf("  1- 120 متری", " 2- 100 متری ", "  3- 50 متری", "  4- 150 متری"),

        "#9. برای امن کردن محل حادثه با چه اقدامی میتوان صحنه را عاری از خطر نمود؟ ?"
                to listOf(" 1- خاموش کردن وسیله نقلیه حادثه دیده.", "  2- مراقب سلامت خود باشید.\n", "  3- مصدوم را تا حد ممکن از محل دور نمایید.", " 4- موارد ۱ و ۲ "),

        "#10. در حال رانندگی در جاده خیس به دنبال اتومبیلی دیگر ، فاصله زمانی ایمن از اتومبیل جلویی چقدر باید باشد ؟ ?"
                to listOf("  1- 4 ثانیه", " 2- 6 ثانیه", " 3- 10 ثانیه ", "  4- 8 ثانیه"),

        "#11. مهم ترین نوع آلودگی که سهم زیادی در آلودگی ترافیک شهری را تشکیل می دهد کدام است؟ ?"
                to listOf("  1- آلودگی دیداری", " 2-آلودگی دیداری و آلودگی شنیداری", " 3- آلودگی هوا", "  4- آلودگی شنیداری"),

        "#12. در تقاطع بالا حق تقدم عبور را مشخص کنید.(آمبولانس در حال ماموریت است) ?"
                to listOf(" 1- آمبولانس - قرمز - آبی ", "  - آمبولانس - آبی - قرمز", " 3- آبی - قرمز - آمبولانس", "  4- قرمز - آبی - آمبولانس"),

        "#13. در چه شرایطی میتوان شاسی خودرو را عوض کرد؟ ?"
                to listOf("  1- در هیچ شرایطی نمیتوان عوض نمود.", " 2- تعویض آن دل به خواه و بدون اشکال است. ", "  3- تنها با مجوز راهنمایی و رانندگی میتوان به این کار اقدام نمود.", "  4- مجوز کارخانه سازنده برای این کار کافی است."),

        "#14. شستوشوی وسایل نقلیه در راهها، …………… ?"
                to listOf(" 1- ممنوع است. ", "  2- مجاز است.", "  3- باعث سد راه و ایجاد خطر و انحراف وسایل نقلیه از مسیر حرکت میشود.", "  4- موارد ۱ و ۳."),

        "#15. شما اولین نفری هستید که به محل حادثه میرسید کدام مورد را باید انجام دهید ؟ ?"
                to listOf(" 1- از محل حادثه دور شوید ", "  2- اتومبیل را از حادثه دیده دور نمائید.", " 3- با دوربین شروع به فیلم برداری نمائید. ", "  4- موتور اتومبیل را خاموش و زخمی ها را خارج نمایید به اورژانس زنگ بزنید."),

        "#16. برای گردش به چپ از خیابان دو طرفه دو خطه به خیابان دوطرفه دو خطه باید کدامیک از اقدامات زیر را انجام دهیم؟ ?"
                to listOf(" 1- به جریان ترافیک توجه داشته باشیم. ", " 2- از فاصله یکصد متری راهنما بزنیم.", "  3- در خط عبور چپ خود قرار بگیریم.", " 4- همه موارد "),

        "#17. تکیه نمودن و آویزان شدن اشخاص به وسایل نقلیه ……… ?"
                to listOf(" 1- مجاز است.", "  2- با رعایت احتیاز مجاز است.", " 3- ممنوع است. ", "  4- در صورت بیخطر بودن مجاز است."),

        "#18. چه هنگام میتوان از ترمز دستی استفاده نمود؟ ?"
                to listOf(" 1- هنگام توقف خودرو و یا در سرعت های بسیار پایین. ", "  2- در هر شرایطی.", " 3- تنها در سرعت های بالا.", "  4- در هنگام توقف کامل وسیله نقلیه."),

        "#19. تابلو های هشدار دهنده یا ……بیشتر به صورت …..با نوارهای حاشیه….رنگ و زمینه……رنگ می باشد. ?"
                to listOf("  1- اخطاری-مثلث-قرمز- سفید", "  2- اخطاری- مربع- قرمز-سفید", "  3- اخباری-دایره- قرمز-آبی", "  4- انتظامی- پنج ضلعی - قرمز-سفید"),

        "#20. رانندگی با وسیله نقلیهای که برگ معاینه فنی معتبر نداشته باشد، چگونه است؟ ?"
                to listOf("  1- ممنوع است.", " 2- با هماهنگی شهرداری مجاز است. ", "  3- فقط در معابر برون شهری مجاز است.", " 4- در خیابان های محلی مجاز است. "),

        "#21. کدام تابلو به معنای پارک سوار است؟ ?"
                to listOf(" 1- تابلو 1 ", "  2- تابلو 3", "  3- تابلو 2", "  4- تابلو 4"),

        "#22. مفهوم تابلوی شکل زیر چیست ؟ ?"
                to listOf(" 1- به منطقه مسکونی نزدیک می شوید. ", " 2- منطقه صنعتی", " 3- ابتدای منطقه مسکونی", "  4- خروج از منطقه صنعتی"),

        "#23. هنرجوی گرامی نام خطوط مشخص شده به رنگ زرد در تصویر بالا چیست؟ ?"
                to listOf(" 1- خطوط هاشور", "  2- خطوط زیگزاگ", " 3- خطوط مورب ", "  4- خطوط ویژه عبور"),

        "#24. در اشکال بالا تابلوی توقف ممنوع کدام است؟ ?"
                to listOf("  1- تابلو 1", " 2- تابلو 2 ", "  3- تابلو 3", "  4- تابلود 4"),

        "#25. کدام یک از گزینه های زیر صحیح نیست؟ ?"
                to listOf(" 1- در آزادراهها حداقل سرعت 70 کیلومتر بر ساعت", "  2- در جاده های اصلی حداکثر میزان سرعت مجاز95 کیلومتر در روز", "  3- در جاده های فرعی حداکثر میزان سرعت مجاز 90 کیلومتر در روز", "  4- در بزرگراهها حداکثر سرعت برای سواری 110 کیلومتر بر ساعت"),

        "#26. کدام گزینه درباره ضدیخ صحیح می باشد؟"
                to listOf(" 1- بهتر است کل مخزن رادیاتور به جای آب از ضد یخ پُر شود. ", "  2- مایع ضدیخ از یخ زدگی آب در سرما جلوگیری میکند.", "  3- استفاده از ضدیخ در تابستان میتواند منجر به جوش آوردن زودتر موتور شود.", "  4- ضدیخ در جلوگیری از زنگزدگی رادیاتور و قطعات سیستم خنک کاری مؤثر نمیباشد."),

        "#27. هنرجوی گرامی نوع پارک در تصویر زیر را مشخص کنید. ?"
                to listOf(" 1- پارک افقی ", "  2- پارک عمودی", "  3- خطوط پارک مورب", "  4- پارک معمولی"),

        "#28. در چه فاصله از ورودی یا خروجی مراکز آتش نشانی ،پلیس و بیمارستان ها توقف ممنوع است؟ ?"
                to listOf("  1- 20 متری", "  2- 15 متری", "  3- 30 متری", " 4- 10 متری "),

        "#29. شکل بالا چه موردی را هشدار می دهد؟ ?"
                to listOf(" 1- زمانی که شرایط جاده استاندارد نیست و چاله و دست انداز وجود دارد باید احتیاط نمود. ", "  2- هنگامی که شعاع دید کم است در یک جای مناسب توقف نمایید.", "  3- هنگامی که شعاع دید کم است باید سرعتی کمتر از محدوده سرعت قانونی را انتخاب نمایید.", "  4- هنگامی که شعاع دید کم است با احتیاط رانندگی کنید."),

        "#30. در هنگام رانندگی در شرایط بد آب و هوایی چه مواردی را باید رعایت کرد؟ ?"
                to listOf("  1- مطمئن شوید که برف پاک کن ها سالم هستند.", "  2- چراغهای جلو را در حالت نور پایین روشن نمایید.", "  3- از سیستم تهویه خودرو به منظور جلوگیری از عرق کردن شیشه های خودرو استفاده نمایید.", "  4- همه موارد"),


    )

    private val questionImages = listOf(
        "https://th.bing.com/th/id/OIG3.29x2bnBZs80B_UiCxYTA?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.ObtEIJQRb4HCjAT2DzWN?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG4.96q9x6Y4VoMz_kUYSLMV?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2023/09/intersection.jpg" ,
        "https://th.bing.com/th/id/OIG1.x0hxsjHygbQc3PxlBOfB?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn" ,
        "https://test-drive.ir/wp-content/uploads/2022/03/43636767.jpg" ,
        "https://th.bing.com/th/id/OIG1.vDMlWk5HSN10OvCDjm1g?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn" ,
        "https://th.bing.com/th/id/OIG4.QNsup2je8b5o6iGpjT_x?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn" ,
        "https://th.bing.com/th/id/OIG2.Xde8xuHDJtMI5NjajekI?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn" ,
        "https://th.bing.com/th/id/OIG4._ZO5wbei_EQs5w2xxkv4?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn" ,
        "https://th.bing.com/th/id/OIG1.QOccb_ZmyByY1Sa..rYX?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn" ,
        "https://test-drive.ir/wp-content/uploads/2022/04/354767732.jpg" ,
        "https://th.bing.com/th/id/OIG1.XHR7Wp26oBVAjWBM7klH?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn" ,
        "https://th.bing.com/th/id/OIG1.Ye6BTUmc0jqv68rMBOeQ?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn" ,
        "https://th.bing.com/th/id/OIG3.x199OMebvkfLyUp0Ghgx?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn" ,
        "https://test-drive.ir/wp-content/uploads/2022/03/43622687.jpg" ,
        "https://th.bing.com/th/id/OIG4.tFvSknUCjcubdXBa4LES?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn" ,
        "https://test-drive.ir/wp-content/uploads/2022/03/43645378.jpg" ,
        "https://test-drive.ir/wp-content/uploads/2022/03/457686598.jpg" ,
        "https://th.bing.com/th/id/OIG2.Clpo8XuhH4XfjiBdb1cG?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn" ,
        "https://test-drive.ir/wp-content/uploads/2022/04/54346678.jpg" ,
        "https://test-drive.ir/wp-content/uploads/2022/04/43354637775.jpg" ,
        "https://test-drive.ir/wp-content/uploads/2022/05/23535526.jpg" ,
        "https://test-drive.ir/wp-content/uploads/2022/05/456478888.jpg" ,
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn" ,
        "https://th.bing.com/th/id/OIG3.qgYoMyPXYqJp1cqhVFq4?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn" ,
        "https://test-drive.ir/wp-content/uploads/2022/03/436757722.jpg" ,
        "https://th.bing.com/th/id/OIG2.27yzEniMyjhWTvn86FPl?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn" ,
        "https://test-drive.ir/wp-content/uploads/2022/04/4564878.jpg" ,
        "https://th.bing.com/th/id/OIG3.0.erfjcq6CxJ0.2zzISR?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn" ,

        )

    private val correctAnswers = listOf(
        "1- 110 کیلومتر بر ساعت",
        " 3- کوچک - جانبی",
        " 1- باید تنها از چراغ های جانبی جلو و عقب استفاده نمود.",
        "  4- قرمز- سبز - نارنجی" ,
        "  2- وسیله ای که سمت پرتگاه قراردارد.",
        "2- وسیله ای که ضمن حرکت با دنده عقب مشغول پارک کردن است." ,
        " 9" ,
        "  4- 150 متری" ,
        " 4- موارد ۱ و ۲ " ,
        "  1- 4 ثانیه" ,
        " 3- آلودگی هوا" ,
        " 1- آمبولانس - قرمز - آبی " ,
        "  3- تنها با مجوز راهنمایی و رانندگی میتوان به این کار اقدام نمود." ,
        "  4- موارد ۱ و ۳." ,
        "  4- موتور اتومبیل را خاموش و زخمی ها را خارج نمایید به اورژانس زنگ بزنید." ,
        " 4- همه موارد " ,
        " 3- ممنوع است. " ,
        " 1- هنگام توقف خودرو و یا در سرعت های بسیار پایین. ",
        "  1- اخطاری-مثلث-قرمز- سفید",
        "  1- ممنوع است.",
        "  2- تابلو 3",
        " 3- ابتدای منطقه مسکونی",
        "  2- خطوط زیگزاگ",
        " 2- تابلو 2 ",
        "  3- در جاده های فرعی حداکثر میزان سرعت مجاز 90 کیلومتر در روز",
        "  2- مایع ضدیخ از یخ زدگی آب در سرما جلوگیری میکند.",
        "  3- خطوط پارک مورب",
        "  2- 15 متری",
        "  3- هنگامی که شعاع دید کم است باید سرعتی کمتر از محدوده سرعت قانونی را انتخاب نمایید.",
        "  4- همه موارد"

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

        binding.textView31.text = "${questionCounter} /30"
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
        intent.putExtra("examId", "ActivityAzmon")
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
                    binding.textView31.text ="${questionCounter} /30"
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
        layout.setBackgroundResource(R.drawable.rec7_bordered_selectable_item_background)
        selectedAnswer = answer

    }

    private fun resetAnswerLayouts() {
        binding.answerLayout1.setBackgroundResource(R.drawable.rec6_bordered_selectable_item_background)
        binding.answerLayout2.setBackgroundResource(R.drawable.rec6_bordered_selectable_item_background)
        binding.answerLayout3.setBackgroundResource(R.drawable.rec6_bordered_selectable_item_background)
        binding.answerLayout4.setBackgroundResource(R.drawable.rec6_bordered_selectable_item_background)
    }

    private fun endQuiz() {
        countDownTimer.cancel()
        showResult()
    }

    private fun highlightCorrectAnswers() {
        val correctAnswer = correctAnswers[currentQuestionIndex]
        if (binding.option1Text.text == correctAnswer) {
            binding.answerLayout1.setBackgroundResource(R.drawable.rec8_bordered_selectable_item_background)
        }
        if (binding.option2Text.text == correctAnswer) {
            binding.answerLayout2.setBackgroundResource(R.drawable.rec8_bordered_selectable_item_background)
        }
        if (binding.option3Text.text == correctAnswer) {
            binding.answerLayout3.setBackgroundResource(R.drawable.rec8_bordered_selectable_item_background)
        }
        if (binding.option4Text.text == correctAnswer) {
            binding.answerLayout4.setBackgroundResource(R.drawable.rec8_bordered_selectable_item_background)
        }
    }
}



