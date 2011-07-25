var timer_id;
var timer_is_on = 0;

function timedCount() {
	getClientTime();
	timer_id = setTimeout("timedCount()",1000);
}

function doTimer() {
	if (!timer_is_on) {
		timer_is_on = 1;
		timedCount();
	}
}

function getClientTime() {
	var d = new Date();
	
	var curr_hour = d.getHours();
	var curr_min = d.getMinutes();
	var curr_sec = d.getSeconds();
	
	curr_hour = curr_hour.toString();
	curr_min = curr_min.toString();
	curr_sec = curr_sec.toString();

	if (curr_hour.length < 2) {
		curr_hour = "0" + curr_hour;
	}

	if (curr_min.length < 2) {
		curr_min = "0" + curr_min;
	}
	
	if (curr_sec.length < 2) {
		curr_sec = "0" + curr_sec;
	}
	
	document.getElementById('mainForm:txtClientTime').innerHTML = curr_hour + ":" + curr_min + ":" + curr_sec;
}