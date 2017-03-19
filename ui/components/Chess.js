import React from 'react'
import _ from 'lodash'


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


/*
* Ok now let's place some some figures on the board
*
* Then we need an interface to say move <pieceId> from <fieldId> to <fieldId>
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
