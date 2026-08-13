<%@ page contentType="text/html;charset=UTF-8" %>

<%
if(session.getAttribute("user") == null){
    response.sendRedirect("../login.jsp");
}
%>

<html>
<head>
<title>Share Experience</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">

</head>
<body>

<div class="container-large">

    <!--  HEADER -->
    <div class="page-header">
        <h2>Share Interview Experience</h2>
        <a class="back-btn" href="<%=request.getContextPath()%>/dashboard.jsp">Back</a>
    </div>

    <!-- CARD -->
    <div class="card">
    
    <% if(request.getAttribute("error")!= null){ %>

<div class="error-box">
    <%= request.getAttribute("error") %>
</div>

<% } %>
    
    
    

     <form class="form-full"
      action="<%=request.getContextPath()%>/shareExperience"
      method="post"
      enctype="multipart/form-data">

            <div class="form-group">
                <label>Company</label>
                <input type="text" name="company" required/>
            </div>

            <div class="form-group">
                <label>Candidate Name</label>
                <input type="text" name="candidateName" required/>
            </div>

            <div class="form-group">
                <label>Interview Round</label>
                <select name="round" required>
                    <option value="">Select Round</option>
                    <option>HR</option>
                    <option>Technical</option>
                    <option>Managerial</option>
                    <option>Coding</option>
                </select>
            </div>

            <div class="form-group">
                <label>Describe Your Experience</label>
                <textarea name="experienceText" rows="5" required></textarea>
            </div>

            <div class="form-group">
                <label>Guidance for Others</label>
                <textarea name="guidance" rows="4"></textarea>
            </div>

            <div class="form-group">
    <label>Preparation Material Link (Optional)</label>
    <input
        type="url"
        name="materialLink"
        placeholder="https://example.com"
    />
</div>

<div class="form-group">

    <label>Upload Preparation Material (Optional)</label>

    <input
        type="file"
        name="materialFile"
        accept=".pdf,.doc,.docx,.ppt,.pptx"
    />

    <div class="upload-note">
    <strong>Supported Formats:</strong> PDF, DOC, DOCX, PPT, PPTX
    <br>
    <strong>Maximum Size:</strong> 10 MB
</div>

</div>

            <div class="form-actions">
                <button type="submit">Submit Experience</button>
            </div>

        </form>

    </div>

</div>

</body>
</html>