function addToCart(productId) {
    console.log(productId)

    // fetch("api/addToCart",
    //     {
    //         method: "GET",
    //         headers: {
    //             'Content-Type': 'application/json'
    //         },
    //         body: JSON.stringify({id: productId})
    //     } )
    //     .then(status)
    //     .then(json)
    //     .then(res => {
    //
    //     })
    //     .catch()

    fetch(`api/addToCart/${productId}`)
        .then(status)
        .then(json)
        .then(res => {

        })
        .catch()
}

//Checks response status code.
function status(res) {
    if (res.status >= 200 && res.status <= 300) {
        return Promise.resolve(res);
    } else {
        return  Promise.reject(res.statusText);
    }
}

//Casting the promise to json.
function json(res){
    return res.json();
}
