package com.example.requestapp.ui.fragment.editTaskFrgment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.requestapp.R
import com.example.requestapp.model.Task

class EditTaskFragment : AppCompatDialogFragment(), EditTaskFragmentContract.View{

    private var type: ImageView? = null
    private var desryption: EditText? = null
    private var edit: Button? = null
    private  var finish: Button? = null

    private var presenter: EditTaskFragmentPresenter? = EditTaskFragmentPresenter(this)

    override fun onCreateDialog(saveInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(getActivity())
        val inflater: LayoutInflater = getActivity()!!.getLayoutInflater()
        val view: View = inflater.inflate(R.layout.fragment_edit_task, null)
        builder.setView(view)
        builder.setTitle("Edit task")
        builder.setCancelable(true)
        initComponent(view)
        setConponents()
        val bundle: Bundle? = getArguments()
        val key = bundle!!.getString("type")
        val descryption = bundle.getString("descryption")
        finish!!.setOnClickListener(View.OnClickListener {
            presenter!!.onClickedFinish(Task(key,
                    descryption))
        })
        edit!!.setOnClickListener { presenter!!.onClickedEdit(Task(key, desryption!!.text.toString()), desryption!!.text.toString()) }
        return builder.create()
    }

    private fun setConponents() {
        val bundle: Bundle? = getArguments()
        if (bundle!!.getString("type")!!.startsWith("Low")) {
            type!!.setImageResource(R.drawable.ic_low_priority)
        } else if (bundle.getString("type")!!.startsWith("Medium")) {
            type!!.setImageResource(R.drawable.ic_medium_priority)
        } else {
            type!!.setImageResource(R.drawable.ic_high_priority)
        }
        desryption!!.setText(bundle.getString("descryption"))
    }

    private fun initComponent(view: View) {
        type = view.findViewById(R.id.imageEditTask)
        desryption = view.findViewById(R.id.descryptionEditTask)
        edit = view.findViewById(R.id.editEditText)
        finish = view.findViewById<Button>(R.id.finishEditText)
    }

    override fun showMessage(message: String?) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun lockEditTextDescryption() {
        desryption!!.isEnabled = true
    }

    override fun unlockEditTextDescryption() {
        desryption!!.isEnabled = false
    }

    override fun lockButtonSave() {
        edit!!.isEnabled = true
    }

    override fun unlockButtonSave() {
        edit!!.isEnabled = false
    }

    override fun changeNameButton(name: String?) {
        edit!!.text = name
    }

    override fun closeFragment() {
        getActivity()!!.getSupportFragmentManager().popBackStack()
    }


}
