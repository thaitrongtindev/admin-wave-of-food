package com.example.adminwaveoffood

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminwaveoffood.adapter.MenuItemAdapter
import com.example.adminwaveoffood.databinding.ActivityAllItemBinding
import com.example.adminwaveoffood.model.AllMenu
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllItemBinding
    private lateinit var menuItemAdapter: MenuItemAdapter
    private var menuItems : ArrayList<AllMenu> = ArrayList()

    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_item)

        databaseReference = FirebaseDatabase.getInstance().reference
        // retrieveMenuItem
        retrieveMenuItem()




    }

    private fun retrieveMenuItem() {
        database = FirebaseDatabase.getInstance()
        val foodRef : DatabaseReference = database.reference.child("menu")

        // fetch data from data base
        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                menuItems.clear()
                for (foodSnapshot in snapshot.children)  {
                    val menuItem = foodSnapshot.getValue(AllMenu::class.java)
                    menuItem?.let { allMenu ->
                        menuItems.add(allMenu)
                    }
                }
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setAdapter() {
        menuItemAdapter = MenuItemAdapter(this, menuItems)
        binding.rcvAllItem.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = menuItemAdapter
            menuItemAdapter.notifyDataSetChanged()
        }

    }
}