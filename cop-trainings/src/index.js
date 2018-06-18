import React from 'react'
import { render } from 'react-dom'
import { createStore, applyMiddleware } from 'redux'
import { Provider } from 'react-redux'
import thunk from 'redux-thunk'
import { createLogger } from 'redux-logger'
import rootReducer from './reducers'
import App from './containers/App'

/*Thunk middleware to allow use of Async Actions*/
const middleware = [ thunk ]
if (process.env.NODE_ENV !== 'production') {
  middleware.push(createLogger())
}

/*Create Redux store by supplying reducer and middleware*/
const store = createStore(
  rootReducer,
  applyMiddleware(...middleware)
)

/*Provider to make redux store accessible in all components (with the help of connect)*/
render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById('root')
)
