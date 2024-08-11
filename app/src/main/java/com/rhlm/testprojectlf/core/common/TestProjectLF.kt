package com.rhlm.testprojectlf.core.common

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
/*Every app must contain a class, which inherits the Application Android class, which is annotated by @HiltAndroidApp .
* This class is used by Hilt’s code generator, which makes all the components lifecycle aware.
* */
@HiltAndroidApp
class TestProjectLF : Application(){
    override fun onCreate() {
        super.onCreate()

    }
}