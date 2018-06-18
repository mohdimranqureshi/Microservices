import React from 'react';
import { connect } from 'react-redux';
import { logOut } from '../actions/login';

let Header = ({ loginDetails, dispatch }) =>{

    let username = loginDetails.userName;
    if(loginDetails && loginDetails.userName) {
        username = loginDetails.userName
    }
    return(
        <header className="navbar header-bar headerbg">
              <div className="container-fluid fixed-width">
              {loginDetails.loggedIn ?              
                    <ul className="nav navbar-nav navbar-right">
                        <li className = "userData">Hi, {username}</li>
                        <li onClick={e => {
                               dispatch(logOut())
                            } }><div className = "signout-btn">Sign Out</div></li>
                        </ul>
                        : null }
                  </div>
            </header>
    )
}
const mapStateToProps = (state) =>{
    const { loginDetails } = state.login;
    return{
        loginDetails
    }
};
export default connect(mapStateToProps)(Header);