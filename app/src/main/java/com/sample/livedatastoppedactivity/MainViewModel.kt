package com.sample.livedatastoppedactivity

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class MainViewModel : ViewModel() {

    val valuesLiveData : LiveData<Int>
        get() = mutableLiveData

    private val mutableLiveData : MutableLiveData<Int> = MutableLiveData()

    private val mainThreadHandler = Handler( Looper.getMainLooper() )

    init {
        var value = 0

        thread( start = true ) {

            while(true) {

                notifyValue( value )
                value++
                Thread.sleep( 2000 )

            }

        }
    }

    private fun notifyValue( value : Int ) {

        mainThreadHandler.post {
            mutableLiveData.value = value
        }

    }

}