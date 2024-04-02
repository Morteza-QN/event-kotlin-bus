package me.mqn.koltinbus

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import java.lang.reflect.InvocationTargetException

open class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToKotlinBus()
    }

    private fun subscribeToKotlinBus() {
        for (declaredMethod in javaClass.declaredMethods) {
            if (declaredMethod.isAnnotationPresent(Listen::class.java)) {
                try {
                    declaredMethod.invoke(this)
                    Log.i("BaseFragment", " >>> subscribeToKotlinBus: $this")
                } catch (e: IllegalAccessException) {
                    Log.e("BaseFragment", " >>> subscribeToKotlinBus: $e")
                    // Utils.logException(e)
                } catch (e: InvocationTargetException) {
                    Log.e("BaseFragment", " >>> subscribeToKotlinBus: $e")
                    // Utils.logException(e)
                }
            }
        }
    }
}