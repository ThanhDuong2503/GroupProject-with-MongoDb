import React, {useEffect, useState} from 'react';
import './App.css';
import {fetchAllIdeas} from "./utils/ideas-utils";
import IdeaCard from "./components/IdeaCard/IdeaCard";

function App() {

    const [ideas, setIdeas] = useState([]);

    useEffect(() => {
        fetchAllIdeas().then(data => setIdeas(data))
    }, []);


    return (
        <div>
            {ideas.map((idea) => <IdeaCard key={idea.id} idea={idea}/>)}
        </div>
    );
}

export default App;
