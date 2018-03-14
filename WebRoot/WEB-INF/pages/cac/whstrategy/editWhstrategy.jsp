<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.whstrategy-resources" />
<html>
<head>
<title>Edit<fmt:message key="whstrategy.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="navigation.edit" /><fmt:message key="whstrategy.title" /></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexWhstrategy?page=homePage"><span><img src="images/icons/back.gif" /> <fmt:message key="navigation.back" /></span></a></div>
		<form:form action="${pageContext.request.contextPath}/saveWhstrategy" method="POST" modelAttribute="whstrategy">
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="whstrategy.id.title" />:</td>
					<td>
						<c:choose>
						<c:when test='${newFlag}'>
						<form:input id="whstrategy_id" path="id" cssStyle="width:300px;" />
						</c:when>
						<c:otherwise>
						${whstrategy.id}&nbsp;
						<form:hidden id="whstrategy_id" path="id" />
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whstrategy.enable.title" />:</td>
					<td><form:input id="whstrategy_enable" path="enable" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whstrategy.createDate.title" />:</td>
					<td>
						<input id="whstrategy_createDate" name="createDate" type="text" value="<fmt:formatDate value="${whstrategy.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" style="width:300px;"/>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whstrategy.remark.title" />:</td>
					<td><form:input id="whstrategy_remark" path="remark" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whdevice.title" />:</td>
					<td>
						<option></option>
						<c:forEach items="${allWhdevices}" var="whdevice">
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td valign="top"><fmt:message key="whstrategydetails.title" />:</td>
					<td>
					 <c:forEach items="${allWhstrategydetails}" var="whstrategydetail">
					 <input type="checkbox" id="whstrategydetails" name="whstrategydetails" value="${whstrategydetail}"
					 <c:forEach items="${havedWhstrategydetails}" var="havedWhstrategydetail">
                	 <c:if test="${havedWhstrategydetail == whstrategydetail}">
                  	 checked="checked"
                	 </c:if>
               	 	 </c:forEach> />
				 	</c:forEach>
				</td>
				<c:if test="${editType=='new'}">
				<td><a  href="${pageContext.request.contextPath}/newWhstrategy?id=${whstrategy.id}&whstrategydetailPage=previousPage">上一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/newWhstrategy?id=${whstrategy.id}&whstrategydetailPage=nextPage">下一页</a></td>
				</c:if>
				<c:if test="${editType=='edit'}">
				<td><a  href="${pageContext.request.contextPath}/editWhstrategy?id=${whstrategy.id}&whstrategydetailPage=previousPage">上一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/editWhstrategy?id=${whstrategy.id}&whstrategydetailPage=nextPage">下一页</a></td>
				</c:if>
			</tr>
		</tbody>
		</table>
		<span class="inputbutton"><input class="savebutton" id="save" type="submit" value="<fmt:message key="navigation.save"/>" /></span>
		<script type="text/javascript">Spring.addDecoration(new Spring.ValidateAllDecoration({elementId : 'save',event : 'onclick'}));</script>
		</form:form>
		<div class="clear">&nbsp;</div>
	</div>
</body>
</html>
