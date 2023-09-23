package domain

class Tower {

    fun moveValid(origin: Position, destiny: Position, chessboard: Array<Array<Piece?>>): Boolean {
        val dx = destiny.x - origin.x
        val dy = destiny.y - origin.y

        // A tower can move either vertically or horizontally, but not both.
        if ((dx != 0 && dy == 0) || (dx == 0 && dy != 0)) {
            // Check if the path is clear in the vertical direction.
            if (dx == 0) {
                return !isPathBlockedVertical(origin, destiny, chessboard)
            }
            // Check if the path is clear in the horizontal direction.
            if (dy == 0) {
                return !isPathBlockedHorizontal(origin, destiny, chessboard)
            }
        }

        return false // Invalid movement in other directions.
    }

    private fun isPathBlockedVertical(origin: Position, destiny: Position, chessboard: Array<Array<Piece?>>): Boolean {
        val step = if (destiny.y > origin.y) 1 else -1
        var currentY = origin.y + step

        while (currentY != destiny.y) {
            if (chessboard[origin.x][currentY] != null) {
                return true // There is a piece blocking the path.
            }
            currentY += step
        }

        return false // The path is clear.
    }

    private fun isPathBlockedHorizontal(origin: Position, destiny: Position, chessboard: Array<Array<Piece?>>): Boolean {
        val step = if (destiny.x > origin.x) 1 else -1
        var currentX = origin.x + step

        while (currentX != destiny.x) {
            if (chessboard[currentX][origin.y] != null) {
                return true // There is a piece blocking the path.
            }
            currentX += step
        }

        return false // The path is clear.
    }
}