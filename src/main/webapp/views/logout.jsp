<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
</head>

<body>

<div class="container">

    <form:form name="form" method="POST" action="/logout">

        <button type="submit"></button>
    </form:form>

    <script>
        window.onload = ()=>{
            document.forms["form"].submit();
        };

    </script>

</div>

</body>
</html>
