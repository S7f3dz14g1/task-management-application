package com.example.requestapp.ui.fragment.addTaskFragment

import com.example.requestapp.model.Task

interface AddTaskFragmentContract {
    interface Presenter {
        fun onAddTaskClicked(task: Task?)
    }

    interface View {
        fun onAddedSuccessfulToast(message: String?)
        fun onAddedFailureToast(message: String?)
    }
}