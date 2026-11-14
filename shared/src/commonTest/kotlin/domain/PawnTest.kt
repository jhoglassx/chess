package domain

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PawnTest {
    private val pawn = Pawn()

    private fun board() = Chessboard().createInitialChessboard().chessboard

    @Test
    fun whitePawnCanMoveTwoSquaresFromItsStartRow() {
        assertTrue(pawn.moveValid(Position(1, 4), Position(3, 4), board(), PieceColor.WHITE))
    }

    @Test
    fun blackPawnCanMoveTwoSquaresFromItsStartRow() {
        assertTrue(pawn.moveValid(Position(6, 4), Position(4, 4), board(), PieceColor.BLACK))
    }

    @Test
    fun pawnCannotMoveTwoSquaresAfterItLeftTheStartRow() {
        val board = board()
        assertFalse(pawn.moveValid(Position(2, 4), Position(4, 4), board, PieceColor.WHITE))
        assertFalse(pawn.moveValid(Position(5, 4), Position(3, 4), board, PieceColor.BLACK))
    }

    @Test
    fun pawnCannotJumpOverAPiece() {
        val board = board()
        board[2][4] = Piece(type = PieceTypeEnum.KNIGHT, color = PieceColor.BLACK)
        assertFalse(pawn.moveValid(Position(1, 4), Position(3, 4), board, PieceColor.WHITE))
    }

    @Test
    fun pawnMovesOneSquareForwardAndNotBackwards() {
        val board = board()
        assertTrue(pawn.moveValid(Position(1, 0), Position(2, 0), board, PieceColor.WHITE))
        assertTrue(pawn.moveValid(Position(6, 0), Position(5, 0), board, PieceColor.BLACK))
        assertFalse(pawn.moveValid(Position(2, 0), Position(1, 0), board, PieceColor.WHITE))
    }

    @Test
    fun pawnCapturesDiagonallyOnly() {
        val board = board()
        board[2][5] = Piece(type = PieceTypeEnum.PAWN, color = PieceColor.BLACK)
        assertTrue(pawn.moveValid(Position(1, 4), Position(2, 5), board, PieceColor.WHITE))
        assertFalse(pawn.moveValid(Position(1, 4), Position(2, 6), board, PieceColor.WHITE))
    }
}
