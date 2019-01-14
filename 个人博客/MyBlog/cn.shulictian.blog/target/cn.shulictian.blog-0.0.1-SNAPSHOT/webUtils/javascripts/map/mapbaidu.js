var showDataLevel = 17;
var defaultCur;
var gc;
var map;

var InitMap = function (callback) {
	map = new BMap.Map("bmap_box");
	map.addControl(new BMap.NavigationControl());   //地图平移缩放控件
	map.addControl(new BMap.ScaleControl());        //比例尺控件
	map.addControl(new BMap.OverviewMapControl());  //缩略地图控件
	map.addControl(new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP, BMAP_SATELLITE_MAP]}));      //地图类型控件
	map.enableScrollWheelZoom();                    //开启鼠标滚轮控制缩放
	map.setZoom(showDataLevel);                     //设定地图层数
	map.centerAndZoom("北京", 5);                   //定位到13层的北京
	defaultCur = map.getDefaultCursor();
	minZoom = 19;
	gc = new BMap.Geocoder();   //类用于获取用户的地址解析，创建一个地址解析器的实例
};


//地图叠加车辆类
var TrackerView = function () {
	var trackerList = [];
	var currentTrackerMarker = null;

	var getTrackerIcon = function (isFirst) {
		var imgUrl = '/assets/images/loc_' + isFirst + '.png';
		return new BMap.Icon(imgUrl, new BMap.Size(32, 32));
	};

	var addTrackerMarker = function (trackermodel) {
		var trackerMarker = new BMap.Marker(new BMap.Point(trackermodel.lon, trackermodel.lat), {icon: getTrackerIcon(1)});
		trackerMarker.tracker_data = trackermodel;
		gc.getLocation(new BMap.Point(trackermodel.lon, trackermodel.lat), function (rs) {
			trackermodel.address = rs.address;
		});
		trackerMarker.addEventListener("click", function (e) {
			currentTrackerMarker = e.target;
			var trackerModel = e.target.tracker_data;
			if (!trackerModel) {
				return;
			}

			//locateCenter(trackerModel.lon, trackerModel.lat, true);
			createInfoWindow(trackerModel, e.point);

		});
		map.addOverlay(trackerMarker);
	};


	var delAllTrackerMarker = function () {
		map.clearOverlays();
	};

	var addAllTrackers = function () {
		if (trackerList.length > 0) {
			if (trackerList.length > 1) {
				var points = [];
				trackerList.forEach(function (trace) {
					points.push(new BMap.Point(trace.lon, trace.lat));
				});
				var polyline = new BMap.Polyline(points, {
					strokeColor: "blue",
					strokeWeight: 6,
					strokeOpacity: 0.5
				});
				map.addOverlay(polyline);
			}
			addTrackerMarker(trackerList[trackerList.length - 1]);
		}
	};

	return {
		set_trackerlist: function (list) {
			trackerList = list;
		},
		addAllTrackers: function () {
			addAllTrackers();
		},
		delAllTrackers: function () {
			delAllTrackerMarker();
		}
	}
};

var trackerView = new TrackerView();


//地图定位
var locateCenter = function (lon, lat, currentLevel) {
	if (lon && lat) {
		var mapLevel = currentLevel ? map.getZoom() : showDataLevel;
		map.centerAndZoom(new BMap.Point(lon, lat), mapLevel);
	}
};

var range = function () {
	var myDis = new BMapLib.DistanceTool(map);
	myDis.open();
};


//创建弹出窗口
var createInfoWindow = function (trackerModel, point) {
	var opts = {
		width: 350 //信息窗口宽度height:100,//信息窗口高度
	};
	var infoWindow = new BMap.InfoWindow("", opts);
	var postionAddress = (!trackerModel.address || trackerModel.address == '') ? trackerModel.lon + ' , ' + trackerModel.lat : trackerModel.address;
	var strContent =
			'<style type="text/css">.box_info { padding: 10px; } .box_info p { height: 30px; line-height: 30px; }</style><div class="box_info"><p>IMEI：&nbsp;{0}</p><p>时间：&nbsp;{1}</p><p>位置：&nbsp;{2}</p></div>';

	strContent = strContent.replace("{0}", trackerModel.imei).replace("{1}", trackerModel.runtime).replace("{2}", postionAddress);
	infoWindow.setContent(strContent);
	infoWindow.enableMaximize();
	map.openInfoWindow(infoWindow, point);
};
