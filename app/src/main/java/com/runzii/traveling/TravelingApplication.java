package com.runzii.traveling;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.baidu.mapapi.SDKInitializer;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.runzii.traveling.http.HttpUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by Wouldyou on 2015/5/25.
 */
public class TravelingApplication extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {

        SDKInitializer.initialize(getApplicationContext());
        appContext = getApplicationContext();
        HttpUtils.init();

        //initialize and create the image loader logic
        DrawerImageLoader.init(new DrawerImageLoader.IDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Picasso.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Picasso.with(imageView.getContext()).cancelRequest(imageView);
            }

            @Override
            public Drawable placeholder(Context ctx) {
                return null;
            }
        });
        super.onCreate();
    }

    public static Context getAppContext() {
        return appContext;
    }
}
