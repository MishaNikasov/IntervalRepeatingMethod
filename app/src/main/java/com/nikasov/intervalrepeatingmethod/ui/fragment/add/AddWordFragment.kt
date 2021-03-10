package com.nikasov.intervalrepeatingmethod.ui.fragment.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nikasov.intervalrepeatingmethod.R
import com.nikasov.intervalrepeatingmethod.common.util.UiState
import com.nikasov.intervalrepeatingmethod.databinding.FragmentAddWordBinding
import com.nikasov.intervalrepeatingmethod.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AddWordFragment : BaseFragment<FragmentAddWordBinding>(){

    private val viewModel: AddWordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_word, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupState()
        setupUi()
    }

    private fun setupUi() {
        binding.rusEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.rusWord.value = text.toString()
        }
        binding.engEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.engWord.value = text.toString()
        }
    }

    private fun setupState() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                when (state) {
                    is UiState.Loading -> binding.inProgress = state.inProgress
//                    is UiState.Successes -> requireActivity().onBackPressed()
                }
            }
        }
    }
}
