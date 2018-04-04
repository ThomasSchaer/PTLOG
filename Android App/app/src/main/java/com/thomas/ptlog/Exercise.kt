package com.thomas.ptlog


class Exercise {
    var name: String? = null
    var description: String? = null

    constructor() {
        // Used by hibernate
    }

    constructor(name: String, description: String) {
        this.name = name
        this.description = description
    }
}