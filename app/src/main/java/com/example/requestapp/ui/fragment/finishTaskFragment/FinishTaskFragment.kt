package com.example.requestapp.ui.fragment.finishTaskFragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.requestapp.R
import com.example.requestapp.utils.Config

class FinishTaskFragment : AppCompatDialogFragment() , FinishTaskFragmentContract.View {


    var presenter: FinishTaskFragmentContract.Presenter = FinishTaskFragmentPresenter(this)
    var button: Button? = null
    var descryption: TextView? = null
    var image: ImageView? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(getActivity())
        val inflater: LayoutInflater = getActivity()!!.getLayoutInflater()
        val view: View = inflater.inflate(R.layout.fragment_finish_task, null)
        builder.setView(view)
        builder.setCancelable(true)
        initComponent(view)
        val bundle: Bundle? = getArguments()
        if (bundle != null) {
            setConponents(bundle)
        }
        button!!.setOnClickListener { presenter.onClickedDoItAgain(bundle!!.getString(Config.TYPE), bundle.getString(Config.DESCRYPTION)) }
        return builder.create()
    }

    private fun setConponents(bundle: Bundle) {
        this.descryption!!.setText(bundle.getString(Config.DESCRYPTION))
        if (bundle.getString(Config.TYPE)!!.startsWith(Config.LOW)) {
            image!!.setImageResource(R.drawable.ic_low_priority)
        } else if (bundle.getString(Config.TYPE)!!.startsWith(Config.MEDIUM)) {
            image!!.setImageResource(R.drawable.ic_medium_priority)
        } else {
            image!!.setImageResource(R.drawable.ic_high_priority)
        }
    }

    private fun initComponent(view: View) {
        button = view.findViewById(R.id.buttonFinishTask)
        descryption = view.findViewById(R.id.descryptionFinishTask)
        image = view.findViewById(R.id.imageFinishTask)
    }

    override fun showMessage(message: String?) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun closeFragment() {
    }

}