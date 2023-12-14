# Rent A Car Application Project

Rent a car application project provides management functionalities for cars,models,users,colors,rentals and invoices. User can add, update, delete or list vehicle by entering the vehicle information. The Rent A Car application project is a Restfull web service project developed using Java 17, Maven and Spring Boot technologies.

##

### In addition and update operations ;

* The ‘kilometer’ field in the car table cannot be less than 0.
* Validation for compliance with the Turkish license plate for the ’plate’ field must be added with regex.
* Year information must be between 2005-2024
* DailyPrice can not be less than 0.
* ModelId and ColorId cannot be less than 0.


## CRUD - Create, Read, Update, Delete

```
    @GetMapping("/getById/{id}")
    public GetByIdCarResponse getById(@PathVariable int id){
        return carService.getById(id);
    }
```

```
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddCarRequest request){
        carService.add(request);
    }
```
```
    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateCarRequest request){
        this.carService.update(request);
    }
```
```
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        this.carService.delete(id);
    }
```

### Database ER Diagram  
![diagram.png](..%2Fdiagram.png)
### Pair 9 - Contributors

1. [Ayşenur Şaşmaz](https://github.com/Aysenursasmaz)
2. [Zeynep Ozan](https://github.com/ozanzzeynep)
3. [Bilal Ekinci](https://github.com/bilaleknc)
4. [Erman İskender](https://github.com/SoldierAdam)
5. [Abdullah Cemil Vançelik](https://github.com/abdullahcemil)
