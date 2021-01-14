package com.example.requestapp.ui.fragment.completedTaksksFragment

import com.example.requestapp.database.Database
import com.example.requestapp.iterator.Lisner
import com.example.requestapp.model.CompletedTasks
import com.example.requestapp.model.Task

class CompletedTasksFragmentPresenter(_view: CompletedTasksFragmentContract.View) : Lisner(), CompletedTasksFragmentContract.Presenter {

    private val view: CompletedTasksFragmentContract.View = _view
    private var database: Database? = Database()
    private var completedTasks: CompletedTasks? = CompletedTasks()

    init {
        database!!.setLisner(this)
    }


    override fun setCommpletetTaskList(taskList: List<Task?>?) {
        completedTasks!!.tasls = taskList
        if (!completedTasks!!.tasls.isEmpty()) view.setTasksList(taskList) else view.showMessage("Lista jest pusta")
    }

    override fun setListCompletedTask() {
        database!!.getListCompletedTask()
    }
}