package com.nikasov.intervalrepeatingmethod.ui.fragment.carousel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.MarginPageTransformer
import com.nikasov.intervalrepeatingmethod.R
import com.nikasov.intervalrepeatingmethod.data.domain.Word
import com.nikasov.intervalrepeatingmethod.databinding.FragmentCarouselBinding
import com.nikasov.intervalrepeatingmethod.ui.adapter.pager.CarouselAdapter
import com.nikasov.intervalrepeatingmethod.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CarouselFragment : BaseFragment(), CarouselAdapter.Interaction {

    private lateinit var binding: FragmentCarouselBinding
    private val viewModel: CarouselViewModel by viewModels()

    @Inject
    lateinit var carouselAdapter: CarouselAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_carousel, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModelCallbacks()
        setupState()
        setupUi()
    }

    private fun setupViewModelCallbacks() {
        viewModel.apply {
            uncompletedWords.observe(viewLifecycleOwner, {
                carouselAdapter.submitList(it)
            })
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUncompletedWords()
    }

    private fun setupUi() {
        carouselAdapter.interaction = this@CarouselFragment
        binding.carouselPager.adapter = carouselAdapter

        binding.carouselPager.offscreenPageLimit = 3
        binding.carouselPager.setPageTransformer(MarginPageTransformer(3))
    }

    private fun setupState() {

    }

    override fun setResult(word: Word, isCompleted: Boolean) {
        viewModel.setResult(word, isCompleted)
    }
}
