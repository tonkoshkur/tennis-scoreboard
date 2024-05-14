<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="totalPages" value="${matchesPage.totalPages}"/>
<c:set var="currentPage" value="${matchesPage.number + 1}"/>
<c:set var="currentPageMaxAdjacent" value="2"/>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Match score</title>
    <style>
        <%@ include file="/css/common.css" %>
        <%@ include file="/css/matches.css" %>
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
        </tr>
        <c:forEach var="match" items="${matchesPage.content}">
            <tr>
                <td>
                    <c:out value="${match.player1.name}"/>
                    <c:if test="${match.player1.name == match.winner.name}">
                        <i class="fa fa-trophy fa-lg fa-align-right" aria-hidden="true"></i>
                    </c:if>
                </td>
                <td>
                    <c:out value="${match.player2.name}"/>
                    <c:if test="${match.player2.name == match.winner.name}">
                        <i class="fa fa-trophy fa-lg fa-align-right" aria-hidden="true"></i>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br/>

    <c:if test="${totalPages > 1}">
        <div class="flex-centered">
            <c:forEach begin="1" end="${totalPages}" var="page">

                <c:if test="${page == 1 || page == totalPages
                || (currentPage - currentPageMaxAdjacent < page && currentPage + currentPageMaxAdjacent > page)}">
                    <form>
                        <input name="filter_by_player_name" type="hidden" value="${playerName}"/>
                        <button class="${currentPage == page ? "active" : ""}"
                                type="submit"
                                id="page"
                                name="page"
                                value="${page - 1}">
                            <c:out value="${page}"/>
                        </button>
                    </form>
                </c:if>

                <c:if test="${(page > 1 && page == currentPage - currentPageMaxAdjacent)
                || (page < totalPages && page == currentPage + currentPageMaxAdjacent)}">
                    <div>...</div>
                </c:if>

            </c:forEach>
        </div>
    </c:if>
</div>
</body>
</html>
