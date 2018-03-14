<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/vue.js"></script>

<title>Strategies</title>

</head>

<!-- 本页代码公司、项目、建筑等获取方式与策略管理下的页面相同，直接调用策略控制器的接口，建议把策略控制器cn.edu.scau.cmi.pengjie.controller.WhstrategyController提供的方法挪到其他地方  -->
<body>
	<div class="container" id="devices" style="margin: 50px 0px 0px 200px; padding: 0px 0px 0px 20px">
		<div>
			<div>
				<label for="selectCompany">公司</label>
				<select id="selectCompany" v-model="selectCompany">
					<option disabled value="">选择公司</option>
					<option v-for="company in companies" :value="company.id">{{company.name}}</option>
				</select>
				&nbsp;
				<label for="selectProject">项目</label>
				<select id="selectProject" v-model="selectProject">
					<option disabled value="">选择项目</option>
					<option v-for="project in projects" :value="project.id">{{project.name}}</option>
				</select>
				&nbsp;
				<label for="selectBuilding">建筑</label>
				<select id="selectBuilding" v-model="selectBuilding">
					<option disabled value="">选择建筑</option>
					<option v-for="building in buildings" :value="building.id">{{building.name}}</option>
				</select>
				&nbsp;
			</div>
			<hr>
			<div v-if="selectBuilding > -1">
				<div>
					<div>
						<!-- Button trigger modal -->
						<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#newDevice">新建设备</button>
					</div>
				</div>
				<div>
					<table class="table table-hover">
						<thead>
							<th>设备编号</th>
							<th></th>
							<th></th>
						</thead>
						<tbody>
							<tr v-for="device in devices">
								<td>{{device.number}}</td>
								<td><a><button data-target="#updateDevice" data-toggle="modal" @click="queryDevice(device.id)"
											class="btn btn-primary">修改</button></a></td>
								<td><a><button @click="deleteDevice(device.id)" class="btn btn-primary">删除</button></a></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<!-- Modal Create -->
		<div class="modal fade" id="newDevice" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">新建设备</h4>
					</div>
					<div class="modal-body">

						<form id="createDeviceForm">
							<div class="form-group">
								<label for="newDeviceNumber">设备编号</label>
								<input class="form-control" id="newDeviceNumber" name="number">
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button class="btn btn-primary" @click="postDevice()">保存</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal END -->

		<!-- Modal Update-->
		<div class="modal fade" id="updateDevice" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">修改设备</h4>
					</div>
					<div class="modal-body">
						<form id="updateDeviceForm">
							<div class="form-group">
								<label for="updateDeviceNumber">设备编号</label>
								<input id="updateDeviceNumber" class="form-control" name="number" :value="toUpdate.number">
							</div>
						</form>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button class="btn btn-primary" @click="updateDevice(toUpdate.id)">保存修改</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal END -->
	</div>
	<!-- 如果你觉得这个script放在这不合理，我也是无可奈何，具体我也不说什么了，如果你是纯前端人员，你可能更加没办法下手 -->
	<script type="text/javascript">
		var vm = new Vue({
			el : '#devices',
			data : {
				selectCompany : -1,
				selectProject : -1,
				selectBuilding : -1,
				selectDevice : -1,
				companies : [],
				projects : [],
				buildings : [],
				devices : [],
	
				toUpdate : {},
			},
			methods : {
				queryDevice : function(id) {
					this.toUpdate = {}
					$.getJSON(
						'${pageContext.request.contextPath}/whdevice/' + this.selectCompany + '/' + this.selectProject + '/' + this.selectBuilding + '/' + id,
						function(data) {
							console.log(JSON.stringify(data))
							vm.toUpdate = data
						})
				},
				postDevice : function() {
	
					postData = this.form2Json('createDeviceForm')
	
					$.ajax({
						type : 'POST',
						url : '${pageContext.request.contextPath}/whdevice/' + this.selectCompany + '/' + this.selectProject + '/' + this.selectBuilding,
						data : JSON.stringify(postData),
						dataType : 'json',
						contentType : 'application/json; charset=utf-8',
						success : function(data) {
							console.log(data)
							alert('设备保存成功')
							vm.loadDevice()
						},
						error : function(data) {
							alert('保存失败')
							vm.loadDevice()
						}
					})
				},
				updateDevice : function(deviceId) {
	
					postData = this.form2Json('updateDeviceForm')
	
					$.ajax({
						type : 'PUT',
						url : '${pageContext.request.contextPath}/whdevice/' + this.selectCompany + '/' + this.selectProject + '/' + this.selectBuilding + '/' + deviceId,
						data : JSON.stringify(postData),
						dataType : 'json',
						contentType : 'application/json; charset=utf-8',
						success : function(data) {
							console.log(data)
							alert('设备修改成功')
							vm.loadDevice()
							vm.queryDevice(deviceId)
						},
						error : function(data) {
							alert('修改失败')
							vm.loadDevice()
						}
					})
				},
				deleteDevice : function(id) {
					isConfirm = confirm('确认删除本设备吗？id=' + id)
					if (!isConfirm) {
						return false;
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/whdevice/' + this.selectCompany + '/' + this.selectProject + '/' + this.selectBuilding + '/' + id,
						type : 'DELETE',
						dataType : 'json',
						success : function(data) {
							console.log(data)
							alert('删除成功')
							vm.loadDevice()
						},
						fail : function(data) {
							console.log(data)
							alert('删除失败')
							vm.loadDevice()
						}
					})
				},
				loadDevice : function() {
					$.getJSON(
						'${pageContext.request.contextPath}/whstrategy/' + this.selectCompany + '/' + this.selectProject + '/' + this.selectBuilding + '/devices',
						function(data) {
							console.log('Select BuildingId: ' + vm.selectBuilding)
							vm.devices = data
						})
				},
				form2Json : function(id) {
					formArray = $('#' + id).serializeArray();
					data = {}
					for (i in formArray) {
						code = 'data.' + formArray[i].name + '="' + formArray[i].value + '"'
						console.log(code)
						eval(code)
					}
					return data
				},
			},
			watch : {
				selectCompany : function() {
					this.selectProject = this.selectBuilding = -1
					$.getJSON(
						'${pageContext.request.contextPath}/whstrategy/' + this.selectCompany + '/projects',
						function(data) {
							console.log('Select CompanyId: ' + vm.selectCompany)
							vm.projects = data
						})
				},
				selectProject : function() {
					this.selectBuilding = -1
					$.getJSON(
						'${pageContext.request.contextPath}/whstrategy/' + this.selectCompany + '/' + this.selectProject + '/buildings',
						function(data) {
							console.log('Select ProjectId: ' + vm.selectProject)
							vm.buildings = data
						})
				},
				selectBuilding : function() {
					this.loadDevice()
				},
			},
		})
	
		$.getJSON('${pageContext.request.contextPath}/whstrategy/companies', function(data) {
			console.log('Load Companies')
			vm.companies = data
		})
	</script>
</body>
</html>
