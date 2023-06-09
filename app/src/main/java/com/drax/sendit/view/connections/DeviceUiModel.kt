package com.drax.sendit.view.connections

import com.drax.sendit.data.db.model.DeviceDomain
import com.drax.sendit.domain.network.model.type.DevicePlatform
import com.drax.sendit.domain.network.model.type.DeviceStatus
import formatToDate

data class DeviceUiModel(
    val id: Long,
    val name: String,
    @DevicePlatform val platform: Int,
    @DeviceStatus val status: Int,
    val region: String,
    val meta: String,
    val model: String,
    val iconUrl: String,
    val addedDate: Long,
    val platformVersion: String,
    val appVersion: Int,
    val languageCode: String,
    val lastTouch: String,
    val isThisDevice: Boolean,
)

object DeviceTransformer {
    private val DATE_FORMAT = "hh:mm a - MMM dd ,yyyy"
    fun toUiModel(device: DeviceDomain) = DeviceUiModel(
        id = device.id,
        name = device.name,
        platform = device.platform,
        status = device.status,
        region = device.region,
        meta = device.meta,
        model = device.model,
        iconUrl = device.iconUrl,
        addedDate = device.addedDate,
        platformVersion = device.platformVersion,
        appVersion = device.appVersion,
        languageCode = device.languageCode,
        lastTouch = device.lastTouch.formatToDate(DATE_FORMAT),
        isThisDevice = device.isThisDevice,
    )
}
