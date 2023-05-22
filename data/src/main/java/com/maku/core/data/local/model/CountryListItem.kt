package com.maku.core.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maku.core.network.model.AirtimeEntity
import com.maku.core.network.model.AirtimeLimitEntity

@Entity(
    tableName = "country_list"
)
data class CountryListItem(
    val airtimeLimits: List<AirtimeLimitEntity>,
    val code: String,
    val dialCode: String,
    val emoji: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String
) {
    fun toDomain(): AirtimeEntity {
        return AirtimeEntity(
            airtimeLimits,
            code,
            dialCode,
            emoji,
            id,
            name
        )
    }
}
