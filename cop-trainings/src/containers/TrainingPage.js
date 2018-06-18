import React, { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router';
import AddTraining from '../components/training/addTraining';
import EditTraining from '../components/training/editTraining';
import { getTrainingList, editTrainingDetails, addTrainingDetails, findTrainingDetails } from '../actions/training';
import TrainingList from '../components/training/trainingList';
import TrainingBtn from '../components/training/trainingBtn';

class TrainingPage extends Component{
    constructor(props){
        super(props);
        this.state = {
            hAddEdit : false,
            topicNameList:[],
            hList: true,
            trngBtn: true,
            editTraining: false
          
        };

    }
    componentDidMount(){
     this.getTrainingDetails();
    }

    getTrainingDetails = () =>{
      this.props.dispatch(getTrainingList(this.props.loginDetails));
    }

    changePage = () =>{
        
        this.setState({
            hAddEdit : true,
            hList: false,
            trngBtn: false,
            editTraining: false
        })
    }

    goBack = () =>{

        this.setState({
            hAddEdit : false,
            hList: true,
            trngBtn: true,
            editTraining: false
        })
    }

    editDetails = (topicName) =>{
        this.setState({
            hAddEdit : false,
            editTraining: true,
            hList: false,
            trngBtn: false
        })
        this.props.dispatch(editTrainingDetails(topicName));
    }
    addTrainingDetails = (trainingList) =>{
        this.props.dispatch(addTrainingDetails(trainingList));
    }

    searchText = (searchtext, copId) =>{
        this.props.dispatch(findTrainingDetails(searchtext, copId));
    }
    render(){
        const { hAddEdit, hList, trngBtn, editTraining } = this.state;
        const { loginDetails,  topicNameList, dispatch, editTrainingDetails} = this.props;
        let copId = this.props.loginDetails.response.data[0].copId;
        return(

            <div>

               { trngBtn && copId != '6'? <TrainingBtn  changePage = { this. changePage}/> : null }
               { hAddEdit ?  <AddTraining addBtn = { false } goBack = { this. goBack } addTrainingDetails = { this.addTrainingDetails} loginDetails = { loginDetails } getTrainingDetails = { this.getTrainingDetails }/>  : ''}
              
               { hList ?  <TrainingList topicNameList = { topicNameList } editDetails = { this. editDetails } searchText = { this.searchText } loginDetails = { loginDetails }/> : null }

               { editTraining ?  <EditTraining editDetails = { this. editDetails } goBack = { this. goBack } editTrainingDetails = { editTrainingDetails } /> : null }

                </div>
        );
    }
}
const mapStateToProps = (state) =>{
    const { loginDetails } = state.login;
    const { topicNameList, editTrainingDetails } = state.training;    
    return{
        loginDetails,
        topicNameList,
        editTrainingDetails
    }
};
TrainingPage = withRouter(connect(mapStateToProps)(TrainingPage))
export default TrainingPage;