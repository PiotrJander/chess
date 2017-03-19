import React from 'react'
import _ from 'lodash'


export class Board extends React.Component {

    render() {

        console.log(this.props.board[3])

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
    }
}
