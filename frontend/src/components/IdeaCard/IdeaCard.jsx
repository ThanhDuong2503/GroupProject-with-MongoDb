import React from "react";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import Typography from "@material-ui/core/Typography";
import {makeStyles} from "@material-ui/core/styles";
import {deleteIdea} from "../../utils/ideas-utils";
import IconButton from "@material-ui/core/IconButton";
import DeleteIcon from '@material-ui/icons/Delete';

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

function IdeaCard({idea,onDeleteSuccess}) {
    function handleDelete(){
        deleteIdea(idea.id).then(()=> onDeleteSuccess())
    }
    const classes = useStyles()
    return (
        <Card className={classes.root}>
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
