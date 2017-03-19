import { createAction } from 'redux-actions';


const prefix = "chess/"

// Actions
const NEW_GAME = prefix + 'NEW_GAME'
const NEXT_MOVE = prefix + 'NEXT_MOVE'

// Reducer
export default function reducer(
    state = {
        board: new Array(64)
    },
    action = {}
) {
    switch (action.type) {
        case NEW_GAME:
        case NEXT_MOVE:
            return {...state, board: action.payload}
        default:
            return state
    }
}

// Action Creators
export const newGame = createAction(NEW_GAME)
export const nextMove = createAction(NEXT_MOVE)
