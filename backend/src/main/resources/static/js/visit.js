function visit(apiKey) {
    $.ajax({
        url: `http://localhost:8787/visit/${apiKey}`,
        type: "POST",
        data: JSON.stringify({
            time: new Date().toLocaleString(),
            userAgent: navigator.userAgent,
            referralWebsite: new URL(document.location).searchParams.get("referral")
        }),
        crossDomain: true,
        contentType: "application/json"
    })
}
