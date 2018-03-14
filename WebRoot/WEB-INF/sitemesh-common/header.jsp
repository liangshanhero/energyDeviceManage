<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.application-resources" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js">
</script>
<script type="text/javascript">
	$(document).ready(
		function() {
			$(".div2").click(
				/* 打开当前选定项目的子菜单，关闭其他子菜单 */
				function() {
					$(this).next("div").slideToggle(300).siblings(
						".div3:visible").slideUp("slow");
				});
		});
	function openurl(url) {
		var rframe = parent.document.getElementById("rightFrame");
		rframe.src = url;
	}
</script>
<div id="header">
	<!-- 项目顶部 -->
	<div class="top1"></div>
	<div class="top2"><div class="title">能源管理系统<div style="clear:both;"></div></div>
		<div class="fr top-link"><a target="mainCont" title="管理人员"><div class="adminIcon"></div> <span>管理员：${sessionScope.staff.name }</span></a></div>
	</div>
	<!-- 菜单 -->
	<div class="left">
	<!-- 用户管理菜单 -->
		<div class="div1"><div class="div2"><div class="spgl"></div>用户管理</div><div class="div3">
				<ul>
					<li><a class="a1" href="${pageContext.request.contextPath}/listAllStaffs">查看用户</a></li>
					<li><a class="a1" href="${pageContext.request.contextPath}/newStaff">添加用户</a></li>
				</ul>
			</div>
			<div class="div2">
				<div class="spgl"></div>
				公司管理
			</div>
			<div class="div3">
				<ul>
					<li><a class="a1"
						href="${pageContext.request.contextPath}/listAllCompanys">查看公司</a></li>
					
					<li><a class="a1"
						href="${pageContext.request.contextPath}/newCompany">添加公司</a></li>
					
					
				</ul>
			</div>


			<div class="div2">
				<div class="spgl"></div>
				项目管理
			</div>

			<div class="div3">
				<ul>
					<li><a class="a1"
						href="${pageContext.request.contextPath}/indexProject">查看项目</a></li>
					<li><a class="a1"
						href="${pageContext.request.contextPath}/newProject">添加项目</a></li>
					
				</ul>
			</div>

			<div class="div2">
				<div class="spgl"></div>
				设备管理
			</div>

			<div class="div3">
				<ul>
					<li><a class="a1"
						href="${pageContext.request.contextPath}/whdevice">设备</a></li>
				</ul>
			</div>

			<div class="div3">
				<ul>
					<li><a class="a"
						href="${pageContext.request.contextPath}/filehandler.jsp">数据上传</a></li>
				</ul>
			</div>

			

			<div class="div2">
				<div class="spgl"></div>
				设备策略
			</div>
			<div class="div3">
				<ul>
					<li><a class="a"
						href="${pageContext.request.contextPath}/whstrategy">策略</a></li>
				</ul>
			</div>
			
			<div class="div2">
				<div class="spgl"></div>
				数据展示
			</div>
			<div class="div3">
				<ul>
					<li><a class="a"
						href="${pageContext.request.contextPath}/showdataRequestCompanys">公司</a></li>
				</ul>
			</div>

			<a class="a1" href="login.html"><div class="div2">
					<div class="spgl"></div>
					退出后台
				</div> </a>

		</div>
	</div>
</div>
<div id="login"><jsp:include
		page="/WEB-INF/sitemesh-common/login.jsp" />
</div>