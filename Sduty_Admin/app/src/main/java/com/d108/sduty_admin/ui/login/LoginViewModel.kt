package com.d108.sduty_admin.ui.login

import androidx.lifecycle.ViewModel
import com.d108.sduty_admin.model.repository.Repository

class LoginViewModel: ViewModel() {
    private val repository = Repository.get()
}