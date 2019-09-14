"use strict";

var formPrevod = document.querySelector("#formPrevod");
var editMile = document.querySelector("#editMile");
var editKilometry = document.querySelector("#editKilometry");

formPrevod.addEventListener("submit", priStiskuBtnProvedPrevod, true);

function priStiskuBtnProvedPrevod(eventInfo) {
    odesliHttpRequest(editMile.value);
    eventInfo.preventDefault();
}

function odesliHttpRequest(teloPozadavku) {
    var httpKlient = new XMLHttpRequest();
    httpKlient.onload = obsluhaUspesneKomunikace;
    httpKlient.onerror = obsluhaChybyKomunikace;
    httpKlient.open("POST", window.location + "/prevod", true);
    httpKlient.setRequestHeader("Content-Type", "text/plain");
    httpKlient.setRequestHeader("Accept", "text/plain");
    httpKlient.send(teloPozadavku);
}

function obsluhaUspesneKomunikace() {
    console.log(arguments);
    editKilometry.value = this.response;
}

function obsluhaChybyKomunikace(statusCode, statusText) {
    if (typeof statusCode === "number") {
        if (statusCode === 404) {
            zobrazHlasku("Serve nenalezen");
        } else {
            zobrazHlasku("Chyba " + statusCode + ": " + statusText);
        }
    } else {
        zobrazHlasku("Chyba komunikace se serverem: " + statusText);
    }
}

function zobrazHlasku(text) {
    alert(text);
}