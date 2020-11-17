let points = "";
const pt = svg.createSVGPoint();
var flag = false;

if (localStorage.getItem('points') === null)
    localStorage.setItem('points', "");

document.getElementById("triangle").addEventListener("mouseup", function () {
    flag = true;
});

document.getElementById("rectangle").addEventListener("mouseup", function () {
    flag = true;
});

document.getElementById("half_circle").addEventListener("mouseup", function () {
    flag = true;
});


var radius = document.getElementById("form_1:r").value;
const draw = function (event) {
    if (localStorage.getItem('points') === null)
        localStorage.setItem('points', "");
    let circle = document.createElementNS("http://www.w3.org/2000/svg", 'circle');
    pt.x = event.clientX;
    pt.y = event.clientY;
    const cursorPt = pt.matrixTransform(svg.getScreenCTM().inverse());
    let color;
    if (setColor(Number((cursorPt.x - 150) * (radius / 100)),
        Number((150 - cursorPt.y) * (radius / 100)),
        Number(radius)))
        color = "green";
    else
        color = "red";
    circle.style.fill = color;
    circle.style.r = "3";
    circle.style.cx = cursorPt.x;
    circle.style.cy = cursorPt.y;
    circle.className = "point";
    document.getElementById("svg").appendChild(circle);
    // console.log(cursorPt.x);
    // console.log(cursorPt.y);
    //if (localStorage.getItem("points"))
        localStorage.setItem("points", localStorage.getItem("points").concat(String(cursorPt.x).concat(";").concat(String(cursorPt.y).concat(";")).concat(color).concat(";")));
    // else {
    //     localStorage.setItem("points", String(cursorPt.x).concat(";").concat(String(cursorPt.y)));
    // }
    console.log(localStorage.getItem("points"));
    console.log(radius);
    //console.log(circle);
    //if (radius >= 2 && radius <= 5)
    _send([
        {
            name: 'x',
            value: (cursorPt.x - 150) * (radius / 100)
        },
        {
            name: 'y',
            value: (150 - cursorPt.y) * (radius / 100)
        },
        {
            name: 'r',
            value: radius
        },
        ]);
    console.log("zalupa");
    // send([["x", (cursorPt.x - 150) * (r / 100)], ["y", (150 - cursorPt.y) * (r / 100), r]]);
};

function _send() {

}

document.getElementById("svg").addEventListener("mouseup", function (e) {
    draw(e);
    //flag = false;
});

if (window.performance) {
    ptn = localStorage.getItem("points").split(";");
    for (let i = 0; i < ptn.length; i += 3) {
        let circle = document.createElementNS("http://www.w3.org/2000/svg", 'circle');
        circle.style.fill = ptn[i + 2];
        circle.style.r = "3";
        circle.style.cx = ptn[i];
        circle.style.cy = ptn[i + 1];
        if (ptn[i] !== "")
            document.querySelector("#svg").appendChild(circle);
    }
}

// redraw
redraw = function (value) {
    console.log("redraw");
    // console.log(document.getElementById("j_idt6:r").value);
    var radius = document.getElementById("form_1:r").value;
    document.querySelectorAll("circle").forEach(function (element) {
        element.remove();
    });

    ptn = localStorage.getItem("points").split(";");
    // for (let i = 0; i < ptn.length - 3; i+=3) {
    //     console.log(ptn[i]);
    //     console.log(ptn[i + 1]);
    //     console.log(ptn[i + 2]);
    // }
    for (let i = 0; i < ptn.length - 3; i += 3) {
        // console.log(1);
         let circle = document.createElementNS("http://www.w3.org/2000/svg", 'circle');
         circle.style.fill = ptn[i + 2];
         circle.style.r = "3";
         circle.style.cx = String(Number(ptn[i]) + Number(radius));
         circle.style.cy = String(Number(ptn[i + 1]) + Number(radius));
         // console.log(String(Number(ptn[i]) + Number(radius)));
         // console.log(Number(ptn[i + 1]));
         document.querySelector("#svg").appendChild(circle);
    }
    // доработать алгоритм перерисовки
    // right


};

function clearData() {
    console.log("clearData");
    document.querySelectorAll("circle").forEach((e) => e.remove());
    localStorage.clear();
}


function setColor(x, y, r) {
    console.log("setColor");
    console.log(x, y, r);
    if (x > 0 && y < 0)
        return false;
    else if (x <= 0 && y <= 0 && (-2 * x - y - r <= 0))
        return true;
    else if (x >= 0 && y >= 0 && (x * x + y * y + 0.17 <= (r * r / 4)))
        return true;
    else return x <= 0 && y >= 0 && (y - 0.000001 <= r) && (x - 0.000001 >= -r / 2);
}
/*drawFromData = function (radius) {
    ptn = localStorage.getItem("points").split(";");
    for (let i = 0; i < ptn.length; i+=3) {
        let circle = document.createElementNS("http://www.w3.org/2000/svg", 'circle');
        circle.style.fill = ptn[i + 2];
        circle.style.r = "3";
        circle.style.cx = ptn[i] + radius;
        circle.style.cy = ptn[i + 1] + radius;
        if (ptn[i] !== "")
            document.querySelector("#svg").appendChild(circle);
    }
};*/