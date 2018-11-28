<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>DATA</title>
	
	<script src="https://code.jquery.com/jquery-3.1.0.js"></script>
	
	
	<link rel="stylesheet" type="text/css" href="/css/dashboard.css"/>
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
	<%--<script src="/js/charts/loader.js"></script>--%>
	<script src="https://www.gstatic.com/charts/loader.js"></script>
	
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
		
		#chart_div {
			width: 500px;
			height: 500px;
			margin-left: 300px;
		}
	
	</style>
	
	
	<script>
        google.charts.load('current', {packages: ['corechart', 'line']});
        google.charts.setOnLoadCallback(drawBasic);

        var googleChart;
        var googleChartData;
        var numberData = [];
        var tempNumber = 0;
        var startNumber = 0;
        var options = {
            hAxis: {
                title: 'Time'
            },
            vAxis: {
                title: 'Coef of friction'
            }
        };
        var flag = true;
        var numbers = [];
        
        function newData(data) {

            console.log(data);
            var t = parseInt(data[0], 10);
            var f = parseFloat(data[1]);
            console.log(t);
            console.log(f);
            
            if (tempNumber > 0){
                  t = t + startNumber;
            }
	            
            data[0] = t;
            data[1] = f;
            return data;
	        
        }
        
        
        
        function getData() {

            if (flag == true) {
                
                $.get("/frictionData", function (data) {
                    
                    numberData.push(newData(data));
                    addDataToChart(numberData);
                    setTimeout(getData, 1000);
                    tempNumber = 1;
                }, "json");
            }
        }

        function addDataToChart(data) {
            googleChartData.addRows(data);
            googleChart.draw(googleChartData, options);
            googleChartData.removeRows(0, numberData.length);


        }

        function drawBasic() {
            var chartData = new google.visualization.DataTable();
            chartData.addColumn('number', 'T');
            chartData.addColumn('number', 'f');

            chartData.addRows([]);

            var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

            chart.draw(chartData, options);

            googleChartData = chartData;
            googleChart = chart;

            getData();

        }

        function stopData() {

            $.post("/frictionData", {
                buton: 'STOP',
                com: '0',
                option: 'stop'
            });
            flag = false;
            startNumber = numberData.length;
        }

        function continueData() {


            $.post("/frictionData", {
                buton: 'START',
                com: '1',
                option: 'online'
            });
            flag = true;
            getData();

        }
        
        
        function numbersDataSend(form) {
            
            var elements = form.elements.namedItem('numbers');
            var  numbers = [];
            for (var i = 0, element; element = elements[i++];) {
                numbers.push(element.value);
	            console.log(element.value);
            }
            
            var ok = form.elements.namedItem('ok').value;
            
            $.post("/friction", {
                numbers: JSON.stringify(numbers),
                LOADS: JSON.stringify(numberData)
            });
            console.log(numberData);
            console.log("send numberData to friction");
            console.log(numbers);
            console.log(ok);
            
	        
        }
	
	</script>
</head>
<body>

<%@include file="../commons/header.jsp" %>
<div class="container-fluid">
	<div class="row">
		<%@include file="../commons/sidebar_online.jsp" %>
		<div class="row">
			<div class="main main-content">
				<div class="col-xs-3">
					<table class="table table-bordered">
						<thead>
						<tr class="info">
							<th class="center">Number</th>
							<th class="center">Load</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="load" items="${loads}">
							<tr class="info">
								<td class="center">${load.id}</td>
								<td class="center">${load.load}</td>
							
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			
			</div>
		</div>
		<div id="chart_div">
		
		</div>
	
	</div>
</div>

</body>
</html>
