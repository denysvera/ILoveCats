package com.example.ilovecats.network

import com.example.ilovecats.Helpers.Constants
import com.example.ilovecats.Helpers.Helpers
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthenticationInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val builder: Request.Builder = original.newBuilder()
            .header("x-api-key", Constants.API_KEY)
        val request: Request = builder.build()
        val response =  chain.proceed(request)
        val allHeaders = response.headers
        Helpers.paginationCount = allHeaders.get("Pagination-Count")
        Helpers.paginationPage = allHeaders.get("Pagination-Page")

        return response
    }
}