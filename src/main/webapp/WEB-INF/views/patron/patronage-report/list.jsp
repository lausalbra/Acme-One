<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
    <acme:list-column code="patron.patronage-report.list.label.sequence-number" path="sequenceNumber" width="70%"/>
    <acme:list-column code="patron.patronage-report.list.label.creation-moment" path="creationMoment" width="20%"/>
</acme:list>