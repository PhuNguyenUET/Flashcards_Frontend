package com.example.flashcards.ui.components

class CardList () {
    private var numOfWords = 0
    private var words = mutableListOf<Card>()
    var title = ""
    var description = ""
    fun addCard (card: Card) {
        words.add(card)
        numOfWords ++
    }
    fun removeCard (card: Card) {
        words.remove(card)
        numOfWords --
    }
    fun removeAtIdx (idx: Int) {
        words.removeAt(idx)
        numOfWords --
    }
    constructor (words: List<Card>): this() {
        this.words = words.toMutableList()
    }
}