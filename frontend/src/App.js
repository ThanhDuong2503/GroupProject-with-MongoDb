import React, {useEffect, useState} from 'react';
import './App.css';

function App() {

    const [ideas, setIdeas] = useState([]);

    useEffect(() => {
        fetch('/api/ideas')
            .then(response => response.json())
            .then(data => setIdeas(data))
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
