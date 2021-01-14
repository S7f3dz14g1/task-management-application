package com.example.requestapp.ui.fragment.availableTasksFragment

import com.example.requestapp.database.Database
import com.example.requestapp.iterator.Lisner
import com.example.requestapp.model.AvailableTasks
import com.example.requestapp.model.Task
import com.example.requestapp.utils.Config
import java.util.*

class AvailableTasksFragmentPresenter(view: AvailableTasksFragmentContract.View) : Lisner(), AvailableTasksFragmentContract.Presenter {

    val view: AvailableTasksFragmentContract.View? = view
    var database: Database? = Database()
    var availableTasks: AvailableTasks? = AvailableTasks()

    init {

    database!!.setLisner(this)
    setListTasks()
}


    private fun setListTasks() {
        database!!.getListAvilableTask(database!!.userNick, Config.LOW)
        database!!.getListAvilableTask(database!!.userNick, Config.MEDIUM)
        database!!.getListAvilableTask(database!!.userNick, Config.HIGH)
    }

     private fun getTaskList(type: String, desryptions: List<String>): List<Task>? {
        val tasks: MutableList<Task> = ArrayList()
        for (d in desryptions) {
            tasks.add(Task(type, d))
        }
        return tasks
    }

    override fun setList(type: String?, listTasks: List<String?>?) {
        availableTasks!!.setList(type, listTasks)
        setViewList()
    }

     private fun setViewList() {
        if (!availableTasks!!.getList(Config.LOW).isEmpty()) view!!.setListLow(getTaskList(Config.LOW, availableTasks!!.getList(Config.LOW)))
        if (!availableTasks!!.getList(Config.MEDIUM).isEmpty()) view!!.setListMedium(getTaskList(Config.MEDIUM, availableTasks!!.getList(Config.MEDIUM)))
        if (!availableTasks!!.getList(Config.HIGH).isEmpty()) view!!.setListHigh(getTaskList(Config.HIGH, availableTasks!!.getList(Config.HIGH)))
    }

}