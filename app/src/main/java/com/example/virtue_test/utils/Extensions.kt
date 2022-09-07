package com.example.virtue_test.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.virtue_test.R

fun FragmentActivity.mainNavController(): NavController{
    return this.findNavController(R.id.fcv_mainactivity_container)
}

fun Fragment.mainNavController(): NavController{
    return this.findNavController()
}

fun Context.showToast(message:String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}