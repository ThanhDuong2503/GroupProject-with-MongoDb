import {FETCH_IDEAS, FETCH_IDEAS_FAILED, FETCH_IDEAS_SUCCESS} from "./idea-actions";

export default function ideaReducer(state, action) {
    console.log(action);
    switch (action.type) {
        case FETCH_IDEAS:
            return {...state, fetchStatus: 'PENDING'}
        case FETCH_IDEAS_SUCCESS:
            return {...state, fetchStatus: 'SUCCESS', ideas: action.payload}
        case FETCH_IDEAS_FAILED:
            return {...state, fetchStatus: 'FAILED'}
        default:
            return state;
    }
}
