export async function fetchAllIdeas() {
    const response = await fetch('/api/ideas');
    return await response.json();
}


export function putIdea(description){
    return fetch("/api/ideas",{
        method: "PUT",
        headers: {
            'Content-Type': 'application/json'
        },
        body:description
    }).then(response=> response.json());
}
