
<body>
  <h1>Connect Four Game in Java with AI (Using Minimax Algorithm)</h1>

  <h2>Introduction</h2>
  <p>This is a simple implementation of the classic Connect Four game in Java, featuring a two-player mode and a single-player mode against an AI that uses the Minimax algorithm.</p>

  <h2>How to Play</h2>
  <ul>
    <li>The game is played on a grid, 7 wide by 6 deep.</li>
    <li>Two players alternate turns dropping their colored discs from the top into a column.</li>
    <li>The first player to connect four of their discs in a row horizontally, vertically, or diagonally wins the game.</li>
    <li>If the board is full and no player has won, the game is a draw.</li>
  </ul>

  <h2>Running the Game</h2>
  <ol>
    <li>Clone the repository:</li>
    <pre>git clone https://github.com/dorpanz/Connect4AI.git</pre>
    <li>Compile the code:</li>
    <pre>cd connect-four-java<br>javac ConnectFour.java</pre>
    <li>Run the game:</li>
    <pre>java ConnectFour</pre>
  </ol>

  <h2>How to Play Two-Player Mode</h2>
  <ol>
    <li>Start the game.</li>
    <li>Enter the player's names and choose a symbol ('R' for red, 'Y' for yellow).</li>
    <li>Players take turns dropping their colored discs into a column of their choice.</li>
    <li>The first player to connect four discs of their color wins.</li>
  </ol>

  <h2>How to Play Single-Player Mode Against AI</h2>
  <ol>
    <li>Start the game.</li>
    <li>Enter your name and choose a symbol ('R' for red, 'Y' for yellow).</li>
    <li>Choose whether you want to go first or not.</li>
    <li>If you choose to go second, the AI will make the first move.</li>
    <li>Players take turns dropping their colored discs into a column.</li>
    <li>The first player to connect four discs of their color wins. If the board is full and no player has won, the game is a draw.</li>
  </ol>
</body>
</html>


