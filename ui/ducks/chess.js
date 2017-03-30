import {createAction} from "redux-actions"


const prefix = "chess/"

// Actions
const REQUEST_NEW_GAME = prefix + 'REQUEST_NEW_GAME'
const RECEIVE_NEW_GAME = prefix + 'RECEIVE_NEW_GAME'
const FAIL_NEW_GAME = prefix + 'FAIL_NEW_GAME'
const REQUEST_NEXT_MOVE = prefix + 'REQUEST_NEXT_MOVE'
const RECEIVE_NEXT_MOVE = prefix + 'RECEIVE_NEXT_MOVE'
const FAIL_NEXT_MOVE = prefix + 'FAIL_NEXT_MOVE'

// Reducer
export default function reducer(state: {
    board: any,
    message: string
} = {
        board: (new Array(64)).fill(null),
        message: ''
    },
                                action = {}
) {
    switch (action.type) {
        case REQUEST_NEW_GAME:
        case REQUEST_NEXT_MOVE:
            return {...state, message: "Waiting for the engine"}
        case RECEIVE_NEW_GAME:
        case RECEIVE_NEXT_MOVE:
            return {...state, board: action.payload, message: ''}
        case FAIL_NEW_GAME:
        case FAIL_NEXT_MOVE:
            return {...state, message: 'Communication with the engine failed'}
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

export const newGameAction = () => dispatch => {
    dispatch(requestNewGame())
    return fetch('http://localhost:4567/new-game')
        .then(response => response.json())
        .then(payload => dispatch(receiveNewGame(payload)))
        .catch(e => dispatch(failNewGame()))
}

export const nextMoveAction = () => dispatch => {
    dispatch(requestNextMove())
    return fetch('http://localhost:4567/next-move')
        .then(response => response.json())
        .then(payload => dispatch(receiveNextMove(payload)))
        .catch(e => dispatch(failNextMove()))
}
