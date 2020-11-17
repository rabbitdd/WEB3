document.addEventListener("DOMContentLoaded", update);
function update() {
    let time = new Date(), h1 = Math.floor(time.getHours() / 10), h2 = time.getHours() % 10, m1 = Math.floor(time.getMinutes() / 10),
        m2 = time.getMinutes() % 10, s1 = Math.floor(time.getSeconds() / 10), s2 = time.getSeconds() % 10;
    document.getElementById("clock").textContent = h1 + "" + h2 + ":" + m1 + "" + m2 + ":" + s1 + "" + s2;
    setTimeout(update, 13000);
}
