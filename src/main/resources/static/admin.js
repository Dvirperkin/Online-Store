( function () {

    document.addEventListener("DOMContentLoaded", () => {
        document.getElementById("v-pills-payments-tab").addEventListener("click", showPayments)
        //document.getElementById("v-pills-productManagement-tab").addEventListener("click", showProducts)
        //document.getElementById("AddProductForm").addEventListener("submit", addProduct)
        // document.getElementById("UpdateProductForm").addEventListener("submit", updateProduct)
        // document.getElementById("DeleteProductForm").addEventListener("submit", deleteProduct)
        // document.getElementById("ToDeleteID").addEventListener("input", searchProductToDelete)
    })

    function showPayments(event) {
        fetch("/admin/api/Payments")
            .then(status)
            .then(json)
            .then(res => {
                let paymentsSum = 0
                let payments = document.getElementById("Payments")
                payments.innerHTML = ""

                res.map(payment => {
                    payments.innerHTML += `
                    <tr>
                        <td>${payment.id}</td>
                        <td>Anonymous</td>
                        <td>${payment.datetime}</td>
                        <td>${payment.amount}</td>
                    </tr>
                    `
                    paymentsSum += payment.amount
                })

                document.getElementById("PaymentsSum").innerText = paymentsSum
            })
            .catch(e => {
                document.getElementById("Payments").innerHTML = ""
                document.getElementById("PaymentsSum").innerText = '0'
            })
    }
    // function showProducts(event) {
    //     fetch("/api/products")
    //         .then(status)
    //         .then(json)
    //         .then(res => {
    //             let products = document.getElementById("Products")
    //             products.innerHTML = ""
    //
    //             res.map(product => {
    //                 products.innerHTML += `
    //                 <tr>
    //                     <td>${product.id}</td>
    //                     <td>${product.name}</td>
    //                     <td>${product.price}</td>
    //                     <td>${product.discount}</td>
    //                 </tr>
    //                 `
    //             })
    //         })
    //         .catch(e => {
    //             document.getElementById("Products").innerHTML = ""
    //         })
    // }
    // function addProduct(event) {
    //     event.preventDefault();
    //
    //     fetch("admin/api/add", {
    //         method: "POST",
    //         headers: {
    //             "Content-Type": "application/json"
    //         },
    //         body: JSON.stringify({
    //             name: document.getElementById("ProductName").value,
    //             image: "",
    //             quantity: 0,
    //             price: document.getElementById("Price").value,
    //             discount: document.getElementById("Discount").value
    //         })
    //     }).then(status)
    //         .then(json)
    //         .then(res => {
    //             showProducts()
    //             document.getElementById("AddProductForm").reset()
    //         })
    // }
    // function updateProduct(event) {
    //     event.preventDefault();
    //     fetch(`admin/api/update/${document.getElementById("ToUpdateID").value}`, {
    //         method: "PUT",
    //         headers: {
    //             "Content-Type": "application/json"
    //         },
    //         body: JSON.stringify({
    //             id: document.getElementById("ToUpdateID").value,
    //             name: document.getElementById("ToUpdateName").value,
    //             image: "",
    //             quantity: 0,
    //             price: document.getElementById("ToUpdatePrice").value,
    //             discount: document.getElementById("ToUpdateDiscount").value
    //         })
    //     }).then(status)
    //         .then(json)
    //         .then(res => {
    //             showProducts()
    //             document.getElementById("UpdateProductForm").reset()
    //         })
    // }
    // function deleteProduct(event) {
    //     event.preventDefault();
    //
    //     fetch(`admin/api/delete/${document.getElementById("ToDeleteID").value}`, {
    //         method: "DELETE",
    //     }).then(status)
    //         .then(json)
    //         .then(res => {
    //             showProducts()
    //             document.getElementById("DeleteProductForm").reset()
    //         })
    // }
    // function searchProductToDelete(event) {
    //     let productsBoard = document.getElementById("ProductsBoard")
    //     productsBoard.innerHTML = ""
    //     fetch(`/api/product/${event.target.value}`)
    //         .then(status)
    //         .then(json)
    //         .then(res => {
    //             addProductTo(res, productsBoard)
    //         })
    //         .catch(e => {
    //             productsBoard.innerHTML = ""
    //         })
    // }
    // function addProductTo(product, container) {
    //     let id = product.id;
    //     let listItem = document.createElement('div')
    //     listItem.setAttribute("id", `${product.id}`)
    //     listItem.setAttribute("class", "col-3")
    //
    //     //Need to add quantity if sold out
    //     listItem.innerHTML += `
    //                             <h2>${product.name}</h2> <br/>
    //                             <img class="w-25" src="${product.image}"/> <br/>
    //                             Category: ${product.category} <br/>
    //                             Description: ${product.description} <br/>
    //                             Price: ${product.price} Price After Discount: ${product.price * (1 - ( product.discount / 100 ))}<br/>
    //                             <p id="quantity${id}">
    //                                 Quantity: ${product.counter}
    //                             </p>
    //                           `
    //     container.append(listItem)
    // }

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
})()