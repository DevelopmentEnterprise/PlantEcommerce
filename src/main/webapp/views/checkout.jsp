<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="cart" scope="request" type="java.util.List<com.WebMall.model.CartItem>"/>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Main page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/checkout.css">
    <link rel="stylesheet" href="/resources/css/cart.css">
</head>
<body>
<header class="page-component">
    <div class="company-logo">
        <img src="https://cdn1.ozone.ru/s3/cms/b8/t95/logo_ozon_24_7_1.svg" alt="">
    </div>
</header>

<main class="page-component">
    <a href="" class="cart-redirect-link">
        <svg width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="_1nZS" style="transform: rotate(180deg);">
            <path fill-rule="evenodd" clip-rule="evenodd" d="M17.586 12L8.293 2.707a1 1 0 011.414-1.414l10 10a1 1 0 010 1.414l-10 10a1 1 0 01-1.414-1.414L17.586 12z" fill="currentColor"></path>
        </svg>
        В корзину
    </a>
    <h1>Оформление заказа</h1>

    <div style="display: flex; margin-bottom: 50px;">
        <div class="left-section">
            <c:if test="${error}">
                <div style="color: red">${error}</div>
            </c:if>
            <div class="delivery-types">
                <h2>Выберите тип доставки</h2>

                <div class="delivery-type">
                    <div class="delivery-type__select">
                        <input type="radio" name="delivery-type" value="0">
                    </div>

                    <div class="delivery-type__img">
                        <img src="https://www.torid.ru/images/data/pic1.jpg" alt="Самовывоз">
                    </div>
                    <div style="display: flex; flex-direction: column; align-items: center; justify-content: center; margin-left: 15px">
                        <div class="delivery-type__name">Самовывоз</div>
                        <div class="delivery-type__price">0 ₽</div>
                    </div>
                </div>

                <div class="delivery-type">
                    <div class="delivery-type__select">
                        <input type="radio" name="delivery-type" value="1">
                    </div>

                    <div class="delivery-type__img">
                        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Russian_Post.svg/1200px-Russian_Post.svg.png" alt="Почта">
                    </div>
                    <div style="display: flex; flex-direction: column; align-items: center; justify-content: center; margin-left: 15px">
                        <div class="delivery-type__name">Почта</div>
                        <div class="delivery-type__price">от 200 ₽</div>
                    </div>
                </div>

                <div class="delivery-type">
                    <div class="delivery-type__select">
                        <input type="radio" name="delivery-type" value="2">
                    </div>
                    <div class="delivery-type__img">
                        <img src="https://int-sm.ru/upload/iblock/4a3/4a3f65f4568dee5b9a73d9bdb22f34b2.jpg" alt="Сдэк">
                    </div>
                    <div style="display: flex; flex-direction: column; align-items: center; justify-content: center; margin-left: 15px">
                        <div class="delivery-type__name">Сдэк</div>
                        <div class="delivery-type__price">от 150 ₽</div>
                    </div>
                </div>
            </div>

            <div class="payment-types" style="margin-top: 45px;">
                <h2>Выберите тип оплаты</h2>

                <div class="payment-type">
                    <div class="delivery-type__select">
                        <input type="radio" name="payment-type" value="0">
                    </div>

                    <div class="delivery-type__img">
                        <img src="https://content.revizorro.ru/storage/app/uploads/public/5d2/86b/7e5/thumb_390_748_400_0_0_auto.jpg" alt="Карта">
                    </div>
                    <div style="display: flex; flex-direction: column; align-items: center; justify-content: center; margin-left: 15px">
                        <div class="delivery-type__name">Карта</div>
                    </div>
                </div>

                <div class="payment-type">
                    <div class="delivery-type__select">
                        <input type="radio" name="payment-type" value="1">
                    </div>

                    <div class="delivery-type__img">
                        <img src="https://lh3.googleusercontent.com/Ql5Hx8QKuQ23-6JFlRNVHy6AiW5KXUDWGkkHR1lI-XQFsuRLVUEoFhY_GO61CVa7C0FGHSKfYABavz0LmqDUa6zbUqEKTnVqXqbGng" alt="Google Pay">
                    </div>
                    <div style="display: flex; flex-direction: column; align-items: center; justify-content: center; margin-left: 15px">
                        <div class="delivery-type__name">Google Pay</div>
                    </div>
                </div>
            </div>

            <div class="order-items" style="margin-top: 45px;">
                <h2>Состав заказа</h2>

                <c:forEach var="cartItem" items="${cart}">
                    <div class="order-item">
                        <div style="display: flex; align-items: center">
                            <div class="order-item__img">
                                <img src="https://ae04.alicdn.com/kf/Hbe6667ad8d2541598bb6d5999b15e76aY.jpeg" alt="${cartItem.good.name}">
                            </div>

                            <div style="margin-left: 25px;">
                                <div class="order-item__comp">${cartItem.good.name}</div>
                                <div class="order-item__comp">${cartItem.good.description}</div>
<%--                                <div class="order-item__comp">${cartItem.good.}</div>--%>
                                <div class="order-item__comp">${cartItem.good.price} ₽</div>
                            </div>
                        </div>

                        <div>
                                ${cartItem.quantity} шт.
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="right-section">
            <div class="cart-info" style="width: unset;">
                <div class="cart-info__checkout">

                    <form:form action="/checkout/proceed" method="post" id="checkout-form" onsubmit="return formSendHandling();">
                        <input type="hidden" id="delivery-type" name="delivery-type" value="">
                        <input type="hidden" id="payment-type" name="payment-type" value="">

                        <button type="submit" class="register-btn c-pointer" style="text-transform: uppercase; text-align: center;border-radius: 5px; padding: 12px;">Оплатить</button>
                    </form:form>
                </div>

                <div class="cart-info__cart-stats">
                    <div>Общая стоимость</div>
                    <div>${sum} ₽</div>
                </div>
            </div>
        </div>
    </div>

</main>

<div class="page-overlay hide">
    <div class="main-menu-close-btn"></div>
</div>

<footer>
    <div class="footer-col">
        <div class="footer-col__header">Покупателям</div>

        <div class="footer-col__item">
            <a href="">Как сделать заказ</a>
        </div>
        <div class="footer-col__item">
            <a href="">Способы оплаты</a>
        </div>
        <div class="footer-col__item">
            <a href="">Доставка</a>
        </div>
        <div class="footer-col__item">
            <a href="">Возарат товара</a>
        </div>
        <div class="footer-col__item">
            <a href="">Возврат денежных средств</a>
        </div>
        <div class="footer-col__item">
            <a href="">Правила продажи</a>
        </div>
        <div class="footer-col__item">
            <a href="">Правила пользования торговой площадкой</a>
        </div>
        <div class="footer-col__item">
            <a href="">Вопросы и ответы</a>
        </div>
    </div>

    <div class="footer-col">
        <div class="footer-col__header">Партнерам</div>

        <div class="footer-col__item">
            <a href="">Продавайте у нас</a>
        </div>
        <div class="footer-col__item">
            <a href="">Транспортным кампаниям</a>
        </div>
        <div class="footer-col__item">
            <a href="">Правила размещения товаров</a>
        </div>
    </div>

    <div class="footer-col">
        <div class="footer-col__header">Компания</div>

        <div class="footer-col__item">
            <a href="">О нас</a>
        </div>
        <div class="footer-col__item">
            <a href="">Реквезиты</a>
        </div>
        <div class="footer-col__item">
            <a href="">Контакты</a>
        </div>
    </div>

    <div class="footer-col">
        <div class="footer-col__header">Мы в соцсетях</div>

        <div class="footer-col__item">
            <a href="">Вконтакте</a>
        </div>
        <div class="footer-col__item">
            <a href="">Facebook</a>
        </div>
        <div class="footer-col__item">
            <a href="">Instagram</a>
        </div>
        <div class="footer-col__item">
            <a href="">Одноклассники</a>
        </div>
    </div>

    <div class="footer-col">
        <div class="footer-col__header">Мобильные устройства</div>

        <div class="footer-col__item">
            <a href="">Приложение в Google Play</a>
        </div>
        <div class="footer-col__item">
            <a href="">Приложение в App Store</a>
        </div>
    </div>
</footer>
</body>

<script>
    document.querySelectorAll('.delivery-type').forEach(el => el.addEventListener('click', ()=>{
        const radioBtn = el.children[0].children[0];
        radioBtn.checked = true;
        const deliveryType = radioBtn.getAttribute('value');

        document.querySelector('#delivery-type').value = deliveryType;
    }));

    //Add layer before form sending
    const formSendHandling = ()=>{
        const deliveryTypeField = document.querySelector('#delivery-type');
        const paymentTypeField = document.querySelector('#payment-type');

        if (deliveryTypeField.value.trim() === "" || paymentTypeField.value.trim() === ""){
            alert("Вы не выбрали тип доставки или оплаты");
            return false;
        }

        return true;
    };

    document.querySelectorAll('.payment-type').forEach(el => el.addEventListener('click', ()=>{
        const radioBtn = el.children[0].children[0];
        radioBtn.checked = true;
        const paymentType = radioBtn.getAttribute('value');

        document.querySelector('#payment-type').value = paymentType;
    }));
</script>
</html>