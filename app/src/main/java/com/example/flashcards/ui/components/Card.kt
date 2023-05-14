package com.example.flashcards.ui.components

class Card () {
    var word: String = ""
    var definition: String = ""
    constructor (word: String, definition: String) : this() {
        this.word = word
        this.definition = definition
    }
}