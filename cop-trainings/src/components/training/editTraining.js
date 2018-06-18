import React, { Component } from 'react';

class EditTraining extends Component{

    goBack = () =>{
        this.props.goBack();
    }
    render(){
        let response = this.props;
        let abc = null;
        let xyz = null;
        const topicName = Object.values(response.editTrainingDetails);

        {  topicName.map((topicNameList) => {
         abc =  topicNameList.topicNameList.map((topicNameList) =>{
            
            return(
                <div>
                <div className="row">
                <div className="col-md-4">
                   <div className="form-group">
                      <label htmlFor="topic_name">Topic Name *</label>
                      <input id="topic_name" type="text" name="topic_name" ref= "topic_name" className="form-control" placeholder="Topic Name" required="required" data-error="Topic Name is required." value = {topicNameList.topicName}/>                
                      <div className="help-block with-errors"></div>
                   </div>
                    </div>
                    
                <div className="col-md-4">
                   <div className="form-group">
                      <label htmlFor="training_level">Training Level *</label>
                      <input id="training_level" type="text" name="training_level" ref= "training_level" className="form-control" placeholder="Topic Name" required="required" data-error="Training Level is required." value = { topicNameList.trainingLevel }/>                
                      <div className="help-block with-errors"></div>
                   </div>
                    </div> 
                   
                <div className="col-md-4">
                   <div className="form-group">
                      <label htmlFor="material_url">Material URL *</label>
                      <input id="material_url" type="text" name="material_url" ref= "material_url" className="form-control" placeholder="Material URL" required="required" data-error="Material URL is required." value = { topicNameList.url }/>                
                      <div className="help-block with-errors"></div>
                   </div>
                    </div> 
                      </div>

                  { xyz = topicNameList.trainerDetails.map((abc) =>{ 
                      return(
                                <div className="row">
                                <div className="col-md-6">
                                <div className="form-group">
                                    <label htmlFor="trainer_name">Trainer Name *</label>
                                    <input id="trainer_name" type="text" name="trainer_name" ref= "trainer_name" className="form-control" placeholder="Trainier Name" required="required" data-error="Trainer Name is required." value = { abc.trainerName }/>                
                                    <div className="help-block with-errors"></div>
                                </div>
                                    </div> 

    
                                <div className="col-md-6">
                                <div className="form-group">
                                    <label htmlFor="trainer_prefrence">Trainer Prefrence *</label>
                                    <input id="trainer_prefrence" type="text" name="trainer_prefrence" ref= "trainer_prefrence" className="form-control" placeholder="Trainier Prefrence" required="required" data-error="Trainer Prefrence is required." value = {abc.trainerPrefrence }/>                
                                    <div className="help-block with-errors"></div>
                                </div>
                                    </div> </div>
                            )
                                 })
                  }
</div>
            )
        })
        
    });  
}
       
        return(
                  <div className = "container">
                     
                            <div className = 'btn btn-primary' onClick = { this.goBack }>Back</div>
                            {abc}
                            
                            <div className = 'btn btn-primary' onClick = { this.goBack }>Cancel</div>
                            <div className = 'btn btn-primary'>Save Changes</div>
                        </div>
        )
    }
}
export default EditTraining;