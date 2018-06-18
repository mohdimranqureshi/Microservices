import { combineReducers } from 'redux'
import login from './login'
import training from './training'

const rootReducer = combineReducers({
    login,
    training
  })
  
  export default rootReducer