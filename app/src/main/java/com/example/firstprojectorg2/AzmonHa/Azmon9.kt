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


class Azmon9 : AppCompatActivity() {
    private lateinit var binding: ActivityAzmonBinding
    private var currentQuestionIndex = 0
    private var questionCounter = 1 // شمارنده سوالات

    private val questions = listOf(

        "#1. قبل از شروع حرکت باید کدامیک از چراغهای خودرو مورد بازبینی قرار گیرد؟"
                to listOf(
            " 1- چراغ جلو - چراغ خطر - چراغهای راهنما- چراغ دنده عقب ",
            "  2- چراغ جلو - چراغ عقب - چراغ راهنما - چراغ ABS",
            "  3- چراغ جلو - چراغ عقب و چراغ راهنما در صورت کثیف بودن",
            " 4- نیازی به بازبینی چراغها نمی باشد"
        ),

        "#2. زمانیکه راننده پشت سر در یک خیابان شلوغ و پر ترافیک از شما می خواهد سبقت بگیرد و شما با سرعت مجاز در حال رانندگی هستید کدام اقدام صحیح تر است؟"
                to listOf(
            " 1- بلافاصله ترمز کرده و راه را برای سبقت آزاد می کنیم. ",
            "  2- از خود واکنش نشان داده و اجازه سبقت نمی دهیم.",
            " 3- مسیر صحیح و مستقیم خود را ادامه می دهیم تا به راننده پشت سر فرصت دهید در شرایطی بی خطر سبقت بگیرد.",
            " 4- بر سرعت خود افزوده و از خودرو فاصله می گیریم."
        ),

        "#3. حداکثر سرعت در آزاد راه ها چند کیلومتر است؟"
                to listOf(
            "  1- 125 کیلومتر در ساعت",
            "  2- 120 کیلومتر در ساعت",
            " 3- 110 کیلومتر در ساعت ",
            " 4- 70 کیلومتر در ساعت"
        ),

        "#4. چه زمانی لنت های ترمز بوی لاستیک سوخته می دهند؟"
                to listOf(
            " 1- هنگام سرد شدن",
            " 2- هنگام داغ شدن ",
            "  3- هنگام سایش زیاد",
            " 4- هنگام ضربه خوردن"
        ),

        "#5. زمانی که تجربه کافی ندارید و به تازگی گواهینامه گرفته اید نسبت تصادف شما به سایر رانندگان چقدر می باشد؟"
                to listOf(
            " 1- خیلی زیاد",
            " 2- تجربه نیاز نیست",
            " 3- قبولی امتحان شهر کافی می باشد.",
            " 4- فرقی نمی کند. "
        ),

        "#6. سیستم هدایت و فرمان باید هر از چه مدت بازدید شود؟"
                to listOf(
            "  1- هر ماه یک بار",
            " 2- هر دو ماه یکبار",
            " 3- هر ۶ ماه یکبار ",
            "  4- هر سال یک بار"
        ),

        "#7. در علائم عبور و مرور تابلو هشت گوشه به معنی؟"
                to listOf(
            "  1- شروع و پایان حق تقدم مسیر",
            " 2- معمولا برای تابلوهای دستوری",
            " 3- علامت دستوری، بازدارنده ",
            "  4- تنها برای تابلوهای ایست (توقف)"
        ),

        "#8. کدام یک از موارد زیر در رابطه با سیلندر درست است؟"
                to listOf(
            "  1- استوانه ای است.",
            " 2- پیستون در آن حرکت می کند. ",
            " 3- مورد ۱ و ۲",
            " 4- هیچکدام"
        ),

        "#9. نام دیگر ترمز پایی چیست؟"
                to listOf(
            " 1- ترمز اولیه",
            " 2- ترمز ثانویه",
            "  3- ترمز پارک",
            "  4- ترمز سرویس"
        ),

        "#10. کدام یک از موارد زیر در رابطه با میل لنگ درست است ؟"
                to listOf(
            " 1- حرکت رفت و برگشتی پیستون را دریافت میکند.",
            "  2- حرکت رفت و برگشتی پیستون را به حرکت دورانی تبدیل می کند حرکت رفت و برگشتی",
            " 3- پیستون را از طریق شاتون دریافت می کند.",
            "  4- همه موارد"
        ),

        "#11. در شرایطی که موتور جوش بیاورد چه اقدامی باید انجام داد؟"
                to listOf(
            " 1- موتور را نباید خاموش کرد.",
            "  2- درب رادیاتور را باز نمی کنیم.",
            " 3- مقداری آب بر روی رادیاتور میریزیم. ",
            "  4- همه موارد"
        ),

        "#12. در شکل زیر حق تقدم عبور با کدام خودرو است؟"
                to listOf(
            "  1- خودروی که با دایره مشخص شده است.",
            "  2- خودرویی که خارج میدان قرار دارد.",
            "  3- بستگی به جهت حرکت خودرو دارد.",
            "  4- بستگی به مسیر نهایی خودرو دارد."
        ),

        "#13. روغن گیربکس هر چند وقت باید بررسی شود؟"
                to listOf(
            "  1- هر ماه یک بار هر سال یک بار",
            "  2- هر هفته یک بار",
            " 3- هر بار تعویض روغن ",
            " 4- هیچ کدام"
        ),

        "#14. عمر مفید کنیستر چند سال می باشد؟"
                to listOf(
            "  1- یک سال",
            "  2- دو سال",
            "  3- سه سال",
            "  4- چهار سال"
        ),

        "#15. در سبقت گرفتن کدامیک از موارد زیر را باید رعایت نمود؟"
                to listOf(
            " 1- یافتن فضایی برای جلو افتادن از خودروی دیگر",
            "  2- داشتن سرعتی معادل سرعت خودرویی که می خواهیم از آن سبقت بگیریم.",
            "  3- عدم ممنوعیت سبقت در آن مکان از راه، اطمینان از خالی بودن وسیله نقلیه در لاین دیگر",
            " 4- عدم ممنوعیت سبقت در آن مکان از راه ، مگر سرعت خودر آن قدر باشد که در زمان کوتاه عمل سبقت گرفتن تمام شود."
        ),

        "#16. در کدامیک از موارد زیر احتمال وقوع تصادفات کمتر است؟"
                to listOf(
            " 1- هوای خشک، شرایط جاده استاندارد",
            " 2- وجود مه، ریزش باران، کولاک ",
            "  3- وجود مه، ریزش باران، شرایط جاده استاندارد",
            " 4- وجود چاله و دست انداز در جاده، ریزش باران"
        ),

        "#17. کدامیک جزو وظایف سیستم تعلیق نمی باشد؟"
                to listOf(
            "  1- کنترل قدرت و شتاب خودرو",
            "  2- تامین راحتی راننده",
            " 3- حفظ تعادل و پایداری خودرو هنگام عبور از پستی یا بلندی ها",
            " 4- ممانعت از واژگونی خودرو هنگام عبور از پیچها"
        ),

        "#18. کدامیک از موارد زیر در حین رانندگی در شب اشتباه است؟"
                to listOf(
            " 1- روشن نمودن چراغ های کوچک و بزرگ بطور همزمان",
            " 2- استفاده از بوق کوتاه هنگام سبقت",
            " 3- کاهش سرعت در صورت نزدیک شدن خودرویی از روبرو که از نور بالا استفاده می کند. ",
            "  4- استفاده از چراغ مه شکن در هنگام ریزش"
        ),

        "#19. راننده ای که دست چپ را به طرف پایین نگهداشته به چه معناست؟"
                to listOf(
            " 1- گردش یا تغییر مسیر به راست ",
            " 2- گردش یا تغییر مسیر به چپ",
            " 3- توقف",
            " 4- افزایش سرعت"
        ),

        "#20. مفهوم رنگ سبز در علائم عبور و مرور چیست؟"
                to listOf(
            "  1- حرکات مجاز، راهنمای مسیر در بزرگراه ها و امکان مذهبی",
            "  2- منع کننده یا ایست",
            " 3- راهنما برای مناطق اداری و آموزشی و خدماتی ",
            " 4- راهنما برای مناطق تفریحی و فرهنگی و گردشگری"
        ),

        "#21. دور زدن از روی خط پر و یا یک خط ممتد ……"
                to listOf(
            "  1- با رعایت احتیاط بدون اشکال است.",
            " 2- ممنوع است. ",
            " 3- مجاز است.",
            "  4- در صورت خلوت بودن مسیر مجاز است."
        ),

        "#22. ترمز سرویس (ترمز پایی) مطابق با الزامات استاندارهای خودرویی باید بر ….. عمل کند."
                to listOf(
            " 1- تمامی چرخها",
            "  2- فقط بر چرخ های جلو",
            " 3- فقط بر چرخهای عقب",
            " 4- فقط بر چرخهای محرک"
        ),

        "#23. انجام کدامیک از موارد زیر در هنگام تنفس مصنوعی صحیح تر است؟"
                to listOf(
            " 1- خارج کردن اجسام خارجی از دهان مصدوم، دادن تنفس مصنوعی، بررسی تنفس حداقل به مدت 20 ثانیه",
            " 2- خارج کردن اجسام خارجی از دهان مصدوم، بررسی تنفس حداقل به مدت 20 ثانیه، دادن تنفس به مدت 5 ثانیه ",
            "  3- خارج کردن اجسام خارجی از دهان مصدوم، باز کردن راه هوایی با بالا بردن چانه مصدوم، بررسی داشتن تنفس پس از باز بودن راه هوایی",
            " 4- باز کردن دهان مصدوم و دادن تنفس مصنوعی، بررسی تنفس به مدت 20 ثانیه، دادن تنفس مصنوعی به مدت 5 ثانیه "
        ),

        "#24. مفهوم تابلوی بالا چیست؟"
                to listOf(
            " 1- راهروی مخصوص عابرین",
            " 2- پل هوایی و تونل زیرگذر پیاده",
            " 3- مسیر مخصوص عابرین پیاده",
            "  4- زیرگذر یا روگذر"
        ),

        "#25. کدامیک از موارد زیر باید قبل از شروع حرکت مورد بازبینی قرار گیرد؟"
                to listOf(
            "1- بوق - چراغ- شیشه ها- آینه ها - ترمز - تایرها - میزان آب رادیاتور",
            "  2- میزان آب رادیاتور - برف پاک کن - بوق - کلاچ - گاز",
            "  3- ترمزها - چراغ ها - بوق - آینه ها - کلاچ",
            " 4- چراغ ها - بوق - شیشه ها - گاز "
        ),

        "#26. مفهوم تابلوی بالا چیست؟"
                to listOf(
            " 1- تعیین فاصله 100 متری با راه آهن",
            " 2- تقاطع جاده و راه آهن بدون راه بند، نصب در 100 متری",
            " 3- تقاطع جاده و راه آهن با راه بند، نصب در 100 متری",
            "  4- گزینه های 2 و 3 صحیح است."
        ),

        "#27. ایستادن یا توقف وسایل نقلیه در کدام یک محل های زیر ممنوع است؟"
                to listOf(
            " 1- پیاده رو و گذرگاه پیاده ",
            " 2- در فاصله 15 متری میدان یا تقاطع یا سه راه ها یا تقاطع راه آهن",
            " 3- از ابتدا تا انتهای پیچها",
            " 4- تمام موارد"
        ),

        "#28. کدام یک از موارد زیر در باره ی رادیاتور درست است؟"
                to listOf(
            " 1- مخزن آب سیستم خنک کاری موتور است.",
            " 2- باعث خنک شدن آب در حال گردش می شود.",
            "  3- شکل شبکه ای دارد.",
            " 4- همه موارد"
        ),

        "#29. مفهوم تابلوی بالا چیست؟"
                to listOf(
            " 1- عبور با وزن بیش از 5.5 تن ممنوع ",
            " 2- عبور کامیون 5.5 تنی ممنوع",
            "  3- عبور وسایل نقلیه با وزن بیش از 5.5 تن ممنوع",
            "  4- عبور ممنوع"
        ),

        "#30. هنرجوی گرامی حق تقدم عبور را در تقاطع زیر مشخص کنید."
                to listOf(
            " 1- سبز - آبی - قرمز ",
            " 2- آبی - سبز - قرمز ",
            " 3- قرمز - آبی - سبز ",
            " 4- قرمز - سبز - آبی "
        ),



        )
    private val questionImages = listOf(

        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG3.9bzjYVQkmJER044E5bwt?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.h26T4At7GI7s_IGncu6J?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG4..fS9nCClm2NYKvKgYYgv?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn&cb=1720785835972",
        "https://th.bing.com/th/id/OIG3.NILZS8Qk1byUi8jKZ.ba?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/09/456637.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG3.NVScz4L5K90zBkECHf3I?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2023/10/alpaca85503.jpg",
        "https://th.bing.com/th/id/OIG4.NSEIxuoaCUgnFWu_q.A3?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG4.ee4I1ykqwn2R6HF8K.aY?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.Du9S3ZX0GzTzZVeimvpM?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG4.uAYzlaGhsR.gD5NIxtme?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/435765487.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/457686598.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/886594.jpg",
        "https://th.bing.com/th/id/OIG2.KXkckz6iSEf0t6LKzX6E?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/03/4375782.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/03/378755885.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/03/666574832.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/05/3546784.jpg",
    )

    private val correctAnswers = listOf(

        " 1- چراغ جلو - چراغ خطر - چراغهای راهنما- چراغ دنده عقب ",
        " 3- مسیر صحیح و مستقیم خود را ادامه می دهیم تا به راننده پشت سر فرصت دهید در شرایطی بی خطر سبقت بگیرد.",
        "  2- 120 کیلومتر در ساعت",
        " 2- هنگام داغ شدن ",
        " 1- خیلی زیاد",
        " 3- هر ۶ ماه یکبار ",
        "  4- تنها برای تابلوهای ایست (توقف)",
        " 3- مورد ۱ و ۲",
        " 1- ترمز اولیه",
        "  4- همه موارد",
        "  4- همه موارد",
        "  1- خودروی که با دایره مشخص شده است.",
        " 3- هر بار تعویض روغن ",
        "  2- دو سال",
        "  3- عدم ممنوعیت سبقت در آن مکان از راه، اطمینان از خالی بودن وسیله نقلیه در لاین دیگر",
        " 1- هوای خشک، شرایط جاده استاندارد",
        "  1- کنترل قدرت و شتاب خودرو",
        " 2- استفاده از بوق کوتاه هنگام سبقت",
        " 3- توقف",
        "  1- حرکات مجاز، راهنمای مسیر در بزرگراه ها و امکان مذهبی",
        " 2- ممنوع است. ",
        " 1- تمامی چرخها",
        "  3- خارج کردن اجسام خارجی از دهان مصدوم، باز کردن راه هوایی با بالا بردن چانه مصدوم، بررسی داشتن تنفس پس از باز بودن راه هوایی",
        "  4- زیرگذر یا روگذر" ,
        "1- بوق - چراغ- شیشه ها- آینه ها - ترمز - تایرها - میزان آب رادیاتور",
        " 2- تقاطع جاده و راه آهن بدون راه بند، نصب در 100 متری",
        " 4- تمام موارد",
        " 4- همه موارد" ,
        "  3- عبور وسایل نقلیه با وزن بیش از 5.5 تن ممنوع",
        " 4- قرمز - سبز - آبی "

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
        intent.putExtra("examId", "Azmon9")
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


