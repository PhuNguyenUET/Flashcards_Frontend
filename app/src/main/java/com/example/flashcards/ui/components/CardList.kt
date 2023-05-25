package com.example.flashcards.ui.components

import androidx.annotation.DrawableRes
import com.example.flashcards.R

class CardList () {
    private var numOfWords = 0
    private var words = mutableListOf<FlashCard>()
    var title = ""
    var description = ""
    @DrawableRes
    var photoId: Int = R.drawable.profile_photo_1
    var isStarred: Boolean = false
    fun addCard (flashCard: FlashCard) {
        words.add(flashCard)
        numOfWords ++
    }
    fun removeCard (flashCard: FlashCard) {
        words.remove(flashCard)
        numOfWords --
    }
    fun removeAtIdx (idx: Int) {
        words.removeAt(idx)
        numOfWords --
    }

    fun setStar () {
        isStarred = !isStarred
    }

    public constructor (title: String,
                 description: String,
                 @DrawableRes photoId: Int,
                 words: List<FlashCard>): this() {
        this.title = title
        this.description = description
        this.photoId = photoId
        this.words = words.toMutableList()
    }
}