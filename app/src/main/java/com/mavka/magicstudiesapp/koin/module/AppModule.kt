package com.mavka.magicstudiesapp.koin.module

import androidx.room.Room
import com.mavka.magicstudiesapp.data.mapper.IconMapper
import com.mavka.magicstudiesapp.data.repository.QuestRepositoryImpl
import com.mavka.magicstudiesapp.data.storage.AppDatabase
import com.mavka.magicstudiesapp.domain.repository.QuestRepository
import com.mavka.magicstudiesapp.presentation.screens.quests.QuestsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    // Краще використовувати androidContext() замість get() для Room
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "magic_studies_database"
        ).build()
    }

    single { get<AppDatabase>().questDao() }

    single { IconMapper() }

    single<QuestRepository> {
        QuestRepositoryImpl(questDao = get(), mapper = get())
    }
}

val uiModule = module {
    viewModel { QuestsViewModel(get()) }
}

