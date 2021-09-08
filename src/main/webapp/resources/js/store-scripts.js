let goodControlBtns =  document.querySelectorAll('.good-control-btn img');
let editOrderBtns = document.querySelectorAll('.edit-order-btn');

document.addEventListener('DOMContentLoaded', ()=>{
    const goodTypes = document.querySelectorAll('.good-type');
    const orderTypes = document.querySelectorAll('.order-type');
    const goodSearcherInput = document.querySelector('.goods-searcher__input');
    const deleteStoreGood = document.querySelector('.delete-store-good');
    const variationAdd = document.querySelector('#variation-add');
    const goodOptionAdd = document.querySelector('#good-option-add');
    const goodImageCont = document.querySelectorAll('.good-image-cont');
    const deleteGoodImage = document.querySelectorAll('.delete-good-image');
    const controlForm = document.querySelector('#control-form');
    const deleteGoodOptionBtns = document.querySelectorAll('.delete-good-option');
    const customSelect = document.querySelector('.custom-select');
    const storeEditPopup = document.querySelector('.store-edit-popup');
    const pageOverlay = document.querySelector('.page-overlay');
    const cancelOrderBtn = document.querySelector('.cancel-order-btn');
    const editedOrderId = document.querySelector('#edited-order-id');
    const saveStoreInfo = document.querySelector('.save-store-info');
    const addCouponBtn = document.querySelector('.add-coupon-btn');
    const addCouponPopup = document.querySelector('.coupon-add-popup');
    const addCouponSubmit = document.querySelector('.add-coupon-btn-popup');

    //Make api calls to filter goods in store account
    goodTypes.forEach((el, index) => el.addEventListener('click',()=>{
        //Make clicked elem selected
        goodTypes.forEach(e => e.classList.remove('order-type-selected'))
        el.classList.add('order-type-selected');
        makeFilterRequest(el, index);
    }));

    //Make api calls to filter orders in store account
    orderTypes.forEach((el, index) => el.addEventListener('click', ()=>{
        orderTypes.forEach(e => e.classList.remove('order-type-selected'));
        el.classList.add('order-type-selected');
        makeFilterRequest(el, index);
    }));

    //Set trigger for dynamic search
    if (goodSearcherInput != null)
        goodSearcherInput.addEventListener('input', e =>{
           dynamicSearch(e);
        });

    //Context menu open triggers set
    contextMenuToggle(goodControlBtns);

    //Delete good btn clicked
    if (deleteStoreGood != null)
        deleteStoreGood.addEventListener('click', event =>{
        const goodId = event.target.parentElement.parentElement.parentElement.children[0].textContent;

        if (goodId == null) return;

        //Execute delete good request
        let request = new XMLHttpRequest();
        let url = "http://localhost:8080/api/store/deleteGoodById?goodId=" + goodId;

        request.open("GET", url);
        request.send();

        request.onload = ()=>{
            if (request.status === 200){
                const responseText = request.response;

                if (responseText === -1)
                    alert("Ошибка при удалении товара, повторите попытку позже!");
                else{
                    let rowToRemove = event.target.parentElement.parentElement.parentElement;
                    rowToRemove.remove();
                    alert("Товар успешно удален!");
                }
            }else
                alert("Ошибка при удалении товара, повторите попытку позже!");
        };
    });

    //Add new variation button
    if (variationAdd != null)
        variationAdd.addEventListener('click', ()=>{
            document.querySelector('.variation-popup').classList.remove('hide');
            document.querySelector('.page-overlay').classList.remove('hide');
        });

    //Add good option button
    if (goodOptionAdd != null)
        goodOptionAdd.addEventListener('click', ()=>{
            const goodItemsCtrl = document.querySelector('.good-options-ctrl');
            const currentGoodItemId = document.querySelector('.variations').children[0].children.length;

            const name = document.querySelector('#good-option-name');
            const price = document.querySelector('#good-option-price');

            const nameElem = document.createElement('input');
            nameElem.setAttribute('name', `goodOptions[${currentGoodItemId}].name`);
            nameElem.setAttribute('value', name.value);
            nameElem.setAttribute('type', 'text');
            nameElem.classList.add('hide');

            const priceElem = document.createElement('input');
            priceElem.setAttribute('name', `goodOptions[${currentGoodItemId}].price`);
            priceElem.setAttribute('value', price.value);
            priceElem.setAttribute('type', 'text');
            priceElem.classList.add('hide');

            goodItemsCtrl.appendChild(nameElem);
            goodItemsCtrl.appendChild(priceElem);
        });

    //Good card image selection on hover
    goodImageCont.forEach(el => el.addEventListener('mouseover', e =>{
        const closeBtn = e.target.parentElement.children[0];
        const overlay = e.target.parentElement.children[1];

        closeBtn.classList.remove('hide');
        overlay.classList.remove('hide');
    }));
    goodImageCont.forEach(el => el.addEventListener('mouseout', e =>{
        const closeBtn = e.target.parentElement.children[0];
        const overlay = e.target.parentElement.children[1];

        closeBtn.classList.add('hide');
        overlay.classList.add('hide');
    }));

    //Delete good image request sender
    deleteGoodImage.forEach(el => el.addEventListener('click', ()=>{
        const imageId = parseInt(el.parentElement.getAttribute('data'));
        if (isNaN(imageId)) return;

        const request = new XMLHttpRequest();
        let url = 'http://localhost:8080/api/store/deleteGoodImageById?goodImageId=' + imageId;
        request.open('GET', url);
        request.send();

        request.onload = () => {
            if (request.status === 200 && request.response == 1){
                el.parentElement.remove();
            }
        };
    }));

    //Form validation before sending to API
    if (controlForm!= null){
        controlForm.onsubmit = () =>{

            //Validate form inputs
            const goodNameInput = document.querySelector('#good-name');
            const goodDescInput = document.querySelector('#good-description');

            if (goodNameInput.value.trim() === "" || goodDescInput.value.trim() === "" || customSelect.selectedIndex === -1){
                return false;
            }

            let goodCategory = document.querySelector('.custom-select')
                .options[document.querySelector('.custom-select').selectedIndex].value;

            document.querySelector('#good-category-param').setAttribute('value', goodCategory);
        }
    }

    //Delete good option
    if (deleteGoodOptionBtns)
        deleteGoodOptionBtns.forEach(el => el.addEventListener('click', ()=>{
            const dataAttr = el.parentElement.parentElement.parentElement.getAttribute('data');
            const goodOptionNameElem = document.getElementsByName(`goodOptions[${dataAttr}].name`)[0];
            const goodOptionPriceElem = document.getElementsByName(`goodOptions[${dataAttr}].price`)[0];
            goodOptionNameElem.remove();
            goodOptionPriceElem.remove();
        }));

    //Edit store btn
    refreshEditOrderBtnsEvents();

    if (cancelOrderBtn)
        cancelOrderBtn.addEventListener('click', ()=>{
            let confirmResult = confirm("Вы действительно хотите отменить заказ клиента?\n Это значительно снизит Ваш рейтинг и составит о Вас негативное впечатление.");
            if(!confirmResult) return;

            const orderId = editedOrderId.getAttribute('data');

            storeEditPopup.classList.add('hide');
            pageOverlay.classList.add('hide');

            sendCancelOrderRequest(orderId);
        });

    if (saveStoreInfo)
        saveStoreInfo.addEventListener('click', ()=>{
            const orderId = editedOrderId.getAttribute('data');

            storeEditPopup.classList.add('hide');
            pageOverlay.classList.add('hide');

            sendSaveOrderRequest(orderId);
        });

    if (addCouponBtn)
        addCouponBtn.addEventListener('click', ()=>{
            addCouponPopup.classList.remove('hide');
            pageOverlay.classList.remove('hide');
        });

    if (addCouponSubmit)
        addCouponSubmit.addEventListener('click', ()=>{
            const couponName = document.querySelector('#coupon-name-input').value;
            const couponPercent = parseInt(document.querySelector('#coupon-percent-input').value);
            const couponExpiredDate = document.querySelector('#coupon-date-input').value;
            const couponActive = document.querySelector('#coupon-active-input').checked;

            //Validate created coupon
            if (couponName === "" || couponExpiredDate === "" || isNaN(couponPercent))
                return;

            //Check coupon percent limit
            if (couponPercent > 100){
                alert("Величина процента скидки не может быть больше 100!");
                return;
            }

            const requestBody = {
                coupon: couponName,
                expiredDate: couponExpiredDate,
                discount: couponPercent,
                isActive: couponActive
            };

            console.log(requestBody);

            const request = new XMLHttpRequest();
            const url = 'http://localhost:8080/api/store/addCoupon';

            request.open('POST', url);
            request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            request.send(JSON.stringify(requestBody));

            request.onload = ()=>{
                if (request.response == 1){
                    let html = `<div class="coupon__preview">
                            <span>
                                Купон на ${requestBody.discount} %
                            </span>
                        </div>
    
                        <div class="coupon-info">
                            <div>
                                <div class="coupon-info__elem">Срок действия: активен до ${requestBody.expiredDate}</div>
                                    <div class="coupon-info__elem">Магазин: ${document.querySelector('.store-name').textContent}</div>
                                    <div class="coupon-info__elem">Статус купона: ${requestBody.isActive ? "Активен" : "Не активен"}</div>
                                </div>
                            </div>
                        </div>`;

                    const couponsBlock = document.querySelector('.coupons');
                    const newCoupon = document.createElement('div');
                    newCoupon.classList.add('coupon');
                    newCoupon.innerHTML = html;
                    couponsBlock.appendChild(newCoupon);

                    addCouponPopup.classList.add('hide');
                    pageOverlay.classList.add('hide');
                }else{
                    alert("Ошибка при создании купона. Пожалуйста, повторите попытку позже!");
                }
            }
        });
});

const refreshEditOrderBtnsEvents = ()=>{
    editOrderBtns = document.querySelectorAll('.edit-order-btn');

    editOrderBtns.forEach(el => el.addEventListener('click', ()=>{
        const orderId = el.parentElement.parentElement.parentElement.getAttribute('data');
        document.querySelector('#edited-order-id').setAttribute('data', orderId);

        document.querySelector('.store-edit-popup').classList.remove('hide');
        document.querySelector('.page-overlay').classList.remove('hide');
        el.parentElement.classList.add('hide');
    }));
};

const contextMenuToggle = items =>{
    items.forEach(el => el.addEventListener('click',()=>{
        const contextMenuBlock = el.parentElement.children[1];
        contextMenuBlock.classList.toggle('hide');
    }));
};

const makeFilterRequest = (el, index) => {
    let request = new XMLHttpRequest();
    let url = "http://localhost:8080/api/store/";
    let isGoodsRequest = true;

    //Order filter
    if(el.classList.contains("order-type")){
        url += "getOrdersByFilter?ordersFilter=";
        isGoodsRequest = false;

        switch (index) {
            case 1:
                //Заказы, которые ожидают сборки
                url += "assembly";
                break;

            case 2:
                //Заказы, которые доставляются
                url += "deliver";
                break;

            case 3:
                //Заказы, которые выполнены
                url += "completed";
                break;
        }
    }else{
        //Goods filter
        url += "getGoodsByFilter?goodsFilter=";

        switch (index) {
            case 1:
                //Товары, которые в продаже
                url += "sale";
                break;

            case 2:
                //Товары, которые скрыты
                url += "hidden";
                break;
        }
    }

    request.open("GET", url);
    request.send();

    request.onload = () => {
        if (request.status === 200){
            let jsonResponse = JSON.parse(request.response);
            sessionStorage.setItem("storeGoods", JSON.stringify(jsonResponse));

            if (isGoodsRequest) {
                showStoreGoods(jsonResponse);
                goodControlBtns = document.querySelectorAll('.good-control-btn img');
                contextMenuToggle(goodControlBtns);
            }else {
                showStoreOrders(jsonResponse);
                goodControlBtns = document.querySelectorAll('.good-control-btn img');
                contextMenuToggle(goodControlBtns);
                refreshEditOrderBtnsEvents();
            }
        }
    };
};

const showStoreGoods = goodsToShow =>{
    const goodsList = document.querySelector(".goods-list").children[0].children[0];

    for (let i = 1; i < goodsList.children.length; i++){
        goodsList.children[i].innerHTML = '';
    }

    for (let i = 0;i < goodsToShow.length; i++){
        let elem = document.createElement("tr");
        const good = goodsToShow[i];

        let html = `<tr>
        <td>${good.id}</td>
        <td>
        <img src="${good.goodImages[0].imageSrc}" alt="${good.name}">
            </td>
            <td>
            ${good.name}
            </td>
            <td>${good.price} руб.</td>`;

            if (good.priceBeforeDiscount != null){
                html += `<td>${ Math.round(100 * (1 - good.price / good.priceBeforeDiscount)) }%</td>`;
            }else{
                html += `<td></td>`;
            }

            html += `
                <td class="good-control-btn c-pointer">
                    <img src="/resources/static/ctrl-dots.svg" alt="Управлять">
                    
                    <div class="good-action-panel hide">
                        <a href="/store/editStoreGood?goodId=${goodsToShow[i].id}" class="good-action-panel__btn edit-store-good">Редактировать</a>
                        <a href="" class="good-action-panel__btn delete-store-good">Удалить</a>
                    </div>
                </td>
            </tr>`;

        elem.innerHTML = html;
        goodsList.appendChild(elem);
    }
};

const showStoreOrders = ordersToShow =>{
    const goodsList = document.querySelector(".orders-list").children[0];

    for (let i = 1; i < goodsList.children.length; i++){
        goodsList.children[i].innerHTML = '';
    }

    let html = ``;

    for (let i = 0; i < ordersToShow.length; i++) {
        let elem = document.createElement("tr");
        const order = ordersToShow[i];
        elem.setAttribute('data', order.id.toString());

        html = `
                <td>
                    ${order.id}
                </td>
                
                <td>
                    ${order.status}
                </td>
                <td>`;

                for (let j = 0; j < order.orderGoods.length; j++) {
                    html+=`
                        <div class="order-good-info">${order.orderGoods[j].name}, ${order.orderGoods[j].quantity} шт.</div>
                    `;
                }

                html += `</td>

                <td>
                    ${order.sum} руб.
                </td>
                
                <td>
                    ${order.orderDate.substr(0, 10).replace('-', ' ')}
                </td>
                
                <td class="good-control-btn c-pointer">
                    <img src="/resources/static/ctrl-dots.svg" alt="Управлять">

                    <div class="good-action-panel hide">
                        <div class="good-action-panel__btn edit-order-btn">Редактировать</div>
                    </div>
                </td>
        `;

        elem.innerHTML = html;
        goodsList.appendChild(elem);
    }
};

const dynamicSearch = event => {
    const value = event.target.value;
    let lastLoadedGoods = JSON.parse(sessionStorage.getItem("storeGoods"))

    //Make api call if no data in SS
    if (lastLoadedGoods == null){
        let request = new XMLHttpRequest();
        let url = '/api/store/getAllStoreGoods';
        request.open("GET", url);
        request.send();

        request.onload = () =>{
            if (request.status === 200){
                let jsonResponse = JSON.parse(request.response);
                sessionStorage.setItem("storeGoods", JSON.stringify(jsonResponse));

                lastLoadedGoods = jsonResponse;
            }
        };
    }

    let goodsFit = [];

    //Filter goods to get ones that fit search request
    goodsFit = lastLoadedGoods.filter(el => el.name.toLowerCase().includes(value.toLowerCase()));

    //Show goods in UI
    showStoreGoods(goodsFit);
};

const sendCancelOrderRequest = orderId =>{
    //Check orderId is number and not null
    if(orderId === '' || isNaN(orderId)) return;

    let request = new XMLHttpRequest();
    let url = 'http://localhost:8080/api/store/cancelOrder?orderId=' + orderId;
    request.open('GET', url);
    request.send();

    request.onload = ()=>{
        const response = request.response;
        console.log(response);
    };
};

const sendSaveOrderRequest = orderId =>{
    //Check orderId is number and not null
    if(orderId === '' || isNaN(orderId)) return;

    const orderStatusInput = document.querySelector('#order-status-input')

    let orderStatusId = orderStatusInput.selectedIndex;
    if(orderStatusId === -1) return;

    let request = new XMLHttpRequest();
    let url = `http://localhost:8080/api/store/saveOrder?orderId=${orderId}&orderStatusId=${orderStatusId}`;
    request.open('GET', url);
    request.send();

    //Refresh page after sending request
    location.reload();
};