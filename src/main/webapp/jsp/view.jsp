<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.app.model.Experience" %>

<%
if(session.getAttribute("user") == null){
    response.sendRedirect("../login.jsp");
}
%>

<html>
<head>
<title>View Experiences</title>

<link rel="stylesheet"
href="<%=request.getContextPath()%>/css/styles.css">

</head>

<body>

<div class="container-large">

<div class="card">

<h2>View Interview Experiences</h2>

<!-- FILTER -->

<form action="<%=request.getContextPath()%>/viewExperience" method="get">

    Company:
    <input type="text" name="company"/>

    Candidate:
    <input type="text" name="candidate"/>

    Round:

    <select name="round">

        <option value="">All</option>
        <option>HR</option>
        <option>Technical</option>
        <option>Managerial</option>
        <option>Coding</option>

    </select>

    <button type="submit">Filter</button>

</form>

<br><br>

<table border="1">

<tr>

    <th>Company</th>
    <th>Candidate</th>
    <th>Round</th>
    <th>Experience</th>
    <th>Guidance</th>
    <th>Resources</th>
    <th>Actions</th>

</tr>

<%

List<Experience> list =
(List<Experience>)request.getAttribute("list");

if(list!=null){

for(Experience e:list){

%>

<tr>

<td><%=e.getCompany()%></td>

<td><%=e.getCandidateName()%></td>

<td><%=e.getRound()%></td>

<td><%=e.getExperienceText()%></td>

<td><%=e.getGuidance()%></td>

<td>

<!-- Material Link -->

<%

if(e.getMaterialLink()!=null &&
!e.getMaterialLink().trim().isEmpty()){

%>

<b>Material Link</b><br>

<a href="<%=e.getMaterialLink()%>"
target="_blank">

Open Link

</a>

<br><br>

<%

}

%>

<!-- Uploaded File -->

<%

if(e.getUploadedFile()!=null &&
!e.getUploadedFile().trim().isEmpty()){

%>

<b>Uploaded File</b><br>

<%=e.getOriginalFileName()%>

<br>

<a href="<%=request.getContextPath()%>/download?id=<%=e.getId()%>">

Download

</a>

<%

}

%>

</td>

<td>

<a href="<%=request.getContextPath()%>/editExperience?id=<%=e.getId()%>">

Edit

</a>

<br><br>

<a href="<%=request.getContextPath()%>/deleteExperience?id=<%=e.getId()%>"
onclick="return confirm('Are you sure you want to delete?')">

Delete

</a>

</td>

</tr>

<%

}

}

%>

</table>

<br>

<a href="<%=request.getContextPath()%>/dashboard.jsp">

Back

</a>

</div>

</div>

</body>

</html>