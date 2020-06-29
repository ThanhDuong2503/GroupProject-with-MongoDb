export async function fetchAllIdeas() {
    const response = await fetch('/api/ideas');
    if(response.status !== 200){
        throw new Error(response.statusText);
    }
    return await response.json();
}


export function putIdea(description) {
    return fetch("/api/ideas", {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({description: description})
    }).then(response => {
        if (response.status !== 200) {
            throw new Error("invalid response");
        }

        return response.json()
    });
}

export function deleteIdea(id) {
    return fetch(`/api/ideas/${id}`, {
        method: "DELETE"
    });
}

export async function fetchIdea(id) {
    const response = await fetch(`/api/ideas/${id}`)
    if(response.status !== 200){
        throw new Error("something went wrong!")
    }
    return await response.json();
}
