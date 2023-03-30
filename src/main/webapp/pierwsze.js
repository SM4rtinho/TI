const button = document.querySelector('#licz');

function showPrimes() {
    const num = parseInt(document.querySelector('#prime').value);
    let primes = [];
    let i = 2;
    while (primes.length < num) {
        if (isPrime(i)) {
            primes.push(i);
        }
        i++;
    }
    let list = document.querySelector('#lista');
    list.innerHTML = "";
    for (let j = 0; j < primes.length; j++) {
        let item = document.createElement("li");
        item.appendChild(document.createTextNode(primes[j]));
        list.appendChild(item);
    }
}

function isPrime(n) {
    if (n <= 1) {
        return false;
    }
    for (let i = 2; i <= Math.sqrt(n); i++) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}
button.addEventListener('click', showPrimes)