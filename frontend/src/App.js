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
import LoginPage from "./pages/LoginPage";
import UserContextProvider from "./context/user/UserContextProvider";

function Navigation() {
    return <BrowserRouter>
        <Switch>
            <Route path="/idea/:id">
                <IdeaDetails/>
            </Route>
            <Route path="/" exact>
                <IdeaOverview/>
            </Route>
            <Route path="/login" exact>
                <LoginPage/>
            </Route>
        </Switch>
    </BrowserRouter>;
}

function App() {
    return <UserContextProvider>
        <IdeaProvider>
            <Navigation/>
        </IdeaProvider>
    </UserContextProvider>
}

export default App;
