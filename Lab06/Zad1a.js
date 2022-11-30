
function printAsync(s, cb) {
    var delay = Math.floor((Math.random()*1000)+500);
    setTimeout(function() {
        console.log(s);
        if (cb) cb();
    }, delay);
}

function task1(cb, iter) {
    if (iter == 0) {
        console.log('done!');
        return
    }
    printAsync("1", function() {
        task2(cb, iter);
    });
}

function task2(cb, iter) {
    printAsync("2", function() {
        task3(cb, iter);
    });
}

function task3(cb, iter) {
    printAsync("3", function() {
        task1(cb, iter-1);
    });
}
 
// wywolanie sekwencji zadan
// task1(function() {
//     console.log('done!');
// }, 4);
 
/* 
** Zadanie:
** Napisz funkcje loop(n), ktora powoduje wykonanie powyzszej 
** sekwencji zadan n razy. Czyli: 1 2 3 1 2 3 1 2 3 ... done
** 
*/
 function loop(n) {
    task1(function() {
        console.log('done!');
    }, n);
};

loop(4);
