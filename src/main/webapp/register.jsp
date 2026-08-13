<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>Register</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">

<style>
.register-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

/*  CARD */
.register-box {
    background: white;
    padding: 35px;
    width: 350px;
    border-radius: 12px;
    box-shadow: 0px 0px 15px rgba(0,0,0,0.1);
    text-align: center;
}

.register-box h2 {
    margin-bottom: 20px;
}

/* INPUT */
.register-box input {
    width: 100%;
    padding: 12px;
    margin: 10px 0;
}

/* BUTTON */
.register-box button {
    width: 100%;
    margin-top: 10px;
}

/* LINK */
.register-box a {
    display: block;
    margin-top: 15px;
    background: none;
    color: #007bff;
}
</style>

</head>
<body>

<div class="register-container">

    <div class="register-box">

        <h2>Create Account </h2>
        
        <% if(request.getAttribute("error") != null) { %>

    <p class="error-msg">
        <%= request.getAttribute("error") %>
    </p>

        <% } %>

        <form action="register" method="post">

            <input type="text" name="username" placeholder="Enter Username" required/>
            <input type="password" name="password" placeholder="Enter Password" required/>

            <button type="submit">Register</button>

        </form>

        <a href="index.jsp">← Back to Login</a>

    </div>

</div>

</body>
</html>