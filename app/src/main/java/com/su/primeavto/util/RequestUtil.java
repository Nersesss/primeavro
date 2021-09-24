package com.su.primeavto.util;

import androidx.annotation.StringDef;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public final class RequestUtil {


    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            RequestUtil.MediaTypes.MEDIA_TYPE_TEXT,
            RequestUtil.MediaTypes.MEDIA_TYPE_JSON,
            RequestUtil.MediaTypes.MEDIA_TYPE_IMAGE,
            RequestUtil.MediaTypes.MEDIA_TYPE_IMAGE_JPEG,
            RequestUtil.MediaTypes.MEDIA_TYPE_IMAGE_PNG,
            RequestUtil.MediaTypes.MEDIA_TYPE_AUDIO,
            RequestUtil.MediaTypes.MEDIA_TYPE_AUDIO_MPEG,
            RequestUtil.MediaTypes.MEDIA_TYPE_AUDIO_OGG,
            RequestUtil.MediaTypes.MEDIA_TYPE_VIDEO,
    })
    @interface MediaType {
    }


    public static class MediaTypes {
        public static final String MEDIA_TYPE_TEXT = "text/plain";
        public static final String MEDIA_TYPE_JSON = "application/json";
        public static final String MEDIA_TYPE_IMAGE = "image/*";
        public static final String MEDIA_TYPE_IMAGE_JPEG = "image/jpeg";
        public static final String MEDIA_TYPE_IMAGE_PNG = "image/png";
        public static final String MEDIA_TYPE_AUDIO = "audio/*";
        public static final String MEDIA_TYPE_AUDIO_MPEG = "audio/mpeg*";
        public static final String MEDIA_TYPE_AUDIO_OGG = "audio/ogg";
        public static final String MEDIA_TYPE_VIDEO = "video/mp4";
    }


    public static final class RequestBodyFactory {


        public static Map<String, RequestBody> create(Map<String, String> dataMap) {
            final Map<String, RequestBody> params = new LinkedHashMap<>();

            for (String paramKey : dataMap.keySet()) {

                params.put(paramKey, create(dataMap.get(paramKey)));
            }
            return params;
        }


        public static Map<String, RequestBody> create(String key, List<String> values) {
            final Map<String, RequestBody> params = new LinkedHashMap<>();

            int i = 0;

            for (String value : values)
                params.put(
                        key + "[" + i++ + "]",
                        create(value)
                );

            return params;
        }


        public static RequestBody create(String value) {
            return RequestBody.create(okhttp3.MediaType.parse(RequestUtil.MediaTypes.MEDIA_TYPE_TEXT), value);
        }
    }

    public static final class MultipartBodyFactory {

        public static List<MultipartBody.Part> create(
                List<File> files,
                final String name,
                final @MediaType String mediaType) {

            final List<MultipartBody.Part> bodyList = new ArrayList<>();

            int i = 0;
            for (final File file : files)
                bodyList.add(
                        create(name + "[" + i++ + "]", mediaType, file)
                );
            return bodyList;
        }

        public static MultipartBody.Part create(final String name, final @MediaType String mediaType, final File file) {

            return MultipartBody.Part.createFormData(
                    name, file.getName(), RequestBody.create(okhttp3.MediaType.parse(mediaType), file)
            );
        }
    }
}
