package com.yearofthedurian.android.widget.fruitprogresshud;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.yearofthedurian.android.widget.fruitprogresshud.FruitProgressHUD;
import com.yearofthedurian.android.widget.fruitprogresshud.R;


import android.content.Context;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

/**
 * Created by Richard Koivusalo
 * Copyright 2020
 * Year of the Durian, FruitVenture LLC
 */

public class MainActivity extends AppCompatActivity {

    FruitProgressHUD fruitProgressHUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fruitProgressHUD  = (FruitProgressHUD) getSupportFragmentManager().findFragmentById(R.id.fragment_progress_hud);

        fruitProgressHUD.setTheme(FruitProgressTheme.DARK);
        fruitProgressHUD.setAlpha(0.3f);

        fruitProgressHUD.show("Loading...");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fruitProgressHUD.dismiss("Success", 1000, true);
            }
        }, 2000);






    }


}
