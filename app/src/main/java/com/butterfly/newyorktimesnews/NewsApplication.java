package com.butterfly.newyorktimesnews;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

/**
 * Created by Fatih Kaplan on 02/05/16.
 */
public class NewsApplication extends Application {

    private static NewsApplication singleton;

    public static String API_SEARCH_KEY = "350ff71b91b4e62a55d5578c90a053f1:17:75168778";

    public static Picasso p;


    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        p = getPicassoInstance();

    }

    public static NewsApplication getInstance() {
        if (singleton == null)
            singleton = new NewsApplication();
        return singleton;
    }

    public Picasso getPicassoInstance() {
        if (p == null) {
            final int memClass = ((ActivityManager) getApplicationContext().getSystemService(
                    Context.ACTIVITY_SERVICE)).getMemoryClass();

            // Use 1/8th of the available memory for this memory cache.
            final int cacheSize = 1024 * 1024 * memClass / 8;
            p = new Picasso.Builder(getApplicationContext())
                    .memoryCache(new LruCache(cacheSize))
                    .build();

        }
        return p;
    }

}
