package gio.ado.prova

import androidx.lifecycle.ViewModel

class ViewModelScarico() : ViewModel() {

    val isChecked : Boolean = false

    fun checkBox() : Boolean{
        return !isChecked
    }
}