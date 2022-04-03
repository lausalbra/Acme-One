<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
    <acme:list-column code="any.item.list.label.name" path="name" width="30%"/>
    <acme:list-column code="any.item.list.label.code" path="code" width="10%"/>
    <acme:list-column code="any.item.list.label.technology" path="technology" width="20%"/>
    <acme:list-column code="any.item.list.label.retailPrice" path="retailPrice" width="10%"/>
    <acme:list-column code="any.item.list.label.inventor" path="inventor.userAccount.identity.fullName" width="10%"/>
</acme:list>