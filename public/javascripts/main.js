// Global Variables
var activeScreen= "#homeScreen";
var round;
var modAlerts;
var user;
var allMarkers;

/******************** GLOBAL FUNCTIONS ********************/

// Switches screens
function switchScreen(screen) {

    //New Screen
    var newScreen = "#"+screen+"Screen";

    // Switch Screens
    if(activeScreen != newScreen) {
        $(activeScreen).fadeOut("fast", function () {
            activeScreen = newScreen;
            $(activeScreen).fadeIn(400, "linear");
            if(screen=="map"){
                initMap();
                $.ajax({
                    type: "GET",
                    url: '@routes.Maps.getMarkers',
                    success : function(data) {
                    console.log(data);
                    allMarkers = data;
                    loadMarkers();
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log("Marker Alert Error");
                    document.getElementById('no-game-message').style.borderColor = "red";
                    document.getElementById('no-game-message').style.display= 'block';
                }
            });
            }
        });
    }

}

/* Submit Marker on Map */
$("#markerForm").on('submit', function (e) {
    e.preventDefault();

    var body = {
        title: $('#title').val(),
        iconImage: $('#markerType').val(),
        position: newCoord,
        title: document.getElementById('markerType').options[document.getElementById('markerType').selectedIndex].innerHTML
    };
    $.ajax({
        type: "POST",
        url: '@routes.Maps.addMarker()',
        data: body,
        success : function(data) {

        // Cal something to then go update the maps for everyone
        /*insertMagicalFunctionHere();*/

        /* Reset the form fields */
        document.getElementById('markerForm').positionStuff.value = "";
        document.getElementById('markerForm').additionalInformation.value = "";
        console.log(data);
    },
    error : function(data) {
        console.log(data);
    }
})
});

/*
/!*
// Add Marker to map
function addMarker(mapID, form){
    var mymap = document.getElementById(mapID)
    var newTitle = form.title.value;
    var newPos = {lat: -25.363, lng: 131.044};
    var contentString = newTitle+'<br><br>'+form.information.value;
    console.log(mapID+ " | "+ form+ " | "+ newTitle+ " | "+ newPos+ " | "+ contentString)

    var marker = new google.maps.Marker({
        position: newPos,
        map: mymap,
        title: 'Hello World!'
    });

*!/

    /!*'<div id="content">'+
        '<div id="siteNotice">'+
        '</div>'+
        '<h1 id="firstHeading" class="firstHeading">Uluru</h1>'+
        '<div id="bodyContent">'+
        '<p><b>Uluru</b>, also referred to as <b>Ayers Rock</b>, is a large ' +
        'sandstone rock formation in the southern part of the '+
        'Northern Territory, central Australia. It lies 335&#160;km (208&#160;mi) '+
        'south west of the nearest large town, Alice Springs; 450&#160;km '+
        '(280&#160;mi) by road. Kata Tjuta and Uluru are the two major '+
        'features of the Uluru - Kata Tjuta National Park. Uluru is '+
        'sacred to the Pitjantjatjara and Yankunytjatjara, the '+
        'Aboriginal people of the area. It has many springs, waterholes, '+
        'rock caves and ancient paintings. Uluru is listed as a World '+
        'Heritage Site.</p>'+
        '<p>Attribution: Uluru, <a href="https://en.wikipedia.org/w/index.php?title=Uluru&oldid=297882194">'+
        'https://en.wikipedia.org/w/index.php?title=Uluru</a> '+
        '(last visited June 22, 2009).</p>'+
        '</div>'+
        '</div>';
       *!/

    var infowindow2 = new google.maps.InfoWindow({
        content: contentString
    });

    var marker2 = new google.maps.Marker({
        position: newPos,
        map: mapID,
        title: newTitle
    });
    marker2.addListener('click', function() {
        infowindow2.open(map, marker2);
    });

}*/
