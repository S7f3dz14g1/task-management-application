package com.example.requestapp.ui.fragment.availableTasksFragment

import com.example.requestapp.model.Task

interface AvailableTasksFragmentContract {

    interface Presenter

    interface View {
        fun setListMedium(task: List<Task?>?)
        fun setListHigh(task: List<Task?>?)
        fun setListLow(task: List<Task?>?)
    }
}