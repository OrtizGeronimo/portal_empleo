
function changeForm(tipo){
    const inputs = document.querySelectorAll(".input");
    if (tipo == "asp"){
        inputs.forEach((input) => {
            switch (input.id) {
                case "0":
                    input.setAttribute("name", "nombre");
                    input.setAttribute("placeholder", "Nombre");
                    break;
                case "1":
                    input.setAttribute("name", "apellido");
                    input.setAttribute("placeholder", "Apellido");
                    break;
                case "2":
                    input.setAttribute("name", "fnac");
                    input.setAttribute("placeholder", "Fecha de Nacimiento");
                    break;
                case "3":
                    input.setAttribute("name", "dni");
                    input.setAttribute("placeholder", "DNI");
                    break;
                default:
                    console.log("entra default")
                    break;
            }
        })
    } else if (tipo == "emp"){
        inputs.forEach((input) => {
            switch (input.id) {
                case "0":
                    input.setAttribute("name", "RS");
                    input.setAttribute("placeholder", "Razón Social");
                    break;
                case "1":
                    input.setAttribute("name", "cuil");
                    input.setAttribute("placeholder", "CUIL");
                    break;
                case "2":
                    input.setAttribute("name", "fnac");
                    input.setAttribute("type", "text");
                    input.setAttribute("placeholder", "Teléfono");
                    break;
                case "3":
                    input.setAttribute("name", "dni");
                    input.setAttribute("placeholder", "DNI");
                    break;
                default:
                    break;
            }
        })
    }

}
