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

class Azmon3 : AppCompatActivity() {
    private lateinit var binding: ActivityAzmonBinding
    private var currentQuestionIndex = 0
    private var questionCounter = 1 // شمارنده سوالات
    private val questions = listOf(

        "#1. کدام یک از موارد زیر در خصوص حرکت با دنده عقب صحیح است؟"
                to listOf(" 1- در حرکت با دنده عقب بایستی از سمت چپ پشت سر را نگاه نمود.", " 2- در حرکت با دنده عقب باید از فردی که در کنار شما در خودرو است، بخواهید به عقب نگاه کند.", "  3- در حرکت با دنده عقب باید میدان دید عقب را کنترل نموده و به آرامی و با نگاه کردن به فضای پشت حرکت کرد.", " 4- در حرکت با دنده عقب نیازی به نگاه کردن به پشت سر نبوده و صرفاً از طریق آیینه ها میتوان این کار را انجام داد."),

        "#2. کدامیک از موارد زیر علت اصلی تصادف با اتومبیل جلویی است؟"
                to listOf(" 1- عدم توجه به راه و نزدیک شدن بیش از حد به اتومبیل جلویی", " 2- سرعت غیرمجاز", " 3- عبور عابر پیاده در مناطق شهری و شلوغ ", " 4- وجود ترافیک سنگین در راهها"),

        "#3. برای راه اندازی خودرو راننده در چه مرحله ای بایستی کمربند ایمنی را ببندد؟"
                to listOf(" 1- در حین حرکت", " 2- قبل از روشن کردن موتور خودرو", "  3- پس از روشن کردن موتور خودرو", " 4- نیازی به بستن کمربند ایمنی نمیباشد."),

        "#4. تابلو های منع کننده به رنگ….. و تابلو ها در آزاد راهها به رنگ….و در بزرگراهها به رنگ…..می باشد."
                to listOf(" 1- قرمز - سبز - آبی", " 2- قرمز - آبی - سبز", " 3- قرمز - سیاه - سبز ", " 4- سبز - آبی - قرمز"),

        "#5. نحوه گرفتن صحیح فرمان اتومبیل در حالت معمول کدام است؟"
                to listOf(" 1- با یک دست فرمان و با دست دیگر دنده را می گیریم.", " 2- با هر دو دست و از خارج فرمان را می گیریم.", " 3- با یک دست خارج فرمان و با دست دیگر داخل فرمان. ", " 4- با هر دو دست محکم فرمان را در پایین نگه می داریم."),

        "#6. در حین رانندگی و در صورت خستگی بایست….."
                to listOf(" 1- سرعت را کم کرد و به راه ادامه دهیم", " 2- به محض توانستن اتومبیل را متوقف کنید و در معرض هوای تازه قرار بگیرید.", " 3- با رعایت حق تقدم تغییر مسیر سرعت داده و از سرعت خود بکاهید ", " 4- بر سرعت خود می افزائیم تا زودتر به مقصد برسیم"),

        "#7. شما با سرعت ۶۰ کیلومتر در ساعت در یک جاده مرطوب در حال رانندگی هستید، فاصله شما با اتومبیل جلوئی چند طول اتومبیل باید باشد؟"
                to listOf("  8", "6", " 12", "9"),

        "#8. در هنگام پارک در سربالایی و در راه بدون جدول، فرمان وسیله نقلیه باید به کدام سمت چرخانده شود؟"
                to listOf(" 1- به سمت چپ", " 2- تفاوتی ندارد.", "  3- سمت راست", " 4- مخالف عقربه ساعت"),

        "#9. در جاده های فرعی حداکثر میزان سرعت مجاز در روز…..کیلومتر در ساعت و در شب…..کیلومتر در ساعت می باشد؟"
                to listOf("95-85", "90-80", "75-85 ", "80-70"),

        "#10. در حال رانندگی هستید راننده خودروی جلوی دست خود را به صورت تصویر بالا از وسیله نقلیه خود بیرون میاورد مفهوم آن چیست؟"
                to listOf(" 1- گردش یا تغییر مسیر به راست", " 2- گردش یا تغییر مسیر به چپ", "  3- گردش و توقف", " 4- توقف"),

        "#11. تابلو های راهنمایی که فرمانی صادر می کند به چه شکل است؟"
                to listOf(" 1- مستطیل با حاشیه قرمز", " 2- مثلث با حاشیه قرمز", " 3- مستطیل فلش دار با حاشیه قرمز ", " 4- دایره با حاشیه قرمز"),

        "#12. در کدامک از موارد زیر دور کردن خود و اطرافیان از صحنه تصادف از اهمیت بیشتری نسبت به سایر موارد برخوردار است؟"
                to listOf(" 1- در مواردی که خودرو در کنار راه قرار نداشته باشد.", " 2- در حوادثی که تعداد مصدومین کم می باشد.", "  3- در حوادثی که خطراتی نظیر جاری شدن مواد خطر ساز یا گازهای سمی وجود دارد.", " 4- در تصادفات خودروهای عمومی که امدادرسانی کلیه افراد ممکن نمی باشد."),

        "#13. مراحل اساسی که در هنگام تصادف و آسیب جدی شدید لازم است اتخاذ شود عبارتند از:"
                to listOf(" 1- حفظ صحنه تصادف", " 2- آگاه ساختن مراجع ذیصلاح", " 3- مراقبت نمودن از مجروحین ", " 4- هر سه مورد فوق"),

        "#14. هنگامیکه در شب فاصله اتومبیل شما با اتومبیل جلوئی بیش از ۱۵۰ متر باشد……"
                to listOf(" 1- از نور پایین استفاده می کنیم.", " 2- چراغها را خاموش میکنیم.", "  3- از نور بالا استفاده میکنیم.", " 4- از هر نوری که خواستیم استفاده میکنیم."),

        "#15. معیوب بودن سیستم تعلیق یک خودرو موجب میشود ……"
                to listOf(" 1- افزایش آلایندگی گازهای خروجی از موتور شود.", " 2- آب رادیاتور جوش بیاورد.", " 3- فرمانپذیری و سیستم هدایت خودرو دچار اختلال شود. ", " 4- روغن موتور به مرور زمان کاهش یابد."),

        "#16. حرکت کردن از روی خطوط هاشور در حاشیه راهها که معمولاً کناره اتوبانها، آزادراهها، بزرگراهها و تونلها کشیده میشوند، ……………"
                to listOf(" 1- مجاز است.", " 2- در شیبها مجاز است.", " 3- در صورت ترافیک سنگین مجاز است. ", " 4- ممنوع است."),

        "#17. در تقاطع زیر حق تقدم عبور را مشخص کنید."
                to listOf(" 1- سواری مشکی - سواری قرمز- کامیون - دوچرخه", " 2- سواری قرمز - سواری مشکی - دوچرخه - کامیون", "  3- کامیون - دوچرخه - سواری مشکی - سواری قرمز", " 4- دوچرخه - سواری مشکی - کامیون - سواری قرمز"),

        "#18. رانندگان کلیه وسایل نقلیه موظفاند از کدام خط عبور راه حرکت نمایند؟"
                to listOf(" 1- خط وسط", " 2- سمت چپ", "  3- سمت راست", " 4- خط زیگزاگ"),

        "#19. هنگامی که پلیس دست راست خود را به صورت قائم بالا نگه داردبه معنای این است؟"
                to listOf(" 1- رانندگان بایستند.", " 2- رانندگان با سرعت عبور کنند.", " 3- بعد از عبور درپشت او بایستند. ", " 4- رانندگان در 4 طرف تقاطع باید پشت خط ایست توقف نمایند."),

        "#20. در تقاطع شکل زیر حق تقدم عبوربا کدام وسیله است؟(آمبولانس در حال ماموریت)"
                to listOf(" 1- آمبولانس - صورتی - مشکی", " 2- آمبولانس - مشکی - صورتی", "  3- مشکی - آمبولانس - صورتی", " 4- مشکی - صورتی - آمبولانس"),

        "#21. مفهوم تابلوی شکل بالا چیست ؟"
                to listOf(" 1- فقط عبور کامیون", " 2- عبور کامیون ممنوع", " 3- مسیر توصیه شده برای وسایل سنگین ", " 4- گزینه 1 و 2"),

        "#22. مهمترین نکته ای که در خرید خودرو باید در نظر داشت چیست؟"
                to listOf(" 1. نوع خودرو", " 2. زیبایی ظاهری", "  3. تجهیزات ایمنی", " 4. سطح ایمنی"),

        "#23. در چه شرایطی می توان مانع سبقت گرفتن خودروی در حال سبقت شد؟"
                to listOf(" 1. زمانی که هوا رو به تاریکی است.", " 2. زمانی که سرعت خودرو سبقت گیرنده خیلی زیاد باشد.", " 3. زمانی که شرایط بارانی یا برفی است. ", " 4. در هیچ شرایطی"),

        "#24. هنگام سبقت گرفتن اعم از اینکه روز باشد یا شب، برای جلب توجه وسایل نقلیه پشت سر، انجام کدام مورد زیر ضروری است؟"
                to listOf(" 1. بوق زدن", " 2. تبدیل نور چراغ خودرو", "  3. اشاره با دست", " 4. زدن چراغ راهنما"),

        "#25. کدامیک از موارد زیر در خصوص عبور از روی خط آهن صحیح می باشد؟"
                to listOf(" 1. در طول عبور از خط آهن دنده خودرو باید از دنده سنگین به دنده سبک تغییر داد.", " 2. در طول عبور از خط آهن دنده خودرو باید از دنده سبک به دنده سنگین تغییر داد.", " 3. در طول عبور از خط آهن دنده خودرو را نباید تعویض نمود.", " 4. قبل از رسیدن به خط آهن باید سرعت را افزایش داده و با سرعت از خط آهن عبور نمود."),

        "#26. هنرجوی گرامی تابلوی زیر بیانگر چیست؟"
                to listOf(" 1. عبور موتور گازی ممنوع", " 2. سبقت موتور سیکلت ممنوع", "  3. عبور وسائط نقلیه موتوری ممنوع", " 4. عبور موتور سیکلت ممنوع"),

        "#27. تابلو شکل بالا به چه معنا می باشد؟"
                to listOf(" 1- پایان خیابان اصلی", " 2- خیابان اصلی", "  3- آزاد راه", " 4- پایان آزاد راه"),

        "#28. حداکثر سرعت در بزرگراه های واقع در شهرها و مناطق مسکونی چند کیلومتر در ساعت است؟"
                to listOf(" 1- 100 کیلومتر بر ساعت", " 2- 110 کیلومتر بر ساعت", " 3- 120 کیلومتر بر ساعت ", " 4- 125 کیلومتر بر ساعت"),

        "#29. تابلوی زیر چه مفهومی دارد؟"
                to listOf(" 1- بن بست", " 2- تقاطع هم سطح در قوس", "  3- تقاطع فرعی با اصلی در قوس", " 4- گردش به راست و چپ"),

        "#30. تابلوی بالا به چه معناست؟"
                to listOf(" 1- دورزدن در میدان", " 2- میدان", "  3- تعیین حرکت", " 4- جهت عبور در میدان"),


        )

    private val questionImages = listOf(
        "https://th.bing.com/th/id/OIG1.fc.mu9Qcr600wlGToy4v?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.pDPjHJQYJVLz.a5matTW?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG4.WizIEiocEm3LZlTSAKFj?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/43647558.jpg",
        "https://th.bing.com/th/id/OIG1.a3IWGGo_XlCp2ukht08S?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.j8rhjgKbINxe7MxANNEc?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG1.JoLhDRFY.Q3kF7OiVJaW?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.4Dy7a79S2fItdygUt8jL?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn" ,
        "https://test-drive.ir/wp-content/uploads/2022/03/4387328.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/457686598.jpg",
        "https://th.bing.com/th/id/OIG4.dPOlJXZG6TTp.ybQ2c.G?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG4.tt2oaec0qbebgjxOms1X?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn" ,
        "https://th.bing.com/th/id/OIG4.SvO_WSexWieCdCHn5LDi?w=1024&h=1024&rs=1&pid=ImgDetMain",
        "https://test-drive.ir/wp-content/uploads/2022/03/33578.jpg",
        "https://test-drive.ir/wp-content/uploads/2023/10/4688443.jpg",
        "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn" ,
        "https://test-drive.ir/wp-content/uploads/2023/10/446789967.jpg",
        "https://test-drive.ir/wp-content/uploads/2023/10/4848934.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/754848.jpg",
        "https://th.bing.com/th/id/OIG3.yjgeMXnGxiQXP7xwjhOa?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/00567856.jpg",
        "https://th.bing.com/th/id/OIG3.3DnekE4T88U2wNz6DPQ9?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://th.bing.com/th/id/OIG4.40ZfBCzVrt7h7dPmu.7.?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/04/346346778.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/94343737.jpg",
        "https://th.bing.com/th/id/OIG3.UgUsovZm.vHlqrVRlW6r?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
        "https://test-drive.ir/wp-content/uploads/2022/03/34008.jpg",
        "https://test-drive.ir/wp-content/uploads/2022/03/87998055.jpg",
    )

    private val correctAnswers = listOf(

        "  3- در حرکت با دنده عقب باید میدان دید عقب را کنترل نموده و به آرامی و با نگاه کردن به فضای پشت حرکت کرد.",
        " 1- عدم توجه به راه و نزدیک شدن بیش از حد به اتومبیل جلویی",
        "  3- پس از روشن کردن موتور خودرو",
        " 2- قرمز - آبی - سبز",
        " 2- با هر دو دست و از خارج فرمان را می گیریم.",
        " 2- به محض توانستن اتومبیل را متوقف کنید و در معرض هوای تازه قرار بگیرید.",
        "  8",
        "  3- سمت راست",
        "75-85 ",
        " 2- گردش یا تغییر مسیر به چپ",
        " 4- دایره با حاشیه قرمز",
        "  3- در حوادثی که خطراتی نظیر جاری شدن مواد خطر ساز یا گازهای سمی وجود دارد.",
        " 4- هر سه مورد فوق",
        "  3- از نور بالا استفاده میکنیم.",
        " 3- فرمانپذیری و سیستم هدایت خودرو دچار اختلال شود. ",
        " 4- ممنوع است." ,
        " 4- دوچرخه - سواری مشکی - کامیون - سواری قرمز" ,
        "  3- سمت راست",
        " 4- رانندگان در 4 طرف تقاطع باید پشت خط ایست توقف نمایند." ,
        " 2- آمبولانس - مشکی - صورتی",
        " 3- مسیر توصیه شده برای وسایل سنگین ",
        " 4. سطح ایمنی",
        " 4. در هیچ شرایطی",
        " 4. زدن چراغ راهنما",
        " 3. در طول عبور از خط آهن دنده خودرو را نباید تعویض نمود.",
        " 4. عبور موتور سیکلت ممنوع",
        " 2- خیابان اصلی",
        " 1- 100 کیلومتر بر ساعت",
        "  3- تقاطع فرعی با اصلی در قوس",
        " 4- جهت عبور در میدان"
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
        intent.putExtra("examId", "Azmon3")
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


