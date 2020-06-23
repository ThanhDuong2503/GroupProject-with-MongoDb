import React, {useEffect, useState} from 'react';
import './App.css';
import {fetchAllIdeas} from "./utils/ideas-utils";
import IdeaCard from "./components/IdeaCard/IdeaCard";
import Button from "@material-ui/core/Button";
import AddIdeaDialog from "./components/AddIdeaDialog/AddIdeaDialog";

function App() {

    const [ideas, setIdeas] = useState([]);
    const [showAddDialog, setShowAddDialog] = useState(false);


    useEffect(() => {
        fetchAllIdeas().then(data => setIdeas(data))
    }, []);


    return (
        <div className={"app"}>
            <Button variant="outlined" color="primary" onClick={() => setShowAddDialog(true)}>
                Add Idea
            </Button>

            <AddIdeaDialog open={showAddDialog}
                           handleClose={() => setShowAddDialog(false)}
                           onAdd={(idea) => setIdeas([...ideas, idea])}
            />
            {ideas.map((idea) => <IdeaCard key={idea.id} idea={idea}/>)}
        </div>
    );
}

export default App;
