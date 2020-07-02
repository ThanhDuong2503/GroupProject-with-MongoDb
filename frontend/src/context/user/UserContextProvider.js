import {UserDispatchContext, UserStateContext} from "./UserContext";
import React, {useReducer} from "react";

export const LOGIN = "LOGIN"
export const LOGIN_SUCCESS = "LOGIN_SUCCESS"
export const LOGIN_FAILED = "LOGIN_FAILED"



function reducer(state, action) {
    switch (action.type) {
        case LOGIN:
            return {...state, authStatus: 'PENDING'}
        case LOGIN_SUCCESS:
            return {...state, authStatus: 'SUCCESS'}
        case LOGIN_FAILED:
            return {...state, authStatus: 'FAILED'}
    }

    return state;
}

function UserContextProvider({children}) {
    const [state, dispatch] = useReducer(reducer,{
        authStatus: undefined
    })

    return <UserStateContext.Provider value={state}>
        <UserDispatchContext.Provider value={dispatch}>
            {children}
        </UserDispatchContext.Provider>
    </UserStateContext.Provider>
}

export default UserContextProvider
