import {fetchAllIdeas} from "../utils/ideas-utils";

export const FETCH_IDEAS = 'FETCH_IDEAS'
export const FETCH_IDEAS_SUCCESS = 'FETCH_IDEAS_SUCCESS'
export const FETCH_IDEAS_FAILED = 'FETCH_IDEAS_FAILED'
export const ADD_IDEA = 'ADD_IDEA'
export const ADD_IDEA_SUCCESS = 'ADD_IDEA_SUCCESS'
export const ADD_IDEA_FAILED = 'ADD_IDEA_FAILED'

export async function fetchIdeas(dispatch) {
    dispatch({type: FETCH_IDEAS})
    try {
        const ideas = await fetchAllIdeas();
        dispatch({type: FETCH_IDEAS_SUCCESS, payload: ideas})
    } catch (error) {
        dispatch({type: FETCH_IDEAS_FAILED, payload: error})
    }
}

export async function addIdea(dispatch) {
    dispatch({type: ADD_IDEA})
    try {
        const idea = await addIdea();
        dispatch({type: ADD_IDEA_SUCCESS, payload: idea})
    } catch (error) {
        dispatch({type: ADD_IDEA_FAILED, payload: error})
    }
}