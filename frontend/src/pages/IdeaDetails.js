import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import IdeaCard from "../components/IdeaCard/IdeaCard";
import {fetchIdea} from "../utils/ideas-utils";

function IdeaDetails() {
    const {id} = useParams();

    const [idea, setIdea] = useState();

    useEffect(() => {
        fetchIdea(id).then(data => setIdea(data))
            .catch((e) => console.error(e))
    }, [id])

    return <>
        {idea && <IdeaCard idea={idea}/>}
    </>
}

export default IdeaDetails;
