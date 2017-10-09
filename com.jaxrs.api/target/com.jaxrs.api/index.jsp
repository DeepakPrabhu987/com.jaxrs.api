<html>
<head>
<script src="https://code.jquery.com/jquery-1.10.2.min.js" type="text/javascript"></script>
<script type="text/javascript">

	var dataSaved;
	$(document).ready(function () {
	    $.ajax({ 
	        type: 'GET', 
	        url: 'http://localhost:9999/com.jaxrs.api/webapi/hotels/id/73',
	        success: function (data) { 
	        	alert("Hi");
	            dataSaved = JSON.parse(data);
	            $('#divHotels').html(data);
	        }
	    });
	}); 

</script>
</head>
<body>
    <div id="divHotels"></div>
</body>
</html>
