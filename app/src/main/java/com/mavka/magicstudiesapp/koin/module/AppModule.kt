package com.mavka.magicstudiesapp.koin.module

import androidx.room.Room
import com.mavka.magicstudiesapp.data.mapper.IconMapper
import com.mavka.magicstudiesapp.data.repository.PathRepositoryImpl
import com.mavka.magicstudiesapp.data.storage.AppDatabase
import com.mavka.magicstudiesapp.domain.provider.PathIconProvider
import com.mavka.magicstudiesapp.domain.provider.PathOverviewProvider
import com.mavka.magicstudiesapp.domain.repository.PathRepository
import com.mavka.magicstudiesapp.presentation.screens.paths.PathsViewModel
import com.mavka.magicstudiesapp.presentation.screens.paths.details.DetailsViewModel
import com.mavka.magicstudiesapp.presentation.screens.paths.stats.StatsViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun dataModule(applicationScope: CoroutineScope) = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "magic_studies_database"
        ).fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }

    single { get<AppDatabase>().pathDao() }

    single { IconMapper() }
    single<PathIconProvider> { get<IconMapper>() }

    single<PathRepository> {
        PathRepositoryImpl(
            pathDao = get(),
            mapper = get(),
            scope = applicationScope
        )
    }

    single {
        PathOverviewProvider(
            pathRepository = get(),
            externalScope = applicationScope
        )
    }
}

val uiModule = module {
    viewModelOf(::PathsViewModel)
    viewModelOf(::DetailsViewModel)
    viewModelOf(::StatsViewModel)
}
