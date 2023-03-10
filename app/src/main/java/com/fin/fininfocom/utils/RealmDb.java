package com.fin.fininfocom.utils;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Regeti Jhansi Rani  on 09-03-2023.
 */
public class RealmDb extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .allowWritesOnUiThread(true)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
