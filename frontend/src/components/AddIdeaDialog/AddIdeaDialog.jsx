import React, {useState} from "react";
import {putIdea} from "../../utils/ideas-utils";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import TextField from "@material-ui/core/TextField";
import DialogActions from "@material-ui/core/DialogActions";
import Button from "@material-ui/core/Button";
import CircularProgress from "@material-ui/core/CircularProgress";
import Typography from "@material-ui/core/Typography";

export default function AddIdeaDialog({open, handleClose, onAdd}) {
    const [description, setDescription] = useState("");
    const [addStatus, setAddStatus] = useState();

    function handleSubmit() {
        setAddStatus("PENDING")
        putIdea(description)
            .then((idea) => {
                handleClose();
                setDescription("");
                onAdd(idea);
                setAddStatus("SUCCESS")
            }).catch(() => {
            setAddStatus("FAILED")
        });

    }

    function handleChange(event) {
        setDescription(event.target.value);
    }

    return (
        <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title" maxWidth={"sm"} fullWidth={true}>
            <DialogTitle id="form-dialog-title">Add Idea</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    Add your idea ;)
                </DialogContentText>
                <form onSubmit={handleSubmit}>
                    <TextField
                        fullWidth={true}
                        multiline={true}
                        label="Description"
                        value={description}
                        onChange={handleChange}
                        margin="normal"
                        error={description.length < 5}
                        helperText={"min length 5"}
                    />
                </form>
                {addStatus === 'PENDING' && <CircularProgress />}
                {addStatus === 'FAILED' &&  <Typography variant="body1" component="p">
                  Add idea failed
                </Typography>}
            </DialogContent>
            <DialogActions>
                <Button onClick={handleClose} color="primary">
                    Cancel
                </Button>
                <Button disabled={description.length < 5} onClick={handleSubmit} color="primary">
                    Add
                </Button>
            </DialogActions>
        </Dialog>
    );
}
