import axios from 'axios';
export const LOGIN = 'LOGIN';
export const LOGIN_SUCCESS = 'LOGIN_SUCCESS';
export const LOGIN_ERROR = 'LOGIN_ERROR';
export const LOG_OUT = 'LOG_OUT';
export const CLEAR_ERROR = 'CLEAR_ERROR';

/*Action to show loading while fetching response from Login API*/
export const loginFetch = () =>({

    type: LOGIN
})
/*Action to update app state for successfull login*/
export const loginSuccess = (userName, response) =>{
    return{
        type: LOGIN_SUCCESS,
        userName,
        response
    }
}
/*Action to display login error*/
export const loginError = (userName, error) =>{
    return{
        type: LOGIN_ERROR,
        userName,
        error
    }
}
/*Async Event to hit Login API*/
export const login = (option) => dispatch =>{
    dispatch(loginFetch())
    let request = {
        method : 'GET',
        headers: {'Content-Type': 'application/json'},
        url: 'http://xen-821.xavient.com:7070/login/validateUser?'+'userName='+option.userName+'&'+'password='+option.password
    };
    axios(request).then( response => {

        dispatch(loginSuccess(option.userName, response))
      /*  localStorage.setItem('resObj',JSON.stringify({
			response: response.data		
		}));*/
        window.location.href = '/#/training';	
    }).catch(function(error){
    //    localStorage.removeItem('resObj')
        dispatch(loginError(option.userName, error));
    })
}
/*Action to handle Logout*/
export const logOut = () =>{
    return{
        type: LOG_OUT
    }
}
/*Action to clear Login error*/
export const clearError = () =>{
    return{
        type: CLEAR_ERROR
    }
}
