<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.cacdevice-resources" />
<html>
<head>
<title>View<fmt:message key="cacdevice.title"/></title>
</head>
<body>
	<div id="contentarea">
		<div id="content">
			<h1><fmt:message key="cacdevice.title"/>Details</h1>
			<h1><fmt:message key="cacdevice.title"/>Details</h1>
<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexCacdevice?page=homePage"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
				<table cellpadding="0" cellspacing="0" id="viewTable">
					<tbody>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="cacdevice.id.title"/>:
							</td>
							<td>
							${cacdevice.id}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="cacdevice.name.title"/>:
							</td>
							<td>${cacdevice.name}&nbsp;</td>
						</tr>
				</tbody>
			</table>
			<h1><fmt:message key="cac.title"/></h1>
			<c:if test='${cacdevice.relativeCac != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="cac.id.title"/>:
						</td>
						<td>
							${cacdevice.relativeCac.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="cac.remark.title"/>:
						</td>
						<td>
							${cacdevice.relativeCac.remark}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>

			<h1><fmt:message key="cacdevicedatas.title"/></h1>
			<form action="${pageContext.request.contextPath}/selectCacdevice?id=${cacdevice.id}&cacdevicedataPage=eachPageShow&cacmalfunctionPage=${cacmalfunctionPage}&cacmalfunctionPageSize=${cacmalfunctionPageSize}&cacmalfunctionWantToPage=${cacmalfunctionWantToPage}" method="post">
				选择每页显示<input type="text" id="cacdevicedataPageSize" name="cacdevicedataPageSize" style="height:15px;width:50px">条 
				<input type="submit" id="sure" value="确认" style="height:22px;width:35px">
			</form>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead"><fmt:message key="cacdevicedata.cacdevice.title"/></th>
						<th class="thead"><fmt:message key="cacdevicedata.cacrecordtime.title"/></th>
						<th class="thead"><fmt:message key="cacdevicedata.value.title"/></th>
						<th class="thead"><fmt:message key="cacdevicedata.isreport.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cacdevicedatas}" var="current"  varStatus="i">
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
							${current.cacdevice}
						&nbsp;
						</td>
						<td>
							${current.cacrecordtime}
						&nbsp;
						</td>
						<td>
							${current.value}
						&nbsp;
						</td>
						<td>
							${current.isreport}
						&nbsp;
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<tr>
				<td><a  href="${pageContext.request.contextPath}/selectCacdevice?id=${cacdevice.id}&cacdevicedataPage=homePage&cacmalfunctionPage=${cacmalfunctionPage}&cacmalfunctionPageSize=${cacmalfunctionPageSize}&cacmalfunctionWantToPage=${cacmalfunctionWantToPage}">首页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectCacdevice?id=${cacdevice.id}&cacdevicedataPage=previousPage&cacmalfunctionPage=${cacmalfunctionPage}&cacmalfunctionPageSize=${cacmalfunctionPageSize}&cacmalfunctionWantToPage=${cacmalfunctionWantToPage}">上一页</a></td>
				<c:if test="${cacdevicedataTotalPage>=7}">
				<c:if test="${cacdevicedataPageNumber-2>0&&cacdevicedataPageNumber+4<=cacdevicedataTotalPage}">
				<%String pageCount2 = request.getAttribute("cacdevicedataPageNumber").toString();
				int pageCount = Integer.parseInt(pageCount2);
				for(int i=pageCount-2;i<pageCount+5;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectCacdevice?id=${cacdevice.id}&cacdevicedataPage=jumpPage&cacdevicedataWantToPage=<%=i%>&cacmalfunctionPage=${cacmalfunctionPage}&cacmalfunctionPageSize=${cacmalfunctionPageSize}&cacmalfunctionWantToPage=${cacmalfunctionWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<c:if test="${cacdevicedataPageNumber-2<=0}">
				<%for(int i=1;i<8;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectCacdevice?id=${cacdevice.id}&cacdevicedataPage=jumpPage&cacdevicedataWantToPage=<%=i%>&cacmalfunctionPage=${cacmalfunctionPage}&cacmalfunctionPageSize=${cacmalfunctionPageSize}&cacmalfunctionWantToPage=${cacmalfunctionWantToPage}"><%=i%></a></td>
				<%}%>
			    </c:if>
			    <c:if test="${cacdevicedataPageNumber+4>totalPage }">
			     <%String pageCount2 = request.getAttribute("cacdevicedataTotalPage").toString();
				int pageCount = Integer.parseInt(pageCount2);
			   	for(int i=pageCount-5;i<pageCount+2;i++){%>
			    <td><a  href="${pageContext.request.contextPath}/selectCacdevice?id=${cacdevice.id}&cacdevicedataPage=jumpPage&cacdevicedataWantToPage=<%=i%>&cacmalfunctionPage=${cacmalfunctionPage}&cacmalfunctionPageSize=${cacmalfunctionPageSize}&cacmalfunctionWantToPage=${cacmalfunctionWantToPage}"><%=i%></a></td>
			   <%}%>
			   	</c:if>
			    </c:if>				
				<c:if test="${cacdevicedataTotalPage<7}">
				<% 
						String pageCount2 = request.getAttribute("cacdevicedataTotalPage").toString();
						int pageCount = Integer.parseInt(pageCount2);
						for (int i = 1; i <= pageCount+1; i++) {
				%>
				 <td><a href="${pageContext.request.contextPath}/selectCacdevice?id=${cacdevice.id}&cacdevicedataPage=jumpPage&cacdevicedataWantToPage=<%=i%>&cacmalfunctionPage=${cacmalfunctionPage}&cacmalfunctionPageSize=${cacmalfunctionPageSize}&cacmalfunctionWantToPage=${cacmalfunctionWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<td><a  href="${pageContext.request.contextPath}/selectCacdevice?id=${cacdevice.id}&cacdevicedataPage=nextPage&cacmalfunctionPage=${cacmalfunctionPage}&cacmalfunctionPageSize=${cacmalfunctionPageSize}&cacmalfunctionWantToPage=${cacmalfunctionWantToPage}">下一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectCacdevice?id=${cacdevice.id}&cacdevicedataPage=lastPage&cacmalfunctionPage=${cacmalfunctionPage}&cacmalfunctionPageSize=${cacmalfunctionPageSize}&cacmalfunctionWantToPage=${cacmalfunctionWantToPage}">尾页</a></td>
				</tr><br>
				当前为第${cacdevicedataPageNumber+1}页         每页显示${cacdevicedataPageSize}条      总共${cacdevicedataTotalPage+1}页<br>
				<form action="${pageContext.request.contextPath}/selectCacdevice?id=${cacdevice.id}&cacdevicedataPage=jumpPage&cacmalfunctionPage=${cacmalfunctionPage}&cacmalfunctionPageSize=${cacmalfunctionPageSize}&cacmalfunctionWantToPage=${cacmalfunctionWantToPage}" method="post">
					跳转到第<input type="text" id="cacdevicedataWantToPage" name="cacdevicedataWantToPage" style="height:15px;width:100px">页 
					<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
				</form>
			<h1><fmt:message key="cacmalfunctions.title"/></h1>
			<form action="${pageContext.request.contextPath}/selectCacdevice?id=${cacdevice.id}&cacmalfunctionPage=eachPageShow&cacdevicedataPage=${cacdevicedataPage}&cacdevicedataPageSize=${cacdevicedataPageSize}&cacdevicedataWantToPage=${cacdevicedataWantToPage}" method="post">
				选择每页显示<input type="text" id="cacmalfunctionPageSize" name="cacmalfunctionPageSize" style="height:15px;width:50px">条 
				<input type="submit" id="sure" value="确认" style="height:22px;width:35px">
			</form>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead"><fmt:message key="cacmalfunction.cacrecordtime.title"/></th>
						<th class="thead"><fmt:message key="cacmalfunction.cacdevice.title"/></th>
						<th class="thead"><fmt:message key="cacmalfunction.status.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cacmalfunctions}" var="current"  varStatus="i">
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
							${current.cacrecordtime}
						&nbsp;
						</td>
						<td>
							${current.cacdevice}
						&nbsp;
						</td>
						<td>
							${current.status}
						&nbsp;
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<tr>
				<td><a  href="${pageContext.request.contextPath}/selectCacdevice?id=${cacdevice.id}&cacmalfunctionPage=homePage&cacdevicedataPage=${cacdevicedataPage}&cacdevicedataPageSize=${cacdevicedataPageSize}&cacdevicedataWantToPage=${cacdevicedataWantToPage}">首页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectCacdevice?id=${cacdevice.id}&cacmalfunctionPage=previousPage&cacdevicedataPage=${cacdevicedataPage}&cacdevicedataPageSize=${cacdevicedataPageSize}&cacdevicedataWantToPage=${cacdevicedataWantToPage}">上一页</a></td>
				<c:if test="${cacmalfunctionTotalPage>=7}">
				<c:if test="${cacmalfunctionPageNumber-2>0&&cacmalfunctionPageNumber+4<=cacmalfunctionTotalPage}">
				<%String pageCount2 = request.getAttribute("cacmalfunctionPageNumber").toString();
				int pageCount = Integer.parseInt(pageCount2);
				for(int i=pageCount-2;i<pageCount+5;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectCacdevice?id=${cacdevice.id}&cacmalfunctionPage=jumpPage&cacmalfunctionWantToPage=<%=i%>&cacdevicedataPage=${cacdevicedataPage}&cacdevicedataPageSize=${cacdevicedataPageSize}&cacdevicedataWantToPage=${cacdevicedataWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<c:if test="${cacmalfunctionPageNumber-2<=0}">
				<%for(int i=1;i<8;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectCacdevice?id=${cacdevice.id}&cacmalfunctionPage=jumpPage&cacmalfunctionWantToPage=<%=i%>&cacdevicedataPage=${cacdevicedataPage}&cacdevicedataPageSize=${cacdevicedataPageSize}&cacdevicedataWantToPage=${cacdevicedataWantToPage}"><%=i%></a></td>
				<%}%>
			    </c:if>
			    <c:if test="${cacmalfunctionPageNumber+4>totalPage }">
			     <%String pageCount2 = request.getAttribute("cacmalfunctionTotalPage").toString();
				int pageCount = Integer.parseInt(pageCount2);
			   	for(int i=pageCount-5;i<pageCount+2;i++){%>
			    <td><a  href="${pageContext.request.contextPath}/selectCacdevice?id=${cacdevice.id}&cacmalfunctionPage=jumpPage&cacmalfunctionWantToPage=<%=i%>&cacdevicedataPage=${cacdevicedataPage}&cacdevicedataPageSize=${cacdevicedataPageSize}&cacdevicedataWantToPage=${cacdevicedataWantToPage}"><%=i%></a></td>
			   <%}%>
			   	</c:if>
			    </c:if>				
				<c:if test="${cacmalfunctionTotalPage<7}">
				<% 
						String pageCount2 = request.getAttribute("cacmalfunctionTotalPage").toString();
						int pageCount = Integer.parseInt(pageCount2);
						for (int i = 1; i <= pageCount+1; i++) {
				%>
				 <td><a href="${pageContext.request.contextPath}/selectCacdevice?id=${cacdevice.id}&cacmalfunctionPage=jumpPage&cacmalfunctionWantToPage=<%=i%>&cacdevicedataPage=${cacdevicedataPage}&cacdevicedataPageSize=${cacdevicedataPageSize}&cacdevicedataWantToPage=${cacdevicedataWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<td><a  href="${pageContext.request.contextPath}/selectCacdevice?id=${cacdevice.id}&cacmalfunctionPage=nextPage&cacdevicedataPage=${cacdevicedataPage}&cacdevicedataPageSize=${cacdevicedataPageSize}&cacdevicedataWantToPage=${cacdevicedataWantToPage}">下一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectCacdevice?id=${cacdevice.id}&cacmalfunctionPage=lastPage&cacdevicedataPage=${cacdevicedataPage}&cacdevicedataPageSize=${cacdevicedataPageSize}&cacdevicedataWantToPage=${cacdevicedataWantToPage}">尾页</a></td>
				</tr><br>
				当前为第${cacmalfunctionPageNumber+1}页         每页显示${cacmalfunctionPageSize}条      总共${cacmalfunctionTotalPage+1}页<br>
				<form action="${pageContext.request.contextPath}/selectCacdevice?id=${cacdevice.id}&cacmalfunctionPage=jumpPage&cacdevicedataPage=${cacdevicedataPage}&cacdevicedataPageSize=${cacdevicedataPageSize}&cacdevicedataWantToPage=${cacdevicedataWantToPage}" method="post">
					跳转到第<input type="text" id="cacmalfunctionWantToPage" name="cacmalfunctionWantToPage" style="height:15px;width:100px">页 
					<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
				</form>
			<div class="clear">&nbsp;</div>
		</div>
	</div>
</body>
</html>
