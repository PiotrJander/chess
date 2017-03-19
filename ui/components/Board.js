import React from 'react'
import _ from 'lodash'


export class Board extends React.Component {

    render() {

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

const pieceImage = s => {
    return require(`../svg/Chess_${s}t45.svg`)
}

const isBlack = i => {
    const row = Math.floor(i / 8);
    const col = i % 8;
    return row % 2 == col % 2
}

const tileColor = i => isBlack(i) ? colors.blackTile : colors.whiteTile


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
