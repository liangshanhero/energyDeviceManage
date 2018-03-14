<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.cacmalfunction-resources" />
<html>
<head>
<title>View<fmt:message key="cacmalfunction.title"/></title>
</head>
<body>
	<div id="contentarea">
		<div id="content">
			<h1><fmt:message key="cacmalfunction.title"/>Details</h1>
			<h1><fmt:message key="cacmalfunction.title"/>Details</h1>
<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexCacmalfunction?page=homePage"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
				<table cellpadding="0" cellspacing="0" id="viewTable">
					<tbody>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="cacmalfunction.cacrecordtime.title"/>:
							</td>
							<td>
							${cacmalfunction.cacrecordtime}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="cacmalfunction.cacdevice.title"/>:
							</td>
							<td>
							${cacmalfunction.cacdevice}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="cacmalfunction.status.title"/>:
							</td>
							<td>${cacmalfunction.status}&nbsp;</td>
						</tr>
				</tbody>
			</table>
			<h1><fmt:message key="cacdevice.title"/></h1>
			<c:if test='${cacmalfunction.relativeCacdevice != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="cacdevice.id.title"/>:
						</td>
						<td>
							${cacmalfunction.relativeCacdevice.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="cacdevice.name.title"/>:
						</td>
						<td>
							${cacmalfunction.relativeCacdevice.name}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>
			<h1><fmt:message key="cacrecordtime.title"/></h1>
			<c:if test='${cacmalfunction.relativeCacrecordtime != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="cacrecordtime.id.title"/>:
						</td>
						<td>
							${cacmalfunction.relativeCacrecordtime.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="cacrecordtime.RecordTime.title"/>:
						</td>
						<td>
							${cacmalfunction.relativeCacrecordtime.recordTime}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="cacrecordtime.Watchkeeper.title"/>:
						</td>
						<td>
							${cacmalfunction.relativeCacrecordtime.watchkeeper}
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
