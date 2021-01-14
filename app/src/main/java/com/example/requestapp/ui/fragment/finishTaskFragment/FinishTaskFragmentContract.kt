package com.example.requestapp.ui.fragment.finishTaskFragment

interface FinishTaskFragmentContract {
    interface Presenter {
        fun onClickedDoItAgain(piority: String?, descryption: String?)
    }

    interface View {
        fun showMessage(message: String?)
        fun closeFragment()
    }
}