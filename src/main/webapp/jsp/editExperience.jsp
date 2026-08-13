<%@ page contentType="text/html;charset=UTF-8" %>

<%
com.app.model.Experience exp =
(com.app.model.Experience)request.getAttribute("exp");
%>

<html>

<head>

<title>Edit Experience</title>

<link rel="stylesheet"
href="<%=request.getContextPath()%>/css/styles.css">

</head>

<body>

<div class="container-large">

<div class="card">

<h2>Edit Interview Experience</h2>

<form class="form-full"
action="<%=request.getContextPath()%>/updateExperience"
method="post"
enctype="multipart/form-data">

    <input type="hidden"
           name="id"
           value="<%=exp.getId()%>"/>

    <div class="form-group">

        <label>Company</label>

        <input type="text"
               name="company"
               value="<%=exp.getCompany()%>"
               required/>

    </div>

    <div class="form-group">

        <label>Candidate Name</label>

        <input type="text"
               name="candidateName"
               value="<%=exp.getCandidateName()%>"
               required/>

    </div>

    <div class="form-group">

        <label>Interview Round</label>

        <select name="round">

            <option
            <%=exp.getRound().equals("HR")?"selected":""%>>
            HR
            </option>

            <option
            <%=exp.getRound().equals("Technical")?"selected":""%>>
            Technical
            </option>

            <option
            <%=exp.getRound().equals("Managerial")?"selected":""%>>
            Managerial
            </option>

            <option
            <%=exp.getRound().equals("Coding")?"selected":""%>>
            Coding
            </option>

        </select>

    </div>

    <div class="form-group">

        <label>Experience</label>

        <textarea
        name="experienceText"
        rows="5"><%=exp.getExperienceText()%></textarea>

    </div>

    <div class="form-group">

        <label>Guidance</label>

        <textarea
        name="guidance"
        rows="4"><%=exp.getGuidance()%></textarea>

    </div>

    <div class="form-group">

        <label>Preparation Material Link</label>

        <input type="url"
               name="materialLink"
               value="<%=exp.getMaterialLink()==null?"":exp.getMaterialLink()%>"/>

    </div>

    <% if(exp.getOriginalFileName()!=null){ %>

    <div class="form-group">

        <label>Current Uploaded File</label>

        <p>

        <b><%=exp.getOriginalFileName()%></b>

        </p>

    </div>

    <% } %>

    <div class="form-group">

        <label>Replace Uploaded File (Optional)</label>

        <input
        type="file"
        name="materialFile"
        accept=".pdf,.doc,.docx,.ppt,.pptx"/>

        <div>

        Leave this empty if you don't want to change the uploaded file.

        </div>

    </div>

    <div class="form-actions">

        <button type="submit">

        Update Experience

        </button>

    </div>

</form>

</div>

</div>

</body>

</html>