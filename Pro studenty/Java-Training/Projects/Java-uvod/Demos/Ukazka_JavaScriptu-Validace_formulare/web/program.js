"use strict";

var elementJmeno;
var elementPrijmeni;
var elementEmail;
var elementEmailChyba;
var elementHeslo1;
var elementHeslo2;
var elementHesloChyba;

function priZmeneEmailu() {
    var email = elementEmail.value;
    if (email.match(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/) !== null) {
        elementEmailChyba.innerHTML = "";
    } else {
        elementEmailChyba.innerHTML = "<- Neplatný email";
    }
}

function priZmeneHesla() {
    var heslo1 = elementHeslo1.value;
    var heslo2 = elementHeslo2.value;
    if (heslo1 === heslo2) {
        elementHesloChyba.innerHTML = "";
    } else {
        elementHesloChyba.innerHTML = "<- Hesla nejsou stejná";
    }
}

function priNacteniStranky() {
    elementJmeno = document.querySelector("#jmeno");
    elementPrijmeni = document.querySelector("#prijmeni");
    elementEmail = document.querySelector("#email");
    elementEmailChyba = document.querySelector("#emailChyba");
    elementHeslo1 = document.querySelector("#heslo1");
    elementHeslo2 = document.querySelector("#heslo2");
    elementHesloChyba = document.querySelector("#hesloChyba");

    elementEmail.addEventListener("input", priZmeneEmailu, false);
    elementHeslo1.addEventListener("input", priZmeneHesla, false);
    elementHeslo2.addEventListener("input", priZmeneHesla, false);
}


window.addEventListener("load", priNacteniStranky);
