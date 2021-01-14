package com.example.requestapp.ui.fragment.completedTaksksFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.requestapp.R
import com.example.requestapp.adapter.OnItemClickListener
import com.example.requestapp.adapter.RecycleViewAdapter
import com.example.requestapp.model.StringHelper
import com.example.requestapp.model.Task
import com.example.requestapp.ui.fragment.finishTaskFragment.FinishTaskFragment
import com.example.requestapp.utils.Config

class CompletedTasksFragment : Fragment() , CompletedTasksFragmentContract.View, OnItemClickListener {

    var presenter: CompletedTasksFragmentContract.Presenter = CompletedTasksFragmentPresenter(this)
    var recyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_completed_tasks, container, false)
        initComponents(view)
        presenter.setListCompletedTask();
        return view
    }

    private fun initComponents(view: View) {
        recyclerView = view.findViewById(R.id.lowList_A)
    }

    override fun showMessage(message: String?) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun setTasksList(tasksList: List<Task?>?) {
        recyclerView!!.layoutManager = GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false)
        recyclerView!!.adapter = RecycleViewAdapter(getContext(), tasksList, this)
    }

    override fun onItemClick(position: Int, background: String?, descryption: String?) {
        val bundle = Bundle()
        if (background != null&&descryption!=null) {
            setStringBundle(bundle, position, background, descryption)
            val finishTaskFragment = FinishTaskFragment()
            finishTaskFragment.arguments = bundle
            finishTaskFragment.show(getActivity()!!.getSupportFragmentManager(), Config.TAG_CREATE_DIALOG_FINSH)
        }
    }

    private fun setStringBundle(bundle: Bundle, position: Int, background: String, descryption: String) {
        bundle.putString(Config.POSSITION, StringHelper.parseIntToString(position))
        bundle.putString(Config.TYPE, background)
        bundle.putString(Config.DESCRYPTION, descryption)
    }

}