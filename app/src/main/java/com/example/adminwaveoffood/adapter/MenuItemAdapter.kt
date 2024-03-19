package com.example.adminwaveoffood.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adminwaveoffood.databinding.ItemItemBinding
import com.example.adminwaveoffood.model.AllMenu

class MenuItemAdapter(
    private val context: Context,
    private val menuItemList:ArrayList<AllMenu>
) : RecyclerView.Adapter<MenuItemAdapter.AllItemViewHolder>() {
    private val itemQuantities: IntArray = IntArray(menuItemList.size)

    inner class AllItemViewHolder(val binding : ItemItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val menuItem = menuItemList[position]
            val uriString = menuItem.foodImage
            val uri  = Uri.parse(uriString)
            binding.apply {
                tvCartFoodName.text = menuItem.foodName
                tvCartPrice.text = menuItem.foodPrice
                Glide.with(context).load(uri).into(imageView)
                tvItemCartQuantities.text = itemQuantities[position].toString()


                minusButton.setOnClickListener {
                    if (itemQuantities.size > 1 ) {
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
                    menuItemList.removeAt(position)
                    menuItemList.removeAt(position)
                    menuItemList.removeAt(position)
                    notifyItemRangeChanged(position, menuItemList.size)
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
        return menuItemList.size
    }

    override fun onBindViewHolder(holder: AllItemViewHolder, position: Int) {
        holder.bind(position)
    }
}