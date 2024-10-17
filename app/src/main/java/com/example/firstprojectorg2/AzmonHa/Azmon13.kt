
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

//23
class Azmon13 : AppCompatActivity() {
    private lateinit var binding: ActivityAzmonBinding
    private var currentQuestionIndex = 0
    private var questionCounter = 1 // شمارنده سوالات

    private val questions = listOf(

        "#1. روش یک طول اتومبیل به چه معناست؟"
                to listOf(
            " 1- به ازای هر ۶۰ کیلومتر سرعت در ساعت فاصله ای به اندازه ۴ طول اتومبیل (حدود ۶ متر) نیاز است. ",
            " 2- به ازای هر ۴۵ کیلومتر سرعت در ساعت فاصله ای به اندازه ۳ طول اتومبیل (حدود ۶ متر) نیاز است. ",
            " 3- به ازای هر ۳۰ کیلومتر سرعت در ساعت فاصله ای به اندازه ۲ طول اتومبیل (حدود ۶ متر) نیاز است. ",
            " 4- به ازای هر ۱۵ کیلومتر سرعت در ساعت فاصله ای به اندازه ی یک طول اتومبیل (حدود ۶ متر) نیاز است. "
        ),

        "#2. نور پایین اتومبیل باید اشیا را از چند متری مشخص نماید؟"
                to listOf(
            " 1- صد و پنجاه متری ",
            " 2- سی متری ",
            " 3- سی و پنج متری ",
            " 4- صد و سی پنج متری"
        ),

        "#3. درصورت ترکیدن لاستیک جلوی اتومبیل باید…"
                to listOf(
            " 1- به شدت ترمز کرد...",
            " 2- فرمان را رها کرد.",
            " 3- فرمان را محکم گرفته و به آرامی توقف کرد.",
            " 4- یک و دو درست است. "
        ),

        "#4. یکی از موارد افزایش احتمال خطر هنگام رانندگی در شب را بگویید؟"
                to listOf(
            " 1- خواب آلودگی راننده ",
            " 2- کم بودن میدان دید راننده",
            " 3- استفاده از سایبان ",
            " 4- استفاده از چراغها "
        ),

        "#5. کدامیک از موارد زیر در خصوص استفاده از تلفن همراه در حین رانندگی صحیح تر است؟"
                to listOf(
            " 1- هرگونه استفاده از تلفن همراه شامل صحبت کردن، ارسال پیامک، دیدن تصاویر و ... ممنوع است. ",
            " 2- صرفا صحبت کردن با تلفن همراه ممنوع است.",
            " 3- صرفا ارسال پیامک با تلفن همراه در حین رانندگی ممنوع می باشد. ",
            " 4- فقط خواند پیامک از تلفن همراه در حین رانندگی ممنوع است. "
        ),

        "#6. در جاده دچار حادثه ای شده اید در چه فاصله ای باید تابلوی احتیاط را قرار دهید؟"
                to listOf(
            " 1- هفتاد متری",
            " 2- شصت متری ",
            " 3- پنجاه متری",
            " 4- چهل متری "
        ),

        "#7. به هنگام رانندگی در طول روز و در شرایط هوای مه آلود چکار باید انجام داد؟"
                to listOf(
            " 1- چراغهای راهنما را روشن کرد.",
            " 2- از نور بالا استفاده کرد. ",
            " 3- از چراغهای بغل استفاده نمود. ",
            " 4- از نور پایین استفاده کرد."
        ),

        "#8. کدام یک از موارد زیر صحیح نمی باشد؟"
                to listOf(
            " 1- تابلوهای بازدارنده یا انتظامی",
            " 2- تابلوهای آگاهی دهنده یا اخباری ",
            " 3- تابلوهای هشدار دهنده یا اخطاری",
            " 4- تابلو های بازدارنده یا اخطاری"
        ),

        "#9. چه موقع امکان سبقت در داخل تونل وجود دارد؟"
                to listOf(
            " 1- این کار ممنوع است. ",
            " 2- نور کافی داشته باشیم.",
            " 3- راه یکطرفه باشد. ",
            " 4- پهنای تونل عریض باشد. "
        ),

        "#10. چرا شکسته و کثیف بودن شیشه های خودرو می تواند خطرناک باشد؟"
                to listOf(
            " 1- چون باعث کاهش انعکاس نور می شود.",
            " 2- چون باعث کاهش دید می شود.",
            " 3- چون باعث افزایش انعکاس نور می شود.",
            " 4- شکسته بودن و کثیف بودن شیشه ها خطر آفرین نمی باشد. "
        ),

        "#11. هنگام پارک کردن در سربالایی که دارای جدول کناری می باشد فرمان را به کدام سمت بچرخانیم؟"
                to listOf(
            " 1- فرقی ندارد.= ",
            " 2- فرمان را به سمت مقابل صاف می کنیم.=",
            " 3- فرمان را به راست=",
            " 4- فرمان را به چپ(در جهت مخالف عقربه های ساعت).= "
        ),

        "#12. اگر به هنگام رانندگی متوجه بوی بنزین شدید چه اقدامی می کنید؟"
                to listOf(
            " 1- شیشه اتومبیل را پایین می دهید.",
            " 2- توقف کرده و مسئله را بررسی می کنید.",
            " 3- نگران نباشید زیرا بو ناشی از سوخت بنزین است.",
            " 4- با سرعت کم برانید."
        ),

        "#13. کدام یک از گزینه های زیر نقص فنی محسوب نمی شود؟"
                to listOf(
            " 1- نداشتن زنجیر چرخ در یخبندان ",
            " 2- دود کردن",
            " 3- کم بودن باد لاستیک ها",
            " 4- نداشتن چراغ های جلو وعقب "
        ),

        "#14. در تقاطع بالا حق تقدم را مشخص کنید."
                to listOf(
            " 1- زرد - آبی - سبز ",
            " 2- آبی - سبز - زرد ",
            " 3- سبز - آبی - زرد ",
            " 4- آبی - زرد - سبز "
        ),

        "#15. در کدام موقعیت زیر وزش باد شدید می تواند در مسیر شما تاثیر بگذارد؟"
                to listOf(
            "1- بعد از سبقت گرفتن از یک وسیله نقلیه بزرگ ",
            " 2- در مسیر های باز بدون درخت و موانع طبیعی ",
            "در بخشی از شهرها که دارای ساختمتن نبوده و در معرض باد قرار دارند ",
            " 4- همه ی موارد"
        ),

        "#16. کدامیک از نوع آلودگی سهم زیادی از آلودگی ترافیک را تشکیل می دهد؟"
                to listOf(
            " 1- آلودگی دیداری و آلودگی شنیداری",
            " 2- آلودگی شنیداری",
            " 3- آلودگی هوا",
            " 4- آلودگی دیداری"
        ),

        "#17. معنی تابلوی شکل بالا چیست؟"
                to listOf(
            "  1- سرعت بیش از 30 کیلومتر در ساعت ممنوع",
            " 2- حداقل سرعت 30 کیلومتر در ساعت",
            " 3- پایان حداقل سرعت 30 کیلومتر در ساعت",
            " 4- هیچکدام "
        ),

        "#18. میزان کارایی روغن های معمولی موتور چند کیلومتر می باشد؟"
                to listOf(
            " 1- هزار کیلومتر",
            " 2- در حدود 80 تا 100 هزار کیلومتر",
            " 3- در حدود 2 هزار کیلومتر",
            " 4- در حدود 8 تا 10 هزار کیلومتر"
        ),

        "#19. دینام توسط کدامیک از موارد زیر می چرخد؟"
                to listOf(
            " 1- پیستون ",
            " 2- میل لنگ",
            " 3- دیفرانسیل",
            " 4- تسمه دینام "
        ),

        "#20. چه کسانی قانونا مجاز به هدایت ترافیک هستند؟"
                to listOf(
            " 1- با تشخیص خودمان ",
            " 2- پلیس راهنمائی و رانندگی ، پلیس مدرسه که مسئول کودکان در عبور از خیابان است ،کارگران تعمیرات ",
            " 3- هر کس که راننده وسیله نقلیه بزرگ را در دنده عقب کمک و راهنمائی می کند.",
            " 4- نیروی کمکی "
        ),

        "#21. پس از روشن کردن شدن موتور برای حرکت ابتدا چه عملی را باید انجام دهیم ؟"
                to listOf(
            " 1- با پای چپ کلاچ بگیرید ",
            " 2- بستن کمربند. ",
            " 3- صندلی را تنظیم نمایئم. ",
            " 4- آزمایش ترمز."
        ),

        "#22. رانندگی در بخش وسط راههایی که به وسیله خط کشی یا علائم دیگر به ۳ بخش تقسیم شده به جز چه مواردی ممنوع است؟"
                to listOf(
            " 1- برای گردش به راست",
            " 2- برای سبقت گرفتن یا گردش به راست ",
            " 3- برای دور زدن و گردش به چپ ",
            " 4- برای سبقت گرفتن یا گردش به چپ "
        ),

        "#23. چه مواقعی باید بیش از حد مراقب موتور سیکلت سوار ها و دوچرخه سوار ها باشید؟"
                to listOf(
            " 1- در محل تقاطع ها ",
            " 2- در خط کشی عابرین پیاده ",
            " 3- در خیابان های یکطرفه",
            " 4- در جاده های دو طرفه "
        ),

        "#24. هنرجوی گرامی تابلوی زیر بیانگر چیست؟"
                to listOf(
            " 1. سرعت کمتر از 70 کلیومتر بر ساعت ممنوع ",
            " 2. رعایت فاصله بیشتر از 70 متر ممنوع ",
            " 3. سرعت بیش از 70 کیلومتر بر ساعت ممنوع",
            " 4. رعایت فاصله کمتر از 70 متر ممنوع "
        ),

        "#25. مفهوم تابلو شکل زیر چیست؟"
                to listOf(
            " 1- گردش به راست ممنوع",
            " 2- گردش به راست ",
            " 3- پیچ به راست ",
            " 4- هیچ کدام "
        ),

        "#26. کدام یک از گزینه های زیر نادرست است؟"
                to listOf(
            " 1- سبقت از وسیله ای که خود در حال سبقت گرفتن است ممنوع است. ",
            " 2- در سر پیچ های تند و سر بالایی ها دور زدن ممنوع است. ",
            " 3- در تقاطع ها سبقت گرفتن ممنوع است.",
            " 4- سبقت گرفتن در تونل ها بلا مانع است. "
        ),

        "#27. عنوان این تابلو چیست؟"
                to listOf(
            " 1. پرتاب سنگ",
            " 2. خطر سقوط در آب",
            " 3. خطر پرتاب سنگ ",
            " 4. ریزش سنگ "
        ),

        "#28. کدام مورد در ایجاد سوانح ترافیکی نقش بیشتری دارد؟"
                to listOf(
            " 1- تایرها ",
            " 2- چراغ های راهنما ",
            " 3- سرعت مطمئنه",
            " 4- عدم رعایت نکات ایمنی"
        ),

        "#29. در این تصویر حق تقدم وسایل نقلیه به ترتیب از راست به چپ کدام است؟"
                to listOf(
            " 1- زرد - قرمز - سبز ",
            " 2- سبز - زرد - قرمز ",
            " 3- قرمز - سبز - زرد ",
            " 4- سبز - قرمز - زرد "
        ),

        "#30. مفهوم این تابلو چیست؟"
                to listOf(
            " 1. هتل",
            " 2. استراحتگاه اتو کاروان",
            " 3. محل مسکونی",
            " 4. استراحتگاه (کاروان)"
        ),



        )
    private val questionImages = listOf(

        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG3.9tqpmq3iVYTemn2dV1Ul?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG3.tcoRSuPhxdePE0C3BWE7?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG1.C_Ine16PLyCiFnrZgZpQ?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG1.lWZ.Whf11ix0FUUPaBZN?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG1.DoWIJ0I4ODsnXo5yvXAs?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.LvJWBgwhg2aUNtjaTPVE?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG3.hBihINhOqfk24h2yo5dh?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cVLNSyV6pnLr6aQvOMZM?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/05/3534677006.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/43366.jpg",
        "https://th.bing.com/th/id/OIG2.l5cG3Hvv5VeZoAetAuB0?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/66454567547.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.74UHWhF8CTy.7gukgF7L?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG3.JCJjeDRYzFgFB8j.wWsi?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.8sTMkZovjrjajhs3eZQp?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/04/64587844.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/8884745.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/04/46478885.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn ",
        "https://test-drive.ir/wp-content/uploads/2022/05/55684332.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/04/53457878.jpg",
    )

    private val correctAnswers = listOf(

        " 4- به ازای هر ۱۵ کیلومتر سرعت در ساعت فاصله ای به اندازه ی یک طول اتومبیل (حدود ۶ متر) نیاز است. " ,
        " 3- سی و پنج متری ",
        " 3- فرمان را محکم گرفته و به آرامی توقف کرد.",
        " 2- کم بودن میدان دید راننده",
        " 1- هرگونه استفاده از تلفن همراه شامل صحبت کردن، ارسال پیامک، دیدن تصاویر و ... ممنوع است. ",
        " 1- هفتاد متری",
        " 4- از نور پایین استفاده کرد.",
        " 4- تابلو های بازدارنده یا اخطاری" ,
        " 1- این کار ممنوع است. ",
        " 2- چون باعث کاهش دید می شود.",
        " 4- فرمان را به چپ(در جهت مخالف عقربه های ساعت).= " ,
        " 2- توقف کرده و مسئله را بررسی می کنید.",
        " 3- کم بودن باد لاستیک ها",
        " 4- آبی - زرد - سبز " ,
        " 4- همه ی موارد",
        " 3- آلودگی هوا",
        " 3- پایان حداقل سرعت 30 کیلومتر در ساعت",
        " 4- در حدود 8 تا 10 هزار کیلومتر" ,
        " 4- تسمه دینام " ,
        " 2- پلیس راهنمائی و رانندگی ، پلیس مدرسه که مسئول کودکان در عبور از خیابان است ،کارگران تعمیرات ",
        " 2- بستن کمربند. ",
        " 4- برای سبقت گرفتن یا گردش به چپ " ,
        " 1- در محل تقاطع ها ",
        " 4. رعایت فاصله کمتر از 70 متر ممنوع " ,
        " 3- پیچ به راست ",
        " 4- سبقت گرفتن در تونل ها بلا مانع است. " ,
        " 4. ریزش سنگ " ,
        " 4- عدم رعایت نکات ایمنی",
        " 4- سبز - قرمز - زرد " ,
        " 4. استراحتگاه (کاروان)"
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
        intent.putExtra("examId", "Azmon13")
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


