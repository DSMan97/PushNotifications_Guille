package com.dsman.pushnotifications
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.app.NotificationManager
import android.media.RingtoneManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.app.NotificationChannel
import android.os.Build
import android.support.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)//Ejecuta el codigo para su Api minima compatible
class MyFirebaseMessagingService : FirebaseMessagingService() {
    val tag = "FirebaseMessagingServic"

    //genera un nuevo tocken al abrir la App por primera vez
   override fun onNewToken(token: String?) {
        Log.d("Messaging", "Token nuevo: $token")
    }

//al recivbir el mensaje de firebase muestra la notificacion
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(tag, "Remote Message ${remoteMessage.from}")

        //si el mensaje recibido no es nulo llama al metodo (showNotification)
        remoteMessage.notification?.let {
            showNotification(remoteMessage.notification?.title, remoteMessage.notification?.body)
        }
    }


    //metodo que crea el intent del tipo de notificacion
    private fun showNotification(title: String?, body: String?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT)

        //construye la notificacion
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSound(soundUri)
            .setChannelId("channelID") //setea el canal
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
        val notificationChannel = notificationManager.getNotificationChannel("channelID")

    }


}