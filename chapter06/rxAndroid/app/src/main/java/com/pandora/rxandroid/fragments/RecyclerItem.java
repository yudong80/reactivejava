package com.pandora.rxandroid.fragments;

import android.graphics.drawable.Drawable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class RecyclerItem {
    Drawable image;
    String title;
}
