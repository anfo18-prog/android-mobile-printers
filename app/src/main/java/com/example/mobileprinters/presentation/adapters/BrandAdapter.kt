package com.example.mobileprinters.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileprinters.R
import com.example.mobileprinters.domain.models.PrinterBrand

class BrandAdapter(private val onItemClick: (PrinterBrand) -> Unit) :
    ListAdapter<PrinterBrand, BrandAdapter.BrandViewHolder>(BrandDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_printer_brand, parent, false)
        return BrandViewHolder(view)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

    class BrandViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView = view.findViewById(R.id.brandName)

        fun bind(brand: PrinterBrand, onItemClick: (PrinterBrand) -> Unit) {
            nameTextView.text = brand.name
            itemView.setOnClickListener { onItemClick(brand) }
        }
    }

    class BrandDiffCallback : DiffUtil.ItemCallback<PrinterBrand>() {
        override fun areItemsTheSame(oldItem: PrinterBrand, newItem: PrinterBrand): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: PrinterBrand, newItem: PrinterBrand): Boolean =
            oldItem == newItem
    }
}