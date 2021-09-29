const searchInput = document.querySelector('#search-input');
const searchBtn = document.querySelector('.search-btn');
const searchFieldForm = document.querySelector('#user-input-field');

document.addEventListener('DOMContentLoaded', ()=>{

    searchInput.addEventListener('input', ()=>{
        searchFieldForm.value = searchInput.value.trim();
    });

    searchBtn.addEventListener('click', () => {
        document.forms[0].submit();
    });
});