import React from 'react'
import _ from 'lodash'
// import knight from '../images/kdt45.png'


export class Chess extends React.Component {

    // constructor() {
    //     super()
    //     this.blackTileColor = "#FFCE9E"
    // }

    render() {

        const isBlack = i => {
            const row = Math.floor(i / 8);
            const col = i % 8;
            return row % 2 == col % 2
        }

        return (
            <div style={styles.board}>
                {_.range(64).map(i => (
                    <div key={i} style={{...styles.tile, backgroundColor: isBlack(i) ? colors.blackTile : colors.whiteTile}}>

                    </div>
                ))}
            </div>
        )
    }
}


/*  <img src={knight} alt="" style={{width: "100%"}} />
* Ok now let's place some some figures on the board
*
* Then we need an interface to say move <pieceId> from <fieldId> to <fieldId>
*
* Maybe we don't need websockets.
*
* Maybe just request: I made this move, what is your move?
*
* Ok great now we know how to display
*
* We keep the protocol simple and assume that we can only say move <pieceId> from <fieldId> to <fieldId> .
*
* Therefore the UI must know the initial pieces layout. Also field naming and piece naming should be consistent.
*
* This is time for us to use redux. We keep the board state in redux and display it.
*
* Piece naming black_pawn_1, white_pawn_2 etc
*
* Field naming: a3 b6 etc
*
* For now we assume ui makes no moves, only reads moves from the server
*
* Therefore the state could be an object mapping field names to piece names
*
* We also need a mapping from piece id to piece (color / type)
*
* We might want to download the pieces for now
*
* for piece in "kqrbnp"
*   for color in "dl"
*       https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/Chess_<{}{}>t45.svg/240px-Chess_{}{}t45.svg.png
*       and save as {}{}.png
* */


const colors = {
    blackTile: "#FFCE9E",
    whiteTile: "#D18B47"
}


const styles = {
    board: {
        width: "800px",
        height: "800px",
        margin: "0 auto",
        display: "flex",
        flexWrap: "wrap"
    },
    tile: {
        width: "100px",
        height: "100px"
    }
}
