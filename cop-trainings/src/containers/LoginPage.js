import React, { Component } from 'react'; 
import { connect } from 'react-redux';
import { withRouter } from 'react-router';
import Loader from 'react-loader';
import { login, clearError, logOut } from '../actions/login';
import {  options } from '../constants/appConst';

class Login extends Component{

    constructor(props){
        super(props);
        this.authenticate = this.authenticate.bind(this);
    }

    componentDidMount(){
        let { dispatch, loginDetails } = this.props;
        if (loginDetails.loggedIn)
            this.props.dispatch(logOut());
        this.setState({
            error: false
        })
    }
 
    authenticate(e){
        e.preventDefault();
        var userName = this.refs.userName.value;
        var password = this.refs.password.value;
        this.props.dispatch(login({userName, password}));
    }

    clearHandler = () => {
        this.props.dispatch(clearError())
      }

    render(){
        const {  isFetching, loginDetails } = this.props;
        return(

            <div>


                
<div className="wrapper">
    <form className="form-signin">  
        <fieldset>  
      <legend>Login</legend>
      <input type="text" className="form-control" name="username" placeholder="User Name" ref = "userName" required="" autoFocus="" />
      <input type="password" className="form-control" name="password" placeholder="Password" ref= "password" required=""/>      
      <input type="submit" value = "Login" className="btn btn-lg btn-primary btn-block" onClick= {this.authenticate} /> 
      
      {loginDetails.error ? <div className="alert alert-danger alert-dismissable">
                                            <a onClick={() => this.clearHandler()} className="close" data-dismiss="alert" aria-label="close">&times;</a><strong>Error:</strong> {loginDetails.errorMessage}
                                        </div> : ''}
      
      <Loader loaded={!isFetching} options={options} className="spinner overlay" />
    </fieldset>
    </form>
  </div>

                </div>

        );}
}
const mapStateToProps = (state) =>{
    const {loginDetails, isFetching} = state.login;
    return{
        loginDetails,
        isFetching
    }
};
Login = withRouter(connect(mapStateToProps)(Login))
export default Login;