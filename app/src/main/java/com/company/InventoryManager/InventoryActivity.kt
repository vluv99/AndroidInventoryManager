package com.company.inventoryManager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController

class InventoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        val controller = findNavController(R.id.navHostFragment);
        controller.setGraph(R.navigation.product_nav);
    }
}