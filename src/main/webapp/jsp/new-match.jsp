<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>New match</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css">
</head>
<body>
<div class="content horizontal-center">
    <a href="${pageContext.request.contextPath}/">
        <button>Home</button>
    </a>
    <form class="content"
          action="new-match"
          method="post">
        <div>
            <label for="player1">First player name</label>
            <input class="full-width"
                   id="player1"
                   name="player1"
                   type="text"
                   maxlength="25"
                   value="${param.player1}"
                   autofocus
                   required/>
        </div>
        <div>
            <label for="player2">Second player name</label>
            <input class="full-width"
                   id="player2"
                   name="player2"
                   type="text"
                   maxlength="25"
                   value="${param.player2}"
                   required/>
        </div>
        <div class="error">${error}</div>
        <input class="horizontal-center"
               type="submit"
               value="Start"/>
    </form>
</div>
</body>
</html>