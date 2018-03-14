<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.whstrategydetail-resources" />
<html>
<head>
<title>View<fmt:message key="whstrategydetail.title"/></title>
</head>
<body>
	<div id="contentarea">
		<div id="content">
			<h1><fmt:message key="whstrategydetail.title"/>Details</h1>
			<h1><fmt:message key="whstrategydetail.title"/>Details</h1>
<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexWhstrategydetail?page=homePage"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
				<table cellpadding="0" cellspacing="0" id="viewTable">
					<tbody>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="whstrategydetail.max.title"/>:
							</td>
							<td>${whstrategydetail.max}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="whstrategydetail.min.title"/>:
							</td>
							<td>${whstrategydetail.min}&nbsp;</td>
						</tr>
				</tbody>
			</table>
			<h1><fmt:message key="whstrategy.title"/></h1>
			<c:if test='${whstrategydetail.whstrategy != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="whstrategy.enable.title"/>:
						</td>
						<td>
							${whstrategydetail.whstrategy.enable}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="whstrategy.createDate.title"/>:
						</td>
						<td>
							${whstrategydetail.whstrategy.createDate}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="whstrategy.remark.title"/>:
						</td>
						<td>
							${whstrategydetail.whstrategy.remark}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>
			<h1><fmt:message key="whstrategytype.title"/></h1>
			<c:if test='${whstrategydetail.whstrategytype != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="whstrategytype.name.title"/>:
						</td>
						<td>
							${whstrategydetail.whstrategytype.name}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>

			<div class="clear">&nbsp;</div>
		</div>
	</div>
</body>
</html>
