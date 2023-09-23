package domain

enum class PieceTypeEnum {
    BISHOP{
        override val img: String = "bishop.xml"
        override fun moveValid(
            origin: Position,
            destiny: Position,
            chessboard: Array<Array<Piece?>>,
            color: PieceColor
        ): Boolean =
            Bishop().moveValid(origin, destiny, chessboard)
    },
    KNIGHT{
        override val img: String = "knight.xml"
        override fun moveValid(
            origin: Position,
            destiny: Position,
            chessboard: Array<Array<Piece?>>,
            color: PieceColor
        ): Boolean =
            Knight().moveValid(origin, destiny, chessboard)
    },
    TOWER{
        override val img: String = "tower.xml"
        override fun moveValid(
            origin: Position,
            destiny: Position,
            chessboard: Array<Array<Piece?>>,
            color: PieceColor
        ): Boolean =
            Tower().moveValid(origin, destiny, chessboard)
    },
    KING{
        override val img: String = "king.xml"
        override fun moveValid(
            origin: Position,
            destiny: Position,
            chessboard: Array<Array<Piece?>>,
            color: PieceColor
        ): Boolean =
            King().moveValid(origin, destiny, chessboard, color)
    },
    QUEEN{
        override val img: String = "queen.xml"
        override fun moveValid(
            origin: Position,
            destiny: Position,
            chessboard: Array<Array<Piece?>>,
            color: PieceColor
        ): Boolean =
            Queen().moveValid(origin, destiny, chessboard)
    },
    PAWN{
        override val img: String = "pawn.xml"
        override fun moveValid(
            origin: Position,
            destiny: Position,
            chessboard: Array<Array<Piece?>>,
            color: PieceColor
        ): Boolean =
            Pawn().moveValid(origin, destiny, chessboard, color)
    };

    abstract val img: String
    abstract fun moveValid(origin: Position, destiny: Position, chessboard: Array<Array<Piece?>>, color: PieceColor): Boolean
}