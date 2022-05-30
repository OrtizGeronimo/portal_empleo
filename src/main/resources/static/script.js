
function changeForm(tipo){
    if (tipo == "emp"){
        document.querySelector(".register-form-candidate").style.display = 'none';
        document.querySelector(".register-form-company").style.display = 'block';
    } else {
        document.querySelector(".register-form-company").style.display = 'none';
        document.querySelector(".register-form-candidate").style.display = 'block';

    }
}
