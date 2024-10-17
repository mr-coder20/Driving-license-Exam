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


class Azmon7 : AppCompatActivity() {
    private lateinit var binding: ActivityAzmonBinding
    private var currentQuestionIndex = 0
    private var questionCounter = 1 // شمارنده سوالات

    private val questions = listOf(

        "#1. سبقت گرفتن برای اتوبوس ها و کامیون ها در معابر شهری……"
                to listOf(
            " 1- بلامانع است.",
            " 2- ممنوع است.",
            " 3- با احتیاط کامل ضروری است.",
            "  4- ضرورتی ندارد."
        ),

        "#2. در برخورد با یک مصدوم بیهوش، مهم ترین اقدام کدام است؟"
                to listOf(
            " 1- با کشیدن پتو بدن او را گرم نگه دارید. ",
            " 2- باز کردن راه هوایی تنفس. ",
            " 3- دادن مایعات و نوشیدنی گرم.",
            " 4- مصدوم را با تکان دادن بدنش به هوش آورید. "
        ),

        "#3. استفاده از تلفن همراه در حین رانندگی………."
                to listOf(
            " 1- ممنوع است. ",
            " 2- مجاز است. ",
            "  3- فقط در معابر شهری مجاز است.",
            "  4- در صورت حواس پرتی ممنوع است."
        ),

        "#4. بی احترامی به سایر کاربران ترافیک، دشنام و ناسزا گفتن به سایرین در حین رانندگی نشاندهنده چیست؟"
                to listOf(
            " 1- جرأت و شجاعت.",
            "  2- شخصیت متزلزل و منفی فرد.",
            " 3- شلوغی ترافیک. ",
            " 4- عجله و شتاب. "
        ),

        "#5. حداکثر میزان سرعت مجاز در بزرگراه های بیرون شهری برای سواری چند کیلومتر در ساعت است؟"
                to listOf(
            " 1- 85 کیلومتر بر ساعت ",
            "  2- 120 کیلومتر بر ساعت",
            " 3- 125 کیلومتر بر ساعت ",
            " 4- 110 کیلومتر بر ساعت "
        ),

        "#6. کدام یک از موارد زیر جزء رفتارهای رانندگان پرخطر و پرخاشگر نمی باشد؟"
                to listOf(
            " 1- بوق زدنهای بیهوده و مکرر. ",
            " 2- استفاده از نور بالا برای اذیت کردن راننده مقابل. ",
            "  3- رعایت حقوق شهروندی و حریم دیگران در هنگام رانندگی.",
            "  4- توهین به سایر رانندگان و انجام رفتارهای ناپسند و زشت."
        ),

        "#7. مفهوم تابلو زیر چیست؟"
                to listOf(
            " 1- عبور پیاده ممنوع ",
            "  2- گذرگاه عابر پیاده",
            " 3- عبور عابر پیاده ",
            "  4- شروع محل پیاده روی"
        ),

        "#8. چه زمانی از چراغ هشدار دهنده ( فلاشر و چهار چراغ ) می توان استفاده نمود؟"
                to listOf(
            "  1- زمانیکه اتومبیلی به صورت پارک دوبل پارک نموده",
            "  2- در زمانی که نقص فنی ایجاده شده و یا بخواهید اتومبیل پشت سر را از خطری که در جلو اتفاق افتاده آگاه کنید",
            " 3- وقتی که راننده عقبی نور بالازده و یا از جاده خارج شده ",
            " 4- در زمان یدک کشیده شدن و یا زمان پارک در منطقه ممنوعه "
        ),

        "#9. در کنترل و تنظیم عبور و مرور کدام مورد بر سایرین مقدم است؟"
                to listOf(
            "  1- خط کشی و ترسیم ها",
            " 2- چراغ راهنمایی",
            " 3- فرمان پلیس ",
            "  4- تابلوهای راهنمایی"
        ),

        "#10. در مبحث مفهوم رنگ ها و علائم عبور و مرور، مفهوم کدام رنگ به معنی حرکات مجاز می باشد؟"
                to listOf(
            "1. 4",
            " 2. 3",
            "3. 1",
            " 4. 2 "
        ),

        "#11. شما با سرعت 30 کیلومتر بر ساعت در یک جاده خیس رانندگی می کنید، فاصله زمانی بین شما و اتومبیل جلویی چقدر باید باشد؟"
                to listOf(
            " 1- 2 ثانیه ",
            "  2- 6 ثانیه",
            " 3- 4 ثانیه",
            "  4- 8 ثانیه"
        ),

        "#12. هنرجوی گرامی حق تقدم عبور در تقاطع زیر را به ترتیب مشخص کنید."
                to listOf(
            " 1- زرد - آبی - قرمز",
            " 2- آبی - قرمز - زرد",
            "  3- قرمز - زرد - آبی",
            "  4- آبی- زرد - قرمز"
        ),

        "#13. کدام یک گزینه های زیر صحیح می باشد؟"
                to listOf(
            " 1- در جاده های اصلی حداکثر میزان سرعت مجاز در روز 85 کیلومتر بر ساعت است. ",
            "  2- در جاده های فرعی حداکثر میزان سرعت مجاز در روز 75 کیلومتر بر ساعت است.",
            " 3- در جاده های فرعی حداکثر میزان سرعت مجاز در شب 85 کیلومتر بر ساعت است. ",
            "  4- در جاده های اصلی حداکثر میزان سرعت مجاز در شب 85 کیلومتر بر ساعت است."
        ),

        "#14. هنرجوی گرامی مفهوم این تابلو چیست؟"
                to listOf(
            " 1- عبور با وزن بیش از 3/5 تن ممنوع ",
            "  2- عبور با بار بیش از3/5 متر ممنوع",
            " 3- عبور با ارتفاع بیش از 3/5 متر ممنوع",
            " 4- عبور با عرض بیش از 3/5 متر ممنوع "
        ),

        "#15. اگر خودرویی با نور بالا از روبرو به شما نزدیک شود چه کاری انجام می دهید؟"
                to listOf(
            "  1- باید با تبدیل نور پایین به نور بالا و بوق ممتد او را متوجه می کنیم.",
            " 2- باید سرعت را افزایش داده و سریع از کنار آن عبور کنیم. ",
            " 3- سرعت را کاهش داده و به سمت راست جاده نگاه کنیم کنار چشم حرکت خودرو مقابل را تحت نظر داشته باشیم. ",
            "  4- هیچ کدام"
        ),

        "#16. برای پارک کردن در کنار خیابان، حق تقدم با وسیله ای است که……\n"
                to listOf(
            " 1- با حرکت به جلو مشغول پارک کردن است.",
            "  2- چراغ خطر دنده عقب اتومبیلش روشن باشد.",
            " 3- ضمن حرکت به عقب مشغول پارک کردن است. ",
            "  4- هیچکدام"
        ),

        "#17. در شبها و در راههای بیرون شهر حداکثر سرعت چند کیلومتر در ساعت است؟"
                to listOf(
            "  1- 85 کیلومتر بر ساعت",
            " 2- 75 کیلومتر بر ساعت",
            "  3- 95 کیلومتر بر ساعت",
            " 4- 65 کیلومتر بر ساعت "
        ),

        "#18. کلاچ گرفتن باعث کدامیک از موارد زیر می شود؟"
                to listOf(
            " 1- کنترل راننده به حرکت و سرعت اتومبیل بیشتر می شود. ",
            "  2- کنترل راننده به حرکت و سرعت اتومبیل کم می شود.",
            "  3- پیچیدن به طرفین و چرخاندن فرمان آسانتر می شود.",
            "  4- پیچیدن به طرفین و چرخاندن فرمان سخت تر می شود."
        ),

        "#19. چرا باید برای سبقت از یک وسیله طویل فاصله بیشتری با آن حفظ کرد؟"
                to listOf(
            " 1- در صورت توقف کامیون دنده عقب بگیرید.",
            "  2- با سرعت بیشتری از اتومبیل سبقت بگیرید.",
            "  3- برای اینکه نقطه کور قرار نگرفته و از موقعیت جلوی کامیون اطلاع پیدا کنید.",
            " 4- برای سبقت گرفتن در پیچ ها سرعت بیشتری داشته باشید. "
        ),

        "#20. پر باد بودن تایر خودرو می تواند باعث بروز کدامیک از موارد زیر شود؟"
                to listOf(
            " 1- فرمان پذیری خودرو را کاهش داده و باعث ایجاد خطر می گردد.",
            " 2- باعث افزایش مصرف سوخت در سرعت های زیر 50 کیلومتر در ساعت می شود. ",
            " 3- باعث فرسایش تایر در سرعت های بالای 50 کیلومتر خواهد بود. ",
            "  4- همه موارد"
        ),

        "#21. چرا رانندگی در شب سخت تر است؟"
                to listOf(
            "  1- محدود بودن دید راننده",
            " 2- عدم تشخیص به موقع عابر پیاده ",
            " 3- عدم تشخیص فاصله و سرعت وسایل نقلیه",
            " 4- همه موارد "
        ),

        "#22. تابلو های بازدارنده را تابلوهای …..نیز میگویند که بیشتر به شکل…..با نوار حاشیه……رنگ هستند؟"
                to listOf(
            " 1- انتظامی -مثلث- قرمز",
            " 2- هشدار دهنده -دایره-قرمز ",
            " 3- انتظامی-دایره-قرمز",
            " 4- هشدار دهنده- مثلث- آبی "
        ),

        "#23. مفهوم تابلوی بالا چیست؟"
                to listOf(
            " 1- پرتاپ سنگ ",
            " 2- ریزش سنگ ",
            " 3- شانه خطرناک",
            " 4- جاده در دست تعمیر است."
        ),

        "#24. در تصویر شکل بالا حق تقدم عبور به ترتیب عبارت است از:"
                to listOf(
            " 1- سواری سفید - سواری سبز - سواری قرمز - دوچرخه ",
            "  2- سواری سبز - سواری سفید - دوچرخه - سواری قرمز",
            " 3- سواری قرمز- سواری سبز - دوچرخه - سواری سفید",
            "  4- دوچرخه - سواری سبز - سواری سفید - سواری قرمز"
        ),

        "#25. تابلوی بالا به چه معناست؟"
                to listOf(
            "  1- دورزدن در میدان",
            " 2- میدان",
            "  3- تعیین حرکت",
            " 4- جهت عبور در میدان " ,
        ),

        "#26. از کدام علامت آگاه سازی برای سبقت در شب استفاده می شود؟\n"
                to listOf(
            " 1. استفاده از بوق کوتاه",
            " 2. فقط تبدیل و تعویض نور چراغ بالا و پایین",
            "  3. تبدیل و تعویض نور چراغ بالا و پایین به همراه چراغ راهنما",
            "  4. استفاده از بوق بلند بطور متناوب"
        ),

        "#27. تابلو شکل بالا به چه معنا می باشد؟"
                to listOf(
            " 1- پایان خیابان اصلی",
            " 2- خیابان اصلی",
            " 3- آزاد راه",
            " 4- پایان آزاد راه "
        ),

        "#28. حداکثر سرعت در بزرگراه های واقع در شهرها و مناطق مسکونی چند کیلومتر در ساعت است؟\n"
                to listOf(
            " 1- 100 کیلومتر بر ساعت ",
            "  2- 110 کیلومتر بر ساعت",
            " 3- 120 کیلومتر بر ساعت",
            " 4- 125 کیلومتر بر ساعت "
        ),

        "#29. تابلوی بالا چه مفهومی دارد؟"
                to listOf(
            " 1- بن بست",
            "  2- تقاطع هم سطح در قوس",
            " 3- تقاطع فرعی با اصلی در قوس ",
            " 4- گردش به راست و چپ"
        ),

        "#30. تجاوز از سرعت مجاز (بیش از ۳۰ تا ۵۰ کیلومتر بر ساعت) چند نمره منفی دارد؟"
                to listOf(
            " 1- شخصی 10 سنگین و عمومی 5 ",
            " 2- شخصی 5 سنگین و عمومی 15 ",
            " 3- شخصی 5 سنگین و عمومی 10 ",
            " 4- شخصی 10 سنگین و عمومی 15 "
        ),



        )
    private val questionImages = listOf(

        "https://th.bing.com/th/id/OIG1.MPm9.EYUF1YcUZQ_VS6r?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG1.37bD1qh0l6sF.HNI5sqx?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG1.3qZc0Ei0rz..pP_u1lE5?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.ybFwhmgqUgpWicw0f2zx?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.wYZKuwijtAblrnCuUEvb?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/03/057634.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG1.CFcPqnzXH2k3PXweSg9W?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/04/346788655.jpg",
        "https://th.bing.com/th/id/OIG2.QIAmG39LFhsgckIRMuj2?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2023/10/54358889.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/03/456889.jpg",
        "https://th.bing.com/th/id/OIG3.BNX_TP5HyAYJl4PuQpDh?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG3.CQj4Aku41e2GbfOnE8yf?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG1.SirRCV4nE3n55DOechIb?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.htyNd90EoqXtlskVzd5z?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG3.MIKN0wSIUt_6fIZvR9nC?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/05/43634647.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/05/345377775.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/87998055.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/03/94343737.jpg",
        "https://th.bing.com/th/id/OIG2.tdLoZS1NuhH47hyQjDeJ?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn&cb=1720709649864",
        "https://test-drive.ir/wp-content/uploads/2022/03/34008.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
    )

    private val correctAnswers = listOf(

        " 2- ممنوع است.",
        " 2- باز کردن راه هوایی تنفس. ",
        " 1- ممنوع است. ",
        "  2- شخصیت متزلزل و منفی فرد.",
        " 4- 110 کیلومتر بر ساعت " ,
        "  3- رعایت حقوق شهروندی و حریم دیگران در هنگام رانندگی.",
        "  4- شروع محل پیاده روی",
        "  2- در زمانی که نقص فنی ایجاده شده و یا بخواهید اتومبیل پشت سر را از خطری که در جلو اتفاق افتاده آگاه کنید",
        " 3- فرمان پلیس ",
        " 4. 2 " ,
        " 3- 4 ثانیه",
        "  4- آبی- زرد - قرمز" ,
        "  4- در جاده های اصلی حداکثر میزان سرعت مجاز در شب 85 کیلومتر بر ساعت است." ,
        " 3- عبور با ارتفاع بیش از 3/5 متر ممنوع",
        " 3- سرعت را کاهش داده و به سمت راست جاده نگاه کنیم کنار چشم حرکت خودرو مقابل را تحت نظر داشته باشیم. ",
        " 3- ضمن حرکت به عقب مشغول پارک کردن است. ",
        "  1- 85 کیلومتر بر ساعت",
        "  2- کنترل راننده به حرکت و سرعت اتومبیل کم می شود.",
        "  3- برای اینکه نقطه کور قرار نگرفته و از موقعیت جلوی کامیون اطلاع پیدا کنید.",
        " 1- فرمان پذیری خودرو را کاهش داده و باعث ایجاد خطر می گردد.",
        " 4- همه موارد " ,
        " 3- انتظامی-دایره-قرمز",
        " 2- ریزش سنگ ",
        "  4- دوچرخه - سواری سبز - سواری سفید - سواری قرمز" ,
        " 4- جهت عبور در میدان " ,
        "  3. تبدیل و تعویض نور چراغ بالا و پایین به همراه چراغ راهنما",
        " 2- خیابان اصلی",
        " 1- 100 کیلومتر بر ساعت ",
        " 3- تقاطع فرعی با اصلی در قوس ",
        " 3- شخصی 5 سنگین و عمومی 10 "

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
        intent.putExtra("examId", "Azmon7")
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


