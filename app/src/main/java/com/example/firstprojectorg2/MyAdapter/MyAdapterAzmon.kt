import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstprojectorg2.AzmonHa.ActivityAzmon
import com.example.firstprojectorg2.AzmonHa.Azmon10
import com.example.firstprojectorg2.AzmonHa.Azmon11
import com.example.firstprojectorg2.AzmonHa.Azmon12
import com.example.firstprojectorg2.AzmonHa.Azmon13
import com.example.firstprojectorg2.AzmonHa.Azmon14
import com.example.firstprojectorg2.AzmonHa.Azmon15
import com.example.firstprojectorg2.AzmonHa.Azmon2
import com.example.firstprojectorg2.AzmonHa.Azmon3
import com.example.firstprojectorg2.AzmonHa.Azmon4
import com.example.firstprojectorg2.AzmonHa.Azmon5
import com.example.firstprojectorg2.AzmonHa.Azmon6
import com.example.firstprojectorg2.AzmonHa.Azmon7
import com.example.firstprojectorg2.AzmonHa.Azmon8
import com.example.firstprojectorg2.AzmonHa.Azmon9
import com.example.firstprojectorg2.R

class MyAdapterAzmon(val context: Context) : RecyclerView.Adapter<MyAdapterAzmon.MyViewHolder>() {

    private val activities = listOf(
        ActivityAzmon::class.java,
        Azmon2::class.java,
        Azmon3::class.java,
        Azmon4::class.java,
        Azmon5::class.java,
        Azmon6::class.java,
        Azmon7::class.java,
        Azmon8::class.java,
        Azmon9::class.java,
        Azmon10::class.java,
        Azmon11::class.java,
        Azmon12::class.java,
        Azmon13::class.java,
        Azmon14::class.java,
        Azmon15::class.java,
    )

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView28: TextView = itemView.findViewById(R.id.textView28)
        val textView29: TextView = itemView.findViewById(R.id.textView29)
        val font_txt: TextView = itemView.findViewById(R.id.font_txt)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION && position < activities.size) {
                    val intent = Intent(context, activities[position])
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.recycler_view_azmon, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return activities.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.textView28.text = "آزمون شماره ${position + 1}"
        holder.font_txt.text= "${position+1}"
        holder.textView29.text = "4 اشتباه مجاز است"
    }
}


