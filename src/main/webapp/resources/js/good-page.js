document.addEventListener('DOMContentLoaded', ()=>{
    const goodCardActiveImage = document.querySelector('.active-image');
    const smallImages = document.querySelectorAll('.good-image-small img');
    const bigImg = document.querySelector('.good-selected-image img');
    const goodGallery = document.querySelector('.good-gallery');
    const mainMenuCloseBtn = document.querySelector('.close-good-gallery');
    const goodGallerySmallImgs = document.querySelectorAll('.good-gallery__img-preview img');
    const goodGallerySelectedImage = document.querySelector('.good-selected-image');
    const ctrlBtnLeft = document.querySelector('.ctrl-btn-left');
    const ctrlBtnRight = document.querySelector('.ctrl-btn-right');
    const goodVariationBtns = document.querySelectorAll('.good-variations__item');

    smallImages.forEach(el => el.addEventListener('mouseover', e=>{
        const block = e.target.parentElement;
        smallImages.forEach(el => el.parentElement.classList.remove('good-image-small-active'));
        block.classList.add('good-image-small-active');
        const imgSrc = block.children[0].getAttribute('src');
        goodCardActiveImage.children[0].setAttribute('src', imgSrc);
    }));

    goodCardActiveImage.addEventListener('click', ()=>{
        goodGallery.classList.remove('hide');
        document.body.classList.add('scroll-stop');
    });

    mainMenuCloseBtn.addEventListener('click', () => {
        goodGallery.classList.add('hide');
        document.body.classList.remove('scroll-stop');
    });

    goodGallerySmallImgs.forEach(el => el.addEventListener('click', e =>{
        const imgSrc = e.target.getAttribute('src');

        goodGallerySmallImgs.forEach(el => el.parentElement.classList.remove('good-image-small-active'));
        e.target.parentElement.classList.add('good-image-small-active');
        
        bigImg.setAttribute('src', imgSrc);
    }));

    ctrlBtnLeft.addEventListener('click', ()=>{
        const allSmallImagesGallery = document.querySelector('.good-gallery__img-previews').children;
        const currentSelectedElem = Array.from(allSmallImagesGallery).filter(el => el.classList.contains('good-image-small-active'))[0];
        let imgRequiredSelection = Array.from(allSmallImagesGallery).indexOf(currentSelectedElem) - 1;

        if(imgRequiredSelection < 0)
            imgRequiredSelection = allSmallImagesGallery.length-1;

        const imgSrcToShow = allSmallImagesGallery[imgRequiredSelection].children[0].getAttribute('src');
        
        goodGallerySelectedImage.children[0].setAttribute('src', imgSrcToShow);

        currentSelectedElem.classList.remove('good-image-small-active');
        allSmallImagesGallery[imgRequiredSelection].classList.add('good-image-small-active');
    });

    ctrlBtnRight.addEventListener('click', ()=>{
        const allSmallImagesGallery = document.querySelector('.good-gallery__img-previews').children;
        const currentSelectedElem = Array.from(allSmallImagesGallery).filter(el => el.classList.contains('good-image-small-active'))[0];
        let imgRequiredSelection = Array.from(allSmallImagesGallery).indexOf(currentSelectedElem) + 1;

        if(imgRequiredSelection >= allSmallImagesGallery.length)
            imgRequiredSelection = 0;

        const imgSrcToShow = allSmallImagesGallery[imgRequiredSelection].children[0].getAttribute('src');
        
        goodGallerySelectedImage.children[0].setAttribute('src', imgSrcToShow);

        currentSelectedElem.classList.remove('good-image-small-active');
        allSmallImagesGallery[imgRequiredSelection].classList.add('good-image-small-active');
    });

    goodVariationBtns.forEach(el => el.addEventListener('click', ()=>{
        goodVariationBtns.forEach(c => c.classList.remove('good-variations__item-active'));
        el.classList.add('good-variations__item-active');
    }));
});