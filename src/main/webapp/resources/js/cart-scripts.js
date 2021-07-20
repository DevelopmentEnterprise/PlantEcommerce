let cart = [];

//CartGood entity
class CartGood{
    constructor(goodId, quantity) {
        this.goodId = goodId;
        this.quantity = quantity;
    }
}

document.addEventListener("DOMContentLoaded", ()=>{
    document.querySelectorAll('.add-to-cart-btn').forEach(el => el.addEventListener('click', ()=>{
        addGoodToCart(parseInt(el.getAttribute('data')) || -1);
    }));

    if (document.querySelector('.cart-items') != null){
        showCartItemsFromLS();
    }
    if (document.querySelector('.cart-item-info__ctrl-btn'))
        document.querySelectorAll('.cart-item-info__ctrl-btn').forEach(el => el.addEventListener('click', ()=>{
            handleCartItemDelete(el);
        }));
});

const getCart = ()=>{
    //Check value in local storage
    if (localStorage.getItem('cart') != null)
        cart = JSON.parse(localStorage.getItem('cart'));
    else
        getCartRequest();
};

const addGoodToCart = goodId =>{
    if (goodId === -1) return false;

    const cartState = document.querySelector('.cart-state');
    let goodExist = checkGoodInCartExists(goodId);
    let quantity;

    //If good exists increase count of this good
    if (goodExist){
        const cartItem = cart.find(el => el.goodId === goodId);
        // cart.find(el => el.goodId === goodId).quantity++;
        cartItem.quantity++;
        quantity = cartItem.quantity;
    }else{
        const cartGood = new CartGood(goodId, 1);
        quantity = 1;
        cart.push(cartGood);
    }

    //Update cart info on good page
    if (cartState){
        cartState.classList.remove('hide');
        cartState.textContent = 'В корзине: ' + quantity + 'шт.';
    }

    localStorage.setItem('cart', JSON.stringify(cart));
    editCartItemRequest(goodId, quantity);
};

const checkGoodInCartExists = goodId =>{
    for (let i = 0;i < cart.length; i++){
        if (cart[i].goodId === goodId)
            return true;
    }

    return false;
};

const getCartRequest = () =>{
  const request = new XMLHttpRequest();
  const url = 'http://localhost:8080/api/customer/getCustomerCart';
  request.open('GET', url);
  request.send();

  request.onload = ()=>{
      const jsonResponse = JSON.parse(request.response);

      jsonResponse.forEach(el => {
          let newCartGood = new CartGood(el.good.id, el.quantity);
          cart.push(newCartGood);
      });

      localStorage.setItem('cart', JSON.stringify(cart));
  };
};

const getCartItemQuantity = goodId =>{
    for (let i = 0; i < cart.length; i++) {
        if (cart[i].goodId === goodId)
            return cart[i].quantity;
    }

    return -1;
};

const editCartItemRequest = (goodId, quantity) =>{
    const request = new XMLHttpRequest();
    let url = `http://localhost:8080/api/customer/addGoodToCart?goodId=${goodId}&quantity=${quantity}`;
    request.open('GET', url);
    request.send();
};

const deleteCartItemRequest = goodId =>{
    const request = new XMLHttpRequest();
    let url = `http://localhost:8080/api/customer/deleteCartGood?goodId=${goodId}`;
    request.open('GET', url);
    request.send();
};
//-----Scripts for cart page----------//
const showCartItemsFromLS = () =>{
    if (document.querySelector('.cart-items').children.length > 1){
        document.querySelectorAll('.good-quantity').forEach(el => el.addEventListener('change', ()=>{
            const goodId = parseInt(el.parentElement.parentElement.getAttribute('data'));
            handleCartItemQuantityChange(el, goodId);
        }));
        return;
    }

    //No good items got from server
    const localCart = JSON.parse(localStorage.getItem('cart'));

    if (localCart == null){
        const cartEmptyInfoBlock = document.querySelector('.cart-empty-info');
        cartEmptyInfoBlock.classList.remove('hide');
        return;
    }

    let goodCartIds = [];
    localCart.forEach(el => goodCartIds.push(el.goodId));

    const request = new XMLHttpRequest();
    const url = 'http://localhost:8080/api/customer/getCartGoods';
    request.open('POST', url);
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    request.send(JSON.stringify(goodCartIds));

    request.onload = ()=>{
        const responseJson = JSON.parse(request.response);
        sessionStorage.setItem('cartGoods', JSON.stringify(responseJson));
        showGoodsInCart(responseJson, localCart);
        calculateSum();
    };

    document.querySelector('.cart-info').classList.remove('hide');
};

const showGoodsInCart = (goods, localCart) =>{
    let innerHtml = ``;

    for (let i = 0; i < goods.length; i++) {
        innerHtml += `<div class="cart-item" data="`+ goods[i].id +`">
                        <div class="cart-item__img">
                            <img src="`+ goods[i].goodImages[0].imageSrc +`" alt="`+ goods[i].name +`">
                        </div>
                        <div class="cart-item-info">
                            <div class="cart-item-info__name">` + goods[i].name + `</div>
                            <div class="cart-item-info__description">`+ goods[i].description +`</div>
                            <a class="cart-item-info__ctrl-btn c-pointer">Удалить</a>
                        </div>
                        <div class="cart-item__sum">` + goods[i].price * localCart[i].quantity + `₽</div>
                        <div class="cart-item__quantity">
                            <input type="number" class="good-quantity" min="1" value="`+ localCart[i].quantity +`">
                        </div>
                    </div>`;
    }

    document.querySelector('.cart-items').innerHTML = innerHtml;
    document.querySelectorAll('.good-quantity').forEach(el => el.addEventListener('change', ()=>{
        const goodId = parseInt(el.parentElement.parentElement.getAttribute('data'));
        handleCartItemQuantityChange(el, goodId);
    }));
    document.querySelectorAll('.cart-item-info__ctrl-btn').forEach(el => el.addEventListener('click', ()=>{
        handleCartItemDelete(el);
    }));
};

const handleCartItemQuantityChange = (input, goodId) =>{
    cart.find(el => el.goodId === goodId).quantity = parseInt(input.value);

    const cartItemSumBlock = input.parentElement.parentElement.children[2];
    const cartGood = JSON.parse(sessionStorage.getItem('cartGoods')).find(el => el.id === goodId);
    let cartItemSum = parseInt(input.value) * cartGood.price;

    cartItemSumBlock.textContent = `${cartItemSum}₽`;
    localStorage.setItem('cart', JSON.stringify(cart));
    editCartItemRequest(goodId, input.value);
    calculateSum();
};

const calculateSum = ()=>{
    let sum = 0;
    const cart = JSON.parse(localStorage.getItem('cart'));
    const cartGoods = JSON.parse(sessionStorage.getItem('cartGoods'));

    for (let i = 0; i < cart.length; i++){
        sum += cart[i].quantity * cartGoods.find(el => el.id === cart[i].goodId).price;
    }

    document.querySelector('#order-checkout-sum').textContent = sum.toString();
};

const handleCartItemDelete = btn =>{
    const goodId = parseInt(btn.parentElement.parentElement.getAttribute('data'));
    let index = cart.findIndex(el => el.goodId === goodId);
    cart.splice(index, 1);
    localStorage.setItem('cart', JSON.stringify(cart));

    //Delete cart line
    btn.parentElement.parentElement.remove();

    deleteCartItemRequest(goodId);
};
//-----End of scripts for cart page----//

getCart();