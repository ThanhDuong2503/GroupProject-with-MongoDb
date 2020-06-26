import React from "react";
import Card from "@material-ui/core/Card";
import Button from "@material-ui/core/Button";
import CardContent from "@material-ui/core/CardContent";
import Typography from "@material-ui/core/Typography";
import {makeStyles} from "@material-ui/core/styles";
import {deleteIdea} from "../../utils/ideas-utils";

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
    const classes = useStyles()
    return (
        <Card className={classes.root}>
            <CardContent>
                <Typography variant="body1" component="p">
                    {idea.description}
                </Typography>
                <button onClick={() => deleteIdea(idea.id)}>delete</button>
            </CardContent>
        </Card>
    )
}

export default IdeaCard;
