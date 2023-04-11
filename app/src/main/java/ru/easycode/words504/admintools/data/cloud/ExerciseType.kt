package ru.easycode.words504.admintools.data.cloud

import com.google.gson.annotations.SerializedName

enum class ExerciseType {
    @SerializedName("fillBlank")
    FILL_BLANK,

    @SerializedName("trueFalse")
    TRUE_FALSE,

    @SerializedName("opposites")
    OPPOSITES,

    @SerializedName("synonym")
    SYNONYM,

    @SerializedName("wordDetective")
    WORD_DETECTIVE,

    @SerializedName("choseCorrect")
    CHOSE_CORRECT,

    @SerializedName("matching")
    MATCHING,

    @SerializedName("letterOfCompliant")
    LETTER_OF_COMPLIANT
}
