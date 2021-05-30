package com.company.inventory_manager.notifications

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import com.company.inventory_manager.InventoryActivity
import com.company.inventory_manager.R


class ProductNotificationManager @SuppressLint("ServiceCast") constructor(context: Context) {

    private val CHANNEL_ID = "shop_notification_channel"
    private val NOTIFICATION_ID = 0

    private var mNotifyManager: NotificationManager? = null
    private var mContext: Context = context


    init {
        mNotifyManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createChannel()
    }

    private fun createChannel() {
        //We are targeting API level 26 so we do not need this.
        //if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return

        val channel = NotificationChannel(
            CHANNEL_ID,
            "Inventory Notification",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        channel.description = "Notifications from Inventory Manager"
        mNotifyManager?.createNotificationChannel(channel)
    }

    fun send(title:String? ,message: String?) {
        val intent = Intent(mContext, InventoryActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            mContext,
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(mContext, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.cookie)
            .setContentIntent(pendingIntent)

        mNotifyManager!!.notify(NOTIFICATION_ID, builder.build())
    }

}