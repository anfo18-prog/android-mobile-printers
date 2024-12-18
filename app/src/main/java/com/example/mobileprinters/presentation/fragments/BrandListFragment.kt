package com.example.mobileprinters.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileprinters.R
import com.example.mobileprinters.presentation.adapters.BrandAdapter
import com.example.mobileprinters.presentation.viewmodels.BrandViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

@AndroidEntryPoint
class BrandListFragment : Fragment(R.layout.fragment_brand_list) {
    private val viewModel: BrandViewModel by viewModels()
    private lateinit var adapter: BrandAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        adapter = BrandAdapter {
            findNavController().navigate(
                R.id.action_brandListFragment_to_brandDetailFragment,
                bundleOf("brand" to it)
            )
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.brands.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.loadBrands()
    }
}