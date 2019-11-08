package com.xlib.limeutils.core;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * Copyright 2018 (C) Xplo
 * <p>
 * Created  : 10/23/2018
 * Author   : Xplo
 * Version  : 1.0
 * Desc     :
 * Comment  : Contextor.getInstance().init(appContext);
 */
public class Contextor {

    @SuppressLint("StaticFieldLeak")
    private static Contextor instance;

    private Context context;

    public static Contextor getInstance() {
        if (instance == null) {
            instance = new Contextor();
        }
        return instance;
    }

    public void init(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }



}
