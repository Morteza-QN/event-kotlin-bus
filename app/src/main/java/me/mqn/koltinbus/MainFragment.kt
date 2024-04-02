package me.mqn.koltinbus

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

class MainFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onStart() {
        super.onStart()
        val a = AnyRandomClass()
        lifecycleScope.launch {
            a.triggerEvent()
        }
    }

    @Listen
    fun onEventTriggered() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                KotlinBus.subscribe<String> {
                    Log.i("Received", it.toString())
                }
            }
        }
    }
}