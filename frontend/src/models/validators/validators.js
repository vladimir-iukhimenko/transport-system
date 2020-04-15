export function isCorrectAutonumber(number) {
    const format = new RegExp("[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$");
    return format.test(number.toUpperCase());
}