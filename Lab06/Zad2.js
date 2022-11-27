
function printAsync(s, cb) {
    var delay = Math.floor((Math.random() * 1000) + 500);
    setTimeout(function () {
        console.log(s);
        if (cb) {
            // console.log("callback started")
            cb();
        }
    }, delay);
}

// Napisz funkcje (bez korzytania z biblioteki async) wykonujaca 
// rownolegle funkcje znajdujace sie w tablicy 
// parallel_functions. Po zakonczeniu wszystkich funkcji
// uruchamia sie funkcja final_function. Wskazowka:  zastosowc 
// licznik zliczajacy wywolania funkcji rownoleglych 


function inparallel(parallel_functions, final_function) {
    // solution 1.
    let cnt = 0;
    let arrLen = parallel_functions.length;
    parallel_functions.forEach(cb => {
        cb(() => {
            cnt ++;
            if (cnt == arrLen-1) final_function();
        })
    });

    // solutin 2. - bad
    // for (let i = 0; i < parallel_functions.length-1; i++) {
    //     parallel_functions[i]();
    // }
    // parallel_functions[parallel_functions.length-1](() => D(() => {}))
    
    // solution 3. - recursive
    // myImparallel(parallel_functions, final_function, 0)
}

function myImparallel(parallel_functions, final_function, idx) {
    if (idx == parallel_functions.length) {
        final_function();
    } else {
        parallel_functions[idx](() => myImparallel(parallel_functions, final_function, idx+1));
    }
}

const A = function (cb) { printAsync("A", cb); }
const B = function (cb) { printAsync("B", cb); }
const C = function (cb) { printAsync("C", cb); }
const D = function (cb) { printAsync("Done", cb); }

inparallel([A, B, C, A, B, C, A, B, C, A, B, C], D);
// A(() => {B(() => {C(() => {D(() => {})})})})

// kolejnosc: A, B, C - dowolna, na koncu zawsze "Done" 
