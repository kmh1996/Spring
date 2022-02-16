<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uploadForm.jsp</title>
</head>
<body>
	<h2>Upload Form</h2>
	<form action="uploadForm" method="POST" 
					enctype="multipart/form-data">
		<input type="file" name="file"/>
		<input type="submit" value="UPLOAD"/>
	</form>
	
	<h2>Upload Form1 multiple</h2>
	<form action="uploadForm1" method="POST" 
					encType="multipart/form-data">
		<input type="file" name="file" multiple />
		<input type="submit" value="UPLOAD" />
	</form>
	
	<h2>uploadForm2 multiple param</h2>
	<form action="uploadForm2" method="POST" 
						encType="multipart/form-data">
		<input type="text" name="auth"/> <br/>
		<input type="file" name="file" multiple />
		<input type="file" name="file1" />
		<input type="submit" value="UPLOAD"/>		
	</form>
	
	<h2>uploadForm3 multiple param</h2>
	<form action="uploadForm3" method="POST" 
						encType="multipart/form-data">
		<input type="text" name="auth" /> <br/>
		<textarea name="content"></textarea> <br/>
		<input type="file" name="files" multiple />
		<input type="file" name="file" accept="image/*"/>
		<input type="submit" value="UPLOAD"/>
	</form>
</body>
</html>




















