export type Piece = {
    type: "King" | "Queen" | "Bishop" | "Knight" | "Rook" | "Pawn",
    color: "WHITE" | "BLACK",
    code: string,
    id: number
}

export type Coor = [number, number]

export type Move = {
    pieceId: number,
    from: Coor,
    to: Coor
}