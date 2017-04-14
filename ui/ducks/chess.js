import {createAction} from "redux-actions"
import {Piece, Move, Coor} from "../types/index"
import {isMove} from "../utils/index"


const prefix = "chess/"

// Actions
const REQUEST_NEW_GAME = prefix + 'REQUEST_NEW_GAME'
const RECEIVE_NEW_GAME = prefix + 'RECEIVE_NEW_GAME'
const FAIL_NEW_GAME = prefix + 'FAIL_NEW_GAME'
const REQUEST_NEXT_MOVE = prefix + 'REQUEST_NEXT_MOVE'
const RECEIVE_NEXT_MOVE = prefix + 'RECEIVE_NEXT_MOVE'
const FAIL_NEXT_MOVE = prefix + 'FAIL_NEXT_MOVE'

const SET_SELECTED_PIECE_ID = prefix + 'SET_SELECTED_PIECE_ID'

type State = {
    board: Piece[][],
    validMoves: { [number]: Move[] },
    message: string,
    selectedPieceId: ?number
}

type Action = {
    type: string,
    payload: Object
}


const initState: State = {
    board: (new Array(8)).fill((new Array(8)).fill(null)),
    message: '',
    selectedPieceId: null,
    validMoves: {}
}


// Reducer
export default function reducer(state: State = initState,
                                action: Action = {}): State {
    switch (action.type) {
        case REQUEST_NEW_GAME:
        case REQUEST_NEXT_MOVE:
            return {...state, message: "Waiting for the engine"}
        case RECEIVE_NEW_GAME:
        case RECEIVE_NEXT_MOVE:
            const {board, validMoves} = action.payload
            return {...initState, board, validMoves, message: ''}
        case FAIL_NEW_GAME:
        case FAIL_NEXT_MOVE:
            return {...state, message: 'Communication with the engine failed'}
        case SET_SELECTED_PIECE_ID:
            return {...state, selectedPieceId: action.payload}
        default:
            return state
    }
}

// Action Creators
const requestNewGame = createAction(REQUEST_NEW_GAME)
const receiveNewGame = createAction(RECEIVE_NEW_GAME)
const failNewGame = createAction(FAIL_NEW_GAME)
const requestNextMove = createAction(REQUEST_NEXT_MOVE)
const receiveNextMove = createAction(RECEIVE_NEXT_MOVE)
const failNextMove = createAction(FAIL_NEXT_MOVE)

// export const s = createAction(SET_SELECTED_PIECE_ID)

export function newGameAction(): Function {
    return dispatch => {
        dispatch(requestNewGame())
        console.log(process.env)
        return fetch(`http://${process.env.SERVER}:9090/new-game`, {method: 'POST'})
            .then(response => response.json())
            .then(payload => dispatch(receiveNewGame(payload)))
            .catch(e => dispatch(failNewGame()))
    }
}

const setSelectedPieceIdAction = createAction(SET_SELECTED_PIECE_ID)

export function clickTileAction(board: Piece[][], moves: Coor[], selectedPieceId: number, [i, j]: Coor): Function {
    return dispatch => {
        const pieceOnClickedTile = board[i][j]

        if (pieceOnClickedTile && pieceOnClickedTile.color === "WHITE") {
            dispatch(
                setSelectedPieceIdAction(
                    selectedPieceId === pieceOnClickedTile.id ? null : pieceOnClickedTile.id
                )
            )
        } else if (isMove([i, j], moves)) {
            dispatch(nextMoveAction(selectedPieceId, [i, j]))
        } else {
            dispatch(setSelectedPieceIdAction(null))
        }
    }
}

export function nextMoveAction(selectedPieceId: number, [i, j]: Coor): Function {
    return dispatch => {
        dispatch(requestNextMove())
        return fetch(
            `http://${process.env.SERVER}:9090/next-move`,
            {
                method: 'POST',
                body: `${selectedPieceId} ${i} ${j}`
            }
        )
            .then(response => response.json())
            .then(payload => dispatch(receiveNextMove(payload)))
            .catch(e => dispatch(failNextMove()))
    }
}
