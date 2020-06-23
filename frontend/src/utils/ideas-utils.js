export async function fetchAllIdeas() {
    const response = await fetch('/api/ideas');
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
