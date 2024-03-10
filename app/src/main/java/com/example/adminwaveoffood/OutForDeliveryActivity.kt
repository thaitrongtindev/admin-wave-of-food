package com.example.adminwaveoffood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminwaveoffood.adapter.DeliveryAdapter
import com.example.adminwaveoffood.databinding.ActivityOutForDeliveryBinding
import com.example.adminwaveoffood.databinding.OutForDeliveryBinding

class OutForDeliveryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOutForDeliveryBinding
    private lateinit var deliveryAdapter: DeliveryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_out_for_delivery)


        val customerName = listOf<String>("Tin", "Tuan",  "Manh")
        val statusMoney = listOf<String>("received", "not Received", "pending")

        deliveryAdapter = DeliveryAdapter(ArrayList(customerName), ArrayList(statusMoney))

        binding.rcvOutForDelivery.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = deliveryAdapter
        }
        }
}