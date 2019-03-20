package com.example.rxjava_retrofit_mvp_md.http.converter.string;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;


public final class StringResponseBodyConverter implements Converter<ResponseBody, String> {
    @Override
    public String convert(ResponseBody value) throws IOException {

        try {
            return value.string();
        } finally {
            value.close();
        }


    }
}
