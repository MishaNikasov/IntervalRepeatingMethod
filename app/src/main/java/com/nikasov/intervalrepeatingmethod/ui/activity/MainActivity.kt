package com.nikasov.intervalrepeatingmethod.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.nikasov.intervalrepeatingmethod.R
import com.nikasov.intervalrepeatingmethod.common.extentions.setupWithNavController
import com.nikasov.intervalrepeatingmethod.databinding.ActivityMainBinding
import com.nikasov.intervalrepeatingmethod.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.navigation_carousel,
            R.navigation.navigation_add,
            R.navigation.navigation_main,
            R.navigation.navigation_statistic
        )
        val controller = binding.bottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.navHostContainer,
            intent = intent
        )

        controller.observe(this, {

        })

        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}