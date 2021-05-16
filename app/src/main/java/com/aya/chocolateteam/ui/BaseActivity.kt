package com.aya.chocolateteam.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding


abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    abstract val LOG_TAG: String
    abstract val bindingInflater: (LayoutInflater) -> VB
    var _binding: ViewBinding? = null
    protected var binding = _binding as? VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        setup()
        addCallBack()
    }

    abstract fun setup()
    abstract fun addCallBack()
    protected fun log(value: Any) {
        Log.v(LOG_TAG, value.toString())
    }
}
