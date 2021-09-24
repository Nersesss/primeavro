package com.su.primeavto.util;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.su.primeavto.R;

import java.io.File;

public class PicassoHelper {


    public static void setImagePhoto(ImageView imageView, String filePath) {
        Picasso picasso = Picasso.get();
        picasso.setLoggingEnabled(true);
        picasso.load(filePath) // web image url
                .error(R.drawable.ic_logo)
                .placeholder(R.drawable.ic_logo)
                .into(imageView);
    }
    public static void setImagePhoto(ImageView imageView, File file) {
        Picasso picasso = Picasso.get();
        picasso.setLoggingEnabled(true);
        picasso.load(file) // web image url
                .error(R.drawable.ic_logo)
                .placeholder(R.drawable.ic_logo)
                .into(imageView);
    }

}
