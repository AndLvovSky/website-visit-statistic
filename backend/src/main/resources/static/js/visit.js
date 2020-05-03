function visit(apiKey) {
    $.ajax({
        url: `http://localhost:8787/visit/${apiKey}`,
        type: "POST",
        data: JSON.stringify({
            time: new Date().toLocaleString()
        }),
        crossDomain: true,
        contentType: "application/json"
    })
}
