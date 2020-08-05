let formContent = {
    addFormInput() {
        let form = document.createElement("form");
        form.setAttribute("name", "point");
        let inputX = document.createElement("input");
        let inputY = document.createElement("input");
        let labelX = document.createElement("label");
        let labelY = document.createElement("label");
        let li = document.createElement("li");
        let deleteButton = document.createElement("button");
        deleteButton.setAttribute("class", "deleteButton");
        deleteButton.setAttribute("onclick", "formContent.removeFormByButton(this)");
        deleteButton.innerText = "✖";
        labelX.innerText = "x:";
        labelY.innerText = "y:";
        inputX.setAttribute("name", "x");
        inputY.setAttribute("name", "y");
        inputX.setAttribute("step", "any");
        inputY.setAttribute("step", "any");
        inputX.setAttribute("type", "number");
        inputY.setAttribute("type", "number");
        inputX.setAttribute("class", "input-point");
        inputY.setAttribute("class", "input-point");
        inputX.setAttribute("placeholder", "0.0");
        inputY.setAttribute("placeholder", "0.0");
        labelX.appendChild(inputX);
        labelY.appendChild(inputY);
        form.appendChild(labelX);
        form.appendChild(labelY);
        li.appendChild(form);
        li.appendChild(deleteButton);
        document.querySelector(".inputList").appendChild(li);
        formContent.switchFirstForm();
    },
    postShape() {
        let points = document.getElementsByName('point');
        let textArea = document.getElementById("pontsInfo");
        let pointsArr = [];
        for (let point of points) {
            let x = Number(point.x.value),
                y = Number(point.y.value);
            if(!x || !y){
                textArea.textContent = "Invalid input in point " + (pointsArr.length + 1);
                return;
            }
            let newPoint = {"x": x, "y": y};
            pointsArr.push(newPoint);
        }
        let d = JSON.stringify(pointsArr);
        let request = new XMLHttpRequest();
        request.open('POST', '/shapes/add', true);
        request.setRequestHeader('Content-type', 'application/json');
        request.onreadystatechange = function () {
            let textArea = document.getElementById("pontsInfo");
            if (request.readyState === 4 && request.status === 200) {
                textArea.textContent = "Shape added successfully";
            }
            else {
                textArea.textContent = "An error has occurred, shape hasn't been added. Please, try again";
            }
        };
        let data = JSON.stringify({"points":pointsArr});
        console.log(data);
        formContent.removeForms();
        request.send(data);
    },
    resetForm() {
        let textArea = document.getElementById("pontsInfo");
        textArea.textContent = "";
        formContent.removeForms();
    },
    removeForms(){
        let pointForms = document.getElementById("pointForms");
        let points = pointForms.children;
        console.log(points);
        while(pointForms.hasChildNodes()){
            console.log(1);
            pointForms.removeChild(pointForms.firstChild);
        }
        formContent.addFormInput();
    },
    removeFormByButton(button){
        let li = button.parentNode;
        let pointForms = document.getElementById("pointForms");
        if(pointForms.children.length > 1)
            pointForms.removeChild(li);
        formContent.switchFirstForm();
    },
    switchFirstForm(){
        let pointForms = document.getElementById("pointForms");
        let button = pointForms.children[0].querySelector(".deleteButton");
        if(pointForms.children.length === 1)
            button.setAttribute("disabled", "disabled");
        else if(pointForms.children.length === 2)
            button.removeAttribute("disabled");
    }
};

// document.onload = ()=> {
//
//     document.getElementsByName('x')[0].onkeydown = function (e) {
//         return !(/^[А-Яа-яA-Za-z ]$/.test(e.key));  // IE > 9
//     }
//     document.getElementsByName('y')[0].onkeydown = function (e) {
//         return !(/^[А-Яа-яA-Za-z ]$/.test(e.key));  // IE > 9
//     }
//
// }

document.addEventListener('DOMContentLoaded', function(){ // Аналог $(document).ready(function(){
    formContent.addFormInput();

    document.getElementsByName('x')[0].onkeydown = function (e) {
        return !(/^[А-Яа-яA-Za-z ]$/.test(e.key));  // IE > 9
    };
    document.getElementsByName('y')[0].onkeydown = function (e) {
        return !(/^[А-Яа-яA-Za-z ]$/.test(e.key));  // IE > 9
    };
});
