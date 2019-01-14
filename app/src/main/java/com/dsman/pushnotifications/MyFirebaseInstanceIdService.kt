package com.dsman.pushnotifications
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class MyFirebaseInstanceIdService : FirebaseInstanceIdService() {

    val TAG = "PushNotifService"
    lateinit var name: String

    //refresca el tocken (se que no es necesario, pero he querido probar, ya que antes si me funcionaban
    // las notificaciones con la app abierta)
    override fun onTokenRefresh() {

        val token = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "Token: ${token}")
    }
}


