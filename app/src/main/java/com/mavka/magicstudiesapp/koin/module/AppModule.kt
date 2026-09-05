package com.mavka.magicstudiesapp.koin.module

import androidx.room.Room
import com.mavka.magicstudiesapp.data.mapper.IconMapper
import com.mavka.magicstudiesapp.data.repository.QuestRepositoryImpl
import com.mavka.magicstudiesapp.data.storage.AppDatabase
import com.mavka.magicstudiesapp.domain.provider.QuestIconProvider
import com.mavka.magicstudiesapp.domain.repository.QuestRepository
import com.mavka.magicstudiesapp.presentation.screens.quests.QuestsViewModel
import com.mavka.magicstudiesapp.presentation.screens.quests.details.DetailsViewModel
import com.mavka.magicstudiesapp.presentation.screens.quests.stats.StatsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val dataModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "magic_studies_database"
        ).fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }

    single { get<AppDatabase>().questDao() }

    single { IconMapper() }
    single<QuestIconProvider> { get<IconMapper>() }

    single<QuestRepository> {
        QuestRepositoryImpl(questDao = get(), mapper = get())
    }
}

val uiModule = module {
    viewModelOf(::QuestsViewModel)
    viewModelOf(::DetailsViewModel)
    viewModelOf(::StatsViewModel)
}

