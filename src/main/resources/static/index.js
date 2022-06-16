/**
 * Adds item to cart
 * @param productId - The id of the product to add.
 * @param spanId - the id of the span element that shows the quantity of each product.
 */
function addToCart(productId, spanId) {
    let toastBody = document.getElementById("toastBody");
    let pop = new bootstrap.Toast( document.getElementById("liveToast"));
    let popImage = document.getElementById("pop-image");
    fetch(`api/addToCart/${productId}/${document.getElementById(spanId + productId).innerText}`)
        .then(status)
        .then(json)
        .then(res => {
            document.getElementById("cartQuantity").innerText = res.toString();
            popImage.setAttribute("src","Success.png")
            toastBody.innerText = "The product added a successfully to cart"
            pop.show();
        })
        .catch(()=>{
            popImage.setAttribute("src","notSuccess.png")
            toastBody.innerText = "The product didn't added to cart"
            pop.show();
        })
}

/**
 * Increases the quantity of product.
 * @param quantitySpanId
 */
function incQuantities(quantitySpanId){
    let quantitySpan = document.getElementById(quantitySpanId)
    quantitySpan.innerText = (parseInt(quantitySpan.innerText) + 1).toString()
}
/**
 * Decreases the quantity of product.
 * @param quantitySpanId
 */
function decQuantities(quantitySpanId){
    let quantitySpan = document.getElementById(quantitySpanId)
    let newQuantity = (parseInt(quantitySpan.innerText) - 1)
    quantitySpan.innerText = (newQuantity >= 0) ? newQuantity.toString() : 0
}

/**
 * Checks response status code.
 * @param res
 * @returns {Promise<never>|Promise<unknown>}
 */
function status(res) {
    if (res.status >= 200 && res.status <= 300) {
        return Promise.resolve(res);
    } else {
        return  Promise.reject(res.statusText);
    }
}

/**
 * /Casting the promise to json.
 * @param res
 * @returns {*}
 */
function json(res){
    return res.json();
}
( function () {

    addEventListener("DOMContentLoaded",(event)=>{
        document.getElementById("search-input").addEventListener("input",searchProduct)
    })

    /**
     * The results box of search gets the 5 first products.
     * @param event
     */
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
            }).catch(()=>{
            let option = document.createElement("div");
            option.innerHTML = `<div><p>Something went wrong</p></div>`;
            select.appendChild(option);
        })
    }
})()