package com.company.inventory_manager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.company.inventory_manager.R

class InventoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        val controller = findNavController(R.id.navHostFragment);
        controller.setGraph(R.navigation.product_nav);
    }
}