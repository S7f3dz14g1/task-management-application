package com.example.requestapp.ui.fragment.addTaskFragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.requestapp.R
import com.example.requestapp.model.Task
import com.example.requestapp.utils.Config

class AddTaskFragment : AppCompatDialogFragment(), AddTaskFragmentContract.View {

    var descryption: EditText? = null
    var radioGroup: RadioGroup? = null
    var add: Button? = null
    var radioButton: RadioButton? = null

    var presenter: AddTaskFragmentContract.Presenter = AddTaskFragmentPresenter(this)

    override fun onCreateDialog(saveInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(getActivity())
        val inflater: LayoutInflater = getActivity()!!.getLayoutInflater()
        var view: View = inflater.inflate(R.layout.fragment_add_task, null)
        builder.setView(view)
        builder.setTitle(Config.TITLE_WINDOW_ADD_TASK)
        builder.setCancelable(true)
        initComponent(view)
        add!!.setOnClickListener(View.OnClickListener {
            radioButton = view.findViewById(radioGroup!!.checkedRadioButtonId)
            presenter.onAddTaskClicked(Task(radioButton!!.getText().toString(), descryption!!.text.toString()))
        }
        )
        return builder.create()
    }

    private fun initComponent(view: View) {
        descryption = view.findViewById(R.id.description_add)
        radioGroup = view.findViewById(R.id.type_RB)
        add = view.findViewById(R.id.add_task_buttom)
    }

    override fun onAddedSuccessfulToast(message: String?) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onAddedFailureToast(message: String?) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show()
    }
}