package com.jspstudio.tpplaceappbykakaosearchapi.network

import com.google.cloud.audit.AuthorizationInfo
import com.jspstudio.tpplaceappbykakaosearchapi.model.NidUserInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface RetrofitApiService {

    // 네아로 사용자 정보 API
    @GET("/v1/nid/me")
    fun getNidUser(@Header("Authorization") authorizationInfo:String): Call<NidUserInfoResponse>
}