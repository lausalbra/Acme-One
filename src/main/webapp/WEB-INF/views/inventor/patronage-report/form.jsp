<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
    <acme:input-textbox code="inventor.patronage-report.form.label.sequence-number" path="sequenceNumber"/>
    <acme:input-textarea code="inventor.patronage-report.form.label.creation-moment" path="creationMoment"/>
    <acme:input-money code="inventor.patronage-report.form.label.memorandum" path="memorandum"/>
    <acme:input-url code="inventor.patronage-report.form.label.link" path="link"/>
</acme:form>