package com.ftpd.weldy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.ftpd.weldy.navigator.DestinationFragment
import com.ftpd.weldy.navigator.NavigationHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NavigationHelper.navigateToDestination(
            DestinationFragment.CATS_FRAGMENT,
            replace = true,
            addToBackStack = false,
        )
        lifecycleScope.launch {
            NavigationHelper.navigationFlow.collect { navigationModel ->
                Navigator.navigate(
                    R.id.nav_host_fragment,
                    supportFragmentManager,
                    navigationModel
                )
            }
        }
    }
}