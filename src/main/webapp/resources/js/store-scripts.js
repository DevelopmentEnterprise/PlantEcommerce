document.addEventListener('DOMContentLoaded', ()=>{
    const goodTypes = document.querySelectorAll('.good-type');
    const orderTypes = document.querySelectorAll('.order-type');
    const goodSearcherInput = document.querySelector('.goods-searcher__input');
    const goodControlBtns =  document.querySelectorAll('.good-control-btn img');
    const deleteStoreGood = document.querySelector('.delete-store-good');
    const variationAdd = document.querySelector('#variation-add');
    const goodOptionAdd = document.querySelector('#good-option-add');
    const goodImageCont = document.querySelectorAll('.good-image-cont');
    const deleteGoodImage = document.querySelectorAll('.delete-good-image');
    const controlForm = document.querySelector('#control-form');

    //Make api calls to filter goods in store account
    goodTypes.forEach((el, index) => el.addEventListener('click',()=>{
            makeFilterRequest(el, index);
        }));

    //Make api calls to filter orders in store account
    orderTypes.forEach((el, index) => el.addEventListener('click', ()=>{
            makeFilterRequest(el, index);
        }));

    //Set trigger for dynamic search
    if (goodSearcherInput != null)
        goodSearcherInput.addEventListener('input', e =>{
           dynamicSearch(e);
        });

    //Context menu open triggers set
    goodControlBtns.forEach(el => el.addEventListener('click',()=>{
        const contextMenu = document.querySelector('.good-action-panel');
        contextMenu.classList.toggle('hide');
    }));

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

    if (variationAdd != null)
        variationAdd.addEventListener('click', ()=>{
            document.querySelector('.variation-popup').classList.remove('hide');
            document.querySelector('.page-overlay').classList.remove('hide');
        });

    if (goodOptionAdd != null)
        goodOptionAdd.addEventListener('click', ()=>{
            const goodItemsCtrl = document.querySelector('.good-options-ctrl');
            const currentGoodItemId = document.querySelector('.variations').children[0].children.length;

            const name = document.querySelector('#good-option-name');
            const price = document.querySelector('#good-option-price');

            const nameElem = document.createElement('input');
            nameElem.setAttribute('name', `goodOptions[${currentGoodItemId}].name`);
            nameElem.setAttribute('value', name.value);
            nameElem.classList.add('hide');

            const priceElem = document.createElement('input');
            priceElem.setAttribute('name', `goodOptions[${currentGoodItemId}].price`);
            priceElem.setAttribute('value', price.value);
            priceElem.classList.add('hide');

            goodItemsCtrl.appendChild(nameElem);
            goodItemsCtrl.appendChild(priceElem);
        });

    // if (customSelect != null)
    //     customSelect.addEventListener('change', ()=>{
    //     let goodCategory = document.querySelector('.custom-select').options[document.querySelector('.custom-select').selectedIndex].value;
    //     document.querySelector('#good-category-param').setAttribute('value', goodCategory);
    // });

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

    deleteGoodImage.forEach(el => el.addEventListener('click', ()=>{
        const imageId = parseInt(el.parentElement.getAttribute('data'));
        if (isNaN(imageId)) return;

        const request = new XMLHttpRequest();
        let url = 'http://localhost:8080/api/store/deleteGoodImageBy?goodImageId=' + imageId;
        request.open('GET', url);
        request.send();

        request.onload = () => {
            if (request.status === 200 && request.response == 1){
                el.parentElement.remove();
            }
        };
    }));

    if (controlForm!= null){
        controlForm.onsubmit = () =>{

            //Validate form inputs
            const goodNameInput = document.querySelector('#good-name');
            const goodDescInput = document.querySelector('#good-description');

            // if (goodNameInput.value.trim() === "" || goodDescInput.value.trim() === "" || customSelect.selectedIndex !== -1){
            //     return false;
            // }

            let goodCategory = document.querySelector('.custom-select').options[document.querySelector('.custom-select').selectedIndex].value;
            document.querySelector('#good-category-param').setAttribute('value', goodCategory);
        }
    }
});

const makeFilterRequest = (el, index) => {
    let request = new XMLHttpRequest();
    let url = "http://localhost:8080/api/store/";
    let isGoodsRequest = true;

    //Order filter
    if(el.classList.contains("order-type")){
        url += "getOrdersByFilter?ordersFilter=";
        isGoodsRequest = false;
        //TODO: Изменить вид отправки запроса с текстового вида на enum параметр
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

            if (isGoodsRequest)
                showStoreGoods(jsonResponse);
            else
                showStoreOrders(jsonResponse);
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

            html += ` <td class="good-control-btn c-pointer"><img src="${window.location.pathname}/resources/static/ctrl-dots.svg" alt="Управлять"></td>
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

        html += `
            <tr>
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
                
            </tr>
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
