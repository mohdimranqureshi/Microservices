import React, { Component } from 'react';
import {HashRouter as Router, Route, Switch, Redirect} from 'react-router-dom';
import PrivateRoute from './PrivateRoute';
import LoginPage from './LoginPage';
import TrainingPage from './TrainingPage';
import Header from '../common/header';


import '../resources/css/bootstrap.min.css';
import '../resources/css/font-awesome.min.css';
import '../resources/css/index.css';

class App extends Component{

    render(){
        return(
            <Router>
                 <div>
                 <Route component={Header} />
                 <Switch>
                        <Route path = "/login" component = {LoginPage} />
                        <PrivateRoute path = "/training" component = {TrainingPage} />
                      
                        <Redirect to="/login"/>
                        </Switch>
                 </div>
                 </Router>            
        );
    }
}
export default App;