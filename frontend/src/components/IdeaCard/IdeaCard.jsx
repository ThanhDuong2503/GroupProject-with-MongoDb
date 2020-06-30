import React, {useContext} from "react";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import Typography from "@material-ui/core/Typography";
import {makeStyles} from "@material-ui/core/styles";
import IconButton from "@material-ui/core/IconButton";
import DeleteIcon from '@material-ui/icons/Delete';
import { useHistory } from "react-router-dom";
import {IdeaDispatchContext} from "../../context/idea/IdeaContext";
import {removeIdea} from "../../context/idea/idea-actions";

const useStyles = makeStyles({
    root: {
        margin: 10,
        maxWidth: 350,
        backgroundColor: "lightgray",
        "&:hover": { 
            backgroundColor: 'rgb(7, 177, 77, 0.42)' 
        } 
    },
});

function IdeaCard({idea}) {
    const dispatch = useContext(IdeaDispatchContext);
    function handleDelete(event){
        event.stopPropagation();
        removeIdea(dispatch, idea.id);
    }
    const classes = useStyles()
    const history = useHistory();
    return (
        <Card className={classes.root} onClick={() => history.push(`/idea/${idea.id}`)}>
            <CardContent>
                <Typography variant="body1" component="p">
                    {idea.description}
                </Typography>
                <IconButton onClick={handleDelete}>
                    <DeleteIcon />
                </IconButton>
            </CardContent>
        </Card>
    )
}

export default IdeaCard;
