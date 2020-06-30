import {IdeaStateContext, IdeaDispatchContext} from "./IdeaContext";
import  React, {useReducer} from "react";
import ideaReducer from "./ideaReducer";


export default function IdeaProvider({children}) {
    const [state, dispatch] = useReducer(ideaReducer, {
        ideas: [],
        fetchStatus: undefined
    })


    return <IdeaStateContext.Provider value={state}>
            <IdeaDispatchContext.Provider value={dispatch}>
                {children}
            </IdeaDispatchContext.Provider>
    </IdeaStateContext.Provider>
}
