package com.company.inventory_manager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.company.inventory_manager.R
import com.company.inventory_manager.database.FireBaseDatabase
import com.company.inventory_manager.notifications.ProductNotificationManager

class InventoryActivity : AppCompatActivity() {

    val FIRST_NOTIFICATION_KEY = "firstNotification";


    val db = FireBaseDatabase();

    lateinit var notif: ProductNotificationManager
    var firstNotification: Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)


        firstNotification = savedInstanceState?.getBoolean(FIRST_NOTIFICATION_KEY) ?: false;


        notif = ProductNotificationManager(applicationContext);

        db.getAllProducts().addSnapshotListener { value, error ->

            if(firstNotification) {
                notif.send("New Document", null);
            }
            else{
                firstNotification = true;
            }
        }
    }




}
