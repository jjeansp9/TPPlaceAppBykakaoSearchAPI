package com.jspstudio.tpplaceappbykakaosearchapi

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        // 카카오 SDK 초기화 - 플랫폼에서 발급된 "네이티브앱키" 필요
        KakaoSdk.init(this, "20cf11def71d8a430eafb1a2daec3d7b")
    }
}