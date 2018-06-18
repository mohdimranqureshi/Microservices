import React , {  Component } from 'react';
import ReactJsSearchBox from 'reactjs-search-box';

class TrainingList extends Component{

    editDetails = (topicName) =>{
        this.props.editDetails(topicName);
    }

    searchText = (copId) =>{
       let searchText = this.refs.searchText.value; 
     //   alert('in SearchText' +searchText)
        this.props.searchText(searchText,copId);
    }

    renderResult = () =>{
        
    }
     render(){

        let response = this.props;
        let copId = this.props.loginDetails.response.data[0].copId;
        let abc = null;
        let xyz = null;
        const topicName = Object.values(response.topicNameList);

        if(topicName == undefined || topicName == null){
            return abc = "No Record Found"
        }else{
        { topicName.map((topicNameList) => {
            if(topicNameList.topicNameList != undefined){
            abc =  topicNameList.topicNameList.map((data) =>{
            
                return(
                    <div className = "trngData col-md-5" key = {data.topicName}>
                    <span className = "col-md-6">Topic Name: {data.topicName}</span>
                    <span className = "col-md-6">Topic URL: {data.url}</span><br />
                    <span className = "col-md-12 trngLvl">Training Level: {data.trainingLevel}</span>
                    <br/>
                  { xyz = data.trainerDetails.map((abc) =>{ 
                       return(
                       <div key = {abc.trainerName}>
                       <span className = "col-md-5">Trainer Name: {abc.trainerName}</span>
                    <span className = "col-md-5">Trainer Prefrence: {abc.trainerPrefrence}</span><br/>
                       </div>
                       )
                   })
                  }
                  <div className = 'btn btn-primary editCenter' onClick = { e => { this.editDetails(data.topicName)}}>Edit</div>
                    </div>
                ) 
            })
        }else{
            abc =  topicNameList.map((data) =>{
                
                    return(
                        <div className = "trngData col-md-5" key = {data.topicName}>
                        <span className = "col-md-6">Topic Name: {data.topicName}</span>
                        <span className = "col-md-6">Topic URL: {data.url}</span><br />
                        <span className = "col-md-12 trngLvl">Training Level: {data.trainingLevel}</span>
                        <br/>
                      { xyz = data.trainerDetails.map((abc) =>{ 
                           return(
                           <div key = {abc.trainerName}>
                           <span className = "col-md-5">Trainer Name: {abc.trainerName}</span>
                        <span className = "col-md-5">Trainer Prefrence: {abc.trainerPrefrence}</span><br/>
                           </div>
                           )
                       })
                      }
                      {copId !='6'? <div className = 'btn btn-primary editCenter' onClick = { e => { this.editDetails(data.topicName)}}>Edit</div> : ""}
                        </div>
                    ) 
                })
        }
        });}}
        return(
            <div>
                <div>
                <input className = "searchTxt" ref = "searchText" placeholder = "Enter Topic name" onChange = {e => {this.searchText(copId)}}></input>
               <span className="glyphicon glyphicon-search serachIcn"></span>
               </div>
            {abc}
            </div>
        )
    } 
}
export default TrainingList;