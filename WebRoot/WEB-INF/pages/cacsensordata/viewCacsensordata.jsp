<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.cacsensordata-resources" />
<html>
<head>
<title>View<fmt:message key="cacsensordata.title"/></title>
</head>
<body>
	<div id="contentarea">
		<div id="content">
			<h1><fmt:message key="cacsensordata.title"/>Details</h1>
			<h1><fmt:message key="cacsensordata.title"/>Details</h1>
<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexCacsensordata?page=homePage"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
				<table cellpadding="0" cellspacing="0" id="viewTable">
					<tbody>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="cacsensordata.cacrecordtime.title"/>:
							</td>
							<td>
							${cacsensordata.cacrecordtime}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="cacsensordata.cacsensor.title"/>:
							</td>
							<td>
							${cacsensordata.cacsensor}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="cacsensordata.value.title"/>:
							</td>
							<td>${cacsensordata.value}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="cacsensordata.isreport.title"/>:
							</td>
							<td>${cacsensordata.isreport}&nbsp;</td>
						</tr>
				</tbody>
			</table>
			<h1><fmt:message key="cacrecordtime.title"/></h1>
			<c:if test='${cacsensordata.relativeCacrecordtime != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="cacrecordtime.id.title"/>:
						</td>
						<td>
							${cacsensordata.relativeCacrecordtime.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="cacrecordtime.RecordTime.title"/>:
						</td>
						<td>
							${cacsensordata.relativeCacrecordtime.recordTime}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="cacrecordtime.Watchkeeper.title"/>:
						</td>
						<td>
							${cacsensordata.relativeCacrecordtime.watchkeeper}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>
			<h1><fmt:message key="cacsensor.title"/></h1>
			<c:if test='${cacsensordata.relativeCacsensor != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="cacsensor.id.title"/>:
						</td>
						<td>
							${cacsensordata.relativeCacsensor.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="cacsensor.name.title"/>:
						</td>
						<td>
							${cacsensordata.relativeCacsensor.name}
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
