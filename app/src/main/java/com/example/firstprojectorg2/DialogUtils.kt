package com.example.firstprojectorg2



import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

object DialogUtils {
    fun showNoInternetDialog(context: Context) {
        AlertDialog.Builder(context)
            .setTitle("No Internet Connection")
            .setMessage("Please turn on your internet connection to use this app.")
            .setCancelable(false)
            .setPositiveButton("Retry") { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
                if (!NetworkUtils.isNetworkAvailable(context)) {
                    showNoInternetDialog(context)
                } else {
                    // If internet is available after retry, proceed to MainActivity
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    (context as AppCompatActivity).finish()
                }
            }
            .setNegativeButton("Exit") { _, _ ->
                (context as AppCompatActivity).finish()
            }
            .show()
    }
}
