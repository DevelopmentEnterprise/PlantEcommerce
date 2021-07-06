document.addEventListener("DOMContentLoaded", ()=>{
    document.querySelector(".menu-button").addEventListener("click", enableOverlayLayout);

    if (document.querySelector('#page-overlay-close') != null)
        document.querySelector('#page-overlay-close').addEventListener('click', disableOverlayLayout);

    const burgerMenu = document.querySelector('.burger-menu');

    if (burgerMenu != null){
        burgerMenu.addEventListener('click', ()=>{
            document.querySelector('.burger-menu').classList.toggle('burger-menu-active');
            document.querySelector('.main-menu-mobile').classList.toggle('hide');
            document.querySelectorAll('.search-btn')[1].classList.toggle('hide');
            document.body.classList.toggle('scroll-stop');
        });
    }

    document.querySelector('.main-menu-close-btn').addEventListener('click', ()=>{
        document.querySelector('.main-menu').classList.add('hide');
        document.querySelector('.page-overlay').classList.add('hide');
    });

    if (document.querySelector('.private-area-list') != null){
        document.querySelectorAll('.private-area-link').forEach(el => {
            el.addEventListener('click', (e)=>{
                let panelName = e.target.attributes.id.value.replace("-page-link", "");
                panelName = ".my-"+panelName;
                controlPrivateAreaPanelsEx(e.target, panelName);
            });
        });
    }

    if (document.querySelector('#variation-add') != null){
        document.querySelector('#variation-add').addEventListener('click', ()=>{
            document.querySelector('.variation-popup').classList.remove('hide');
        });
    }

    responsiveUI();
});

const responsiveUI = () => {
    //Check for smaller width and change UI
    const width = window.screen.width;
    if(width <= 600){
        const header = document.querySelector('header');
        const mobileHeader = document.querySelector('.mobile-header-type');
        header.classList.remove("page-component");
        mobileHeader.classList.toggle('hide');
    }
};

const enableOverlayLayout = () => {
    document.body.classList.add('scroll-stop');
    const menu = document.querySelector('.main-menu');
    const pageOverlay = document.querySelector('.page-overlay');

    menu.classList.remove('hide');
    pageOverlay.classList.remove('hide');
};

const disableOverlayLayout = () => {
    document.body.classList.remove('scroll-stop');
    const menu = document.querySelector('.main-menu');
    const pageOverlay = document.querySelector('.page-overlay');
    const variationPopup = document.querySelector('.variation-popup');

    menu.classList.add('hide');
    pageOverlay.classList.add('hide');

    if (variationPopup != null)
        variationPopup.classList.add('hide');
};

// Logic for private area pages
const controlPrivateAreaPanelsEx = (requestSender, panelToShowClass) => {
    document.querySelectorAll(".private-area-content").forEach(el => {
        if (!el.classList.contains("hide")) el.classList.add('hide');
    });

    document.querySelectorAll(".private-area-link").forEach(el => {
        el.classList.remove('list-item-active');
        el.classList.add('btn-underline');
    });

    document.querySelector(panelToShowClass).classList.remove('hide');
    requestSender.classList.add('list-item-active');
    requestSender.classList.remove('btn-underline');
};