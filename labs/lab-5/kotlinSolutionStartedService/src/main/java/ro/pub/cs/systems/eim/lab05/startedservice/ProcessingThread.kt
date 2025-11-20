package ro.pub.cs.systems.eim.lab05.startedservice

import android.content.Context
import android.content.Intent
import android.util.Log

class ProcessingThread(private val context: Context) : Thread() {

    override fun run() {
        Log.d(Constants.TAG, "Thread.run() was invoked, PID: ${android.os.Process.myPid()} TID: ${android.os.Process.myTid()}")
        while (true) {
            sendMessage(Constants.MESSAGE_STRING)
            sleep()
            sendMessage(Constants.MESSAGE_INTEGER)
            sleep()
            sendMessage(Constants.MESSAGE_ARRAY_LIST)
            sleep()
        }
    }

    private fun sendMessage(messageType: Int) {
        val intent = Intent()
        when (messageType) {
            Constants.MESSAGE_STRING -> {
                intent.action = Constants.ACTION_STRING
                intent.putExtra(Constants.DATA, Constants.STRING_DATA)
            }
            Constants.MESSAGE_INTEGER -> {
                intent.action = Constants.ACTION_INTEGER
                intent.putExtra(Constants.DATA, Constants.INTEGER_DATA)
            }
            Constants.MESSAGE_ARRAY_LIST -> {
                intent.action = Constants.ACTION_ARRAY_LIST
                intent.putExtra(Constants.DATA, Constants.ARRAY_LIST_DATA)
            }
        }
        // Set the target package for cross-app broadcasts
        val packageName = "ro.pub.cs.systems.eim.lab05.startedserviceactivity"
        intent.setPackage(packageName)
        Log.d(Constants.TAG, "Sending broadcast with action: ${intent.action} to package: $packageName")
        context.sendBroadcast(intent)
    }

    private fun sleep() {
        try {
            sleep(Constants.SLEEP_TIME)
        } catch (interruptedException: InterruptedException) {
            Log.e(Constants.TAG, interruptedException.message ?: "InterruptedException occurred")
            interruptedException.printStackTrace()
        }
    }
}
