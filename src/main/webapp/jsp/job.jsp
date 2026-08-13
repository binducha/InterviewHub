<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.app.model.Job" %>

<%
if(session.getAttribute("user") == null){
    response.sendRedirect("../login.jsp");
}
%>

<html>
<head>
    <title>Job Opportunities</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
</head>

<body>

<div class="container-large">
<div class="card">

<h2>Post Job Opportunity</h2>

<form class="form-full" action="<%=request.getContextPath()%>/job" method="post">

    <label>Company</label>
    <input type="text" name="company" required/>

    <label>Role</label>
    <input type="text" name="role" required/>

    <label>Job Link</label>
    <input type="url" name="link" required/>

    <button type="submit">Post Job</button>
</form>

<br><br>

<h2>Available Jobs</h2>

<table border="1">
    <tr>
        <th>Company</th>
        <th>Role</th>
        <th>Link</th>
        <th>Actions</th>
    </tr>

<%
List<Job> jobs = (List<Job>) request.getAttribute("jobs");

if (jobs != null) {
    for (Job j : jobs) {
%>
    <tr>
        <td><%= j.getCompany() %></td>
        <td><%= j.getRole() %></td>

        <td>
            <a href="<%= j.getLink() %>" target="_blank">Apply</a>
        </td>

        <!--  ACTION BUTTONS -->
        <td>
            <a href="<%=request.getContextPath()%>/editJob?id=<%=j.getId()%>">Edit</a>

            <a href="<%=request.getContextPath()%>/deleteJob?id=<%=j.getId()%>"
               onclick="return confirm('Are you sure you want to delete this job?')">
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
<a href="<%=request.getContextPath()%>/dashboard.jsp">Back</a>
</div>
</div>

</body>
</html>