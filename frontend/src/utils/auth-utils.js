

export async function performLogin(username, password) {
    const response = await fetch("/auth/login", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({username, password})
    });
    return await response.text()
}
