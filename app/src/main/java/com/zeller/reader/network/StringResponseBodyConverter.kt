package com.zeller.reader.network

import okhttp3.ResponseBody
import retrofit2.Converter

class StringResponseBodyConverter : Converter<ResponseBody, String> {

    override fun convert(value: ResponseBody): String? {
        try {
            return value.string()
        } finally {
            value.close()
        }
    }

}