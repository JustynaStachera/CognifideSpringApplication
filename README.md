# COGNIFIDE LIBRARY WEB APPLICATION

`Version: 1.0`

---

Tools used:

* org.springframework.boot
* com.googlecode.json-simple
* org.apache.commons

---

## BUILDING

To build the project use following command:

`WITHOUT tests:`

```text
mvn clean package -Dmaven.test.skip=true
```

`WITH tests:`

```text
mvn clean package -Dparam=src\main\resources\static\json\books.json
```

## RUNNING

```text
java -Dparam=src\main\resources\static\json\books.json -jar target\cognifide-0.0.1-SNAPSHOT.jar
```

`Port:`

```text
8080
```

***

Application tested on **WINDOWS 10** and created by _Justyna Stachera_. 2018. **ALL RIGHTS RESERVED.**