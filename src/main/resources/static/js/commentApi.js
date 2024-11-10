window.onload = function(){
    drawComment();
}

// api 데이터 가져오기
async function getApi(url, method) {
    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (!response.ok) throw new Error(`Error: ${response.status}`);
        const data = await response.json();
        console.log("Fetched data:", data);
        return data;
    } catch (error) {
        console.error("Failed to fetch data:", error);
    }
}