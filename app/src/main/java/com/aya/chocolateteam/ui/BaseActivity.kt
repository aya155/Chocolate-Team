package com.aya.chocolateteam.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.aya.chocolateteam.ui.fragments.BaseInterface


abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), BaseInterface<VB> {

    abstract override val LOG_TAG: String
    abstract override val bindingInflater: (LayoutInflater) -> VB
    override var _binding: ViewBinding? = null
    override var binding: VB?
        get() = _binding as VB?
        set(value) = TODO()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        setup()
        addCallBack()
    }
    abstract override fun setup()
    abstract override fun addCallBack()
}
