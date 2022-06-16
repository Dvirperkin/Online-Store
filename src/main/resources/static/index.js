function addToCart(productId) {

    fetch(`api/addToCart/${productId}/${document.getElementById("quantityProductSpan" + productId).innerText}`)
        .then(status)
        .then(json)
        .then(res => {


        })
        .catch()
}

function incQuantities(quantitySpanId){
    let quantitySpan = document.getElementById(quantitySpanId)
    quantitySpan.innerText = (parseInt(quantitySpan.innerText) + 1).toString()
}
function decQuantities(quantitySpanId){
    let quantitySpan = document.getElementById(quantitySpanId)
    let newQuantity = (parseInt(quantitySpan.innerText) - 1)
    quantitySpan.innerText = (newQuantity >= 0) ? newQuantity.toString() : 0
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
        document.getElementById("search-input").addEventListener("input",searchProduct)
    })

    function searchProduct(event){
        let name = event.target.value.trim();
        let select = document.getElementById("searchDropDown");
        select.innerHTML = "";
        fetch(`/api/findProducts/${name}`)
            .then(status)
            .then(json)
            .then(res=>{
                res.forEach((product)=>{
                    let option = document.createElement("div");
                    option.innerHTML = `<div>
                                                <a class="text-black text-decoration-none"  href="/product/${product.id}">
                                                    <img class="w-25" src="${product.image}"/>
                                                    ${product.name}
                                                </a>
                                             </div>`;
                    select.appendChild(option);
                })
            }).catch()
    }
})()