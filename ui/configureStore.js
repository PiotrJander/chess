import { createStore, applyMiddleware, compose } from 'redux'
import chess from './ducks/chess';
import { combineReducers } from 'redux';
import thunkMiddleware from 'redux-thunk'

export default function configureStore(initialState) {
  const store = createStore(
      combineReducers({chess}),
      initialState,
      compose(
          applyMiddleware(thunkMiddleware)
      //   window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
      )
  )

  //  TODO enable
  // if (module.hot) {
  //   // Enable Webpack hot module replacement for reducers
  //   module.hot.accept('./ducks', () => {
  //     const nextReducer = require('./ducks').default
  //     store.replaceReducer(nextReducer)
  //   })
  // }

  return store
}
