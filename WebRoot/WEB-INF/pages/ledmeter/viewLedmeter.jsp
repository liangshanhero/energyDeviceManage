<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.ledmeter-resources" />
<html>
<head>
<title>View<fmt:message key="ledmeter.title"/></title>
</head>
<body>
	<div id="contentarea">
		<div id="content">
			<h1><fmt:message key="ledmeter.title"/>Details</h1>
			<h1><fmt:message key="ledmeter.title"/>Details</h1>
<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexLedmeter?page=homePage"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
				<table cellpadding="0" cellspacing="0" id="viewTable">
					<tbody>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="ledmeter.id.title"/>:
							</td>
							<td>
							${ledmeter.id}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="ledmeter.number.title"/>:
							</td>
							<td>${ledmeter.number}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="ledmeter.well.title"/>:
							</td>
							<td>${ledmeter.well}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="ledmeter.storey.title"/>:
							</td>
							<td>${ledmeter.storey}&nbsp;</td>
						</tr>
				</tbody>
			</table>
			<h1><fmt:message key="ledbuilding.title"/></h1>
			<c:if test='${ledmeter.relativeLedbuilding != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="ledbuilding.id.title"/>:
						</td>
						<td>
							${ledmeter.relativeLedbuilding.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="ledbuilding.name.title"/>:
						</td>
						<td>
							${ledmeter.relativeLedbuilding.name}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="ledbuilding.well.title"/>:
						</td>
						<td>
							${ledmeter.relativeLedbuilding.well}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="ledbuilding.storey.title"/>:
						</td>
						<td>
							${ledmeter.relativeLedbuilding.storey}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>

			<h1><fmt:message key="ledmeterdatas.title"/></h1>
			<form action="${pageContext.request.contextPath}/selectLedmeter?id=${ledmeter.id}&ledmeterdataPage=eachPageShow" method="post">
				选择每页显示<input type="text" id="ledmeterdataPageSize" name="ledmeterdataPageSize" style="height:15px;width:50px">条 
				<input type="submit" id="sure" value="确认" style="height:22px;width:35px">
			</form>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead"><fmt:message key="ledmeterdata.id.title"/></th>
						<th class="thead"><fmt:message key="ledmeterdata.value.title"/></th>
						<th class="thead"><fmt:message key="ledmeterdata.time.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ledmeterdatas}" var="current"  varStatus="i">
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
							${current.value}
						&nbsp;
						</td>
						<td>
							${current.time}
						&nbsp;
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<tr>
				<td><a  href="${pageContext.request.contextPath}/selectLedmeter?id=${ledmeter.id}&ledmeterdataPage=homePage">首页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectLedmeter?id=${ledmeter.id}&ledmeterdataPage=previousPage">上一页</a></td>
				<c:if test="${ledmeterdataTotalPage>=7}">
				<c:if test="${ledmeterdataPageNumber-2>0&&ledmeterdataPageNumber+4<=ledmeterdataTotalPage}">
				<%String pageCount2 = request.getAttribute("ledmeterdataPageNumber").toString();
				int pageCount = Integer.parseInt(pageCount2);
				for(int i=pageCount-2;i<pageCount+5;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectLedmeter?id=${ledmeter.id}&ledmeterdataPage=jumpPage&ledmeterdataWantToPage=<%=i%>"><%=i%></a></td>
				<%}%>
				</c:if>
				<c:if test="${ledmeterdataPageNumber-2<=0}">
				<%for(int i=1;i<8;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectLedmeter?id=${ledmeter.id}&ledmeterdataPage=jumpPage&ledmeterdataWantToPage=<%=i%>"><%=i%></a></td>
				<%}%>
			    </c:if>
			    <c:if test="${ledmeterdataPageNumber+4>totalPage }">
			     <%String pageCount2 = request.getAttribute("ledmeterdataTotalPage").toString();
				int pageCount = Integer.parseInt(pageCount2);
			   	for(int i=pageCount-5;i<pageCount+2;i++){%>
			    <td><a  href="${pageContext.request.contextPath}/selectLedmeter?id=${ledmeter.id}&ledmeterdataPage=jumpPage&ledmeterdataWantToPage=<%=i%>"><%=i%></a></td>
			   <%}%>
			   	</c:if>
			    </c:if>				
				<c:if test="${ledmeterdataTotalPage<7}">
				<% 
						String pageCount2 = request.getAttribute("ledmeterdataTotalPage").toString();
						int pageCount = Integer.parseInt(pageCount2);
						for (int i = 1; i <= pageCount+1; i++) {
				%>
				 <td><a href="${pageContext.request.contextPath}/selectLedmeter?id=${ledmeter.id}&ledmeterdataPage=jumpPage&ledmeterdataWantToPage=<%=i%>"><%=i%></a></td>
				<%}%>
				</c:if>
				<td><a  href="${pageContext.request.contextPath}/selectLedmeter?id=${ledmeter.id}&ledmeterdataPage=nextPage">下一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectLedmeter?id=${ledmeter.id}&ledmeterdataPage=lastPage">尾页</a></td>
				</tr><br>
				当前为第${ledmeterdataPageNumber+1}页         每页显示${ledmeterdataPageSize}条      总共${ledmeterdataTotalPage+1}页<br>
				<form action="${pageContext.request.contextPath}/selectLedmeter?id=${ledmeter.id}&ledmeterdataPage=jumpPage" method="post">
					跳转到第<input type="text" id="ledmeterdataWantToPage" name="ledmeterdataWantToPage" style="height:15px;width:100px">页 
					<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
				</form>
			<div class="clear">&nbsp;</div>
		</div>
	</div>
</body>
</html>
