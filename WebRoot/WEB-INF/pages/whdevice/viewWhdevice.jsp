<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.whdevice-resources" />
<html>
<head>
<title>View<fmt:message key="whdevice.title"/></title>
</head>
<body>
	<div id="contentarea">
		<div id="content">
			<h1><fmt:message key="whdevice.title"/>Details</h1>
			<h1><fmt:message key="whdevice.title"/>Details</h1>
<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexWhdevice?page=homePage"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
				<table cellpadding="0" cellspacing="0" id="viewTable">
					<tbody>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="whdevice.id.title"/>:
							</td>
							<td>
							${whdevice.id}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="whdevice.number.title"/>:
							</td>
							<td>${whdevice.number}&nbsp;</td>
						</tr>
				</tbody>
			</table>
			<h1><fmt:message key="whbuilding.title"/></h1>
			<c:if test='${whdevice.relativeWhbuilding != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="whbuilding.id.title"/>:
						</td>
						<td>
							${whdevice.relativeWhbuilding.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="whbuilding.name.title"/>:
						</td>
						<td>
							${whdevice.relativeWhbuilding.name}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>

<h1><fmt:message key="whdatatypes.title"/></h1>
			<form action="${pageContext.request.contextPath}/selectWhdevice?id=${whdevice.id}&whdatatypePage=eachPageShow&whdevicedataPage=${whdevicedataPage}&whdevicedataPageSize=${whdevicedataPageSize}&whdevicedataWantToPage=${whdevicedataWantToPage}" method="post">
				选择每页显示<input type="text" id="whdatatypePageSize" name="whdatatypePageSize" style="height:15px;width:50px">条 
				<input type="submit" id="sure" value="确认" style="height:22px;width:35px">
			</form>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead"><fmt:message key="whdatatype.id.title"/></th>
						<th class="thead"><fmt:message key="whdatatype.name.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${whdevice.relativeWhdatatypes}" var="current"  varStatus="i">	
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
							${current.name}
						&nbsp;
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<tr>
				<td><a  href="${pageContext.request.contextPath}/selectWhdevice?id=${whdevice.id}&whdatatypePage=homePage&whdevicedataPage=${whdevicedataPage}&whdevicedataPageSize=${whdevicedataPageSize}&whdevicedataWantToPage=${whdevicedataWantToPage}">首页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectWhdevice?id=${whdevice.id}&whdatatypePage=previousPage&whdevicedataPage=${whdevicedataPage}&whdevicedataPageSize=${whdevicedataPageSize}&whdevicedataWantToPage=${whdevicedataWantToPage}">上一页</a></td>
				<c:if test="${whdatatypeTotalPage>=7}">
				<c:if test="${whdatatypePageNumber-2>0&&whdatatypePageNumber+4<=whdatatypeTotalPage}">
				<%String pageCount2 = request.getAttribute("whdatatypePageNumber").toString();
				int pageCount = Integer.parseInt(pageCount2);
				for(int i=pageCount-2;i<pageCount+5;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectWhdevice?id=${whdevice.id}&whdatatypePage=jumpPage&whdatatypeWantToPage=<%=i%>&whdevicedataPage=${whdevicedataPage}&whdevicedataPageSize=${whdevicedataPageSize}&whdevicedataWantToPage=${whdevicedataWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<c:if test="${whdatatypePageNumber-2<=0}">
				<%for(int i=1;i<8;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectWhdevice?id=${whdevice.id}&whdatatypePage=jumpPage&whdatatypeWantToPage=<%=i%>&whdevicedataPage=${whdevicedataPage}&whdevicedataPageSize=${whdevicedataPageSize}&whdevicedataWantToPage=${whdevicedataWantToPage}"><%=i%></a></td>
				<%}%>
			    </c:if>
			    <c:if test="${whdatatypePageNumber+4>totalPage }">
			     <%String pageCount2 = request.getAttribute("whdatatypeTotalPage").toString();
				int pageCount = Integer.parseInt(pageCount2);
			   	for(int i=pageCount-5;i<pageCount+2;i++){%>
			    <td><a  href="${pageContext.request.contextPath}/selectWhdevice?id=${whdevice.id}&whdatatypePage=jumpPage&whdatatypeWantToPage=<%=i%>&whdevicedataPage=${whdevicedataPage}&whdevicedataPageSize=${whdevicedataPageSize}&whdevicedataWantToPage=${whdevicedataWantToPage}"><%=i%></a></td>
			   <%}%>
			   	</c:if>
			    </c:if>				
				<c:if test="${whdatatypeTotalPage<7}">
				<% 
						String pageCount2 = request.getAttribute("whdatatypeTotalPage").toString();
						int pageCount = Integer.parseInt(pageCount2);
						for (int i = 1; i <= pageCount+1; i++) {
				%>
				 <td><a href="${pageContext.request.contextPath}/selectWhdevice?id=${whdevice.id}&whdatatypePage=jumpPage&whdatatypeWantToPage=<%=i%>&whdevicedataPage=${whdevicedataPage}&whdevicedataPageSize=${whdevicedataPageSize}&whdevicedataWantToPage=${whdevicedataWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<td><a  href="${pageContext.request.contextPath}/selectWhdevice?id=${whdevice.id}&whdatatypePage=nextPage&whdevicedataPage=${whdevicedataPage}&whdevicedataPageSize=${whdevicedataPageSize}&whdevicedataWantToPage=${whdevicedataWantToPage}">下一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectWhdevice?id=${whdevice.id}&whdatatypePage=lastPage&whdevicedataPage=${whdevicedataPage}&whdevicedataPageSize=${whdevicedataPageSize}&whdevicedataWantToPage=${whdevicedataWantToPage}">尾页</a></td>
				</tr><br>
				当前为第${whdatatypePageNumber+1}页         每页显示${whdatatypePageSize}条      总共${whdatatypeTotalPage+1}页<br>
				<form action="${pageContext.request.contextPath}/selectWhdevice?id=${whdevice.id}&whdatatypePage=jumpPage&whdevicedataPage=${whdevicedataPage}&whdevicedataPageSize=${whdevicedataPageSize}&whdevicedataWantToPage=${whdevicedataWantToPage}" method="post">
					跳转到第<input type="text" id="whdatatypeWantToPage" name="whdatatypeWantToPage" style="height:15px;width:100px">页 
					<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
				</form>
			<h1><fmt:message key="whdevicedatas.title"/></h1>
			<form action="${pageContext.request.contextPath}/selectWhdevice?id=${whdevice.id}&whdevicedataPage=eachPageShow&whdatatypePage=${whdatatypePage}&whdatatypePageSize=${whdatatypePageSize}&whdatatypeWantToPage=${whdatatypeWantToPage}" method="post">
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
				<td><a  href="${pageContext.request.contextPath}/selectWhdevice?id=${whdevice.id}&whdevicedataPage=homePage&whdatatypePage=${whdatatypePage}&whdatatypePageSize=${whdatatypePageSize}&whdatatypeWantToPage=${whdatatypeWantToPage}">首页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectWhdevice?id=${whdevice.id}&whdevicedataPage=previousPage&whdatatypePage=${whdatatypePage}&whdatatypePageSize=${whdatatypePageSize}&whdatatypeWantToPage=${whdatatypeWantToPage}">上一页</a></td>
				<c:if test="${whdevicedataTotalPage>=7}">
				<c:if test="${whdevicedataPageNumber-2>0&&whdevicedataPageNumber+4<=whdevicedataTotalPage}">
				<%String pageCount2 = request.getAttribute("whdevicedataPageNumber").toString();
				int pageCount = Integer.parseInt(pageCount2);
				for(int i=pageCount-2;i<pageCount+5;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectWhdevice?id=${whdevice.id}&whdevicedataPage=jumpPage&whdevicedataWantToPage=<%=i%>&whdatatypePage=${whdatatypePage}&whdatatypePageSize=${whdatatypePageSize}&whdatatypeWantToPage=${whdatatypeWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<c:if test="${whdevicedataPageNumber-2<=0}">
				<%for(int i=1;i<8;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectWhdevice?id=${whdevice.id}&whdevicedataPage=jumpPage&whdevicedataWantToPage=<%=i%>&whdatatypePage=${whdatatypePage}&whdatatypePageSize=${whdatatypePageSize}&whdatatypeWantToPage=${whdatatypeWantToPage}"><%=i%></a></td>
				<%}%>
			    </c:if>
			    <c:if test="${whdevicedataPageNumber+4>totalPage }">
			     <%String pageCount2 = request.getAttribute("whdevicedataTotalPage").toString();
				int pageCount = Integer.parseInt(pageCount2);
			   	for(int i=pageCount-5;i<pageCount+2;i++){%>
			    <td><a  href="${pageContext.request.contextPath}/selectWhdevice?id=${whdevice.id}&whdevicedataPage=jumpPage&whdevicedataWantToPage=<%=i%>&whdatatypePage=${whdatatypePage}&whdatatypePageSize=${whdatatypePageSize}&whdatatypeWantToPage=${whdatatypeWantToPage}"><%=i%></a></td>
			   <%}%>
			   	</c:if>
			    </c:if>				
				<c:if test="${whdevicedataTotalPage<7}">
				<% 
						String pageCount2 = request.getAttribute("whdevicedataTotalPage").toString();
						int pageCount = Integer.parseInt(pageCount2);
						for (int i = 1; i <= pageCount+1; i++) {
				%>
				 <td><a href="${pageContext.request.contextPath}/selectWhdevice?id=${whdevice.id}&whdevicedataPage=jumpPage&whdevicedataWantToPage=<%=i%>&whdatatypePage=${whdatatypePage}&whdatatypePageSize=${whdatatypePageSize}&whdatatypeWantToPage=${whdatatypeWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<td><a  href="${pageContext.request.contextPath}/selectWhdevice?id=${whdevice.id}&whdevicedataPage=nextPage&whdatatypePage=${whdatatypePage}&whdatatypePageSize=${whdatatypePageSize}&whdatatypeWantToPage=${whdatatypeWantToPage}&whdevicePage=${whdevicePage}&whdevicePageSize=${whdevicePageSize}&whdeviceWantToPage=${whdeviceWantToPage}">下一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectWhdevice?id=${whdevice.id}&whdevicedataPage=lastPage&whdatatypePage=${whdatatypePage}&whdatatypePageSize=${whdatatypePageSize}&whdatatypeWantToPage=${whdatatypeWantToPage}&whdevicePage=${whdevicePage}&whdevicePageSize=${whdevicePageSize}&whdeviceWantToPage=${whdeviceWantToPage}">尾页</a></td>
				</tr><br>
				当前为第${whdevicedataPageNumber+1}页         每页显示${whdevicedataPageSize}条      总共${whdevicedataTotalPage+1}页<br>
				<form action="${pageContext.request.contextPath}/selectWhdevice?id=${whdevice.id}&whdevicedataPage=jumpPage&whdatatypePage=${whdatatypePage}&whdatatypePageSize=${whdatatypePageSize}&whdatatypeWantToPage=${whdatatypeWantToPage}&whdevicePage=${whdevicePage}&whdevicePageSize=${whdevicePageSize}&whdeviceWantToPage=${whdeviceWantToPage}" method="post">
					跳转到第<input type="text" id="whdevicedataWantToPage" name="whdevicedataWantToPage" style="height:15px;width:100px">页 
					<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
				</form>
			<div class="clear">&nbsp;</div>
		</div>
	</div>
</body>
</html>
