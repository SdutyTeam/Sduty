package com.d108.sduty.ui.main.home.viewmodel

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TAG = "StoryViewModel"
class StoryViewModel: ViewModel() {
    private val _bitmap = MutableLiveData<Bitmap>()
    val bitmap: LiveData<Bitmap>
        get() = _bitmap

    fun setBitmap(bitmap: Bitmap?) {
        _bitmap.postValue(bitmap!!)
        // Log.d(TAG, "setBitmap: $bitmap")
    }

    private val _image = MutableLiveData<Drawable?>(null)
    val image: LiveData<Drawable?>
        get() = _image
    fun setStoryImage(){
        _image.postValue(BitmapDrawable(bitmap.value))
    }
    fun clearStoryImage(){
        _image.value = null
        Log.d(TAG, "clearStoryImage: ${image.value}")

    }
}