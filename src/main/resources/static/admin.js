( function () {

    document.addEventListener("DOMContentLoaded", () => {
        document.getElementById("AddBookForm").addEventListener("submit", addBook)
        document.getElementById("UpdateBookForm").addEventListener("submit", updateBook)
        document.getElementById("DeleteBookForm").addEventListener("submit", deleteBook)
        document.getElementById("ToDeleteID").addEventListener("change", searchProductToDelete)
    })

    function addBook(event) {
        event.preventDefault();

        fetch("admin/api/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                id: document.getElementById("BookID").value,
                name: document.getElementById("BookName").value,
                image: "",
                quantity: 0,
                price: document.getElementById("Price").value,
                discount: document.getElementById("Discount").value
            })
        }).then(status)
            .then(json)
            .then(res => {

            })
    }
    function updateBook(event) {
        event.preventDefault();
        fetch(`admin/api/update/${document.getElementById("ToUpdateID").value}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                id: document.getElementById("ToUpdateID").value,
                name: document.getElementById("ToUpdateName").value,
                image: "",
                quantity: 0,
                price: document.getElementById("ToUpdatePrice").value,
                discount: document.getElementById("ToUpdateDiscount").value
            })
        }).then(status)
            .then(json)
            .then(res => {

            })
    }
    function deleteBook(event) {
        event.preventDefault();

        fetch(`admin/api/delete/${document.getElementById("ToDeleteID").value}`, {
            method: "DELETE",
        }).then(status)
            .then(json)
            .then(res => {

            })
    }

    function searchProductToDelete(event) {
        let productsBoard = document.getElementById("ProductsBoard")
        productsBoard.innerHTML = ""
        fetch("/api/books")
            .then(status)
            .then(json)
            .then(res => {
                console.log(res)
            })
    }


    function addItemsList(product, container) {
        let id = product.id;
        let listItem = document.createElement('div')
        listItem.setAttribute("id", `${product.id}`)
        listItem.setAttribute("class", "col-3")

        //Need to add quantity if sold out
        listItem.innerHTML += `
                                <h2>${product.name}</h2> <br/>
                                <img class="w-25" src="${product.image}"/> <br/>
                                Category: ${product.category} <br/>
                                Description: ${product.description} <br/>
                                Price: ${product.price} Price After Discount: ${product.price * (1 - ( product.discount / 100 ))}<br/>
                                <p id="quantity${id}">
                                    Quantity: ${product.counter}      
                                </p>        
                              `
        container.append(listItem)
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
})()