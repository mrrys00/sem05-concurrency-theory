.PHONY: archiveLab01
archiveLab01:
	mkdir -p archives/
	zip -r archives/archiveLab01.zip Lab01/
	tar czvf archives/archiveLab01.tar.gz Lab01/

.PHONY: archiveLab02
archiveLab02:
	mkdir -p archives/
	zip -r archives/archiveLab02.zip Lab02/
	tar czvf archives/archiveLab02.tar.gz Lab02/

.PHONY: archiveLab03
archiveLab03:
	mkdir -p archives/
	zip -r archives/archiveLab03.zip Lab03/
	tar czvf archives/archiveLab03.tar.gz Lab03/

.PHONY: archiveLab04
archiveLab04:
	mkdir -p archives/
	zip -r archives/archiveLab04.zip Lab04/
	tar czvf archives/archiveLab04.tar.gz Lab04/

.PHONY: archiveLab05
archiveLab05:
	mkdir -p archives/
	zip -r archives/archiveLab05.zip Lab05/
	tar czvf archives/archiveLab05.tar.gz Lab05/

.PHONY: archiveLab06
archiveLab06:
	mkdir -p archives/
	zip -r archives/archiveLab06.zip Lab06/
	tar czvf archives/archiveLab06.tar.gz Lab06/
