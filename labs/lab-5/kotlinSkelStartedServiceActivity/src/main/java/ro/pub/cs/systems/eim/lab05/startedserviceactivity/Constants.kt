package ro.pub.cs.systems.eim.lab05.startedserviceactivity

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar

interface Constants {

    companion object {

        const val TAG = "[Started Service]"

        const val ACTION_STRING = "ro.pub.cs.systems.eim.lab05.startedservice.string"
        const val ACTION_INTEGER = "ro.pub.cs.systems.eim.lab05.startedservice.integer"
        const val ACTION_ARRAY_LIST = "ro.pub.cs.systems.eim.lab05.startedservice.arraylist"

        const val MESSAGE_STRING = 1
        const val MESSAGE_INTEGER = 2
        const val MESSAGE_ARRAY_LIST = 3

        const val DATA = "ro.pub.cs.systems.eim.lab05.startedservice.data"

        const val STRING_DATA = "EIM"
        val INTEGER_DATA: Int = SimpleDateFormat("yyyy").format(Calendar.getInstance().time).toInt()

        val ARRAY_LIST_DATA: ArrayList<String> = arrayListOf("EIM", "laborator")

        const val SLEEP_TIME: Long = 5000
    }
}
