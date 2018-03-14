<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.project-resources" />
<html>
<head>
<title>View<fmt:message key="project.title"/></title>
</head>
<body>
	<div id="contentarea">
		<div id="content">
			<h1><fmt:message key="project.title"/>Details</h1>
			<h1><fmt:message key="project.title"/>Details</h1>
<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexProject?page=homePage"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
				<table cellpadding="0" cellspacing="0" id="viewTable">
					<tbody>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="project.id.title"/>:
							</td>
							<td>
							${project.id}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="project.name.title"/>:
							</td>
							<td>${project.name}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="project.type.title"/>:
							</td>
							<td>${project.type}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="project.status.title"/>:
							</td>
							<td>${project.status}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="project.province.title"/>:
							</td>
							<td>${project.province}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="project.city.title"/>:
							</td>
							<td>${project.city}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="project.area.title"/>:
							</td>
							<td>${project.area}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="project.address.title"/>:
							</td>
							<td>${project.address}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="project.detail.title"/>:
							</td>
							<td>${project.detail}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="project.remark.title"/>:
							</td>
							<td>${project.remark}&nbsp;</td>
						</tr>
				</tbody>
			</table>
			<h1><fmt:message key="company.title"/></h1>
			<c:if test='${project.relativeCompany != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="company.id.title"/>:
						</td>
						<td>
							${project.relativeCompany.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="company.name.title"/>:
						</td>
						<td>
							${project.relativeCompany.name}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="company.phone.title"/>:
						</td>
						<td>
							${project.relativeCompany.phone}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="company.fax.title"/>:
						</td>
						<td>
							${project.relativeCompany.fax}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="company.postcode.title"/>:
						</td>
						<td>
							${project.relativeCompany.postcode}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="company.address.title"/>:
						</td>
						<td>
							${project.relativeCompany.address}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="company.website.title"/>:
						</td>
						<td>
							${project.relativeCompany.website}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="company.personduty.title"/>:
						</td>
						<td>
							${project.relativeCompany.personduty}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="company.detail.title"/>:
						</td>
						<td>
							${project.relativeCompany.detail}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="company.remark.title"/>:
						</td>
						<td>
							${project.relativeCompany.remark}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>
			<h1><fmt:message key="staff.title"/></h1>
			<c:if test='${project.relativeStaff != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="staff.id.title"/>:
						</td>
						<td>
							${project.relativeStaff.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="staff.name.title"/>:
						</td>
						<td>
							${project.relativeStaff.name}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="staff.duty.title"/>:
						</td>
						<td>
							${project.relativeStaff.duty}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="staff.token.title"/>:
						</td>
						<td>
							${project.relativeStaff.token}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="staff.type.title"/>:
						</td>
						<td>
							${project.relativeStaff.type}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="staff.status.title"/>:
						</td>
						<td>
							${project.relativeStaff.status}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="staff.level.title"/>:
						</td>
						<td>
							${project.relativeStaff.level}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="staff.loginname.title"/>:
						</td>
						<td>
							${project.relativeStaff.loginname}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="staff.password.title"/>:
						</td>
						<td>
							${project.relativeStaff.password}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="staff.remark.title"/>:
						</td>
						<td>
							${project.relativeStaff.remark}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>

			<h1><fmt:message key="cacs.title"/></h1>
			<form action="${pageContext.request.contextPath}/selectProject?id=${project.id}&cacPage=eachPageShow&ledbuildingPage=${ledbuildingPage}&ledbuildingPageSize=${ledbuildingPageSize}&ledbuildingWantToPage=${ledbuildingWantToPage}&whbuildingPage=${whbuildingPage}&whbuildingPageSize=${whbuildingPageSize}&whbuildingWantToPage=${whbuildingWantToPage}" method="post">
				选择每页显示<input type="text" id="cacPageSize" name="cacPageSize" style="height:15px;width:50px">条 
				<input type="submit" id="sure" value="确认" style="height:22px;width:35px">
			</form>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead"><fmt:message key="cac.id.title"/></th>
						<th class="thead"><fmt:message key="cac.remark.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cacs}" var="current"  varStatus="i">
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
							${current.remark}
						&nbsp;
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<tr>
				<td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&cacPage=homePage&ledbuildingPage=${ledbuildingPage}&ledbuildingPageSize=${ledbuildingPageSize}&ledbuildingWantToPage=${ledbuildingWantToPage}&whbuildingPage=${whbuildingPage}&whbuildingPageSize=${whbuildingPageSize}&whbuildingWantToPage=${whbuildingWantToPage}">首页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&cacPage=previousPage&ledbuildingPage=${ledbuildingPage}&ledbuildingPageSize=${ledbuildingPageSize}&ledbuildingWantToPage=${ledbuildingWantToPage}&whbuildingPage=${whbuildingPage}&whbuildingPageSize=${whbuildingPageSize}&whbuildingWantToPage=${whbuildingWantToPage}">上一页</a></td>
				<c:if test="${cacTotalPage>=7}">
				<c:if test="${cacPageNumber-2>0&&cacPageNumber+4<=cacTotalPage}">
				<%String pageCount2 = request.getAttribute("cacPageNumber").toString();
				int pageCount = Integer.parseInt(pageCount2);
				for(int i=pageCount-2;i<pageCount+5;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&cacPage=jumpPage&cacWantToPage=<%=i%>&ledbuildingPage=${ledbuildingPage}&ledbuildingPageSize=${ledbuildingPageSize}&ledbuildingWantToPage=${ledbuildingWantToPage}&whbuildingPage=${whbuildingPage}&whbuildingPageSize=${whbuildingPageSize}&whbuildingWantToPage=${whbuildingWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<c:if test="${cacPageNumber-2<=0}">
				<%for(int i=1;i<8;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&cacPage=jumpPage&cacWantToPage=<%=i%>&ledbuildingPage=${ledbuildingPage}&ledbuildingPageSize=${ledbuildingPageSize}&ledbuildingWantToPage=${ledbuildingWantToPage}&whbuildingPage=${whbuildingPage}&whbuildingPageSize=${whbuildingPageSize}&whbuildingWantToPage=${whbuildingWantToPage}"><%=i%></a></td>
				<%}%>
			    </c:if>
			    <c:if test="${cacPageNumber+4>totalPage }">
			     <%String pageCount2 = request.getAttribute("cacTotalPage").toString();
				int pageCount = Integer.parseInt(pageCount2);
			   	for(int i=pageCount-5;i<pageCount+2;i++){%>
			    <td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&cacPage=jumpPage&cacWantToPage=<%=i%>&ledbuildingPage=${ledbuildingPage}&ledbuildingPageSize=${ledbuildingPageSize}&ledbuildingWantToPage=${ledbuildingWantToPage}&whbuildingPage=${whbuildingPage}&whbuildingPageSize=${whbuildingPageSize}&whbuildingWantToPage=${whbuildingWantToPage}"><%=i%></a></td>
			   <%}%>
			   	</c:if>
			    </c:if>				
				<c:if test="${cacTotalPage<7}">
				<% 
						String pageCount2 = request.getAttribute("cacTotalPage").toString();
						int pageCount = Integer.parseInt(pageCount2);
						for (int i = 1; i <= pageCount+1; i++) {
				%>
				 <td><a href="${pageContext.request.contextPath}/selectProject?id=${project.id}&cacPage=jumpPage&cacWantToPage=<%=i%>&ledbuildingPage=${ledbuildingPage}&ledbuildingPageSize=${ledbuildingPageSize}&ledbuildingWantToPage=${ledbuildingWantToPage}&whbuildingPage=${whbuildingPage}&whbuildingPageSize=${whbuildingPageSize}&whbuildingWantToPage=${whbuildingWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&cacPage=nextPage&ledbuildingPage=${ledbuildingPage}&ledbuildingPageSize=${ledbuildingPageSize}&ledbuildingWantToPage=${ledbuildingWantToPage}&whbuildingPage=${whbuildingPage}&whbuildingPageSize=${whbuildingPageSize}&whbuildingWantToPage=${whbuildingWantToPage}">下一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&cacPage=lastPage&ledbuildingPage=${ledbuildingPage}&ledbuildingPageSize=${ledbuildingPageSize}&ledbuildingWantToPage=${ledbuildingWantToPage}&whbuildingPage=${whbuildingPage}&whbuildingPageSize=${whbuildingPageSize}&whbuildingWantToPage=${whbuildingWantToPage}">尾页</a></td>
				</tr><br>
				当前为第${cacPageNumber+1}页         每页显示${cacPageSize}条      总共${cacTotalPage+1}页<br>
				<form action="${pageContext.request.contextPath}/selectProject?id=${project.id}&cacPage=jumpPage&ledbuildingPage=${ledbuildingPage}&ledbuildingPageSize=${ledbuildingPageSize}&ledbuildingWantToPage=${ledbuildingWantToPage}&whbuildingPage=${whbuildingPage}&whbuildingPageSize=${whbuildingPageSize}&whbuildingWantToPage=${whbuildingWantToPage}" method="post">
					跳转到第<input type="text" id="cacWantToPage" name="cacWantToPage" style="height:15px;width:100px">页 
					<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
				</form>
			<h1><fmt:message key="ledbuildings.title"/></h1>
			<form action="${pageContext.request.contextPath}/selectProject?id=${project.id}&ledbuildingPage=eachPageShow&cacPage=${cacPage}&cacPageSize=${cacPageSize}&cacWantToPage=${cacWantToPage}&whbuildingPage=${whbuildingPage}&whbuildingPageSize=${whbuildingPageSize}&whbuildingWantToPage=${whbuildingWantToPage}" method="post">
				选择每页显示<input type="text" id="ledbuildingPageSize" name="ledbuildingPageSize" style="height:15px;width:50px">条 
				<input type="submit" id="sure" value="确认" style="height:22px;width:35px">
			</form>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead"><fmt:message key="ledbuilding.id.title"/></th>
						<th class="thead"><fmt:message key="ledbuilding.name.title"/></th>
						<th class="thead"><fmt:message key="ledbuilding.well.title"/></th>
						<th class="thead"><fmt:message key="ledbuilding.storey.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ledbuildings}" var="current"  varStatus="i">
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
				<td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&ledbuildingPage=homePage&cacPage=${cacPage}&cacPageSize=${cacPageSize}&cacWantToPage=${cacWantToPage}&whbuildingPage=${whbuildingPage}&whbuildingPageSize=${whbuildingPageSize}&whbuildingWantToPage=${whbuildingWantToPage}">首页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&ledbuildingPage=previousPage&cacPage=${cacPage}&cacPageSize=${cacPageSize}&cacWantToPage=${cacWantToPage}&whbuildingPage=${whbuildingPage}&whbuildingPageSize=${whbuildingPageSize}&whbuildingWantToPage=${whbuildingWantToPage}">上一页</a></td>
				<c:if test="${ledbuildingTotalPage>=7}">
				<c:if test="${ledbuildingPageNumber-2>0&&ledbuildingPageNumber+4<=ledbuildingTotalPage}">
				<%String pageCount2 = request.getAttribute("ledbuildingPageNumber").toString();
				int pageCount = Integer.parseInt(pageCount2);
				for(int i=pageCount-2;i<pageCount+5;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&ledbuildingPage=jumpPage&ledbuildingWantToPage=<%=i%>&cacPage=${cacPage}&cacPageSize=${cacPageSize}&cacWantToPage=${cacWantToPage}&whbuildingPage=${whbuildingPage}&whbuildingPageSize=${whbuildingPageSize}&whbuildingWantToPage=${whbuildingWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<c:if test="${ledbuildingPageNumber-2<=0}">
				<%for(int i=1;i<8;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&ledbuildingPage=jumpPage&ledbuildingWantToPage=<%=i%>&cacPage=${cacPage}&cacPageSize=${cacPageSize}&cacWantToPage=${cacWantToPage}&whbuildingPage=${whbuildingPage}&whbuildingPageSize=${whbuildingPageSize}&whbuildingWantToPage=${whbuildingWantToPage}"><%=i%></a></td>
				<%}%>
			    </c:if>
			    <c:if test="${ledbuildingPageNumber+4>totalPage }">
			     <%String pageCount2 = request.getAttribute("ledbuildingTotalPage").toString();
				int pageCount = Integer.parseInt(pageCount2);
			   	for(int i=pageCount-5;i<pageCount+2;i++){%>
			    <td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&ledbuildingPage=jumpPage&ledbuildingWantToPage=<%=i%>&cacPage=${cacPage}&cacPageSize=${cacPageSize}&cacWantToPage=${cacWantToPage}&whbuildingPage=${whbuildingPage}&whbuildingPageSize=${whbuildingPageSize}&whbuildingWantToPage=${whbuildingWantToPage}"><%=i%></a></td>
			   <%}%>
			   	</c:if>
			    </c:if>				
				<c:if test="${ledbuildingTotalPage<7}">
				<% 
						String pageCount2 = request.getAttribute("ledbuildingTotalPage").toString();
						int pageCount = Integer.parseInt(pageCount2);
						for (int i = 1; i <= pageCount+1; i++) {
				%>
				 <td><a href="${pageContext.request.contextPath}/selectProject?id=${project.id}&ledbuildingPage=jumpPage&ledbuildingWantToPage=<%=i%>&cacPage=${cacPage}&cacPageSize=${cacPageSize}&cacWantToPage=${cacWantToPage}&whbuildingPage=${whbuildingPage}&whbuildingPageSize=${whbuildingPageSize}&whbuildingWantToPage=${whbuildingWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&ledbuildingPage=nextPage&cacPage=${cacPage}&cacPageSize=${cacPageSize}&cacWantToPage=${cacWantToPage}&whbuildingPage=${whbuildingPage}&whbuildingPageSize=${whbuildingPageSize}&whbuildingWantToPage=${whbuildingWantToPage}">下一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&ledbuildingPage=lastPage&cacPage=${cacPage}&cacPageSize=${cacPageSize}&cacWantToPage=${cacWantToPage}&whbuildingPage=${whbuildingPage}&whbuildingPageSize=${whbuildingPageSize}&whbuildingWantToPage=${whbuildingWantToPage}">尾页</a></td>
				</tr><br>
				当前为第${ledbuildingPageNumber+1}页         每页显示${ledbuildingPageSize}条      总共${ledbuildingTotalPage+1}页<br>
				<form action="${pageContext.request.contextPath}/selectProject?id=${project.id}&ledbuildingPage=jumpPage&cacPage=${cacPage}&cacPageSize=${cacPageSize}&cacWantToPage=${cacWantToPage}&whbuildingPage=${whbuildingPage}&whbuildingPageSize=${whbuildingPageSize}&whbuildingWantToPage=${whbuildingWantToPage}" method="post">
					跳转到第<input type="text" id="ledbuildingWantToPage" name="ledbuildingWantToPage" style="height:15px;width:100px">页 
					<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
				</form>
			<h1><fmt:message key="whbuildings.title"/></h1>
			<form action="${pageContext.request.contextPath}/selectProject?id=${project.id}&whbuildingPage=eachPageShow&cacPage=${cacPage}&cacPageSize=${cacPageSize}&cacWantToPage=${cacWantToPage}&ledbuildingPage=${ledbuildingPage}&ledbuildingPageSize=${ledbuildingPageSize}&ledbuildingWantToPage=${ledbuildingWantToPage}" method="post">
				选择每页显示<input type="text" id="whbuildingPageSize" name="whbuildingPageSize" style="height:15px;width:50px">条 
				<input type="submit" id="sure" value="确认" style="height:22px;width:35px">
			</form>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead"><fmt:message key="whbuilding.id.title"/></th>
						<th class="thead"><fmt:message key="whbuilding.name.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${whbuildings}" var="current"  varStatus="i">
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
				<td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&whbuildingPage=homePage&cacPage=${cacPage}&cacPageSize=${cacPageSize}&cacWantToPage=${cacWantToPage}&ledbuildingPage=${ledbuildingPage}&ledbuildingPageSize=${ledbuildingPageSize}&ledbuildingWantToPage=${ledbuildingWantToPage}">首页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&whbuildingPage=previousPage&cacPage=${cacPage}&cacPageSize=${cacPageSize}&cacWantToPage=${cacWantToPage}&ledbuildingPage=${ledbuildingPage}&ledbuildingPageSize=${ledbuildingPageSize}&ledbuildingWantToPage=${ledbuildingWantToPage}">上一页</a></td>
				<c:if test="${whbuildingTotalPage>=7}">
				<c:if test="${whbuildingPageNumber-2>0&&whbuildingPageNumber+4<=whbuildingTotalPage}">
				<%String pageCount2 = request.getAttribute("whbuildingPageNumber").toString();
				int pageCount = Integer.parseInt(pageCount2);
				for(int i=pageCount-2;i<pageCount+5;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&whbuildingPage=jumpPage&whbuildingWantToPage=<%=i%>&cacPage=${cacPage}&cacPageSize=${cacPageSize}&cacWantToPage=${cacWantToPage}&ledbuildingPage=${ledbuildingPage}&ledbuildingPageSize=${ledbuildingPageSize}&ledbuildingWantToPage=${ledbuildingWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<c:if test="${whbuildingPageNumber-2<=0}">
				<%for(int i=1;i<8;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&whbuildingPage=jumpPage&whbuildingWantToPage=<%=i%>&cacPage=${cacPage}&cacPageSize=${cacPageSize}&cacWantToPage=${cacWantToPage}&ledbuildingPage=${ledbuildingPage}&ledbuildingPageSize=${ledbuildingPageSize}&ledbuildingWantToPage=${ledbuildingWantToPage}"><%=i%></a></td>
				<%}%>
			    </c:if>
			    <c:if test="${whbuildingPageNumber+4>totalPage }">
			     <%String pageCount2 = request.getAttribute("whbuildingTotalPage").toString();
				int pageCount = Integer.parseInt(pageCount2);
			   	for(int i=pageCount-5;i<pageCount+2;i++){%>
			    <td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&whbuildingPage=jumpPage&whbuildingWantToPage=<%=i%>&cacPage=${cacPage}&cacPageSize=${cacPageSize}&cacWantToPage=${cacWantToPage}&ledbuildingPage=${ledbuildingPage}&ledbuildingPageSize=${ledbuildingPageSize}&ledbuildingWantToPage=${ledbuildingWantToPage}"><%=i%></a></td>
			   <%}%>
			   	</c:if>
			    </c:if>				
				<c:if test="${whbuildingTotalPage<7}">
				<% 
						String pageCount2 = request.getAttribute("whbuildingTotalPage").toString();
						int pageCount = Integer.parseInt(pageCount2);
						for (int i = 1; i <= pageCount+1; i++) {
				%>
				 <td><a href="${pageContext.request.contextPath}/selectProject?id=${project.id}&whbuildingPage=jumpPage&whbuildingWantToPage=<%=i%>&cacPage=${cacPage}&cacPageSize=${cacPageSize}&cacWantToPage=${cacWantToPage}&ledbuildingPage=${ledbuildingPage}&ledbuildingPageSize=${ledbuildingPageSize}&ledbuildingWantToPage=${ledbuildingWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&whbuildingPage=nextPage&cacPage=${cacPage}&cacPageSize=${cacPageSize}&cacWantToPage=${cacWantToPage}&ledbuildingPage=${ledbuildingPage}&ledbuildingPageSize=${ledbuildingPageSize}&ledbuildingWantToPage=${ledbuildingWantToPage}">下一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectProject?id=${project.id}&whbuildingPage=lastPage&cacPage=${cacPage}&cacPageSize=${cacPageSize}&cacWantToPage=${cacWantToPage}&ledbuildingPage=${ledbuildingPage}&ledbuildingPageSize=${ledbuildingPageSize}&ledbuildingWantToPage=${ledbuildingWantToPage}">尾页</a></td>
				</tr><br>
				当前为第${whbuildingPageNumber+1}页         每页显示${whbuildingPageSize}条      总共${whbuildingTotalPage+1}页<br>
				<form action="${pageContext.request.contextPath}/selectProject?id=${project.id}&whbuildingPage=jumpPage&cacPage=${cacPage}&cacPageSize=${cacPageSize}&cacWantToPage=${cacWantToPage}&ledbuildingPage=${ledbuildingPage}&ledbuildingPageSize=${ledbuildingPageSize}&ledbuildingWantToPage=${ledbuildingWantToPage}" method="post">
					跳转到第<input type="text" id="whbuildingWantToPage" name="whbuildingWantToPage" style="height:15px;width:100px">页 
					<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
				</form>
			<div class="clear">&nbsp;</div>
		</div>
	</div>
</body>
</html>
