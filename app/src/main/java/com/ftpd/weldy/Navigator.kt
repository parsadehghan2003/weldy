package com.ftpd.weldy

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.ftpd.cat.ui.CatsFragment
import com.ftpd.weldy.navigator.DestinationFragment
import com.ftpd.weldy.navigator.NavigationModel
import java.io.Serializable

object Navigator {

    fun navigate(
        @IdRes containerId: Int,
        fragmentManager: FragmentManager,
        navigationModel: NavigationModel
    ) {

        val transaction = fragmentManager.beginTransaction()

        val fragment = findDestinationFragment(navigationModel.destinationFragment)

        navigationModel.arg?.let { arguments ->
            val argBundle = Bundle()
            fillArguments(arguments, argBundle)
            fragment.arguments = argBundle
        }
        if (navigationModel.replace) {
            transaction.replace(containerId, fragment)
        } else {
            transaction.add(containerId, fragment)
        }

        if (navigationModel.addToBackStack) {
            transaction.addToBackStack(fragment::class.java.simpleName)
        }

        if (navigationModel.shouldBeVisible) {
            transaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
        }

        transaction.setPrimaryNavigationFragment(fragment)

        transaction.commit()

    }

    private fun fillArguments(
        arguments: Map<String, Any>,
        argBundle: Bundle
    ) {
        arguments.forEach {
            when (it.value) {
                is String -> argBundle.putString(it.key, it.value as String)
                is Int -> argBundle.putInt(it.key, it.value as Int)
                is Long -> argBundle.putLong(it.key, it.value as Long)
                is Float -> argBundle.putFloat(it.key, it.value as Float)
                is Double -> argBundle.putDouble(it.key, it.value as Double)
                is Short -> argBundle.putShort(it.key, it.value as Short)
                is Byte -> argBundle.putByte(it.key, it.value as Byte)
                is ByteArray -> argBundle.putByteArray(it.key, it.value as ByteArray)
                is Serializable -> argBundle.putSerializable(it.key, it.value as Serializable)
            }
        }
    }

    private fun findDestinationFragment(destinationFragment: DestinationFragment): Fragment {
        return when (destinationFragment) {
            DestinationFragment.CATS_FRAGMENT -> CatsFragment()
        }
    }
}