import React from 'react';
import  {
    Route,
    Redirect
  } from 'react-router-dom';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
//import { checkLoginStatus } from '../util/checkLoginStatus';
 
let PrivateRoute = ({component: Component, loginDetails, dispatch, history, ...rest}) =>(

    <Route {...rest} render={props => (
      //  checkLoginStatus(loginDetails, dispatch) ? (
       loginDetails.loggedIn ? (
          <Component {...props}/>
        ) : (
          <Redirect to={{
            pathname: '/login',
            state: { from: props.location }
          }}/>
        )
      )}/>
)
const mapStateToProps = (state) =>{
    const { loginDetails } = state.login;
    return{
        loginDetails
    }
};
PrivateRoute = withRouter(connect(mapStateToProps)(PrivateRoute))
export default PrivateRoute;
