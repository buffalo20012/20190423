$(function () {
    initialize();
});

let initialize = async () => {
    let response = $.get('http://localhost:8080/findpost/1');
    console.log(response);
};