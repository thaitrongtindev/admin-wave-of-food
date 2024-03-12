package com.example.adminwaveoffood.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.adminwaveoffood.databinding.PendingItemBinding

class PendingAdapter(
    private val customerName: ArrayList<String>,
    private val quantity: ArrayList<String>,
    private val customerImage: ArrayList<Int>,
    private val context: Context
) : RecyclerView.Adapter<PendingAdapter.PendingViewHolder>() {

    inner class PendingViewHolder(val binding: PendingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var isAccepted = false
        fun bind(position: Int) {
            binding.apply {
                tvNameCustomer.text = customerName[position]
                tvPrice.text = quantity[position]
                imageView.setImageResource(customerImage[position])

                tvAccept.apply {
                    if (!isAccepted) {
                        text = "Accepted"
                    } else {
                        text = "Dispach"
                    }

                    setOnClickListener() {
                        if (!isAccepted) {
                            text = "Dispach"
                            isAccepted = true
                            Toast.makeText(context, "Order is accepted", Toast.LENGTH_SHORT).show()
                        } else {
                            customerName.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                        }
                    }
                }
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingViewHolder {
        val binding = PendingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PendingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return customerName.size
    }

    override fun onBindViewHolder(holder: PendingViewHolder, position: Int) {
        holder.bind(position)
    }
}