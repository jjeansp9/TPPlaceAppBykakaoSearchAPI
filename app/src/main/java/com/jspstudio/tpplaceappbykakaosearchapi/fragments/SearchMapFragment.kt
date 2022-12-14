package com.jspstudio.tpplaceappbykakaosearchapi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jspstudio.tpplaceappbykakaosearchapi.databinding.FragmentSearchMapBinding

class SearchMapFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    val binding:FragmentSearchMapBinding by lazy { FragmentSearchMapBinding.inflate(layoutInflater) }

}
