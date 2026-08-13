<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>InterviewHub</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

<style>

/* GLOBAL FIX */
* {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
    font-family: Arial, sans-serif;
}

/*  LANDING PAGE */
.hero {
    display: flex;
    height: 100vh;
}

/* LEFT SIDE */
.left {
    width: 60%;
    background: linear-gradient(135deg, #007bff, #0056b3);
    color: white;
    padding: 80px 60px;
}

.left h1 {
    font-size: 40px;
    margin-bottom: 20px;
}

.left p {
    font-size: 18px;
    margin-bottom: 20px;
}

.left ul {
    margin-top: 20px;
    line-height: 2;
}

/* RIGHT SIDE */
.right {
    width: 40%;
    display: flex;
    justify-content: center;
    align-items: center;
    background: #f5f5f5;
}

/*  LOGIN BOX */
.auth-box {
    background: white;
    padding: 35px;
    width: 320px;
    border-radius: 12px;
    box-shadow: 0px 4px 15px rgba(0,0,0,0.15);
    text-align: center;
}

/* INPUT FIX */
.auth-box input {
    width: 100%;
    margin: 12px 0;
    padding: 12px;
    border-radius: 6px;
    border: 1px solid #ccc;
    font-size: 14px;
}

/* BUTTON */
.auth-box button {
    width: 100%;
    padding: 12px;
    background-color: #28a745;
    color: white;
    border: none;
    border-radius: 6px;
    font-size: 16px;
    cursor: pointer;
}

.auth-box button:hover {
    background-color: #218838;
}

/* REGISTER LINK */
.auth-box a {
    display: inline-block;
    margin-top: 15px;
    padding: 8px 12px;
    background-color: #1e6fd9;
    color: white;
    text-decoration: none;
    border-radius: 6px;
    font-size: 14px;
}

.auth-box a:hover {
    background-color: #155ab6;
}

/* ERROR MESSAGE */
.error-msg {
    color: red;
    margin-bottom: 10px;
    font-size: 14px;
}

</style>

</head>

<body>

<div class="hero">

    <!-- LEFT SIDE -->
    <div class="left">
        <h1>InterviewHub</h1>
        <p>Share your interview experiences and help others succeed.</p>

        <ul>
            <li>✔ Share real interview experiences</li>
            <li>✔ Learn from others</li>
            <li>✔ Explore job opportunities</li>
        </ul>
    </div>

    <!-- RIGHT SIDE -->
    <div class="right">
        <div class="auth-box">

            <h3>Welcome</h3>

            <% if(request.getAttribute("error") != null) { %>
                <p class="error-msg">
                    <%= request.getAttribute("error") %>
                </p>
            <% } %>

            <form action="login" method="post">
                <input type="text" name="username" placeholder="Enter Username" required/>
                <input type="password" name="password" placeholder="Enter Password" required/>
                <button type="submit">Login</button>
            </form>

            <a href="register.jsp">New user? Register</a>

        </div>
    </div>

</div>

</body>
</html>