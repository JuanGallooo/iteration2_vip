//var myStops = JSON.parse(Stop);

var mymap = L.map('mapid').setView([3.442484,-76.516342], 15);

var layerGroupBuses = L.layerGroup().addTo(mymap);
var layerGroupStops = L.layerGroup().addTo(mymap);

const tileUrl = 'https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw';

const attribut = {maxZoom: 20, 
				  attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>,Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
				  id: 'mapbox/streets-v11',
				  tileSize: 512,
				  zoomOffset: -1};

L.tileLayer(tileUrl,attribut).addTo(mymap);


function onMapClick(e) {
	popup = L.popup();
	popup.setLatLng(e.latlng).setContent("You clicked the map at " + e.latlng.toString()).openOn(mymap);
}	

function loadStops(){
	
	for (let item of myStops) {
		const Gicon = L.icon({iconUrl: item.img, iconSize:[20, 20],iconAnchor:[20, 20],popupAnchor:[0, 0]});		
		L.marker([item.lat, item.long], {icon: Gicon}).addTo(layerGroupStops);
	}
}

var info = null;

var xmlHttp = null;  

function GetPositionRequest() { 
	
	var Url = "http://localhost:8080/api/positions";      
	xmlHttp = new XMLHttpRequest();      
	xmlHttp.onreadystatechange = ProcessRequest;     
	xmlHttp.open( "GET", Url, true );     
	xmlHttp.send( null ); 

} 



function ProcessRequest()  {     
	if ( xmlHttp.readyState == 4 && xmlHttp.status == 200 )      {         
		if ( xmlHttp.responseText == "Not found" )          {            

			} else {             
				info = xmlHttp.responseText;
		}
	}
}

var markers = {};

function update(){
	 this.GetPositionRequest()
	 
	 
	 
	 if(info!=null){

		 myBuses = JSON.parse(info)["Bus"];
		 myStops = JSON.parse(info)["Stop"];
		
		 for (let item of myBuses) {
			 const Gicon = L.icon({iconUrl: item.img, iconSize:[20, 20],iconAnchor:[20, 20],popupAnchor:[0, 0]});	
			 
			 if(markers[item.id]==null){					
			
				 markers[item.id] = L.marker([item.lat, item.long]).addTo(layerGroupBuses);				 
				 markers[item.id].setIcon(Gicon);
			 
			 }else{
				 
				 markers[item.id].setIcon(Gicon);
				 markers[item.id].setLatLng([item.lat, item.long]);
				 				
			 }
			}
		 
		 for (let item of myStops) {
				const Gicon = L.icon({iconUrl: item.img, iconSize:[20, 20],iconAnchor:[20, 20],popupAnchor:[0, 0]});		
				L.marker([item.lat, item.long], {icon: Gicon}).addTo(layerGroupStops);
			}
	 }
}

setInterval(update,300);
mymap.on('click', onMapClick);