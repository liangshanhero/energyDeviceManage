<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.company-resources" />
<html>
<head>
<script src="js/iziModal.min.js"></script>
<title>View<fmt:message key="company.title"/></title>
</head>
<body>
	<div id="contentarea">
		<div id="content">
			<h1><fmt:message key="company.title"/>Details</h1>
			<h1><fmt:message key="company.title"/>Details</h1>
<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexCompany?page=homePage"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
				<table cellpadding="0" cellspacing="0" id="viewTable">
					<tbody>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="company.id.title"/>:
							</td>
							<td>
							${company.id}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="company.name.title"/>:
							</td>
							<td>${company.name}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="company.phone.title"/>:
							</td>
							<td>${company.phone}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="company.fax.title"/>:
							</td>
							<td>${company.fax}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="company.postcode.title"/>:
							</td>
							<td>${company.postcode}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="company.address.title"/>:
							</td>
							<td>${company.address}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="company.website.title"/>:
							</td>
							<td>${company.website}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="company.personduty.title"/>:
							</td>
							<td>${company.personduty}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="company.detail.title"/>:
							</td>
							<td>${company.detail}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="company.remark.title"/>:
							</td>
							<td>${company.remark}&nbsp;</td>
						</tr>
				</tbody>
			</table>
			<h1><fmt:message key="staff.title"/></h1>
			<c:if test='${company.relativeStaff != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="staff.id.title"/>:
						</td>
						<td>
							${company.relativeStaff.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="staff.name.title"/>:
						</td>
						<td>
							${company.relativeStaff.name}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="staff.duty.title"/>:
						</td>
						<td>
							${company.relativeStaff.duty}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="staff.token.title"/>:
						</td>
						<td>
							${company.relativeStaff.token}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="staff.type.title"/>:
						</td>
						<td>
							${company.relativeStaff.type}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="staff.status.title"/>:
						</td>
						<td>
							${company.relativeStaff.status}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="staff.level.title"/>:
						</td>
						<td>
							${company.relativeStaff.level}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="staff.loginname.title"/>:
						</td>
						<td>
							${company.relativeStaff.loginname}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="staff.password.title"/>:
						</td>
						<td>
							${company.relativeStaff.password}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="staff.remark.title"/>:
						</td>
						<td>
							${company.relativeStaff.remark}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>

			<h1><fmt:message key="projects.title"/></h1>
			<form action="${pageContext.request.contextPath}/selectCompany?id=${company.id}&projectPage=eachPageShow&staffPage=${staffPage}&staffPageSize=${staffPageSize}&staffWantToPage=${staffWantToPage}" method="post">
				选择每页显示<input type="text" id="projectPageSize" name="projectPageSize" style="height:15px;width:50px">条 
				<input type="submit" id="sure" value="确认" style="height:22px;width:35px">
			</form>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead"><fmt:message key="project.id.title"/></th>
						<th class="thead"><fmt:message key="project.name.title"/></th>
						<th class="thead"><fmt:message key="project.type.title"/></th>
						<th class="thead"><fmt:message key="project.status.title"/></th>
						<th class="thead"><fmt:message key="project.province.title"/></th>
						<th class="thead"><fmt:message key="project.city.title"/></th>
						<th class="thead"><fmt:message key="project.area.title"/></th>
						<th class="thead"><fmt:message key="project.address.title"/></th>
						<th class="thead"><fmt:message key="project.detail.title"/></th>
						<th class="thead"><fmt:message key="project.remark.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${projects}" var="current"  varStatus="i">
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
							${current.type}
						&nbsp;
						</td>
						<td>
							${current.status}
						&nbsp;
						</td>
						<td>
							${current.province}
						&nbsp;
						</td>
						<td>
							${current.city}
						&nbsp;
						</td>
						<td>
							${current.area}
						&nbsp;
						</td>
						<td>
							${current.address}
						&nbsp;
						</td>
						<td>
							${current.detail}
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
				<td><a  href="${pageContext.request.contextPath}/selectCompany?id=${company.id}&projectPage=homePage&staffPage=${staffPage}&staffPageSize=${staffPageSize}&staffWantToPage=${staffWantToPage}">首页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectCompany?id=${company.id}&projectPage=previousPage&staffPage=${staffPage}&staffPageSize=${staffPageSize}&staffWantToPage=${staffWantToPage}">上一页</a></td>
				<c:if test="${projectTotalPage>=7}">
				<c:if test="${projectPageNumber-2>0&&projectPageNumber+4<=projectTotalPage}">
				<%String pageCount2 = request.getAttribute("projectPageNumber").toString();
				int pageCount = Integer.parseInt(pageCount2);
				for(int i=pageCount-2;i<pageCount+5;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectCompany?id=${company.id}&projectPage=jumpPage&projectWantToPage=<%=i%>&staffPage=${staffPage}&staffPageSize=${staffPageSize}&staffWantToPage=${staffWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<c:if test="${projectPageNumber-2<=0}">
				<%for(int i=1;i<8;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectCompany?id=${company.id}&projectPage=jumpPage&projectWantToPage=<%=i%>&staffPage=${staffPage}&staffPageSize=${staffPageSize}&staffWantToPage=${staffWantToPage}"><%=i%></a></td>
				<%}%>
			    </c:if>
			    <c:if test="${projectPageNumber+4>totalPage }">
			     <%String pageCount2 = request.getAttribute("projectTotalPage").toString();
				int pageCount = Integer.parseInt(pageCount2);
			   	for(int i=pageCount-5;i<pageCount+2;i++){%>
			    <td><a  href="${pageContext.request.contextPath}/selectCompany?id=${company.id}&projectPage=jumpPage&projectWantToPage=<%=i%>&staffPage=${staffPage}&staffPageSize=${staffPageSize}&staffWantToPage=${staffWantToPage}"><%=i%></a></td>
			   <%}%>
			   	</c:if>
			    </c:if>				
				<c:if test="${projectTotalPage<7}">
				<% 
						String pageCount2 = request.getAttribute("projectTotalPage").toString();
						int pageCount = Integer.parseInt(pageCount2);
						for (int i = 1; i <= pageCount+1; i++) {
				%>
				 <td><a href="${pageContext.request.contextPath}/selectCompany?id=${company.id}&projectPage=jumpPage&projectWantToPage=<%=i%>&staffPage=${staffPage}&staffPageSize=${staffPageSize}&staffWantToPage=${staffWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<td><a  href="${pageContext.request.contextPath}/selectCompany?id=${company.id}&projectPage=nextPage&staffPage=${staffPage}&staffPageSize=${staffPageSize}&staffWantToPage=${staffWantToPage}">下一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectCompany?id=${company.id}&projectPage=lastPage&staffPage=${staffPage}&staffPageSize=${staffPageSize}&staffWantToPage=${staffWantToPage}">尾页</a></td>
				</tr><br>
				当前为第${projectPageNumber+1}页         每页显示${projectPageSize}条      总共${projectTotalPage+1}页<br>
				<form action="${pageContext.request.contextPath}/selectCompany?id=${company.id}&projectPage=jumpPage&staffPage=${staffPage}&staffPageSize=${staffPageSize}&staffWantToPage=${staffWantToPage}" method="post">
					跳转到第<input type="text" id="projectWantToPage" name="projectWantToPage" style="height:15px;width:100px">页 
					<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
				</form>
			<h1><fmt:message key="staffs.title"/></h1>
			<form action="${pageContext.request.contextPath}/selectCompany?id=${company.id}&staffPage=eachPageShow&projectPage=${projectPage}&projectPageSize=${projectPageSize}&projectWantToPage=${projectWantToPage}" method="post">
				选择每页显示<input type="text" id="staffPageSize" name="staffPageSize" style="height:15px;width:50px">条 
				<input type="submit" id="sure" value="确认" style="height:22px;width:35px">
			</form>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead"><fmt:message key="staff.id.title"/></th>
						<th class="thead"><fmt:message key="staff.name.title"/></th>
						<th class="thead"><fmt:message key="staff.duty.title"/></th>
						<th class="thead"><fmt:message key="staff.token.title"/></th>
						<th class="thead"><fmt:message key="staff.type.title"/></th>
						<th class="thead"><fmt:message key="staff.status.title"/></th>
						<th class="thead"><fmt:message key="staff.level.title"/></th>
						<th class="thead"><fmt:message key="staff.loginname.title"/></th>
						<th class="thead"><fmt:message key="staff.password.title"/></th>
						<th class="thead"><fmt:message key="staff.remark.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${staffs}" var="current"  varStatus="i">
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
							${current.duty}
						&nbsp;
						</td>
						<td>
							${current.token}
						&nbsp;
						</td>
						<td>
							${current.type}
						&nbsp;
						</td>
						<td>
							${current.status}
						&nbsp;
						</td>
						<td>
							${current.level}
						&nbsp;
						</td>
						<td>
							${current.loginname}
						&nbsp;
						</td>
						<td>
							${current.password}
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
				<td><a  href="${pageContext.request.contextPath}/selectCompany?id=${company.id}&staffPage=homePage&projectPage=${projectPage}&projectPageSize=${projectPageSize}&projectWantToPage=${projectWantToPage}">首页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectCompany?id=${company.id}&staffPage=previousPage&projectPage=${projectPage}&projectPageSize=${projectPageSize}&projectWantToPage=${projectWantToPage}">上一页</a></td>
				<c:if test="${staffTotalPage>=7}">
				<c:if test="${staffPageNumber-2>0&&staffPageNumber+4<=staffTotalPage}">
				<%String pageCount2 = request.getAttribute("staffPageNumber").toString();
				int pageCount = Integer.parseInt(pageCount2);
				for(int i=pageCount-2;i<pageCount+5;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectCompany?id=${company.id}&staffPage=jumpPage&staffWantToPage=<%=i%>&projectPage=${projectPage}&projectPageSize=${projectPageSize}&projectWantToPage=${projectWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<c:if test="${staffPageNumber-2<=0}">
				<%for(int i=1;i<8;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectCompany?id=${company.id}&staffPage=jumpPage&staffWantToPage=<%=i%>&projectPage=${projectPage}&projectPageSize=${projectPageSize}&projectWantToPage=${projectWantToPage}"><%=i%></a></td>
				<%}%>
			    </c:if>
			    <c:if test="${staffPageNumber+4>totalPage }">
			     <%String pageCount2 = request.getAttribute("staffTotalPage").toString();
				int pageCount = Integer.parseInt(pageCount2);
			   	for(int i=pageCount-5;i<pageCount+2;i++){%>
			    <td><a  href="${pageContext.request.contextPath}/selectCompany?id=${company.id}&staffPage=jumpPage&staffWantToPage=<%=i%>&projectPage=${projectPage}&projectPageSize=${projectPageSize}&projectWantToPage=${projectWantToPage}"><%=i%></a></td>
			   <%}%>
			   	</c:if>
			    </c:if>				
				<c:if test="${staffTotalPage<7}">
				<% 
						String pageCount2 = request.getAttribute("staffTotalPage").toString();
						int pageCount = Integer.parseInt(pageCount2);
						for (int i = 1; i <= pageCount+1; i++) {
				%>
				 <td><a href="${pageContext.request.contextPath}/selectCompany?id=${company.id}&staffPage=jumpPage&staffWantToPage=<%=i%>&projectPage=${projectPage}&projectPageSize=${projectPageSize}&projectWantToPage=${projectWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<td><a  href="${pageContext.request.contextPath}/selectCompany?id=${company.id}&staffPage=nextPage&projectPage=${projectPage}&projectPageSize=${projectPageSize}&projectWantToPage=${projectWantToPage}">下一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectCompany?id=${company.id}&staffPage=lastPage&projectPage=${projectPage}&projectPageSize=${projectPageSize}&projectWantToPage=${projectWantToPage}">尾页</a></td>
				</tr><br>
				当前为第${staffPageNumber+1}页         每页显示${staffPageSize}条      总共${staffTotalPage+1}页<br>
				<form action="${pageContext.request.contextPath}/selectCompany?id=${company.id}&staffPage=jumpPage&projectPage=${projectPage}&projectPageSize=${projectPageSize}&projectWantToPage=${projectWantToPage}" method="post">
					跳转到第<input type="text" id="staffWantToPage" name="staffWantToPage" style="height:15px;width:100px">页 
					<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
				</form>
			<div class="clear">&nbsp;</div>
		</div>
	</div>
</body>
</html>
