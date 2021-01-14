
package com.example.requestapp.ui.fragment.availableTasksFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.requestapp.R
import com.example.requestapp.adapter.OnItemClickListener
import com.example.requestapp.adapter.RecycleViewAdapter
import com.example.requestapp.model.StringHelper
import com.example.requestapp.model.Task
import com.example.requestapp.ui.fragment.editTaskFrgment.EditTaskFragment
import com.example.requestapp.utils.Config


class AvailableTasksFragment : Fragment() , AvailableTasksFragmentContract.View, OnItemClickListener {

    private var recyclerViewLow: RecyclerView? = null
    private  var recyclerViewMedium:RecyclerView? = null
    private  var recyclerViewHigh:RecyclerView? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_available_tasks, container, false)
        initComponents(view)
        var presenter: AvailableTasksFragmentContract.Presenter = AvailableTasksFragmentPresenter(this)
        return view
    }

    private fun initComponents(view: View) {
        recyclerViewLow = view.findViewById(R.id.lowList_A)
        recyclerViewMedium = view.findViewById(R.id.mediumList_A)
        recyclerViewHigh = view.findViewById(R.id.highList_A)
    }

    override fun setListMedium(task: List<Task?>?) {
        setList(recyclerViewMedium, task )
    }

    override fun setListHigh(task: List<Task?>?) {
        setList(recyclerViewHigh, task)
    }

    override fun setListLow(task: List<Task?>?) {
        setList(recyclerViewLow, task)
    }

    private fun setList(recycler: RecyclerView?, tasksList: List<Task?>?) {
        recycler!!.layoutManager = GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false)
        recycler.adapter = RecycleViewAdapter(getContext(), tasksList, this)
    }

    override fun onItemClick(position: Int, background: String?, descryption: String?) {
        val bundle = Bundle()
        if (background != null&&descryption!=null) {
            setStringBundle(bundle, position, background, descryption)
        }
        val editTaskFragment = EditTaskFragment()
        editTaskFragment.arguments = bundle
        editTaskFragment.show(getActivity()!!.getSupportFragmentManager(), Config.TAG_CREATE_DIALOG_EDIT)
    }

    private fun setStringBundle(bundle: Bundle, position: Int, background: String, descryption: String) {
        bundle.putString(Config.POSSITION, StringHelper.parseIntToString(position))
        bundle.putString(Config.TYPE, background)
        bundle.putString(Config.DESCRYPTION, descryption)
    }
}
