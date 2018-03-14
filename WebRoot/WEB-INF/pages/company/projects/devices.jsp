<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/iziModal.min.css">

<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>

<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
<script src="js/myCharts.js"></script>

<script type="text/javascript">

/*-------- PJ添加的内容------------
  var projectid = '${projectid}';
  data : {
				"projectID" : projectid
			},
 */
	var projectid = '${projectid}';

	$(function() {
		$.ajax({
			url : "getAllDivices",
			type : "post",
			data : {
				"projectID" : projectid
			},
			dataType : "json",
			success : function(deviceList) {
				buildDeviceList(deviceList)
			}
		});

		$('#modal').iziModal({
			title : "chart"
		});

		$(document).on("click", ".trigger", function(event) { //dom action
			event.preventDefault();
			var deviceId = $(this).parents("tr").find("td:eq(0)").text();
			var status = $(this).parents("tr").find("td:eq(1)").text();
			var type = status.substring(0, status.indexOf(':'));
			var device = deviceId + '\n' + type;

			$("#modal").iziModal("open", function(modal) {
				modal.startLoading();

				$.post('getDeviceData', {
					deviceID : deviceId,
					dataType : type,
					start : -1,
					max : -1
				}, function(data) {

					createLineChart(data, device);
					modal.stopLoading();
				});

			});
		});

	});

	function buildDeviceList(deviceList) {
		$.each(deviceList, function(index, device) {
			var tr = $("<tr></tr>");
			var deviceId = $("<td></td>").append(device.deviceID);
			tr = tr.append(deviceId);
			var tb = $("<td></td>").append($("<table></table>")); //create a table in td
			$.each(device.status, function(dataType, value) {
				tb = tb.append($("<tr></tr>").append($("<td></td>").append(dataType + "  :  " + value)));
			});
			tr = tr.append(tb); //add table to tr
			var chartButton = $("<td></td>").append($("<button></button>").addClass("btn btn-primary btn-sm trigger").append($("<span></span>").addClass("glyphicon glyphicon-stats").attr("aria-hidden", "true")).append(" chart"));
			tr.append(chartButton).appendTo("#deviceTable tbody");
		})
	}
</script>
</head>
<body>
	<script type="text/javascript" src="js/iziModal.min.js"></script>
	<div class="container" style="margin: 20px 0px 0px 210px;">
		<!-- Title -->
		<div class="row">
			<div class="col-md-12">
				<h1>设备列表</h1>
			</div>
		</div>
		<!-- Device List -->
		<div class="row">
			<div class="col-md-12">
				<table id="deviceTable" class="table table-hover">
					<thead>
						<tr>
							<th>设备ID</th>
							<th>状态</th>
							<th>图表</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
	</div>

	<div id="modal">
		<div id="line" style="width:600px; height:400px;"></div>
	</div>
</body>
</html>