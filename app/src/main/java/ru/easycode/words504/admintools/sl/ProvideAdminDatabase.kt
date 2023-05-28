package ru.easycode.words504.admintools.sl

import ru.easycode.words504.admintools.core.cache.AdminDataBase

interface ProvideAdminDatabase {
    fun provideAdminDatabase(): AdminDataBase.Base
}
