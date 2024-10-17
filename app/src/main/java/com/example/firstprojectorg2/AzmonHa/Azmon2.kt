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

class Azmon2 : AppCompatActivity() {

    private lateinit var binding: ActivityAzmonBinding
    private var currentQuestionIndex = 0
    private var questionCounter = 1

    private val questions = listOf(

        "#1 برای بهبود وضع ترافیک کدامیک از کارهای زیر را باید انجام داد؟"
                to listOf(" 1- رعایت قوانین و مقررات", " 2- رانندگی با سرعت پایین", " 3- رانندگی با احتیاط باسرعت زیاد", " 4- همه موارد بالا"),

        "#2. کدام گزینه به ترتیب اولویت صحیح است؟"
                to listOf(" 1- فرمان پلیس-تابلوها-خط کشی - چراغ راهنما", "  2- فرمان پلیس- چراغ راهنما - تابلوها - خط کشی", "  3- تابلوها- فرمان پلیس-چراغ راهنما-خط کشی", "  4- فرمان پلیس - خط کشی - چراغ راهنما-تابلوها"),

        "#3. هنرجوی گرامی مفهوم تابلوی شکل زیر چیست؟"
                to listOf(" 1- منطقه عبور معلولین", "  2- رانندگی با ویلچر", "  3- راننده معلول", "  4- محدوده راننده معلول"),

        "#4. برای امن کردن محل حادثه با چه اقدامی میتوان صحنه را عاری از خطر نمود؟"
                to listOf(" 1- خاموش کردن وسیله نقلیه حادثه دیده ", "  2- مراقب سلامت خود باشید.", " 3- مصدوم را تا حد ممکن از محل دور نمایید.", "  4- موارد یک و دو"),

        "#5. هنرجوی گرامی حق تقدم عبور را در این تقاطع مشخص کنید."
                to listOf("  1- سبز – آبی – نارنجی – زرد", "  2- زرد- آبی – سبز – نارنجی", " 3- زرد – آبی – نارنجی – سبز ", "  4- آبی – زرد – نارنجی - سبز"),

        "#6. مفهوم تابلو زیر چیست؟"
                to listOf("  1- سبقت ممنوع", " 2- ورود ممنوع ", "  3- سبقت کامیون ممنوع", " 4- عبور سواری ممنوع "),

        "#7. ترمز سرویس (ترمز پایی) مطابق با الزامات استانداردهای خودرویی باید بر …… عمل کند."
                to listOf(" 1- فقط بر چرخ های محرک. ", "  2- فقط بر چرخ های جلو.", "  3- بر تمامی چرخ ها عمل کند.", " 4- فقط بر چرخ های عقب. "),

        "#8. در حال رانندگی هستید راننده خودروی جلویی دست خود را به صورت تصویر زیر از وسیله نقلیه خود بیرون می آورد مفهوم آن چیست؟\n"
                to listOf(" توقف", "  2- آهسته بیایید", "  3- احتیاط", "  4- دور زدن"),

        "#9. کدام گزینه در مورد حداقل فاصله طول ایمن با خودروی جلوئی در شرایط برف صحیح نمی باشد؟"
                to listOf("  1- به ازای هر 15 کیلومتر 3 طول اتومبیل", "  2- به ازای هر 60 کیلومتر 13 طول اتومبیل", "  3- به ازای هر45 کیلومتر 9 طول اتومبیل", " 4- به ازای هر 30کیلومتر 6 طول اتومبیل "),

        "#10. زمان رسیدن به محل تصادف چه اقداماتی باید انجام داد؟"
                to listOf("  1- اتومبیل را از محل حادثه کنار بکشید.", " 2- در صورت آتش سوزی با آب خاموش کنید. ", " موتور اتومبیل حادثه دیده را خاموش کرده و افراد زخمی را از داخل اتومبیل خارج کرده و زخمی ها را با صحبت آرام نمایید.", " 4- به محض رسیدن اتومبیل دیگری ما به راه خود ادامه می دهیم"),

        "#11. هنرجوی گرامی مفهوم شکل زیر چیست؟"
                to listOf(" 1-توقف برروی آن ممنوع است و همواره باید در مسیر مزبور وسیله نقلیه باشد. ", "  2- قبل از محل خط کشی متوقف شده و مسیر برای حرکت دیگر خودروها باز نگه داشته شود.", "  3- توقف بر روی آن ممنوع نیست و همواره باید مسیر مزبور خالی از وسیله نقلیه باشد.", "  4-توقف بر روی آن ممنوع نیست و همواره باید مسیر مزبور در حال عبور وسیله نقلیه باشد."),

        "#12. چرا در سطح جاده های خیس ،برفی،لغزنده باید از دنده سنگین استفاده کرد؟"
                to listOf("  1- جهت ترمز سریع", " 2- جهت ممانعت از چپ و راست رفتن خودرو ", " 3- جهت حرکت سریع ", " 4- جهت ممانعت از لغزش چرخ ها "),

        "#13. بر روی یک راه لغزنده قسمت عقب اتومبیل شما به سمت راست میلغزد،چکار باید انجام دهید؟"
                to listOf(" 1- بادقت و مراقبت فرمان را به سمت چپ بچرخانید.", "  2- با دقت و مراقبت فرمان را به سمت راست بچرخانید.", " 3- ترمز کنید وفرمان را بچرخانید. ", "  4- از کلاچ و ترمز به طور همزمان استفاده کنید."),

        "#14. مفهوم تابلو شکل زیر چیست؟"
                to listOf("  1- تقاطع فرعی به اصلی", " 2- تقاطع", "  3- دو راهی", " 4- تقاطع اصلی به فرعی "),

        "#15. در کدام یک از موارد زیر بایستی از رانندگی خودداری نمود؟"
                to listOf(" 1- عصبانیت شدید در شرایط جسمی نامناسب. ", "  2- هنگام بارندگی شدید.", "  3- نداشتن جعبه کمکهای اولیه در داخل خودرو.", "  4- سوختن چراغ داخل خودرو."),

        "#16. در برخورد با یک مصدوم بیهوش، مهم ترین اقدام کدام است؟"
                to listOf("  1- با کشیدن پتو بدن او را گرم نگه دارید.", " 2- باز کردن راه هوایی تنفس. ", " 3- دادن مایعات و نوشیدنی گرم ", "  4- مصدوم را با تکان دادن بدنش به هوش آورید."),

        "#17. کدام یک از گزینه های زیر ناصحیح است؟"
                to listOf("  1- سبقت گرفتن در تونل ها بلامانع است.", "  2- در تقاطع سبقت ممنوع است.", " 3- در سر پیچ های تند وسربالایی ها دور زدن ممنوع است. ", "  4- سبقت از وسیله ای که خود در حال سبقت گرفتن است ممنوع است."),

        "#18. کدام جمله درباره کیسه هوا صحیح نمی باشد؟"
                to listOf(" 1- فعال شدن کیسه هوا در حالتی که کمربند ایمنی بسته نشده باشد نتایج مخرب تری خواهد داشت. ", "  2- در خودروهای مجهز به کیسه هوا دیگر نیازی به کمربند ایمنی نمی باشد.", "  3- یکی از تکنولوژی های پیشرفته در زمینه ایمنی خودرو، سیستم کیسه هوا یا ایربگ می باشد.", "  4- کیسه هوا، هنگام تشخیص ضربه ناشی از تصادف در کسری از ثانیه باز می شود."),

        "#19. حواس پرتی در زمان رانندگی عامل مهمی در………..است"
                to listOf("  1- عدم توجه به ترافیک شهری", "  2- فرامین پلیس", "  3- عدم توجه به پیچ ها در جاده", "  4- عدم تصمیم گیری به موقع"),

        "#20. حق تقدم عبور عبارت است از……"
                to listOf(" 1-رعایت حقوق رانندگان", " 2- اولویت در عبور از چراغ های راهنمایی ", "  3- اولویت حق عبور وسیله نقلیه ای که زودتر از وسایل نقلیه دیگر یا از پیاده ها و بالعکس", "  4- ترتیب عبور و مرور در چهارراه ها"),

        "#21. مفهوم تابلوی شکل زیر چیست؟"
                to listOf(" 1- عبور وسائط نقلیه موتوری ممنوع ", "  2- عبور کلیه وسائط نقلیه ممنوع", "  3- عبور فقط با زنجیر چرخ", "  4- راه لغزنده"),

        "#22. هنرجوی گرامی حق تقدم عبور در تقاطع شکل بالا را بر اساس رنگ وسیله نقلیه مشخص نمایید."
                to listOf("  1- سبز - زرد - نارنجی - بنفش", "  2- زرد - نارنجی - سبز - بنفش", "  3- نارنجی - زرد - بنفش - سبز", " 4- بنفش - نارنجی - سبز - زرد "),

        "#23. چراغ های جانبی جلو چیست؟"
                to listOf("  1- چراغ هایی است که حضور وسیله نقلیه و عرض آن را از سمت جلو نشان می دهد.", " 2- چراغ هایی است که حضور وسیله نقلیه را از سمت جلو نشان می دهد. ", "  3- چراغ هایی است که حضور وسیله نقلیه و طول آن را از سمت جلو نشان می دهد.", "  4- چراغ های اصلی وسیله نقلیه است."),

        "#24. سبقت گرفتن از طرف راست وسیله نقلیه ای که در حال گردش به چپ می باشد."
                to listOf("  1- فقط در معابر شریانی درجه دو ممنوع است.", "  2- ممنوع است.", "  3- در هنگام روز مجاز است.", " 4- مجاز است. "),

        "#25. چه زمانی شما می توانید محل حادثه تصادف را ترک نمایید؟"
                to listOf("  1- هر وقت اراده کنید می توانید صحنه تصادف را ترک کنید.", "  2- بعد از اطمینان پیدا کردن از حال مصدومین.", " 3- بعد از تماس با اورژانس می توانید صحنه تصادف را ترک کنید.", "تا زمانی که گزارش کامل کارهای انجام شده در خصوص مصدوم رابه پزشک یاامدادگر ارائه نموده ومطمئن شوید که به کمک شمادرمراحل بعدی نیازی نیست"),

        "#26. به عنوان یک کمک دهنده به یک مصدوم باید چه مواردی را اولیت قرار داد؟"
                to listOf("  1- ارزیابی موقعیت و امن کردن محل حادثه", " 2- ارائه کمک های اولیه", "  3- در خواست کمک", " 4- همه موارد "),

        "#27. مفهوم این شکل چیست؟"
                to listOf(" 1- خط توقف _ رانندگان باید توقف کامل نمایند و سپس با رعایت احتیاط عبور کنند.", " 2- خط توقف دوبله مقطع _ رانندگان از سرعت خود کم کرده و با احتیاط و رعایت حق تقدم عبور کنند. ", " 3- خط مقطع _ رانندگان سرعت خود را کاهش داده و به راست گردش کنند. ", "  4- رانندگان با سرعت و رعایت حق تقدم عبور کنند."),

        "#28. زمان درج شده در متن برگ معاینه فنی وسیله نقله چیست؟"
                to listOf(" 1- تاریخ صدور برگ معاینه فنی ", "  2- تاریخ اخذ برگ معاینه فنی", "  3- اعتبار برگ معاینه فنی", "  4- هیچ کدام"),

        "#29. ابطال گواهی نامه چیست؟"
                to listOf("  1- اخذ گواهی نامه رانندگی.", "  2- صدور گواهینامه رانندگی.", "  3- تمدید اعتبار قانونی گواهی نامه رانندگی.", "  4- سلب اعتبار قانونی گواهی نامه رانندگی."),

        "#30. کدام یک از موارد زیر جزو رفتار ناهنجار اجتماعی و ناپسند نمی باشد؟"
                to listOf("  1- نصب بوق های گوش خراش", " 2- رانندگی با سرعت مجاز ", " 3- نصب سیستم های صوتی پر قدرت ", "  4- استفاده از المپ های غیر مجاز"),


        )

    private val questionImages = listOf(

        "https://th.bing.com/th/id/OIG2.l8nfNIkEZtk3S8mz572o?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG4.O2ysDWUdO9JpOJ8MeXli?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/07654565.jpg",
        "https://th.bing.com/th/id/OIG3.Q2N2lWTs5Lq3hEQyew25?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/04/6457588989.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/368955756.jpg",
        "https://th.bing.com/th/id/OIG3.Omnk4F.QmyV7l1urMaSu?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn&cb=1717359857132",
        "https://test-drive.ir/wp-content/uploads/2022/03/435765487.jpg",
        "https://th.bing.com/th/id/OIG1.vDMlWk5HSN10OvCDjm1g?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.Xde8xuHDJtMI5NjajekI?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/43457548.jpg",
        "https://th.bing.com/th/id/OIG4.olC6jAuF5.1GEDXo7dJj?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG1.K1RvGRZOoMDuqldvg8.1?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/34890065.jpg",
        "https://th.bing.com/th/id/OIG2.1.cg_HxtWzKW7geDMH8b?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG4.gjwGj2UDKkcNB3O4I80h?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.ttNFSyTjsPIWMIwn6ChU?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG3.ZoMesaF34Mlf1YP9YRa8?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/43788.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/5367885.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/4574833.jpg",
        "https://th.bing.com/th/id/OIG3.UhJNp6pTBOxA2zeHTd1Z?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/05/43622687.jpg",
        "https://th.bing.com/th/id/OIG2.XQsz7TUOg6YysrccAVkR?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.oFJ8t0iWMu48Sp1dtqu5?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/37357886.jpg",
        "https://th.bing.com/th/id/OIG2.Clpo8XuhH4XfjiBdb1cG?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn" ,
        "https://th.bing.com/th/id/OIG3.htGRmKVm9cpb3xzVL2hn?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/7547456868.jpg"
    )

    private val correctAnswers = listOf(

        " 1- رعایت قوانین و مقررات",
        "  2- فرمان پلیس- چراغ راهنما - تابلوها - خط کشی",
        "  3- راننده معلول",
        "  4- موارد یک و دو" ,
        "  4- آبی – زرد – نارنجی - سبز" ,
        " 4- عبور سواری ممنوع " ,
        "  3- بر تمامی چرخ ها عمل کند.",
        " توقف",
        "  2- به ازای هر 60 کیلومتر 13 طول اتومبیل",
        " موتور اتومبیل حادثه دیده را خاموش کرده و افراد زخمی را از داخل اتومبیل خارج کرده و زخمی ها را با صحبت آرام نمایید.",
        "  2- قبل از محل خط کشی متوقف شده و مسیر برای حرکت دیگر خودروها باز نگه داشته شود.",
        " 4- جهت ممانعت از لغزش چرخ ها " ,
        "  2- با دقت و مراقبت فرمان را به سمت راست بچرخانید.",
        " 2- تقاطع",
        " 1- عصبانیت شدید در شرایط جسمی نامناسب. ",
        " 2- باز کردن راه هوایی تنفس. ",
        "  1- سبقت گرفتن در تونل ها بلامانع است.",
        "  2- در خودروهای مجهز به کیسه هوا دیگر نیازی به کمربند ایمنی نمی باشد.",
        "  4- عدم تصمیم گیری به موقع" ,
        "  3- اولویت حق عبور وسیله نقلیه ای که زودتر از وسایل نقلیه دیگر یا از پیاده ها و بالعکس" ,
        "  3- عبور فقط با زنجیر چرخ",
        "  3- نارنجی - زرد - بنفش - سبز",
        "  1- چراغ هایی است که حضور وسیله نقلیه و عرض آن را از سمت جلو نشان می دهد.",
        " 4- مجاز است. " ,
        " تا زمانی که گزارش کامل کارهای انجام شده در خصوص مصدوم را به پزشک یا امدادگر ارائه نموده و مطمئن شوید که به کمک شما در مراحل بعدی نیازی نیست" ,
        " 4- همه موارد " ,
        " 2- خط توقف دوبله مقطع _ رانندگان از سرعت خود کم کرده و با احتیاط و رعایت حق تقدم عبور کنند. ",
        "  3- اعتبار برگ معاینه فنی",
        "  4- سلب اعتبار قانونی گواهی نامه رانندگی.",
        " 2- رانندگی با سرعت مجاز "
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
        intent.putExtra("examId", "Azmon2")
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