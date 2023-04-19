package id.rafli.baseproject.base

import android.app.Application
import android.os.Build
import android.os.Environment
import android.os.StrictMode
import android.view.View
import com.google.android.material.snackbar.Snackbar
import id.rafli.baseproject.enum.TimeOfDay
import id.rafli.baseproject.helper.Config.DIRECTORY_IMAGE
import id.rafli.baseproject.helper.Helper
import id.rafli.baseproject.helper.SharedPref
import id.rafli.baseproject.model.User
import java.io.File
import java.util.*

class App : Application() {

    companion object {
        lateinit var pref: SharedPref
        lateinit var user: User

        var helper = Helper

        var accessToken = ""
        var refreshToken = ""
        lateinit var app:App

    }

    fun clearAppData(){
        pref.clearAll()

        user = User()
    }



    override fun onCreate() {
        super.onCreate()
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        pref = SharedPref(this)
        updateUserData()
        updateStorage()
        app = this

    }

    fun updateUserData(){
        accessToken = pref.getAccessToken()
        refreshToken = pref.getRefreshToken()
        user = pref.getUser()
    }

    fun showSnackbar(view: View, message:String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }

    fun getTimeOfDay(): TimeOfDay {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        return when (hour) {
            in 6..11 -> TimeOfDay.MORNING
            in 12..15 -> TimeOfDay.AFTERNOON
            in 16..18 -> TimeOfDay.EVENING
            else -> TimeOfDay.NIGHT
        }
    }

    private fun updateStorage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            DIRECTORY_IMAGE =  getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString()
            val sdIconStorageDir = File(DIRECTORY_IMAGE)
            if (!sdIconStorageDir.isDirectory) {
                //BUAT DIRECTORY
                sdIconStorageDir.mkdirs()
            }
        } else{
            val sdIconStorageDir = File(DIRECTORY_IMAGE)
            if (!sdIconStorageDir.isDirectory) {
                //BUAT DIRECTORY
                sdIconStorageDir.mkdirs()
            }
        }
    }

}