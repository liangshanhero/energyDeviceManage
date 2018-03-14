function createPieChart(datalist) {
	var myChart = echarts.init(document.getElementById('pie'));
	var option = {
		visualMap : {
			show : false,
			min : 80,
			max : 550,
			inRange : {
				colorLightness : [ 0, 1 ]
			}
		},
		series : [ {
			name : 'pancake',
			type : 'pie',
			radius : '55%',
			data : datalist,
			roseType: 'angle' //显示成南丁格尔图
		} ]
	}
	myChart.setOption(option);
};

function createBarChart(data){
	var barChart = echarts.init(document.getElementById('bar'));
	var option = {
			xAxis:{
				data:data.map(function (item){
					return item.xAxis;
				})
			},
			yAxis:{
				splitLine:{show:false}
			},
			series:[{
				label:{
					normal:{
						show:true
					}
				},
				name:'column',
				type:'bar',
				silent:true,
				barWidth:40,
				data:data.map(function(item){
					return item.yAxis;
				})
			}]
	}
	barChart.setOption(option);
};

function createLineChart(data,deviceId){
	var valueList = data.map(function(item){
		return item.value;
	});
	var statusList = data.map(function(item){
		return item.sensorStatus;
	});
	var dateList = data.map(function(item){
		return item.time;
	});
	var lineChart = echarts.init(document.getElementById('line'));
	var option={
			title:{
				text:deviceId
			},
			tooltip:{
				trigger:'axis'
			},
			legend:{
				data:['value']
			},
			toolbox:{
				show:true,
				feature:{saveAsImage:{show:true}}
			},
			dataZoom:[{
				startValue:dateList[0]
			},{
				type:'inside'
			}],
			xAxis:{
				type:'category',
				boundaryGap:false,
				data:dateList
			},
			yAxis:{
				type:'value',
				splitLine: {
		             show: false
		        }
			},
			series:[{
				name:'value',
				type:'line',
				smooth:true,
				data:valueList
			}
			]
	};
	lineChart.setOption(option);
};
