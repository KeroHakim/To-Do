package com.route.to_do.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.to_do.databinding.FragmentTaskBinding

class TaskFragment : Fragment(){
    lateinit var binding: FragmentTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater)
        return binding.root
    }
}