import React from 'react';
import './App.css';
import {
    BrowserRouter,
    Switch,
    Route
} from "react-router-dom";
import IdeaOverview from "./pages/IdeaOverview";
import IdeaDetails from "./pages/IdeaDetails";

function App() {
    return <BrowserRouter>
        <Switch>
            <Route path="/idea/:id">
                <IdeaDetails/>
            </Route>
            <Route path="/" exact>
                <IdeaOverview/>
            </Route>
        </Switch>
    </BrowserRouter>
}

export default App;
