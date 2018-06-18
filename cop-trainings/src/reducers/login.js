
import { LOGIN, LOGIN_SUCCESS,LOGIN_ERROR,LOG_OUT,CLEAR_ERROR } from '../actions/login';

const initialState = {
    isFetching:false,
    loginDetails:{
        loggedin: false,
        error: false,
        userName:'',
        response:null
    }
}
/*Reducer functions for LoginPage*/
export default function login(state= initialState, action){
    //debugger;
    switch(action.type){
        case LOGIN:
        return{
            ...state,
            isFetching: true
        }
            case LOGIN_ERROR:
            return{
                ...state,
                isFetching: false,
                loginDetails:{
                    ...state.loginDetails,
                    loggedIn: false,
                    error: true,
                    errorMessage: action.error.response.data
                }
            }
            case LOGIN_SUCCESS:
            //debugger;
            return{
                ...state,
                isFetching: false,
                loginDetails:{
                    ...state.loginDetails,
                    loggedIn: true,
                    error: false,
                    userName: action.userName,
                    response: action.response
                }
            }
            case LOG_OUT:
            return{
                ...state,
                isFetching: false,
                loginDetails:{
                    ...state.loginDetails,
                    loggedIn: false
                }
            }
            case CLEAR_ERROR:
            return{
                ...state,
                isFetching: false,
                loginDetails:{
                    loggedIn: false,
                    error: false
                }
            }

            default:
            return state

        }
    }