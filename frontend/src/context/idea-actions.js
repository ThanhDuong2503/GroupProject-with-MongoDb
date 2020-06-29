import {fetchAllIdeas} from "../utils/ideas-utils";

export const FETCH_IDEAS = 'FETCH_IDEAS'
export const FETCH_IDEAS_SUCCESS = 'FETCH_IDEAS_SUCCESS'
export const FETCH_IDEAS_FAILED = 'FETCH_IDEAS_FAILED'

export async function fetchIdeas(dispatch) {
    dispatch({type: FETCH_IDEAS})
    try {
        const ideas = await fetchAllIdeas();
        dispatch({type: FETCH_IDEAS_SUCCESS, payload: ideas})
    } catch (error) {
        dispatch({type: FETCH_IDEAS_FAILED, payload: error})
    }
}
