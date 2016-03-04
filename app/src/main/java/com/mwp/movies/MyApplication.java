package com.mwp.movies;
///*
//Copyright 2009-2014 Urban Airship Inc. All rights reserved.
//
//Redistribution and use in source and binary forms, with or without
//modification, are permitted provided that the following conditions are met:
//
//1. Redistributions of source code must retain the above copyright notice, this
//list of conditions and the following disclaimer.
//
//2. Redistributions in binary form must reproduce the above copyright notice,
//this list of conditions and the following disclaimer in the documentation
//and/or other materials provided with the distribution.
//
//THIS SOFTWARE IS PROVIDED BY THE URBAN AIRSHIP INC ``AS IS'' AND ANY EXPRESS OR
//IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
//MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
//EVENT SHALL URBAN AIRSHIP INC OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
//INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
//BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
//DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
//LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
//OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
//ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
// */
//


import android.app.Application;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class MyApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
 
     // UNIVERSAL IMAGE LOADER SETUP
     		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
     				.cacheOnDisc(true).cacheInMemory(true)
     				.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
     				.displayer(new FadeInBitmapDisplayer(200)).build();

     		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
     				getApplicationContext())
     				.defaultDisplayImageOptions(defaultOptions)
     				.memoryCache(new WeakMemoryCache())
     				.discCacheSize(100 * 1024 * 1024).build();

     		ImageLoader.getInstance().init(config);
     		// END - UNIVERSAL IMAGE LOADER SETUP
        
        
//        AirshipConfigOptions options = AirshipConfigOptions.loadDefaultOptions(this);
//
//        // Optionally, customize your config at runtime:
//        //
//         options.inProduction = true;
////         options.developmentAppKey = "JLxNnsbjRAest_B1eYUNVg";
////         options.developmentAppSecret ="_ZwqGmfUQymZ_cyzk31spQ";
//
//        UAirship.takeOff(this, options);
//        Logger.logLevel = Log.VERBOSE;
//
//        PushManager.enablePush();
//        //use CustomPushNotificationBuilder to specify a custom layout
////        CustomPushNotificationBuilder nb = new CustomPushNotificationBuilder();
////
////        nb.statusBarIconDrawableId = R.drawable.icon_small;//custom status bar icon
////
////        nb.layout = R.layout.notification;
////        nb.layoutIconDrawableId = R.drawable.icon;//custom layout icon
////        nb.layoutIconId = R.id.icon;
////        nb.layoutSubjectId = R.id.subject;
////        nb.layoutMessageId = R.id.message;
//
//        // customize the sound played when a push is received
//        //nb.soundUri = Uri.parse("android.resource://"+this.getPackageName()+"/" +R.raw.cat);
//
////        PushManager.shared().setNotificationBuilder(nb);
//        PushManager.shared().setIntentReceiver(IntentReceiver.class);
//        UALocationManager.shared().setIntentReceiver(IntentReceiver.class);
    }
}
