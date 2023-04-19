package id.rafli.baseproject.base

import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import id.rafli.baseproject.R
import id.rafli.baseproject.databinding.LayoutToolbarBinding
import id.rafli.baseproject.helper.SweetAlert


open class BaseActivity : AppCompatActivity() {

    fun removeActionBar() {
        supportActionBar?.hide()

        val window: Window = window
        val decorView: View = window.decorView
        val wic = WindowInsetsControllerCompat(window, decorView)


        wic.isAppearanceLightStatusBars = false  // true or false as desired.
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

    }


    fun setToolbar(title: String, toolbarTitle: LayoutToolbarBinding, menuCallback: MenuCallback? = null, isWhite:Boolean = false) {
        removeActionBar()
        if (menuCallback != null){
            toolbarTitle.toolbarMenu.visibility = View.VISIBLE
            toolbarTitle.toolbarMenu.setOnClickListener {
                menuCallback.onClicked()
            }
        }else{
            toolbarTitle.toolbarMenu.visibility = View.GONE
        }

        if(isWhite) {
            toolbarTitle.toolbarBack.setImageResource(R.drawable.ic_back_white)
            toolbarTitle.toolbarTitle.setTextColor(ContextCompat.getColor(this, R.color.white))
        }

        toolbarTitle.toolbarTitle.text = title
        toolbarTitle.toolbarBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        //mengubah warna status bar
    }

    fun removeLoading() {
        SweetAlert.dismis()
    }

    fun showLoading() {
        SweetAlert.onLoading(this)
    }
}

interface MenuCallback {
    fun onClicked()
}