package domain

import kotlin.math.abs

class Knight {

    fun moveValid(origin: Position, destiny: Position, chessboard: Array<Array<Piece?>>): Boolean {
        val dx = abs(destiny.x - origin.x)
        val dy = abs(destiny.y - origin.y)

        // A knight moves in an "L" shape: two squares in one direction and one square in the other direction.
        // The absolute differences between dx and dy must be 1 and 2 in some order.
        if ((dx == 1 && dy == 2) || (dx == 2 && dy == 1)) {
            return true
        }

        return false // Invalid movement for a knight.
    }
}