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
											<h1>
												<fmt:message key="navigation.view" />
												<fmt:message key="whbuilding.title" />
											</h1>
											<div class="navitem">
												<a class="button"
													href="${pageContext.request.contextPath}/indexProject?page=homePage"><span><img
														src="images/icons/back.gif" />
													<fmt:message key="navigation.back" /></span></a>
											</div>

								<form action="${pageContext.request.contextPath}/selectProjectWhbuildings?project_id=${project.id}&page=eachPageShow" method="post">
									选择每页显示<input type="text" id="pageSize" name="pageSize" style="height:15px;width:50px">条 
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
												<td>${current.id}&nbsp;</td>
												<td>${current.name}&nbsp;</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
										<tr>
											<td><a  href="${pageContext.request.contextPath}/selectProjectWhbuildings?project_id=${project.id}&page=homePage">首页</a></td>
											<td><a  href="${pageContext.request.contextPath}/selectProjectWhbuildings?project_id=${project.id}&page=previousPage">上一页</a></td>
											<c:if test="${whbuildingTotalPage>=7}">
											<c:if test="${whbuildingPageNumber-2>0&&whbuildingPageNumber+4<=whbuildingTotalPage}">
											<%String pageCount2 = request.getAttribute("whbuildingPageNumber").toString();
											int pageCount = Integer.parseInt(pageCount2);
											for(int i=pageCount-2;i<pageCount+5;i++){%>
											<td><a  href="${pageContext.request.contextPath}/selectProjectWhbuildings?project_id=${project.id}&page=jumpPage&WantToPage=<%=i%>"><%=i%></a></td>
											<%}%>
										</c:if>
										<c:if test="${whbuildingPageNumber-2<=0}">
										 <%for(int i=1;i<8;i++){%>
											<td><a  href="${pageContext.request.contextPath}/selectProjectWhbuildings?project_id=${project.id}&page=jumpPage&WantToPage=<%=i%>"><%=i%></a></td>
										<%}%>
			   							</c:if>		    
			   							 <c:if test="${whbuildingPageNumber+4>whbuildingTotalPage }">
			   							 <%String pageCount2 = request.getAttribute("whbuildingTotalPage").toString();
											int pageCount = Integer.parseInt(pageCount2);
			   							 for(int i=pageCount-5;i<pageCount+2;i++){%>
			   							 <td><a  href="${pageContext.request.contextPath}/selectProjectWhbuildings?project_id=${project.id}&page=jumpPage&WantToPage=<%=i%>"><%=i%></a></td>
			    						<%}%>
			   							 </c:if>
			   							 </c:if>			
										<c:if test="${whbuildingTotalPage<7}">
										<% 
											String pageCount2 = request.getAttribute("whbuildingTotalPage").toString();
											int pageCount = Integer.parseInt(pageCount2);
											for (int i = 1; i <= pageCount+1; i++) {%>
										 <td><a href="${pageContext.request.contextPath}/selectProjectWhbuildings?project_id=${project.id}&page=jumpPage&WantToPage=<%=i%>"><%=i%></a></td>
										<%}%>
										</c:if>
										<td><a  href="${pageContext.request.contextPath}/selectProjectWhbuildings?project_id=${project.id}&page=nextPage">下一页</a></td>
										<td><a  href="${pageContext.request.contextPath}/selectProjectWhbuildings?project_id=${project.id}&page=lastPage">尾页</a> 	</td>
								</tr><br>
									当前为第${whbuildingPageNumber+1}页         每页显示${whbuildingPageSize}条      总共${whbuildingTotalPage+1}页<br>
									<form action="${pageContext.request.contextPath}/selectProjectWhbuildings?project_id=${project.id}&page=jumpPage" method="post">
									跳转到第<input type="text" id="WantToPage" name="WantToPage" style="height:15px;width:100px">页 
									<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
								</form>

		</div>
	</div>
</body>

</html>