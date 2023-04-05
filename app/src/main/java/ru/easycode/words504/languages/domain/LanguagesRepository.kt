package ru.easycode.words504.languages.domain

interface LanguagesRepository {

    interface Fetch {
        suspend fun fetch()
    }

    interface Languages {
        suspend fun languages(): List<LanguageDomain>
    }

    interface Save {
        suspend fun save(languageCode: String)
    }

    interface ChosenLanguage {
        suspend fun language(): LanguageDomain
    }
}
