package com.example.flashcards.ui.components

class FlashCard () {
    var word: String = ""
    var definition: String = ""
    var isStarred: Boolean = false

    fun copy(): FlashCard {
        return FlashCard(word = this.word, definition = this.definition)
    }
    constructor (word: String, definition: String) : this() {
        this.word = word
        this.definition = definition
    }
}