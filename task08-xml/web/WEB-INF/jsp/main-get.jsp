<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Main page</title>
</head>
<body>
<h1>Parse XML with gems:</h1>
<form action="${pageContext.request.contextPath}/" method="post" enctype="multipart/form-data">
    <label for="fileId">Load XML file:
        <input type="file" name="xml" id="fileId" required/>
    </label><br/>
    <label for="parserId">Choose parser:
        <select name="parser" id="parserId" required>
            <option value="dom">DOM</option>
            <option value="sax">SAX</option>
            <option value="stax">StAX</option>
        </select>
    </label><br>
    <button type="submit">Parse</button>
</form>
</body>
</html>
