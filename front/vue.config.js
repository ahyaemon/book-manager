module.exports = {
    devServer: {
        port: 8081,
        proxy: {
            "/book": {
                target: "http://localhost:3000",
            },
        },
    },
}
