.PHONY: build
build:
	javac Lab01.java

.PHONY: sync
sync:
	make build
	java Lab01 sync
	make clean

.PHONY: not
not:
	make build
	java Lab01 not
	make clean

.PHONY: prod
prod:
	make build
	java Lab01 prod
	make clean

.PHONY: clean
clean:
	rm *.class
