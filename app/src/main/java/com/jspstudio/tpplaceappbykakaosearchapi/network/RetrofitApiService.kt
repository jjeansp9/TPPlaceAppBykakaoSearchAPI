package com.jspstudio.tpplaceappbykakaosearchapi.network

import com.google.cloud.audit.AuthorizationInfo
import com.jspstudio.tpplaceappbykakaosearchapi.model.KakaoSearchPlaceResponse
import com.jspstudio.tpplaceappbykakaosearchapi.model.NidUserInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitApiService {

    // 네아로(네이버아이디로그인) 사용자 정보 API
    @GET("/v1/nid/me")
    fun getNidUser(@Header("Authorization") authorizationInfo:String): Call<NidUserInfoResponse>

    // 카카오 키워드 장소검색 API
    @Headers("Authorization: KakaoAK 017279895554e42fcb3b5c9b95870a71")
    @GET("/v2/local/search/keyword.json")
    fun searchPlaces(@Query("query") query:String,@Query("x") longitude:String,@Query("y") latitude:String,):Call<KakaoSearchPlaceResponse>
}