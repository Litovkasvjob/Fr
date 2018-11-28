<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>FRICTION</title>
    <script src="https://code.jquery.com/jquery-3.1.0.js"></script>
    <link rel="stylesheet" type="text/css" href="http://getbootstrap.com/examples/dashboard/dashboard.css"/>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <script src="http://getbootstrap.com/assets/js/ie-emulation-modes-warning.js"></script>
	
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	
	
	<script>
        google.charts.load('current', {packages: ['corechart', 'line']});
        google.charts.setOnLoadCallback(drawBasic);

        function drawBasic() {

            var data = new google.visualization.DataTable();
            data.addColumn('number', 'load');
            data.addColumn('number', 'friction');

            data.addRows([
                <c:forEach var="friction" items="${frictions}">
                
                [${friction.key}, ${friction.value}],
              
                 </c:forEach>
            ]);

            var options = {
                hAxis: {
                    title: 'load'
                },
                vAxis: {
                    title: 'friction'
                },
                width:  500,
                height: 300
            };

            var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

            chart.draw(data, options);
        }
	</script>



    <style type="text/css">
        body {
            padding-top: 70px;
        }

        .absolute {
            position: absolute;
        }

        .main-content {
            float: right;
            width: 83%;
            padding-right: 40px;
            padding-left: 40px;
        }

        .center {
            text-align: center;
        }

    </style>
</head>
<body>

<%@include file="../commons/header.jsp" %>
<div class="container-fluid">
    <div class="row">

        <div class="row">
            <div class="main main-content">
                <div class="col-xs-3">
                    <table class="table table-bordered">
                        <thead>
                        <tr class="info">
                            <th class="center">Load</th>
                            <th class="center">Friction</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="friction" items="${frictions}">
                            <tr class="info">

                                <td class="center">${friction.key}</td>
                                <td class="center">${friction.value}</td>

                            </tr>
                        </c:forEach>
                        <tr>
                            <td class="active" colspan="2">
                                <center>
                                    <a class="btn btn-info" href="${base}/home">Home</a>
                                </center>
                            </td>
                        </tr>
                        <tr>
                            <td class="active" colspan="2">
                                <center>
                                    <a class="btn btn-info" href="${base}/logout">Log out</a>
                                </center>
                            </td>
                        </tr>
                        
                        </tbody>
                       
                    </table>
                    <div id="chart_div">
    
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
