import React from "react"


export class Board extends React.Component {

    render() {

        return (
            <div style={styles.board}>
                {this.props.board.map((row, i) => <Row row={row} i={i}/>)}
            </div>
        )

        return (
            <div style={styles.board}>
                {this.props.board.map((piece, i) => (
                    <div key={i} style={{...styles.tile, backgroundColor: tileColor(i)}}>
                        {piece ? <img src={pieceImage(piece)} style={styles.pieceImage} /> : null}
                    </div>
                ))}
            </div>
        )
    }
}


const Row = ({row, i}) => (
    <div>
        row.map((piece, j) => <Tile i={i} j={j} piece={piece}/>)
    </div>
)


const Tile = ({i, j, piece}) => (
    <div key={2^i * 3^j} style={{...styles.tile, backgroundColor: tileColor(i, j)}}>
        {piece ? <img src={pieceImage(piece.piece)} style={styles.pieceImage}/> : null}
    </div>
)



const pieceImage = s => {
    return require(`../svg/Chess_${s}t45.svg`)
}


const tileColor = (i, j) => i % 2 == j % 2 ? colors.blackTile : colors.whiteTile


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
        height: "100px"
    },
    pieceImage: {
        width: "100%"
    }
}
