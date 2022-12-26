package com.jspstudio.tpplaceappbykakaosearchapi.model

data class KakaoSearchPlaceResponse(var meta:PlaceMeta, var documents:MutableList<Place>)

data class PlaceMeta(var total_count:Int, var pageable_count:Int, var is_end:Boolean)

data class Place(
    var id:String,
    var place_name:String, // 장소이름
    var category_name:String,
    var phone:String,
    var address_name:String, // 지번주소
    var road_address_name:String, // 도로명주소
    var x:String, // 경도 - longitude
    var y:String, // 위도 - latitude
    var place_url:String, // 장소에 대한 정보 웹사이트
    var distance:String // 중심좌표(내 위치)로 부터의 거리. 단위 : meter [단, 요청파라미터로 x,y를 줬을 때만 값이 존재함]
)