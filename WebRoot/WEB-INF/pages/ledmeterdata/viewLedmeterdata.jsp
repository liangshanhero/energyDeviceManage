<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.ledmeterdata-resources" />
<html>
<head>
<title>View<fmt:message key="ledmeterdata.title"/></title>
</head>
<body>
	<div id="contentarea">
		<div id="content">
			<h1><fmt:message key="ledmeterdata.title"/>Details</h1>
			<h1><fmt:message key="ledmeterdata.title"/>Details</h1>
<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexLedmeterdata?page=homePage"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
				<table cellpadding="0" cellspacing="0" id="viewTable">
					<tbody>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="ledmeterdata.id.title"/>:
							</td>
							<td>
							${ledmeterdata.id}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="ledmeterdata.value.title"/>:
							</td>
							<td>${ledmeterdata.value}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="ledmeterdata.time.title"/>:
							</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${ledmeterdata.time}"/>&nbsp;</td>
						</tr>
				</tbody>
			</table>
			<h1><fmt:message key="ledmeter.title"/></h1>
			<c:if test='${ledmeterdata.relativeLedmeter != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="ledmeter.id.title"/>:
						</td>
						<td>
							${ledmeterdata.relativeLedmeter.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="ledmeter.number.title"/>:
						</td>
						<td>
							${ledmeterdata.relativeLedmeter.number}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="ledmeter.well.title"/>:
						</td>
						<td>
							${ledmeterdata.relativeLedmeter.well}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="ledmeter.storey.title"/>:
						</td>
						<td>
							${ledmeterdata.relativeLedmeter.storey}
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
