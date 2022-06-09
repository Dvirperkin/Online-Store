( function () {


    document.addEventListener("DOMContentLoaded", () => {
        document.getElementById("AddBookForm").addEventListener("submit", addBook)
    })





    function addBook(event) {
        event.preventDefault();

        const c = {
            id: document.getElementById("BookID").value,
            name: document.getElementById("BookName").value,
            image: "",
            quantity: 0,
            price: document.getElementById("Price").value,
            discount: document.getElementById("Discount").value
        };

        console.log(c)

        fetch("/api/addBook", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: {
                id: document.getElementById("BookID").value,
                name: document.getElementById("BookName").value,
                image: "",
                quantity: 0,
                price: document.getElementById("Price").value,
                discount: document.getElementById("Discount").value
            }
        }).then(status)
            .then(json)
            .then(res => {

            })
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