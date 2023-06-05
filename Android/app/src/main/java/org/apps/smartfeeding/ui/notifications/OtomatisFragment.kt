package org.apps.smartfeeding.ui.notifications

import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.apps.smartfeeding.databinding.FragmentNotificationsBinding
import org.apps.smartfeeding.ui.ViewModelFactory

class OtomatisFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    private val factory: ViewModelFactory by lazy {
        ViewModelFactory.getInstance(requireActivity().application)
    }
    private val viewModel: OtomatisViewModel by viewModels {
        factory
    }
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        binding.activeButton.setOnClickListener {
            viewModel.sendDataToBluetooth("auto", requireActivity())
        }
        binding.disableButton.setOnClickListener {
            viewModel.sendDataToBluetooth("nonauto", requireActivity())
        }

        binding.jamEditText.filters = arrayOf(InputFilterMinMax(0, 24))
        binding.menitEditText.filters = arrayOf(InputFilterMinMax(0, 59))
        binding.detikEditText.filters = arrayOf(InputFilterMinMax(0, 59))


        binding.setWaktupakan.setOnClickListener {
            viewModel.sendDataToBluetooth(
                "${binding.jamEditText.text}:${binding.menitEditText.text}:${binding.detikEditText.text}",
                requireActivity()
            )
            resetEditText()
        }



        return binding.root
    }

    inner class InputFilterMinMax(private val min: Int, private val max: Int) : InputFilter {

        override fun filter(
            source: CharSequence,
            start: Int,
            end: Int,
            dest: Spanned,
            dstart: Int,
            dend: Int
        ): CharSequence? {
            try {
                val input = (dest.toString() + source.toString()).toInt()
                if (isInRange(input)) {
                    return null
                }
            } catch (ignored: NumberFormatException) {
            }
            return ""
        }

        private fun isInRange(value: Int): Boolean {
            return value >= min && value <= max
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun resetEditText() {
        binding.jamEditText.text!!.clear()
        binding.menitEditText.text!!.clear()
        binding.detikEditText.text!!.clear()
    }
}