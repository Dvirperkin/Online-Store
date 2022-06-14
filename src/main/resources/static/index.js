( function () {


    fetch('/api/top5products')
        .then(status)
        .then(json)
        .then(res=>{
            document.getElementById("TopSalesList").innerHTML = ""
            let item_list = document.getElementById("TopSalesList")
            for(let product of res){
                addItemsList(product, item_list)
            }
    })

    fetch('/api/products')
        .then(status)
        .then(json)
        .then(res=>{
            document.getElementById("Products").innerHTML = ""
            let item_list = document.getElementById("Products")
            for(let product of res){
                addItemsList(product, item_list)
            }
        })




    function addItemsList(product,item_list) {
        let id = product.id;
        let listItem = document.createElement('div')
        listItem.setAttribute("id", `${product.id}`)
        listItem.setAttribute("class", "border mt-2 col-md-2")

        //Need to add quantity if sold out
        listItem.innerHTML += `
                                <h3>${product.name}</h3> <br/>
                                <img class="w-25" src="${product.image}"></img> <br/>
                                Price: ${product.price} Price After Discount: ${(product.price * (1 - ( product.discount / 100 ))).toPrecision(2)}<br/>
                                <p id="quantity${id}">
                                    Quantity: ${product.counter}      
                                </p>        
                              `
        let plusButton = document.createElement('button')
        plusButton.innerHTML = '+'
        // plusButton.addEventListener("click", function addQ(event) {
        //     fetch(`/addToCart${id}`, {method: "PUT"})
        //         .then(status)
        //         .then(json)
        //         .then()
        //         .catch((err) => {
        //             console.log(err)
        //         })
        // })
        let minusButton = document.createElement('button')
        minusButton.innerHTML = '-'
        // minusButton.addEventListener("click", function minusQ(event){
        //     fetch(`/minusToCart${id}`, {method: "PUT"})
        //         .then(getStatusCode)
        //         .then(json)
        //         .then()
        //         .catch((err)=>{
        //             console.log(err)
        //         })
        // })
        listItem.append(plusButton);
        listItem.append(minusButton);
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