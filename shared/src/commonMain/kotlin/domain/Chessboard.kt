package domain

import kotlin.math.abs

class Chessboard {
    var size = 8
    val chessboard: Array<Array<Piece?>> = Array(size) { Array(size) { null } }

    fun getPiecePosition(position: Position): Piece? {
        return if (position.x in 0 until size && position.y in 0 until size) {
            this.chessboard[position.x][position.y]
        } else {
            null
        }
    }

    fun getPositionPieceEmeny(chessboard: Array<Array<Piece?>>, type: PieceTypeEnum, color: PieceColor): Position?{
        for (x in chessboard.indices) {
            for (y in chessboard[x].indices) {
                val piece = chessboard[x][y]
                if (piece != null && piece.type == type && piece.color != color) {
                    return Position(x, y)
                }
            }
        }
        return null
    }


    private fun addPiece(piece: Piece, position: Position) {
        if (position.x in 0 until size && position.y in 0 until size) {
            this.chessboard[position.x][position.y] = piece
        }
    }

    fun movePiece(currentPosition: Position, targetPosition: Position): Boolean {
        val piece = this.getPiecePosition(currentPosition)
        if (piece != null && targetPosition.x in 0 until size && targetPosition.y in 0 until size) {
            if(piece.type!!.moveValid(currentPosition, targetPosition, this.chessboard, piece.color!!)){
                val targetPiece = this.getPiecePosition(targetPosition)
                if(isEnemy(currentPiece =  piece, targetPiece =  targetPiece)){
                    when(piece.type){
                        PieceTypeEnum.PAWN -> {
                            if(pawnCapture(currentPosition, targetPosition, targetPiece)){
                                this.addPiece(piece, targetPosition)
                                this.removePiece(currentPosition)
                                this.removePiece(currentPosition, false)
                            }
                        }
                        else -> {
                            this.addPiece(piece, targetPosition)
                            this.removePiece(currentPosition)
                            this.removePiece(currentPosition, false)
                        }
                    }
                }else{
                    if(this.chessboard[targetPosition.x][targetPosition.y] == null){
                        this.addPiece(piece, targetPosition)
                        this.removePiece(currentPosition, false)
                    }
                }
            }
        }
        return true
    }

    private fun isEnemy(currentPiece: Piece, targetPiece: Piece?): Boolean {
        return currentPiece != null && targetPiece != null && currentPiece.color != targetPiece.color
    }

    private fun pawnCapture(currentPosition: Position, targetPosition: Position, targetPiece: Piece?): Boolean {
        // Implement capture rule for the pawn: Pawn captures diagonally.
        val dx = abs(currentPosition.x - targetPosition.x)
        val dy = abs(currentPosition.y - targetPosition.y)

        // A pawn can capture diagonally (dx == dy == 1) and must have a target piece.
        return dx == 1 && dy == 1 && targetPiece != null
    }

    private fun removePiece(position: Position, isEnemy: Boolean = true) {
        if(isEnemy){
            this.chessboard[position.x][position.y]!!.isCaptured = true
        }
        this.chessboard[position.x][position.y] = null
    }

    fun createInitialChessboard(): Chessboard {
        this.addPiece(Piece(type = PieceTypeEnum.KING, color = PieceColor.WHITE), position = Position(0, 4))
        this.addPiece(Piece(type = PieceTypeEnum.QUEEN, color = PieceColor.WHITE), position = Position(0, 3))
        this.addPiece(Piece(type = PieceTypeEnum.BISHOP, color = PieceColor.WHITE), position = Position(0, 2))
        this.addPiece(Piece(type = PieceTypeEnum.BISHOP, color = PieceColor.WHITE), position = Position(0, 5))
        this.addPiece(Piece(type = PieceTypeEnum.KNIGHT, color = PieceColor.WHITE), position = Position(0, 1))
        this.addPiece(Piece(type = PieceTypeEnum.KNIGHT, color = PieceColor.WHITE), position = Position(0, 6))
        this.addPiece(Piece(type = PieceTypeEnum.TOWER, color = PieceColor.WHITE), position = Position(0, 0))
        this.addPiece(Piece(type = PieceTypeEnum.TOWER, color = PieceColor.WHITE), position = Position(0, 7))

        for (y in 0 until 8) {
            this.addPiece(Piece(type = PieceTypeEnum.PAWN, color = PieceColor.WHITE), position = Position(x = 1, y = y))
        }

        this.addPiece(Piece(type = PieceTypeEnum.KING, color = PieceColor.BLACK), position = Position(7, 4))
        this.addPiece(Piece(type = PieceTypeEnum.QUEEN, color = PieceColor.BLACK), position = Position(7, 3))
        this.addPiece(Piece(type = PieceTypeEnum.BISHOP, color = PieceColor.BLACK), position = Position(7, 2))
        this.addPiece(Piece(type = PieceTypeEnum.BISHOP, color = PieceColor.BLACK), position = Position(7, 5))
        this.addPiece(Piece(type = PieceTypeEnum.KNIGHT, color = PieceColor.BLACK), position = Position(7, 1))
        this.addPiece(Piece(type = PieceTypeEnum.KNIGHT, color = PieceColor.BLACK), position = Position(7, 6))
        this.addPiece(Piece(type = PieceTypeEnum.TOWER, color = PieceColor.BLACK), position = Position(7, 0))
        this.addPiece(Piece(type = PieceTypeEnum.TOWER, color = PieceColor.BLACK), position = Position(7, 7))

        for (y in 0 until 8) {
            this.addPiece(Piece(type = PieceTypeEnum.PAWN, color = PieceColor.BLACK), position = Position(x = 6, y = y))
        }
        return this
    }

    fun copy(): Chessboard {
        val newChessboard = Chessboard()
        for (x in 0 until size) {
            for (y in 0 until size) {
                newChessboard.chessboard[x][y] = this.chessboard[x][y]
            }
        }
        return newChessboard
    }
}