

const miesiace = ['stycznia', 'lutego', 'marca', 'kwietnia', 'maja', 'czerwca', 'lipca', 'sierpnia',  'września', 'października', 'listopada', 'grudnia' ];

function funkcje() {
    let news1 = document.getElementById("news1");
    let news2 = document.getElementById("news2");
    news1.innerHTML = powitanie()+"<br/>"+data()+"<br/>";
    news2.innerHTML = dniDoUrodzin();
}

function powitanie() {
    let dzisiaj = new Date();
    let godzina = dzisiaj.getHours();
    if( (godzina<18) && (godzina>6) ) {
        return 'Dzień dobry,';
    } else {
        return 'Dobry wieczór,';
    }
}

function data() {
    let dzisiaj = new Date();
    let dzien =  dzisiaj.getDate();
    let miesiac = miesiace[dzisiaj.getMonth()];
    let rok = dzisiaj.getFullYear();

    return 'dzisiaj jest '+ dzien + ' ' +  miesiac + ' ' + ' '  + rok + ' r.';
}

function dniDoUrodzin() {
    const dzisiaj = new Date();
    const rokBiezacy = dzisiaj.getFullYear();
    const miesiacUrodzenia = 2;
    const dzienUrodzenia = 5;
    const rokUrodzenia = 2001;
    const wiek = rokBiezacy-rokUrodzenia;

    const dataUrodzin = new Date(rokBiezacy, miesiacUrodzenia, dzienUrodzenia);
    const dataUrodzin2 = new Date(rokUrodzenia, miesiacUrodzenia, dzienUrodzenia);
    const milisekundyWJednymDniu = 1000 * 60 * 60 * 24;
    let dniDoUrodzin = (dataUrodzin - dzisiaj) / milisekundyWJednymDniu;
    const options = { day: 'numeric', month: 'long', year: 'numeric' };
    const dataUrodzinFormatted = dataUrodzin2.toLocaleDateString('pl-PL', options);


    if (dniDoUrodzin < 0) {
        dniDoUrodzin = (dataUrodzin - dzisiaj) / milisekundyWJednymDniu;
        dniDoUrodzin = Math.ceil(dniDoUrodzin);


        return 'Autor miał '+wiek+' urodziny ' +(-dniDoUrodzin)+ ' dni temu. (' +dataUrodzinFormatted+')'
    }
    dniDoUrodzin = Math.ceil(dniDoUrodzin);
    return 'Autor będzie miał '+ wiek+ ' urodziny za '+ dniDoUrodzin+ ' dni. (' +dataUrodzinFormatted+')'
}

function zegarek(){
    let data = new Date();
    let godzina = data.getHours();
    let minuta = data.getMinutes();
    let sekunda = data.getSeconds();

    if (minuta<10) minuta="0"+minuta;
    if (sekunda<10) sekunda="0"+sekunda;
    let stopka =document.getElementById("stopka");
    stopka.innerHTML="&copy;2023 SM | "+godzina+":"+minuta+":"+sekunda;
}