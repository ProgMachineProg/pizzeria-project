let iconCart = document.querySelector('.d-flex');
let closeCart = document.querySelector('.close');
let body = document.querySelector('body');
let listProductHTML = document.querySelector('.row.gx-4.gx-lg-5.row-cols-2.row-cols-md-3.row-cols-xl-4.justify-content-center');

let listProducts = [];
iconCart.addEventListener('click', () => {
    body.classList.toggle('showCart')
})

closeCart.addEventListener('click', () => {
    body.classList.toggle('showCart');
})

const addDataToHTML = () => {
    listProductHTML.innerHTML = '';
    if(listProducts.length > 0) {
        listProducts.forEach(product => {
            let newProduct = document.createElement('div');
            newProduct.classList.add('col');
            newProduct.classList.add('mb-5')
            newProduct.innerHTML = `
                    <div class="card h-100">
                        <img class="card-img-top" src="${product.photoUrl}" alt="..."/>
                        <div class="card-body p-4">
                            <div class="text-center">
                                <h5 class="fw-bolder">${product.title}</h5>
                                <span>${product.price}</span>
                                <span>$</span>
                            </div>
                        </div>
                        <div class="buttons">
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent d-flex justify-content-between">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto"
                                                            href="/pizzas/${product.id}/edit">Edit</a>
                                </div>
                                    <div class="text-center">
                                        <button class="addCart btn btn-outline-dark mt-auto" href="">Order</button>
                                </div>
                            </div>
                        </div>
                    </div>
            `;
            listProductHTML.appendChild(newProduct);
        })
    }
}

listProductHTML.addEventListener('click', (event) => {
    let positionClick = event.target;
    if(positionClick.classList.contains('addCart')) {
        alert(1);
    }
})

const initApp = () => {
    // get data from json
    fetch('/pizzas/json')
        .then(response => response.json())
        .then(data => {
            listProducts = data;
            addDataToHTML();
        })
}
initApp();