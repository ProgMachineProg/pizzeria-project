let iconCart = document.querySelector('.d-flex');
let closeCart = document.querySelector('.close');
let body = document.querySelector('body');
let listProductHTML = document.querySelector('.row.gx-4.gx-lg-5.row-cols-2.row-cols-md-3.row-cols-xl-4.justify-content-center');
let listCartHTML = document.querySelector('.listCart');
let iconCartSpan = document.querySelector('.badge.bg-dark.text-white.ms-1.rounded-pill');

let listProducts = [];
let carts = [];
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
            newProduct.classList.add('mb-5');
            newProduct.dataset.id = product.id;
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
        let product_id = positionClick.closest('.col').dataset.id;
        addToCart(product_id);
    }
})

const addToCart = (product_id) => {
    let positionThisProductInCart = carts.findIndex((value) => value.product_id == product_id);
    if(carts.length <= 0) {
        carts = [{
            product_id: product_id,
            quantity: 1
        }]
    }else if(positionThisProductInCart < 0) {
        carts.push({
            product_id:product_id,
            quantity: 1
        });
    }else{
        carts[positionThisProductInCart].quantity = carts[positionThisProductInCart].quantity + 1;
    }
    addCartToHTML();
    addCartToMemory();
}

const addCartToMemory = () => {
    localStorage.setItem('cart', JSON.stringify(carts));
}

const addCartToHTML = () => {
    listCartHTML.innerHTML = '';
    let totalQuantity = 0;
    if(carts.length > 0) {
        carts.forEach(cart => {
            totalQuantity = totalQuantity + cart.quantity;
            let newCart = document.createElement('div');
            newCart.classList.add('item');
            newCart.dataset.id = cart.product_id;
            let positionProduct = listProducts.findIndex((value) => value.id == cart.product_id);
            let info = listProducts[positionProduct];
            newCart.innerHTML = `
            <div class="image">
                    <img src="${info.photoUrl}" alt="">
                </div>
                <div class="name">
                    ${info.title}
                </div>
                <div class="totalPrice">
                    ${info.price * cart.quantity}$
                </div>
                <div class="quantity">
                    <span class="minus"><</span>
                    <span>${cart.quantity}</span>
                    <span class="plus">></span>
                </div>
            `;
            listCartHTML.appendChild(newCart);
        })
    }
    iconCartSpan.innerHTML = totalQuantity;
}

listCartHTML.addEventListener('click', (event) => {
    let positionClick = event.target;
    if(positionClick.classList.contains('minus') || positionClick.classList.contains('plus')) {
        let product_id = positionClick.parentElement.parentElement.dataset.id;
        let type = 'minus';
        if(positionClick.classList.contains('plus')){
            type='plus';
        }
        changeQuantity(product_id, type);
    }
})

const changeQuantity = (product_id, type) => {
    let positionItemInCart = carts.findIndex((value) => value.product_id == product_id);
    if(positionItemInCart >=0) {
        switch (type) {
            case 'plus':
                carts[positionItemInCart].quantity = carts[positionItemInCart].quantity + 1;
                break;
            default:
                let valueChange = carts[positionItemInCart].quantity - 1;
                if(valueChange > 0) {
                    carts[positionItemInCart].quantity = valueChange;
                }else {
                    carts.splice(positionItemInCart, 1);
                }
                break;
        }
    }
    addCartToMemory();
    addCartToHTML();
}

const initApp = () => {
    // get data from json
    fetch('/pizzas/json')
        .then(response => response.json())
        .then(data => {
            listProducts = data;
            addDataToHTML();

            // get cart from memory
            if(localStorage.getItem('cart')) {
                carts = JSON.parse(localStorage.getItem('cart'));
                addCartToHTML()
            }
        })
}
initApp();