# blackjack-cli
A console-based Blackjack game in Java with a 6-class object-oriented design. Implements standard casino rules including hand splitting, dynamic Ace valuation, and dealer AI.

## 🚀 Overview
A fully playable terminal-based Blackjack game that focuses on accurate casino logic and object-oriented principles. Players manage a bankroll, place bets, and play against an automated dealer. The game supports complex rules like splitting pairs into independent hands.

## ✨ Features
- 6-class object-oriented architecture (Main, Card, Deck, Hand, Player, Dealer).
- Dynamic Ace valuation to maximize hand totals without busting.
- Hand splitting for pairs, allowing multiple independent hands per round with separate bets.
- Betting system with input validation and persistent bankroll tracking across rounds.
- Dealer AI that automatically plays until reaching a soft or hard 17.
- Unicode suit rendering (♠, ♥, ♦, ♣) for card display in the console.

## 🛠️ Technical Implementation
- **Architecture**: The game logic is distributed across independent models. `Deck` handles card generation and shuffling. `Hand` calculates sums and manages lists of cards. `Player` tracks the bankroll and handles multiple `Hand` objects for splits. `Dealer` extends `Hand` to reuse sum calculations while adding automated AI logic.
- **Algorithms**: The Fisher-Yates algorithm randomly swaps elements in an array to shuffle the 52-card deck. Ace valuation uses a while loop to iteratively adjust Ace values between 1 and 11 to calculate the optimal score.
- **Data Structures**: `ArrayList` is used for hands, allowing dynamic resizing when hitting or splitting. A fixed `Card[]` array is used for the deck.

## 🧠 Design Decisions & Challenges
- **Dealer as a Hand**: The `Dealer` class extends `Hand` rather than `Player`. A dealer only needs to manage a single set of cards and sum evaluations, but overrides play strategy. It does not require betting, bankroll tracking, or splitting logic.
- **Ace Valuation**: Calculating hands with multiple Aces is handled dynamically. The code tracks the number of Aces, assigns a base value of 1, and uses a while loop to promote them to 11 one at a time as long as the total remains under or equal to 21.
- **Hand Splitting**: When a player splits, the original hand is replaced by two new `Hand` objects in a list. Betting is tracked using parallel lists (`playerHands` and `handBets`), allowing each split hand to have its own independent wager evaluated against the dealer.
- **Console Interface**: Kept as a terminal application to focus strictly on game logic, data flow, and object-oriented design rather than UI event loops.

## 📁 Project Structure
- `src/Main.java`: Game loop, input handling, and round evaluation.
- `src/Card.java`: Represents a single playing card with a value and Unicode suit.
- `src/Deck.java`: 52-card collection utilizing the Fisher-Yates shuffle algorithm.
- `src/Hand.java`: Core logic for storing cards, checking conditions, and Ace math.
- `src/Player.java`: Manages bankroll, bets, and multiple hands.
- `src/Dealer.java`: Extends `Hand` with automated hit/stand logic and hidden card display.

## ▶️ Running the Project
**Requirements**: Java Development Kit (JDK) 8 or higher.

1. Clone the repository and navigate to the project directory.
2. Compile the Java files:
   ```bash
   javac src/*.java
   ```
3. Run the main class:
   ```bash
   java -cp src Main
   ```

## 🔮 Future Improvements
- Multi-deck shoe implementation.
- Insurance and double-down betting options.
- Basic strategy advisor to recommend optimal moves.
- GUI version with graphical card rendering.

## Author
Mahit Pulavarthi — [github.com/MahitP](https://github.com/MahitP)
