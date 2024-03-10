package com.example.adminwaveoffood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminwaveoffood.adapter.AllItemAdapter
import com.example.adminwaveoffood.databinding.ActivityAddItemBinding
import com.example.adminwaveoffood.databinding.ActivityAllItemBinding

class AllItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllItemBinding
    private lateinit var allItemAdapter: AllItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_item)

        val menuItemName = listOf<String>("Item 1","Item 2","Item 3","Item 4","Item 5",)
        val menuItemPrice = listOf<String>("$1","$2","$3","$4","$5")
        val menuItemImage = listOf<Int>(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3,
            R.drawable.menu4,
            R.drawable.menu5,
        )
        allItemAdapter = AllItemAdapter(ArrayList(menuItemName), ArrayList(menuItemPrice), ArrayList(menuItemImage))

        binding.rcvAllItem.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = allItemAdapter

        }

    }
}