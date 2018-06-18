import { SHOW_LOADER, CLEAR_ERROR, FETCH_SUCCESS,
     FETCH_ERROR, UPDATE_SUCCESS,
      UPDATE_ERROR, EDIT_SUCCESS, EDIT_ERROR, CREATE_SUCCESS, CREATE_ERROR,
      FIND_SUCCESS, FIND_ERROR } from '../actions/training';


      const initialState = {
        isFetching: false,
        topicNameList: [],
        editTrainingDetails: []
      }

      export default function training(state = initialState, actions) {
          
        switch(actions.type){
        case SHOW_LOADER:
        return{

            ...state,
            action:actions.type,
            isFetching: true,
        }
        case FETCH_SUCCESS:
        return{
            ...state,
            isFetching: false,
            topicNameList: actions.response
        }
        case FETCH_ERROR:
        return{
            ...state,
            isFetching: false,
            error: true,
            topicNameList: actions.response
        }
        case FIND_SUCCESS:
        return{
            ...state,
            isFetching:false,
            topicNameList:actions.response
        }
        case FIND_ERROR:
        return{
            ...state,
            isFetching:false,
            error:true,
            topicNameList: actions.response
        }
        case CLEAR_ERROR:
        return{
            ...state,
            error: false
        }
        case EDIT_SUCCESS:
        return{
            ...state,
            isFetching: false,
            editTrainingDetails: actions.response
        }
        case EDIT_ERROR:
        return{
            ...state,
            isFetching: false,
            error: true,
            editTrainingDetails: actions.response
        }
        case CREATE_SUCCESS:
        return{
            ...state,
            isFetching: false,
            createTrainingDeails: actions.response
        }
        case CREATE_ERROR:
        return{
            ...state,
            isFetching: false,
            error: true,
            createTrainingDeails: actions.response
        }
        default:
        return state
        }
      }