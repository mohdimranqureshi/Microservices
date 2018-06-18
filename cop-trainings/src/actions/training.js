import  axios  from 'axios';

export const SHOW_LOADER = 'TRAINING_SHOW_LOADER';
export const CLEAR_ERROR = 'TRAINING_CLEAR_ERROR';
export const FETCH_SUCCESS = 'TRAINING_FETCH_SUCCESS';
export const FETCH_ERROR = 'TRAINING_FETCH_ERROR';
export const UPDATE_SUCCESS = 'TRAINING_UPDATE_SUCCESS';
export const UPDATE_ERROR = 'TRAINING_UPDATE_ERROR';
export const EDIT_SUCCESS = 'TRAINING_EDIT_SUCCESS';
export const EDIT_ERROR = 'TRAINING_EDIT_ERROR';
export const CREATE_SUCCESS = 'TRAINING_CREATE_SUCCESS';
export const CREATE_ERROR = 'TRAINING_CREATE_ERROR';
export const FIND_SUCCESS = 'TRAINING_FIND_SUCCESS';
export const FIND_ERROR = 'TRAINING_FIND_ERROR';

/* Action to get training list */
export const getTrainingList = (options) => dispatch =>{
    dispatch(showLoader())
    let request = null;
    if(options.response.data[0].userType === 0){

       request = {
            method: 'GET',
            url: 'http://xen-821.xavient.com:7070/copTraining/getTrainingDetails?'+'copId='+options.response.data[0].copId
        };
    }else{
        request = {
            method: 'GET',
            url: 'http://xen-821.xavient.com:7070/copTraining/getAllTrainingDetails'
        };
    }
    axios(request).then(response =>{
        dispatch(ApiSuccess(FETCH_SUCCESS,response.data))
    }).catch(function(error){
        dispatch(ApiError(FETCH_ERROR, error))
    })
}

/* Action to edit Training Details */
export const editTrainingDetails = (options) => dispatch =>{
    dispatch(showLoader())
    let request = {
        method:'GET',
        url:'http://xen-821.xavient.com:7070/copTraining/getDetailsByTopicName?'+'topicName='+options
    };
    axios(request).then(response =>{
        dispatch(ApiSuccess(EDIT_SUCCESS, response.data))
    }).catch(function(error){
        dispatch(ApiError(EDIT_ERROR, error))
    })
}

export const addTrainingDetails = (trainingList) => dispatch =>{
    dispatch(showLoader())
    let request = {
        method: 'POST',
        url:'http://xen-821.xavient.com:7070/copTraining/saveTrainingDetails',
        data: trainingList
    };
    axios(request).then(response =>{
        dispatch(ApiSuccess(CREATE_SUCCESS, response.data))
    }).catch(function(error){
        dispatch(ApiError(CREATE_ERROR, error))
    })
}

export const findTrainingDetails = (searchText, copId) => dispatch =>{
    dispatch(showLoader())
    let request = "";
    if(searchText == "" && copId != "6"){
       
        request = {
            method: 'GET',
            url: 'http://xen-821.xavient.com:7070/copTraining/getTrainingDetails?'+'copId='+copId
        };
    }else if(searchText == "" && copId == "6"){
        request = {
            method: 'GET',
            url: 'http://xen-821.xavient.com:7070/copTraining/getAllTrainingDetails'
        };
    }
    else{
     request = {
        method: 'GET',
        url: 'http://xen-821.xavient.com:7070/copTraining/getDetailsByTopicName?'+'topicName='+searchText
    };}
    axios(request).then(response =>{
        dispatch(ApiSuccess(FIND_SUCCESS, response.data))
    }).catch(function(error){
        dispatch(ApiError(FIND_ERROR, error))
    })
}


export const ApiSuccess = (type, response) =>{
    return{
        type: type,
        response
    }
}

export const ApiError = (type, error) =>{
    return{
        type: type,
        error
    }
}

/* Action to show loading while fetching response */
export const showLoader = () => ({
	type: SHOW_LOADER
});

/*Action to clear error*/
export const clearError = () => {
	return {
		type: CLEAR_ERROR
	}
}