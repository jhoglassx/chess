package domain

import kotlin.math.abs

class Queen{

    fun podeCapturarRei(positionQueen: Position, destinyKing: Position): Boolean {
        if (positionQueen.x == destinyKing.x || positionQueen.y == destinyKing.y ||
            abs(positionQueen.x - destinyKing.x) == abs(positionQueen.y - destinyKing.y)
        ) {
            return true
        }
        return false
    }


    fun moveValid(origin: Position, destiny: Position, chessboard: Array<Array<Piece?>>): Boolean {
        val dx = destiny.x - origin.x
        val dy = destiny.y - origin.y

        // Verifique se a rainha está se movendo na mesma linha horizontal, vertical ou diagonal.
        if (dx == 0 && dy != 0) {
            // Movimento vertical
            return !isPathBlockedVertical(origin, destiny, chessboard)
        } else if (dy == 0 && dx != 0) {
            // Movimento horizontal
            return !isPathBlockedHorizontal(origin, destiny, chessboard)
        } else if (abs(dx) == abs(dy)) {
            // Movimento diagonal
            return !isPathBlockedDiagonal(origin, destiny, chessboard)
        }

        return false // Movimento inválido em outras direções.
    }

    private fun isPathBlockedVertical(origin: Position, destiny: Position, chessboard: Array<Array<Piece?>>): Boolean {
        val step = if (destiny.y > origin.y) 1 else -1
        var currentY = origin.y + step

        while (currentY != destiny.y) {
            if (chessboard[origin.x][currentY] != null) {
                return true // Há uma peça bloqueando o caminho.
            }
            currentY += step
        }

        return false // O caminho está livre.
    }

    private fun isPathBlockedHorizontal(origin: Position, destiny: Position, chessboard: Array<Array<Piece?>>): Boolean {
        val step = if (destiny.x > origin.x) 1 else -1
        var currentX = origin.x + step

        while (currentX != destiny.x) {
            if (chessboard[currentX][origin.y] != null) {
                return true // Há uma peça bloqueando o caminho.
            }
            currentX += step
        }

        return false // O caminho está livre.
    }

    private fun isPathBlockedDiagonal(origin: Position, destiny: Position, chessboard: Array<Array<Piece?>>): Boolean {
        val dx = if (destiny.x > origin.x) 1 else -1
        val dy = if (destiny.y > origin.y) 1 else -1
        var currentX = origin.x + dx
        var currentY = origin.y + dy

        while (currentX != destiny.x && currentY != destiny.y) {
            if (chessboard[currentX][currentY] != null) {
                return true // Há uma peça bloqueando o caminho.
            }
            currentX += dx
            currentY += dy
        }

        return false // O caminho está livre.
    }

}
