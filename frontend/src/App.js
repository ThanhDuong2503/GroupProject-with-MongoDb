import React from 'react';
import './App.css';
import {
    BrowserRouter,
    Switch,
    Route
} from "react-router-dom";
import IdeaOverview from "./pages/IdeaOverview";
import IdeaDetails from "./pages/IdeaDetails";
import IdeaProvider from "./context/idea/IdeaContextProvider";

function App() {
    return <IdeaProvider>
        <BrowserRouter>
            <Switch>
                <Route path="/idea/:id">
                    <IdeaDetails/>
                </Route>
                <Route path="/" exact>
                    <IdeaOverview/>
                </Route>
            </Switch>
        </BrowserRouter>
    </IdeaProvider>
}

export default App;
