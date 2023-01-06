package com.example.a5

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.telephony.SmsMessage
import android.widget.Toast
import java.lang.Exception


class MyReceiver : BroadcastReceiver() {
    private val smsReceivedAction = "android.provider.Telephony.SMS_RECEIVED"
    private val pdu_type = "pdus";

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(smsReceivedAction)) {
            handleReceivedSms(intent, context)
        }
        if (intent.action.equals(Intent.ACTION_BATTERY_LOW))
            Toast.makeText(context, "Niski poziom naładowania baterii", Toast.LENGTH_SHORT).show()

        if (intent.action.equals(Intent.ACTION_BATTERY_OKAY))
            Toast.makeText(context, "Bateria jest Ok", Toast.LENGTH_SHORT).show()

        if (intent.action.equals(Intent.ACTION_POWER_CONNECTED))
            Toast.makeText(context, "Podłączono ładowanie", Toast.LENGTH_SHORT).show()

        if (intent.action.equals(Intent.ACTION_POWER_DISCONNECTED))
            Toast.makeText(context, "Odłączono ładowanie", Toast.LENGTH_SHORT).show()
    }

    private fun handleReceivedSms(intent: Intent, context: Context) {
        val bundle = intent.extras
        var messageContent = ""
        var receiverPhoneNumber = ""
        val format = bundle!!.getString("format")

        val pdus = bundle.get(pdu_type) as Array<*>?
        for (pdu in pdus!!) {
            val msg = SmsMessage.createFromPdu(pdu as ByteArray, format)
            receiverPhoneNumber = msg.displayOriginatingAddress
            messageContent = msg.messageBody
        }
        val smsManager = SmsManager.getDefault()
        if (messageContent.startsWith("PILNE")) {
            Toast.makeText(
                context,
                "Otrzymano SMSa o treści: $messageContent",
                Toast.LENGTH_LONG
            ).show()
        }
        try {
            val messageContentInt = messageContent.toInt()
            if (messageContentInt < 10) {
                smsManager.sendTextMessage(
                    receiverPhoneNumber, null,
                    messageContentInt.plus(1).toString(), null, null
                )
            }
        } catch (_: Exception) {
        }
    }
}
