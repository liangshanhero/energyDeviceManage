<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.cacrecordtime-resources" />
<html>
<head>
<title>View<fmt:message key="cacrecordtime.title"/></title>
</head>


<body>
	<div id="contentarea">
										<div id="content">
											<h1>
												<fmt:message key="navigation.view" />
												<fmt:message key="cacsensordata.title" />
											</h1>
											<div class="navitem">
												<a class="button"
													href="${pageContext.request.contextPath}/indexCacrecordtime?page=homePage"><span><img
														src="images/icons/back.gif" />
													<fmt:message key="navigation.back" /></span></a>
											</div>

								<form action="${pageContext.request.contextPath}/selectCacrecordtimeCacsensordatas?cacrecordtime_id=${cacrecordtime.id}&page=eachPageShow" method="post">
									选择每页显示<input type="text" id="pageSize" name="pageSize" style="height:15px;width:50px">条 
									<input type="submit" id="sure" value="确认" style="height:22px;width:35px">
								</form>
								<table cellpadding="0" cellspacing="0" id="viewTable">
												<thead>
													<tr>
														<th class="thead"><fmt:message key="cacsensordata.cacrecordtime.title"/></th>
														<th class="thead"><fmt:message key="cacsensordata.cacsensor.title"/></th>
														<th class="thead"><fmt:message key="cacsensordata.value.title"/></th>
														<th class="thead"><fmt:message key="cacsensordata.isreport.title"/></th>
													</tr>
												</thead>
											<tbody>
												<c:forEach items="${cacsensordatas}" var="current"  varStatus="i">	
												<c:choose>
												<c:when test="${(i.count) % 2 == 0}">
					    						<c:set var="rowclass" value="rowtwo"/>
												</c:when>
												<c:otherwise>
					    						<c:set var="rowclass" value="rowone"/>
												</c:otherwise>
												</c:choose>
												<tr class="${rowclass}">
												<td>${current.cacrecordtime}&nbsp;</td>
												<td>${current.cacsensor}&nbsp;</td>
												<td>${current.value}&nbsp;</td>
												<td>${current.isreport}&nbsp;</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
										<tr>
											<td><a  href="${pageContext.request.contextPath}/selectCacrecordtimeCacsensordatas?cacrecordtime_id=${cacrecordtime.id}&page=homePage">首页</a></td>
											<td><a  href="${pageContext.request.contextPath}/selectCacrecordtimeCacsensordatas?cacrecordtime_id=${cacrecordtime.id}&page=previousPage">上一页</a></td>
											<c:if test="${cacsensordataTotalPage>=7}">
											<c:if test="${cacsensordataPageNumber-2>0&&cacsensordataPageNumber+4<=cacsensordataTotalPage}">
											<%String pageCount2 = request.getAttribute("cacsensordataPageNumber").toString();
											int pageCount = Integer.parseInt(pageCount2);
											for(int i=pageCount-2;i<pageCount+5;i++){%>
											<td><a  href="${pageContext.request.contextPath}/selectCacrecordtimeCacsensordatas?cacrecordtime_id=${cacrecordtime.id}&page=jumpPage&WantToPage=<%=i%>"><%=i%></a></td>
											<%}%>
										</c:if>
										<c:if test="${cacsensordataPageNumber-2<=0}">
										 <%for(int i=1;i<8;i++){%>
											<td><a  href="${pageContext.request.contextPath}/selectCacrecordtimeCacsensordatas?cacrecordtime_id=${cacrecordtime.id}&page=jumpPage&WantToPage=<%=i%>"><%=i%></a></td>
										<%}%>
			   							</c:if>		    
			   							 <c:if test="${cacsensordataPageNumber+4>cacsensordataTotalPage }">
			   							 <%String pageCount2 = request.getAttribute("cacsensordataTotalPage").toString();
											int pageCount = Integer.parseInt(pageCount2);
			   							 for(int i=pageCount-5;i<pageCount+2;i++){%>
			   							 <td><a  href="${pageContext.request.contextPath}/selectCacrecordtimeCacsensordatas?cacrecordtime_id=${cacrecordtime.id}&page=jumpPage&WantToPage=<%=i%>"><%=i%></a></td>
			    						<%}%>
			   							 </c:if>
			   							 </c:if>			
										<c:if test="${cacsensordataTotalPage<7}">
										<% 
											String pageCount2 = request.getAttribute("cacsensordataTotalPage").toString();
											int pageCount = Integer.parseInt(pageCount2);
											for (int i = 1; i <= pageCount+1; i++) {%>
										 <td><a href="${pageContext.request.contextPath}/selectCacrecordtimeCacsensordatas?cacrecordtime_id=${cacrecordtime.id}&page=jumpPage&WantToPage=<%=i%>"><%=i%></a></td>
										<%}%>
										</c:if>
										<td><a  href="${pageContext.request.contextPath}/selectCacrecordtimeCacsensordatas?cacrecordtime_id=${cacrecordtime.id}&page=nextPage">下一页</a></td>
										<td><a  href="${pageContext.request.contextPath}/selectCacrecordtimeCacsensordatas?cacrecordtime_id=${cacrecordtime.id}&page=lastPage">尾页</a> 	</td>
								</tr><br>
									当前为第${cacsensordataPageNumber+1}页         每页显示${cacsensordataPageSize}条      总共${cacsensordataTotalPage+1}页<br>
									<form action="${pageContext.request.contextPath}/selectCacrecordtimeCacsensordatas?cacrecordtime_id=${cacrecordtime.id}&page=jumpPage" method="post">
									跳转到第<input type="text" id="WantToPage" name="WantToPage" style="height:15px;width:100px">页 
									<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
								</form>

		</div>
	</div>
</body>

</html>