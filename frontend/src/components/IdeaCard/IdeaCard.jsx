import React from "react";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import Typography from "@material-ui/core/Typography";
import {makeStyles} from "@material-ui/core/styles";

const useStyles = makeStyles({
    root: {
       margin: 10,
    }
});

function IdeaCard({idea}) {
    const classes = useStyles()
    return (
        <Card className={classes.root}>
            <CardContent>
                <Typography variant="body2" component="p">
                    {idea.description}
                </Typography>
            </CardContent>
        </Card>
    )
}

export default IdeaCard;
