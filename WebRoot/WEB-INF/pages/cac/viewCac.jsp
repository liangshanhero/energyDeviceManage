<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.cac-resources" />
<html>
<head>
<title>View<fmt:message key="cac.title"/></title>
</head>
<body>
	<div id="contentarea">
		<div id="content">
			<h1><fmt:message key="cac.title"/>Details</h1>
			<h1><fmt:message key="cac.title"/>Details</h1>
<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexCac?page=homePage"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
				<table cellpadding="0" cellspacing="0" id="viewTable">
					<tbody>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="cac.id.title"/>:
							</td>
							<td>
							${cac.id}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="cac.remark.title"/>:
							</td>
							<td>${cac.remark}&nbsp;</td>
						</tr>
				</tbody>
			</table>
			<h1><fmt:message key="project.title"/></h1>
			<c:if test='${cac.relativeProject != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="project.id.title"/>:
						</td>
						<td>
							${cac.relativeProject.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.name.title"/>:
						</td>
						<td>
							${cac.relativeProject.name}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.type.title"/>:
						</td>
						<td>
							${cac.relativeProject.type}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.status.title"/>:
						</td>
						<td>
							${cac.relativeProject.status}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.province.title"/>:
						</td>
						<td>
							${cac.relativeProject.province}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.city.title"/>:
						</td>
						<td>
							${cac.relativeProject.city}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.area.title"/>:
						</td>
						<td>
							${cac.relativeProject.area}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.address.title"/>:
						</td>
						<td>
							${cac.relativeProject.address}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.detail.title"/>:
						</td>
						<td>
							${cac.relativeProject.detail}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="project.remark.title"/>:
						</td>
						<td>
							${cac.relativeProject.remark}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>

			<h1><fmt:message key="cacdevices.title"/></h1>
			<form action="${pageContext.request.contextPath}/selectCac?id=${cac.id}&cacdevicePage=eachPageShow&cacsensorPage=${cacsensorPage}&cacsensorPageSize=${cacsensorPageSize}&cacsensorWantToPage=${cacsensorWantToPage}" method="post">
				选择每页显示<input type="text" id="cacdevicePageSize" name="cacdevicePageSize" style="height:15px;width:50px">条 
				<input type="submit" id="sure" value="确认" style="height:22px;width:35px">
			</form>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead"><fmt:message key="cacdevice.id.title"/></th>
						<th class="thead"><fmt:message key="cacdevice.name.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cacdevices}" var="current"  varStatus="i">
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
				<td><a  href="${pageContext.request.contextPath}/selectCac?id=${cac.id}&cacdevicePage=homePage&cacsensorPage=${cacsensorPage}&cacsensorPageSize=${cacsensorPageSize}&cacsensorWantToPage=${cacsensorWantToPage}">首页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectCac?id=${cac.id}&cacdevicePage=previousPage&cacsensorPage=${cacsensorPage}&cacsensorPageSize=${cacsensorPageSize}&cacsensorWantToPage=${cacsensorWantToPage}">上一页</a></td>
				<c:if test="${cacdeviceTotalPage>=7}">
				<c:if test="${cacdevicePageNumber-2>0&&cacdevicePageNumber+4<=cacdeviceTotalPage}">
				<%String pageCount2 = request.getAttribute("cacdevicePageNumber").toString();
				int pageCount = Integer.parseInt(pageCount2);
				for(int i=pageCount-2;i<pageCount+5;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectCac?id=${cac.id}&cacdevicePage=jumpPage&cacdeviceWantToPage=<%=i%>&cacsensorPage=${cacsensorPage}&cacsensorPageSize=${cacsensorPageSize}&cacsensorWantToPage=${cacsensorWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<c:if test="${cacdevicePageNumber-2<=0}">
				<%for(int i=1;i<8;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectCac?id=${cac.id}&cacdevicePage=jumpPage&cacdeviceWantToPage=<%=i%>&cacsensorPage=${cacsensorPage}&cacsensorPageSize=${cacsensorPageSize}&cacsensorWantToPage=${cacsensorWantToPage}"><%=i%></a></td>
				<%}%>
			    </c:if>
			    <c:if test="${cacdevicePageNumber+4>totalPage }">
			     <%String pageCount2 = request.getAttribute("cacdeviceTotalPage").toString();
				int pageCount = Integer.parseInt(pageCount2);
			   	for(int i=pageCount-5;i<pageCount+2;i++){%>
			    <td><a  href="${pageContext.request.contextPath}/selectCac?id=${cac.id}&cacdevicePage=jumpPage&cacdeviceWantToPage=<%=i%>&cacsensorPage=${cacsensorPage}&cacsensorPageSize=${cacsensorPageSize}&cacsensorWantToPage=${cacsensorWantToPage}"><%=i%></a></td>
			   <%}%>
			   	</c:if>
			    </c:if>				
				<c:if test="${cacdeviceTotalPage<7}">
				<% 
						String pageCount2 = request.getAttribute("cacdeviceTotalPage").toString();
						int pageCount = Integer.parseInt(pageCount2);
						for (int i = 1; i <= pageCount+1; i++) {
				%>
				 <td><a href="${pageContext.request.contextPath}/selectCac?id=${cac.id}&cacdevicePage=jumpPage&cacdeviceWantToPage=<%=i%>&cacsensorPage=${cacsensorPage}&cacsensorPageSize=${cacsensorPageSize}&cacsensorWantToPage=${cacsensorWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<td><a  href="${pageContext.request.contextPath}/selectCac?id=${cac.id}&cacdevicePage=nextPage&cacsensorPage=${cacsensorPage}&cacsensorPageSize=${cacsensorPageSize}&cacsensorWantToPage=${cacsensorWantToPage}">下一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectCac?id=${cac.id}&cacdevicePage=lastPage&cacsensorPage=${cacsensorPage}&cacsensorPageSize=${cacsensorPageSize}&cacsensorWantToPage=${cacsensorWantToPage}">尾页</a></td>
				</tr><br>
				当前为第${cacdevicePageNumber+1}页         每页显示${cacdevicePageSize}条      总共${cacdeviceTotalPage+1}页<br>
				<form action="${pageContext.request.contextPath}/selectCac?id=${cac.id}&cacdevicePage=jumpPage&cacsensorPage=${cacsensorPage}&cacsensorPageSize=${cacsensorPageSize}&cacsensorWantToPage=${cacsensorWantToPage}" method="post">
					跳转到第<input type="text" id="cacdeviceWantToPage" name="cacdeviceWantToPage" style="height:15px;width:100px">页 
					<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
				</form>
			<h1><fmt:message key="cacsensors.title"/></h1>
			<form action="${pageContext.request.contextPath}/selectCac?id=${cac.id}&cacsensorPage=eachPageShow&cacdevicePage=${cacdevicePage}&cacdevicePageSize=${cacdevicePageSize}&cacdeviceWantToPage=${cacdeviceWantToPage}" method="post">
				选择每页显示<input type="text" id="cacsensorPageSize" name="cacsensorPageSize" style="height:15px;width:50px">条 
				<input type="submit" id="sure" value="确认" style="height:22px;width:35px">
			</form>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead"><fmt:message key="cacsensor.id.title"/></th>
						<th class="thead"><fmt:message key="cacsensor.name.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cacsensors}" var="current"  varStatus="i">
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
				<td><a  href="${pageContext.request.contextPath}/selectCac?id=${cac.id}&cacsensorPage=homePage&cacdevicePage=${cacdevicePage}&cacdevicePageSize=${cacdevicePageSize}&cacdeviceWantToPage=${cacdeviceWantToPage}">首页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectCac?id=${cac.id}&cacsensorPage=previousPage&cacdevicePage=${cacdevicePage}&cacdevicePageSize=${cacdevicePageSize}&cacdeviceWantToPage=${cacdeviceWantToPage}">上一页</a></td>
				<c:if test="${cacsensorTotalPage>=7}">
				<c:if test="${cacsensorPageNumber-2>0&&cacsensorPageNumber+4<=cacsensorTotalPage}">
				<%String pageCount2 = request.getAttribute("cacsensorPageNumber").toString();
				int pageCount = Integer.parseInt(pageCount2);
				for(int i=pageCount-2;i<pageCount+5;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectCac?id=${cac.id}&cacsensorPage=jumpPage&cacsensorWantToPage=<%=i%>&cacdevicePage=${cacdevicePage}&cacdevicePageSize=${cacdevicePageSize}&cacdeviceWantToPage=${cacdeviceWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<c:if test="${cacsensorPageNumber-2<=0}">
				<%for(int i=1;i<8;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectCac?id=${cac.id}&cacsensorPage=jumpPage&cacsensorWantToPage=<%=i%>&cacdevicePage=${cacdevicePage}&cacdevicePageSize=${cacdevicePageSize}&cacdeviceWantToPage=${cacdeviceWantToPage}"><%=i%></a></td>
				<%}%>
			    </c:if>
			    <c:if test="${cacsensorPageNumber+4>totalPage }">
			     <%String pageCount2 = request.getAttribute("cacsensorTotalPage").toString();
				int pageCount = Integer.parseInt(pageCount2);
			   	for(int i=pageCount-5;i<pageCount+2;i++){%>
			    <td><a  href="${pageContext.request.contextPath}/selectCac?id=${cac.id}&cacsensorPage=jumpPage&cacsensorWantToPage=<%=i%>&cacdevicePage=${cacdevicePage}&cacdevicePageSize=${cacdevicePageSize}&cacdeviceWantToPage=${cacdeviceWantToPage}"><%=i%></a></td>
			   <%}%>
			   	</c:if>
			    </c:if>				
				<c:if test="${cacsensorTotalPage<7}">
				<% 
						String pageCount2 = request.getAttribute("cacsensorTotalPage").toString();
						int pageCount = Integer.parseInt(pageCount2);
						for (int i = 1; i <= pageCount+1; i++) {
				%>
				 <td><a href="${pageContext.request.contextPath}/selectCac?id=${cac.id}&cacsensorPage=jumpPage&cacsensorWantToPage=<%=i%>&cacdevicePage=${cacdevicePage}&cacdevicePageSize=${cacdevicePageSize}&cacdeviceWantToPage=${cacdeviceWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<td><a  href="${pageContext.request.contextPath}/selectCac?id=${cac.id}&cacsensorPage=nextPage&cacdevicePage=${cacdevicePage}&cacdevicePageSize=${cacdevicePageSize}&cacdeviceWantToPage=${cacdeviceWantToPage}">下一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectCac?id=${cac.id}&cacsensorPage=lastPage&cacdevicePage=${cacdevicePage}&cacdevicePageSize=${cacdevicePageSize}&cacdeviceWantToPage=${cacdeviceWantToPage}">尾页</a></td>
				</tr><br>
				当前为第${cacsensorPageNumber+1}页         每页显示${cacsensorPageSize}条      总共${cacsensorTotalPage+1}页<br>
				<form action="${pageContext.request.contextPath}/selectCac?id=${cac.id}&cacsensorPage=jumpPage&cacdevicePage=${cacdevicePage}&cacdevicePageSize=${cacdevicePageSize}&cacdeviceWantToPage=${cacdeviceWantToPage}" method="post">
					跳转到第<input type="text" id="cacsensorWantToPage" name="cacsensorWantToPage" style="height:15px;width:100px">页 
					<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
				</form>
			<div class="clear">&nbsp;</div>
		</div>
	</div>
</body>
</html>
