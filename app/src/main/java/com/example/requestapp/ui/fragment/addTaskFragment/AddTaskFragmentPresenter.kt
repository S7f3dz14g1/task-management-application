package com.example.requestapp.ui.fragment.addTaskFragment

import com.example.requestapp.database.Database
import com.example.requestapp.iterator.Lisner
import com.example.requestapp.model.AvailableTasks
import com.example.requestapp.model.StringHelper
import com.example.requestapp.model.Task
import com.example.requestapp.utils.Config

class AddTaskFragmentPresenter(_view : AddTaskFragmentContract.View?): Lisner(), AddTaskFragmentContract.Presenter {

    var view: AddTaskFragmentContract.View? = _view
    var database: Database? = Database()
    var availableTasks: AvailableTasks? = AvailableTasks()

    init {
        database!!.setLisner(this)
        setAvailableTasks()
    }

    override fun onAddTaskClicked(task: Task?) {
        if (StringHelper.taskDateIsEmpty(task!!.descryption, task.type)) {
            view!!.onAddedFailureToast(Config.MESSAGE_EMPTY_FIELDS)
        } else if (!StringHelper.lenghDescryption(task.descryption)) {
            view!!.onAddedFailureToast(Config.MESSAGE_TO_LONG_DESCRIPTION)
        } else {
            onAddedSuccessful(task)
        }
    }

    private fun setAvailableTasks() {
        database!!.getListAvilableTask(database!!.userNick, Config.LOW)
        database!!.getListAvilableTask(database!!.userNick, Config.HIGH)
        database!!.getListAvilableTask(database!!.userNick, Config.MEDIUM)
    }

    private fun onAddedSuccessful(task: Task) {
        task.nick = database!!.userNick
        updateListTasks(task)
        database!!.addAvailableTask(task, availableTasks!!.sizeList(task.type))
        view!!.onAddedSuccessfulToast(Config.TITLE_WINDOW_ADD_TASK)
    }

    override fun setList(type: String?, listTasks: List<String?>?) {
        availableTasks!!.setList(type, listTasks)
    }

    override fun getSizeList(type: String?): Int {
        return availableTasks!!.getList(type).size
    }

    private fun updateListTasks(task: Task) {
        database!!.getListAvilableTask(task.nick, task.type)
    }
}