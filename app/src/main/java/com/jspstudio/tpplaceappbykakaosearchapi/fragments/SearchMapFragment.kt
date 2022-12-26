package com.jspstudio.tpplaceappbykakaosearchapi.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jspstudio.tpplaceappbykakaosearchapi.activities.MainActivity
import com.jspstudio.tpplaceappbykakaosearchapi.activities.PlaceUrlActivity
import com.jspstudio.tpplaceappbykakaosearchapi.databinding.FragmentSearchMapBinding
import com.jspstudio.tpplaceappbykakaosearchapi.model.Place
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapView.POIItemEventListener

class SearchMapFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    val binding:FragmentSearchMapBinding by lazy { FragmentSearchMapBinding.inflate(layoutInflater) }

    val mapView: MapView by lazy { MapView(context) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 맵뷰를 뷰그룹에 추가하여 화면에 배치하도록.. 여기까지만 해도 지도는 일단 보여짐
        binding.containerMapview.addView(mapView)

        // 마커 or 말풍선을 클릭했을 때 반응하기 - 반드시 마커설정[ setMapAndMarkers() ]보다 먼저 맵뷰에 설정해 놓아야 동작함
        mapView.setPOIItemEventListener(markerEventListener)

        // 지도관련 설정들 [ 지도위치, 마커추가 등 ]
        setMapAndMarkers()

        // 앱에 출시하면 동작 안됨. 반드시 플레이스토어 개발자사이트에 있는 앱무결성란에 SHA-1 해시값을 얻어와야함
        // 그 후 카카오개발자사이트의 내애플리케이션에서 키해시값을 추가해야함 [ 지도가이드 참고 ]
    }

    private fun setMapAndMarkers(){
        // 앱 중심점을 내 위치로 변경
        // 현재 내 위치정보는 MainActivity의 멤버변수로 저장되어 있음
        var latitude:Double = (activity as MainActivity).mylocation?.latitude ?: 37.566705
        var longitude:Double = (activity as MainActivity).mylocation?.longitude ?: 126.9784147

        var myMapPoint:MapPoint= MapPoint.mapPointWithGeoCoord(latitude, longitude)
        mapView.setMapCenterPointAndZoomLevel(myMapPoint, 5, true)
        mapView.zoomIn(true)
        mapView.zoomOut(true)

        // 내 위치에 마커 표시
        var marker= MapPOIItem().apply {
            //this.markerType [this 생략가능]
            itemName="me"
            mapPoint= myMapPoint
            markerType= MapPOIItem.MarkerType.YellowPin
        }
        mapView.addPOIItem(marker)

        // 검색결과 장소들.. 마커들을 forEach 반복문으로 추가하기
        val documents:MutableList<Place>? = (activity as MainActivity).searchPlaceResponse?.documents
        documents?.forEach {
            val point:MapPoint= MapPoint.mapPointWithCONGCoord(it.y.toDouble(), it.x.toDouble())

            // 마커옵션 객체를 만들어서 기본 설정들 하기
            val marker: MapPOIItem= MapPOIItem().apply {
                itemName= it.place_name
                mapPoint= point
                markerType= MapPOIItem.MarkerType.RedPin
                selectedMarkerType= MapPOIItem.MarkerType.YellowPin

                // 마커에 표시되지는 않지만 저장하고 싶은 사용자정보가 있다면
                userObject= it
            }
            mapView.addPOIItem(marker)
        }


    }
    private val markerEventListener: MapView.POIItemEventListener = object : POIItemEventListener{
        override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {

        }

        override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {

        }

        // 말풍선을 클릭했을 때 반응하는 콜백메소드
        override fun onCalloutBalloonOfPOIItemTouched(
            p0: MapView?,
            p1: MapPOIItem?,
            p2: MapPOIItem.CalloutBalloonButtonType?
        ) {
            // 두번째 파라미터 p1 : 클릭한 말풍선의 마커객체
            if (p1?.userObject == null) return

            var place:Place= p1?.userObject as Place

            // 장소의 상세정보 URL을 가지고 상세정보 웹페이지를 보여주는 화면으로 전환
            val intent: Intent= Intent(context, PlaceUrlActivity::class.java)
            intent.putExtra("place_url", place.place_url)
            startActivity(intent)
        }

        override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {

        }
    }

}






















