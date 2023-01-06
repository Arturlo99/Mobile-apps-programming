package com.example.a6

class FieldOfStudy {
    var id: Int = 0
    var name: String
    var speciality: String? = null

    constructor(id: Int, name: String, speciality: String) {
        this.id = id
        this.name = name
        this.speciality = speciality
    }

    constructor(name: String, speciality: String) {
        this.name = name
        this.speciality = speciality
    }
}
