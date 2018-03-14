<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.whdatatype-resources" />
<html>
<head>
<title>View<fmt:message key="whdatatype.title"/></title>
</head>
<body>
	<div id="contentarea">
		<div id="content">
			<h1><fmt:message key="whdatatype.title"/>Details</h1>
			<h1><fmt:message key="whdatatype.title"/>Details</h1>
<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexWhdatatype?page=homePage"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
				<table cellpadding="0" cellspacing="0" id="viewTable">
					<tbody>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="whdatatype.id.title"/>:
							</td>
							<td>
							${whdatatype.id}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="whdatatype.name.title"/>:
							</td>
							<td>${whdatatype.name}&nbsp;</td>
						</tr>
				</tbody>
			</table>

<h1><fmt:message key="whdevices.title"/></h1>
			<form action="${pageContext.request.contextPath}/selectWhdatatype?id=${whdatatype.id}&whdevicePage=eachPageShow&whdevicedataPage=${whdevicedataPage}&whdevicedataPageSize=${whdevicedataPageSize}&whdevicedataWantToPage=${whdevicedataWantToPage}" method="post">
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
					<c:forEach items="${whdatatype.relativeWhdevices}" var="current"  varStatus="i">	
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
				<td><a  href="${pageContext.request.contextPath}/selectWhdatatype?id=${whdatatype.id}&whdevicePage=homePage&whdevicedataPage=${whdevicedataPage}&whdevicedataPageSize=${whdevicedataPageSize}&whdevicedataWantToPage=${whdevicedataWantToPage}">首页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectWhdatatype?id=${whdatatype.id}&whdevicePage=previousPage&whdevicedataPage=${whdevicedataPage}&whdevicedataPageSize=${whdevicedataPageSize}&whdevicedataWantToPage=${whdevicedataWantToPage}">上一页</a></td>
				<c:if test="${whdeviceTotalPage>=7}">
				<c:if test="${whdevicePageNumber-2>0&&whdevicePageNumber+4<=whdeviceTotalPage}">
				<%String pageCount2 = request.getAttribute("whdevicePageNumber").toString();
				int pageCount = Integer.parseInt(pageCount2);
				for(int i=pageCount-2;i<pageCount+5;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectWhdatatype?id=${whdatatype.id}&whdevicePage=jumpPage&whdeviceWantToPage=<%=i%>&whdevicedataPage=${whdevicedataPage}&whdevicedataPageSize=${whdevicedataPageSize}&whdevicedataWantToPage=${whdevicedataWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<c:if test="${whdevicePageNumber-2<=0}">
				<%for(int i=1;i<8;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectWhdatatype?id=${whdatatype.id}&whdevicePage=jumpPage&whdeviceWantToPage=<%=i%>&whdevicedataPage=${whdevicedataPage}&whdevicedataPageSize=${whdevicedataPageSize}&whdevicedataWantToPage=${whdevicedataWantToPage}"><%=i%></a></td>
				<%}%>
			    </c:if>
			    <c:if test="${whdevicePageNumber+4>totalPage }">
			     <%String pageCount2 = request.getAttribute("whdeviceTotalPage").toString();
				int pageCount = Integer.parseInt(pageCount2);
			   	for(int i=pageCount-5;i<pageCount+2;i++){%>
			    <td><a  href="${pageContext.request.contextPath}/selectWhdatatype?id=${whdatatype.id}&whdevicePage=jumpPage&whdeviceWantToPage=<%=i%>&whdevicedataPage=${whdevicedataPage}&whdevicedataPageSize=${whdevicedataPageSize}&whdevicedataWantToPage=${whdevicedataWantToPage}"><%=i%></a></td>
			   <%}%>
			   	</c:if>
			    </c:if>				
				<c:if test="${whdeviceTotalPage<7}">
				<% 
						String pageCount2 = request.getAttribute("whdeviceTotalPage").toString();
						int pageCount = Integer.parseInt(pageCount2);
						for (int i = 1; i <= pageCount+1; i++) {
				%>
				 <td><a href="${pageContext.request.contextPath}/selectWhdatatype?id=${whdatatype.id}&whdevicePage=jumpPage&whdeviceWantToPage=<%=i%>&whdevicedataPage=${whdevicedataPage}&whdevicedataPageSize=${whdevicedataPageSize}&whdevicedataWantToPage=${whdevicedataWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<td><a  href="${pageContext.request.contextPath}/selectWhdatatype?id=${whdatatype.id}&whdevicePage=nextPage&whdevicedataPage=${whdevicedataPage}&whdevicedataPageSize=${whdevicedataPageSize}&whdevicedataWantToPage=${whdevicedataWantToPage}">下一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectWhdatatype?id=${whdatatype.id}&whdevicePage=lastPage&whdevicedataPage=${whdevicedataPage}&whdevicedataPageSize=${whdevicedataPageSize}&whdevicedataWantToPage=${whdevicedataWantToPage}">尾页</a></td>
				</tr><br>
				当前为第${whdevicePageNumber+1}页         每页显示${whdevicePageSize}条      总共${whdeviceTotalPage+1}页<br>
				<form action="${pageContext.request.contextPath}/selectWhdatatype?id=${whdatatype.id}&whdevicePage=jumpPage&whdevicedataPage=${whdevicedataPage}&whdevicedataPageSize=${whdevicedataPageSize}&whdevicedataWantToPage=${whdevicedataWantToPage}" method="post">
					跳转到第<input type="text" id="whdeviceWantToPage" name="whdeviceWantToPage" style="height:15px;width:100px">页 
					<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
				</form>
			<h1><fmt:message key="whdevicedatas.title"/></h1>
			<form action="${pageContext.request.contextPath}/selectWhdatatype?id=${whdatatype.id}&whdevicedataPage=eachPageShow&whdevicePage=${whdevicePage}&whdevicePageSize=${whdevicePageSize}&whdeviceWantToPage=${whdeviceWantToPage}" method="post">
				选择每页显示<input type="text" id="whdevicedataPageSize" name="whdevicedataPageSize" style="height:15px;width:50px">条 
				<input type="submit" id="sure" value="确认" style="height:22px;width:35px">
			</form>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead"><fmt:message key="whdevicedata.id.title"/></th>
						<th class="thead"><fmt:message key="whdevicedata.time.title"/></th>
						<th class="thead"><fmt:message key="whdevicedata.value.title"/></th>
						<th class="thead"><fmt:message key="whdevicedata.isupdate.title"/></th>
						<th class="thead"><fmt:message key="whdevicedata.isio.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${whdevicedatas}" var="current"  varStatus="i">
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
							${current.time}
						&nbsp;
						</td>
						<td>
							${current.value}
						&nbsp;
						</td>
						<td>
							${current.isupdate}
						&nbsp;
						</td>
						<td>
							${current.isio}
						&nbsp;
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<tr>
				<td><a  href="${pageContext.request.contextPath}/selectWhdatatype?id=${whdatatype.id}&whdevicedataPage=homePage&whdevicePage=${whdevicePage}&whdevicePageSize=${whdevicePageSize}&whdeviceWantToPage=${whdeviceWantToPage}">首页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectWhdatatype?id=${whdatatype.id}&whdevicedataPage=previousPage&whdevicePage=${whdevicePage}&whdevicePageSize=${whdevicePageSize}&whdeviceWantToPage=${whdeviceWantToPage}">上一页</a></td>
				<c:if test="${whdevicedataTotalPage>=7}">
				<c:if test="${whdevicedataPageNumber-2>0&&whdevicedataPageNumber+4<=whdevicedataTotalPage}">
				<%String pageCount2 = request.getAttribute("whdevicedataPageNumber").toString();
				int pageCount = Integer.parseInt(pageCount2);
				for(int i=pageCount-2;i<pageCount+5;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectWhdatatype?id=${whdatatype.id}&whdevicedataPage=jumpPage&whdevicedataWantToPage=<%=i%>&whdevicePage=${whdevicePage}&whdevicePageSize=${whdevicePageSize}&whdeviceWantToPage=${whdeviceWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<c:if test="${whdevicedataPageNumber-2<=0}">
				<%for(int i=1;i<8;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectWhdatatype?id=${whdatatype.id}&whdevicedataPage=jumpPage&whdevicedataWantToPage=<%=i%>&whdevicePage=${whdevicePage}&whdevicePageSize=${whdevicePageSize}&whdeviceWantToPage=${whdeviceWantToPage}"><%=i%></a></td>
				<%}%>
			    </c:if>
			    <c:if test="${whdevicedataPageNumber+4>totalPage }">
			     <%String pageCount2 = request.getAttribute("whdevicedataTotalPage").toString();
				int pageCount = Integer.parseInt(pageCount2);
			   	for(int i=pageCount-5;i<pageCount+2;i++){%>
			    <td><a  href="${pageContext.request.contextPath}/selectWhdatatype?id=${whdatatype.id}&whdevicedataPage=jumpPage&whdevicedataWantToPage=<%=i%>&whdevicePage=${whdevicePage}&whdevicePageSize=${whdevicePageSize}&whdeviceWantToPage=${whdeviceWantToPage}"><%=i%></a></td>
			   <%}%>
			   	</c:if>
			    </c:if>				
				<c:if test="${whdevicedataTotalPage<7}">
				<% 
						String pageCount2 = request.getAttribute("whdevicedataTotalPage").toString();
						int pageCount = Integer.parseInt(pageCount2);
						for (int i = 1; i <= pageCount+1; i++) {
				%>
				 <td><a href="${pageContext.request.contextPath}/selectWhdatatype?id=${whdatatype.id}&whdevicedataPage=jumpPage&whdevicedataWantToPage=<%=i%>&whdevicePage=${whdevicePage}&whdevicePageSize=${whdevicePageSize}&whdeviceWantToPage=${whdeviceWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<td><a  href="${pageContext.request.contextPath}/selectWhdatatype?id=${whdatatype.id}&whdevicedataPage=nextPage&whdatatypePage=${whdatatypePage}&whdatatypePageSize=${whdatatypePageSize}&whdatatypeWantToPage=${whdatatypeWantToPage}&whdevicePage=${whdevicePage}&whdevicePageSize=${whdevicePageSize}&whdeviceWantToPage=${whdeviceWantToPage}">下一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectWhdatatype?id=${whdatatype.id}&whdevicedataPage=lastPage&whdatatypePage=${whdatatypePage}&whdatatypePageSize=${whdatatypePageSize}&whdatatypeWantToPage=${whdatatypeWantToPage}&whdevicePage=${whdevicePage}&whdevicePageSize=${whdevicePageSize}&whdeviceWantToPage=${whdeviceWantToPage}">尾页</a></td>
				</tr><br>
				当前为第${whdevicedataPageNumber+1}页         每页显示${whdevicedataPageSize}条      总共${whdevicedataTotalPage+1}页<br>
				<form action="${pageContext.request.contextPath}/selectWhdatatype?id=${whdatatype.id}&whdevicedataPage=jumpPage&whdatatypePage=${whdatatypePage}&whdatatypePageSize=${whdatatypePageSize}&whdatatypeWantToPage=${whdatatypeWantToPage}&whdevicePage=${whdevicePage}&whdevicePageSize=${whdevicePageSize}&whdeviceWantToPage=${whdeviceWantToPage}" method="post">
					跳转到第<input type="text" id="whdevicedataWantToPage" name="whdevicedataWantToPage" style="height:15px;width:100px">页 
					<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
				</form>
			<div class="clear">&nbsp;</div>
		</div>
	</div>
</body>
</html>
