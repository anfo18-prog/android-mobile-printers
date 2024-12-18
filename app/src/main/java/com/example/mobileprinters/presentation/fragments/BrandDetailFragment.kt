package com.example.mobileprinters.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mobileprinters.R
import com.example.mobileprinters.domain.models.PrinterBrand

class BrandDetailFragment : Fragment(R.layout.fragment_brand_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val brand = requireArguments().getParcelable<PrinterBrand>("brand")

        view.findViewById<TextView>(R.id.brandNameTextView).text = brand?.name
        view.findViewById<TextView>(R.id.brandVersionTextView).text = brand?.version
        view.findViewById<TextView>(R.id.brandModelsTextView).text = brand?.compatibleModels
    }
}
