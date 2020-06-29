import React, {useEffect, useState} from 'react';
import Button from "@material-ui/core/Button";
import {fetchAllIdeas} from "../utils/ideas-utils";
import AddIdeaDialog from "../components/AddIdeaDialog/AddIdeaDialog";
import IdeaCard from "../components/IdeaCard/IdeaCard";

function IdeaOverview() {

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
            {ideas.map((idea) => <IdeaCard key={idea.id} idea={idea} onDeleteSuccess={() => {
                const filteredList = ideas.filter(filterIdea => filterIdea.id !== idea.id);
                setIdeas(filteredList)
            }}/>)}
        </div>
    );
}

export default IdeaOverview;
