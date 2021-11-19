exports.index = (req, res) => {
    res.send(`<h2>Goodbye ${req.params.name}</h2>`);
}

exports.test = (req, res) => {
    res.send(`<h2>TEST</h2>`);
}