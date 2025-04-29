package com.gdpu.lostandfound

import android.app.Application
import android.content.Context

class KotlinStudyApplication : Application(){
    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = baseContext
    }

}