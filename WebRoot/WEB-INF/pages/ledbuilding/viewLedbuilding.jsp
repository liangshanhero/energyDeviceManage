<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.ledbuilding-resources" />
<html>
<head>
<title>View<fmt:message key="ledbuilding.title"/></title>
</head>
<body>
	<div id="contentarea">
		<div id="content">
			<h1><fmt:message key="ledbuilding.title"/>Details</h1>
			<h1><fmt:message key="ledbuilding.title"/>Details</h1>
<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexLedbuilding?page=homePage"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
				<table cellpadding="0" cellspacing="0" id="viewTable">
					<tbody>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="ledbuilding.id.title"/>:
							</td>
							<td>
							${ledbuilding.id}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="ledbuilding.name.title"/>:
							</td>
							<td>${ledbuilding.name}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="ledbuilding.well.title"/>:
							</td>
							<td>${ledbuilding.well}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="ledbuilding.storey.title"/>:
							</td>
							<td>${ledbuilding.storey}&nbsp;</td>
						</tr>
				</tbody>
			</table>
			<h1><fmt:message key="project.title"/></h1>
			<c:if test='${ledbuilding.relativeProject != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="project.id.title"/>:
						</td>
						<td>
							${ledbuilding.relativeProject.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.name.title"/>:
						</td>
						<td>
							${ledbuilding.relativeProject.name}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.type.title"/>:
						</td>
						<td>
							${ledbuilding.relativeProject.type}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.status.title"/>:
						</td>
						<td>
							${ledbuilding.relativeProject.status}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.province.title"/>:
						</td>
						<td>
							${ledbuilding.relativeProject.province}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.city.title"/>:
						</td>
						<td>
							${ledbuilding.relativeProject.city}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.area.title"/>:
						</td>
						<td>
							${ledbuilding.relativeProject.area}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.address.title"/>:
						</td>
						<td>
							${ledbuilding.relativeProject.address}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.detail.title"/>:
						</td>
						<td>
							${ledbuilding.relativeProject.detail}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.remark.title"/>:
						</td>
						<td>
							${ledbuilding.relativeProject.remark}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>

			<h1><fmt:message key="ledmeters.title"/></h1>
			<form action="${pageContext.request.contextPath}/selectLedbuilding?id=${ledbuilding.id}&ledmeterPage=eachPageShow" method="post">
				选择每页显示<input type="text" id="ledmeterPageSize" name="ledmeterPageSize" style="height:15px;width:50px">条 
				<input type="submit" id="sure" value="确认" style="height:22px;width:35px">
			</form>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead"><fmt:message key="ledmeter.id.title"/></th>
						<th class="thead"><fmt:message key="ledmeter.number.title"/></th>
						<th class="thead"><fmt:message key="ledmeter.well.title"/></th>
						<th class="thead"><fmt:message key="ledmeter.storey.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ledmeters}" var="current"  varStatus="i">
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
						<td>
							${current.well}
						&nbsp;
						</td>
						<td>
							${current.storey}
						&nbsp;
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<tr>
				<td><a  href="${pageContext.request.contextPath}/selectLedbuilding?id=${ledbuilding.id}&ledmeterPage=homePage">首页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectLedbuilding?id=${ledbuilding.id}&ledmeterPage=previousPage">上一页</a></td>
				<c:if test="${ledmeterTotalPage>=7}">
				<c:if test="${ledmeterPageNumber-2>0&&ledmeterPageNumber+4<=ledmeterTotalPage}">
				<%String pageCount2 = request.getAttribute("ledmeterPageNumber").toString();
				int pageCount = Integer.parseInt(pageCount2);
				for(int i=pageCount-2;i<pageCount+5;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectLedbuilding?id=${ledbuilding.id}&ledmeterPage=jumpPage&ledmeterWantToPage=<%=i%>"><%=i%></a></td>
				<%}%>
				</c:if>
				<c:if test="${ledmeterPageNumber-2<=0}">
				<%for(int i=1;i<8;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectLedbuilding?id=${ledbuilding.id}&ledmeterPage=jumpPage&ledmeterWantToPage=<%=i%>"><%=i%></a></td>
				<%}%>
			    </c:if>
			    <c:if test="${ledmeterPageNumber+4>totalPage }">
			     <%String pageCount2 = request.getAttribute("ledmeterTotalPage").toString();
				int pageCount = Integer.parseInt(pageCount2);
			   	for(int i=pageCount-5;i<pageCount+2;i++){%>
			    <td><a  href="${pageContext.request.contextPath}/selectLedbuilding?id=${ledbuilding.id}&ledmeterPage=jumpPage&ledmeterWantToPage=<%=i%>"><%=i%></a></td>
			   <%}%>
			   	</c:if>
			    </c:if>				
				<c:if test="${ledmeterTotalPage<7}">
				<% 
						String pageCount2 = request.getAttribute("ledmeterTotalPage").toString();
						int pageCount = Integer.parseInt(pageCount2);
						for (int i = 1; i <= pageCount+1; i++) {
				%>
				 <td><a href="${pageContext.request.contextPath}/selectLedbuilding?id=${ledbuilding.id}&ledmeterPage=jumpPage&ledmeterWantToPage=<%=i%>"><%=i%></a></td>
				<%}%>
				</c:if>
				<td><a  href="${pageContext.request.contextPath}/selectLedbuilding?id=${ledbuilding.id}&ledmeterPage=nextPage">下一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectLedbuilding?id=${ledbuilding.id}&ledmeterPage=lastPage">尾页</a></td>
				</tr><br>
				当前为第${ledmeterPageNumber+1}页         每页显示${ledmeterPageSize}条      总共${ledmeterTotalPage+1}页<br>
				<form action="${pageContext.request.contextPath}/selectLedbuilding?id=${ledbuilding.id}&ledmeterPage=jumpPage" method="post">
					跳转到第<input type="text" id="ledmeterWantToPage" name="ledmeterWantToPage" style="height:15px;width:100px">页 
					<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
				</form>
			<div class="clear">&nbsp;</div>
		</div>
	</div>
</body>
</html>
