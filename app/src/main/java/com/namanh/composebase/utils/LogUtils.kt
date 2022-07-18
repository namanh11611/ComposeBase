package com.namanh.composebase.utils

import android.util.Log
import com.namanh.composebase.BuildConfig

object LogUtils {
    private const val TAG = "LogUtils"

    fun v(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.v(TAG, "[${getCallOrigin()}] $msg")
        }
    }

    fun d(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "[${getCallOrigin()}] $msg")
        }
    }

    fun i(msg: String) {
        Log.i(TAG, "[${getCallOrigin()}] $msg")
    }

    fun w(msg: String) {
        Log.w(TAG, "[${getCallOrigin()}] $msg")
    }

    fun e(msg: String) {
        Log.e(TAG, "[${getCallOrigin()}] $msg")
    }

    private fun getCallOrigin(): String {
        val st = Thread.currentThread().stackTrace

        // The call stack should look like:
        //   n [a variable number of calls depending on the vm used]
        //  +0 getCallOrigin()
        //  +1 privateLogFunction: verbose or debug
        //  +2 caller
        var callerStackIndex = 0
        val logClassName: String = LogUtils::class.java.name
        while (callerStackIndex < st.size) {
            if (st[callerStackIndex].className == logClassName) {
                callerStackIndex += 2
                break
            }
            callerStackIndex++
        }
        return st[callerStackIndex].fileName + ":" + st[callerStackIndex].lineNumber
    }
}