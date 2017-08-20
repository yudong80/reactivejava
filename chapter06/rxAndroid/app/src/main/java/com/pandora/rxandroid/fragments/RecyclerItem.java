package com.pandora.rxandroid.fragments;

import android.graphics.drawable.Drawable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
class RecyclerItem {
    private Drawable image;
    private String title;
}
