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
	