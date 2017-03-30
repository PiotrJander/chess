import {createAction} from "redux-actions"
import {Piece, Move} from "../types/index"


const prefix = "chess/"

// Actions
const REQUEST_NEW_GAME = prefix + 'REQUEST_NEW_GAME'
const RECEIVE_NEW_GAME = prefix + 'RECEIVE_NEW_GAME'
const FAIL_NEW_GAME = prefix + 'FAIL_NEW_GAME'
const REQUEST_NEXT_MOVE = prefix + 'REQUEST_NEXT_MOVE'
const RECEIVE_NEXT_MOVE = prefix + 'RECEIVE_NEXT_MOVE'
const FAIL_NEXT_MOVE = prefix + 'FAIL_NEXT_MOVE'

const TOGGLE_PIECE_SELECTION = prefix + 'TOGGLE_PIECE_SELECTION'

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


// Reducer
export default function reducer(state: State = {
    board: (new Array(8)).fill((new Array(8)).fill(null)),
    message: '',
    selectedPieceId: null,
    validMoves: {}
    },
                                action: Action = {}): State {
    switch (action.type) {
        case REQUEST_NEW_GAME:
        case REQUEST_NEXT_MOVE:
            return {...state, message: "Waiting for the engine"}
        case RECEIVE_NEW_GAME:
        case RECEIVE_NEXT_MOVE:
            const {board, validMoves} = action.payload
            return {...state, board, validMoves, message: ''}
        case FAIL_NEW_GAME:
        case FAIL_NEXT_MOVE:
            return {...state, message: 'Communication with the engine failed'}
        case TOGGLE_PIECE_SELECTION:
            const [i, j] = action.payload;
            const pieceOnClickedTile = state.board[i][j]
            if (pieceOnClickedTile && pieceOnClickedTile.color === "WHITE") {
                return {
                    ...state,
                    selectedPieceId: state.selectedPieceId === pieceOnClickedTile.id ? null : pieceOnClickedTile.id
                }
            } else {
                return state  // noop
            }
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

export const toggleTileSelectionAction = createAction(TOGGLE_PIECE_SELECTION)

export function newGameAction(): Function {
    return dispatch => {
        dispatch(requestNewGame())
        return fetch('http://localhost:4567/new-game')
            .then(response => response.json())
            .then(payload => dispatch(receiveNewGame(payload)))
            .catch(e => dispatch(failNewGame()))
    }
}

export function nextMoveAction(): Function {
    return dispatch => {
        dispatch(requestNextMove())
        return fetch('http://localhost:4567/next-move')
            .then(response => response.json())
            .then(payload => dispatch(receiveNextMove(payload)))
            .catch(e => dispatch(failNextMove()))
    }
}
