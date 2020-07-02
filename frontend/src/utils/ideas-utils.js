export async function fetchAllIdeas() {
    const token = localStorage.getItem("planning-user-token");
    const response = await fetch('/api/ideas',
        {
            method: "GET",
            headers: {
                'Authorization': `Bearer ${token}`
            },
        });
    if(response.status !== 200){
        throw new Error(response.statusText);
    }
    return await response.json();
}


export function putIdea(description) {
    const token = localStorage.getItem("planning-user-token");
    return fetch("/api/ideas", {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
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
    const token = localStorage.getItem("planning-user-token");
    return fetch(`/api/ideas/${id}`, {
        method: "DELETE",
        headers: {
            'Authorization': `Bearer ${token}`
        }
    });
}

export async function fetchIdea(id) {
    const token = localStorage.getItem("planning-user-token");
    const response = await fetch(`/api/ideas/${id}`,
        {
            method: "GET",
            headers: {
                'Authorization': `Bearer ${token}`
            },
        })
    if(response.status !== 200){
        throw new Error("something went wrong!")
    }
    return await response.json();
}
