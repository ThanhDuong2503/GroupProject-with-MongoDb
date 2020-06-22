import React, {useEffect, useState} from 'react';
import './App.css';
import {fetchAllIdeas} from "./utils/ideas-utils";

function App() {

    const [ideas, setIdeas] = useState([]);

    useEffect(() => {
        fetchAllIdeas().then(data => setIdeas(data))
    }, []);


    return (
        <div>
            {ideas.map((idea) =>
                <div key={idea.id}>
                    {idea.description}
                </div>)}
        </div>
    );
}

export default App;
