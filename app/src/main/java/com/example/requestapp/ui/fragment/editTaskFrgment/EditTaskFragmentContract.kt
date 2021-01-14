package com.example.requestapp.ui.fragment.editTaskFrgment

import com.example.requestapp.model.Task

interface EditTaskFragmentContract {
    interface Presenter {
        fun onClickedEdit(task: Task?, descryption: String?)
        fun onClickedFinish(task: Task?)
    }

    interface View {
        fun showMessage(message: String?)
        fun lockEditTextDescryption()
        fun unlockEditTextDescryption()
        fun lockButtonSave()
        fun unlockButtonSave()
        fun changeNameButton(name: String?)
        fun closeFragment()
    }
}