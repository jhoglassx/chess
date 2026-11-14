# Chess game using Compose Multiplatform

A chess board written once in Kotlin with Compose Multiplatform, running on Android and iOS.

## Screenshots

| Start | Mid-game | Capture |
|:---:|:---:|:---:|
| <img src="docs/screenshots/start.jpg" width="240" alt="Initial chess position on Android"> | <img src="docs/screenshots/midgame.jpg" width="240" alt="Position after a few pawn moves"> | <img src="docs/screenshots/capture.jpg" width="240" alt="A white pawn capturing a black pawn diagonally"> |

- **Playing:** tap a piece, then tap the destination square.
- **Rules:** each piece type (pawn, rook, knight, bishop, queen, king) has its own move validation in `shared/src/commonMain/kotlin/domain/`.
- **Shared UI:** the board is a single Compose Multiplatform composable in `shared/src/commonMain/kotlin/App.kt`.
