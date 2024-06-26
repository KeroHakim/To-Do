package com.route.to_do.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.to_do.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }
}