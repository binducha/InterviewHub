<%@ page contentType="text/html;charset=UTF-8" %>

<%
com.app.model.Job job = (com.app.model.Job) request.getAttribute("job");
%>

<html>
<head>
<title>Edit Job</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">

</head>
<body>

<div class="container-large">
<div class="card">

<h2>Edit Job</h2>

<form class="form-full" action="<%=request.getContextPath()%>/updateJob" method="post">

    <input type="hidden" name="id" value="<%=job.getId()%>"/>

    <label>Company</label>
    <input type="text" name="company" value="<%=job.getCompany()%>" required/>

    <label>Role</label>
    <input type="text" name="role" value="<%=job.getRole()%>" required/>

    <label>Job Link</label>
    <input type="text" name="link" value="<%=job.getLink()%>" required/>

    <button type="submit">Update Job</button>

</form>

<br>
<a href="<%=request.getContextPath()%>/job">Back</a>

</div>
</div>

</body>
</html>