
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import domain.Chessboard
import domain.Piece
import domain.PieceColor
import domain.Position
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun App() {
    MaterialTheme {
        board()
    }
}

expect fun getPlatformName(): String

// The piece artwork is black; white pieces are the same image with its RGB channels inverted.
// Applied as a color filter on the image only, so the square behind the piece keeps its own color.
private val invertColors = ColorFilter.colorMatrix(
    ColorMatrix(
        floatArrayOf(
            -1f, 0f, 0f, 0f, 255f,
            0f, -1f, 0f, 0f, 255f,
            0f, 0f, -1f, 0f, 255f,
            0f, 0f, 0f, 1f, 0f,
        )
    )
)

@OptIn(ExperimentalResourceApi::class)
@Composable
fun board() {
    var chessboard by remember { mutableStateOf(Chessboard().createInitialChessboard()) }

    var selectedPieceToMove by remember { mutableStateOf<Piece?>(Piece()) }
    var rememberPosition by remember { mutableStateOf<Position?>(null) }


    LazyVerticalGrid(
        columns = GridCells.Fixed(chessboard.size),
    ) {
        for (row in 0 until 8) {
            items(8) { col ->
                val position = Position(row, col)
                val piece = chessboard.getPiecePosition(position)
                val isDark = (row + col) % 2 == 0
                val backgroundColor = if (isDark) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f) // square cells: width comes from the grid column
                        .background(backgroundColor)
                        .clickable {
                            if (selectedPieceToMove != null && rememberPosition != null) {
                                val targetPosition = Position(row, col)
                                if (chessboard.movePiece(rememberPosition!!, targetPosition)) {
                                    // Successfully moved the piece
                                    selectedPieceToMove = null
                                    rememberPosition = null
                                    // Trigger recomposition to update UI
                                    chessboard = chessboard.copy() // Create a new instance to trigger recomposition
                                } else {
                                    // Invalid move, reset selection
                                    selectedPieceToMove = null
                                    rememberPosition = null
                                }
                            } else {
                                val clickedPiece = chessboard.getPiecePosition(position)
                                if (clickedPiece != null) {
                                    // Select the clicked piece for movement
                                    selectedPieceToMove = clickedPiece
                                    rememberPosition = position
                                }
                            }
                        }
                ) {
                    piece?.let { piece ->
                        piece.type?.img?.let {
                            Image(
                                painter = painterResource(it),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                colorFilter = if (piece.color == PieceColor.WHITE) invertColors else null,
                            )
                        }
                    }
                }
            }
        }

    }
}

private fun createInitialChessboard(){
    Chessboard().createInitialChessboard()
}