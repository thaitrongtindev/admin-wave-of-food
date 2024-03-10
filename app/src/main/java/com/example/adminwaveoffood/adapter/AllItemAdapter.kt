package com.example.adminwaveoffood.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminwaveoffood.databinding.ItemItemBinding

class AllItemAdapter(
    private val menuItemName : ArrayList<String>,
    private val menuItemPrice : ArrayList<String>,
    private val menuItemImage: ArrayList<Int>,
) : RecyclerView.Adapter<AllItemAdapter.AllItemViewHolder>() {
    private val itemQuantities: IntArray = IntArray(menuItemName.size)

    inner class AllItemViewHolder(val binding : ItemItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            binding.apply {
                tvCartFoodName.text = menuItemName[position]
                tvCartPrice.text = menuItemPrice[position]
                imageView.setImageResource(menuItemImage[position])
                tvItemCartQuantities.text = itemQuantities[position].toString()


                minusButton.setOnClickListener {
                    if (itemQuantities.size > 0) {
                        itemQuantities[position]--
                        tvItemCartQuantities.text = itemQuantities[position].toString()

                    }
                }
                plusButton.setOnClickListener {
                    if (itemQuantities.size < 10) {
                        itemQuantities[position]++
                        tvItemCartQuantities.text = itemQuantities[position].toString()

                    }
                }
                btnTrash.setOnClickListener {
                    menuItemImage.removeAt(position)
                    menuItemName.removeAt(position)
                    menuItemPrice.removeAt(position)
                    notifyItemRangeChanged(position, menuItemName.size)
                    notifyItemRemoved(position)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllItemViewHolder {
        val binding = ItemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return menuItemName.size
    }

    override fun onBindViewHolder(holder: AllItemViewHolder, position: Int) {
        holder.bind(position)
    }
}