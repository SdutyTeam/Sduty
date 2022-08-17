package com.d108.sduty_admin.ui.home.qna

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d108.sduty_admin.model.dto.Qna
import com.d108.sduty_admin.model.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG ="QnaViewModel"
class QnaViewModel: ViewModel() {
    private val repository = Repository.get()
    
    private val _qnaList = MutableLiveData<MutableList<Qna>>()
    val qnaList: LiveData<MutableList<Qna>>
        get() = _qnaList
    fun getQna(){
        viewModelScope.launch(Dispatchers.IO){
            repository.getQnaList().let { 
                if(it.isSuccessful && it.body() != null){
                    _qnaList.postValue(it.body())
                }else{
                    Log.d(TAG, "getQna: ${it.code()}")
                }
            }
        }
    }

    fun updateQna(qna: Qna){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateQna(qna).let {
                if(it.isSuccessful && it.body() != null){
                    getQna()
                }else{
                    Log.d(TAG, "getQna: ${it.code()}")
                }
            }
        }
    }
}