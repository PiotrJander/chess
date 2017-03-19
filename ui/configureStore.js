import { createStore } from 'redux'
import chess from './ducks/chess';
import { combineReducers } from 'redux';

export default function configureStore(initialState) {
  const store = createStore(combineReducers({chess}), initialState)

  // if (module.hot) {
  //   // Enable Webpack hot module replacement for reducers
  //   module.hot.accept('./ducks', () => {
  //     const nextReducer = require('./ducks').default
  //     store.replaceReducer(nextReducer)
  //   })
  // }

  return store
}
