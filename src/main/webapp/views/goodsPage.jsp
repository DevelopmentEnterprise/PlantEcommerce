<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:useBean id="goodsToShow" scope="request" type="java.util.List<com.WebMall.model.Good>"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="/resources/css/main.css" />
    <link rel="stylesheet" href="/resources/css/category-page.css"></head>
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

            <div class="top-services-bar">
                <a href="" class="top-services-bar__elem btn-underline">
                    <svg width="22" height="22" viewBox="0 0 22 22" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M11.8784 0.525387L15.1434 6.58939L21.1524 7.51339C21.3307 7.54091 21.4982 7.61614 21.6371 7.73111C21.7761 7.84607 21.8814 7.99648 21.9419 8.16642C22.0024 8.33635 22.0158 8.51947 21.9806 8.69639C21.9455 8.87331 21.8632 9.03744 21.7424 9.17139L17.5554 13.8234L18.4894 20.3614C18.5153 20.5441 18.4901 20.7305 18.4166 20.8998C18.3432 21.0691 18.2243 21.2147 18.0731 21.3206C17.922 21.4265 17.7445 21.4885 17.5603 21.4998C17.376 21.5111 17.1923 21.4711 17.0294 21.3844L10.9994 18.1374L4.9694 21.3844C4.80657 21.4719 4.62263 21.5125 4.43809 21.5016C4.25356 21.4906 4.07568 21.4287 3.92428 21.3226C3.77289 21.2165 3.65393 21.0705 3.58068 20.9008C3.50742 20.731 3.48275 20.5443 3.5094 20.3614L4.4434 13.8234L0.256402 9.17139C0.135941 9.03732 0.0539143 8.87321 0.0189812 8.69639C-0.0159519 8.51957 -0.00249498 8.33659 0.0579312 8.16679C0.118357 7.99698 0.223511 7.84663 0.36229 7.73163C0.50107 7.61662 0.668326 7.54122 0.846402 7.51339L6.8544 6.58939L10.1204 0.525387C10.2058 0.366577 10.3326 0.233866 10.4874 0.141358C10.6421 0.0488496 10.8191 0 10.9994 0C11.1797 0 11.3567 0.0488496 11.5114 0.141358C11.6662 0.233866 11.793 0.366577 11.8784 0.525387V0.525387ZM8.1434 8.41539L2.9804 9.20939L6.5554 13.1804L5.7694 18.6824L10.9994 15.8664L16.2294 18.6824L15.4434 13.1804L19.0184 9.20939L13.8554 8.41439L10.9994 3.11139L8.1434 8.41439V8.41539Z" fill="#001A34"/>
                    </svg>
                    <div>Top fashion</div>
                </a>
                <a href="" class="top-services-bar__elem btn-underline">
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" clip-rule="evenodd" d="M11.1317 2.50386C11.3098 2.19229 11.6411 2 12 2C12.3588 2 12.6902 2.19229 12.8682 2.50386L16.2186 8.36711L21.2928 3.29289C21.5967 2.98904 22.0607 2.91328 22.4454 3.1047C22.8301 3.29613 23.0496 3.71194 22.9904 4.13757L21.3238 16.1376C21.2551 16.6319 20.8324 17 20.3333 17L10.5 17C9.94767 17 9.49995 16.5523 9.49995 16C9.49995 15.4477 9.94767 15 10.4999 15L19.4626 15L20.5994 6.81475L16.7071 10.7071C16.4867 10.9275 16.1761 11.0325 15.8672 10.9912C15.5583 10.9498 15.2863 10.7667 15.1317 10.4961L12 5.01556L8.86819 10.4961C8.71356 10.7667 8.4416 10.9498 8.13268 10.9912C7.82377 11.0325 7.51323 10.9275 7.29284 10.7071L3.40049 6.81475L4.53733 15H6.49995C7.05224 15 7.49995 15.4477 7.49995 16C7.49995 16.5523 7.05224 17 6.49995 17H3.66662C3.16749 17 2.74479 16.6319 2.67613 16.1376L1.00946 4.13757C0.950342 3.71194 1.16976 3.29613 1.55448 3.1047C1.9392 2.91328 2.4032 2.98904 2.70706 3.29289L7.78128 8.36711L11.1317 2.50386ZM3.99999 20C3.44771 20 2.99999 20.4477 2.99999 21C2.99999 21.5523 3.44771 22 3.99999 22H20C20.5523 22 21 21.5523 21 21C21 20.4477 20.5523 20 20 20H3.99999Z" fill="#001A34"/>
                    </svg>
                    <div>Premium</div>
                </a>
                <a href="" class="top-services-bar__elem btn-underline">
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" clip-rule="evenodd" d="M1 7C1 5.34315 2.34315 4 4 4H20C21.6569 4 23 5.34315 23 7V17C23 18.6569 21.6569 20 20 20H4C2.34315 20 1 18.6569 1 17V7ZM3 7C3 6.44772 3.44772 6 4 6H20C20.5523 6 21 6.44772 21 7V8H3V7ZM3 11V17C3 17.5523 3.44772 18 4 18H20C20.5523 18 21 17.5523 21 17V11H3Z" fill="#001A34"/>
                    </svg>
                    <div>Ozon Card</div>
                </a>
                <a href="" class="top-services-bar__elem btn-underline">
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" clip-rule="evenodd" d="M4 6H16C16.5523 6 17 6.44772 17 7V17C17 17.5523 16.5523 18 16 18H4C3.44772 18 3 17.5523 3 17V7C3 6.44772 3.44772 6 4 6ZM1 7C1 5.34315 2.34315 4 4 4H16C17.6569 4 19 5.34315 19 7V7.3706L21.6646 6.44972C22.1866 6.26932 22.756 6.54623 22.9364 7.06822C22.9727 7.1733 22.9912 7.28369 22.9912 7.39486V16.6054C22.9912 17.1577 22.5435 17.6054 21.9912 17.6054C21.8841 17.6054 21.7777 17.5882 21.676 17.5544L19 16.6657V17C19 18.6569 17.6569 20 16 20H4C2.34315 20 1 18.6569 1 17V7ZM19.0995 14.5925V9.4525L20.9905 8.7995V15.2195L19.0995 14.5925ZM10.2397 8.84662L11.1303 10.5387L12.769 10.7966C12.9176 10.82 13.0196 10.9625 12.9968 11.1148C12.9887 11.1686 12.9655 11.2188 12.9299 11.2592L11.7881 12.5571L12.0428 14.3814C12.0641 14.5339 11.9607 14.6753 11.8118 14.6971C11.7542 14.7056 11.6955 14.6949 11.6443 14.6667L9.99998 13.7609L8.35567 14.6667C8.22321 14.7396 8.05809 14.6888 7.98687 14.553C7.95932 14.5006 7.94891 14.4404 7.95715 14.3814L8.21189 12.5571L7.07005 11.2592C6.96935 11.1448 6.97828 10.9683 7.08999 10.8652C7.12946 10.8287 7.17846 10.8049 7.23098 10.7966L8.86966 10.5387L9.7603 8.84662C9.83168 8.71099 9.99687 8.66034 10.1292 8.73348C10.176 8.75934 10.2144 8.79867 10.2397 8.84662Z" fill="#001A34"/>
                    </svg>
                    <div>LIVE</div>
                </a>
                <a href="" class="top-services-bar__elem btn-underline">
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" clip-rule="evenodd" d="M15 19C15.552 19 16 18.552 16 18C16 17.448 15.552 17 15 17L4 17C3.449 17 3 16.551 3 16L3 8C3 7.449 3.449 7 4 7L20 7C20.551 7 21 7.449 21 8V16C21 16.551 20.551 17 20 17H19C18.448 17 18 17.448 18 18C18 18.552 18.448 19 19 19H20C21.654 19 23 17.654 23 16V8C23 6.346 21.654 5 20 5H4C2.346 5 1 6.346 1 8V16C1 17.654 2.346 19 4 19L15 19ZM16.0801 8.6204C16.1301 8.4904 16.2001 8.3904 16.2901 8.2904C16.4801 8.1104 16.7401 8.0004 17.0001 8.0004C17.1301 8.0004 17.2601 8.0304 17.3801 8.0804C17.5101 8.1304 17.6101 8.2004 17.7101 8.2904C17.8901 8.4804 18.0001 8.7404 18.0001 9.0004C18.0001 9.2604 17.8901 9.5204 17.7101 9.7104C17.5201 9.8904 17.2601 10.0004 17.0001 10.0004C16.7401 10.0004 16.4801 9.8904 16.2901 9.7104C16.2001 9.6104 16.1301 9.5104 16.0801 9.3804C15.9801 9.1404 15.9801 8.8604 16.0801 8.6204ZM16.2901 11.29C16.2001 11.39 16.1301 11.49 16.0801 11.62C15.9801 11.86 15.9801 12.14 16.0801 12.38C16.1301 12.51 16.2001 12.61 16.2901 12.71C16.4801 12.89 16.7401 13 17.0001 13C17.2601 13 17.5201 12.89 17.7101 12.71C17.8901 12.52 18.0001 12.26 18.0001 12C18.0001 11.74 17.8901 11.48 17.7101 11.29C17.6101 11.2 17.5101 11.13 17.3801 11.08C17.2601 11.03 17.1301 11 17.0001 11C16.7401 11 16.4801 11.11 16.2901 11.29ZM16.2901 14.2896C16.2001 14.3896 16.1301 14.4896 16.0801 14.6196C15.9801 14.8596 15.9801 15.1396 16.0801 15.3796C16.1301 15.5096 16.2001 15.6096 16.2901 15.7096C16.4801 15.8896 16.7401 15.9996 17.0001 15.9996C17.2601 15.9996 17.5201 15.8896 17.7101 15.7096C17.8901 15.5196 18.0001 15.2596 18.0001 14.9996C18.0001 14.7396 17.8901 14.4796 17.7101 14.2896C17.6101 14.1996 17.5101 14.1296 17.3801 14.0796C17.2601 14.0296 17.1301 13.9996 17.0001 13.9996C16.7401 13.9996 16.4801 14.1096 16.2901 14.2896ZM5 10C5 9.448 5.448 9 6 9L11 9C11.552 9 12 9.448 12 10C12 10.552 11.552 11 11 11L6 11C5.448 11 5 10.552 5 10ZM6 13C5.448 13 5 13.448 5 14C5 14.552 5.448 15 6 15H9C9.552 15 10 14.552 10 14C10 13.448 9.552 13 9 13L6 13Z" fill="#001A34"/>
                    </svg>
                    <div>Акции</div>
                </a>
                <a href="" class="top-services-bar__elem btn-underline">
                    <svg width="25" height="24" viewBox="0 0 25 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" clip-rule="evenodd" d="M10.7445 21.6552C10.9345 21.8742 11.2101 22 11.5 22H13.1812C14.3813 22 15.4659 21.2848 15.9386 20.1818L18.1594 15H20.5C22.1569 15 23.5 13.6569 23.5 12C23.5 10.3431 22.1569 9 20.5 9H18.1594L15.9386 3.81824C15.4659 2.71519 14.3813 2 13.1812 2H11.5C11.2101 2 10.9345 2.1258 10.7445 2.3448C10.5546 2.5638 10.4691 2.85444 10.5101 3.14142L11.347 9H5.80278L3.0547 7.16795C2.74784 6.96338 2.3533 6.94431 2.02814 7.11833C1.70298 7.29234 1.5 7.6312 1.5 8V16C1.5 16.3688 1.70298 16.7077 2.02814 16.8817C2.3533 17.0557 2.74784 17.0366 3.0547 16.8321L6.0547 14.8321C6.51423 14.5257 6.6384 13.9048 6.33205 13.4453C6.0257 12.9858 5.40483 12.8616 4.9453 13.1679L3.5 14.1315V9.86852L4.9453 10.8321C5.10957 10.9416 5.30258 11 5.5 11H12.5C12.7899 11 13.0655 10.8742 13.2555 10.6552C13.4454 10.4362 13.5309 10.1456 13.4899 9.85858L12.653 4H13.1812C13.5812 4 13.9428 4.2384 14.1004 4.60608L16.5809 10.3939C16.7384 10.7616 17.1 11 17.5 11H20.5C21.0523 11 21.5 11.4477 21.5 12C21.5 12.5523 21.0523 13 20.5 13H17.5C17.1 13 16.7384 13.2384 16.5809 13.6061L14.1004 19.3939C13.9428 19.7616 13.5812 20 13.1812 20H12.653L13.4899 14.1414C13.5309 13.8544 13.4454 13.5638 13.2555 13.3448C13.0655 13.1258 12.7899 13 12.5 13H9.5C8.94772 13 8.5 13.4477 8.5 14C8.5 14.5523 8.94772 15 9.5 15H11.347L10.5101 20.8586C10.4691 21.1456 10.5546 21.4362 10.7445 21.6552Z" fill="#001A34"/>
                    </svg>
                    <div>Ozon Travel</div>
                </a>
                <a href="" class="top-services-bar__elem btn-underline">
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" clip-rule="evenodd" d="M5.17157 5.17157C6.73367 3.60948 9.26633 3.60948 10.8284 5.17157C11.219 5.5621 11.8521 5.5621 12.2426 5.17157C12.6332 4.78105 12.6332 4.14788 12.2426 3.75736C9.89949 1.41421 6.10051 1.41421 3.75736 3.75736C1.41421 6.1005 1.41421 9.89949 3.75736 12.2426L3.76016 12.2454L12.7957 21.2099L13.5028 21.9114L14.2071 21.2071L21.2071 14.2071L21.9142 13.5L21.2071 12.7929L14.7071 6.29289C14.3166 5.90237 13.6834 5.90237 13.2929 6.29289C12.9024 6.68342 12.9024 7.31658 13.2929 7.70711L19.0858 13.5L13.4972 19.0886L5.17157 10.8284L5.17023 10.8271C3.60948 9.26488 3.60992 6.73322 5.17157 5.17157ZM8 9.5C8.82843 9.5 9.5 8.82843 9.5 8C9.5 7.17157 8.82843 6.5 8 6.5C7.17157 6.5 6.5 7.17157 6.5 8C6.5 8.82843 7.17157 9.5 8 9.5Z" fill="#001A34"/>
                    </svg>
                    <div>Бренды</div>
                </a>
                <a href="" class="top-services-bar__elem btn-underline">
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" clip-rule="evenodd" d="M18.4604 2.20518C18.2868 2.07226 18.0734 1.9991 17.8523 1.9991H6.17134L6.03987 2.00778C5.82312 2.03652 5.62041 2.13577 5.46412 2.2921L2.29612 5.4611L2.21656 5.55086C2.1182 5.67624 2.05121 5.82336 2.02121 5.97986L2.00388 6.13136L1.99988 6.3636C2.00546 6.91319 2.11602 7.54987 2.41274 8.18783C2.66529 8.73082 3.02231 9.18948 3.48203 9.54364L3.48254 20.9741L3.48927 21.0907C3.54703 21.5881 3.9697 21.9741 4.48254 21.9741H9.48754L9.60416 21.9674C10.1015 21.9096 10.4875 21.4869 10.4875 20.9741V15.6614L10.4938 15.5055C10.5641 14.6343 11.2181 13.9748 11.9795 13.9748H12.0975L12.2265 13.981C12.9934 14.0548 13.6215 14.7799 13.6215 15.6948V16.6597L13.6283 16.7763C13.686 17.2736 14.1087 17.6597 14.6215 17.6597C15.1738 17.6597 15.6215 17.2119 15.6215 16.6597V15.6948L15.6167 15.4987C15.5204 13.5499 13.9978 11.9748 12.0975 11.9748H11.9795L11.7925 11.98C9.93561 12.0827 8.48754 13.7069 8.48754 15.6614L8.48704 19.974H5.48204L5.48214 10.3214C5.71005 10.3496 5.9488 10.3641 6.19834 10.3641L6.44527 10.3582L6.70403 10.3388C7.63609 10.2432 8.38383 9.88002 8.93799 9.31706L9.04513 9.20307L9.19754 9.34777C9.877 9.95563 10.8035 10.3123 11.9681 10.33C13.1269 10.3479 14.0645 9.87742 14.7721 9.09356L14.833 9.022L14.8669 9.06107L15.0146 9.21391C15.7226 9.90697 16.6891 10.3141 17.9023 10.3141L18.0917 10.3081L18.091 19.974L14.7979 19.9741L14.6813 19.9808C14.184 20.0386 13.7979 20.4613 13.7979 20.9741C13.7979 21.5264 14.2457 21.9741 14.7979 21.9741H20.0919L20.0926 9.5879C20.5704 9.21469 20.9591 8.71197 21.2598 8.11365C21.6344 7.3684 21.8074 6.64212 21.8628 6.1206C21.8946 5.82024 21.789 5.52157 21.5754 5.30799L18.5594 2.29199L18.4604 2.20518ZM6.58504 3.999H17.438L19.786 6.347L19.7373 6.53533C19.6683 6.77074 19.5804 7.00149 19.4728 7.21555C19.3028 7.55387 19.1053 7.81636 18.8695 7.99893C18.6977 8.03795 18.5427 8.12122 18.4188 8.23821C18.2611 8.28862 18.0895 8.3141 17.9023 8.3141C17.0866 8.3141 16.5647 8.03167 16.2055 7.54402C16.016 7.2868 15.9069 7.02599 15.8617 6.8579L15.8157 6.66143C15.5512 5.77284 14.2853 5.71261 13.9377 6.57207L13.869 6.77954C13.8002 6.97635 13.6619 7.2669 13.4518 7.55161C13.0737 8.0642 12.6117 8.33969 11.9988 8.33022C11.1236 8.31691 10.6042 8.03634 10.2806 7.57291C10.1113 7.33034 10.0235 7.08366 9.99295 6.92919L9.96198 6.7295C9.73807 5.68421 8.19019 5.71122 8.00481 6.76636L7.98107 6.96724C7.95559 7.13239 7.87828 7.39038 7.72569 7.639C7.44267 8.10011 6.99218 8.3641 6.19834 8.3641C5.74447 8.3641 5.38118 8.3013 5.09168 8.18097C5.00256 8.11242 4.90156 8.05856 4.79163 8.02473C4.53846 7.85549 4.35728 7.62623 4.22619 7.34437L4.15695 7.17811L4.10159 7.00995C4.0691 6.89781 4.04526 6.78621 4.02886 6.67876L4.01504 6.57L6.58504 3.999Z" fill="#001A34"/>
                    </svg>
                    <div>Магазины</div>
                </a>
                <a href="" class="top-services-bar__elem btn-underline">
                    <svg width="22" height="22" viewBox="0 0 22 22" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" clip-rule="evenodd" d="M7.41984 2.21408C7.14408 2.07504 6.83639 2 6.51975 2C3.86529 2 2.58595 5.14156 4.28369 7H0V13H2V22H20V13H22V7H17.7163C19.4141 5.14156 18.1347 2 15.4802 2C15.1636 2 14.8559 2.07504 14.5802 2.21408C13.9241 0.901412 12.5673 0 11 0C9.43269 0 8.07593 0.901411 7.41984 2.21408ZM9.00235 3.90216L11 6.39922L12.9976 3.90216C12.9466 2.84303 12.0718 2 11 2C9.92824 2 9.05337 2.84303 9.00235 3.90216ZM8.91938 7L6.51938 4C5.58581 4.00032 5.16329 5.1677 5.88058 5.76544L7.36205 7H8.91938ZM13.0806 7L15.4806 4C16.4142 4.00031 16.8367 5.1677 16.1194 5.76544L14.6379 7H13.0806ZM18 11H20V9H2V11H15C15.5523 11 16 11.4477 16 12C16 12.5523 15.5523 13 15 13H4V20H18V11ZM6 14C6.55228 14 7 14.4477 7 15V18C7 18.5523 6.55228 19 6 19C5.44772 19 5 18.5523 5 18V15C5 14.4477 5.44772 14 6 14Z" fill="#001A34"/>
                    </svg>
                    <div>Сертификаты</div>
                </a>
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
        <div class="bread-crumbs content-section">
            <a href="/" class="bread-crumbs__item">Главная</a>
            <a href="" class="bread-crumbs__item">Детям</a>
            <a href="" class="bread-crumbs__item">Детская одежда</a>
        </div>

        <div class="catalog-title-wrap">
            <h1>${categoryUI}</h1>
            <div class="goods-category-count">1603 товара</div>
        </div>

        <div class="catalog content-section">
            <!-- <div class="catalog-sidebar">
                Здесь размещать фильтры
            </div> -->

            <div class="catalog-content">
                <c:if test="${goodsToShow.size() != 0}">
                    <div class="catalog-sorter">
                        <span style="color: #a7a7a7; margin-right: 20px;">Сортировать по:</span>
                        <a href="/goods?categoryName=${categoryName}&sortBy=popularity" class="sort-item ${pageContext.request.getParameter("sortBy").equals("popularity") ? "sort-item-active" : ""}">Популярности</a>
                        <a href="/goods?categoryName=${categoryName}&sortBy=name" class="sort-item ${pageContext.request.getParameter("sortBy").equals("name") ? "sort-item-active" : ""}">Названию</a>
                        <a href="/goods?categoryName=${categoryName}&sortBy=rating" class="sort-item ${pageContext.request.getParameter("sortBy").equals("rating") ? "sort-item-active" : ""}">Рейтингу</a>
                        <a href="/goods?categoryName=${categoryName}&sortBy=price" class="sort-item ${pageContext.request.getParameter("sortBy").equals("price") ? "sort-item-active" : ""}">Цене</a>
                        <a href="/goods?categoryName=${categoryName}&sortBy=priceDiscount" class="sort-item ${pageContext.request.getParameter("sortBy").equals("priceDiscount") ? "sort-item-active" : ""}">Скидке</a>
                    </div>
                </c:if>

                <div class="goods-list content-section">
                    <c:if test="${goodsToShow.size() == 0}">
                        <h2 style="text-align: center">Ничего не найдено по вашему запросу</h2>
                    </c:if>

                    <c:forEach var="good" items="${goodsToShow}">
                        <div class="good-card">
                            <div class="good-card__to-bookmarks"></div>

                            <a href="/goods/${good.id}" class="good-card__img">
                                <img src="${good.goodImages.get(0).imageSrc}" alt="">
                            </a>

                            <div class="good-card__price">
                                <div class="good-price">${good.price} <span>руб.</span></div>
                                <div class="good-discount-price">
                                        ${ good.priceBeforeDiscount } <span>руб.</span>
                                </div>
                                <div class="discount-percent">-${ Math.round(100 * (1 - good.price / good.priceBeforeDiscount)) }%</div>
                            </div>

                            <div class="good-card__name">
                                    ${good.name}
                            </div>

                            <div class="good-card__description">
                                    ${good.description}
                            </div>

                            <div class="good-card__rating">

                            </div>

                            <div class="good-card-hover hide">
                                <div class="add-to-cart-btn" data="${good.id}">В корзину</div>
                            </div>
                        </div>
                    </c:forEach>

                    <script>
                        const goodCards = document.querySelectorAll('.good-card');
                        let goodRatings = [];

                        <%for(int i = 0; i < goodsToShow.size(); i++) {
                              int rating = Math.round(goodsToShow.get(i).getRating());%>
                        goodRatings.push(<%= rating%>);
                        <%}%>

                        for (let i = 0; i < goodCards.length; i++){
                            const ratingBlock = goodCards[i].children[5];
                            let rating = goodRatings[i];

                            for (let j = 0; j < rating; j++) {
                                const starBackground = document.createElement('div');
                                const starImgFilled = document.createElement('img');
                                starBackground.classList.add('star-cont');
                                starImgFilled.setAttribute('src', "${contextPath}/resources/static/star-fill.svg");
                                starBackground.appendChild(starImgFilled);

                                ratingBlock.appendChild(starBackground);
                            }

                            for (let j = rating; j < 5; j++){
                                const starBackgroundTrans = document.createElement('div');
                                const starImgBorder = document.createElement('img');
                                starBackgroundTrans.classList.add('star-cont');
                                starImgBorder.setAttribute('src', "${contextPath}/resources/static/star-transparent.svg");
                                starBackgroundTrans.appendChild(starImgBorder);

                                ratingBlock.appendChild(starBackgroundTrans);
                            }
                        }
                    </script>

                </div>

                <div class="pager-bottom" style="margin-top: 80px;">
                    <c:forEach begin="1" end="${pagersCount}" varStatus="loop">
                        <div class="${loop.index == 1 ? "pagination-item-active" : "pagination-item"}" data="${loop.index}">
                                ${loop.index}
                        </div>
                    </c:forEach>
                    <div class="pagination-item-text">Следующая страница
                        <span style="margin-left: 10px;">></span>
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
                <a href="/store/myStore">Вход в личный кабинет</a>
            </div>

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

<!-- Connect js -->
<script src="/resources/js/visual.js"></script>
<script src="/resources/js/cart-scripts.js"></script>
</html>