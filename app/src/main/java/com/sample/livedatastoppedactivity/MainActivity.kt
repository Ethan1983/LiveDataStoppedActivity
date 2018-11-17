package com.sample.livedatastoppedactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d( TAG, "onCreate" )

        setContentView(R.layout.activity_main)

        val model = ViewModelProviders.of( this ).get( MainViewModel::class.java)
        model.valuesLiveData.observe( this, Observer { value ->
            value ?.let {
                Log.d( TAG, "Value notified $it" )
                textView.text = "$it"
            }
        } )

        textView.postDelayed( {
            Intent( this, SecondActivity::class.java ).also { intent ->
                startActivity( intent )
            }
        }, 5000)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d( TAG, "onRestart" )
    }

    override fun onResume() {
        super.onResume()
        Log.d( TAG, "onResume" )
    }

    override fun onPause() {
        super.onPause()
        Log.d( TAG, "onPause" )
    }

    override fun onStart() {
        super.onStart()
        Log.d( TAG, "onStart" )
    }

    override fun onStop() {
        super.onStop()
        Log.d( TAG, "onStop" )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d( TAG, "onDestroy" )
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
