# Convo-API

Is a Java REST API application based on Spring Framework.

It serves as conversion tool for [Convo](https://github.com/q-13t/Convo) desktop application.

---

## Usage

The server runs on localhost:8080 instance.

URL sample is as follows:
`http://localhost:8080/[method]?from=[unit1]&to=[unit2]&value=[value]`.
Where `method` is one of:

- [/](#mapping)
- [test](#mapping-test)
- [area](#mapping-area)
- [speed](#mapping-speed)
- [length](#mapping-length)
- [weight](#mapping-weight)
- [temperature](#mapping-temperature)
- [error](#mapping-error)

For `unit1` and `unit2` see corresponding mappings.
`value` is a double value passed as plain text.

API response is a JSON string as follows:
`{"result":[value]}`

---

### mapping "/"

Is a simple Hello World page which can be used to test connection to the server.

---

### mapping "/test"

Unlike stated int [Usage](#usage) section this mapping has unique URL pattern:
`http://localhost:8080/test?from=[value1]&to=[value2]`
Where `value1` and `value2` are double values that will be added and returned like so:
Example:

```
Request: http://localhost:8080/test?from=2&to=12
Response: {"result":14.0}
```
---

### mapping "/area"

Allows users to convert areas in units like:

- Millimeter
- Centimeter
- Meter
- Acre
- Hectare
- Kilometer
- Inch
- Feet
- Yard
- Mile

URL request and response are the same as stated in [Usage](#usage) section.

Example:

```
Request: http://localhost:8080/area?from=centimeter&to=mile&value=168465641
Response: {"result":0.006504494763033677}
```

---

### mapping "/speed"

Allows users to convert speeds in units like:

- Meters Per Second `mps`
- Centimeters Per Second `cps`
- Kilometers Per Hour `kph`
- Feet Per Second `fps`
- Miles Per Hour `mph`
- Knots `k`
- Mach (SI standard) `m`

NOTE: `unit1` and `unit2` need to be as `highlighted` units.
URL request and response are the same as stated in [Usage](#usage) section.

Example:

```
Request: http://localhost:8080/speed?from=m&to=kph&value=1
Response: {"result":1062.1670399161067}
```

---

### mapping "/length"

Allows users to convert lengths in units like:

- Millimeter
- Centimeter
- Decimeter
- Meter
- Kilometer
- Inch
- Feet
- Yard
- Mile

URL request and response are the same as stated in [Usage](#usage) section.

Example:

```
Request: http://localhost:8080/length?from=Decimeter&to=Feet&value=6
Response: {"result":1.968503937007874}
```

---

### mapping "/weight"

Allows users to convert weights in units like:

- Milligram
- Gram
- Kilogram
- Ton
- Ounce
- Pound

URL request and response are the same as stated in [Usage](#usage) section.

Example:

```
Request: http://localhost:8080/weight?from=ton&to=kilogram&value=0.35
Response: {"result":350.0}
```

---

### mapping "/temperature"

Allows users to convert temperatures in units like:

- Celsius
- Fahrenheit
- Kelvin

URL request and response are the same as stated in [Usage](#usage) section.

Example:

```
Request: http://localhost:8080/temperature?from=celsius&to=fahrenheit&value=5
Response: {"result":41.0}
```

---

### mapping "/error"

This mapping is replacement of 404 mapping indicating that requested query was either incorrect or parameters missing.

Example:

```
Request: http://localhost:8080/hello?from=centimeters&to=fahrenheit&value=apple
Response: {"error":"Not found","message":"The requested resource was not found"}
```
---
# Note
In general this is not a very complicated API and it serves little purpose, but it is very useful for general workflow and development of [Convo](https://github.com/q-13t/Convo) application.