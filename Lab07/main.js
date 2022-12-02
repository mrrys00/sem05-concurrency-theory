// ############## - DEFINITIONS - ##############

var Philosopher = function (id, forks) {
    this.id = id;
    this.forks = forks;
    this.f1 = id % forks.length;
    this.f2 = (id + 1) % forks.length;
    return this;
}

var Fork = function () {
    this.state = 0;
    return this;
}

var Conductor = function (count) {
    this.count = count;
    return this;
}

// ############## - FORK SEMAPHORE - ##############

Fork.prototype.acquire = function (cb) {
    let that = this;
    let acquire = function (timeout, cb) {
        if (that.state == 0) {
            that.state = 1;
            cb()
        }
        else {
            setTimeout(function () { acquire(2 * timeout, cb); }, timeout);
        }
    }
    setTimeout(function () { acquire(2, cb); }, 1)
}

Fork.prototype.release = function () {
    this.state = 0;
}

// ############## - CONDUCTOR "SEMAPHORE" - ##############

Conductor.prototype.acquire = function (cb) {
    let that = this;
    let acquire = function (timeout, cb) {
        if (that.count > 0) {
            that.count--;
            cb()
        }
        else {
            setTimeout(function () { acquire(2 * timeout, cb); }, timeout);
        }
    }

    setTimeout(function () { acquire(2, cb); }, 1)
}

Conductor.prototype.release = function () { 
    this.count++;
}

// #############################

Philosopher.prototype.startNaive = function (count) {
    var forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;

    var recursiveAction = function (count) {
        forks[f1].acquire(function () {
            console.log("p: " + id + "; left fid: " + f1);
            forks[f2].acquire(function () {
                console.log("p: " + id + "; right fid: " + f2);
                setTimeout(function () {
                    console.log("p: " + id + ": forks released (" + f1 + ", " + f2);
                    forks[f1].release();
                    forks[f2].release();
                    count--;
                    if (count > 0)
                        recursiveAction(count)
                }, Math.floor((Math.random() * 2000) + 1));
            });
        });
    }

    recursiveAction(count);
}

// #############################

Philosopher.prototype.startAsym = function (count) {
    var forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;

    var recursiveAction = function (count) {
        forks[(id % 2 == 0) ? f1 : f2].acquire(function () {
            // dla parzystego ID zacznij od lewego widelca
            console.log("p: " + id + "; left fid: " + f1);
            forks[(id % 2 == 0) ? f2 : f1].acquire(function () {
                // dla nieparzystego ID zacznij od prawego widelca
                console.log("p: " + id + "; right fid: " + f2);
                setTimeout(function () {
                    console.log("p: " + id + ": forks released (" + f1 + ", " + f2);
                    forks[f1].release();
                    forks[f2].release();
                    count--;
                    if (count > 0)
                        recursiveAction(count)
                }, Math.floor((Math.random() * 2000) + 1));
            });
        });
    }

    recursiveAction(count);
}

// #############################

Philosopher.prototype.startConductor = function (count) {
    var forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;

    var recursiveAction = function (count) {
        conductor.acquire(function () {
            console.log("p: " + id + ": -> table");
            forks[(id % 2 == 0) ? f1 : f2].acquire(function () {
                console.log("p: " + id + "; left fid: " + f1);
                forks[(id % 2 == 0) ? f2 : f1].acquire(function () {
                    console.log("p: " + id + "; right fid: " + f2);
                    setTimeout(function () {
                        console.log("p: " + id + ": forks released " + f1 + ", " + f2 + ", table ->");
                        forks[f1].release();
                        forks[f2].release();
                        conductor.release();
                        count--;
                        if (count > 0) 
                            recursiveAction(count)
                    }, Math.floor((Math.random() * 2000) + 1));
                });
            });
        });
    }

    recursiveAction(count);
}

// #############################

var forkCount = 5;
var philosopherCount = 5;

var forks = [];
var philosophers = []

var conductor = new Conductor(forkCount - 1);

for (var i = 0; i < forkCount; i++) {
    forks.push(new Fork());
}

for (var i = 0; i < philosopherCount; i++) {
    philosophers.push(new Philosopher(i, forks));
}

// console.log(philosophers)

for (var i = 0; i < philosopherCount; i++) {
    philosophers[i].startNaive(10);
    philosophers[i].startAsym(10);
    philosophers[i].startConductor(10);
}
