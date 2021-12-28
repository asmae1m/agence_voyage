$(document).ready(function() {
	$('input[name=date_arrive]').click(function() {
		verifi();
	});
});
function verifi() {
	var dd = $('input[name=date_depart]').val();
	var da = $('input[name=date_arrive]').val();
	document.getElementById('dr').setAttribute("min",dd);
	console.log(dd);
}