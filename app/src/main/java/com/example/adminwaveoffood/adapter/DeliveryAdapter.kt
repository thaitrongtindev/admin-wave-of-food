package com.example.adminwaveoffood.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminwaveoffood.databinding.OutForDeliveryBinding

class DeliveryAdapter(
    private val customerName: ArrayList<String>,
    private val statusMoney : ArrayList<String>
) : RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder>() {

    inner class DeliveryViewHolder(val binding: OutForDeliveryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                tvCustomerName.text = customerName[position]
                tvStatus.text = statusMoney[position]

                val colorMap = mapOf(
                    "received" to Color.GREEN, "not Received" to Color.RED
                    , "pending" to Color.GRAY
                )
                tvStatus.setTextColor(colorMap[statusMoney[position]]?:Color.BLACK)
                statusColor.backgroundTintList = ColorStateList.valueOf(colorMap[statusMoney[position]]?:Color.BLACK)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
       val binding = OutForDeliveryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeliveryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return customerName.size
    }

    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        holder.bind(position)
    }
}