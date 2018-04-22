package com.ramesh.recyclerviewwithleftandrightarrows;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ramesh.recyclerviewwithleftandrightarrows.data.DummyDataHelper;
import com.ramesh.recyclerviewwithleftandrightarrows.databinding.ActivityMainBinding;
import com.ramesh.recyclerviewwithleftandrightarrows.model.App;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<App> appsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DummyDataHelper dummyDataHelper = new DummyDataHelper();
        appsList = dummyDataHelper.getAppList();
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        AppListAdapter appListAdapter=new AppListAdapter(getApplicationContext(),appsList);
        binding.recyclerViewOne.setAdapter(appListAdapter);

        AppListAdapter appListAdapterTwo=new AppListAdapter(getApplicationContext(),appsList);
        binding.recyclerViewTwo.setAdapter(appListAdapterTwo);
    }
}
