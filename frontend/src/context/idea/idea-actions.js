import {deleteIdea, fetchAllIdeas, putIdea} from "../../utils/ideas-utils";

export const FETCH_IDEAS = 'FETCH_IDEAS'
export const FETCH_IDEAS_SUCCESS = 'FETCH_IDEAS_SUCCESS'
export const FETCH_IDEAS_FAILED = 'FETCH_IDEAS_FAILED'
export const ADD_IDEA = 'ADD_IDEA'
export const ADD_IDEA_SUCCESS = 'ADD_IDEA_SUCCESS'
export const ADD_IDEA_FAILED = 'ADD_IDEA_FAILED'
export const DELETE_IDEA = 'DELETE_IDEA'
export const DELETE_IDEA_SUCCESS = 'DELETE_IDEA_SUCCESS'
export const DELETE_IDEA_FAILED = 'DELETE_IDEA_FAILED'

export async function fetchIdeas(dispatch) {
    dispatch({type: FETCH_IDEAS})
    try {
        const ideas = await fetchAllIdeas();
        dispatch({type: FETCH_IDEAS_SUCCESS, payload: ideas})
    } catch (error) {
        dispatch({type: FETCH_IDEAS_FAILED, payload: error})
    }
}

export async function addIdea(dispatch, description) {
    dispatch({type: ADD_IDEA})
    try {
        const idea = await putIdea(description);
        dispatch({type: ADD_IDEA_SUCCESS, payload: idea})
    } catch (error) {
        dispatch({type: ADD_IDEA_FAILED, payload: error})
    }
}

export async function removeIdea(dispatch, id) {
    dispatch({type: DELETE_IDEA})
    try {
        await deleteIdea(id);
        dispatch({type: DELETE_IDEA_SUCCESS, payload: id})
    } catch (error) {
        dispatch({type: DELETE_IDEA_FAILED})
    }
}
