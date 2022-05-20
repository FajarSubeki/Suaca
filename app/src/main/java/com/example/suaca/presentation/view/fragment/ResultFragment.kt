package com.example.suaca.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.suaca.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment: Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding
        get() = _binding!!

    private val args: ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData(){

        val current = args.current
        if (current != null){
            binding.textCelciusValue.text = current.temp_c.toString()
            binding.textFahrenheitValue.text = current.temp_f.toString()
            println(current.temp_c.toString())
            println(current.temp_f.toString())
        }

    }
}