package com.example.suaca.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.resepku.data.base.Status
import com.example.suaca.R
import com.example.suaca.databinding.FragmentMainBinding
import com.example.suaca.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var city: String? = null
    private var key: String? = null
    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = _binding!!

    private var cities: ArrayList<String>? = null
    private val mViewModel by activityViewModels<WeatherViewModel>()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)
        initCities()
        initData()
    }

    private fun initCities(){

        cities = java.util.ArrayList<String>()
        cities!!.add("Kuala Lumpur")
        cities!!.add("Singapore")

        if (cities!!.size != 0) {
            val citiesAdapter = ArrayAdapter(requireActivity(), R.layout.dropdown_menu_popup_item, cities!!)
            binding.editCityName.setSelection(0)
            binding.editCityName.setAdapter(citiesAdapter)
        }

        binding.editCityName.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            when (position) {
                0 -> {
                    city = "Kuala Lumpur"
                }
                1 -> {
                    city = "Singapore"
                }
            }
        }

    }

    private fun initData(){

        binding.btnSubmit.setOnClickListener {
            key = binding.textKeyValue.text.toString()
            mViewModel.getWeather(key!!, city.toString()).observe(viewLifecycleOwner){
                when (it.status) {
                    Status.LOADING -> {
                        Toast.makeText(requireActivity(), "loading", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        val actions = MainFragmentDirections.actionMainFragmentToResultsFragment(it.data)
                        navController.navigate(actions)
                    }
                    Status.FAIL -> {
                        Toast.makeText(context, "Something went wrong...", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

}