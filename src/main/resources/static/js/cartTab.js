let iconCart = document.querySelector('.d-flex');
let closeCart = document.querySelector('.close');
let body = document.querySelector('body');
let listProductHTML = document.querySelector('.py-5');
let addCart = document.querySelector('.addCart');

let listProducts = [];
iconCart.addEventListener('click', () => {
    body.classList.toggle('showCart')
})

closeCart.addEventListener('click', () => {
    body.classList.toggle('showCart');
})

addCart.addEventListener('click', () => {
    console.log(1);

})

const initApp = () => {
    // get data from json
    fetch('json/products.json')
        .then(response => response.json())
        .then(data => {
            listProducts = data;

        })
}
initApp();