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
											<h1>
												<fmt:message key="navigation.view" />
												<fmt:message key="ledmeter.title" />
											</h1>
											<div class="navitem">
												<a class="button"
													href="${pageContext.request.contextPath}/indexLedbuilding?page=homePage"><span><img
														src="images/icons/back.gif" />
													<fmt:message key="navigation.back" /></span></a>
											</div>

								<form action="${pageContext.request.contextPath}/selectLedbuildingLedmeters?ledbuilding_id=${ledbuilding.id}&page=eachPageShow" method="post">
									选择每页显示<input type="text" id="pageSize" name="pageSize" style="height:15px;width:50px">条 
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
												<td>${current.id}&nbsp;</td>
												<td>${current.number}&nbsp;</td>
												<td>${current.well}&nbsp;</td>
												<td>${current.storey}&nbsp;</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
										<tr>
											<td><a  href="${pageContext.request.contextPath}/selectLedbuildingLedmeters?ledbuilding_id=${ledbuilding.id}&page=homePage">首页</a></td>
											<td><a  href="${pageContext.request.contextPath}/selectLedbuildingLedmeters?ledbuilding_id=${ledbuilding.id}&page=previousPage">上一页</a></td>
											<c:if test="${ledmeterTotalPage>=7}">
											<c:if test="${ledmeterPageNumber-2>0&&ledmeterPageNumber+4<=ledmeterTotalPage}">
											<%String pageCount2 = request.getAttribute("ledmeterPageNumber").toString();
											int pageCount = Integer.parseInt(pageCount2);
											for(int i=pageCount-2;i<pageCount+5;i++){%>
											<td><a  href="${pageContext.request.contextPath}/selectLedbuildingLedmeters?ledbuilding_id=${ledbuilding.id}&page=jumpPage&WantToPage=<%=i%>"><%=i%></a></td>
											<%}%>
										</c:if>
										<c:if test="${ledmeterPageNumber-2<=0}">
										 <%for(int i=1;i<8;i++){%>
											<td><a  href="${pageContext.request.contextPath}/selectLedbuildingLedmeters?ledbuilding_id=${ledbuilding.id}&page=jumpPage&WantToPage=<%=i%>"><%=i%></a></td>
										<%}%>
			   							</c:if>		    
			   							 <c:if test="${ledmeterPageNumber+4>ledmeterTotalPage }">
			   							 <%String pageCount2 = request.getAttribute("ledmeterTotalPage").toString();
											int pageCount = Integer.parseInt(pageCount2);
			   							 for(int i=pageCount-5;i<pageCount+2;i++){%>
			   							 <td><a  href="${pageContext.request.contextPath}/selectLedbuildingLedmeters?ledbuilding_id=${ledbuilding.id}&page=jumpPage&WantToPage=<%=i%>"><%=i%></a></td>
			    						<%}%>
			   							 </c:if>
			   							 </c:if>			
										<c:if test="${ledmeterTotalPage<7}">
										<% 
											String pageCount2 = request.getAttribute("ledmeterTotalPage").toString();
											int pageCount = Integer.parseInt(pageCount2);
											for (int i = 1; i <= pageCount+1; i++) {%>
										 <td><a href="${pageContext.request.contextPath}/selectLedbuildingLedmeters?ledbuilding_id=${ledbuilding.id}&page=jumpPage&WantToPage=<%=i%>"><%=i%></a></td>
										<%}%>
										</c:if>
										<td><a  href="${pageContext.request.contextPath}/selectLedbuildingLedmeters?ledbuilding_id=${ledbuilding.id}&page=nextPage">下一页</a></td>
										<td><a  href="${pageContext.request.contextPath}/selectLedbuildingLedmeters?ledbuilding_id=${ledbuilding.id}&page=lastPage">尾页</a> 	</td>
								</tr><br>
									当前为第${ledmeterPageNumber+1}页         每页显示${ledmeterPageSize}条      总共${ledmeterTotalPage+1}页<br>
									<form action="${pageContext.request.contextPath}/selectLedbuildingLedmeters?ledbuilding_id=${ledbuilding.id}&page=jumpPage" method="post">
									跳转到第<input type="text" id="WantToPage" name="WantToPage" style="height:15px;width:100px">页 
									<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
								</form>

		</div>
	</div>
</body>

</html>