package com.maku.feature.airtime.domain.usecase

import com.maku.core.data.repo.ATComposeRepository
import javax.inject.Inject

class SendAirtime @Inject constructor(
    private val ATComposeRepository: ATComposeRepository
) {

    suspend operator fun invoke(
        apiKey: String,
        name: String,
        recipients: String
    ) = ATComposeRepository.sendAirtime(
        apiKey,
        name,
        recipients
    )
}
