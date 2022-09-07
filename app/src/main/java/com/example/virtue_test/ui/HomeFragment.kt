package com.example.virtue_test.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.virtue_test.R
import com.example.virtue_test.databinding.FragmentHomeBinding
import com.example.virtue_test.utils.mainNavController
import com.example.virtue_test.viewmodels.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding
    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            val navController = requireActivity().mainNavController()
            quoteViewModel.getAllQoute().observe(viewLifecycleOwner){
                if (it.isSuccessful){
                    val listQuote = it.body()!!
                    rvHomeQuotes.apply {
                        val adapterQuote = QuoteAdapter(listQuote, navController)
                        adapterQuote.notifyDataSetChanged()
                        adapter = adapterQuote
                        layoutManager = LinearLayoutManager(requireContext(), LinearLayout.VERTICAL, false)
                    }
                }
            }
        }
    }
}