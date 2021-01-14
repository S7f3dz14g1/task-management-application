package com.example.requestapp.ui.fragment.finishTaskFragment

import com.example.requestapp.database.Database
import com.example.requestapp.iterator.Lisner
import com.example.requestapp.model.AvailableTasks
import com.example.requestapp.model.Task
import com.example.requestapp.utils.Config

class FinishTaskFragmentPresenter(_view:FinishTaskFragmentContract.View): Lisner() , FinishTaskFragmentContract.Presenter{

    val view: FinishTaskFragmentContract.View = _view
    var database: Database = Database()
    var availableTasks: AvailableTasks = AvailableTasks()

init {
    database.setLisner(this)
    database.getListAvilableTask(database.userNick, Config.LOW)
    database.getListAvilableTask(database.userNick, Config.MEDIUM)
    database.getListAvilableTask(database.userNick, Config.HIGH)
}

    override fun onClickedDoItAgain(piority: String?, descryption: String?) {
        database.addAvailableTask(Task(database.userNick, piority, descryption), availableTasks.sizeList(piority))
        view.showMessage("Ponownie dodano task :D")
        view.closeFragment()
    }

    override fun setList(type: String?, listTasks: List<String?>?) {
        availableTasks.setList(type, listTasks)
    }
}
