export type Piece = {
    type: "King" | "Queen" | "Bishop" | "Knight" | "Rook" | "Pawn",
    color: "WHITE" | "BLACK",
    code: string,
    id: number
}
