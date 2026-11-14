package domain

import kotlin.math.abs

class Pawn {
    fun moveValid(origin: Position?, destiny: Position, chessboard: Array<Array<Piece?>>, color: PieceColor): Boolean {
        val difX = destiny.x - origin?.x!!
        val difY = destiny.y - origin?.y!!

        // Determine the direction of movement based on the pawn's color

        val direction = if (color == PieceColor.WHITE) 1 else -1

        // Movimento para frente
        if (difX == 1 * direction && difY == 0 && chessboard[destiny.x][destiny.y] == null) {
            return true
        }

        // Primeira jogada permite mover-se duas casas para frente.
        // White pawns start on row 1 and black pawns on row 6 (see Chessboard.createInitialChessboard),
        // and the square in between must be empty too: a pawn cannot jump over a piece.
        val startRow = if (color == PieceColor.WHITE) 1 else 6
        if (origin.x == startRow && difX == 2 * direction && difY == 0 &&
            chessboard[origin.x + direction][origin.y] == null &&
            chessboard[destiny.x][destiny.y] == null
        ) {
            return true
        }

        // Captura diagonal
        if (difX == 1 * direction && abs(difY) == 1 && chessboard[destiny.x][destiny.y] != null) {
            return true
        }

        return false
    }
}