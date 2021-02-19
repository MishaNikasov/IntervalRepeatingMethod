package com.nikasov.intervalrepeatingmethod.ui.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nikasov.intervalrepeatingmethod.R
import com.nikasov.intervalrepeatingmethod.common.extentions.hideKeyBoard
import com.nikasov.intervalrepeatingmethod.data.domain.Word
import com.nikasov.intervalrepeatingmethod.databinding.FragmentMainBinding
import com.nikasov.intervalrepeatingmethod.ui.adapter.recycler.WordAdapter
import com.nikasov.intervalrepeatingmethod.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment(), WordAdapter.Interaction {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var wordAdapter: WordAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().hideKeyBoard()
        setupUi()
        setupState()
        loadData()
    }

    private fun setupUi() {
        wordAdapter.interaction = this@MainFragment
        binding.wordRecycler.adapter = wordAdapter

        binding.addWordBtn.setOnClickListener {
            findNavController().navigate(R.id.mainFragmentToAddWordFragment)
        }
    }

    private fun loadData() {
        viewModel.wordList.observe(viewLifecycleOwner, {
            wordAdapter.submitList(it)
        })
    }


    private fun setupState() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {

            }
        }
    }

    override fun onItemSelected(position: Int, item: Word) {

    }

    override fun deleteWord(id: Int) {
        viewModel.deleteWordById(id)
    }
}