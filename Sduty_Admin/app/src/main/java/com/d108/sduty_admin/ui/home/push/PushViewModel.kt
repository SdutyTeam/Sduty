package com.d108.sduty_admin.ui.home.push

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d108.sduty_admin.model.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "PushViewModel"
class PushViewModel: ViewModel() {
    private val repository = Repository.get()
    
    fun sendFCM(sendMessage: String){
        viewModelScope.launch(Dispatchers.IO){
            val message = mapOf<String, String>("message" to sendMessage)
            repository.sendFCM(message).let { 
                if(it.isSuccessful){
                    
                }else{
                    Log.d(TAG, "sendFCM: ${it.code()}")
                }
            }
        }
        
    }
}