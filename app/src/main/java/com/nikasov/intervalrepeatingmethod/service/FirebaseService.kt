package com.nikasov.intervalrepeatingmethod.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.nikasov.intervalrepeatingmethod.R
import com.nikasov.intervalrepeatingmethod.ui.activity.MainActivity
import timber.log.Timber
import kotlin.random.Random

class FirebaseService: FirebaseMessagingService() {

    private lateinit var notificationManager: NotificationManager

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Timber.d("FCM message: $remoteMessage")
        super.onMessageReceived(remoteMessage)
        showNotification(remoteMessage)
    }

    override fun onNewToken(token: String) {
        Timber.d("FCM new token: $token")
        super.onNewToken(token)
    }

    private fun showNotification(message: RemoteMessage) {

        val intent = Intent(this, MainActivity::class.java)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationID = Random.nextInt()

        val channelId = createNotificationChannel()

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("title")
            .setContentText("text")
//            .setContentTitle(message.data[PushConst.TITLE])
//            .setContentText(message.data[PushConst.BODY])
            .setContentIntent(pendingIntent)
            .setColor(ContextCompat.getColor(this, R.color.emerald))
            .setSmallIcon(R.drawable.ic_text)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setWhen(System.currentTimeMillis())
            .setShowWhen(true)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setVibrate(longArrayOf(200, 200, 200))
            .setAutoCancel(true)

        notificationManager.notify(notificationID, notification.build())
    }

    private fun createNotificationChannel(): String {
        val channelId = "channel_notification"
        val channelName = "notification"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        return channelId
    }
}