package com.d108.sduty.utils

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.util.TypedValue
import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
import android.view.WindowInsetsController
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.d108.sduty.R
import com.sendbird.calls.AudioDevice
import java.lang.Exception

fun Activity.hideKeyboard() {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager ?: return
    if (inputMethodManager.isAcceptingText) {
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}

fun Context.dpToPixel(dp: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        resources.displayMetrics
    ).toInt()
}

fun Activity.showAlertDialog(title: String, message: String, listener: DialogInterface.OnClickListener?) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton("확인", listener)
        .setNegativeButton("취소", null)
        .create()
        .show()
}

fun Context.copyText(text: String) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
    clipboard?.setPrimaryClip(ClipData.newPlainText("복사 되었습니다.", text))
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun AudioDevice.toReadableString(): String {
    return when (this) {
        AudioDevice.SPEAKERPHONE -> "스피커폰"
        AudioDevice.EARPIECE -> "통화 모드"
        AudioDevice.WIRED_HEADSET -> "유선 이어폰"
        AudioDevice.BLUETOOTH -> "블루투스"
    }
}

fun navigateBack(activity: Activity){
    Navigation.findNavController(activity, R.id.frame_main).popBackStack()
}
fun NavController.safeNavigate(action: NavDirections){
    navigate(action)
}