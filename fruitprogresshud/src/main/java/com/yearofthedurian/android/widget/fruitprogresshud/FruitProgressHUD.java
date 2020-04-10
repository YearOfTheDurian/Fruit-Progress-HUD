package com.yearofthedurian.android.widget.fruitprogresshud;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

/**
 * Created by Richard Koivusalo
 * Copyright 2020
 * Year of the Durian, FruitVenture LLC
 */

public class FruitProgressHUD extends Fragment {

    AnimatedVectorDrawableCompat animatedVectorDrawableCompat;
    AnimatedVectorDrawableCompat animatedVectorDrawableCompatSuccess;
    AnimatedVectorDrawableCompat animatedVectorDrawableCompatFailure;

    private View mView;
    private ImageView mImageView;
    private TextView mTextViewComplete;
    private TextView mTextView;
    private boolean stopAnimation = false;
    private int mDuration;
    private boolean mSuccess;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress_hud, container, false);

        mView = view.findViewById(R.id.progress_hud_background);
        mImageView = view.findViewById(R.id.progress_hud_image_view);
        mTextView = view.findViewById(R.id.progress_hud_text_view);
        mTextViewComplete = view.findViewById(R.id.progress_hud_text_view_end);

        return view;
    }

    private void switchTextField() {
        if (stopAnimation) {
            mTextViewComplete.setVisibility(View.VISIBLE);
            mTextView.setVisibility(View.INVISIBLE);
        } else {
            mTextViewComplete.setVisibility(View.INVISIBLE);
            mTextView.setVisibility(View.VISIBLE);
        }

    }

    private void hideHUD() {
        mView.setVisibility(View.INVISIBLE);
        mImageView.setVisibility(View.INVISIBLE);
        mTextView.setVisibility(View.INVISIBLE);
        mTextViewComplete.setVisibility(View.INVISIBLE);
    }

    private void showHUD() {
        mView.setVisibility(View.VISIBLE);
        mImageView.setVisibility(View.VISIBLE);
        mTextView.setVisibility(View.VISIBLE);
        resetHUD();
    }

    private void resetHUD() {
        mTextView.setText("");
        mTextViewComplete.setText("");
    }


    private void animate(View view) {
        animatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(getContext(), R.drawable.avd_durian);
        animatedVectorDrawableCompatSuccess = AnimatedVectorDrawableCompat.create(getContext(), R.drawable.avd_success_durian);
        animatedVectorDrawableCompatFailure = AnimatedVectorDrawableCompat.create(getContext(), R.drawable.avd_failure_durian);

        animatedVectorDrawableCompat.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationEnd(Drawable drawable) {
                super.onAnimationEnd(drawable);
                if (stopAnimation) {
                    if (mSuccess) {
                        mImageView.setImageDrawable(animatedVectorDrawableCompatSuccess);
                        animatedVectorDrawableCompatSuccess.start();
                    } else {
                        mImageView.setImageDrawable(animatedVectorDrawableCompatFailure);
                        animatedVectorDrawableCompatFailure.start();
                    }

                } else {
                    animatedVectorDrawableCompat.start();
                }

            }
        });

        animatedVectorDrawableCompatSuccess.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationEnd(Drawable drawable) {
                super.onAnimationEnd(drawable);
                try {
                    Thread.sleep(mDuration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hideHUD();
                stopAnimation = false;
            }
        });

        animatedVectorDrawableCompatFailure.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationEnd(Drawable drawable) {
                super.onAnimationEnd(drawable);
                try {
                    Thread.sleep(mDuration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hideHUD();
                stopAnimation = false;
            }
        });

        mImageView.setImageDrawable(animatedVectorDrawableCompat);
        animatedVectorDrawableCompat.start();

    }

    public void show(String message) {
        mTextView.setText(message);
        animate(this.getView());
    }

    public void dismiss(String message, final int duration, boolean success) {
        stopAnimation = true;
        mSuccess = success;
        switchTextField();
        mTextViewComplete.setText(message);

        mDuration = duration;
        animatedVectorDrawableCompat.stop();
    }

    public void setAlpha(float alpha) {
        mView.setAlpha(alpha);
    }
}