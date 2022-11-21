## Wyniki

### Próba 1.

#### Parametry

- [procesor intel code i7 1165g7](https://www.intel.com/content/www/us/en/products/sku/208921/intel-core-i71165g7-processor-12m-cache-up-to-4-70-ghz-with-ipu/specifications.html)
- cores: 4
- threads: 8
- resoultion: 800x600
- max iterations 750
- zoom 150

#### Konfiguracje

1. Ilość zadań równa ilości wątków
2. Ilość zadań 10x większa niż ilości wątków
3. Ilość zadań równa ilości pikseli

#### Wyniki

|Konfiguracja|średnia \[ns\]|średnia \[s\]|odch. st. \[ns\]|odch. st. \[s\]|
|---|---|---|---|---|
|Konfig 1.|4.798440399E9|4.7984|1.624682400075806E8|1.6247|
|Konfig 2.|4.808465218E9|4.8084|2.2187682763821125E8|2.2188|
|Konfig 3.|4.865385843E9|4.8653|2.0782224450917628E8|2.0782|

### Próba 2.

#### Parametry

- [procesor AMD Ryzen 5 2600X](https://www.amd.com/en/products/cpu/amd-ryzen-5-2600x)
- cores: 6
- threads: 12
- resoultion: 800x600
- max iterations 2137
- zoom 412

#### Konfiguracje

1. Ilość zadań równa ilości wątków
2. Ilość zadań 10x większa niż ilości wątków
3. Ilość zadań równa ilości pikseli

#### Wyniki

|Konfiguracja|średnia \[ns\]|średnia \[s\]|odch. st. \[ns\]|odch. st. \[s\]|
|---|---|---|---|---|
|Konfig 1.|1.32704879279E11|132.70|5.043090670642407E9|5.04309|
|Konfig 2.|1.34107533049E11|134.11|3.2203121183061423E9|3.2203|
|Konfig 3.|1.34626827839E11|134.62|5.83832669813946E9|5.8383|

### Próba 3.

#### Parametry

- [procesor AMD Ryzen 5 2600X](https://www.amd.com/en/products/cpu/amd-ryzen-5-2600x)
- cores: 4
- threads: 8
- resoultion: 800x600
- max iterations 2137
- zoom 412

#### Konfiguracje

1. Ilość zadań równa ilości wątków
2. Ilość zadań 10x większa niż ilości wątków
3. Ilość zadań równa ilości pikseli

#### Wyniki

|Konfiguracja|średnia \[ns\]|średnia \[s\]|odch. st. \[ns\]|odch. st. \[s\]|
|---|---|---|---|---|
|Konfig 1.|5.2205512333E11|522.055|1.1525024053319887E10|11.525|
|Konfig 2.|5.11751416094E11|511.7514|1.3121283890759508E10|13.121|
|Konfig 3.|5.17837301531E11|517.8373|1.0457912664957191E10|10.457|

**UWAGA:** Głowy nie dam, że to jest dobrze - w porywach do: uważam, że coś jest źle
