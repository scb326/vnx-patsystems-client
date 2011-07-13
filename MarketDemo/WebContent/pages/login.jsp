<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VNX - Login</title>

<link rel="stylesheet" type="text/css" href="../css/stylesheet.css">
<script type=text/javascript language=java>
  
  function submitLogin(frm) {
  
    if (frm.userId.value == null || frm.userId.value.length == 0) {
      frm.userId.style.backgroundColor = "#FF0000";
      alert("Required field 'User ID' must be not empty.");
    } else {
       window.open('orderlist.jsp','_self');
      //frm.submit();
    }
  }
</script>
<style type="text/css">
.colStyle1 { width: 25%; text-align: left; height: 5px;
}
.colStyle2 { width: 75%; text-align: left; height: 5px;
}
</style>
</head>
<body>
	<f:view>
		<f:loadBundle var="msg" basename="MessageResources"/>
		<div align="center">
			<h:form id="mainForm">
				<h:panelGrid id="headerPanel" border="0" columns="1" width="50%" cellpadding="0">
					<h:outputText id="txtHeader" value="#{msg['vnx.name']}" styleClass="title"></h:outputText>
				</h:panelGrid>
				<br/>
				<h:panelGrid id="loginPanel" border="0" columns="2" columnClasses="colStyle1,colStyle2" width="50%" cellpadding="0">
					<h:panelGroup>
						<h:outputLabel id="lblUserId" value="#{msg['login.jsp.userid']}"></h:outputLabel>
						<h:outputLabel id="lblUserId2" value=" *" style="color: #FF0000;"></h:outputLabel>
					</h:panelGroup>
					<h:inputText id="txtUserId"></h:inputText>
					<h:outputLabel id="lblPassword" value="#{msg['login.jsp.password']}"></h:outputLabel>
					<h:inputSecret id="txtPassword"></h:inputSecret>
					<h:outputText id="temp1" value=""></h:outputText>
					<h:outputText id="temp2" value=""></h:outputText>
					<h:outputText id="temp3" value=""></h:outputText>
					<h:commandButton id="btnLogin" value="#{msg['login.jsp.loginbutton']}" action="orderlist"></h:commandButton>
				</h:panelGrid>
			</h:form>
		</div>
	</f:view>
</body>
</html>