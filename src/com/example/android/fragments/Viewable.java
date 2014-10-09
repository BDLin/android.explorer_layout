package com.example.android.fragments;

import android.content.Context;
import android.view.View;

public interface Viewable {
	public abstract View getView(Context context);
	public abstract String getTitle();
}