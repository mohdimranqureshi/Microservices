import { loginSuccess } from '../actions/login';

/*Function to check whether user is logged in when he hits the pages directly*/

export const checkLoginStatus = (loginDetails, dispatch) =>{

    if (!loginDetails.loggedIn){

        let resObj = JSON.parse(localStorage.getItem('resObj'));
        if(resObj){
            dispatch(loginSuccess(
                resObj.userName,
                resObj.response
            ))
            return true;
        }else{
            return false;
        }
    }

    return true;
}