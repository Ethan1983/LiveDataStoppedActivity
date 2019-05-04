package com.sample.livedatastoppedactivity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {

    var secondFragmentNavigated : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d( TAG, "onCreate" )

        setContentView(R.layout.activity_main)

        val model = ViewModelProviders.of( this ).get( MainViewModel::class.java)
        model.valuesLiveData.observe( this, Observer { value ->
            value ?.let {
                Log.d( TAG, "Value notified $it" )
            }
        } )

        Handler().postDelayed( {
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

        Handler().postDelayed( {
            Log.d( TAG, "Navigate to Second Fragment from Stopped State" )
            secondFragmentNavigated = true
            Navigation.findNavController(this, R.id.navHostFragment ).navigate( R.id.secondFragment )
        }, 5000)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d( TAG, "onDestroy" )
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
