package org.apps.smartfeeding.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import org.apps.smartfeeding.databinding.FragmentDashboardBinding
import org.apps.smartfeeding.model.CekPakanData
import org.apps.smartfeeding.ui.ViewModelFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class StatusPakanFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val dataList = mutableListOf<CekPakanData>()
    private lateinit var adapter: CekPakanAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.

    private val factory: ViewModelFactory by lazy {
        ViewModelFactory.getInstance(requireActivity().application)
    }
    private val viewModel: StatusPakanViewModel by viewModels {
        factory
    }
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        // Observer



        val rv = binding.rvCekpakan
        rv.layoutManager = LinearLayoutManager(requireContext())
        adapter = CekPakanAdapter(dataList)
        rv.adapter = adapter

        // Nanti Using DB ROOM
        viewModel.readDataFromBT.observe(viewLifecycleOwner, { receivedData ->
            try {
                val jarak = receivedData.trim().toIntOrNull()

                if (jarak != null) {
                    var statusKondisi = ""
                    val currentDateTime = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    val dateTime = currentDateTime.format(formatter)

                    when {
                        jarak < 20 -> statusKondisi = "Habis Silahkan Isi Kembali"
                        jarak < 50 -> statusKondisi = "Setengah"
                        else -> statusKondisi = "Penuh"
                    }

                    val sendDataToAdapter = CekPakanData(
                        statusKondisi = statusKondisi,
                        receivedData = receivedData.trim(),
                        dateTime = dateTime
                    )

                    binding.textView7.text = receivedData
                    dataList.add(sendDataToAdapter)
                    adapter.notifyDataSetChanged()
                } else {
                    // Handle the case when the received data is not a valid integer
                    // For example, show an error message or take appropriate action
                }
            } catch (e: NumberFormatException) {
                // Handle the case when the received data cannot be parsed to an integer
                // For example, show an error message or take appropriate action
            }
        })


        binding.cekpakanGetstatus.setOnClickListener {
            viewModel.sendDataToBluetooth("sensor", requireActivity())
            viewModel.readDataFromBluetooth()
        }



        return binding.root
    }

}