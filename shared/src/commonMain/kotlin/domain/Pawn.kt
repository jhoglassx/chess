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

        // Primeira jogada permite mover-se duas casas para frente

        if (origin.x == 1 + 5 * direction && difX == 2 * direction && difY == 0 && chessboard[destiny.x][destiny.y] == null) {
            return true
        }

        // Captura diagonal
        if (difX == 1 * direction && abs(difY) == 1 && chessboard[destiny.x][destiny.y] != null) {
            return true
        }

        return false
    }
}