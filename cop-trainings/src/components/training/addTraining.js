import React, { Component } from 'react';

class AddTraining extends Component{

    constructor(props){
        super(props);
        this.state = {
            shouldHide: false
        };
        this.addMoreTrainer = this.addMoreTrainer.bind(this);
    }
    addMoreTrainer(e){
        e.preventDefault();
        this.setState({
            shouldHide: !this.state.shouldHide
        })
    }

    saveTrainingDetails =(e) => {
        e.preventDefault();
        let topicNameList = {};
        let trainerDetails =[];
        let trainerName1 = '';
        let trainerPrefrence1 = '';
        let topicName = this.refs.topic_name.value;
        let trainingLevel = this.refs.training_level.value;
        let trainerName = this.refs.trainer_name.value;
        let trainerPrefrence = this.refs.trainer_prefrence.value;
        let url = this.refs.material_url.value;
        let copId = this.props.loginDetails.response.data[0].copId;

        if(this.refs.trainer_name1 != undefined && this.refs.trainer_prefrence != undefined){
         trainerName1 = this.refs.trainer_name1.value;
         trainerPrefrence1 = this.refs.trainer_prefrence1.value;
        }
        if(!trainerName1 == '' && !trainerPrefrence1 == ''){
        trainerDetails = [{
             "trainerName": trainerName,
            "trainerPrefrence": trainerPrefrence
            },
            { "trainerName": trainerName1,
            "trainerPrefrence": trainerPrefrence1
            }
        ]
        topicNameList.topicName = topicName,
        topicNameList.trainingLevel= trainingLevel,
        topicNameList.trainerDetails= trainerDetails,
        topicNameList.url = url,
        topicNameList.copId = copId
    }else{
        trainerDetails = [
            { "trainerName": trainerName,
            "trainerPrefrence": trainerPrefrence
            }
         ]
             topicNameList.topicName = topicName,
             topicNameList.trainingLevel= trainingLevel,
             topicNameList.trainerDetails= trainerDetails,
             topicNameList.url = url,
             topicNameList.copId = copId
}
    this.props.addTrainingDetails(topicNameList);
    this.props.getTrainingDetails();
}

    goBack = () =>{
        this.props.goBack();
    }
    render(){
        let shouldHide = this.state.shouldHide;
        const addBtn = this.props;
        return(
            <div>
               
   <form>
      <div className="container">
          <div className = 'btn btn-primary' onClick = { this.goBack }>Back</div>
         <div className = "trainerLink"><a onClick = {this.addMoreTrainer}>Add Trainer</a></div>
         <div className="row">
            <div className="col-md-4">
               <div className="form-group">
                  <label htmlFor="topic_name">Topic Name *</label>
                  <input id="topic_name" type="text" name="topic_name" ref= "topic_name" className="form-control" placeholder="Topic Name" required="required" data-error="Topic Name is required." />                
                  <div className="help-block with-errors"></div>
               </div></div>

               <div className="col-md-4">
                   <div className="form-group">
                      <label htmlFor="training_level">Training Level *</label>
                      <input id="training_level" type="text" name="training_level" ref= "training_level" className="form-control" placeholder="Topic Name" required="required" data-error="Training Level is required." />                
                      <div className="help-block with-errors"></div>
                   </div>
                    </div> 

                    <div className="col-md-4">
               <div className="form-group">
                  <label htmlFor="material_url">Material URL *</label>
                  <input id="material_url" type="text" name="material_url" ref="material_url" className="form-control" placeholder="Material URL" />
                  <div className="help-block with-errors"></div>
               </div>
            </div>

            </div>
            <div className="row">
            <div className="col-md-6">
               <div className="form-group">
                  <label htmlFor="trainer_name">Trainer Name *</label>
                  <input id="trainer_name" type="text" name="trainer_name" ref= "trainer_name" className="form-control" placeholder="Trainer Name" required="required" data-error="Trainer is required." />
                  <div className="help-block with-errors"></div>
               </div>
            </div>
        
        
            <div className="col-md-6">
               <div className="form-group">
                  <label htmlFor="trainer_prefrence">Trainer Prefrence *</label>
                  <input id="trainer_prefrence" type="text" name="trainer_prefrence" ref= "trainer_prefrence" className="form-control" placeholder="Trainer Prefrence" required="required" data-error="Trainer Prefrence is required." />
                  <div className="help-block with-errors"></div>
               </div>
            </div> </div>
           
         
         {this.state.shouldHide ? 
         <div>
            <div className="row">
               <div className="col-md-6">
                  <div className="form-group">
                     <label htmlFor="trainer_name1">Trainer Name *</label>
                     <input id="trainer_name1" type="text" name="trainer_name1" ref="trainer_name1" className="form-control" placeholder="Trainer Name" required="required" data-error="Trainer is required." />
                     <div className="help-block with-errors"></div>
                  </div>
               </div>
               <div className="col-md-6">
                  <div className="form-group">
                     <label htmlFor="trainer_prefrence1">Trainer Prefrence *</label>
                     <input id="trainer_prefrence1" type="text" name="trainer_prefrence1" ref= "trainer_prefrence1" className="form-control" placeholder="Trainer Prefrence" required="required" data-error="Trainer Prefrence is required." />
                     <div className="help-block with-errors"></div>
                  </div>
               </div>
            </div>
         </div>
         : ''}
         <div className="row">
            <div className="col-md-12">
               <input type="submit" className="btn btn-success btn-send" value="Submit" onClick = { this.saveTrainingDetails}/>
            </div>
         </div>
      </div>
   </form>
</div>
 );}
}
export default AddTraining;