<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.cacsensor-resources" />
<html>
<head>
<title>View<fmt:message key="cacsensor.title"/></title>
</head>
<body>
	<div id="contentarea">
		<div id="content">
			<h1><fmt:message key="cacsensor.title"/>Details</h1>
			<h1><fmt:message key="cacsensor.title"/>Details</h1>
<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexCacsensor?page=homePage"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
				<table cellpadding="0" cellspacing="0" id="viewTable">
					<tbody>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="cacsensor.id.title"/>:
							</td>
							<td>
							${cacsensor.id}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="cacsensor.name.title"/>:
							</td>
							<td>${cacsensor.name}&nbsp;</td>
						</tr>
				</tbody>
			</table>
			<h1><fmt:message key="cac.title"/></h1>
			<c:if test='${cacsensor.relativeCac != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="cac.id.title"/>:
						</td>
						<td>
							${cacsensor.relativeCac.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="cac.remark.title"/>:
						</td>
						<td>
							${cacsensor.relativeCac.remark}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>

			<h1><fmt:message key="cacsensordatas.title"/></h1>
			<form action="${pageContext.request.contextPath}/selectCacsensor?id=${cacsensor.id}&cacsensordataPage=eachPageShow" method="post">
				选择每页显示<input type="text" id="cacsensordataPageSize" name="cacsensordataPageSize" style="height:15px;width:50px">条 
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
						<td>
							${current.cacrecordtime}
						&nbsp;
						</td>
						<td>
							${current.cacsensor}
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
				<td><a  href="${pageContext.request.contextPath}/selectCacsensor?id=${cacsensor.id}&cacsensordataPage=homePage">首页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectCacsensor?id=${cacsensor.id}&cacsensordataPage=previousPage">上一页</a></td>
				<c:if test="${cacsensordataTotalPage>=7}">
				<c:if test="${cacsensordataPageNumber-2>0&&cacsensordataPageNumber+4<=cacsensordataTotalPage}">
				<%String pageCount2 = request.getAttribute("cacsensordataPageNumber").toString();
				int pageCount = Integer.parseInt(pageCount2);
				for(int i=pageCount-2;i<pageCount+5;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectCacsensor?id=${cacsensor.id}&cacsensordataPage=jumpPage&cacsensordataWantToPage=<%=i%>"><%=i%></a></td>
				<%}%>
				</c:if>
				<c:if test="${cacsensordataPageNumber-2<=0}">
				<%for(int i=1;i<8;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectCacsensor?id=${cacsensor.id}&cacsensordataPage=jumpPage&cacsensordataWantToPage=<%=i%>"><%=i%></a></td>
				<%}%>
			    </c:if>
			    <c:if test="${cacsensordataPageNumber+4>totalPage }">
			     <%String pageCount2 = request.getAttribute("cacsensordataTotalPage").toString();
				int pageCount = Integer.parseInt(pageCount2);
			   	for(int i=pageCount-5;i<pageCount+2;i++){%>
			    <td><a  href="${pageContext.request.contextPath}/selectCacsensor?id=${cacsensor.id}&cacsensordataPage=jumpPage&cacsensordataWantToPage=<%=i%>"><%=i%></a></td>
			   <%}%>
			   	</c:if>
			    </c:if>				
				<c:if test="${cacsensordataTotalPage<7}">
				<% 
						String pageCount2 = request.getAttribute("cacsensordataTotalPage").toString();
						int pageCount = Integer.parseInt(pageCount2);
						for (int i = 1; i <= pageCount+1; i++) {
				%>
				 <td><a href="${pageContext.request.contextPath}/selectCacsensor?id=${cacsensor.id}&cacsensordataPage=jumpPage&cacsensordataWantToPage=<%=i%>"><%=i%></a></td>
				<%}%>
				</c:if>
				<td><a  href="${pageContext.request.contextPath}/selectCacsensor?id=${cacsensor.id}&cacsensordataPage=nextPage">下一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectCacsensor?id=${cacsensor.id}&cacsensordataPage=lastPage">尾页</a></td>
				</tr><br>
				当前为第${cacsensordataPageNumber+1}页         每页显示${cacsensordataPageSize}条      总共${cacsensordataTotalPage+1}页<br>
				<form action="${pageContext.request.contextPath}/selectCacsensor?id=${cacsensor.id}&cacsensordataPage=jumpPage" method="post">
					跳转到第<input type="text" id="cacsensordataWantToPage" name="cacsensordataWantToPage" style="height:15px;width:100px">页 
					<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
				</form>
			<div class="clear">&nbsp;</div>
		</div>
	</div>
</body>
</html>
