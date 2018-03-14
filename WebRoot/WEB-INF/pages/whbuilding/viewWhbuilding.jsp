<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.whbuilding-resources" />
<html>
<head>
<title>View<fmt:message key="whbuilding.title"/></title>
</head>
<body>
	<div id="contentarea">
		<div id="content">
			<h1><fmt:message key="whbuilding.title"/>Details</h1>
			<h1><fmt:message key="whbuilding.title"/>Details</h1>
<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexWhbuilding?page=homePage"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
				<table cellpadding="0" cellspacing="0" id="viewTable">
					<tbody>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="whbuilding.id.title"/>:
							</td>
							<td>
							${whbuilding.id}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="whbuilding.name.title"/>:
							</td>
							<td>${whbuilding.name}&nbsp;</td>
						</tr>
				</tbody>
			</table>
			<h1><fmt:message key="project.title"/></h1>
			<c:if test='${whbuilding.relativeProject != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="project.id.title"/>:
						</td>
						<td>
							${whbuilding.relativeProject.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.name.title"/>:
						</td>
						<td>
							${whbuilding.relativeProject.name}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.type.title"/>:
						</td>
						<td>
							${whbuilding.relativeProject.type}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.status.title"/>:
						</td>
						<td>
							${whbuilding.relativeProject.status}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.province.title"/>:
						</td>
						<td>
							${whbuilding.relativeProject.province}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.city.title"/>:
						</td>
						<td>
							${whbuilding.relativeProject.city}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.area.title"/>:
						</td>
						<td>
							${whbuilding.relativeProject.area}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.address.title"/>:
						</td>
						<td>
							${whbuilding.relativeProject.address}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.detail.title"/>:
						</td>
						<td>
							${whbuilding.relativeProject.detail}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.remark.title"/>:
						</td>
						<td>
							${whbuilding.relativeProject.remark}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>

			<h1><fmt:message key="whdevices.title"/></h1>
			<form action="${pageContext.request.contextPath}/selectWhbuilding?id=${whbuilding.id}&whdevicePage=eachPageShow" method="post">
				选择每页显示<input type="text" id="whdevicePageSize" name="whdevicePageSize" style="height:15px;width:50px">条 
				<input type="submit" id="sure" value="确认" style="height:22px;width:35px">
			</form>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead"><fmt:message key="whdevice.id.title"/></th>
						<th class="thead"><fmt:message key="whdevice.number.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${whdevices}" var="current"  varStatus="i">
						<c:choose>
							<c:when test="${(i.count) % 2 == 0}">
					    		<c:set var="rowclass" value="rowtwo"/>
							</c:when>
							<c:otherwise>
					    		<c:set var="rowclass" value="rowone"/>
							</c:otherwise>
						</c:choose>
					<tr class="${rowclass}">
						<td>
							${current.id}
						&nbsp;
						</td>
						<td>
							${current.number}
						&nbsp;
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<tr>
				<td><a  href="${pageContext.request.contextPath}/selectWhbuilding?id=${whbuilding.id}&whdevicePage=homePage">首页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectWhbuilding?id=${whbuilding.id}&whdevicePage=previousPage">上一页</a></td>
				<c:if test="${whdeviceTotalPage>=7}">
				<c:if test="${whdevicePageNumber-2>0&&whdevicePageNumber+4<=whdeviceTotalPage}">
				<%String pageCount2 = request.getAttribute("whdevicePageNumber").toString();
				int pageCount = Integer.parseInt(pageCount2);
				for(int i=pageCount-2;i<pageCount+5;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectWhbuilding?id=${whbuilding.id}&whdevicePage=jumpPage&whdeviceWantToPage=<%=i%>"><%=i%></a></td>
				<%}%>
				</c:if>
				<c:if test="${whdevicePageNumber-2<=0}">
				<%for(int i=1;i<8;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectWhbuilding?id=${whbuilding.id}&whdevicePage=jumpPage&whdeviceWantToPage=<%=i%>"><%=i%></a></td>
				<%}%>
			    </c:if>
			    <c:if test="${whdevicePageNumber+4>totalPage }">
			     <%String pageCount2 = request.getAttribute("whdeviceTotalPage").toString();
				int pageCount = Integer.parseInt(pageCount2);
			   	for(int i=pageCount-5;i<pageCount+2;i++){%>
			    <td><a  href="${pageContext.request.contextPath}/selectWhbuilding?id=${whbuilding.id}&whdevicePage=jumpPage&whdeviceWantToPage=<%=i%>"><%=i%></a></td>
			   <%}%>
			   	</c:if>
			    </c:if>				
				<c:if test="${whdeviceTotalPage<7}">
				<% 
						String pageCount2 = request.getAttribute("whdeviceTotalPage").toString();
						int pageCount = Integer.parseInt(pageCount2);
						for (int i = 1; i <= pageCount+1; i++) {
				%>
				 <td><a href="${pageContext.request.contextPath}/selectWhbuilding?id=${whbuilding.id}&whdevicePage=jumpPage&whdeviceWantToPage=<%=i%>"><%=i%></a></td>
				<%}%>
				</c:if>
				<td><a  href="${pageContext.request.contextPath}/selectWhbuilding?id=${whbuilding.id}&whdevicePage=nextPage">下一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectWhbuilding?id=${whbuilding.id}&whdevicePage=lastPage">尾页</a></td>
				</tr><br>
				当前为第${whdevicePageNumber+1}页         每页显示${whdevicePageSize}条      总共${whdeviceTotalPage+1}页<br>
				<form action="${pageContext.request.contextPath}/selectWhbuilding?id=${whbuilding.id}&whdevicePage=jumpPage" method="post">
					跳转到第<input type="text" id="whdeviceWantToPage" name="whdeviceWantToPage" style="height:15px;width:100px">页 
					<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
				</form>
			<div class="clear">&nbsp;</div>
		</div>
	</div>
</body>
</html>
