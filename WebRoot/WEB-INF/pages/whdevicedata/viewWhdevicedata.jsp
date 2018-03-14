<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.whdevicedata-resources" />
<html>
<head>
<title>View<fmt:message key="whdevicedata.title"/></title>
</head>
<body>
	<div id="contentarea">
		<div id="content">
			<h1><fmt:message key="whdevicedata.title"/>Details</h1>
			<h1><fmt:message key="whdevicedata.title"/>Details</h1>
<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexWhdevicedata?page=homePage"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
				<table cellpadding="0" cellspacing="0" id="viewTable">
					<tbody>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="whdevicedata.id.title"/>:
							</td>
							<td>
							${whdevicedata.id}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="whdevicedata.time.title"/>:
							</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${whdevicedata.time}"/>&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="whdevicedata.value.title"/>:
							</td>
							<td>${whdevicedata.value}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="whdevicedata.isupdate.title"/>:
							</td>
							<td>${whdevicedata.isupdate}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="whdevicedata.isio.title"/>:
							</td>
							<td>${whdevicedata.isio}&nbsp;</td>
						</tr>
				</tbody>
			</table>
			<h1><fmt:message key="whdatatype.title"/></h1>
			<c:if test='${whdevicedata.relativeWhdatatype != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="whdatatype.id.title"/>:
						</td>
						<td>
							${whdevicedata.relativeWhdatatype.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="whdatatype.name.title"/>:
						</td>
						<td>
							${whdevicedata.relativeWhdatatype.name}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>
			<h1><fmt:message key="whdevice.title"/></h1>
			<c:if test='${whdevicedata.relativeWhdevice != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="whdevice.id.title"/>:
						</td>
						<td>
							${whdevicedata.relativeWhdevice.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="whdevice.number.title"/>:
						</td>
						<td>
							${whdevicedata.relativeWhdevice.number}
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
