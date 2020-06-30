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

function Navigation () {
    return <BrowserRouter>
        <Switch>
            <Route path="/idea/:id">
                <IdeaDetails/>
            </Route>
            <Route path="/" exact>
                <IdeaOverview/>
            </Route>
        </Switch>
    </BrowserRouter>;
}

function App() {
    return <IdeaProvider>
        <Navigation/>
    </IdeaProvider>
}

export default App;
