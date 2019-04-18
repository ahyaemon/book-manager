module.exports = {
    devServer: {
        port: 8081,
        proxy: {
            "/book": {
                target: "http://localhost:8080",
            },
        },
    },
    outputDir: "../src/main/resources/static",
    indexPath: "../templates/index.html",
}
