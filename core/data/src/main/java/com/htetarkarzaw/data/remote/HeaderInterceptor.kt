package com.htetarkarzaw.data.remote

import com.htetarkarzaw.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder().addHeader(
            "Authorization",
            BuildConfig.API_TOKEN
        )
        return chain.proceed(builder.build())
    }
}
//todo : this should be use with credential.properties in BuildConfig. But don't have time
//9ea7ba427112520832f5da2af3a47bd4
//eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5ZWE3YmE0MjcxMTI1MjA4MzJmNWRhMmFmM2E0N2JkNCIsInN1YiI6IjY0NWExMzZkMTU2Y2M3MDEzZmYyMWIxZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4ec3-tZUlQrdKQsKID9Wt904Slj_-0H4Gdue2v3ujD0
