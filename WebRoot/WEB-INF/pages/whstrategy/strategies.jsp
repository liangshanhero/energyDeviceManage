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

<body>
	<div class="container" id="strategies" style="margin: 50px 0px 0px 200px; padding: 0px 0px 0px 20px">
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
				<label for="selectDevice">设备</label>
				<select id="selectDevice" v-model="selectDevice">
					<option disabled value="">选择设备</option>
					<option v-for="device in devices" :value="device.id">{{device.number}}</option>
				</select>
			</div>
			<hr>
			<div v-if="current.device != undefined">
				<div>
					<label for="deviceNumber">设备</label>
					<span id="deviceNumber">{{current.device.number}}</span>
				</div>
				<div>
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#newStrategy">新建策略</button>
				</div>
			</div>
			<div>
				<div v-for="strategy in current.strategies">
					<hr>
					<label>策略类型</label>
					<span>{{strategy.name}}</span> <br>
					<label>创建日期</label>
					<span>{{humanDate(strategy.createDate)}}</span> <br>
					<label>是否生效</label>
					<span v-if="strategy.enable" style="color: green;">是</span> <span v-else style="color: red;">否</span>
					<table class="table table-hover">
						<thead>
							<th>策略属性</th>
							<th>下限</th>
							<th>上限</th>
							<th>更新时间</th>
						</thead>
						<tbody>
							<tr v-for="detail in strategy.strategyDetails">
								<td>{{detail.type}}</td>
								<td v-if="detail.type == '时间'">{{Math.floor(detail.min / 60)}}:{{detail.min % 60}}</td>
								<td v-else>{{detail.min}}</td>
								<td v-if="detail.type == '时间'">{{Math.floor(detail.max / 60)}}:{{detail.max % 60}}</td>
								<td v-else>{{detail.max}}</td>
								<td>{{new Date(detail.time.time).toLocaleString()}}</td>
							</tr>
						</tbody>
						<button class="btn btn-primary" @click="queryStrategy(strategy.id)" data-toggle="modal" data-target="#updateStrategy">修改策略</button>
						<button class="btn btn-primary" @click="deleteStrategy(strategy.id)">删除策略</button>
					</table>
				</div>
			</div>
		</div>

		<!-- Modal Create -->
		<div class="modal fade" id="newStrategy" tabindex="-1" role="dialog" aria-labelledby="createModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="createModalLabel">新建策略</h4>
					</div>
					<div class="modal-body">

						<label for="strategyType">策略类型</label>
						<select id="strategyType" v-model="selectType">
							<option v-for="(type, index) in strategyTypes" :value="index">{{type.name}}</option>
						</select>
						<div>
							<label>是否生效</label>
							&nbsp;
							<input type="radio" name="postEnable" value="true">
							是 &nbsp;&nbsp;
							<input type="radio" name="postEnable" value="false">
							否
						</div>
						<form id="createStrategyForm">
							<input id="name" name="name" type="hidden" :value="strategyTypes[selectType].name">
							<div v-for="strategy in strategyTypes" v-if="selectType == strategyTypes.indexOf(strategy)">
								<div class="form-group" v-for="(param, index) in strategy.params">
									<div v-if="param == '时间'">
										<label>{{param}}</label>
										<br>
										<input :name="'details[' + index + '].type'" :value="param" type="hidden">
										<label>起始时间</label>
										<input :name="'details[' + index + '].min'" v-model="startTime" type="hidden">
										<input v-model="startHour" maxlength="2">
										:
										<input v-model="startMinute" maxlength="2">
										<br>
										<label>结束时间</label>
										<input :name="'details[' + index + '].max'" v-model="endTime" type="hidden">
										<input v-model="endHour" maxlength="2">
										:
										<input v-model="endMinute" maxlength="2">
									</div>
									<div v-else>
										<label>{{param}}</label>
										<br>
										<input :name="'details[' + index + '].type'" :value="param" type="hidden">
										<label>下限</label>
										<input :name="'details[' + index + '].min'">
										<label>上限</label>
										<input :name="'details[' + index + '].max'">
									</div>
								</div>
							</div>


						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button class="btn btn-primary" @click="postStrategy()">保存</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal END -->

		<!-- Modal Update-->
		<div class="modal fade" id="updateStrategy" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">修改策略</h4>
					</div>
					<div class="modal-body">
						<div>
							<label>是否生效</label>
							<input type="radio" name="updateEnable" value="true">
							是
							<input type="radio" name="updateEnable" value="false">
							否
						</div>
						<form id="updateStrategyForm">
							<div v-for="(detail, index) in toUpdate.strategyDetails">

								<div v-if="detail && detail.type == '时间'">
									<input type="hidden" :name="'details[' + index + '].id'" :value="detail.id">
									<label>{{detail.type}}</label>
									<br>
									<input :name="'details[' + index + '].type'" :value="detail.type" type="hidden">
									<label>起始时间</label>
									<input :name="'details[' + index + '].min'" :value="detail.min" type="hidden">
									<input v-model="startHour" maxlength="2">
									:
									<input v-model="startMinute" maxlength="2">
									<br>
									<label>结束时间</label>
									<input :name="'details[' + index + '].max'" :value="detail.max" type="hidden">
									<input v-model="endHour" maxlength="2">
									:
									<input v-model="endMinute" maxlength="2">
								</div>

								<div v-else>
									<input type="hidden" :name="'details[' + index + '].id'" :value="detail.id">
									<label>{{detail.type}}</label>
									<br>
									<input :name="'details[' + index + '].type'" :value="detail.type" type="hidden">
									<label>下限</label>
									<input :name="'details[' + index + '].min'" :value="detail.min">
									<label>上限</label>
									<input :name="'details[' + index + '].max'" :value="detail.max">
								</div>
							</div>
						</form>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button class="btn btn-primary" @click="updateStrategy(toUpdate.id)">保存修改</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal END -->
	</div>
	<!-- 如果你觉得这个script放在这不合理，我也是无可奈何，具体我也不说什么了，如果你是纯前端人员，你可能更加没办法下手 -->
	<script type="text/javascript">
		DETAILS = {
			TIME : '时间',
			TEMPERATURE : '温度',
			WATER_LEVEL : '水位',
			TEMPERATURE_DIFF : '温差',
		}
		var vm = new Vue({
			el : '#strategies',
			data : {
				selectCompany : -1,
				selectProject : -1,
				selectBuilding : -1,
				selectDevice : -1,
				companies : [],
				projects : [],
				buildings : [],
				devices : [],
				current : {},
	
				toUpdate : {},
	
				DETAILS : DETAILS,
				strategyTypes : [
					{
						name : '热泵策略',
						params : [
							DETAILS.TIME,
							DETAILS.TEMPERATURE,
						]
					},
					{
						name : '太阳能加热策略',
						params : [
							DETAILS.TEMPERATURE
						]
					},
					{
						name : '混水策略',
						params : [
							DETAILS.TEMPERATURE,
							DETAILS.TEMPERATURE_DIFF,
						]
					},
					{
						name : '内循环策略',
						params : [
							DETAILS.TEMPERATURE,
							DETAILS.TEMPERATURE_DIFF,
						]
					},
					{
						name : '补水策略',
						params : [
							DETAILS.TIME,
							DETAILS.TEMPERATURE,
							DETAILS.WATER_LEVEL,
						]
					},
					{
						name : '供水策略',
						params : [
							DETAILS.TIME,
						]
					},
					{
						name : '回水策略',
						params : [
							DETAILS.TIME,
							DETAILS.TEMPERATURE,
						]
					},
					{
						name : '缺水保护',
						params : [
							DETAILS.WATER_LEVEL,
						]
					},
				],
	
				selectType : 0,
				startTime : 0,
				endTime : 0,
				startHour : 0,
				startMinute : 0,
				endHour : 0,
				endMinute : 0,
			},
			computed : {
			},
			methods : {
				humanDate : function(date) {
					d = new Date(date.time)
					return d.toLocaleString()
				},
				loadStrategy : function() {
					$.getJSON(
						'${pageContext.request.contextPath}/whstrategy/' + this.selectCompany + '/' + this.selectProject + '/' + this.selectBuilding + '/' + this.selectDevice,
						function(data) {
							console.log('Select DeviceId: ' + vm.selectDevice)
							vm.current = data
						})
				},
				queryStrategy : function(id) {
					this.toUpdate = {}
					$.getJSON(
						'${pageContext.request.contextPath}/whstrategy/' + this.selectCompany + '/' + this.selectProject + '/' + this.selectBuilding + '/' + this.selectDevice + '/' + id,
						function(data) {
							console.log(JSON.stringify(data))
							vm.toUpdate = data
						})
				},
				postStrategy : function() {
	
					postData = this.form2FormatJson('createStrategyForm')
					postData.enable = eval($("input[name='postEnable']:checked").val())
					postData.name = document.getElementById('name').value;
					console.log('POST' + JSON.stringify(postData))
	
					$.ajax({
						type : 'POST',
						url : '${pageContext.request.contextPath}/whstrategy/' + this.selectCompany + '/' + this.selectProject + '/' + this.selectBuilding + '/' + this.selectDevice,
						data : JSON.stringify(postData),
						dataType : 'json',
						contentType : 'application/json; charset=utf-8',
						success : function(data) {
							console.log(data)
							alert('策略保存成功')
							vm.loadStrategy()
						},
						error : function(data) {
							alert('保存失败')
							vm.loadStrategy()
						}
					})
				},
				updateStrategy : function(strategyId) {
	
					postData = this.form2FormatJson('updateStrategyForm')
					postData.enable = eval($("input[name='updateEnable']:checked").val())
					$.ajax({
						type : 'PUT',
						url : '${pageContext.request.contextPath}/whstrategy/' + this.selectCompany + '/' + this.selectProject + '/' + this.selectBuilding + '/' + this.selectDevice + '/' + strategyId,
						data : JSON.stringify(postData),
						dataType : 'json',
						contentType : 'application/json; charset=utf-8',
						success : function(data) {
							console.log(data)
							alert('策略修改成功')
							vm.loadStrategy()
						},
						error : function(data) {
							alert('修改失败')
							vm.loadStrategy()
						}
					})
				},
				form2FormatJson : function(id) {
					formArray = $('#' + id).serializeArray()
					dataJson = {
						details : []
					}
					details = []
					for (i in formArray) {
						if (eval(formArray[i].name.split('.')[0] + ' == undefined')) {
							eval(formArray[i].name.split('.')[0] + ' = {}')
						}
						code = formArray[i].name + '="' + formArray[i].value + '"'
						console.log('eval: ' + code)
						eval(code)
					}
					console.log(details)
					dataJson.details = details
					return dataJson
				},
				deleteStrategy : function(id) {
					isConfirm = confirm('确认删除本策略吗？id=' + id)
					if (!isConfirm) {
						return false;
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/whstrategy/' + this.selectCompany + '/' + this.selectProject + '/' + this.selectBuilding + '/' + this.selectDevice + '/' + id,
						type : 'DELETE',
						dataType : 'json',
						success : function(data) {
							console.log(data)
							alert('删除成功')
							vm.loadStrategy()
						},
						fail : function(data) {
							console.log(data)
							alert('删除失败')
							vm.loadStrategy()
						}
					})
				},
				timeToMinute : function() {
					this.startTime = parseInt(this.startHour) * 60 + parseInt(this.startMinute)
					this.endTime = parseInt(this.endHour) * 60 + parseInt(this.endMinute)
				},
				minuteToTime : function() {
					this.startMinute = this.startTime % 60
					this.startHour = Math.floor(this.startTime / 60)
					this.endMinute = this.endTime % 60
					this.endHour = Math.floor(this.endTime / 60)
				},
			},
			watch : {
				selectCompany : function() {
					this.selectProject = this.selectBuilding = this.selectDevice = -1
					this.current = {}
					$.getJSON(
						'${pageContext.request.contextPath}/whstrategy/' + this.selectCompany + '/projects',
						function(data) {
							console.log('Select CompanyId: ' + vm.selectCompany)
							vm.projects = data
						})
				},
				selectProject : function() {
					this.selectBuilding = this.selectDevice = -1
					this.current = {}
					$.getJSON(
						'${pageContext.request.contextPath}/whstrategy/' + this.selectCompany + '/' + this.selectProject + '/buildings',
						function(data) {
							console.log('Select ProjectId: ' + vm.selectProject)
							vm.buildings = data
						})
				},
				selectBuilding : function() {
					this.selectDevice = -1
					this.current = {}
					$.getJSON(
						'${pageContext.request.contextPath}/whstrategy/' + this.selectCompany + '/' + this.selectProject + '/' + this.selectBuilding + '/devices',
						function(data) {
							console.log('Select BuildingId: ' + vm.selectBuilding)
							vm.devices = data
						})
				},
				selectDevice : function() {
					this.loadStrategy()
				},
				startHour : function() {
					this.timeToMinute()
				},
				startMinute : function() {
					this.timeToMinute()
				},
				endHour : function() {
					this.timeToMinute()
				},
				endMinute : function() {
					this.timeToMinute()
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
