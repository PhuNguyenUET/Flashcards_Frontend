package com.example.flashcards.ui.components

import androidx.annotation.DrawableRes
import com.example.flashcards.R

class CardList () {
    private var numOfWords = 0
    var words: MutableList<FlashCard> = mutableListOf()
    var title = ""
    var description = ""
    @DrawableRes
    var photoId: Int = R.drawable.profile_photo_1
    var isStarred: Boolean = false
    fun removeAtIdx (idx: Int) {
        words.removeAt(idx)
        numOfWords --
    }

    fun addWord (card: FlashCard) {
        words.add(card)
        numOfWords ++
    }

    fun setStar () {
        isStarred = !isStarred
    }

    fun changeId (@DrawableRes id: Int) {
        photoId = id
    }

    fun copy(): CardList {
        var tempWords: MutableList<FlashCard> = mutableListOf()
        for (item in words) {
            tempWords.add(item)
        }
        return CardList(
            title = this.title,
            description = this.description,
            photoId = this.photoId,
            words = tempWords
        )
    }

    constructor (title: String,
                 description: String,
                 @DrawableRes photoId: Int,
                 words: List<FlashCard>): this() {
        this.title = title
        this.description = description
        this.photoId = photoId
        this.words = words.toMutableList()
        this.numOfWords = words.size
    }
}