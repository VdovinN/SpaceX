package com.vdovin.spacex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vdovin.spacex.api.SpaceApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    val spaceApi : SpaceApi by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(IO) {
            val spaceList = spaceApi.allPastLaunches()
            withContext(Main){
                text_field.text = spaceList.toString()
            }
        }

    }
}