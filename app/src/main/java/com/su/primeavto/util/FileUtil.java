package com.su.primeavto.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.os.Environment;

import com.su.primeavto.App;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileUtil {

    public static final String GALLERY_DIR =
            App.getInstance().getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString();
    public static final String DIR_APP_NAME = "/primeAuto";

    public static File createImageFile(Context context) throws IOException {

        String timeStamp = new SimpleDateFormat("MM.dd HH.mm.ss", Locale.getDefault()).format(new Date());
        String imageFileName = timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        return File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
    }

    public static File getAppDir() {
        File appDir = new File(GALLERY_DIR, DIR_APP_NAME);

        appDir.mkdirs();
        return appDir;
    }

    public static File saveToGallery(Context context, File imageFile, String fileName) {
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        File file = new File(getAppDir(), fileName);
        if (bitmap != null) {
            try {
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            FileUtil.scan(context, file);
        }
        return file;

    }


    public static void scan(Context context, File file) {
        if (context != null)
            MediaScannerConnection.scanFile(context, new String[]{file.toString()}, null, (path, uri) -> {

            });
    }


}
