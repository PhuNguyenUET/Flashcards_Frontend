package com.example.flashcards.ui.components

class FlashCard () {
    var word: String = ""
    var definition: String = ""
    var isStarred: Boolean = false
    constructor (word: String, definition: String) : this() {
        this.word = word
        this.definition = definition
    }
}