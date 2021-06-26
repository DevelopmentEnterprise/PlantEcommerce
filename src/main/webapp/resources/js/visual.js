document.addEventListener("DOMContentLoaded", ()=>{
    document.querySelector(".menu-button").addEventListener("click", handleMenuBtnClicked);
    document.querySelector('.main-menu-close-btn').addEventListener('click', handleMenuBtnClicked);

    const burgerMenu = document.querySelector('.burger-menu');

    if (burgerMenu != null){
        burgerMenu.addEventListener('click', ()=>{
            document.querySelector('.burger-menu').classList.toggle('burger-menu-active');
            document.querySelector('.main-menu-mobile').classList.toggle('hide');
            document.querySelectorAll('.search-btn')[1].classList.toggle('hide');
            document.body.classList.toggle('scroll-stop');
        });
    }

    if(document.querySelector('.private-area-list') != null){
        document.querySelectorAll('.private-area-link').forEach(el => {
            el.addEventListener('click', (e)=>{
                let panelName = e.target.attributes.id.value.replace("-page-link", "");
                panelName = ".my-"+panelName;
                controlPrivateAreaPanelsEx(e.target, panelName);
            });
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

const handleMenuBtnClicked = () => {
    document.body.classList.toggle('scroll-stop');
    const menu = document.querySelector('.main-menu');
    const pageOverlay = document.querySelector('.page-overlay');

    menu.classList.toggle('hide');
    pageOverlay.classList.toggle('hide');
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