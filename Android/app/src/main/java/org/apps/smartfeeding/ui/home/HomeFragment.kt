package org.apps.smartfeeding.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import org.apps.smartfeeding.databinding.FragmentHomeBinding
import org.apps.smartfeeding.ui.ViewModelFactory
import org.apps.smartfeeding.ui.connect_bluetooth.ConnectBTViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val factory: ViewModelFactory by lazy {
        ViewModelFactory.getInstance(requireActivity().application)
    }
    private val viewModel: HomeViewModel by viewModels {
        factory
    }

    private var delayServo: String = "status"
    private lateinit var delayServoLiveData: MutableLiveData<String>


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        delayServoLiveData = MutableLiveData(delayServo)




        binding.bukaButton.setOnClickListener {
            viewModel.sendDataToBluetooth("open", requireActivity())
            viewModel.isConnected.observe(viewLifecycleOwner) {
                Log.d(HomeFragment::class.java.simpleName, it.toString())
            }
        }

        binding.kosongEmpat.setOnClickListener {
            delayServo = "${binding.kosongEmpat.text}delay"
            delayServoLiveData.value = delayServo

        }

        binding.kosongDelapan.setOnClickListener {
            delayServo = "${binding.kosongDelapan.text}delay"
            delayServoLiveData.value = delayServo
        }

        binding.satu.setOnClickListener {
            delayServo = "${binding.satu.text}delay"
            delayServoLiveData.value = delayServo

        }

        binding.setDelayservo.setOnClickListener {
            viewModel.sendDataToBluetooth(delayServo, requireActivity())
            delayServoLiveData.observe(viewLifecycleOwner) { value ->
                binding.statusDelayServo.text = value
            }
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}