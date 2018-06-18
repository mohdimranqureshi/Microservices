import React, { Component }  from 'react';

class TrainingBtn extends Component{

    constructor(props){
        super(props);
    }
    changePage = () => {
       
        this.props.changePage();
    }
    render(){
        

        return(
            <div>
         <div className = 'btn btn-primary' onClick = {this.changePage}>Add Training</div>

            </div>
        )
    }
}
export default TrainingBtn;