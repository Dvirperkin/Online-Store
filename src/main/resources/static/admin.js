( function () {
    document.addEventListener("DOMContentLoaded", ()=>{
        document.getElementById("ProductName").addEventListener("change", trimInput)
        document.getElementById("Price").addEventListener("change", trimInput)
        document.getElementById("Discount").addEventListener("change", trimInput)
        document.getElementById("Stock").addEventListener("change", trimInput)
        document.getElementById("ToUpdateID").addEventListener("change", trimInput)
        document.getElementById("ToUpdateName").addEventListener("change", trimInput)
        document.getElementById("ToUpdatePrice").addEventListener("change", trimInput)
        document.getElementById("ToUpdateDiscount").addEventListener("change", trimInput)
        document.getElementById("ToUpdateStock").addEventListener("change", trimInput)
        document.getElementById("ToDeleteID").addEventListener("change", trimInput)
    })

    function trimInput(event){
        event.target.value = event.target.value.trim()
    }
})()