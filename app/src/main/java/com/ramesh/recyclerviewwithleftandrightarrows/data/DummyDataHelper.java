package com.ramesh.recyclerviewwithleftandrightarrows.data;

import com.ramesh.recyclerviewwithleftandrightarrows.R;
import com.ramesh.recyclerviewwithleftandrightarrows.model.App;

import java.util.ArrayList;
import java.util.List;

public class DummyDataHelper {

    public DummyDataHelper() {
    }

    public List<App> getAppList() {

        List<App> appList = new ArrayList<>();

        appList.add(new App("WhatsApp", R.drawable.ic_whatsapp));
        appList.add(new App("Skype", R.drawable.ic_skype));
        appList.add(new App("Facebook", R.drawable.ic_facebook));
        appList.add(new App("Google+", R.drawable.ic_gplus));
        appList.add(new App("Instagram", R.drawable.ic_instagram));
        appList.add(new App("LinkedIn", R.drawable.ic_linkedin));
        appList.add(new App("Quora", R.drawable.ic_quora));
        appList.add(new App("Twitter", R.drawable.ic_twitter));
        appList.add(new App("Tumblr", R.drawable.ic_tumblr));
        appList.add(new App("Email", R.drawable.ic_email));
        appList.add(new App("Gallery", R.drawable.ic_gallery));


        return appList;
    }
}

