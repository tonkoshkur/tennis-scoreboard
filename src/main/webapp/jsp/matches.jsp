<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Match score</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/matches.css">
</head>
<body>
<div class="content horizontal-center">
    <a href="${pageContext.request.contextPath}/">
        <button>Home</button>
    </a>

    <br/>

    <form class="flex-centered"
          role="search">
        <label for="filter_by_player_name">Player name:</label>
        <input class="m-10"
               id="filter_by_player_name"
               name="filter_by_player_name"
               type="search"
               value="${playerName}"/>
        <button type="submit">Search</button>
    </form>

    <h2 id="table-title">Matches</h2>
    <table aria-describedby="table-title">
        <tr>
            <th>Player 1</th>
            <th>Player 2</th>
            <th>Winner</th>
        </tr>
        <c:forEach var="match" items="${matchesPage.content}">
            <tr>
                <td><c:out value="${match.player1.name}"/></td>
                <td><c:out value="${match.player2.name}"/></td>
                <td><c:out value="${match.winner.name}"/></td>
            </tr>
        </c:forEach>
    </table>

    <br/>

    <c:if test="${matchesPage.totalPages > 1}">
        <div class="flex-centered">
            <c:forEach begin="0" end="${matchesPage.totalPages-1}" var="pageNumber">
                <form>
                    <button class="${matchesPage.number == pageNumber ? "active" : ""}"
                            type="submit"
                            id="page"
                            name="page"
                            value="${pageNumber}">
                        <c:out value="${pageNumber+1}"/>
                    </button>
                </form>
            </c:forEach>
        </div>
    </c:if>
</div>
</body>
</html>
