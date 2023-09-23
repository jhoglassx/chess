package domain

data class Piece (
    val type: PieceTypeEnum? = null,
    val color: PieceColor? = null,
    var isCaptured: Boolean = false,
)
