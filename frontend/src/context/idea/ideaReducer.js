import {
    ADD_IDEA,
    ADD_IDEA_FAILED,
    ADD_IDEA_SUCCESS,
    DELETE_IDEA_SUCCESS,
    FETCH_IDEAS,
    FETCH_IDEAS_FAILED,
    FETCH_IDEAS_SUCCESS
} from "./idea-actions";

export default function ideaReducer(state, action) {
    console.log(action);
    switch (action.type) {
        case FETCH_IDEAS:
            return {...state, fetchStatus: 'PENDING'}
        case FETCH_IDEAS_SUCCESS:
            return {...state, fetchStatus: 'SUCCESS', ideas: action.payload}
        case FETCH_IDEAS_FAILED:
            return {...state, fetchStatus: 'FAILED'}
        case ADD_IDEA:
            return {...state, addStatus: 'PENDING'}
        case ADD_IDEA_SUCCESS:
            return {...state, addStatus: 'SUCCESS', ideas: [...state.ideas, action.payload]}
        case ADD_IDEA_FAILED:
            return {...state, addStatus: 'FAILED'}
        case DELETE_IDEA_SUCCESS:
            return {...state, ideas: state.ideas.filter((idea) => {
                return idea.id !== action.payload;
                })}
        default:
            return state;
    }
}
