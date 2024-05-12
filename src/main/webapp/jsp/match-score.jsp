<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="player1" value="${match.player1}"/>
<c:set var="player2" value="${match.player2}"/>
<c:set var="player1Score" value="${match.player1Score}"/>
<c:set var="player2Score" value="${match.player2Score}"/>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Match score</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/match-score.css">
</head>
<body>
<div class="content horizontal-center">
    <a href="${pageContext.request.contextPath}/">
        <button>
            ${match.finished ? "Home" : "Cancel"}
        </button>
    </a>

    <h1>Scoreboard</h1>
    <div class="scoreboard white-border">
        <div>Player</div>
        <div></div>
        <div>Sets</div>
        <div>Games</div>
        <div>Points</div>

        <div class="player-name">${player1.name}</div>
        <div class="${player1Score.advantage ? "white-background" : ""}"></div>
        <div>${player1Score.sets}</div>
        <div>${player1Score.games}</div>
        <div>${player1Score.points}</div>

        <div class="player-name">${player2.name}</div>
        <div class="${player2Score.advantage ? "white-background" : ""}"></div>
        <div>${player2Score.sets}</div>
        <div>${player2Score.games}</div>
        <div>${player2Score.points}</div>
    </div>
    <br/>

    <c:if test="${!match.finished}">
        <form method="post">
            <div class="flex-centered">
                <button name="winnerId"
                        value="${player1.id}">
                    Player 1 won the point
                </button>
                <button name="winnerId"
                        value="${player2.id}">
                    Player 2 won the point
                </button>
            </div>
        </form>
    </c:if>
    <c:if test="${match.finished}">
        <h2>Winner: ${match.winner.name}</h2>
    </c:if>
</div>
</body>
</html>
