import React from "react"
import {Piece, Coor} from "../types/index"
import {isMove} from "../utils/index"


export class Board extends React.Component {

    props: {
        board: Piece[][],
        moves: Coor[],
        selectedPieceId: ?number,
        clickTileAction: Function
    }

    render() {
        return (
            <div style={styles.board}>
                {this.props.board.map((row, i) => <Row key={i} row={row} i={i} {...this.props} />)}
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
const Tile = ({i, j, piece, selectedPieceId, moves, board, clickTileAction}) => {
    const tileStyles = {
        ...styles.tile,
        backgroundColor: tileColor(i, j),
        border: (piece && piece.id === selectedPieceId) || isMove([i, j], moves) ? "4px solid #63D863" : "inherit"
    }
    return (
        <div style={tileStyles} onClick={() => clickTileAction(board, moves, selectedPieceId, [i, j])}>
            {piece ? <img src={pieceImage(piece.code)} style={styles.pieceImage}/> : null}
        </div>
    )
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
