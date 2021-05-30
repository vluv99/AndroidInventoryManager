package com.company.inventory_manager

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.company.inventory_manager.database.FireBaseDatabase
import com.company.inventory_manager.notifications.ProductNotificationManager



class InventoryActivity : AppCompatActivity() {

    val FIRST_NOTIFICATION_KEY = "firstNotification";
    private val REQUEST_CODE_ASK_PERMISSIONS = 123;


    val db = FireBaseDatabase();

    lateinit var notif: ProductNotificationManager
    var firstNotification: Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)


        firstNotification = savedInstanceState?.getBoolean(FIRST_NOTIFICATION_KEY) ?: false;


        notif = ProductNotificationManager(applicationContext);

        db.getAllProducts().addSnapshotListener { value, error ->

            if (firstNotification) {
                var text = "";
                value?.documentChanges?.forEach {
                    text += it.document["name"].toString();
                }
                notif.send("Database Changes", text);
            } else {
                firstNotification = true;
            }
        }
    }

    fun checkUserPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_CODE_ASK_PERMISSIONS
            )
            return
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_CODE_ASK_PERMISSIONS -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //
            } else {
                Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show()
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

}
