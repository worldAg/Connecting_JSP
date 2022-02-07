<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>main page</title>
    <link rel="stylesheet" href="css/bootstrap.css" />
    
    <link rel="preconnect" href="https://fonts.googleapis.com"> <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin> 
	<link href="https://fonts.googleapis.com/css2?family=Gaegu&display=swap" rel="stylesheet">
	
	<style>
		* {
			font-family: 'Gaegu', cursive!important;
		}
	</style>
</head>

<body>
    <jsp:include page="header.jsp" />

	<button type="button" class="btn btn-primary btn-lg">새로운 소식을 확인하세요></button>
	
    <div id="boards">
        <div class="card" style="width: 18rem;">
            <img src="./images/icecream.png" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the
                    card title and make up the bulk of the card's content.</p>
            </div>
        </div>
        <div class="card" style="width: 18rem;">
            <img src="./images/icecream.png" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the
                    card title and make up the bulk of the card's content.</p>
            </div>
        </div>
        <div class="card" style="width: 18rem;">
            <img src="./images/icecream.png" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the
                    card title and make up the bulk of the card's content.</p>
            </div>
        </div>
        <div class="card" style="width: 18rem;">
            <img src="./images/icecream.png" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the
                    card title and make up the bulk of the card's content.</p>
            </div>
        </div>


    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
    </script>
</body>

</html>