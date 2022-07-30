package com.d108.sduty.ui.main.home.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TAG = "StoryViewModel"
class StoryViewModel: ViewModel() {
    private val _bitmap = MutableLiveData<Bitmap>()
    val bitmap: LiveData<Bitmap>
        get() = _bitmap

    fun setBitmap(bitmap: Bitmap) {
        _bitmap.postValue(bitmap)
        // Log.d(TAG, "setBitmap: $bitmap")
    }
}