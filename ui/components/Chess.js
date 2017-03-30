import React from "react"
import {Board} from "./Board"
import {connect} from "react-redux"
import * as actions from "../ducks/chess"
import {Piece, Coor} from "../types/index"


class Chess1 extends React.Component {

    props: {
        board: Piece[][],
        selectedTile: ?Coor,
        message: string,
        newGameAction: Function,
        nextMoveAction: Function,
        toggleTileSelectionAction: Function
    }

    render() {
        const {newGameAction, nextMoveAction, message, ...boardProps} = this.props
        return (
            <div style={styles.main}>
                <p>
                    <button onClick={newGameAction}>New game</button>
                    <button onClick={nextMoveAction}>Next move</button>
                    <span>{message}</span>
                </p>
                <Board {...boardProps} />
            </div>
        )
    }
}

export const Chess = connect(
    s => s.chess,
    actions
)(Chess1)

const styles = {
    main: {
        width: "800px",
        margin: "0 auto"
    }
}