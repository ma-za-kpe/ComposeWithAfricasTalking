package com.maku.core.network.usecases

import com.maku.core.network.model.AirtimeEntity
import com.maku.core.network.repo.ATComposeRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetCountries @Inject constructor(
  private val atComposeRepository: ATComposeRepository
) {
  suspend operator fun invoke(): Flow<List<AirtimeEntity>> =
    atComposeRepository.getAllCountries()
}
