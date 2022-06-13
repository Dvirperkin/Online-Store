( function () {
    fetch('/api/productcarts')
        .then(status)
        .then(json)
        .then(res=>{
            let item_list = document.getElementById("cartItems")
            for(let product in res.product){
                addItemsList(product, item_list)
            }
        })

    function addItemsList(product,item_list) {
        let id = product.id;
        let listItem = document.createElement('div')
        listItem.setAttribute("id", `${product.id}`)
        listItem.setAttribute("class", "col-3")

        //Need to add quantity if sold out
        listItem.innerHTML += `
                                <h2>${product.name}</h2> <br/>
                                <img class="w-25" src="${product.image}"></img> <br/>
                                Price: ${product.price} Price After Discount: ${product.price * (1 - ( product.discount / 100 ))}<br/>
                                <p id="quantity${id}">
                                    Quantity: ${product.quantity}      
                                </p>        
                              `
        let plusButton = document.createElement('button')
        plusButton.innerHTML = '+'
        plusButton.addEventListener("click", function addQ(event) {
            fetch(`/addToQunatity${id}`, {method: "PUT"})
                .then(status)
                .then(json)
                .then()
                .catch((err) => {
                    console.log(err)
                })
        })
        let minusButton = document.createElement('button')
        minusButton.innerHTML = '-'
        minusButton.addEventListener("click", function minusQ(event){
            fetch(`/minusToQunatity${id}`, {method: "PUT"})
                .then(getStatusCode)
                .then(json)
                .then()
                .catch((err)=>{
                    console.log(err)
                })
        })

        let deleteButton = document.createElement("button");
        deleteButton.setAttribute("type", "button");
        deleteButton.setAttribute("class", "bg-danger rounded");
        deleteButton.innerText = `Delete`;
        deleteButton.addEventListener("click", function listener(event){
            let toRemove = document.getElementById(`${product.id}`)
            deleteButton.removeEventListener("click", listener);
            toRemove.parentElement.removeChild(toRemove);

            fetch(`/deleteFromCart${id}`,{method: "DELETE"})
                .then(getStatusCode)
                .then(json)
                .catch((err)=>{
                    console.log(err)
                })
            deleteButton.click();
        })


        listItem.append(plusButton);
        listItem.append(minusButton);
        listItem.append(deleteButton);
        item_list.append(listItem);

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