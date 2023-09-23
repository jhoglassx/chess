package domain

import kotlin.math.abs

class King{

    fun moveValid(origin: Position, destiny: Position, chessboard: Array<Array<Piece?>>, color: PieceColor): Boolean {
        if (movimentoValido(origin, destiny, chessboard, color) && estaEmXeque(chessboard, color, destiny).not()) {
            return true
        }
        return false
    }

    private fun movimentoValido(origin: Position, destiny: Position, chessboard: Array<Array<Piece?>>, color: PieceColor): Boolean {
        val difX = abs(destiny.x - origin.x)
        val difY = abs(destiny.y - origin.y)

        // Verificar se o rei se move apenas uma casa em qualquer direção.
        return difX <= 1 && difY <= 1 && !destiny.equals(origin) &&
                (chessboard[destiny.x][destiny.y] == null || chessboard[destiny.x][destiny.y]!!.color != color)
    }


    fun estaEmXeque(chessboard: Array<Array<Piece?>>, color: PieceColor, destiny: Position): Boolean {
        val position = Chessboard().getPositionPieceEmeny(
            chessboard =  chessboard,
            type = PieceTypeEnum.QUEEN,
            color = color
        ) ?: return false

        if (Queen().podeCapturarRei(positionQueen = position, destinyKing = destiny)) {
            return true
        }
        return false
    }
}