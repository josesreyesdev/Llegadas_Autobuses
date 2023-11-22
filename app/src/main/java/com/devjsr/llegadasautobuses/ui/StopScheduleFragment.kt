package com.devjsr.llegadasautobuses.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devjsr.llegadasautobuses.databinding.FragmentStopScheduleBinding
import com.devjsr.llegadasautobuses.ui.viewmodels.BusScheduleViewModel
import com.devjsr.llegadasautobuses.ui.viewmodels.BusScheduleViewModelFactory
import kotlinx.coroutines.launch


class StopScheduleFragment : Fragment() {

    companion object {
        var STOP_NAME = "stopName"
    }

    private val viewModel: BusScheduleViewModel by activityViewModels {
        BusScheduleViewModelFactory(
            (activity?.application as BusScheduleApplication).database.scheduleDao()
        )
    }

    private var _binding: FragmentStopScheduleBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private lateinit var stopName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            stopName = it.getString(STOP_NAME).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStopScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val busStopAdapter = BusStopAdapter {}
        recyclerView.adapter = busStopAdapter

        lifecycle.coroutineScope.launch {
            viewModel.scheduleForStopName(stopName).collect {
                busStopAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}