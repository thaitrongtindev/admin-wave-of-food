package com.example.adminwaveoffood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminwaveoffood.adapter.PendingAdapter
import com.example.adminwaveoffood.databinding.ActivityPendingBinding

class PendingOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPendingBinding
    private lateinit var pendingAdapter: PendingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_pending)
        binding.btnBack.setOnClickListener {
            finish()
        }

        val orderCustomerName = listOf<String>(
            "David",
            "Silva",
            "Alaba"
        )
        val orderQuantity = listOf<String>(
            "5",
            "6",
            "1"
        )

        val customerImage = listOf<Int>(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3,
        )

        pendingAdapter = PendingAdapter(ArrayList(orderQuantity), ArrayList(orderQuantity), ArrayList(customerImage), this)
        binding.rcvPending.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pendingAdapter
        }
    }
}