<%@ page contentType="text/html;charset=UTF-8" %>

<%
if(session.getAttribute("user") == null){
    response.sendRedirect("index.jsp");
}
%>

<html>
<head>
<title>Dashboard</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">

<style>
.dashboard {
    display: flex;
    justify-content: center;
    gap: 30px;
    margin-top: 50px;
    flex-wrap: wrap;
}

.box {
    width: 250px;
    padding: 25px;
    background: white;
    text-align: center;
    border-radius: 12px;
    box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
}

.box h3 {
    margin-bottom: 10px;
}

.box p {
    font-size: 14px;
    color: #555;
    margin-bottom: 15px;
}

/* 🔥 BUTTON STYLE */
.box a {
    display: inline-block;
    padding: 10px 20px;
    background: #007bff;
    color: white;
    border-radius: 5px;
    text-decoration: none;
}

.box a:hover {
    background: #0056b3;
}
</style>

</head>
<body>

<h2>Welcome ${sessionScope.user}</h2>

<div class="dashboard">

    <!-- SHARE -->
    <div class="box">
        <h3>Share Experience</h3>
        <p>Share your interview experience and help others prepare better.</p>
        <a href="jsp/share.jsp">Share</a>
    </div>

    <!-- VIEW -->
    <div class="box">
        <h3>View Experiences</h3>
        <p>Explore interview experiences shared by others.</p>
        <a href="jsp/view.jsp">View</a>
    </div>

    <!-- JOB -->
    <div class="box">
        <h3>Post Jobs</h3>
        <p>Share latest job opportunities with others.</p>
        <a href="job">Post</a>
    </div>

    <!-- LOGOUT -->
    <div class="box">
        <h3>Logout</h3>
        <p>Sign out from your account securely.</p>
        <a href="logout">Logout</a>
    </div>

</div>

</body>
</html>