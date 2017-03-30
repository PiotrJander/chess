import React from "react"
import {Piece, Coor} from "../types/index"
import {coorEquals} from "../utils/index"


export class Board extends React.Component {

    props: {
        board: Piece[][],
        moves: Coor[],
        selectedPieceId: ?number,
        toggleTileSelectionAction: Function
    }

    render() {
        const {board, ...otherProps} = this.props;
        return (
            <div style={styles.board}>
                {board.map((row, i) => <Row key={i} row={row} i={i} {...otherProps} />)}
            </div>
        )
    }
}


// const Row = ({row, i} : {row: Piece[], i: number}): React.Component => (
const Row = ({row, i, ...otherProps}) => {
    return (
        <div style={{display: "flex"}}>
            {row.map((piece, j) => <Tile key={2^i * 3^j} i={i} j={j} piece={piece} {...otherProps} />)}
        </div>
    )
}


// const Tile = ({i, j, piece} : {i: number, j: number, piece: Piece}) => (
const Tile = ({i, j, piece, selectedPieceId, moves, toggleTileSelectionAction}) => {
    const tileStyles = {
        ...styles.tile,
        backgroundColor: tileColor(i, j),
        border: (piece && piece.id === selectedPieceId) || isMove([i, j], moves) ? "2px dotted" : "inherit"
    }
    return (
        <div style={tileStyles} onClick={() => toggleTileSelectionAction([i, j])}>
            {piece ? <img src={pieceImage(piece.code)} style={styles.pieceImage}/> : null}
        </div>
    )
}


function isMove(coor: Coor, moves: Coor[]): boolean {
    return moves.some(m => coorEquals(coor, m));
}


function pieceImage(s) {
    return require(`../svg/Chess_${s}t45.svg`)
}


function tileColor(i, j) {
    return i % 2 == j % 2 ? colors.blackTile : colors.whiteTile
}


const colors = {
    blackTile: "#FFCE9E",
    whiteTile: "#D18B47"
}


const styles = {
    board: {
        width: "800px",
        height: "800px",
        display: "flex",
        flexWrap: "wrap"
    },
    tile: {
        width: "100px",
        height: "100px",
        boxSizing: 'border-box'
    },
    pieceImage: {
        width: "100%"
    }
}
