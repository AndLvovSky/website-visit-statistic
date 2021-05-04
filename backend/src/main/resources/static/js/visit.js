function visit(apiKey, url, websiteVersion) {
    $.ajax({
        url: `${url}/visit/${apiKey}`,
        type: "POST",
        data: JSON.stringify({
            time: new Date().toLocaleString(),
            userAgent: navigator.userAgent,
            referralWebsite: new URL(document.location).searchParams.get("referral"),
            websiteVersion,
            path: document.location.pathname
        }),
        crossDomain: true,
        contentType: "application/json"
    })
}
