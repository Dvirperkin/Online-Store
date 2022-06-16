function addToCart(productId) {
    console.log(productId)

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
( function () {
    addEventListener("DOMContentLoaded",(event)=>{
        document.getElementById("search-input").addEventListener("input",(event)=>{
            let name = event.target.value.trim();
            let select = document.getElementById("searchDropDown");
            select.innerHTML = "";
            fetch(`/api/findProducts/${name}`)
                .then(status)
                .then(json)
                .then(res=>{
                    res.forEach((product)=>{
                        let option = document.createElement("div");
                        option.innerHTML = `<div >
                                                <a class="text-black text-decoration-none"  href="/product/${product.id}">
                                                    <img class="w-25" src="${product.image}"/>
                                                    ${product.name}
                                                </a>
                                             </div>`;
                        select.appendChild(option);
                    })
            }).catch()
        })

        // document.getElementById("search-button").addEventListener("submit",(event)=>{
        //     fetch(`/searchProucts/${names}`)
        //         .then(status)
        //         .then(json)
        //         .then(res=>{
        //
        //     }).catch()
        // })


    })

})()