package com.aliozdemir.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.aliozdemir.navigation.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_pageAFragment)
        }

        binding.button2.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_pageXFragment)
        }

        return binding.root
    }

}