


class kwadratowe {
    #A;
    #B;
    #C;
    #delta;
    #x1;
    #x2;
    constructor(a, b, c) {
        this.#A = a;
        this.#B = b;
        this.#C = c;
        this.#delta = null;
        this.#x1 = null;
        this.#x2 = null;
    }
    get A() {
        return this.#A;
    }

    set A(a) {
        this.#A = a;
    }

    get B() {
        return this.#B;
    }

    set B(b) {
        this.#B = b;
    }

    get C() {
        return this.#C;
    }

    set C(c) {
        this.#C = c;
    }


    getDelta() {
        return this.#delta;
    }

    setDelta(delta) {
        this.#delta = delta;
    }

    getX1() {
        return this.#x1;
    }

    setX1(x1) {
        this.#x1 = x1;
    }

    getX2() {
        return this.#x2;
    }

    setX2(x2) {
        this.#x2 = x2;
    }

    liczDelte() {
        this.#delta = (this.#B * this.#B) - (4 * (this.#A * this.#C));
        return this.#delta
    }
    miejscaZerowe(){
        const d= this.liczDelte();

        if (d > 0){
            console.log(this.#A,this.#B, this.#C, Math.sqrt(d))
            this.#x1 = (((this.#B * -1) - Math.sqrt(d)) / (2* this.#A));
            this.#x2 = (((this.#B * -1) + Math.sqrt(d)) / (2* this.#A));
            console.log(this.#x1, this.#x2)
        } else if (d == 0){
            this.#x1 = (this.#B * -1) / (2 * this.#A);
            this.#x2 = null;
        } else {
            this.#x1 = null;
            this.#x2 = null;
        }
    }
    waliduj(){
        if(this.#A == null || isNaN(+this.#A) || +this.#A == 0)
            return false;
        if(this.#B == null || isNaN(+this.#B))
            return false;
        if(this.#C == null || isNaN(+this.#C))
            return false;

        return true;
    }
    licz(){
        if (this.waliduj()){
            this.miejscaZerowe();
        } else {
            alert('Nie podano prawidłowych danych');
        }
    }
}

const przycisk = document.getElementById("licz");
przycisk.addEventListener("click", function() {
    const a = parseFloat(document.getElementById("wsp_a").value);
    const b = parseFloat(document.getElementById("wsp_b").value);
    const c = parseFloat(document.getElementById("wsp_c").value);

    const rownanie = new kwadratowe(a, b, c);
    rownanie.licz();
    const delta = rownanie.liczDelte();
    if(rownanie.getDelta() == null)
        return false;

    let lista = document.getElementById("lista");
    let x1 = null;
    let x2 = null;

    if(lista.hasChildNodes()){
        while (lista.firstChild){
            lista.removeChild(lista.firstChild)
        }
    }


    let wynik = document.createTextNode('Wynik:');

    if(rownanie.getDelta() > 0){
        x1 = 'x\u2081 = ' + rownanie.getX1();
        x2 = 'x\u2082 = ' + rownanie.getX2();
    } else if (rownanie.getDelta() == 0) {
        x1 = 'x = ' + rownanie.getX1();
    } else{
        x1 = 'Brak miejsc zerowych, \u0394 < 0';
    }

    let ul = document.createElement('ul');
    let li = document.createElement('li');

    let liText = document.createTextNode(x1);
    li.appendChild(liText);
    ul.appendChild(li);


    lista.appendChild(wynik);
    lista.appendChild(ul);

    if (x2) {
        let li2 = document.createElement('li');
        let liText2 = document.createTextNode(x2);

        li2.appendChild(liText2);
        ul.appendChild(li2);
    }

});