var theImages = new Array(); 

theImages[0] = '/images/titles/title3.gif'
theImages[1] = '/images/titles/title4.gif'
theImages[2] = '/images/titles/title5.gif'
theImages[3] = '/images/titles/title7.gif'
theImages[4] = '/images/titles/title8.gif'
theImages[5] = '/images/titles/title9.gif'
theImages[6] = '/images/titles/title10.gif'
theImages[7] = '/images/titles/title11.gif'
theImages[8] = '/images/titles/title12.gif'
theImages[9] = '/images/titles/title13.gif'

var j = 0
var p = theImages.length;
var preBuffer = new Array()
for (i = 0; i < p; i++){
   preBuffer[i] = new Image()
   preBuffer[i].src = theImages[i]
}

var whichImage = Math.round(Math.random()*(p-1));
function showImage(){
    document.write('<img width="683" height="71" border="0" alt="Andreas Nerlich" src="'+theImages[whichImage]+'">');
}
