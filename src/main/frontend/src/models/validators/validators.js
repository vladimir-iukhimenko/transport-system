export function isCorrectAutonumber(number) {
    const format = new RegExp("[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$");
    return format.test(number.toUpperCase());
}

export function isCorrectUsername(username) {
    const format = new RegExp("[a-zA-Z._]+");
    return format.test(username);
}