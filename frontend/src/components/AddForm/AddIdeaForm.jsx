import React, {useState} from "react";
import {putIdea} from "../../utils/ideas-utils";

export default function AddIdeaForm() {
    const [description, setDescription] = useState("");

    function handleSubmit(){
        putIdea(description);
    }

    function handleChange(event){
        setDescription(event.target.value);
    }

    return (
        <>
            <form onSubmit={handleSubmit}>
                <label htmlFor="ideaText">
                    <input onChange={handleChange} value={description} id="ideaText" type="text"/>
                    <button type={"submit"}>submit</button>
                </label>
            </form>
        </>
    );
}