<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:useBean id="good" scope="request" type="com.WebMall.model.Good"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>Название</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/resources/css/main.css" />
    <link rel="stylesheet" href="/resources/css/add-good.css">
</head>
<body>
<header class="page-component">
    <div class="pc-type">
        <div class="top-bar">
            <div class="top-bar__location">
                <div>
                    <!-- Here svg goes -->
                </div>
            </div>
            <div class="top-bar-nav">
                <a href="" class="top-bar-nav__elem">
                    <div>Покупайте как юрлицо</div>
                </a>
                <a href="" class="top-bar-nav__elem">
                    <div>Мобильное приложение</div>
                </a>
                <a href="" class="top-bar-nav__elem">
                    <div>Реферальная система</div>
                </a>
                <a href="" class="top-bar-nav__elem">
                    <div>Зарабатывайте с нами</div>
                </a>
                <a href="" class="top-bar-nav__elem">
                    <div>Подарочные сертификаты</div>
                </a>
                <a href="" class="top-bar-nav__elem">
                    <div>Пункты выдачи</div>
                </a>
                <a href="" class="top-bar-nav__elem">
                    <div>Постаматы</div>
                </a>
                <a href="" class="top-bar-nav__elem">
                    <div>Помощь</div>
                </a>
            </div>
        </div>

        <div class="header-bar">
            <div class="logo">
                <img src="" alt="Logo">
            </div>

            <div class="menu-button" style="display: flex; align-items: center;">
                <div class="menu-button__icon">
                    <svg width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="_1nZS"><path fill-rule="evenodd" clip-rule="evenodd" d="M5 6a1.5 1.5 0 11-3 0 1.5 1.5 0 013 0zm4-1a1 1 0 000 2h12a1 1 0 100-2H9zm0 6a1 1 0 100 2h12a1 1 0 100-2H9zm-1 7a1 1 0 011-1h6a1 1 0 110 2H9a1 1 0 01-1-1zm-4.5-4.5a1.5 1.5 0 100-3 1.5 1.5 0 000 3zM5 18a1.5 1.5 0 11-3 0 1.5 1.5 0 013 0z" fill="currentColor"></path></svg>
                </div>
                <div>
                    <div>Каталог</div>
                </div>

            </div>

            <div class="search-bar">
                <div class="category-btn">
                    <div>Везде</div>
                    <svg width="16" height="16" fill="none" xmlns="http://www.w3.org/2000/svg" class=""><path d="M11.8 6.6l-3 4a1 1 0 01-1.6 0l-3-4A1 1 0 015 5h6a1 1 0 01.8 1.6z" fill="currentColor"></path></svg>
                </div>
                <input type="text" id="search-input" placeholder="Искать на Озон">
                <div class="search-btn">
                    <svg width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" type="submit" aria-label="Поиск" class=""><path d="M9.996 17.991a7.996 7.996 0 117.995-7.995 1 1 0 11-2 0 5.996 5.996 0 10-1.756 4.24l.707-.708 7.115 7.115a1 1 0 11-1.414 1.414l-5.745-5.745a7.967 7.967 0 01-4.902 1.68z" fill="currentColor"></path></svg>
                </div>
            </div>

            <div class="customer-services">
                <a href="/login" class="customer-services__elem">
                    <svg width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="b2x8"><path fill-rule="evenodd" clip-rule="evenodd" d="M13.01 20.305c-5.105.586-9.51-3.37-9.51-8.442A8.507 8.507 0 018.788 3.99a5.453 5.453 0 005.288 4.123h.299a1 1 0 100-2h-.3a3.45 3.45 0 01-3.45-3.45v-1.29l-1.25.32A10.503 10.503 0 001.5 11.864c0 6.264 5.443 11.151 11.737 10.43 4.675-.537 8.503-4.26 9.156-8.913.505-3.6-.819-7.072-3.448-9.392a10.473 10.473 0 00-4.32-2.294 1 1 0 10-.5 1.937 8.473 8.473 0 013.496 1.857c2.132 1.88 3.202 4.687 2.791 7.613-.526 3.75-3.632 6.771-7.403 7.204zm-6.72-8.497c.19.181.45.292.71.292.26 0 .52-.11.71-.292.18-.191.29-.453.29-.714 0-.262-.11-.524-.29-.715a1.042 1.042 0 00-1.42 0c-.18.191-.29.453-.29.715 0 .261.11.523.29.714zm10 .002c.19.18.45.29.71.29.26 0 .52-.11.71-.29.09-.1.16-.21.21-.331.05-.12.08-.25.08-.38s-.03-.261-.08-.381-.12-.23-.21-.33c-.1-.091-.2-.161-.33-.211-.37-.16-.81-.06-1.09.21-.09.1-.16.21-.21.33-.05.12-.08.251-.08.381s.03.26.08.381c.05.12.12.23.21.33zm-7.497 4.995a1 1 0 111.414-1.415c.99.99 2.596.99 3.586 0a1 1 0 011.414 1.415 4.537 4.537 0 01-6.414 0z" fill="currentColor"></path></svg>
                </a>

                <div class="customer-services__elem">
                    <svg width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="d1b3"><path d="M12.486 1.626L20.97 6.34A2 2 0 0122 8.088v7.824a2 2 0 01-1.029 1.748l-8.485 4.714a1 1 0 01-.972 0L3.03 17.66A2 2 0 012 15.912V8.088A2 2 0 013.029 6.34l8.485-4.714a1 1 0 01.972 0zM4 9.176v6.736l7 3.888v-6.711L4 9.176zm16 .022l-7 3.887V19.8l7-3.888V9.198zm-3.5-3.055L9.566 9.996l2.431 1.36 6.943-3.857-2.44-1.356zM12 3.644L5.079 7.488l2.433 1.36 6.929-3.849L12 3.644z" fill="currentColor"></path></svg>
                </div>

                <div class="customer-services__elem">
                    <svg width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="c6j1"><path fill-rule="evenodd" clip-rule="evenodd" d="M23 8.5C23 4.8 20.861 2 17.282 2 15.115 2 13.136 3.069 12 4.742 10.864 3.069 8.884 2 6.718 2 3.138 2 1 4.8 1 8.5c0 3.817 2.886 7.664 10.4 13.3l.6.45.6-.45C20.115 16.164 23 12.316 23 8.5zM17.282 4C19.622 4 21 5.805 21 8.5c0 2.93-2.398 6.211-9 11.246C5.398 14.712 3 11.43 3 8.5 3 5.805 4.379 4 6.718 4 9.03 4 11 5.743 11 8h2c0-2.257 1.969-4 4.282-4z" fill="currentColor"></path></svg>
                </div>

                <div class="customer-services__elem">
                    <svg width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="c6h2"><path fill-rule="evenodd" clip-rule="evenodd" d="M10 2a4 4 0 00-4 4v2H.867L2.18 18.496A4 4 0 006.15 22h11.703a4 4 0 003.969-3.504L23.133 8H18V6a4 4 0 00-4-4h-4zm3 7a1 1 0 00-1-1H8V6a2 2 0 012-2h4a2 2 0 012 2v4h4.867l-1.03 8.248A2 2 0 0117.851 20H6.148a2 2 0 01-1.984-1.752L3.133 10H12a1 1 0 001-1z" fill="currentColor"></path></svg>
                </div>
            </div>
        </div>
    </div>

    <div class="mobile-header-type hide">
        <div class="burger-menu">
            <span></span>
        </div>
        <div class="logo-mobile"></div>
        <div style="display: flex;">
            <div class="search-btn mobile-header-type__elem">
                <img src="https://mstatic.wbstatic.net/sitemobile/3.5.5/assets/2c7cc256.svg" alt="">
            </div>

            <div class="profile-btn mobile-header-type__elem">
                <img src="data:image/svg+xml,<svg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' fill='none'> <g opacity='0.75'> <path fill-rule='evenodd' clip-rule='evenodd' d='M12 21C16.9706 21 21 16.9706 21 12C21 7.02944 16.9706 3 12 3C7.02944 3 3 7.02944 3 12C3 16.9706 7.02944 21 12 21ZM12 23C18.0751 23 23 18.0751 23 12C23 5.92487 18.0751 1 12 1C5.92487 1 1 5.92487 1 12C1 18.0751 5.92487 23 12 23Z' fill='white'/> <path d='M7.51211 17.3721C7.03486 16.973 6.6116 16.5114 6.25488 16C7.51983 14.1865 9.62145 13 12.0002 13C14.3789 13 16.4806 14.1865 17.7455 16C17.3888 16.5114 16.9655 16.973 16.4883 17.3721C15.5853 15.8332 13.9134 14.8 12.0002 14.8C10.087 14.8 8.41512 15.8332 7.51211 17.3721Z' fill='white'/> <path fill-rule='evenodd' clip-rule='evenodd' d='M12 10.2C12.9389 10.2 13.7 9.43888 13.7 8.5C13.7 7.56112 12.9389 6.8 12 6.8C11.0611 6.8 10.3 7.56112 10.3 8.5C10.3 9.43888 11.0611 10.2 12 10.2ZM12 12C13.933 12 15.5 10.433 15.5 8.5C15.5 6.567 13.933 5 12 5C10.067 5 8.5 6.567 8.5 8.5C8.5 10.433 10.067 12 12 12Z' fill='white'/> </g> </svg>" alt="">
            </div>

            <div class="cart-btn mobile-header-type__elem" style="margin-right: 15px;">
                <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' fill='none'%3E %3Cg opacity='0.75'%3E %3Cpath fill-rule='evenodd' clip-rule='evenodd' d='M1 3C1 2.44772 1.44772 2 2 2H4.68722C5.16073 2 5.56934 2.3321 5.66611 2.79562L6.33513 6H19C20.1046 6 21 6.89543 21 8V12.325C21 13.2718 20.3362 14.0888 19.4094 14.2827L8.54269 16.5555C7.46144 16.7816 6.40161 16.0884 6.17556 15.0071L3.87444 4H2C1.44772 4 1 3.55228 1 3ZM7.92594 13.6194C8.03884 14.1601 8.56879 14.5069 9.1095 14.3939L18.2047 12.4922C18.668 12.3953 19 11.9867 19 11.5133V9C19 8.44772 18.5523 8 18 8H6.7527L7.92594 13.6194Z' fill='white'/%3E %3Ccircle cx='7.36359' cy='20.1815' r='1.81818' fill='white'/%3E %3Ccircle cx='16.4544' cy='20.1815' r='1.81818' fill='white'/%3E %3C/g%3E %3C/svg%3E" alt="">
            </div>
        </div>

    </div>
</header>

<div class="main-menu hide">
    <div class="main-menu__categories">
        <div class="main-menu-category">
            <div class="category-icon">
                <!-- Here svg goes -->
            </div>
            <div class="category-name">Фиалки</div>
        </div>

        <div class="main-menu-category">
            <div class="category-icon">
                <!-- Here svg goes -->
            </div>
            <div class="category-name">Стрептокарпусы</div>
        </div>
    </div>

    <div class="main-menu__subcategories">
        <div class="main-menu-subcategories-groups">

            <div class="main-menu-group">
                <div class="main-menu-group__header"><a href="">Фиалки отечественной селекции</a></div>
                <div class="main-menu-group__items">
                    <div class="main-menu-group-item"><a href="">Селекция Фиалковода</a></div>
                    <div class="main-menu-group-item"><a href="">Селекция Коршуновой</a></div>
                </div>
            </div>

            <div class="main-menu-group">
                <div class="main-menu-group__header"><a href="">Фиалки украинской селекции</a></div>
                <div class="main-menu-group__items">
                    <div class="main-menu-group-item"><a href="">Селекция Денисенко</a></div>
                    <div class="main-menu-group-item"><a href="">Селекция Лебецкой</a></div>
                </div>
            </div>

        </div>
        <div class="image-preview-cont">
            <img src="http://images.wbstatic.net/poster/ru/menuright1/c352x428/456666.jpg" alt="">
        </div>
    </div>
</div>

<div class="main-menu-mobile hide">
    <div class="search-bar-mobile">
        <div class="search-bar-mobile__icon">
            <img src="https://mstatic.wbstatic.net/sitemobile/3.5.5/assets/2c7cc256.svg" alt="">
        </div>
        <input type="text" name="search-input" />
    </div>

    <div class="main-menu-mobile__categories">
        <div class="categories-wrapper">
            <a href="" class="category-mobile-category">
                <div class="category-mobile-category__content">Фиалки украинской селекции</div>
            </a>
            <a href="" class="category-mobile-category">
                <div class="category-mobile-category__content">Фиалки украинской селекции</div>
            </a>
            <a href="" class="category-mobile-category">
                <div class="category-mobile-category__content">Фиалки украинской селекции</div>
            </a>
        </div>
    </div>
</div>

<main class="page-component">
    <h1>Добавить товар</h1>

    <form:form method="POST" action="/store/${acceptUrl}" modelAttribute="good" enctype="multipart/form-data" id="control-form" >
        <div class="good-params">
            <div class="params-group">
                <h3>Информация о товаре</h3>

                <div class="good-param">
                    <spring:bind path="name">
                        <form:label path="name" cssClass="good-param__name">Название</form:label>
                        <form:input type="text" path="name" class="good-param__input no-border-input" id="good-name" />
                    </spring:bind>
                </div>

                <div class="good-param">
                    <label class="good-param__name">Категория</label>

                    <div class="select">
                        <select class="custom-select">
                            <option value="">Выберите категорию</option>
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.name}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <input type="text" name="goodCategory" class="hide" id="good-category-param">
                </div>

                <div class="good-param">
                    <input name="id" value="${good.id}" hidden>
                    <spring:bind path="price">
                        <form:label path="price" cssClass="good-param__name">Цена</form:label>
                        <form:input path="price" cssClass="good-param__input no-border-input" id="good-price" min="0" />
                    </spring:bind>

                </div>

                <div class="good-param">
                    <spring:bind path="priceBeforeDiscount">
                        <form:label path="priceBeforeDiscount" cssClass="good-param__name">Цена до скидки</form:label>
                        <form:input path="priceBeforeDiscount" cssClass="good-param__input no-border-input" id="good-price-discount" min="0" />
                    </spring:bind>
                </div>

                <div class="good-param">
                    <spring:bind path="description">
                        <form:label path="description" cssClass="good-param__name">Описание</form:label>
                        <form:textarea path="description"
                                       id="good-description"
                                       style="resize: none; font-size: 14px; width: 250px; height: 100px;"
                                       maxlength="200" />
                    </spring:bind>
                </div>

                <div class="trigger-btn-large add-good-btn" id="variation-add">Добавить вариацию</div>
            </div>

            <div class="params-group">
                <h3>Медиа</h3>
                <div>Требования к изображению</div>
                <p>
                    1. Формат JPEG или PNG
                </p>
                <p>
                    2. Разрешение: от 700 до 1600 пикселей по любой стороне
                </p>
                <p>
                    3. Запрещены: логотипы, водяные знаки, цены, надписи
                </p>

                <input type="file" name="images" class="file-area" id="good-images" multiple />

                <div style="margin-top: 5px;">
                    Для сброса загруженных файлов перезагрузите страницу!
                </div>

                <div class="good-images">
                    <c:forEach items="${good.goodImages}" var="goodImage">
                        <div class="good-image-cont" data="${goodImage.id}">
                            <div class="main-menu-close-btn delete-good-image hide"></div>
                            <div class="good-image-overlay hide"></div>
                            <img class="good-image" src="${goodImage.imageSrc}" alt="${good.name}">
                        </div>
                    </c:forEach>
                </div>

            </div>

            <div class="params-group hide">
                <h3>Вариации</h3>
                <table class="orders-list gen-table variations" cellspacing="0" cellpadding="0" style="min-width: 280px;">
                    <tr style="background-color: #e8e8e8">
                        <td style="width: 10%; font-size: 17px;">Название вариации</td>
                        <td style="width: 15%; font-size: 17px;">Цена</td>
                    </tr>

<%--                    <tr>--%>
<%--                        <td>Имя вариации</td>--%>
<%--                        <td>180</td>--%>
<%--                    </tr>--%>
                </table>
            </div>
        </div>

        <div class="good-options-ctrl">
            <c:forEach var="goodOption" items="${good.goodOptions}" varStatus="status">
                <input type="text" name="goodOptions[${status.index}].name" value="${goodOption.name}" class="hide">
                <input type="text" name="goodOptions[${status.index}].price" value="${goodOption.price}" class="hide">
            </c:forEach>
        </div>

        <button type="submit" class="good-btn-submit trigger-btn-large createStoreGood">
                ${ acceptUrl.equals("createStoreGood") ? "Создать" : "Сохранить" }
        </button>
    </form:form>

    <div style="height: 200px;"></div>
</main>

<div class="page-overlay hide">
    <div class="main-menu-close-btn" id="page-overlay-close"></div>
</div>

<div class="variation-popup hide">
    <h3 style="margin-top: 0;">Добавление вариации товара</h3>

    <div class="good-param">
        <label for="good-option-name" class="good-param__name">Название</label>
        <input type="text" class="good-param__input no-border-input" id="good-option-name" />
    </div>

    <div class="good-param">
        <label for="good-option-price" class="good-param__name">Цена</label>
        <input type="number" class="good-param__input no-border-input" id="good-option-price" min="0" />
    </div>

<%--    <input type="file" class="file-area" id="good-option-image" style="margin-top: 0;" />--%>

    <div class="trigger-btn-large add-good-btn" id="good-option-add" style="margin-top: 25px;">Добавить</div>
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

<script>
    document.querySelector('.custom-select').selectedIndex = ${good.goodCategories.get(0).id};
</script>

<script src="/resources/js/visual.js"></script>
<script src="/resources/js/store-scripts.js"></script>
</body>
</html>
