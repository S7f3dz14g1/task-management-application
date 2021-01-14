package com.example.requestapp.ui.fragment.editTaskFrgment

import com.example.requestapp.database.Database
import com.example.requestapp.iterator.Lisner
import com.example.requestapp.model.AvailableTasks
import com.example.requestapp.model.StringHelper
import com.example.requestapp.model.Task
import com.example.requestapp.utils.Config

class EditTaskFragmentPresenter(_view: EditTaskFragmentContract.View): Lisner() , EditTaskFragmentContract.Presenter {

    private var database: Database? = Database()
    private var sizeCompletedList: Int =0
    private var descryption: String? = null
    private var availableTasks: AvailableTasks? = null
    private var index = 0
    private var view: EditTaskFragmentContract.View =_view

    init {
        database!!.setLisner(this)
        database!!.getSizeCompletedList()
        availableTasks = AvailableTasks()
        database!!.getListAvilableTask(database!!.userNick, Config.LOW)
        database!!.getListAvilableTask(database!!.userNick, Config.MEDIUM)
        database!!.getListAvilableTask(database!!.userNick, Config.HIGH)
    }

    override fun onClickedEdit(task: Task?, descryption: String?) {
        if (this.descryption == null || this.descryption == descryption) {
            println(task!!.descryption)
            view.changeNameButton("Save")
            index = availableTasks!!.getList(task.type).indexOf(task.descryption)
            view.lockButtonSave()
            this.descryption = availableTasks!!.getList(task.type)[index]
            view.lockEditTextDescryption()
        } else if (!StringHelper.lenghDescryption(descryption)) {
            view.showMessage("Błędna długiść")
        } else {
            view.showMessage("Udana edycja")
            view.lockEditTextDescryption()
            view.unlockButtonSave()
            view.changeNameButton("Edit")
            task!!.descryption = descryption
            database!!.addAvailableTask(task, index)
            this.descryption = null
        }
    }

    override fun onClickedFinish(task: Task?) {
        task!!.nick = database!!.userNick
        database!!.moveAvailableTaskToCompletedTask(task, sizeCompletedList.toString() + "")
        view.closeFragment()
    }

    override fun setList(type: String?, listTasks: List<String?>?) {
        availableTasks!!.setList(type, listTasks)
    }

      override fun setSizeCompletedList(size: Int) {
            this.sizeCompletedList = size
    }
}
