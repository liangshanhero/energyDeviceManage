<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.cacdevicedata-resources" />
<html>
<head>
<title>View<fmt:message key="cacdevicedata.title"/></title>
</head>
<body>
	<div id="contentarea">
		<div id="content">
			<h1><fmt:message key="cacdevicedata.title"/>Details</h1>
			<h1><fmt:message key="cacdevicedata.title"/>Details</h1>
<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexCacdevicedata?page=homePage"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
				<table cellpadding="0" cellspacing="0" id="viewTable">
					<tbody>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="cacdevicedata.cacdevice.title"/>:
							</td>
							<td>
							${cacdevicedata.cacdevice}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="cacdevicedata.cacrecordtime.title"/>:
							</td>
							<td>
							${cacdevicedata.cacrecordtime}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="cacdevicedata.value.title"/>:
							</td>
							<td>${cacdevicedata.value}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="cacdevicedata.isreport.title"/>:
							</td>
							<td>${cacdevicedata.isreport}&nbsp;</td>
						</tr>
				</tbody>
			</table>
			<h1><fmt:message key="cacdevice.title"/></h1>
			<c:if test='${cacdevicedata.relativeCacdevice != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="cacdevice.id.title"/>:
						</td>
						<td>
							${cacdevicedata.relativeCacdevice.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="cacdevice.name.title"/>:
						</td>
						<td>
							${cacdevicedata.relativeCacdevice.name}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>
			<h1><fmt:message key="cacrecordtime.title"/></h1>
			<c:if test='${cacdevicedata.relativeCacrecordtime != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="cacrecordtime.id.title"/>:
						</td>
						<td>
							${cacdevicedata.relativeCacrecordtime.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="cacrecordtime.RecordTime.title"/>:
						</td>
						<td>
							${cacdevicedata.relativeCacrecordtime.recordTime}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="cacrecordtime.Watchkeeper.title"/>:
						</td>
						<td>
							${cacdevicedata.relativeCacrecordtime.watchkeeper}
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
