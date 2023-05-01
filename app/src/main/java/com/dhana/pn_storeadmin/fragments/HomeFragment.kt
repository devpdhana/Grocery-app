package com.dhana.pn_storeadmin.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dhana.pn_storeadmin.Activity.AllOrderActivity
import com.dhana.pn_storeadmin.R
import com.dhana.pn_storeadmin.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.button.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_sliderFragment)
        }

        binding.button1.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_categoryFragment)
        }

        binding.button2.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_productFragment)
        }

        binding.button3.setOnClickListener{
            startActivity(Intent(requireContext(),AllOrderActivity::class.java))
        }
        return binding.root
    }

}