import React from 'react';
import './App.css';
import {
    BrowserRouter,
    Switch,
    Route
} from "react-router-dom";
import IdeaOverview from "./pages/IdeaOverview";

function App() {
    return <BrowserRouter>
        <Switch>
            <Route path="/">
                <IdeaOverview/>
            </Route>
        </Switch>
    </BrowserRouter>
}

export default App;
