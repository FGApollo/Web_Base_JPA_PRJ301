<%-- 
    Document   : createNewAccount
    Created on : 30-06-2025, 15:33:48
    Author     : FGApollo
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="DispatchServlet" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
            Username <input type="text" name="txtUsername" 
                            value="${param.txtUsername}" /> (6-12 character) <br/>
            <c:if test="${not empty errors.usernameLengthErr}">
                <font color = "red">
                    ${errors.usernameLengthErr}
                </font><br>     
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color = "red">
                    ${errors.usernameIsExisted}
                </font><br>     
            </c:if>
            Password <input type="password" name="txtPassword" value="" />(8-20 character)<br/>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color = "red">
                    ${errors.passwordLengthErr}
                </font><br>     
            </c:if>
                Confirm <input type="text" name="txtConfirm" value="" /> <br/>
            <c:if test="${not empty errors.conformNotMatch}">
                <font color = "red">
                    ${errors.conformNotMatch}
                </font><br>     
            </c:if>
            Fullname <input type="text" name="txtFullname" 
                            value="${param.txtFullname}" /> (2-40 character)<br/>
            <c:if test="${not empty errors.fullNameLengErr}">
                <font color = "red">
                    ${errors.fullNameLengErr}
                </font><br>     
            </c:if>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="reset" />
        </form>

    </body>
</html>
