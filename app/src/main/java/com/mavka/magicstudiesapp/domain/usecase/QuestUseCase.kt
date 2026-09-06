package com.mavka.magicstudiesapp.domain.usecase

import com.mavka.magicstudiesapp.domain.provider.QuestIconProvider
import com.mavka.magicstudiesapp.domain.repository.QuestRepository

class QuestUseCase(
    private val questRepository: QuestRepository,
    iconProvider: QuestIconProvider
) {



}