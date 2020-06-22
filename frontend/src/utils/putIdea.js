
export function putIdea(body){
    fetch("/api/ideas/add",{
        method: "PUT",
        headers: {
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(body)
    }).then(response=> response.json());
}