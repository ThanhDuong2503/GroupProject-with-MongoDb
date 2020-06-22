export async function fetchAllIdeas() {
    const response = await fetch('/api/ideas');
    return await response.json();
}
