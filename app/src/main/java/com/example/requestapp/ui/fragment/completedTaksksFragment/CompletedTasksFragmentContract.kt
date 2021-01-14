package com.example.requestapp.ui.fragment.completedTaksksFragment

import com.example.requestapp.model.Task

interface CompletedTasksFragmentContract {
    interface Presenter {
        fun setListCompletedTask();
    }

    interface View {
        fun showMessage(message: String?)
        fun setTasksList(tasksList: List<Task?>?)
    }
}