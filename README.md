**AI-Powered Chess Game**

(Chess_Project_Team10_Final) is the main working program. Brain.java (inside src) does the most of the work.

This project is a Java-based Chess application with:
	•	*AI-driven move suggestions via a custom evaluation engine*
	•	*Randomized opening variants for a novel twist on standard chess*
	•	*Balanced odds variants (including dynamic piece score adjustments)*
	•	*A Graphical User Interface (GUI) built using Java Swing*
	•	*Comprehensive chess rules (castling, en passant, stalemate, threefold repetition, and so on)*

The core AI logic is contained in the Brain class, which uses a Minimax-like approach with search to evaluate moves. The user interface is handled by Main, which sets up the board, handles mouse input, and orchestrates the gameplay loop.

*Features*
	•	Classic and Randomized Modes
	  •	Classic: Standard chess initial setup.
	  •	Random: Randomizes the initial piece positions, including special promotions.
	•	AI Move Suggestions (Hint System)
	  •	Click on the “Hint” button in the GUI to see the best move as evaluated by the AI.
	•	Visual Move Selection & Highlights
	  •	Select a piece, see possible valid moves highlighted, and click again to move.
	•	Balanced Odds Variants
	  •	Piece values can change slightly as the game progresses (opening vs. endgame).
	•	Chess Rules
	  •	Threefold repetition: Declares a draw when a board state repeats three times.
	  •	Check, Checkmate, Stalemate.
	  •	Castling (both King-side and Queen-side).
	  •	Promotion for pawns reaching the last rank.
	  •	En passant captures (in standard mode).
	•	Game Recording
	  •	At game end, you can download a record of the moves.

   

