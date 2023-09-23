package domain

import kotlin.math.abs

class Bishop {

    fun moveValid(origin: Position, destiny: Position, chessboard: Array<Array<Piece?>>): Boolean {
        val dx = destiny.x - origin.x
        val dy = destiny.y - origin.y

        // A bishop can only move diagonally, meaning the change in x and y must be equal.
        if (abs(dx) == abs(dy)) {
            return !isPathBlockedDiagonal(origin, destiny, chessboard)
        }

        return false // Invalid movement in other directions.
    }

    private fun isPathBlockedDiagonal(origin: Position, destiny: Position, chessboard: Array<Array<Piece?>>): Boolean {
        val dx = if (destiny.x > origin.x) 1 else -1
        val dy = if (destiny.y > origin.y) 1 else -1
        var currentX = origin.x + dx
        var currentY = origin.y + dy

        while (currentX != destiny.x && currentY != destiny.y) {
            if (chessboard[currentX][currentY] != null) {
                return true // There is a piece blocking the path.
            }
            currentX += dx
            currentY += dy
        }

        return false // The path is clear.
    }
}
