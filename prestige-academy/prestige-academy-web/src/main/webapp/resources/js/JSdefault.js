setInterval(displayclock, 500);
function displayclock () {
	var time = new Date();
	var hrs = time.getHours();
	var min = time.getMinutes();
	var sec = time.getSeconds();
	
	if (hrs > 24){
		hrs = hrs-24;
	}
	
	if (hrs == 0){
		hrs = 24;
	}
	if (hrs < 10){
		hrs = '0' + hrs;
	}
	
	if (min < 10){
		min = '0' + min;
	}

	if (sec < 10){
		sec = '0' + sec;
	}
	document.getElementById('clock').innerHTML = hrs + ':' + min +':' + sec;
}


//When the user scrolls the page, execute myFunction
window.onscroll = function() {myFunction()};

// Get the navbar
var navbar = document.getElementById("navbar");

// Get the offset position of the navbar
var sticky = navbar.offsetTop;

// Add the sticky class to the navbar when you reach its scroll position. Remove "sticky" when you leave the scroll position
function myFunction() {
  if (window.pageYOffset >= sticky) {
    navbar.classList.add("sticky")
  } else {
    navbar.classList.remove("sticky");
  }
}

//Onglets

$('a')
.click(function (e) {
  e.preventDefault()
  $(this).tab('show')
})
.on('shown.bs.tab', function (e) {
  $('#actif').text($(e.target).text())
  $('#precedent').text($(e.relatedTarget).text())
})


//click liste
$(document).ready(function($) {
    $(".table-row").click(function() {
        window.document.location = $(this).data("href");
    });
});


//click datatable
$(document).ready(function() {
    var table = $('#example').DataTable();
     
    $('#example tbody').on('click', 'tr', function () {
        var data = table.row( this ).data();
        alert( 'You clicked on '+data[0]+'\'s row' );
    } );
} );

//onglets mail
$('#myTab a').on('click', function (e) {
	  e.preventDefault()
	  $(this).tab('show')
	})
ourceMappingURL=bootstrap.min.js.map



//<![CDATA[
function dateTemplateFunc(date) {
    return '<span style="background-color:' + ((date.day < 21 && date.day > 10) ? '#81C784' : 'inherit') + ';border-radius:50%;padding: .25em;width: 1.75em; height:1.75em; display:block;">' + date.day + '</span>';
}
//]]>



 //Todoliste draganddrop

function handleDrop(event, ui) {
    var droppedCar = ui.draggable;

    droppedCar.fadeOut('fast');
}



//reset fields
$('input:text').focus(
	    function(){
	        $(this).val('');
	    });
