package com.vdovin.spacex.util

import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.snackbar.Snackbar
import com.vdovin.spacex.R
import com.vdovin.spacex.api.model.Space
import com.vdovin.spacex.database.model.SpaceX
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


fun Long.convertTimestampToFormattedDate(): String {
    val timestamp = this
    val date = Date(timestamp * 1000)
    val formatter = SimpleDateFormat("EEEE dd, yyyy", Locale.getDefault())
    return formatter.format(date)
}

fun String.getYoutubeId(): String {
    val link = this
    var youtubeVideoId = ""
    val m: Matcher = Pattern.compile("[?&;]v=([^?&;]+)").matcher(link)
    if (m.find()) {
        youtubeVideoId = m.group(1)
    }
    return youtubeVideoId
}

fun Space.convertToDatabaseModel() = SpaceX(
        flightNumber,
        missionName,
        details,
        launchDateUnix?.convertTimestampToFormattedDate(),
        rocket?.rocketName,
        rocket?.secondStage?.payloads?.get(0)?.payloadMassKg,
        links?.wikipedia,
        links?.videoLink?.getYoutubeId()
)

inline fun FragmentManager.transaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun AppCompatActivity.add(fragment: Fragment, container: Int) {
    supportFragmentManager.transaction { add(container, fragment) }
}

fun AppCompatActivity.replace(fragment: Fragment, container: Int) {
    supportFragmentManager.transaction { replace(container, fragment) }
}

fun View.showSnackBar(text: String){
    Snackbar.make(this, text, Snackbar.LENGTH_LONG).show()
}

fun AppCompatActivity.setupStatusBarColor() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.generalGray)
    }
}