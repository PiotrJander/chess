import 'babel-polyfill'
import React from 'react'
import { render } from 'react-dom'
import { Provider } from 'react-redux'
import configureStore from './configureStore'
import 'normalize.css/normalize.css'
import {Chess} from './components/Chess'


const store = configureStore()

render(
  <Provider store={store}>
      <Chess />
  </Provider>,
  document.getElementById('root')
)
