package com.nikasov.intervalrepeatingmethod.ui.fragment.carousel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
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
        setupUi()
    }

    private fun setupViewModelCallbacks() {
        viewModel.apply {
            uncompletedWords.observe(viewLifecycleOwner, {
                binding.count = it.size
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
        binding.carouselPager.apply {
            adapter = carouselAdapter
            offscreenPageLimit = 3
            isUserInputEnabled = false
            setPageTransformer(MarginPageTransformer(3))

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.progress = position
                }
            })
        }
    }

    private fun goToNextWord() {
        if (binding.carouselPager.currentItem != carouselAdapter.itemCount - 1)
            binding.carouselPager.currentItem = binding.carouselPager.currentItem + 1
        else {
            binding.isComplete = true
            binding.progress = carouselAdapter.itemCount
        }
    }

    override fun setResult(word: Word, isCompleted: Boolean) {
        viewModel.setResult(word, isCompleted)
        lifecycleScope.launch {
            delay(1500)
            goToNextWord()
        }
    }
}