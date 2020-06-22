import React, {useState} from "react";
import {putIdea} from "../../utils/putIdea";

export default function AddIdeaForm() {
    const [idea, setIdea] = useState([]);

    function handleSubmit(){
        putIdea({description:idea});
    }

    function handleChange(event){
        setIdea(event.target.value);
    }

    return (
        <>
            <form onSubmit={handleSubmit}>
                <label htmlFor="ideaText">
                    <input onChange={handleChange} id="ideaText" type="text"/>
                    <button type={"submit"}>submit</button>
                </label>
            </form>
        </>
    );
}