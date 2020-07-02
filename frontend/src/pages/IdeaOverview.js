import React, {useContext, useEffect, useState} from 'react';
import Button from "@material-ui/core/Button";
import AddIdeaDialog from "../components/AddIdeaDialog/AddIdeaDialog";
import IdeaCard from "../components/IdeaCard/IdeaCard";
import {IdeaDispatchContext, IdeaStateContext} from "../context/idea/IdeaContext";
import CircularProgress from "@material-ui/core/CircularProgress";
import Typography from "@material-ui/core/Typography";
import {fetchIdeas} from "../context/idea/idea-actions";
import {UserStateContext} from "../context/user/UserContext";
import {Redirect} from "react-router-dom";

function IdeaOverview() {

    const [showAddDialog, setShowAddDialog] = useState(false);

    const {ideas, fetchStatus} = useContext(IdeaStateContext);
    const dispatch = useContext(IdeaDispatchContext);
    const {authStatus} = useContext(UserStateContext);

    useEffect(() => {
        if (!fetchStatus && authStatus === 'SUCCESS') {
            fetchIdeas(dispatch);
        }
    }, [fetchStatus, dispatch,authStatus])



    if(authStatus !== 'SUCCESS') {
        return  <Redirect to={"/login"}/>
    }

    return (
        <div className={"app"}>
            <Button variant="outlined" color="primary" onClick={() => setShowAddDialog(true)}>
                Add Idea
            </Button>

            <AddIdeaDialog open={showAddDialog}
                           handleClose={() => setShowAddDialog(false)}
            />
            {fetchStatus === 'PENDING' && <CircularProgress/>}
            {fetchStatus === 'FAILED' && <Typography variant="body1" color="error" component="p">
                Fetch Ideas failed
            </Typography>}
            {ideas.map((idea) => <IdeaCard key={idea.id} idea={idea} onDeleteSuccess={() => console.log('delete')}/>)}
        </div>
    );
}

export default IdeaOverview;
